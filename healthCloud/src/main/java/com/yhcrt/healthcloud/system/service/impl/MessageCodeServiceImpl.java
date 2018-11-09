package com.yhcrt.healthcloud.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.system.entity.MessageCode;
import com.yhcrt.healthcloud.system.mapper.MessageCodeMapper;
import com.yhcrt.healthcloud.system.service.MessageCodeService;
@Service
public class MessageCodeServiceImpl implements MessageCodeService{

	@Autowired
	private MessageCodeMapper messageCodeMapper ;
	@Override
	public int insert(MessageCode record) {
		return messageCodeMapper.insertSelective(record);
	}

	@Override
	public MessageCode selectByKey(String phoneNum) {
		return messageCodeMapper.selectByKey(phoneNum);
	}

}
