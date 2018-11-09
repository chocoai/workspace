package com.yhcrt.iHealthCloud.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.HdSleep;
import com.yhcrt.iHealthCloud.entity.HdSleepExample;
import com.yhcrt.iHealthCloud.entity.MemberDevice;
import com.yhcrt.iHealthCloud.mapper.HdSleepMapper;
import com.yhcrt.iHealthCloud.mapper.MemberDeviceMapper;
import com.yhcrt.iHealthCloud.pojo.SleepDTO;
import com.yhcrt.iHealthCloud.service.HealthDataSleepService;
import com.yhcrt.iHealthCloud.service.SysSequenceService;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.DateUtil;

/**
 * 
 * @author huzelin
 *
 */
@Service
public class HealthDataSleepServiceImpl extends BaseService implements HealthDataSleepService {

	// debug mode
	protected boolean isDebug = true;

	@Autowired
	private HdSleepMapper sleepMapper;
	@Autowired
	private MemberDeviceMapper memberDeviceMapper;
	@Autowired
	private SysSequenceService sequenceService;

	@Override
	public String getSleepByTime(JSONObject pdataObj) {

		// 获取查询时间条件
		String memberId = getMemberId(pdataObj);
		String startTime = getStartTime(pdataObj);
		String endTime = getEndTime(pdataObj);
		endTime = endTime + " 23:59:59";
		
		// 对数据进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, memberId, startTime, endTime)) {
			// 获取最新睡眠数据
			SleepDTO latestSleepData =  sleepMapper.getLatestSleepData(memberId);
			// 根据时间条件进行查询
			List<SleepDTO> sleepDatas = sleepMapper.listSleepDataByTime(memberId, startTime, endTime);
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("latestSleepData", latestSleepData);
			jsonObj.put("sleepDatas", sleepDatas);
			pdataObj.put(Const.TAG_BIZ, jsonObj);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			
		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getSleepByMemberId(JSONObject pdataObj) {

		// 获取参数
		String memberId = getMemberId(pdataObj);
		String currentPage = getCurrentPage(pdataObj);
		String pageSize = getPageSize(pdataObj);

		// 对参数进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, memberId)) {

			// 向数据库请求数据并判断是否分页
			List<SleepDTO> list;
			if (judgePageInfoIsLegal(currentPage, pageSize)) {

				PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
				list = sleepMapper.listSleepDataByMemberId(memberId);
				PageInfo<SleepDTO> p = new PageInfo<>(list);
				setPagingData(pdataObj, p.getPages(), p.getPageNum());

			} else {
				list = sleepMapper.listSleepDataByMemberId(memberId);
			}

			requestSucceed(pdataObj, list, "");

		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String insertSleep(JSONObject pdataObj) {

		// 获取参数
		String imei = getImei(pdataObj);
		String memberId = getMemberId(pdataObj);
		if (StringUtils.isEmpty(memberId)) {
			MemberDevice memberDevice = memberDeviceMapper.selectByImei(imei, Constants.STATUS_NORMAL);
			if (memberDevice != null) {
				memberId = memberDevice.getMemberId() + "";
			}
		}
		String sleepQuality = getSleepQuality(pdataObj);

		// 对取出的值进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, memberId, sleepQuality)) {

			// 向数据库中插入数据
			HdSleep sleep = new HdSleep();
			int cid = sequenceService.getSequenceValue(Constants.SequenceName.HD_SLEEP);
			String uploadTime = DateUtil.getDateTime();
			sleep.setCid(cid);
			sleep.setImei(imei);
			sleep.setMemberId(Integer.parseInt(memberId));
			sleep.setUploadTime(uploadTime);
			sleep.setDataDate(uploadTime);
			sleep.setSleepQuality(Integer.valueOf(sleepQuality));

			// 判断数据插入是否成功
			int insert = sleepMapper.insert(sleep);
			if (insert > 0) {
				requestSucceed(pdataObj, sleep, "");
			} else {
				requestFailed(pdataObj, "insert failed");
			}
		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getSleepForAWeekByMemberId(JSONObject pdataObj) {
		// 获取参数
		String memberId = getMemberId(pdataObj);

		int totalSize = 0; // 数据总数目
		double valueTotal = 0; // 数据总和
		double valueMax = 0; // 数据最大值
		double valueMin = 0; // 数据最小值
		double valueAve = 0; // 数据平均值

		if (judgeAgumentsIsLegal(pdataObj, memberId)) {
			// get time
			List<String> dateList = getDateString4week();
			// 向数据库中请求近7天的所有数据
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("memberId", Integer.parseInt(memberId));
			map.put("startTime", dateList.get(dateList.size() - 1));
			map.put("endTime", dateList.get(0));
			List<HdSleep> dataList = sleepMapper.selectHdSleep(map);
			if(!dataList.isEmpty()){
				totalSize = dataList.size();
			}

			// 筛选7天中 每天的最近一条数据
			Map<String, HdSleep> dataMap4Week = new HashMap<>();
			for (HdSleep hdSleep : dataList) {
				String time = hdSleep.getUploadTime();
				double value = hdSleep.getDeepSleepDuration();

				// 获取总值、最大值、最小值
				valueTotal += value;
				if (valueMin == 0) {
					valueMin = value;
				}
				if (value > valueMax) {
					valueMax = value;
				}
				if (value < valueMin) {
					valueMin = value;
				}

				// 第一天
				if (!dataMap4Week.containsKey("day0") && dateList.get(0).contains(time)) {
					dataMap4Week.put("day0", hdSleep);
				}

				// 第二天
				if (!dataMap4Week.containsKey("day1") && dateList.get(1).contains(time)) {
					dataMap4Week.put("day1", hdSleep);
				}

				// 第三天
				if (!dataMap4Week.containsKey("day2") && dateList.get(2).contains(time)) {
					dataMap4Week.put("day2", hdSleep);
				}

				// 第四天
				if (!dataMap4Week.containsKey("day3") && dateList.get(3).contains(time)) {
					dataMap4Week.put("day3", hdSleep);
				}

				// 第五天
				if (!dataMap4Week.containsKey("day4") && dateList.get(4).contains(time)) {
					dataMap4Week.put("day4", hdSleep);
				}

				// 第六天
				if (!dataMap4Week.containsKey("day5") && dateList.get(5).contains(time)) {
					dataMap4Week.put("day5", hdSleep);
				}

				// 第七天
				if (!dataMap4Week.containsKey("day6") && dateList.get(6).contains(time)) {
					dataMap4Week.put("day6", hdSleep);
				}
			}

			// 封装数据
			List<HdSleep> data4Week = new ArrayList<>();
			for (int i = dateList.size()-1; i >=0 ; i--) {
				HdSleep hdSleep = dataMap4Week.get("day" + i);
				if (hdSleep == null) {
					hdSleep = new HdSleep();
					hdSleep.setUploadTime(dateList.get(i).substring(5));
				}
				data4Week.add(hdSleep);
			}

			// 取平均值
			if (totalSize == 0)
				totalSize = 1;
			valueAve = (valueTotal * 100 / totalSize) / 100d;

			requestSucceed(pdataObj, data4Week, "");
			putMaxMinAveValueToBizObj(pdataObj, valueMax, valueMin, valueAve);

		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public List<HdSleep> getSleepForAWeekByMemberId(String memberId) {
		HdSleepExample example = new HdSleepExample();
		example.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId));
		example.setOrderByClause("cid desc");
		return sleepMapper.selectByExample(example);
	}

}
