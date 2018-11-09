package com.yhcrt.healthcloud.health.service;

import java.util.List;

import com.yhcrt.healthcloud.memberBack.entity.MemberBack;


/**
 * 会员查询服务接口
 * @author huzelin
 *
 */
public interface MemberQueryService {

	/**
	 * 获取所有的会员集合
	 * @return 所有的会员集合
	 */
	public List<MemberBack> getAllMember();
	
	/**
	 * 根据memberId获取会员对象
	 * @param memberId 会员编号
	 * @return 会员对象
	 */
	public MemberBack getMemberByMemberId(int memberId);
	
	/**
	 * 调教查询会员对象
	 * @param orgList 机构代码
	 * @param realName 真实姓名
	 * @param idCard 身份证号
	 * @return 会员对象集合
	 */
	public List<MemberBack> getMemberByCondition(List<Integer> orgList, String realName, String idCard);
}
