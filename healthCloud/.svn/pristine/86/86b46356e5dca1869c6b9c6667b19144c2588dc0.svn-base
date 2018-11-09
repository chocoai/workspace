package com.yhcrt.healthcloud.device.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.yhcrt.healthcloud.common.Constants.CallType;
import com.yhcrt.healthcloud.device.entity.CallRecord;
import com.yhcrt.healthcloud.device.entity.CallRecordExample;
import com.yhcrt.healthcloud.device.mapper.CallRecordMapper;
import com.yhcrt.healthcloud.device.service.CallRecordService;
import com.yhcrt.healthcloud.memberBack.entity.MemberBack;
import com.yhcrt.healthcloud.memberBack.entity.MemberDeviceBack;
import com.yhcrt.healthcloud.memberBack.entity.MemberDeviceExampleBack;
import com.yhcrt.healthcloud.memberBack.entity.MemberExampleBack;
import com.yhcrt.healthcloud.memberBack.mapper.MemberBackMapper;
import com.yhcrt.healthcloud.memberBack.mapper.MemberDeviceBackMapper;
import com.yhcrt.healthcloud.organization.entity.Doctor;
import com.yhcrt.healthcloud.organization.entity.Employee;
import com.yhcrt.healthcloud.organization.mapper.DoctorMapper;
import com.yhcrt.healthcloud.organization.mapper.EmployeeMapper;
import com.yhcrt.healthcloud.util.DateUtil;

/**
 * service实现层
 * @author PC
 *
 */
@Service
public class CallRecordServiceImpl implements CallRecordService {

	@Autowired
	private CallRecordMapper callRecordMapper;
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Autowired
	private DoctorMapper doctorMapper;
	
	@Autowired
	private MemberBackMapper memberMapper;
	
	@Autowired
	private MemberDeviceBackMapper memberDeviceMapper;
	
	// 新增操作
	@Override
	public int insert(String phoneNo, String callType) {
		CallRecord record = new CallRecord();
		record.setPhoneNo(phoneNo);
		record.setCallInOut(Integer.valueOf(callType));
		record.setCreateTime(DateUtil.getDateTime());
		record.setIsAnswer(0);
		// 根据电话号码查找呼叫人
		MemberExampleBack example = new MemberExampleBack();
		example.createCriteria().andPhoneNoEqualTo(phoneNo);
		List<MemberBack> members = memberMapper.selectByExample(example);
		if (members.size() > 1) {
			record.setType(CallType.CALL_MEMBER);
			record.setCallName(members.get(0).getRealName());
			record.setRefId(members.get(0).getMemberId());
			return callRecordMapper.insert(record);
		}
		MemberDeviceExampleBack memberDeviceExample = new MemberDeviceExampleBack();
		memberDeviceExample.createCriteria().andSimEqualTo(phoneNo);
		List<MemberDeviceBack> memberDevices = memberDeviceMapper.selectByExample(memberDeviceExample);
		if (memberDevices.size() > 1) {
			record.setType(CallType.CALL_MEMBER);
			record.setCallName(memberDevices.get(0).getMemberName());
			record.setRefId(memberDevices.get(0).getMemberId());
			return callRecordMapper.insert(record);
		}
		List<Employee> emps = employeeMapper.selectByPhoneNo(phoneNo);
		if (emps.size() > 1) {
			record.setType(CallType.CALL_EMP);
			record.setCallName(emps.get(0).getRealName());
			record.setRefId(emps.get(0).getEmpId());
			return callRecordMapper.insert(record);
		}
		List<Doctor> docs = doctorMapper.selectByPhoneNo(phoneNo);
		if (docs.size() > 1) {
			record.setType(CallType.CALL_DOC);
			record.setCallName(docs.get(0).getRealName());
			record.setRefId(docs.get(0).getDocId());
			return callRecordMapper.insert(record);
		}
		record.setCallName("未知");
		return callRecordMapper.insert(record);

	}
	
	// 接听操作
	@Override
	public int connectCall(String phoneNo) {
		CallRecordExample example = new CallRecordExample();
		example.createCriteria().andPhoneNoEqualTo(phoneNo);
		example.setOrderByClause("call_id desc");
		List<CallRecord> recordList = callRecordMapper.selectByExample(example);
		if (recordList.size() > 0) {
			CallRecord record = recordList.get(0);
			if (record.getCallTime() == null) {
				record.setCallTime(DateUtil.getDateTime());
				record.setIsAnswer(1);
				return callRecordMapper.updateByPrimaryKeySelective(record);
			}
		}
		return 0;
	}
	
	//挂断操作
	@Override
	public int disConnectCall(String phoneNo) {
		CallRecordExample example = new CallRecordExample();
		example.createCriteria().andPhoneNoEqualTo(phoneNo);
		example.setOrderByClause("call_id desc");
		List<CallRecord> recordList = callRecordMapper.selectByExample(example);
        if(recordList.size()>0){
        	CallRecord record = recordList.get(0);
        	String current = DateUtil.getDateTime();
        	String  call   = record.getCallTime();
        	if(!StringUtils.isEmpty(call)){
	        	Long times = DateUtil.stringToDate(current).getTime()-DateUtil.stringToDate(call).getTime();
	        	record.setCallDuration(times/1000+"");
	        	return callRecordMapper.updateByPrimaryKeySelective(record);
        	}
        }
		return 0;
	}
	
	

	//呼叫列表
	@Override
	public List<CallRecord> queryList(Map<String, Object> map) {
		return callRecordMapper.queryList(map);
	}

}