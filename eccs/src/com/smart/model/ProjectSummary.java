package com.smart.model;

// default package
// Generated 2017-7-5 11:01:11 by Hibernate Tools 4.0.0.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * ProjectInfoSummary generated by hbm2java
 */
@Entity
@Table(name = "project_summary", catalog = "eccs")
public class ProjectSummary implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer cid;
	private ProjectInfo projectInfo;
	private String summary;
	private String perfectMeasures;
	private String archiveSituation;
	private String other;
	private String createTime;
	private Integer status;
	private String remark;
	
	//非数据库字段
	private boolean resStates; // 编辑操作权限

	public ProjectSummary() {
	}

	public ProjectSummary(Integer cid) {
		this.cid = cid;
	}

	public ProjectSummary(Integer cid, ProjectInfo projectInfo, String summary,
			String perfectMeasures, String archiveSituation, String other,
			String createTime, Integer status, String remark) {
		this.cid = cid;
		this.projectInfo = projectInfo;
		this.summary = summary;
		this.perfectMeasures = perfectMeasures;
		this.archiveSituation = archiveSituation;
		this.other = other;
		this.createTime = createTime;
		this.status = status;
		this.remark = remark;
	}

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "cid", unique = true, nullable = false)
	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectInfo_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public ProjectInfo getProjectInfo() {
		return this.projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	@Column(name = "summary", length = 128)
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(name = "perfect_measures", length = 128)
	public String getPerfectMeasures() {
		return this.perfectMeasures;
	}

	public void setPerfectMeasures(String perfectMeasures) {
		this.perfectMeasures = perfectMeasures;
	}

	@Column(name = "archive_situation", length = 128)
	public String getArchiveSituation() {
		return this.archiveSituation;
	}

	public void setArchiveSituation(String archiveSituation) {
		this.archiveSituation = archiveSituation;
	}

	@Column(name = "other", length = 128)
	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	@Column(name = "create_time", length = 128)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "remark", length = 128)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Transient
	public boolean getResStates() {
		return resStates;
	}

	public void setResStates(boolean resStates) {
		this.resStates = resStates;
	}

}