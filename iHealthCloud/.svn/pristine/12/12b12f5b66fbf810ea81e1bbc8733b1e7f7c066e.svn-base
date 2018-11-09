package com.yhcrt.iHealthCloud.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.entity.DoctorProposal;
import com.yhcrt.iHealthCloud.entity.DoctorProposalExample;
import com.yhcrt.iHealthCloud.mapper.DoctorProposalMapper;
import com.yhcrt.iHealthCloud.pojo.DoctorProposalItem;
import com.yhcrt.iHealthCloud.service.DoctorProposalService;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.DateUtil;

/**
 * 
 * @author huzelin
 *
 */
@Service
public class DoctorProposalServiceImpl extends BaseService implements DoctorProposalService {

	protected boolean isDebug = true;

	@Autowired
	private DoctorProposalMapper proposalMapper;

	@Override
	public String getProposalByCid(JSONObject pdataObj) {
		// 获取cid
		String cid = getCid(pdataObj);
		// 对参数进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, cid)) {
			// 查询数据库
			DoctorProposalItem doctorProposal = proposalMapper.getDoctorProposalById(Integer.parseInt(cid));
			// 对数据库返回结果进行非空判断
			if (doctorProposal == null) {
				requestFailed(pdataObj, " 'cid' doesn't exist ");
			} else {
				requestSucceed(pdataObj, doctorProposal, "");
			}
		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String insertProposal(JSONObject pdataObj) {

		// 获取biz
		JSONObject biz = pdataObj.getJSONObject("biz");
		String docId = biz.getString("doc_id");
		String memberId = biz.getString("member_id");
		String ProposalContent = biz.getString("proposal_content");

		// 对参数进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, docId, memberId, ProposalContent)) {
			DoctorProposal doctorProposal = new DoctorProposal();
			doctorProposal.setDocId(Integer.valueOf(docId));
			doctorProposal.setMemberId(Integer.valueOf(memberId));
			doctorProposal.setProposalContent(ProposalContent);
			doctorProposal.setProposalTime(DateUtil.format(new Date(), DateUtil.DATE_PATTERN.YYYY_MM_DD_HH_MM_SS));
			proposalMapper.insert(doctorProposal);
			requestSucceed(pdataObj, doctorProposal, "");
		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}

		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public String getProposalListByMemberId(JSONObject pdataObj) {
		// 获取参数
		String memberId = getMemberId(pdataObj);
		String currentPage = getCurrentPage(pdataObj);
		String pageSize = getPageSize(pdataObj);

		// 判断参数是否为空
		if (judgeAgumentsIsLegal(pdataObj, memberId)) {

			// 向数据库请求数据并判断是否分页
			List<DoctorProposalItem> doctorProposals;
			if (judgePageInfoIsLegal(currentPage, pageSize)) {

				PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
				doctorProposals = proposalMapper.listDoctorProposal(memberId);
				PageInfo<DoctorProposalItem> p = new PageInfo<>(doctorProposals);
				setPagingData(pdataObj, p.getPages(), p.getPageNum());

			} else {
				doctorProposals = proposalMapper.listDoctorProposal(memberId);
			}

			requestSucceed(pdataObj, doctorProposals, "");

		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return toJsonStringWithOutNull(pdataObj);
	}

	@Override
	public List<DoctorProposal> getProposalListByMemberId(String memberId) {
		if (memberId == null || "".equals(memberId)) {
			return new ArrayList<DoctorProposal>();
		} else {
			DoctorProposalExample example = new DoctorProposalExample();
			example.createCriteria().andMemberIdEqualTo(Integer.parseInt(memberId));
			example.setOrderByClause("proposal_time desc");
			return proposalMapper.selectByExample(example);
		}
	}

}
