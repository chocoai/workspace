package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.MerCholesterol;
import com.yhcrt.iHealthCloud.entity.MerCholesterolExample;
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
    
    MerCholesterol selectByMerId(Integer merId);
}