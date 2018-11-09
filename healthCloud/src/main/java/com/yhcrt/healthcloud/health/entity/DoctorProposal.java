package com.yhcrt.healthcloud.health.entity;

import com.yhcrt.healthcloud.memberBack.entity.MemberBack;
import com.yhcrt.healthcloud.organization.entity.Doctor;

public class DoctorProposal {

	private Integer cid;

	private Integer docId;

	private Integer memberId;

	private String proposalContent;

	private String proposalTime;

	private Doctor doctor;

	private MemberBack member;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getDocId() {
		return docId;
	}

	public void setDocId(Integer docId) {
		this.docId = docId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getProposalContent() {
		return proposalContent;
	}

	public void setProposalContent(String proposalContent) {
		this.proposalContent = proposalContent == null ? null : proposalContent.trim();
	}

	public String getProposalTime() {
		return proposalTime;
	}

	public void setProposalTime(String proposalTime) {
		this.proposalTime = proposalTime == null ? null : proposalTime.trim();
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public MemberBack getMember() {
		return member;
	}

	public void setMember(MemberBack member) {
		this.member = member;
	}

}