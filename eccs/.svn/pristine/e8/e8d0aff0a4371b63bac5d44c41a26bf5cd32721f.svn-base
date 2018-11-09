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
@Table(name = "step4", catalog = "eccs")
public class Step4 implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Project project;

	private String projectName;

	private ConsultType consultType;

	private String senderUnit;

	private String receiverUnit;

	private Date senderDataDate;

	private Dept dept;

	private User user;

	private Date ctime;

	private Integer status;

	private Date confirmTime; // 任务提交时间 --新增

	private String standard; // 规范、软件

	private String other; // 其他相关资料

	@Column(name = "confirm_time")
	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	// Constructors

	/** default constructor */
	public Step4() {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "consult_type_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public ConsultType getConsultType() {
		return consultType;
	}

	public void setConsultType(ConsultType consultType) {
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

	@Column(name = "standard")
	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	@Column(name = "other")
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}