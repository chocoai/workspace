package com.yhcrt.iHealthCloud.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.entity.DoctorProposal;

/**
 * 医师建议服务接口
 * @author huzelin
 *
 */
public interface DoctorProposalService {

	/**
	 * 根据cid获取医师建议详情
	 * @param pdataObj
	 * @return json字符串
	 */
	public String getProposalByCid(JSONObject pdataObj);
	
	/**
	 * @param pdataObj
	 * @return json字符串
	 */
	public String insertProposal(JSONObject pdataObj);
	
	/**
	 * 根据memberId获取医师建议列表
	 * @param pdataObj
	 * @return
	 */
	public String getProposalListByMemberId(JSONObject pdataObj);
	
	public List<DoctorProposal> getProposalListByMemberId(String memberId);
}
