package com.yhcrt.iHealthCloud.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.entity.HdBloodGlucose;

/**
 * 健康数据(血糖)服务接口
 * @author huzelin
 *
 */
@Service
public interface HealthDataBloodGlucoseService {

	/**
	 * 根据时间段获取血糖数据
	 * @param pdataObj
	 * @return 血糖数据列表（json字符串）
	 */
	public String getGlucoseByTime(JSONObject pdataObj);
	
	/**
	 * 根据memberId查询血糖历史数据
	 * @param pdataObj
	 * @return 血糖数据列表（json字符串）
	 */
	public String getGlucoseByMemberId(JSONObject pdataObj);
	
	/**
	 * 上传血糖数据
	 * @param pdataObj
	 * @return 上传结果（json字符串）
	 */
	public String insertGlucose(JSONObject pdataObj);
	
	/**
	 * 获取最近一周的血糖数据
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getGlucoseForAWeekByMemberId(JSONObject pdataObj);
	
	public List<HdBloodGlucose> getGlucoseForAWeekByMemberId(String memberId);
	
}
