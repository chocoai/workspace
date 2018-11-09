package com.yhcrt.iHealthCloud.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.entity.HdBloodPressure;

/**
 * 健康数据-血压 服务接口
 * @author huzelin
 *
 */
public interface HealthDataBloodPressureService {

	/**
	 * 根据时间段获取数据
	 * @param pdataObj 
	 * @return json字符串数据
	 */
	public String getPressureByTime(JSONObject pdataObj);
	
	/**
	 * 根据memberId查询历史数据
	 * @param pdataObj
	 * @return json字符串数据
	 */
	public String getPressureByMemberId(JSONObject pdataObj);
	
	/**
	 * 上传血压数据
	 * @param pdataObj
	 * @return json字符串数据
	 */
	public String insertPressure(JSONObject pdataObj);
	
	/**
	 * 获取最近一周的血压数据
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getPressureForAWeekByMemberId(JSONObject pdataObj);
	
	public List<HdBloodPressure> getPressureForAWeekByMemberId(String memberId);
}
