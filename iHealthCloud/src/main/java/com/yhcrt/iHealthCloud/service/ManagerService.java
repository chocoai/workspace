package com.yhcrt.iHealthCloud.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 机构管理员服务接口
 * @author huzelin
 *
 */
public interface ManagerService {
	
	/**
	 * 根据org_id获取会员列表
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getMemberListByOrgId(JSONObject pdataObj);
	
	/**
	 * 根据条件查询会员列表
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getMemberByCondition(JSONObject pdataObj);
	
	/**
	 * 根据member_id查询会员详情
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getMemberByMemberId(JSONObject pdataObj);
	
	/**
	 * 根据orgId获取员工列表
	 * @param pdataObject
	 * @return
	 */
	public String getEmployeeListByOrgId(JSONObject pdataObj);
	
	/**
	 * 根据orgId获取医师列表
	 * @param pdataObj
	 * @return
	 */
	public String getDoctorListByOrgId(JSONObject pdataObj);

	/**
	 * 根据机构Id获取联系人
	 * @param pdataObj
	 * @return
	 */
	public String getLinkmansByOrgId(JSONObject pdataObj);
	
}
