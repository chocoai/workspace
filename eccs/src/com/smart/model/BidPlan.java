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
 * BidPlan entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bid_plan", catalog = "eccs")
public class BidPlan implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	private Integer id;

	private Project project;

	private String bidDeptName;

	private Dept bidDept; // 投标部门

	private Date bidDate;

	private String area;

	private String tendDeptName;

	private String tendUserName;

	private String tendTel;

	private String tendFax;

	private String tendMail;

	private String tendAddress;

	private String tendRange;

	private String tendContent;

	private String bidNeed;

	private Date openDate;

	private String openPlace;

	private Double bondAmount;

	private Date bondEndDate;

	private String bondAccount;

	private String bondBank;

	private String bondAccountNumber;

	private String bidFileIds;

	private String masterName;

	// private String auditName;
	private User approver;

	private String auditContent;

	private User createUser;

	private Dept createDept;

	private Date ctime;

	private Integer status;// 1 正常状态 2 评审状态

	private String handler;

	private String operaterType; // 处理方式，0表示继续项目，1表示终止项目

	private boolean resStates; // 处理权限

	// Constructors

	/** default constructor */
	public BidPlan() {
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
	@JoinColumn(name = "project_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bid_dept_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Dept getBidDept() {
		return bidDept;
	}

	public void setBidDept(Dept bidDept) {
		this.bidDept = bidDept;
	}

	@Column(name = "bid_dept_name", length = 20)
	public String getBidDeptName() {
		return this.bidDeptName;
	}

	public void setBidDeptName(String bidDeptName) {
		this.bidDeptName = bidDeptName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "bid_date", length = 10)
	public Date getBidDate() {
		return this.bidDate;
	}

	public void setBidDate(Date bidDate) {
		this.bidDate = bidDate;
	}

	@Column(name = "area", length = 20)
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "tend_dept_name", length = 20)
	public String getTendDeptName() {
		return this.tendDeptName;
	}

	public void setTendDeptName(String tendDeptName) {
		this.tendDeptName = tendDeptName;
	}

	@Column(name = "tend_user_name", length = 20)
	public String getTendUserName() {
		return this.tendUserName;
	}

	public void setTendUserName(String tendUserName) {
		this.tendUserName = tendUserName;
	}

	@Column(name = "tend_tel", length = 50)
	public String getTendTel() {
		return this.tendTel;
	}

	public void setTendTel(String tendTel) {
		this.tendTel = tendTel;
	}

	@Column(name = "tend_fax", length = 50)
	public String getTendFax() {
		return this.tendFax;
	}

	public void setTendFax(String tendFax) {
		this.tendFax = tendFax;
	}

	@Column(name = "tend_mail", length = 100)
	public String getTendMail() {
		return this.tendMail;
	}

	public void setTendMail(String tendMail) {
		this.tendMail = tendMail;
	}

	@Column(name = "tend_address", length = 1000)
	public String getTendAddress() {
		return this.tendAddress;
	}

	public void setTendAddress(String tendAddress) {
		this.tendAddress = tendAddress;
	}

	@Column(name = "tend_range", length = 1000)
	public String getTendRange() {
		return this.tendRange;
	}

	public void setTendRange(String tendRange) {
		this.tendRange = tendRange;
	}

	@Column(name = "tend_content", length = 1000)
	public String getTendContent() {
		return this.tendContent;
	}

	public void setTendContent(String tendContent) {
		this.tendContent = tendContent;
	}

	@Column(name = "bid_need", length = 200)
	public String getBidNeed() {
		return this.bidNeed;
	}

	public void setBidNeed(String bidNeed) {
		this.bidNeed = bidNeed;
	}

	@Column(name = "open_date", length = 19)
	public Date getOpenDate() {
		return this.openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	@Column(name = "open_place", length = 100)
	public String getOpenPlace() {
		return this.openPlace;
	}

	public void setOpenPlace(String openPlace) {
		this.openPlace = openPlace;
	}

	@Column(name = "bond_amount", precision = 22, scale = 0)
	public Double getBondAmount() {
		return this.bondAmount;
	}

	public void setBondAmount(Double bondAmount) {
		this.bondAmount = bondAmount;
	}

	@Column(name = "bond_end_date", length = 19)
	public Date getBondEndDate() {
		return this.bondEndDate;
	}

	public void setBondEndDate(Date bondEndDate) {
		this.bondEndDate = bondEndDate;
	}

	@Column(name = "bond_account", length = 100)
	public String getBondAccount() {
		return this.bondAccount;
	}

	public void setBondAccount(String bondAccount) {
		this.bondAccount = bondAccount;
	}

	@Column(name = "bond_bank", length = 100)
	public String getBondBank() {
		return this.bondBank;
	}

	public void setBondBank(String bondBank) {
		this.bondBank = bondBank;
	}

	@Column(name = "bond_account_number", length = 100)
	public String getBondAccountNumber() {
		return this.bondAccountNumber;
	}

	public void setBondAccountNumber(String bondAccountNumber) {
		this.bondAccountNumber = bondAccountNumber;
	}

	@Column(name = "bid_file_ids", length = 30)
	public String getBidFileIds() {
		return this.bidFileIds;
	}

	public void setBidFileIds(String bidFileIds) {
		this.bidFileIds = bidFileIds;
	}

	@Column(name = "master_name", length = 20)
	public String getMasterName() {
		return this.masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	/*
	 * @Column(name = "audit_name", length = 20) public String getAuditName() {
	 * return this.auditName; }
	 * 
	 * public void setAuditName(String auditName) { this.auditName = auditName;
	 * }
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "audit_name")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getApprover() {
		return approver;
	}

	public void setApprover(User approver) {
		this.approver = approver;
	}

	@Column(name = "audit_content", length = 1000)
	public String getAuditContent() {
		return this.auditContent;
	}

	public void setAuditContent(String auditContent) {
		this.auditContent = auditContent;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Dept getCreateDept() {
		return createDept;
	}

	public void setCreateDept(Dept createDept) {
		this.createDept = createDept;
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

	@Column(name = "handler", length = 16)
	public String getHandler() {
		return handler;
	}

	public void setHandler(String handler) {
		this.handler = handler;
	}

	@Column(name = "operater_type", length = 2)
	public String getOperaterType() {
		return operaterType;
	}

	public void setOperaterType(String operaterType) {
		this.operaterType = operaterType;
	}

	@Transient
	public boolean getResStates() {
		return resStates;
	}

	public void setResStates(boolean resStates) {
		this.resStates = resStates;
	}

}