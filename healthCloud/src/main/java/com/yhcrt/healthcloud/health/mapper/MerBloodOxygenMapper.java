package com.yhcrt.healthcloud.health.mapper;

import com.yhcrt.healthcloud.health.entity.MerBloodOxygen;
import com.yhcrt.healthcloud.health.entity.MerBloodOxygenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerBloodOxygenMapper {
    long countByExample(MerBloodOxygenExample example);

    int deleteByExample(MerBloodOxygenExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(MerBloodOxygen record);

    int insertSelective(MerBloodOxygen record);

    List<MerBloodOxygen> selectByExample(MerBloodOxygenExample example);

    MerBloodOxygen selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") MerBloodOxygen record, @Param("example") MerBloodOxygenExample example);

    int updateByExample(@Param("record") MerBloodOxygen record, @Param("example") MerBloodOxygenExample example);

    int updateByPrimaryKeySelective(MerBloodOxygen record);

    int updateByPrimaryKey(MerBloodOxygen record);
    
    MerBloodOxygen selectBymerId(Integer merId);
}