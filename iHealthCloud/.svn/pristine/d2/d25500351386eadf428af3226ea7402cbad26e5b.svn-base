package com.yhcrt.iHealthCloud.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.entity.HdSleep;

/**
 * 深度睡眠数据服务接口
 * @author huzelin
 *
 */
public interface HealthDataSleepService {
	
	/**
	 * 根据时间段获取数据
	 * @param JSONObject
	 * @return json字符串
	 */
	public String getSleepByTime(JSONObject pdataObj);
	
	/**
	 * 根据memberId查询历史数据
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getSleepByMemberId(JSONObject pdataObj);
	
	/**
	 * 上传睡眠数据
	 * @param pdataObj
	 * @return json字符串数据
	 */
	public String insertSleep(JSONObject pdataObj);
	
	/**
	 * 获取近一周的睡眠数据
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getSleepForAWeekByMemberId(JSONObject pdataObj);
	
	public List<HdSleep> getSleepForAWeekByMemberId(String memberId);

}
