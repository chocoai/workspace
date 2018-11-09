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
 * Step3Worker entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "step3_worker", catalog = "eccs")
public class Step3Worker implements java.io.Serializable { // 此表更改为 项目经理指派任务人员

	// Fields

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Project project;

	private Integer type; // 流程步骤1~15

	private String workType; // 类型 0：经营管理 1：项目管理

	private String workUserId;// 操作人ID

	private String workUserName;// 操作人姓名

	private String workLevel;

	private String workLevelNo;

	private Dept dept;

	private User user;

	private Date ctime;

	private Integer status;

	// Constructors

	/** default constructor */
	public Step3Worker() {
		ctime = new Date();
		status = 1;
	}

	public Step3Worker(String worktype) {
		ctime = new Date();
		status = 1;
		workType = worktype;
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

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "work_type", length = 1)
	public String getWorkType() {
		return this.workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
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

	@Column(name = "work_user_id", length = 256)
	public String getWorkUserId() {
		return workUserId;
	}

	public void setWorkUserId(String workUserId) {
		this.workUserId = workUserId;
	}

	@Column(name = "work_user_name", length = 256)
	public String getWorkUserName() {
		return workUserName;
	}

	public void setWorkUserName(String workUserName) {
		this.workUserName = workUserName;
	}

	@Column(name = "work_level", length = 20)
	public String getWorkLevel() {
		return this.workLevel;
	}

	public void setWorkLevel(String workLevel) {
		this.workLevel = workLevel;
	}

	@Column(name = "work_level_no", length = 100)
	public String getWorkLevelNo() {
		return this.workLevelNo;
	}

	public void setWorkLevelNo(String workLevelNo) {
		this.workLevelNo = workLevelNo;
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