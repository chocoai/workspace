package com.yhcrt.healthcloud.health.mapper;

import com.yhcrt.healthcloud.health.entity.MerBloodPressure;
import com.yhcrt.healthcloud.health.entity.MerBloodPressureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerBloodPressureMapper {
    long countByExample(MerBloodPressureExample example);

    int deleteByExample(MerBloodPressureExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(MerBloodPressure record);

    int insertSelective(MerBloodPressure record);

    List<MerBloodPressure> selectByExample(MerBloodPressureExample example);

    MerBloodPressure selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") MerBloodPressure record, @Param("example") MerBloodPressureExample example);

    int updateByExample(@Param("record") MerBloodPressure record, @Param("example") MerBloodPressureExample example);

    int updateByPrimaryKeySelective(MerBloodPressure record);

    int updateByPrimaryKey(MerBloodPressure record);
    
    MerBloodPressure selectBymerId(Integer merId);
}