/**
 * @Title:   HealthDataServiceImpl.java 
 * @Package: com.yhcrt.iHealthCloud.service.impl  
 * @Description: 
 * @author: rpf
 * @date: 2017年12月14日 
 * @version V1.0 
 * Copyright © 2017 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.iHealthCloud.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.BloodPressureSetting;
import com.yhcrt.iHealthCloud.entity.HdBloodOxygen;
import com.yhcrt.iHealthCloud.entity.HdBloodPressure;
import com.yhcrt.iHealthCloud.entity.HdBloodPressureExample;
import com.yhcrt.iHealthCloud.entity.HdPulse;
import com.yhcrt.iHealthCloud.entity.HdPulseExample;
import com.yhcrt.iHealthCloud.entity.HdTemperature;
import com.yhcrt.iHealthCloud.entity.HeartRateSetting;
import com.yhcrt.iHealthCloud.entity.Member;
import com.yhcrt.iHealthCloud.entity.MemberDevice;
import com.yhcrt.iHealthCloud.entity.StepSetting;
import com.yhcrt.iHealthCloud.mapper.BloodPressureSettingMapper;
import com.yhcrt.iHealthCloud.mapper.HdBloodGlucoseMapper;
import com.yhcrt.iHealthCloud.mapper.HdBloodOxygenMapper;
import com.yhcrt.iHealthCloud.mapper.HdBloodPressureMapper;
import com.yhcrt.iHealthCloud.mapper.HdPulseMapper;
import com.yhcrt.iHealthCloud.mapper.HdSleepMapper;
import com.yhcrt.iHealthCloud.mapper.HdStepMapper;
import com.yhcrt.iHealthCloud.mapper.HdTemperatureMapper;
import com.yhcrt.iHealthCloud.mapper.HeartRateSettingMapper;
import com.yhcrt.iHealthCloud.mapper.MemberDeviceMapper;
import com.yhcrt.iHealthCloud.mapper.MemberMapper;
import com.yhcrt.iHealthCloud.mapper.StepSettingMapper;
import com.yhcrt.iHealthCloud.pojo.BloodGlucoseBO;
import com.yhcrt.iHealthCloud.pojo.BloodGlucoseDTO;
import com.yhcrt.iHealthCloud.pojo.BloodPressureDTO;
import com.yhcrt.iHealthCloud.pojo.DataItem;
import com.yhcrt.iHealthCloud.pojo.HdPulseDTO;
import com.yhcrt.iHealthCloud.pojo.HdStepDTO;
import com.yhcrt.iHealthCloud.pojo.SleepDTO;
import com.yhcrt.iHealthCloud.service.HealthDataService;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.DateUtil;
import com.yhcrt.iHealthCloud.util.DateUtils;

/**
 * @ClassName: HealthDataServiceImpl
 * @Description:
 * @version V1.0 
 * @author rpf
 * @date: 2017年12月14日 
 */
@Service
public class HealthDataServiceImpl extends BaseService implements HealthDataService {
	
	@Autowired
	private HdStepMapper stepMapper;
	@Autowired
	private HdSleepMapper sleepMapper;
	@Autowired
	private HdPulseMapper pulseMapper;
	@Autowired
	private MemberDeviceMapper memberDeviceMapper;
	@Autowired
	private StepSettingMapper stepSettingMapper;
	@Autowired
	private HeartRateSettingMapper heartRateSettingMapper;
	@Autowired
	private HdBloodPressureMapper bloodPressureMapper;
	@Autowired
	private BloodPressureSettingMapper bloodPressureSettingMapper;
	@Autowired
	private HdBloodGlucoseMapper bloodGlucoseMapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private HdBloodOxygenMapper bloodOxygenMapper;
	@Autowired
	private HdTemperatureMapper temperatureMapper;
 
	/**
	 * 
	 */
	public HealthDataServiceImpl() {
		
	}

	@Override
	public String getHealthDataForWeek(JSONObject pdataObj) {
		String memberId = getMemberId(pdataObj);
		JSONObject stepDataOfLatestWeek = getStepDataOfLatestWeek(memberId);
		JSONObject sleepDataOfLatestWeek = getSleepDataOfLatestWeek(memberId);
		JSONObject pulseDataOfLatestWeek = getPulseDataOfLatestWeek(memberId);
		JSONObject bloodPressureDataOfLatestWeek = getBloodPressureDataOfLatestWeek(memberId);
		JSONObject BloodGlucoseDataOfLatestWeek = getBloodGlucoseDataOfLatestWeek(memberId);
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("stepDataOfLatestWeek", stepDataOfLatestWeek);
		jsonObj.put("sleepDataOfLatestWeek", sleepDataOfLatestWeek);
		jsonObj.put("pulseDataOfLatestWeek", pulseDataOfLatestWeek);
		jsonObj.put("bloodPressureDataOfLatestWeek", bloodPressureDataOfLatestWeek);
		jsonObj.put("bloodGlucoseDataOfLatestWeek", BloodGlucoseDataOfLatestWeek);
		
		pdataObj.put(Const.TAG_BIZ, jsonObj);
		pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		return toJsonStringWithOutNull(pdataObj);
	}
	
	public JSONObject getBloodGlucoseDataOfLatestWeek(String memberId) {
		// 近一周的血糖数据
		List<BloodGlucoseDTO> bgs = bloodGlucoseMapper.getBloodGlucoseDataForWeek(memberId);
		JSONArray bloodGlucoses = filterSameTypeDataAtOneDay(bgs);
		// 近一周的血糖数据的最大值、最小值、平均值
		DataItem dataItem = bloodGlucoseMapper.getMaxMinAvgBloodGlucoseDataForWeek(memberId);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("bloodGlucoses", bloodGlucoses);
		jsonObj.put("maxValue", dataItem != null ? dataItem.getMax() : "");
		jsonObj.put("minValue", dataItem != null ? dataItem.getMin() : "");
		jsonObj.put("avgValue", dataItem != null ? dataItem.getAvg() : "");
		return jsonObj;
	}
	
	public JSONObject getBloodPressureDataOfLatestWeek(String memberId) {
		// 近一周的血压数据
		List<BloodPressureDTO> bloodPressures = bloodPressureMapper.getBloodPressureDataForWeek(memberId);
		// 近一周的血压数据的最大值、最小值、平均值
		DataItem dataItem = bloodPressureMapper.getMaxMinAvgBloodPressureDataForWeek(memberId);
		// 查询血压的安全范围
		BloodPressureSetting bloodPressureSetting = bloodPressureSettingMapper.selectByMemberId(Integer.parseInt(memberId));
		Integer dbpMax = Constants.BloodPressure.DEFAULT_DBP_MAX_VALUE;
		Integer dbpMin = Constants.BloodPressure.DEFAULT_DBP_MIN_VALUE;
		Integer sbpMax = Constants.BloodPressure.DEFAULT_SBP_MAX_VALUE;
		Integer sbpMin = Constants.BloodPressure.DEFAULT_SBP_MIN_VALUE;
		if (bloodPressureSetting != null) {
			dbpMax = bloodPressureSetting.getDbpMax();
			dbpMin = bloodPressureSetting.getDbpMin();
			sbpMax = bloodPressureSetting.getSbpMax();
			sbpMin = bloodPressureSetting.getSbpMin();
		}
		String dbpSafeRange = dbpMin + "~" + dbpMax;
		String sbpSafeRange = sbpMin + "~" + sbpMax;
		// 计算血压的异常次数
		Integer abnormal = 0;
		for (BloodPressureDTO bp : bloodPressures) {
			if (bp.getDbp() > dbpMax || bp.getDbp() < dbpMin || bp.getSbp() > sbpMax || bp.getSbp() < sbpMin) {
				abnormal++;
			}
		}

		JSONObject jsonObj = new JSONObject();
		jsonObj.put("bloodPressures", bloodPressures);
		jsonObj.put("maxValue", dataItem != null ? dataItem.getMax() : "");
		jsonObj.put("minValue", dataItem != null ? dataItem.getMin() : "");
		jsonObj.put("avgValue", dataItem != null ? dataItem.getAvg() : "");
		jsonObj.put("dbpSafeRange", dbpSafeRange);
		jsonObj.put("sbpSafeRange", sbpSafeRange);
		jsonObj.put("abnormal", abnormal);
		jsonObj.put("trend", "");

		return jsonObj;
	}
	
	public JSONObject getPulseDataOfLatestWeek(String memberId) {
		// 近一周的心率数据
		List<HdPulseDTO> pulses = pulseMapper.getPulseDataForWeek(memberId);
		// 近一周的心率数据最大值、最小值、平均值
		DataItem dataItem = pulseMapper.getMaxMinAvgPulseDataForWeek(memberId);
		// 查询心率的安全范围
		MemberDevice defaultDevice = memberDeviceMapper.getDefaultDevice(memberId);
		Integer highHeartRate = Constants.HeartRate.DEFAULT_HIGH_VALUE;
		Integer lowHeartRate = Constants.HeartRate.DEFAULT_LOW_VALUE;
		if (defaultDevice!= null) {
			HeartRateSetting heartRateSetting = heartRateSettingMapper.selectByImei(defaultDevice.getImei());
			highHeartRate = heartRateSetting != null ? heartRateSetting.getHighHeartRate() : highHeartRate;
			lowHeartRate = heartRateSetting != null ? heartRateSetting.getLowHeartRate() : lowHeartRate;
		}
		String safeRange = lowHeartRate + "~"+highHeartRate;
		// 计算心率异常次数
		Integer abnormal = 0;
		for (HdPulseDTO pulse : pulses) {
			if (pulse.getPulse()>highHeartRate || highHeartRate < lowHeartRate) {
				abnormal++;
			}
		}
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("pulses", pulses);
		jsonObj.put("maxValue", dataItem != null ? dataItem.getMax() : "");
		jsonObj.put("minValue", dataItem != null ? dataItem.getMin() : "");
		jsonObj.put("avgValue", dataItem != null ? dataItem.getAvg() : "");
		jsonObj.put("safeRange", safeRange);
		jsonObj.put("abnormal", abnormal);
		jsonObj.put("trend", "");
		
		return jsonObj;
	}
	
	public JSONObject getSleepDataOfLatestWeek(String memberId) {
		// 近一周的睡眠数据
		List<SleepDTO> sleeps = sleepMapper.getSleepDataForWeek(memberId);
		// 近一周的深度睡眠数据最大值、最小值、平均值
		DataItem dataItem = sleepMapper.getMaxMinAvgSleepDataForWeek(memberId);
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("sleeps", sleeps);
		jsonObj.put("maxValue", dataItem != null ? dataItem.getMax() : "");
		jsonObj.put("minValue", dataItem != null ? dataItem.getMin() : "");
		jsonObj.put("avgValue", dataItem != null ? dataItem.getAvg() : "");
		return jsonObj;
	}
	
	public JSONObject getStepDataOfLatestWeek(String memberId) {
		// 近一周的计步数据
		List<HdStepDTO> steps = stepMapper.getStepDataForWeek(memberId);
		// 近一周的计步数据按天统计后最大值、最小值、平均值
		DataItem dataItem = stepMapper.getMaxMinAvgStepDataForWeek(memberId);
		// 查询会员的运动目标
		Integer targetStep = Constants.DEFAULT_TARGET_STEP;
		StepSetting stepSetting = stepSettingMapper.selectByMemberId(Integer.parseInt(memberId));
		if (stepSetting != null) {
			targetStep = stepSetting.getTargetStep();
		}
		Integer complete = 0;
		for (HdStepDTO step : steps) {
			Integer stepCount = Integer.parseInt(step.getStepCount());
			if (targetStep < stepCount) {
				complete++;
			}
		}
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("steps", steps);
		jsonObj.put("maxValue", dataItem != null ? dataItem.getMax() : "");
		jsonObj.put("minValue", dataItem != null ? dataItem.getMin() : "");
		jsonObj.put("avgValue", dataItem != null ? dataItem.getAvg() : "");
		jsonObj.put("targetStep", targetStep);
		jsonObj.put("complete", complete);
		jsonObj.put("trend", "");
		return jsonObj;
	}
	
	/**
	 * 将集合中同一天内相同血糖类型的数据过滤，只保留最近的一条
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

	@Override
	public String getHealthIndexData(JSONObject jsonObj) {
		String memberId = getMemberId(jsonObj);
		if (StringUtils.isBlank(memberId)) {
			requestFailed(jsonObj, Const.RMK_BIZ_PARAM_NULL);
			return jsonObj.toJSONString();
		}
		Member member = memberMapper.selectByPrimaryKey(Integer.parseInt(memberId));
		if (member == null) {
			requestFailed(jsonObj, Constants.ExceptionMsg.MEMBER_NOT_FOUND);
			return jsonObj.toJSONString();
		}
		// 今日步数
		Integer todaySteps = stepMapper.getTodayStep(memberId);
		// 心率数据
		HdPulseExample example = new HdPulseExample();
		example.createCriteria().andMemberIdEqualTo(member.getMemberId());
		example.setOrderByClause(" upload_time desc");
		List<HdPulse> pulses = pulseMapper.selectByExample(example);
		String pulse = pulses.size() > 0 ? pulses.get(0).getPulse().toString() : "";
		// 血压数据
		HdBloodPressureExample bloodPressureExample = new HdBloodPressureExample();
		bloodPressureExample.createCriteria().andMemberIdEqualTo(member.getMemberId());
		bloodPressureExample.setOrderByClause(" upload_time desc");
		List<HdBloodPressure> bloodPressures = bloodPressureMapper.selectByExample(bloodPressureExample);
		String bpStr = "-/-";
		if (bloodPressures.size() > 0) {
			bpStr = bloodPressures.get(0).getDbp() + "/" + bloodPressures.get(0).getSbp();
		}
		JSONObject obj = new JSONObject();
		obj.put("realName", member.getRealName());
		obj.put("headPic", member.getHeadPic());
		obj.put("todaySteps", todaySteps);
		obj.put("pulse", pulse);
		obj.put("bloodPressure", bpStr);
		requestSucceed(jsonObj, obj, "");
		return toJsonStringWithOutNull(jsonObj);
	}

	@Override
	public String uploadBloodOxygen(JSONObject jsonObject) {
		JSONObject bizObj = getBizObj(jsonObject);
		String memberId = getMemberId(jsonObject);
		String imei = bizObj.getString("imei");
		String boValue = bizObj.getString("bo_value");
		String dataSource = getBizObj(jsonObject).getString("data_source");
		
		HdBloodOxygen bloodOxygen = new HdBloodOxygen();
		bloodOxygen.setImei(imei);
		bloodOxygen.setMemberId(Integer.valueOf(memberId));
		bloodOxygen.setDataDate(DateUtil.getDate());
		bloodOxygen.setUploadTime(DateUtils.getCurrentTime());
		bloodOxygen.setBoValue(Double.valueOf(boValue));
		bloodOxygen.setDataSource(dataSource);
		bloodOxygenMapper.insert(bloodOxygen);
		jsonObject.put(Const.TAG_BIZ, bloodOxygen);
		jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		return toJsonStringWithOutNull(jsonObject);
	}

	@Override
	public String uploadTemperature(JSONObject jsonObject) {
		JSONObject bizObj = getBizObj(jsonObject);
		String memberId = getMemberId(jsonObject);
		String imei = bizObj.getString("imei");
		String tmepValue = bizObj.getString("temperature");
		
		HdTemperature temperature = new HdTemperature();
		temperature.setImei(imei);
		if (StringUtils.isNotBlank(memberId)) {
			temperature.setMemberId(Integer.valueOf(memberId));
		}
		temperature.setDataDate(DateUtil.getDate());
		temperature.setUploadTime(DateUtils.getCurrentTime());
		temperature.setTemperature(Double.valueOf(tmepValue));
		temperatureMapper.insert(temperature);
		jsonObject.put(Const.TAG_BIZ, temperature);
		jsonObject.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		return toJsonStringWithOutNull(jsonObject);
	}


}
