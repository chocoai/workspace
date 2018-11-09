package com.yhcrt.healthcloud.memberBack.mapper;

import com.yhcrt.healthcloud.memberBack.entity.MemberInfo;

public interface MemberInfoMapper {
    
	//根据memberId查询详细信息
    MemberInfo selectByMemberId(Integer memberId);

    //修改详细信息数据
	void updateByMemberInfo(MemberInfo memberInfo);

	void insert(MemberInfo memberInfo);
    
}