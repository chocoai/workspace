package com.yhcrt.iHealthCloud.service;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

/**
 * 用户使用指南服务接口
 * @author huzelin
 *
 */
public interface UserDirectionService {
	
	/**
	 * 获取app使用说明链接
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getDirectionUrl4App(JSONObject pdataObj, HttpServletRequest request);
	
	/**
	 * 获取手表使用说明链接
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getDirectionUrl4Watch(JSONObject pdataObj);

}
