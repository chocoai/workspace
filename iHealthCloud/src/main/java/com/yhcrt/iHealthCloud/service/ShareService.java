package com.yhcrt.iHealthCloud.service;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

public interface ShareService {

	/**
	 * 获取分享链接
	 * @param pdataObj
	 * @return json String
	 */
	public String getShareUrl(JSONObject pdataObj, HttpServletRequest request);
	
}
