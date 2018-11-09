package com.yhcrt.healthcloud.memberBack.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.yhcrt.healthcloud.memberBack.entity.MemberLevel;
import com.yhcrt.healthcloud.memberBack.entity.MemberLevelExample;

public interface MemberLevelMapper {
    long countByExample(MemberLevelExample example);

    int deleteByExample(MemberLevelExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(MemberLevel record);

    int insertSelective(MemberLevel record);

    List<MemberLevel> selectByExample(MemberLevelExample example);

    MemberLevel selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") MemberLevel record, @Param("example") MemberLevelExample example);

    int updateByExample(@Param("record") MemberLevel record, @Param("example") MemberLevelExample example);

    int updateByPrimaryKeySelective(MemberLevel record);

    int updateByPrimaryKey(MemberLevel record);
}