package com.yhcrt.healthcloud.device.service;

import java.util.List;
import java.util.Map;

import com.yhcrt.healthcloud.device.entity.CallRecord;

/**
 * 呼叫service
 * @author PC
 *
 */
public interface CallRecordService {

	//新增操作
	int insert(String phoneNo,String callType);
	
	//接听操作
	int connectCall(String phoneNo);
	
	//挂断操作
	int disConnectCall(String phoneNo);

	//呼叫列表
	List<CallRecord> queryList(Map<String, Object> map);

}
