package com.yhcrt.iHealthCloud.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.iHealthCloud.entity.MessageCode;
import com.yhcrt.iHealthCloud.entity.MessageCodeExample;
import com.yhcrt.iHealthCloud.entity.MessageCodeExample.Criteria;
import com.yhcrt.iHealthCloud.mapper.MessageCodeMapper;
import com.yhcrt.iHealthCloud.service.MessageCodeService;
import com.yhcrt.iHealthCloud.util.DateUtils;
import com.yhcrt.iHealthCloud.util.UUIDGenerator;
@Service
public class MessageCodeServiceImpl implements MessageCodeService {

	@Autowired
	private MessageCodeMapper messageCodeMapper;
	@Override
	public int insert(String phoneNumber, String captcha, String businessType) {
		MessageCode msgCode = new MessageCode();
		msgCode.setMsgId(UUIDGenerator.getId());
		msgCode.setCaptcha(captcha);
		msgCode.setPhoneNum(phoneNumber);
		msgCode.setSendTime(DateUtils.getCurrentTime());
		msgCode.setBusinessType(businessType);
		return messageCodeMapper.insert(msgCode);
	}

	@Override
	public List<MessageCode> listByParams(HashMap<String, Object> params) {
		MessageCodeExample example = new MessageCodeExample();
		Criteria criteria = example.createCriteria();
		if (params.get("user_code") != null) {
			criteria.andPhoneNumEqualTo((String) params.get("user_code"));
		}
		if (params.get("business_type") != null) {
			criteria.andBusinessTypeEqualTo((String) params.get("business_type"));
		}
		if (params.get("captcha") != null) {
			criteria.andCaptchaEqualTo((String) params.get("captcha"));
		}
		return messageCodeMapper.selectByExample(example);
	}



}
