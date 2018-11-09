package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.MerUricAcid;
import com.yhcrt.iHealthCloud.entity.MerUricAcidExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerUricAcidMapper {
    long countByExample(MerUricAcidExample example);

    int deleteByExample(MerUricAcidExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(MerUricAcid record);

    int insertSelective(MerUricAcid record);

    List<MerUricAcid> selectByExample(MerUricAcidExample example);

    MerUricAcid selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") MerUricAcid record, @Param("example") MerUricAcidExample example);

    int updateByExample(@Param("record") MerUricAcid record, @Param("example") MerUricAcidExample example);

    int updateByPrimaryKeySelective(MerUricAcid record);

    int updateByPrimaryKey(MerUricAcid record);
    
    MerUricAcid selectByMerId(Integer merId);
}