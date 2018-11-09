package com.yhcrt.iHealthCloud.service;

import java.util.HashMap;
import java.util.List;

import com.yhcrt.iHealthCloud.entity.MessageCode;

public interface MessageCodeService {
	
	int insert(String phoneNumber,String captcha,String businessType);
	
	List<MessageCode> listByParams(HashMap<String, Object> params);

}
