package com.yhcrt.healthcloud.memberBack.service;

import java.util.List;
import java.util.Map;

import com.yhcrt.healthcloud.memberBack.entity.MemberBack;
import com.yhcrt.healthcloud.memberBack.entity.MemberDeviceBack;
import com.yhcrt.healthcloud.memberBack.entity.MemberRelationshipBack;
import com.yhcrt.healthcloud.system.entity.SysUser;

/* @Description: 会员管理SERVICE层接口
 * @version	1.0		2017年5月22日
 * @author jimmy
*/
public interface MemberBackService {

    List<MemberBack> selAll(List<Integer> orgId_list, Integer status);

    List<MemberBack> search(List<Integer> orgId_list, String realName, String imei, String identity_card, Integer status);

    MemberBack select(Integer memberId);

    Integer delete(MemberBack member, SysUser user);

    Integer recover(MemberBack member, SysUser user);
    
    List<MemberDeviceBack> selectDeviceByMemberId(Integer memberId);
    
    List<MemberRelationshipBack> selectMemberFollow(Integer memberId);
    
    List<MemberRelationshipBack> selectFollowMember(Integer memberId);

    List<MemberBack> selectByEmpId(Integer empId,Integer status);

    Integer update(MemberBack member);
    
    //移除分配的会员
    Integer updateByMember(MemberBack member);

    List<MemberBack> selectByDocIdEqualToNull(Integer orgId, String realName, String phoneNo,Integer status);

    List<MemberBack> selectByDocId(Integer docId,Integer status);

    //查询未绑定机构的会员
	List<MemberBack> queryByNameAndCard(Map<String, String> map);

	//查询该员工所属机构及旗下机构中可分配的会员
	List<MemberBack> queryByEid(Map<String, Object> map);

	//查询该医师所属机构及旗下机构中可分配的会员
	List<MemberBack> selectByDid(Map<String, Object> map);

	//员工批量分配或移除会员
	Integer updateBatchEid(Map<String, Object> map);
	
	//医师批量分配或移除会员
	Integer updateBatchDid(Map<String, Object> map);

	//根据memberId查询会员个人信息
	MemberBack queryByMemberId(Integer memberId);
	
	//根据手机号查询会员个人信息
	MemberBack queryByTel(String tel);

	//为用户服务的人员
	List<MemberBack> getByMemberId(Integer memberId);

	//修改会员信息和详细信息
	void updateByMemberAndInfo(MemberBack member);

	//不管是分配还是移除 首先将会员表中empId清空 
	Integer updateByEmpId(Map<String, Object> map);

	//不管是分配还是移除 首先将会员表中docId清空 
	Integer updateByDocId(Map<String, Object> map);

}
