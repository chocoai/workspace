package com.yhcrt.iHealthCloud.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.AlarmDefine;
import com.yhcrt.iHealthCloud.entity.AlarmMsg;
import com.yhcrt.iHealthCloud.entity.HdStep;
import com.yhcrt.iHealthCloud.entity.HdStepExample;
import com.yhcrt.iHealthCloud.entity.MemberDevice;
import com.yhcrt.iHealthCloud.entity.StepSetting;
import com.yhcrt.iHealthCloud.mapper.AlarmDefineMapper;
import com.yhcrt.iHealthCloud.mapper.AlarmMsgMapper;
import com.yhcrt.iHealthCloud.mapper.HdStepMapper;
import com.yhcrt.iHealthCloud.mapper.MemberDeviceMapper;
import com.yhcrt.iHealthCloud.mapper.StepSettingMapper;
import com.yhcrt.iHealthCloud.pojo.HdStepDTO;
import com.yhcrt.iHealthCloud.service.HealthDataStepService;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.DateUtil;
import com.yhcrt.iHealthCloud.util.UUIDGenerator;

/**
 * 
 * @author huzelin
 *
 */
@Service
public class HealthDataStepServiceImpl extends BaseService implements HealthDataStepService {

	// debug mode
	protected boolean isDebug = true;

	@Autowired
	private HdStepMapper stepMapper;
	@Autowired
	private MemberDeviceMapper memberDeviceMapper;
	@Autowired
	private AlarmDefineMapper alarmDefineMapper;
	@Autowired
	private AlarmMsgMapper alarmMsgMapper;
	@Autowired
	private StepSettingMapper stepSettingMapper;

	@Override
	public String getStepByTime(JSONObject pdataObj) {

		// 获取查询时间条件
		String memberId = getMemberId(pdataObj);
		String startTime = getStartTime(pdataObj);
		String endTime = getEndTime(pdataObj);
		endTime = endTime + " 23:59:59";

		// 对数据进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, memberId, startTime, endTime)) {
			// 根据时间条件进行查询
			List<HdStepDTO> list = stepMapper.getStepByTime(Integer.valueOf(memberId), startTime, endTime);
			pdataObj.put(Const.TAG_BIZ, JSON.toJSON(list));
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);

		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return toJsonStringWithOutNull(pdataObj);
	}

	public String getStepByMemberId(JSONObject pdataObj) {

		// 获取参数
		String memberId = getMemberId(pdataObj);
		String currentPage = getCurrentPage(pdataObj);
		String pageSize = getPageSize(pdataObj);

		// 对参数进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, memberId)) {

			// 向数据库请求数据并判断是否分页
			List<HdStep> list;
			if (judgePageInfoIsLegal(currentPage, pageSize)) {

				PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
				list = selectStepListByMemberId(memberId);
				PageInfo<HdStep> p = new PageInfo<>(list);
				setPagingData(pdataObj, p.getPages(), p.getPageNum());

			} else {
				list = selectStepListByMemberId(memberId);
			}

			requestSucceed(pdataObj, list, "");

		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return toJsonStringWithOutNull(pdataObj);
	}

	private List<HdStep> selectStepListByMemberId(String memberId) {
		List<HdStep> list;
		HdStepExample example = new HdStepExample();
		example.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId));
		example.setOrderByClause("cid desc");
		list = stepMapper.selectByExample(example);
		return list;
	}

	@Override
	public String insertFallDow(JSONObject pdataObj) {
		// 获取biz json对象取出其中的值
		JSONObject bizObj = getBizObj(pdataObj);
		String imei = bizObj.getString("imei");
		String datadate = bizObj.getString("data_date");
		String memberId = null;
		String lbs = bizObj.getString("lbs");
		String wifi = bizObj.getString("wifi");
		MemberDevice memberDevice = memberDeviceMapper.selectByImei(imei, Constants.STATUS_NORMAL);
		if (memberDevice != null) {
			memberId = memberDevice.getMemberId() + "";
		}
		// 对取出的值进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, memberId, lbs, wifi)) {
			AlarmDefine alarmDefine = alarmDefineMapper.selectByAlarmType(Constants.AlarmType.FALLDOWN);
			AlarmMsg alarmMsg = new AlarmMsg();
			alarmMsg.setMemberId(Integer.parseInt(memberId));
			alarmMsg.setAlarmType(Constants.AlarmType.BLOODPRESSURE);
			if (alarmDefine != null) {
				String alarmContent = alarmDefine.getAlarmMsg()
						.replaceFirst("\\{}", memberDevice.getMember().getRealName())
						.replaceFirst("\\{}", "(" + lbs + ")").replaceFirst("\\{}", "(" + wifi + ")");
				alarmMsg.setAlarmContent(alarmContent);
			}
			alarmMsg.setStatus(Constants.STATUS_NORMAL);
			alarmMsg.setIsRead(Constants.UNREAD);
			alarmMsg.setCreateTime(datadate);
			alarmMsgMapper.insert(alarmMsg);
		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String insertStep(JSONObject pdataObj) {

		// 获取参数
		String imei = getImei(pdataObj);
		String dataDate = getDataDate(pdataObj);
		String memberId = getMemberId(pdataObj);
		if (StringUtils.isBlank(memberId)) {
			MemberDevice memberDevice = memberDeviceMapper.selectByImei(imei, Constants.STATUS_NORMAL);
			memberId = memberDevice != null ? memberDevice.getMemberId().toString() : memberId;
		}
		String stepCount = getStepCount(pdataObj);
		
		if (StringUtils.isBlank(stepCount)) {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
			return toJsonStringWithOutNull(pdataObj);
		}

		// 向数据库中插入数据
		HdStep step = new HdStep();
		String uploadTime = DateUtil.getDateTime();
		step.setImei(imei);
		if (StringUtils.isNotBlank(memberId)) {
			step.setMemberId(Integer.parseInt(memberId));
		}
		step.setDataDate(dataDate);
		step.setUploadTime(uploadTime);
		step.setStepCount(Integer.parseInt(stepCount));

		// 判断数据插入是否成功
		int insert = stepMapper.insert(step);
		if (insert > 0) {
			requestSucceed(pdataObj, step, "");
		} else {
			requestFailed(pdataObj, "insert failed");
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getStepForAWeekByMemberId(JSONObject pdataObj) {

		// 获取数据
		String memberId = getMemberId(pdataObj);

		boolean isMin = false;
		int valueTotal = 0; // 数据总和
		int valueMax = 0; // 数据最大值
		int valueMin = 0; // 数据最小值
		int valueAve = 0; // 数据平均值

		if (judgeAgumentsIsLegal(pdataObj, memberId)) {
			List<String> dateList = getDateString4week();	//获取最近七天
			// 向数据库请求数据
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("memberId", Integer.parseInt(memberId));
			map.put("startTime", dateList.get(dateList.size() - 1));
			map.put("endTime", dateList.get(0));
			List<HdStep> list = stepMapper.selectHdStep(map);
			List<String> dateStep = new ArrayList<String>();	//存放返回的最近日期
			if (!list.isEmpty()) {
				for (HdStep step : list) {
					dateStep.add(step.getUploadTime());
					// 获取总值、最大值、最小值
					int value = step.getStepCount();
					valueTotal += value;
					if (!isMin) {
						if (value == 0) {
							isMin = true;
						} else {
							if (valueMin == 0) {
								valueMin = value;
							}
							if (value < valueMin) {
								valueMin = value;
							}
						}
					}
					if (value > valueMax) {
						valueMax = value;
					}
				}
				valueAve = valueTotal / list.size();
			}
			// 分别存入7天数据
			for (int n = 0; n < dateList.size(); n++) {
				String date = dateList.get(n).substring(5);
				if (!dateStep.contains(date)) {
					// 如果在集合中则不处理
					HdStep step = new HdStep();
					step.setUploadTime(date);
					step.setStepCount(0);
					list.add(step);
				}
			}
			list=UUIDGenerator.compare(list);
			requestSucceed(pdataObj, list, "");
			putMaxMinAveValueToBizObj(pdataObj, valueMax, valueMin, valueAve);

		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getTodayStep(JSONObject pdataObj) {
		JSONObject bizObj = getBizObj(pdataObj);
		String memberId = bizObj.getString("member_id");
		Integer todaySteps = stepMapper.getTodayStep(memberId);
		String calories = new Double(todaySteps * Constants.CALORIE_COEFFICIENT).intValue() + "kcal";
		String distance = new Double(todaySteps * Constants.DISTANCE_COEFFICIENT).intValue() + "m";
		Integer targetStep = Constants.DEFAULT_TARGET_STEP;
		StepSetting stepSetting = stepSettingMapper.selectByMemberId(Integer.parseInt(memberId));
		if (stepSetting != null) {
			targetStep = stepSetting.getTargetStep();
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("todaySteps", todaySteps);
		jsonObj.put("calories", calories);
		jsonObj.put("distance", distance);
		jsonObj.put("targetStep", targetStep);
		pdataObj.put(Const.TAG_BIZ, jsonObj);
		pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		return toJsonStringWithOutNull(pdataObj);
	}

}