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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Step2 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "step2", catalog = "eccs")
public class Step2 implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Project project;

	private String projectName;

	private String consultType;

	private String senderUnit;

	private String receiverUnit;

	private Date senderDataDate;

	private Double contractAmount;

	private Double approveAmount;

	private String senderUserName;

	private String receiverUserName;

	private Date receiverEndDate;

	private Dept dept;

	private User user;

	private Date ctime;

	private Integer status;

	private Date confirmTime; // 任务提交时间 --新增

	@Column(name = "confirm_time")
	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	// Constructors

	/** default constructor */
	public Step2() {
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

	@Column(name = "project_name", length = 100)
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	@Column(name = "consult_type_id")
	public String getConsultType() {
		return consultType;
	}

	public void setConsultType(String consultType) {
		this.consultType = consultType;
	}

	@Column(name = "sender_unit", length = 30)
	public String getSenderUnit() {
		return this.senderUnit;
	}

	public void setSenderUnit(String senderUnit) {
		this.senderUnit = senderUnit;
	}

	@Column(name = "receiver_unit", length = 30)
	public String getReceiverUnit() {
		return receiverUnit;
	}

	public void setReceiverUnit(String receiverUnit) {
		this.receiverUnit = receiverUnit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "sender_data_date", length = 10)
	public Date getSenderDataDate() {
		return this.senderDataDate;
	}

	public void setSenderDataDate(Date senderDataDate) {
		this.senderDataDate = senderDataDate;
	}

	@Column(name = "contract_amount", precision = 22, scale = 0)
	public Double getContractAmount() {
		return this.contractAmount;
	}

	public void setContractAmount(Double contractAmount) {
		this.contractAmount = contractAmount;
	}

	@Column(name = "approve_amount", precision = 22, scale = 0)
	public Double getApproveAmount() {
		return this.approveAmount;
	}

	public void setApproveAmount(Double approveAmount) {
		this.approveAmount = approveAmount;
	}

	@Column(name = "sender_user_name", length = 20)
	public String getSenderUserName() {
		return this.senderUserName;
	}

	public void setSenderUserName(String senderUserName) {
		this.senderUserName = senderUserName;
	}

	@Column(name = "receiver_user_name", length = 20)
	public String getReceiverUserName() {
		return this.receiverUserName;
	}

	public void setReceiverUserName(String receiverUserName) {
		this.receiverUserName = receiverUserName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "receiver_end_date", length = 10)
	public Date getReceiverEndDate() {
		return this.receiverEndDate;
	}

	public void setReceiverEndDate(Date receiverEndDate) {
		this.receiverEndDate = receiverEndDate;
	}

	@ManyToOne(fetch = FetchType.EAGER)
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

}