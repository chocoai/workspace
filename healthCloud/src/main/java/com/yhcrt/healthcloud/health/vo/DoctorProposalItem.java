package com.yhcrt.healthcloud.health.vo;

import com.yhcrt.healthcloud.health.entity.DoctorProposal;
import com.yhcrt.healthcloud.organization.entity.Doctor;

/**
 * 医师建议列表条目ov封装对象
 * @author huzelin
 *
 */
public class DoctorProposalItem {

	private Doctor doctor;
	
	private DoctorProposal proposal;

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public DoctorProposal getProposal() {
		return proposal;
	}

	public void setProposal(DoctorProposal proposal) {
		this.proposal = proposal;
	}
	
}
