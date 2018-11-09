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
 * Notice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "backlog", catalog = "eccs")
public class Backlog implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Project project;

	private String name;

	private String type;

	private String remark;

	private String path;

	private User toUser;

	private Dept toDept;

	private Date ctime;

	private Integer status;

	private Integer projectStep;// -5项目立项 -4投标策划 -3投标总结 -2 合同评审 -1合同登记
	// 0项目管理处的人员分配 1-15对应项目的步骤
	// -10 合同评审提交任务 -11投标人员分配 -12投标策划提交评审
	// -13第12步的第一次提交 -14第十步的提交审定
	// Constructors

	/** default constructor */
	public Backlog() {
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

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "to_user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getToUser() {
		return toUser;
	}

	public void setToUser(User toUser) {
		this.toUser = toUser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "to_dept_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Dept getToDept() {
		return toDept;
	}

	public void setToDept(Dept toDept) {
		this.toDept = toDept;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "path")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "project_step")
	public Integer getProjectStep() {
		return projectStep;
	}

	public void setProjectStep(Integer projectStep) {
		this.projectStep = projectStep;
	}

}