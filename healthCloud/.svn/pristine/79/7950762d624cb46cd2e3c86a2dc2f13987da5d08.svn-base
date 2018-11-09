package com.yhcrt.healthcloud.memberBack.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.healthcloud.memberBack.entity.MemberRelationshipBack;
import com.yhcrt.healthcloud.memberBack.entity.MemberRelationshipExampleBack;

public interface MemberRelationshipBackMapper {
    long countByExample(MemberRelationshipExampleBack example);

    int deleteByExample(MemberRelationshipExampleBack example);

    int deleteByPrimaryKey(Integer cid);

    int insert(MemberRelationshipBack record);

    int insertSelective(MemberRelationshipBack record);

    List<MemberRelationshipBack> selectByExample(MemberRelationshipExampleBack example);

    MemberRelationshipBack selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") MemberRelationshipBack record, @Param("example") MemberRelationshipExampleBack example);

    int updateByExample(@Param("record") MemberRelationshipBack record, @Param("example") MemberRelationshipExampleBack example);

    int updateByPrimaryKeySelective(MemberRelationshipBack record);

    int updateByPrimaryKey(MemberRelationshipBack record);
}