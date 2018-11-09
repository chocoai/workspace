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
 * BidSpeed entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bid_speed", catalog = "eccs")
public class BidSpeed implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;

	private Integer id;

	private BidPlan bidPlan;

	private String workContent;

	private Date completeDate;

	private String masterName;

	private User user;

	private Dept dept;

	private Date ctime;

	private Integer status;

	// Constructors

	/** default constructor */
	public BidSpeed() {
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
	@JoinColumn(name = "bid_plan_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public BidPlan getBidPlan() {
		return bidPlan;
	}

	public void setBidPlan(BidPlan bidPlan) {
		this.bidPlan = bidPlan;
	}

	@Column(name = "work_content")
	public String getWorkContent() {
		return this.workContent;
	}

	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "complete_date", length = 10)
	public Date getCompleteDate() {
		return this.completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	@Column(name = "master_name", length = 20)
	public String getMasterName() {
		return this.masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
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

}