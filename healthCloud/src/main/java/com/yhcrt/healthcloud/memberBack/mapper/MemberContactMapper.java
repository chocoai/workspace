package com.yhcrt.healthcloud.memberBack.mapper;

import java.util.List;

import com.yhcrt.healthcloud.memberBack.entity.MemberContact;
import com.yhcrt.healthcloud.memberBack.entity.MemberContactExample;

public interface MemberContactMapper {

	long countByExample(MemberContactExample example);

	int deleteByExample(MemberContactExample example);

	List<MemberContact> selectByExample(MemberContactExample example);

	List<MemberContact> selectByMemberId(Integer memberId);

	// 批量新增
	void addBatch(List<MemberContact> list);

}