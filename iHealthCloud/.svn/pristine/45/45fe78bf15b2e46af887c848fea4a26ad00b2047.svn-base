package com.yhcrt.iHealthCloud.service;


import com.alibaba.fastjson.JSONObject;

/**
 * 活动量(计步)服务接口
 * @author huzelin
 *
 */
public interface HealthDataStepService {
	
	/**
	 * 获取今日步数
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getTodayStep(JSONObject pdataObj);
	
	/**
	 * 根据时间段获取数据
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getStepByTime(JSONObject pdataObj);
	
	/**
	 * 根据memberId查询历史数据
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getStepByMemberId(JSONObject pdataObj);
	
	/**
	 * 上传运动量
	 * @param pdataObj
	 * @return json字符串
	 */
	public String insertStep(JSONObject pdataObj);
	
	/**
	 * 上传跌倒报警
	 * @param pdataObj
	 * @return json字符串
	 */
	public String insertFallDow(JSONObject pdataObj);
	
	/**
	 * 获取近一周的运动量数据
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getStepForAWeekByMemberId(JSONObject pdataObj);
}
