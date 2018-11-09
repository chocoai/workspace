package com.yhcrt.iHealthCloud.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.entity.HdPulse;

/**
 * 健康数据-脉搏 服务接口
 * 
 * @author huzelin
 *
 */
public interface HealthDataPulseService {

	/**
	 * 根据时间段获取数据
	 * 
	 * @param JSONObject
	 * @return json字符串
	 */
	public String getPulseByTime(JSONObject pdataObj);

	/**
	 * 根据最新心率数据
	 * 
	 * @param JSONObject
	 * @return json字符串
	 */
	public String getLatestPulse(JSONObject pdataObj);

	/**
	 * 根据memberId查询历史数据
	 * 
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getPulseByMemberId(JSONObject pdataObj);

	/**
	 * 上传心率
	 * 
	 * @param pdataObj
	 * @return json字符串
	 */
	public String insertHeartRate(JSONObject pdataObj);

	/**
	 * 查询最近一周的数据
	 * 
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getPulseForAWeekByMemberId(JSONObject pdataObj);

	public List<HdPulse> getPulseForAWeekByMemberId(String memberId);
}
