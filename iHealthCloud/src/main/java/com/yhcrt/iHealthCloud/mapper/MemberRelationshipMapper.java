package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.MemberRelationship;
import com.yhcrt.iHealthCloud.entity.MemberRelationshipExample;
import com.yhcrt.iHealthCloud.pojo.MemberRelationshipDTO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MemberRelationshipMapper {
    long countByExample(MemberRelationshipExample example);

    int deleteByExample(MemberRelationshipExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(MemberRelationship record);

    int insertSelective(MemberRelationship record);

    List<MemberRelationship> selectByExample(MemberRelationshipExample example);
    
    List<MemberRelationshipDTO> getMyFollowingForSmall(String memberId);

    MemberRelationship selectByPrimaryKey(Integer cid);
    
    public List<MemberRelationship> selectMemRelaF(Integer memberId);
	 
	public List<MemberRelationship> selectFMemRela(Integer followerId);

    int updateByExampleSelective(@Param("record") MemberRelationship record, @Param("example") MemberRelationshipExample example);

    int updateByExample(@Param("record") MemberRelationship record, @Param("example") MemberRelationshipExample example);

    int updateByPrimaryKeySelective(MemberRelationship record);

    int updateByPrimaryKey(MemberRelationship record);
}