package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.Member;
import com.yhcrt.iHealthCloud.entity.MemberExample;
import com.yhcrt.iHealthCloud.pojo.IndexData;
import com.yhcrt.iHealthCloud.pojo.MemberDTO;
import com.yhcrt.iHealthCloud.pojo.OrgServicer;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberMapper {
    long countByExample(MemberExample example);

    int deleteByExample(MemberExample example);

    int deleteByPrimaryKey(Integer memberId);

    int insert(Member record);

    int insertSelective(Member record);
    
    List<Member> selectByExample(MemberExample example);
    
    List<MemberDTO> listMemberByExample(MemberExample example);
    
    List<MemberDTO> listDeviceMemberByExample(MemberExample example);

    Member selectByPrimaryKey(Integer memberId);

    int updateByExampleSelective(@Param("record") Member record, @Param("example") MemberExample example);

    int updateByExample(@Param("record") Member record, @Param("example") MemberExample example);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
    
    Member selectByUserId(Integer userId);
    
    List<OrgServicer> listOrgServicer(String memberId);
    
    List<com.yhcrt.iHealthCloud.pojo.MemberDTO> listMyFollowing(String memberId);
    
    List<com.yhcrt.iHealthCloud.pojo.MemberDTO> listMyFollowers(String memberId);
    
    List<IndexData> listIndexData(String memberId);
    
	int setProfilePhotoByUserId(@Param("userId") String userId, @Param("profilePhotoUrl") String profilePhotoUrl);

}