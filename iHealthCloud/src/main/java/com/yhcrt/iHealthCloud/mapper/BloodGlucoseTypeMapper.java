package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.BloodGlucoseType;
import com.yhcrt.iHealthCloud.entity.BloodGlucoseTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BloodGlucoseTypeMapper {
    long countByExample(BloodGlucoseTypeExample example);

    int deleteByExample(BloodGlucoseTypeExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(BloodGlucoseType record);

    int insertSelective(BloodGlucoseType record);

    List<BloodGlucoseType> selectByExample(BloodGlucoseTypeExample example);

    BloodGlucoseType selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") BloodGlucoseType record, @Param("example") BloodGlucoseTypeExample example);

    int updateByExample(@Param("record") BloodGlucoseType record, @Param("example") BloodGlucoseTypeExample example);

    int updateByPrimaryKeySelective(BloodGlucoseType record);

    int updateByPrimaryKey(BloodGlucoseType record);
}