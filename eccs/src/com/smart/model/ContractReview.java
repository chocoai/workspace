package com.smart.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * ContractReview entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "contract_review", catalog = "eccs")
public class ContractReview implements java.io.Serializable {

	private static final long serialVersionUID = -2721659011711266813L;

	// Fields
	private Integer id;

	private String contractNo; // 合同编号

	private Contract contract;

	private User sendUser; // 委托单位

	private String recordName;

	private Date recordDate;

	private String reviewName;

	private String reviewView;

	private User approver; // 审批人

	private String approveView;

	private User user;

	private Dept dept;

	private Date ctime;

	private Integer status;// 1 正常状态 2 审批状态

	private String operaterType; // 处理方式，0表示继续项目，1表示终止项目

	private boolean operable; // 处理权限

	// Constructors
	/** default constructor */
	public ContractReview() {
		ctime = new Date();
		status = 1;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contract_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "send_user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getSendUser() {
		return sendUser;
	}

	public void setSendUser(User sendUser) {
		this.sendUser = sendUser;
	}

	@Column(name = "record_name", length = 50)
	public String getRecordName() {
		return this.recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "record_date", length = 10)
	public Date getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	@Column(name = "review_name", length = 100)
	public String getReviewName() {
		return this.reviewName;
	}

	public void setReviewName(String reviewName) {
		this.reviewName = reviewName;
	}

	@Column(name = "review_view", length = 1000)
	public String getReviewView() {
		return this.reviewView;
	}

	public void setReviewView(String reviewView) {
		this.reviewView = reviewView;
	}

	@Column(name = "approve_view", length = 1000)
	public String getApproveView() {
		return this.approveView;
	}

	public void setApproveView(String approveView) {
		this.approveView = approveView;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	@Column(name = "ctime", length = 19)
	public Date getCtime() {
		return this.ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "approve_name")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getApprover() {
		return approver;
	}

	public void setApprover(User approver) {
		this.approver = approver;
	}

	@Column(name = "operater_type", length = 2)
	public String getOperaterType() {
		return operaterType;
	}

	public void setOperaterType(String operaterType) {
		this.operaterType = operaterType;
	}

	@Column(name = "contract_no", length = 16)
	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	@Transient
	public boolean getOperable() {
		return this.operable;
	}

	public void setOperable(boolean operable) {
		this.operable = operable;
	}

}