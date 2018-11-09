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
 * BidFileAssess generated by hbm2java
 */
@Entity
@Table(name = "bid_file_assess", catalog = "eccs")
public class BidFileAssess implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer cid;
	private ProjectInfo projectInfo;
	private T_Customer customer;
	private String consultType;
	private String bidOpenDate;
	private String responsiblePerson;
	private String firstdraftTime;
	private String completeStatus;
	private String submissionTime;
	private Integer auditResult;
	private Integer auditAssess;
	private Integer status;
	private String createTime;
	private String remark;

	//非数据库字段
	private boolean resStates; // 编辑操作权限
	//非数据库字段
	private boolean checkStates; // 审核操作权限
	
	public BidFileAssess() {
	}

	public BidFileAssess(Integer cid) {
		this.cid = cid;
	}

	public BidFileAssess(Integer cid, ProjectInfo projectInfo, T_Customer customer,
			String consultType, String bidOpenDate, String responsiblePerson,
			String firstdraftTime, String completeStatus, String submissionTime,
			Integer auditResult, Integer auditAssess, Integer status,
			String createTime, String remark) {
		this.cid = cid;
		this.projectInfo = projectInfo;
		this.customer = customer;
		this.consultType = consultType;
		this.bidOpenDate = bidOpenDate;
		this.responsiblePerson = responsiblePerson;
		this.firstdraftTime = firstdraftTime;
		this.completeStatus = completeStatus;
		this.submissionTime = submissionTime;
		this.auditResult = auditResult;
		this.auditAssess = auditAssess;
		this.status = status;
		this.createTime = createTime;
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

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public T_Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(T_Customer customer) {
		this.customer = customer;
	}

	@Column(name = "consult_type", length = 32)
	public String getConsultType() {
		return this.consultType;
	}

	public void setConsultType(String consultType) {
		this.consultType = consultType;
	}

	@Column(name = "bid_open_date", length = 19)
	public String getBidOpenDate() {
		return this.bidOpenDate;
	}

	public void setBidOpenDate(String bidOpenDate) {
		this.bidOpenDate = bidOpenDate;
	}

	@Column(name = "responsible_person", length = 32)
	public String getResponsiblePerson() {
		return this.responsiblePerson;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	@Column(name = "firstdraft_time", length = 19)
	public String getFirstdraftTime() {
		return this.firstdraftTime;
	}

	public void setFirstdraftTime(String firstdraftTime) {
		this.firstdraftTime = firstdraftTime;
	}

	@Column(name = "complete_status", length = 32)
	public String getCompleteStatus() {
		return this.completeStatus;
	}

	public void setCompleteStatus(String completeStatus) {
		this.completeStatus = completeStatus;
	}

	@Column(name = "submission_time", length = 19)
	public String getSubmissionTime() {
		return this.submissionTime;
	}

	public void setSubmissionTime(String submissionTime) {
		this.submissionTime = submissionTime;
	}

	@Column(name = "audit_result")
	public Integer getAuditResult() {
		return this.auditResult;
	}

	public void setAuditResult(Integer auditResult) {
		this.auditResult = auditResult;
	}

	@Column(name = "audit_assess")
	public Integer getAuditAssess() {
		return this.auditAssess;
	}

	public void setAuditAssess(Integer auditAssess) {
		this.auditAssess = auditAssess;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "create_time", length = 19)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	@Transient
	public boolean getCheckStates() {
		return checkStates;
	}

	public void setCheckStates(boolean checkStates) {
		this.checkStates = checkStates;
	}

}