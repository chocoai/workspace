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

import com.smart.util.Constants;

/**
 * @Description:项目进程状态表，记录当前项目所处的环节状态及当前处理人(project_process_state)
 * @author raopanfeng
 * @date 2017年1月19日 下午4:08:29
 */
@Entity
@Table(name = "project_process_state", catalog = "eccs")
public class ProjectProcessState implements Serializable {
	/**
	 * 正在进行
	 */
	public static final String IN_PROGRESS = "0";

	/**
	 * 已完成
	 */
	public static final String FINISHED = "1";

	/**
	 * 已归档
	 */
	public static final String ARCHIVED = "2";

	/**
	 * 已终止
	 */
	public static final String STOPED = "3";

	/**
	 * 标示该条状态记录的是经营管理的状态
	 */
	public static final String TYPE_OM = "0";

	/**
	 * 标示该条状态记录的是项目管理的状态
	 */
	public static final String TYPE_PM = "1";

	private static final long serialVersionUID = 8748821010499840588L;

	// 主键ID
	private Integer uuid;
	
	// 项目ID
	private Project project;
	
	//项目信息ID
	private ProjectInfo projectInfo;

	// 当前所处环节
	private ProceStepDef currentStep;

	// 当前状态
	private String currentState;

	private String currentStateView;

	// 当前处理人ID
	private String currentUsers;

	private String currentUserName;

	// 最后更新时间
	private String lastUpdateTime;

	// 标示该条状态记录的是项目管理或者经营管理的状态
	private String type;

	public ProjectProcessState() {
		this.currentState = IN_PROGRESS;
	}

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
	@JoinColumn(name = "current_step")
	@NotFound(action = NotFoundAction.IGNORE)
	public ProceStepDef getCurrentStep() {
		return this.currentStep;
	}

	@Column(name = "current_state", length = 16)
	public String getCurrentState() {
		return this.currentState;
	}

	@Transient
	public String getCurrentStateView() {
		if (getCurrentState().equals(FINISHED)) {
			this.currentStateView = Constants.ProcessState.FINISHED;
		} else if (getCurrentState().equals(ARCHIVED)) {
			this.currentStateView = Constants.ProcessState.ARCHIVED;
		} else if (getCurrentState().equals(STOPED)) {
			this.currentStateView = Constants.ProcessState.STOPED;
		} else {
			this.currentStateView = Constants.ProcessState.IN_PROGRESS;
		}
		return this.currentStateView;
	}

	@Column(name = "current_user_id", length = 64)
	public String getCurrentUsers() {
		return this.currentUsers;
	}

	@Transient
	public String getCurrentUserName() {
		return this.currentUserName;
	}

	@Column(name = "last_update_time", length = 32)
	public String getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	@Column(name = "type", length = 1)
	public String getType() {
		return this.type;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setCurrentStep(ProceStepDef currentStep) {
		this.currentStep = currentStep;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public void setCurrentUsers(String currentUsers) {
		this.currentUsers = currentUsers;
	}

	public void setCurrentUserName(String currentUserName) {
		this.currentUserName = currentUserName;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}
}
