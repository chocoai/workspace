package com.yhcrt.iHealthCloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.entity.UserSuggestions;
import com.yhcrt.iHealthCloud.entity.UserSuggestionsExample;
import com.yhcrt.iHealthCloud.mapper.UserSuggestionsMapper;
import com.yhcrt.iHealthCloud.service.UserSuggestionService;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.DateUtil;

@Service
public class UserSuggestionServiceImpl extends BaseService implements UserSuggestionService {

	// debug mode
	protected boolean isDebug = true;

	@Autowired
	private UserSuggestionsMapper suggestionMapper;

	@Override
	public String getSuggestionListByUserId(JSONObject pdataObj) {

		// 获取参数memberId的值
		JSONObject bizObj = getBizObj(pdataObj);
		String userId = bizObj.getString(Const.TAG_USER_ID);

		// 对参数进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, userId)) {

			UserSuggestionsExample example = new UserSuggestionsExample();
			example.createCriteria().andUserIdEqualTo(userId);
			List<UserSuggestions> list = suggestionMapper.selectByExample(example);

			requestSucceed(pdataObj, list, "");

		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return pdataObj.toJSONString();
	}

	@Override
	public String getSuggestionByCid(JSONObject pdataObj) {

		// 获取参数cid的值
		JSONObject bizObj = getBizObj(pdataObj);
		String cid = bizObj.getString(Const.TAG_CID);

		// 对参数进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, cid)) {

			// 在member_device表中进行查询
			UserSuggestions suggestion = suggestionMapper.selectByPrimaryKey(cid);

			if (suggestion == null) {
				requestFailed(pdataObj, "no such record");
			} else {
				requestSucceed(pdataObj, suggestion, "");
			}

		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return pdataObj.toJSONString();
	}

	@Override
	public String commitSugesstion(JSONObject pdataObj) {

		// 获取biz json对象取出其中的值
		JSONObject bizObj = getBizObj(pdataObj);
		String userId = bizObj.getString(Const.TAG_USER_ID);
		String suggestionContent = bizObj.getString(Const.TAG_SUGGESTION_CONTENT);

		// 对取出的值进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, userId)) {

			// 向数据库中插入数据
			UserSuggestions suggestion = new UserSuggestions();
			suggestion.setUserId(userId);
			suggestion.setSuggestionContent(suggestionContent);
			suggestion.setSuggestionTime(DateUtil.getDateTime());
			int insert = suggestionMapper.insert(suggestion);
			if (insert > 0) {
				requestSucceed(pdataObj, suggestion, "");
			} else {
				requestFailed(pdataObj, "inser failed");
			}

		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return pdataObj.toJSONString();
	}

}
