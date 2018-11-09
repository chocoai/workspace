package com.yhcrt.healthcloud.device.mapper;

import com.yhcrt.healthcloud.device.entity.CallRecord;
import com.yhcrt.healthcloud.device.entity.CallRecordExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CallRecordMapper {
    long countByExample(CallRecordExample example);

    int deleteByExample(CallRecordExample example);

    int deleteByPrimaryKey(Integer callId);

    int insert(CallRecord record);

    int insertSelective(CallRecord record);

    List<CallRecord> selectByExample(CallRecordExample example);

    CallRecord selectByPrimaryKey(Integer callId);

    int updateByExampleSelective(@Param("record") CallRecord record, @Param("example") CallRecordExample example);

    int updateByExample(@Param("record") CallRecord record, @Param("example") CallRecordExample example);

    int updateByPrimaryKeySelective(CallRecord record);

    int updateByPrimaryKey(CallRecord record);

    //呼叫列表
	List<CallRecord> queryList(Map<String, Object> map);
}