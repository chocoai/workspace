package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.MerBodyFat;
import com.yhcrt.iHealthCloud.entity.MerBodyFatExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerBodyFatMapper {
    long countByExample(MerBodyFatExample example);

    int deleteByExample(MerBodyFatExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(MerBodyFat record);

    int insertSelective(MerBodyFat record);

    List<MerBodyFat> selectByExample(MerBodyFatExample example);

    MerBodyFat selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") MerBodyFat record, @Param("example") MerBodyFatExample example);

    int updateByExample(@Param("record") MerBodyFat record, @Param("example") MerBodyFatExample example);

    int updateByPrimaryKeySelective(MerBodyFat record);

    int updateByPrimaryKey(MerBodyFat record);
    
    MerBodyFat selectByMerId(Integer merId);
}