package com.yhcrt.healthcloud.health.service;

import java.util.Date;
import java.util.List;

import com.yhcrt.healthcloud.health.entity.DoctorProposal;

/**
 * 医师建议服务
 * @author huzelin
 *
 */
public interface DoctorProposalService {
	
	// zhengjiadong
	//根据memberid查所有次ID的医师建议
	public List<DoctorProposal> selectAdvice(Integer memberId);
	
	//根据医师建议时间查询该ID的详情建议
	public DoctorProposal selectAdviceDetail(Integer cid);
	
	
	/**
	 * 根据主键查找医师建议
	 * @param cid 主键
	 * @return 医师建议对象
	 */
	public DoctorProposal getProposalByCid(int cid);
	
	/**
	 * 根据memberId获取医师建议集合
	 * @param memberId 会员编号
	 * @return 医师建议集合
	 */
	public List<DoctorProposal> getProposalByMemberId(int memberId);
	
	/**
	 * 根据docId获取医师建议集合
	 * @param docId 医师编号
	 * @return 医师建议集合
	 */
	public List<DoctorProposal> getProposalByDocId(int docId);
	
	/**
	 * 根据时间段获取医师建议集合
	 * @param startTime	起始时间
	 * @param endTime 截止时间
	 * @return 医师建议集合
	 */
	public List<DoctorProposal> getProposalsByTime(Date startTime, Date endTime);
}
