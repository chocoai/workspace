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
 * Step13 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "step13", catalog = "eccs")
public class Step13 implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Project project;

	private String projectName;

	private String visitObject;

	private String serviceContent;

	private String summaryContent;

	private String masterName;

	private Date masterSignDate;

	private String perfectContent;

	private String techName;

	private Date techSingDate;

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
	public Step13() {
		status = 1;
		ctime = new Date();
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

	@Column(name = "project_name", length = 50)
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "visit_object", length = 500)
	public String getVisitObject() {
		return this.visitObject;
	}

	public void setVisitObject(String visitObject) {
		this.visitObject = visitObject;
	}

	@Column(name = "service_content", length = 500)
	public String getServiceContent() {
		return this.serviceContent;
	}

	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
	}

	@Column(name = "summary_content", length = 500)
	public String getSummaryContent() {
		return this.summaryContent;
	}

	public void setSummaryContent(String summaryContent) {
		this.summaryContent = summaryContent;
	}

	@Column(name = "master_name", length = 20)
	public String getMasterName() {
		return this.masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "master_sign_date", length = 10)
	public Date getMasterSignDate() {
		return this.masterSignDate;
	}

	public void setMasterSignDate(Date masterSignDate) {
		this.masterSignDate = masterSignDate;
	}

	@Column(name = "perfect_content", length = 500)
	public String getPerfectContent() {
		return this.perfectContent;
	}

	public void setPerfectContent(String perfectContent) {
		this.perfectContent = perfectContent;
	}

	@Column(name = "tech_name", length = 20)
	public String getTechName() {
		return this.techName;
	}

	public void setTechName(String techName) {
		this.techName = techName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tech_sing_date", length = 10)
	public Date getTechSingDate() {
		return this.techSingDate;
	}

	public void setTechSingDate(Date techSingDate) {
		this.techSingDate = techSingDate;
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