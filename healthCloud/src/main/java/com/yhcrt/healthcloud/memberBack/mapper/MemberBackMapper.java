package com.yhcrt.healthcloud.memberBack.mapper;

import com.yhcrt.healthcloud.memberBack.entity.MemberBack;
import com.yhcrt.healthcloud.memberBack.entity.MemberExampleBack;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MemberBackMapper {
    long countByExample(MemberExampleBack example);

    int deleteByExample(MemberExampleBack example);

    int deleteByPrimaryKey(Integer memberId);

    int insert(MemberBack record);

    int insertSelective(MemberBack record);

    List<MemberBack> selectByExample(MemberExampleBack example);

    MemberBack selectByPrimaryKey(Integer memberId);

    int updateByExampleSelective(@Param("record") MemberBack record, @Param("example") MemberExampleBack example);

    int updateByExample(@Param("record") MemberBack record, @Param("example") MemberExampleBack example);

    int updateByPrimaryKeySelective(MemberBack record);

    int updateByPrimaryKey(MemberBack record);

    List<MemberBack> search(Map<String, Object> map);

    List<MemberBack> selectByEmpId(Integer empId);
    
    List<MemberBack> selectByDocId(Integer docId);

    //查询未绑定机构的会员
	List<MemberBack> queryByNameAndCard(Map<String, String> map);

	//查询该员工所属机构及旗下机构中可分配的会员
	List<MemberBack> queryByEid(Map<String, Object> map);

	//查询该医师所属机构及旗下机构中可分配的会员
	List<MemberBack> queryByDid(Map<String, Object> map);

	//员工批量分配或移除会员
	Integer updateBatchEid(Map<String, Object> map);

	//医师批量分配或移除会员
	Integer updateBatchDid(Map<String, Object> map);

	//根据memberId查询会员个人信息
	MemberBack queryByMemberId(Integer memberId);
	
	//根据Tel查询会员个人信息
	MemberBack queryByTel(String Tel);

	//为用户服务的人员
	List<MemberBack> getByMemberId(Integer memberId);

	//不管是分配还是移除 首先将会员表中empId清空 
	Integer updateByEmpId(Map<String, Object> map);

	//不管是分配还是移除 首先将会员表中docId清空 
	Integer updateByDocId(Map<String, Object> map);
	
}