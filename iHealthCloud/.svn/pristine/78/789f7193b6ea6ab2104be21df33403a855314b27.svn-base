package com.yhcrt.iHealthCloud.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 设备服务接口
 * @author huzelin
 *
 */
public interface DeviceService {

	/**
	 * 根据memberId获取设备列表
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getDeviceListByMemberId(JSONObject pdataObj);
	
	/**
	 * 根据orgId获取设备列表
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getDeviceListByOrgId(JSONObject pdataObj);
	
	/**
	 * 根据cid查询设备详情
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getDeviceDetailByDeviceId(JSONObject pdataObj);
	
	/**
	 * 条件查询终端设备
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getDeviceByCondition(JSONObject pdataObj);
	
	/**
	 * 获取在线设备的定位信息
	 * @param pdataObj
	 * @return
	 */
	public String getOnlineDeviceLocation(JSONObject pdataObj);
	
	
	public String searchOnlineDeviceLocation(JSONObject pdataObj);
	
	
	
}
