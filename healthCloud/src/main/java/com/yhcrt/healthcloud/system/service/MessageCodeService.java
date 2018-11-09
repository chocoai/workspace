package com.yhcrt.healthcloud.system.service;

import com.yhcrt.healthcloud.system.entity.MessageCode;

public interface MessageCodeService {
	//添加验证码
	int insert(MessageCode record);
	
	//按手机号码查询验证码最新的一条
	MessageCode selectByKey(String phoneNum);
}
