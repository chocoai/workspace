package com.yhcrt.healthcloud.health.mapper;

import com.yhcrt.healthcloud.health.entity.HealthRecord;
import com.yhcrt.healthcloud.health.entity.HealthRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HealthRecordMapper {
    long countByExample(HealthRecordExample example);

    int deleteByExample(HealthRecordExample example);

    int deleteByPrimaryKey(Integer recordId);

    int insert(HealthRecord record);

    int insertSelective(HealthRecord record);

    List<HealthRecord> selectByExample(HealthRecordExample example);

    HealthRecord selectByPrimaryKey(Integer recordId);

    int updateByExampleSelective(@Param("record") HealthRecord record, @Param("example") HealthRecordExample example);

    int updateByExample(@Param("record") HealthRecord record, @Param("example") HealthRecordExample example);

    int updateByPrimaryKeySelective(HealthRecord record);

    int updateByPrimaryKey(HealthRecord record);
    
    HealthRecord selectByMemberId(Integer memberId);
}