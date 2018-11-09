package com.yhcrt.healthcloud.health.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.common.Constants.MemberType;
import com.yhcrt.healthcloud.health.service.MemberQueryService;
import com.yhcrt.healthcloud.memberBack.entity.MemberBack;
import com.yhcrt.healthcloud.memberBack.entity.MemberExampleBack;
import com.yhcrt.healthcloud.memberBack.entity.MemberExampleBack.Criteria;
import com.yhcrt.healthcloud.memberBack.mapper.MemberBackMapper;

/**
 * 
 * @author
 *
 */
@Service
public class MemberQueryServiceImpl implements MemberQueryService {

	@Autowired
	MemberBackMapper memberMapper;

	@Override
	public List<MemberBack> getAllMember() {
		MemberExampleBack example = new MemberExampleBack();
		example.createCriteria().andMemberIdIsNotNull();
		example.setOrderByClause("member_id desc");
		return memberMapper.selectByExample(example);
	}

	@Override
	public MemberBack getMemberByMemberId(int memberId) {
		MemberBack selecByPrimaryKey = memberMapper.selectByPrimaryKey(memberId);
		if (selecByPrimaryKey == null) {
			return new MemberBack();
		} else {
			return selecByPrimaryKey;
		}
	}

	@Override
	public List<MemberBack> getMemberByCondition(List<Integer> orgList, String realName, String idCard) {
		MemberExampleBack example = new MemberExampleBack();
		example.setOrderByClause("member_id desc");
		Criteria createCriteria = example.createCriteria();
		if (StringUtils.isNotBlank(realName)) {
			createCriteria.andRealNameLike("%" + realName + "%");
		}
		if (StringUtils.isNotBlank(idCard)) {
			createCriteria.andIdentityCardLike("%" + idCard + "%");
		}
		if (!orgList.isEmpty() && orgList.size()>0) {
			createCriteria.andOrgIdIn(orgList);
		}
		createCriteria.andStatusEqualTo(Constants.STATUS_NORMAL);
		createCriteria.andMemberTypeEqualTo(MemberType.VIP);
		return memberMapper.selectByExample(example);
	}
}
