package com.yhcrt.healthcloud.health.mapper;

import com.yhcrt.healthcloud.health.entity.MerCholesterol;
import com.yhcrt.healthcloud.health.entity.MerCholesterolExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerCholesterolMapper {
    long countByExample(MerCholesterolExample example);

    int deleteByExample(MerCholesterolExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(MerCholesterol record);

    int insertSelective(MerCholesterol record);

    List<MerCholesterol> selectByExample(MerCholesterolExample example);

    MerCholesterol selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") MerCholesterol record, @Param("example") MerCholesterolExample example);

    int updateByExample(@Param("record") MerCholesterol record, @Param("example") MerCholesterolExample example);

    int updateByPrimaryKeySelective(MerCholesterol record);

    int updateByPrimaryKey(MerCholesterol record);
    
    MerCholesterol selectBymerId(Integer merId);
}