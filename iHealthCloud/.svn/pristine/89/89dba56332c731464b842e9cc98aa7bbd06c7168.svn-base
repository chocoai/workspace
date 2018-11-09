package com.yhcrt.iHealthCloud.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.entity.HdBloodGlucose;
import com.yhcrt.iHealthCloud.entity.HdBloodGlucoseExample;
import com.yhcrt.iHealthCloud.mapper.HdBloodGlucoseMapper;
import com.yhcrt.iHealthCloud.pojo.BloodGlucoseBO;
import com.yhcrt.iHealthCloud.pojo.BloodGlucoseDTO;
import com.yhcrt.iHealthCloud.service.HealthDataBloodGlucoseService;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.DateUtil;

/**
 * 
 * @author huzelin
 *
 */
@Service
public class HealthDataBloodGlucoseServiceImpl extends BaseService implements HealthDataBloodGlucoseService {

	protected boolean isDebug = true;

	@Autowired
	private HdBloodGlucoseMapper glucoseMapper;

	@Override
	public String getGlucoseByTime(JSONObject pdataObj) {

		// 获取查询时间条件
		String memberId = getMemberId(pdataObj);
		String startTime = getStartTime(pdataObj);
		String endTime = getEndTime(pdataObj);
		endTime = endTime + " 23:59:59";
		
		// 对数据进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, memberId, startTime, endTime)) {

			// 获取最新的血糖测量数据
			BloodGlucoseDTO latestBloodGlucose = glucoseMapper.getLatestBloodGlucose(memberId);
			latestBloodGlucose = latestBloodGlucose == null ? new BloodGlucoseDTO() : latestBloodGlucose;
			// 根据时间条件进行查询
			List<BloodGlucoseDTO> list = glucoseMapper.listBloodGlucoseByTime(memberId, startTime, endTime);
			JSONArray bloodGlucoses = filterSameTypeDataAtOneDay(list);
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("latestBloodGlucose", latestBloodGlucose);
			jsonObj.put("bloodGlucoses", bloodGlucoses);
			pdataObj.put(Const.TAG_BIZ, jsonObj);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);

		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getGlucoseByMemberId(JSONObject pdataObj) {

		// 获取参数
		String memberId = getMemberId(pdataObj);
		String currentPage = getCurrentPage(pdataObj);
		String pageSize = getPageSize(pdataObj);

		// 对参数进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, memberId)) {

			// 向数据库请求数据并判断是否分页
			List<BloodGlucoseDTO> list;
			if (judgePageInfoIsLegal(currentPage, pageSize)) {
				PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
				list = glucoseMapper.listBloodGlucoseByMemberId(memberId);
				PageInfo<BloodGlucoseDTO> p = new PageInfo<>(list);
				setPagingData(pdataObj, p.getPages(), p.getPageNum());

			} else {
				list = glucoseMapper.listBloodGlucoseByMemberId(memberId);
			}
			requestSucceed(pdataObj, list, "");

		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String insertGlucose(JSONObject pdataObj) {

		// 获取参数
		String imei = getImei(pdataObj);
		String memberId = getMemberId(pdataObj);
		String bgType = getBgType(pdataObj);
		String bgValue = getBgValue(pdataObj);
		String dataSource = getBizObj(pdataObj).getString("data_source");

		// 对参数进行判断
		if (judgeAgumentsIsLegal(pdataObj, memberId, bgType, bgValue)) {

			// 向数据库中插入数据
			HdBloodGlucose glucose = new HdBloodGlucose();
			glucose.setImei(imei);
			glucose.setMemberId(Integer.parseInt(memberId));
			glucose.setBgType(Integer.parseInt(bgType));
			glucose.setBgValue(Float.parseFloat(bgValue));
			glucose.setDataDate(DateUtil.getDate());
			glucose.setUploadTime(DateUtil.getDateTime());
			glucose.setDataSource(dataSource);
			glucoseMapper.insert(glucose);

			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "");

		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getGlucoseForAWeekByMemberId(JSONObject pdataObj) {

		// 获取参数
		String memberId = getMemberId(pdataObj);

		int totalSize = 0; // 数据总数量
		float bgValueTotal = 0; // 血糖数据总和
		float bgValueMax = 0; // 血糖数据(最大)
		float bgValueMin = 0; // 血糖数据(最小)
		float bgValueAve = 0; // 血糖数据(平均)

		if (judgeAgumentsIsLegal(pdataObj, memberId)) {

			// get time
			List<String> dateList = getDateString4week();

			// 向数据库中请求近7天的所有数据
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("memberId", Integer.parseInt(memberId));
			map.put("startTime", dateList.get(dateList.size() - 1));
			map.put("endTime", dateList.get(0));
			List<HdBloodGlucose> dataList = glucoseMapper.selectHdBloodGlucose(map);
			if(!dataList.isEmpty()){
				totalSize = dataList.size();
			}

			// 筛选7天中 每天的最近一条数据
			Map<String, HdBloodGlucose> dataMap4Week = new HashMap<>();
			for (HdBloodGlucose hdBloodGlucose : dataList) {
				String time = hdBloodGlucose.getUploadTime();
				float value = hdBloodGlucose.getBgValue();

				// 获取总值、最大值、最小值
				bgValueTotal += value;
				if (bgValueMin == 0)
					bgValueMin = value;
				if (value > bgValueMax)
					bgValueMax = value;
				if (value < bgValueMin)
					bgValueMin = value;

				// 第一天
				if (!dataMap4Week.containsKey("day0") && dateList.get(0).contains(time)) {
					dataMap4Week.put("day0", hdBloodGlucose);
				}

				// 第二天
				if (!dataMap4Week.containsKey("day1") && dateList.get(1).contains(time)) {
					dataMap4Week.put("day1", hdBloodGlucose);
				}

				// 第三天
				if (!dataMap4Week.containsKey("day2") && dateList.get(2).contains(time)) {
					dataMap4Week.put("day2", hdBloodGlucose);
				}

				// 第四天
				if (!dataMap4Week.containsKey("day3") && dateList.get(3).contains(time)) {
					dataMap4Week.put("day3", hdBloodGlucose);
				}

				// 第五天
				if (!dataMap4Week.containsKey("day4") && dateList.get(4).contains(time)) {
					dataMap4Week.put("day4", hdBloodGlucose);
				}

				// 第六天
				if (!dataMap4Week.containsKey("day5") && dateList.get(5).contains(time)) {
					dataMap4Week.put("day5", hdBloodGlucose);
				}

				// 第七天
				if (!dataMap4Week.containsKey("day6") && dateList.get(6).contains(time)) {
					dataMap4Week.put("day6", hdBloodGlucose);
				}
			}

			// 封装数据
			List<HdBloodGlucose> data4Week = new ArrayList<>();
			for (int i = dateList.size()-1; i >=0 ; i--) {
				HdBloodGlucose hdBloodGlucose = dataMap4Week.get("day" + i);
				if (hdBloodGlucose == null) {
					hdBloodGlucose = new HdBloodGlucose();
					hdBloodGlucose.setUploadTime(dateList.get(i).substring(5));
				}
				data4Week.add(hdBloodGlucose);
			}

			// 取平均值
			if (totalSize == 0)
				totalSize = 1;
			bgValueAve = Math.round((bgValueTotal / totalSize) * 100) / 100f;

			requestSucceed(pdataObj, data4Week, "");
			putMaxMinAveValueToBizObj(pdataObj, bgValueMax, bgValueMin, bgValueAve);

		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public List<HdBloodGlucose> getGlucoseForAWeekByMemberId(String memberId) {
		if (memberId == null || "".equals(memberId)) {
			return new ArrayList<HdBloodGlucose>();
		} else {
			HdBloodGlucoseExample example = new HdBloodGlucoseExample();
			example.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId));
			example.setOrderByClause("cid desc");
			return glucoseMapper.selectByExample(example);
		}
	}

	/**
	 * 将集合中同一天内相同type的数据过滤，只保留最近的一条
	 * 
	 * @param list
	 * @return
	 */
	protected JSONArray filterSameTypeDataAtOneDay(List<BloodGlucoseDTO> list) {
		
		Map<String, BloodGlucoseDTO> map = new HashMap<>();
		JSONArray jsonArray = new JSONArray();
		// 数据日期
		List<String> dataDates = new ArrayList<String>();
		List<BloodGlucoseBO> bloodGlucoses = new ArrayList<BloodGlucoseBO>();
		// 血糖类型
		final String[] bgType = {"0","1","2","3","4","5","6","7"};
		
		for (BloodGlucoseDTO bloodGlucose : list) {
			// 对每天的血糖检测数据过滤，每种血糖类型的检测数据取最新的一条，注意原始血糖数据列表必须是已经按时间倒序排列的
			String key = bloodGlucose.getDataDate() + bloodGlucose.getBgType();
			if (map.containsKey(key)) {
				continue;
			}else{
				map.put(key, bloodGlucose);
			}
			
			String dataDate = bloodGlucose.getDataDate();
			if (!dataDates.contains(dataDate)) {
				
				// 对每天的血糖检测数据进行遍历，对未进行检测的血糖类型数据进行补齐
				List<String> bgTypes = new ArrayList<String>();
				for (int i = 0; i < bloodGlucoses.size(); i++) {
					bgTypes.add(bloodGlucoses.get(i).getBgType());
				}
				
				for(int j = 0; j < bgType.length; j++ ){
					if (!bgTypes.contains(bgType[j])) {
						bloodGlucoses.add(j, new BloodGlucoseBO(bgType[j],"0"));
					}
				}
				
				dataDates.add(dataDate);
				bloodGlucoses = new ArrayList<BloodGlucoseBO>();
				BloodGlucoseBO bg = new BloodGlucoseBO();
				BeanUtils.copyProperties(bloodGlucose, bg);
				bloodGlucoses.add(bg);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("dataDate", dataDate);
				jsonObject.put("bloodGlucoses", bloodGlucoses);
				jsonArray.add(jsonObject);
			}else{
				BloodGlucoseBO bg = new BloodGlucoseBO();
				BeanUtils.copyProperties(bloodGlucose, bg);
				bloodGlucoses.add(bg);
			}
		}
		
		// 对每天的血糖检测数据进行遍历，对未进行检测的血糖类型数据进行补齐
		List<String> bgTypes = new ArrayList<String>();
		for (int i = 0; i < bloodGlucoses.size(); i++) {
			bgTypes.add(bloodGlucoses.get(i).getBgType());
		}
		
		for(int j = 0; j < bgType.length; j++ ){
			if (!bgTypes.contains(bgType[j])) {
				bloodGlucoses.add(j, new BloodGlucoseBO(bgType[j],"0"));
			}
		}
		
		return jsonArray;
	}

}
