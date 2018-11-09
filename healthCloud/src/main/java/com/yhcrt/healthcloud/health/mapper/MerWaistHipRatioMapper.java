package com.yhcrt.healthcloud.health.mapper;

import com.yhcrt.healthcloud.health.entity.MerWaistHipRatio;
import com.yhcrt.healthcloud.health.entity.MerWaistHipRatioExample;
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
    
    MerWaistHipRatio selectBymerId(Integer merId);
}