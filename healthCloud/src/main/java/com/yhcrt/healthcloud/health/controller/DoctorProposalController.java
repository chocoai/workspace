package com.yhcrt.healthcloud.health.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yhcrt.healthcloud.health.entity.DoctorProposal;
import com.yhcrt.healthcloud.health.service.DoctorProposalService;

/**
 * 医师建议Controller
 * @author huzelin
 *
 */
@Controller
@RequestMapping("/healthService")
public class DoctorProposalController {
	
	@Autowired private DoctorProposalService doctorProposalService;

	@RequestMapping(value="/proposal")
	public String doctorProposal(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@RequestParam(name="pageValue",defaultValue="1")Integer page,
			@RequestParam(name="memberId",defaultValue="0")Integer memberId){
		
		// 开始分页
		// PageHelper.startPage(page, 10);
		List<DoctorProposal> proposals = doctorProposalService.getProposalByMemberId(memberId);
		// PageInfo<DoctorProposal> p = new PageInfo<DoctorProposal>(proposals);
		
		// 传入vo集合
		modelMap.addAttribute("proposals", proposals);
		modelMap.addAttribute("memberId", memberId);
		// 传入分页对象
		// modelMap.addAttribute("page", p);
		
		return "/health/jsp/doctor_proposal";
	}
	
	
	@RequestMapping(value="/proposalDetail")
	public String doctorProposalDetail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@RequestParam(name="cid",defaultValue="0")Integer cid){
		// 获取医师建议对象
		DoctorProposal proposal = doctorProposalService.getProposalByCid(cid);
		// 向页面传入对象
		modelMap.addAttribute("proposal", proposal);
		return "/health/jsp/proposal_detail";
	}
}
