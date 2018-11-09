package com.yhcrt.healthcloud.health.mapper;

import com.yhcrt.healthcloud.health.entity.MerElectrocardiogram;
import com.yhcrt.healthcloud.health.entity.MerElectrocardiogramExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerElectrocardiogramMapper {
    long countByExample(MerElectrocardiogramExample example);

    int deleteByExample(MerElectrocardiogramExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(MerElectrocardiogram record);

    int insertSelective(MerElectrocardiogram record);

    List<MerElectrocardiogram> selectByExample(MerElectrocardiogramExample example);

    MerElectrocardiogram selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") MerElectrocardiogram record, @Param("example") MerElectrocardiogramExample example);

    int updateByExample(@Param("record") MerElectrocardiogram record, @Param("example") MerElectrocardiogramExample example);

    int updateByPrimaryKeySelective(MerElectrocardiogram record);

    int updateByPrimaryKey(MerElectrocardiogram record);
    
    MerElectrocardiogram selectBymerId(Integer merId);
}