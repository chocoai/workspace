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
 * Step3 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "step3", catalog = "eccs")
public class Step3 implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Project project;

	private String projectName;

	private String projectInfo;

	private String consultTargetInfo;

	private String consultDivisionInfo;// 人员分配表

	private String workHandleInfo;

	private String masterSign;

	private String techMasterName;

	private String lawMasterName;

	private String basisCompilation;// 编制依据

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
	public Step3() {
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

	@Column(name = "project_name", length = 100)
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "project_info", length = 1000)
	public String getProjectInfo() {
		return this.projectInfo;
	}

	public void setProjectInfo(String projectInfo) {
		this.projectInfo = projectInfo;
	}

	@Column(name = "consult_target_info", length = 1000)
	public String getConsultTargetInfo() {
		return this.consultTargetInfo;
	}

	public void setConsultTargetInfo(String consultTargetInfo) {
		this.consultTargetInfo = consultTargetInfo;
	}

	@Column(name = "consult_division_info", length = 1000)
	public String getConsultDivisionInfo() {
		return this.consultDivisionInfo;
	}

	public void setConsultDivisionInfo(String consultDivisionInfo) {
		this.consultDivisionInfo = consultDivisionInfo;
	}

	@Column(name = "work_handle_info", length = 1000)
	public String getWorkHandleInfo() {
		return this.workHandleInfo;
	}

	public void setWorkHandleInfo(String workHandleInfo) {
		this.workHandleInfo = workHandleInfo;
	}

	@Column(name = "master_sign", length = 20)
	public String getMasterSign() {
		return this.masterSign;
	}

	public void setMasterSign(String masterSign) {
		this.masterSign = masterSign;
	}

	@Column(name = "tech_master_name", length = 100)
	public String getTechMasterName() {
		return this.techMasterName;
	}

	public void setTechMasterName(String techMasterName) {
		this.techMasterName = techMasterName;
	}

	@Column(name = "law_master_name", length = 100)
	public String getLawMasterName() {
		return this.lawMasterName;
	}

	public void setLawMasterName(String lawMasterName) {
		this.lawMasterName = lawMasterName;
	}

	@Column(name = "basis_compilation", length = 100)
	public String getBasisCompilation() {
		return basisCompilation;
	}

	public void setBasisCompilation(String basisCompilation) {
		this.basisCompilation = basisCompilation;
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