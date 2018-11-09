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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Step11 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "step15", catalog = "eccs")
public class Step15 implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	private Integer id;

	private Project project;

	private Dept dept;

	private User user;

	private Date ctime;

	private Integer status;

	private Date confirmTime; // 任务提交时间 --新增
	
	private Integer receiveManagerId; //审核处理人
	
	private Integer validateStatus; //提交审核  1：已提交  0:未提交
	
	private Integer confirmId; //确认归档 1：已归档 0：未归档
	
	private Integer dealUserId; //保存初始归档人

	@Column(name = "confirm_time")
	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	// Constructors

	/** default constructor */
	public Step15() {
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

	@Column(name="receive_manager_id")
	public Integer getReceiveManagerId() {
		return receiveManagerId;
	}

	public void setReceiveManagerId(Integer receiveManagerId) {
		this.receiveManagerId = receiveManagerId;
	}

	@Column(name="validate_status")
	public Integer getValidateStatus() {
		return validateStatus;
	}

	public void setValidateStatus(Integer validateStatus) {
		this.validateStatus = validateStatus;
	}

	@Column(name="confirm_id")
	public Integer getConfirmId() {
		return confirmId;
	}

	public void setConfirmId(Integer confirmId) {
		this.confirmId = confirmId;
	}

	@Column(name="deal_user_id")
	public Integer getDealUserId() {
		return dealUserId;
	}

	/**
	 * @param dealUserId the dealUserId to set
	 */
	public void setDealUserId(Integer dealUserId) {
		this.dealUserId = dealUserId;
	}

}