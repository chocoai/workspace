package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.MerWaistHipRatio;
import com.yhcrt.iHealthCloud.entity.MerWaistHipRatioExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerWaistHipRatioMapper {
    long countByExample(MerWaistHipRatioExample example);

    int deleteByExample(MerWaistHipRatioExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(MerWaistHipRatio record);

    int insertSelective(MerWaistHipRatio record);

    List<MerWaistHipRatio> selectByExample(MerWaistHipRatioExample example);

    MerWaistHipRatio selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") MerWaistHipRatio record, @Param("example") MerWaistHipRatioExample example);

    int updateByExample(@Param("record") MerWaistHipRatio record, @Param("example") MerWaistHipRatioExample example);

    int updateByPrimaryKeySelective(MerWaistHipRatio record);

    int updateByPrimaryKey(MerWaistHipRatio record);
    
    MerWaistHipRatio selectByMerId(Integer merId);
}