package com.yhcrt.healthcloud.health.mapper;

import com.yhcrt.healthcloud.health.entity.MerBmi;
import com.yhcrt.healthcloud.health.entity.MerBmiExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerBmiMapper {
    long countByExample(MerBmiExample example);

    int deleteByExample(MerBmiExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(MerBmi record);

    int insertSelective(MerBmi record);

    List<MerBmi> selectByExample(MerBmiExample example);

    MerBmi selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") MerBmi record, @Param("example") MerBmiExample example);

    int updateByExample(@Param("record") MerBmi record, @Param("example") MerBmiExample example);

    int updateByPrimaryKeySelective(MerBmi record);

    int updateByPrimaryKey(MerBmi record);
    
    MerBmi selectBymerId(Integer merId);
}