/**
 * 
 */
package com.smart.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * @Description:项目进程处理历史记录表，记录项目各个环节的处理情况(project_process_history)
 * @author raopanfeng
 * @date 2017年1月19日 下午4:16:55
 */
@Entity
@Table(name = "project_process_history", catalog = "eccs")
public class ProjectProcessHistory implements Serializable {

	private static final long serialVersionUID = 7344980993943802007L;

	// 主键ID
	private Integer uuid;

	// 项目ID
	private Project project;
	
	//项目信息ID
	private ProjectInfo projectInfo;

	// 处理环节
	private ProceStepDef operateStep;

	// 处理人
	private User operateUser;

	// 处理时间
	private String operateTime;

	// 处理方式
	private String operateType;

	// 下一环节
	private ProceStepDef nextStep;

	// 下一环节处理人
	private String nextHandlers;

	private String nextHandlerName;

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "uuid", unique = true, nullable = false)
	public Integer getUuid() {
		return this.uuid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Project getProject() {
		return this.project;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proinfo_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "operate_step")
	@NotFound(action = NotFoundAction.IGNORE)
	public ProceStepDef getOperateStep() {
		return this.operateStep;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "operate_user")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getOperateUser() {
		return this.operateUser;
	}

	@Column(name = "operate_time", length = 32)
	public String getOperateTime() {
		return this.operateTime;
	}

	@Column(name = "operate_type", length = 16)
	public String getOperateType() {
		return this.operateType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "next_step")
	@NotFound(action = NotFoundAction.IGNORE)
	public ProceStepDef getNextStep() {
		return this.nextStep;
	}

	@Column(name = "next_handler", length = 32)
	public String getNextHandlers() {
		return this.nextHandlers;
	}

	@Transient
	public String getNextHandlerName() {
		return this.nextHandlerName;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setOperateStep(ProceStepDef operateStep) {
		this.operateStep = operateStep;
	}

	public void setOperateUser(User operateUser) {
		this.operateUser = operateUser;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public void setNextStep(ProceStepDef nextStep) {
		this.nextStep = nextStep;
	}

	public void setNextHandlers(String nextHandlers) {
		this.nextHandlers = nextHandlers;
	}

	public void setNextHandlerName(String nextHandlerName) {
		this.nextHandlerName = nextHandlerName;
	}
	
	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

}
