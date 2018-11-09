package com.yhcrt.healthcloud.memberBack.service;

import java.util.List;

import com.yhcrt.healthcloud.memberBack.entity.MemberContact;

/**
 * hull 会员亲属关系service
 * @author PC
 * 
 */
public interface MemberContactService {

	//根据会员id查询亲属信息
	List<MemberContact> queryByMemberId(Integer memberId);

}
