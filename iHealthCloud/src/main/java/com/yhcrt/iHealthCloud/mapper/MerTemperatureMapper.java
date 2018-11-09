package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.MerTemperature;
import com.yhcrt.iHealthCloud.entity.MerTemperatureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerTemperatureMapper {
    long countByExample(MerTemperatureExample example);

    int deleteByExample(MerTemperatureExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(MerTemperature record);

    int insertSelective(MerTemperature record);

    List<MerTemperature> selectByExample(MerTemperatureExample example);

    MerTemperature selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") MerTemperature record, @Param("example") MerTemperatureExample example);

    int updateByExample(@Param("record") MerTemperature record, @Param("example") MerTemperatureExample example);

    int updateByPrimaryKeySelective(MerTemperature record);

    int updateByPrimaryKey(MerTemperature record);
    
    MerTemperature selectByMerId(Integer merId);
}