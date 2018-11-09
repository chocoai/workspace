package com.yhcrt.iHealthCloud.service;

import com.alibaba.fastjson.JSONObject;

public interface UserSuggestionService {

	/**
	 * 根据memberId获取用户建议列表
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getSuggestionListByUserId(JSONObject pdataObj);
	
	/**
	 * 根据cid获取用户建议
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getSuggestionByCid(JSONObject pdataObj);
	
	/**
	 * 提交用户建议
	 * @param pdataObj
	 * @return json字符串
	 */
	public String commitSugesstion(JSONObject pdataObj);
}
