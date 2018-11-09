package com.yhcrt.healthcloud.memberBack.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.memberBack.entity.MemberContact;
import com.yhcrt.healthcloud.memberBack.entity.MemberContactExample;
import com.yhcrt.healthcloud.memberBack.mapper.MemberContactMapper;
import com.yhcrt.healthcloud.memberBack.service.MemberContactService;

@Service
public class MemberContactServiceImpl implements MemberContactService {

	@Autowired
	private MemberContactMapper memberContactMapper;

	// 根据会员id查询亲属信息
	@Override
	public List<MemberContact> queryByMemberId(Integer memberId) {
		MemberContactExample example =new MemberContactExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		example.setOrderByClause("create_time");
		return memberContactMapper.selectByExample(example);
	}

}
