package com.yhcrt.iHealthCloud.mapper;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.HealthRecord;
import com.yhcrt.iHealthCloud.entity.HealthRecordExample;

public interface HealthRecordMapper {
    long countByExample(HealthRecordExample example);

    int deleteByExample(HealthRecordExample example);

    int deleteByPrimaryKey(Integer recordId);

    int insert(HealthRecord record);

    int insertSelective(HealthRecord record);

    HealthRecord selectByExample(HealthRecordExample example);

    HealthRecord selectByPrimaryKey(Integer recordId);

    int updateByExampleSelective(@Param("record") HealthRecord record, @Param("example") HealthRecordExample example);

    int updateByExample(@Param("record") HealthRecord record, @Param("example") HealthRecordExample example);

    int updateByPrimaryKeySelective(HealthRecord record);

    int updateByPrimaryKey(HealthRecord record);
}