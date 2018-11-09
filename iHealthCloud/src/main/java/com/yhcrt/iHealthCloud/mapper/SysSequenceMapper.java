package com.yhcrt.iHealthCloud.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.SysSequence;
import com.yhcrt.iHealthCloud.entity.SysSequenceExample;

public interface SysSequenceMapper {
    long countByExample(SysSequenceExample example);

    int deleteByExample(SysSequenceExample example);

    int deleteByPrimaryKey(String cid);

    int insert(SysSequence record);

    int insertSelective(SysSequence record);

    List<SysSequence> selectByExample(SysSequenceExample example);

    SysSequence selectByPrimaryKey(String cid);

    int updateByExampleSelective(@Param("record") SysSequence record, @Param("example") SysSequenceExample example);

    int updateByExample(@Param("record") SysSequence record, @Param("example") SysSequenceExample example);

    int updateByPrimaryKeySelective(SysSequence record);

    int updateByPrimaryKey(SysSequence record);
}