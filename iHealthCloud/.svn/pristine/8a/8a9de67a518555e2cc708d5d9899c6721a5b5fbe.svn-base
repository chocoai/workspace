package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.HrTrauma;
import com.yhcrt.iHealthCloud.entity.HrTraumaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HrTraumaMapper {
    long countByExample(HrTraumaExample example);

    int deleteByExample(HrTraumaExample example);

    int deleteByPrimaryKey(Integer cId);

    int insert(HrTrauma record);

    int insertSelective(HrTrauma record);

    List<HrTrauma> selectByExample(HrTraumaExample example);
    
    List<HrTrauma> listByRecordId(Integer recordId);

    HrTrauma selectByPrimaryKey(Integer cId);

    int updateByExampleSelective(@Param("record") HrTrauma record, @Param("example") HrTraumaExample example);

    int updateByExample(@Param("record") HrTrauma record, @Param("example") HrTraumaExample example);

    int updateByPrimaryKeySelective(HrTrauma record);

    int updateByPrimaryKey(HrTrauma record);
}