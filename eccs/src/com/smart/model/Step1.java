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
 * Step1 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "step1", catalog = "eccs")
public class Step1 implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Project project;

	private String projectName;

	private ProjectType projectType;

	private ServiceType serviceType;

	private EditorialType editorialType;

	private String senderUnit;

	private String senderAddress;

	private String consultRange;

	private String qualityRequirement;

	private String startWorkTime;

	private String endWorkTime;

	private String otherRequirements;

	private User senderUser;

	private Dept receiverDept;

	private Dept dept;

	private User user;

	private Date ctime;

	private Integer status;

	private Date confirmTime; // 任务提交时间 --新增
	
	private String stepType; // 任务提交时间 --新增

	@Column(name = "confirm_time")
	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	// Constructors

	/** default constructor */
	public Step1() {
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
	@JoinColumn(name = "project_type_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_type_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "editorial_type_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public EditorialType getEditorialType() {
		return editorialType;
	}

	public void setEditorialType(EditorialType editorialType) {
		this.editorialType = editorialType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getSenderUser() {
		return senderUser;
	}

	public void setSenderUser(User senderUser) {
		this.senderUser = senderUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiver_dept_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Dept getReceiverDept() {
		return receiverDept;
	}

	public void setReceiverDept(Dept receiverDept) {
		this.receiverDept = receiverDept;
	}

	@Column(name = "project_name", length = 100)
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "sender_unit", length = 50)
	public String getSenderUnit() {
		return this.senderUnit;
	}

	public void setSenderUnit(String senderUnit) {
		this.senderUnit = senderUnit;
	}

	@Column(name = "sender_address", length = 200)
	public String getSenderAddress() {
		return this.senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	@Column(name = "consult_range", length = 1000)
	public String getConsultRange() {
		return this.consultRange;
	}

	public void setConsultRange(String consultRange) {
		this.consultRange = consultRange;
	}

	@Column(name = "quality_requirement", length = 1000)
	public String getQualityRequirement() {
		return this.qualityRequirement;
	}

	public void setQualityRequirement(String qualityRequirement) {
		this.qualityRequirement = qualityRequirement;
	}

	@Column(name = "start_work_time", length = 64)
	public String getStartWorkTime() {
		return startWorkTime;
	}

	public void setStartWorkTime(String startWorkTime) {
		this.startWorkTime = startWorkTime;
	}

	@Column(name = "end_work_time", length = 64)
	public String getEndWorkTime() {
		return endWorkTime;
	}

	public void setEndWorkTime(String endWorkTime) {
		this.endWorkTime = endWorkTime;
	}

	@Column(name = "other_requirements", length = 1000)
	public String getOtherRequirements() {
		return this.otherRequirements;
	}

	public void setOtherRequirements(String otherRequirements) {
		this.otherRequirements = otherRequirements;
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
	
	@Column(name = "step_type")
	public String getStepType() {
		return stepType;
	}

	public void setStepType(String stepType) {
		this.stepType = stepType;
	}
	
}