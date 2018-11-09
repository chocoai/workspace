package com.yhcrt.healthcloud.memberBack.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.healthcloud.memberBack.entity.MemberRecharge;
import com.yhcrt.healthcloud.memberBack.entity.MemberRechargeExample;
import com.yhcrt.healthcloud.memberBack.mapper.MemberRechargeMapper;
import com.yhcrt.healthcloud.memberBack.service.MemberRechargeService;

@Transactional
@Service
public class MemberRechargeServiceImpl implements MemberRechargeService{

	@Autowired
	private MemberRechargeMapper memberRechargeMapper;
	@Override
	public List<MemberRecharge> selectAll() {
		return memberRechargeMapper.selectByExample(null);
	}

	@Override
	public MemberRecharge getMemberRecharge(String cid) {
		MemberRechargeExample example =new MemberRechargeExample();
		example.createCriteria().andCidEqualTo(cid);
		return memberRechargeMapper.selectByExample(example).get(0);
	}

	@Override
	public Integer addMemberRecharge(MemberRecharge memberRecharge) {
		return memberRechargeMapper.insert(memberRecharge);
	}

	@Override
	public Integer delMemberRecharge(String cid) {
		MemberRechargeExample example =new MemberRechargeExample();
		example.createCriteria().andCidEqualTo(cid);
		return memberRechargeMapper.deleteByExample(example);
	}

	@Override
	public Integer updMemberRecharge(MemberRecharge mmberRecharge) {
		MemberRechargeExample example =new MemberRechargeExample();
		example.createCriteria().andCidEqualTo(mmberRecharge.getCid());
		return memberRechargeMapper.updateByExample(mmberRecharge, example);
	}

}
