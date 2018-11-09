package com.smart.model;

// default package
// Generated 2017-7-5 11:01:11 by Hibernate Tools 4.0.0.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * CallBidAssess generated by hbm2java
 */
@Entity
@Table(name = "call_bid_assess", catalog = "eccs")
public class CallBidAssess implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer cid;
	private ProjectInfo projectInfo;
	private String consultType;
	private String projectAffiliation;
	private String other;
	private String loseScoreCase;
	private String specificRequirements;
	private Integer assessResult;
	private String appraisalnotes;
	private String responsiblePerson;
	private String auditDate;
	private String createTime;
	private Integer status;
	private String remark;

	//非数据库字段
	private boolean resStates; // 编辑操作权限
	

	public CallBidAssess() {
	}

	public CallBidAssess(Integer cid) {
		this.cid = cid;
	}

	public CallBidAssess(Integer cid, ProjectInfo projectInfo, String consultType,
			String projectAffiliation, String other, String loseScoreCase,
			String specificRequirements, Integer assessResult,
			String appraisalnotes, String responsiblePerson, String auditDate,
			String createTime, Integer status, String remark) {
		this.cid = cid;
		this.projectInfo = projectInfo;
		this.consultType = consultType;
		this.projectAffiliation = projectAffiliation;
		this.other = other;
		this.loseScoreCase = loseScoreCase;
		this.specificRequirements = specificRequirements;
		this.assessResult = assessResult;
		this.appraisalnotes = appraisalnotes;
		this.responsiblePerson = responsiblePerson;
		this.auditDate = auditDate;
		this.createTime = createTime;
		this.status = status;
		this.remark = remark;
	}

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "cid", unique = true, nullable = false)
	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectInfo_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public ProjectInfo getProjectInfo() {
		return this.projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	@Column(name = "consult_type", length = 32)
	public String getConsultType() {
		return this.consultType;
	}

	public void setConsultType(String consultType) {
		this.consultType = consultType;
	}

	@Column(name = "project_affiliation", length = 32)
	public String getProjectAffiliation() {
		return this.projectAffiliation;
	}

	public void setProjectAffiliation(String projectAffiliation) {
		this.projectAffiliation = projectAffiliation;
	}

	@Column(name = "other", length = 128)
	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@Column(name = "lose_score_case", length = 256)
	public String getLoseScoreCase() {
		return this.loseScoreCase;
	}

	public void setLoseScoreCase(String loseScoreCase) {
		this.loseScoreCase = loseScoreCase;
	}

	@Column(name = "specific_requirements", length = 256)
	public String getSpecificRequirements() {
		return this.specificRequirements;
	}

	public void setSpecificRequirements(String specificRequirements) {
		this.specificRequirements = specificRequirements;
	}

	@Column(name = "assess_result")
	public Integer getAssessResult() {
		return this.assessResult;
	}

	public void setAssessResult(Integer assessResult) {
		this.assessResult = assessResult;
	}

	@Column(name = "appraisalnotes", length = 128)
	public String getAppraisalnotes() {
		return this.appraisalnotes;
	}

	public void setAppraisalnotes(String appraisalnotes) {
		this.appraisalnotes = appraisalnotes;
	}

	@Column(name = "responsible_person", length = 32)
	public String getResponsiblePerson() {
		return this.responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	@Column(name = "audit_date", length = 19)
	public String getAuditDate() {
		return this.auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	@Column(name = "create_time", length = 19)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "remark", length = 256)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Transient
	public boolean getResStates() {
		return resStates;
	}

	public void setResStates(boolean resStates) {
		this.resStates = resStates;
	}

}
