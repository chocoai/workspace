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
 * Step10 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "step10", catalog = "eccs")
public class Step10 implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Project project;

	private String projectName;

	private String fileName;

	private String receiverDeptName;

	private Integer fileCount;

	private String fileNo;

	private String description;

	// private String senderView;
	// private String senderUserName;
	// private String senderType; //签收方式
	// private Date senderDate;

	private String fkdw; // 反馈单位

	private Date fkrq; // 反馈日期

	private String zxdw; // 咨询单位

	private Date zxrq; // 咨询日期

	private String receiverView;

	private String receiverUserName; // 签收人

	private String receiverType; // 签收方式

	private Date receiverDate; // 签收日期

	private Dept dept;

	private User user;

	private Date ctime;

	private Integer status;

	private Integer validateStatus;// 0：编制 1：审批

	private String validateUser;// 审批人

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
	public Step10() {
		this.ctime = new Date();
		this.status = 1;
		this.validateStatus = 0;
	}

	/** full constructor */

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

	@Column(name = "file_name", length = 50)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "receiver_dept_name", length = 50)
	public String getReceiverDeptName() {
		return this.receiverDeptName;
	}

	public void setReceiverDeptName(String receiverDeptName) {
		this.receiverDeptName = receiverDeptName;
	}

	@Column(name = "file_count")
	public Integer getFileCount() {
		return this.fileCount;
	}

	public void setFileCount(Integer fileCount) {
		this.fileCount = fileCount;
	}

	@Column(name = "file_no", length = 50)
	public String getFileNo() {
		return this.fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	@Column(name = "description", length = 1000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "receiver_user_name", length = 50)
	public String getReceiverUserName() {
		return this.receiverUserName;
	}

	public void setReceiverUserName(String receiverUserName) {
		this.receiverUserName = receiverUserName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "receiver_date", length = 10)
	public Date getReceiverDate() {
		return this.receiverDate;
	}

	public void setReceiverDate(Date receiverDate) {
		this.receiverDate = receiverDate;
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

	@Column(name = "receiver_view")
	public String getReceiverView() {
		return receiverView;
	}

	public void setReceiverView(String receiverView) {
		this.receiverView = receiverView;
	}

	@Column(name = "receiver_type")
	public String getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	@Column(name = "fkdw")
	public String getFkdw() {
		return fkdw;
	}

	public void setFkdw(String fkdw) {
		this.fkdw = fkdw;
	}

	@Column(name = "fkrq")
	public Date getFkrq() {
		return fkrq;
	}

	public void setFkrq(Date fkrq) {
		this.fkrq = fkrq;
	}

	@Column(name = "zxdw")
	public String getZxdw() {
		return zxdw;
	}

	public void setZxdw(String zxdw) {
		this.zxdw = zxdw;
	}

	@Column(name = "zxrq")
	public Date getZxrq() {
		return zxrq;
	}

	public void setZxrq(Date zxrq) {
		this.zxrq = zxrq;
	}

	@Column(name = "validate_status")
	public Integer getValidateStatus() {
		return validateStatus;
	}

	public void setValidateStatus(Integer validateStatus) {
		this.validateStatus = validateStatus;
	}

	@Column(name = "validate_user")
	public String getValidateUser() {
		return validateUser;
	}

	public void setValidateUser(String validateUser) {
		this.validateUser = validateUser;
	}
	
}