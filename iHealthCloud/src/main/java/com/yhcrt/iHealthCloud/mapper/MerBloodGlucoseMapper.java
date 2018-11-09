package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.MerBloodGlucose;
import com.yhcrt.iHealthCloud.entity.MerBloodGlucoseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerBloodGlucoseMapper {
    long countByExample(MerBloodGlucoseExample example);

    int deleteByExample(MerBloodGlucoseExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(MerBloodGlucose record);

    int insertSelective(MerBloodGlucose record);

    List<MerBloodGlucose> selectByExample(MerBloodGlucoseExample example);

    MerBloodGlucose selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") MerBloodGlucose record, @Param("example") MerBloodGlucoseExample example);

    int updateByExample(@Param("record") MerBloodGlucose record, @Param("example") MerBloodGlucoseExample example);

    int updateByPrimaryKeySelective(MerBloodGlucose record);

    int updateByPrimaryKey(MerBloodGlucose record);
    
    MerBloodGlucose selectByMerId(Integer merId);
}