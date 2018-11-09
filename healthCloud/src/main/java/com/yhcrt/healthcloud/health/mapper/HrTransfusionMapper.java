package com.yhcrt.healthcloud.health.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.yhcrt.healthcloud.health.entity.HrTransfusion;
import com.yhcrt.healthcloud.health.entity.HrTransfusionExample;

public interface HrTransfusionMapper {
	
    long countByExample(HrTransfusionExample example);

    int deleteByExample(HrTransfusionExample example);

    int deleteByPrimaryKey(Integer cId);

    int insert(HrTransfusion record);

    int insertSelective(HrTransfusion record);

    List<HrTransfusion> selectByExample(HrTransfusionExample example);
    
    List<HrTransfusion> selectByRecordId(Integer recordId);

    HrTransfusion selectByPrimaryKey(Integer cId);

    int updateByExampleSelective(@Param("record") HrTransfusion record, @Param("example") HrTransfusionExample example);

    int updateByExample(@Param("record") HrTransfusion record, @Param("example") HrTransfusionExample example);

    int updateByPrimaryKeySelective(HrTransfusion record);

    int updateByPrimaryKey(HrTransfusion record);
}