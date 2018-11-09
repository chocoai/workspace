package com.yhcrt.healthcloud.health.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.health.entity.DoctorProposal;
import com.yhcrt.healthcloud.health.entity.DoctorProposalExample;
import com.yhcrt.healthcloud.health.mapper.DoctorProposalMapper;
import com.yhcrt.healthcloud.health.service.DoctorProposalService;

@Service
public class DoctorProposalServiceImpl implements DoctorProposalService {

	@Autowired
	private DoctorProposalMapper doctorProposalMapper;

	// zhengjiadong
	@Override
	public List<DoctorProposal> selectAdvice(Integer memberId) {
		return doctorProposalMapper.selectAdvice(memberId);
	}

	@Override
	public DoctorProposal selectAdviceDetail(Integer cid) {
		return doctorProposalMapper.selectByPrimaryKey(cid);
	}

	// huzelin
	@Override
	public DoctorProposal getProposalByCid(int cid) {
		return doctorProposalMapper.selectByPrimaryKey(cid);
	}

	@Override
	public List<DoctorProposal> getProposalByMemberId(int memberId) {
		DoctorProposalExample example = new DoctorProposalExample();
		example.createCriteria().andMemberIdEqualTo(memberId);
		example.setOrderByClause("proposal_time desc");
		return doctorProposalMapper.selectByExample(example);
	}

	@Override
	public List<DoctorProposal> getProposalByDocId(int docId) {
		DoctorProposalExample example = new DoctorProposalExample();
		example.createCriteria().andDocIdEqualTo(docId);
		example.setOrderByClause("cid desc");
		return doctorProposalMapper.selectByExample(example);
	}

	@Override
	public List<DoctorProposal> getProposalsByTime(Date startTime, Date endTime) {
		DoctorProposalExample example = new DoctorProposalExample();
		example.createCriteria().andProposalTimeBetween(startTime.toString(), endTime.toString());
		example.setOrderByClause("cid desc");
		return doctorProposalMapper.selectByExample(example);
	}

}
