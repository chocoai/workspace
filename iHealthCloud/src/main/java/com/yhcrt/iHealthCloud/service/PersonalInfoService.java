package com.yhcrt.iHealthCloud.service;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.entity.HealthRecord;

/**
 * 个人信息服务接口
 * @author huzelin
 *
 */
public interface PersonalInfoService {

	/**
	 * 注册用户
	 * @param pdataObject
	 * @return	json
	 */
	public String register(JSONObject pdataObj);
	
	/**
	 * 用户登录
	 * @param pdataObject
	 * @return	json
	 */
	public String login(JSONObject pdataObj);
	
	/**
	 * 找回密码
	 * @param pdataObject
	 * @return	json
	 */
	public String retrievePassword(JSONObject pdataObj);
	
	/**
	 * 重置密码
	 * @param pdataObject
	 * @return	json
	 */
	public String resetPassword(JSONObject pdataObj);
	
	/**
	 * 获取健康档案
	 * @param pdataObj
	 * @return
	 */
	public String getHealthRecordByMemberId(JSONObject pdataObj);
	public HealthRecord getHealthRecordByMemberId(String memberId);
	
}
