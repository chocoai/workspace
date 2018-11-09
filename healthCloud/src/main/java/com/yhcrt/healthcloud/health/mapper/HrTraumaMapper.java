package com.yhcrt.healthcloud.health.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.yhcrt.healthcloud.health.entity.HrTrauma;
import com.yhcrt.healthcloud.health.entity.HrTraumaExample;

public interface HrTraumaMapper {
	
    long countByExample(HrTraumaExample example);

    int deleteByExample(HrTraumaExample example);

    int deleteByPrimaryKey(Integer cId);

    int insert(HrTrauma record);

    int insertSelective(HrTrauma record);

    List<HrTrauma> selectByExample(HrTraumaExample example);
    
    List<HrTrauma> selectByRecordId(Integer recordId);

    HrTrauma selectByPrimaryKey(Integer cId);

    int updateByExampleSelective(@Param("record") HrTrauma record, @Param("example") HrTraumaExample example);

    int updateByExample(@Param("record") HrTrauma record, @Param("example") HrTraumaExample example);

    int updateByPrimaryKeySelective(HrTrauma record);

    int updateByPrimaryKey(HrTrauma record);
}