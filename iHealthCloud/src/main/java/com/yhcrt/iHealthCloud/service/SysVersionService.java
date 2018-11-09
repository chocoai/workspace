package com.yhcrt.iHealthCloud.service;


import com.alibaba.fastjson.JSONObject;

/**
 * 版本service
 * @author hull
 *
 */
public interface SysVersionService {
	
	//查询最新版本
	String queryNewVersion(JSONObject pdataObj);
	
	//查询版本list
	String getVersionList(JSONObject pdataObj);
	
	//查询版本说明
	String getVersionDesc(JSONObject pdataObj);

	//统计下载版本次数
	String totalVersionNum(JSONObject pdataObj);

}
