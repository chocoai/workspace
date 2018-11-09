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
 * ProjectContact entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "project_info", catalog = "eccs")
public class ProjectInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	private Integer id;
	
	private String proNo; // 项目编号

	private String proname;

	private T_Customer bidmen; // 招标人

	private String bidfile;

	private String bidway;

	private String zixungusuan;

	private double bidmargin;

	private String bidreturn;

	private String applytime;

	private Date opentime;

	private Date internaltime;

	private String collectionmen;

	private String bidapplymen;

	private String compilemen;

	private String auditmen;

	private String agentmen;

	private String bidresult;

	private String winnotice;

	private String transfer;

	private Integer status; // 0表示已立项，1表示未立项

	private Date qsj;

	private Date zsj;

	private String operationmen;

	private Date ctime;
	
	private Integer receiveType;
	
	private ServiceType serviceType; // 咨询类型
	
	private String agentcompany;
	
	private String signcity;
	
	private String buildmoney;
	
	private String projectProfile;
	
	private String nextOperatorId;

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

	@Column(name = "proName")
	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bidmen")
	@NotFound(action = NotFoundAction.IGNORE)
	public T_Customer getBidmen() {
		return bidmen;
	}

	public void setBidmen(T_Customer bidmen) {
		this.bidmen = bidmen;
	}

	@Column(name = "bidfile")
	public String getBidfile() {
		return bidfile;
	}

	public void setBidfile(String bidfile) {
		this.bidfile = bidfile;
	}

	@Column(name = "bidway")
	public String getBidway() {
		return bidway;
	}

	public void setBidway(String bidway) {
		this.bidway = bidway;
	}

	@Column(name = "zixungusuan")
	public String getZixungusuan() {
		return zixungusuan;
	}

	public void setZixungusuan(String zixungusuan) {
		this.zixungusuan = zixungusuan;
	}

	@Column(name = "bidmargin")
	public double getBidmargin() {
		return bidmargin;
	}

	public void setBidmargin(double bidmargin) {
		this.bidmargin = bidmargin;
	}

	@Column(name = "bidreturn")
	public String getBidreturn() {
		return bidreturn;
	}

	public void setBidreturn(String bidreturn) {
		this.bidreturn = bidreturn;
	}

	@Column(name = "applytime")
	public String getApplytime() {
		return applytime;
	}

	public void setApplytime(String applytime) {
		this.applytime = applytime;
	}

	@Column(name = "opentime")
	public Date getOpentime() {
		return opentime;
	}

	public void setOpentime(Date opentime) {
		this.opentime = opentime;
	}

	@Column(name = "internaltime")
	public Date getInternaltime() {
		return internaltime;
	}

	public void setInternaltime(Date internaltime) {
		this.internaltime = internaltime;
	}

	@Column(name = "collectionmen")
	public String getCollectionmen() {
		return collectionmen;
	}

	public void setCollectionmen(String collectionmen) {
		this.collectionmen = collectionmen;
	}

	@Column(name = "bidapplymen")
	public String getBidapplymen() {
		return bidapplymen;
	}

	public void setBidapplymen(String bidapplymen) {
		this.bidapplymen = bidapplymen;
	}

	@Column(name = "compilemen")
	public String getCompilemen() {
		return compilemen;
	}

	public void setCompilemen(String compilemen) {
		this.compilemen = compilemen;
	}

	@Column(name = "auditmen")
	public String getAuditmen() {
		return auditmen;
	}

	public void setAuditmen(String auditmen) {
		this.auditmen = auditmen;
	}

	@Column(name = "agentmen")
	public String getAgentmen() {
		return agentmen;
	}

	public void setAgentmen(String agentmen) {
		this.agentmen = agentmen;
	}

	@Column(name = "bidresult")
	public String getBidresult() {
		return bidresult;
	}

	public void setBidresult(String bidresult) {
		this.bidresult = bidresult;
	}

	@Column(name = "winnotice")
	public String getWinnotice() {
		return winnotice;
	}

	public void setWinnotice(String winnotice) {
		this.winnotice = winnotice;
	}

	@Column(name = "transfer")
	public String getTransfer() {
		return transfer;
	}

	public void setTransfer(String transfer) {
		this.transfer = transfer;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "qsj")
	public Date getQsj() {
		return qsj;
	}

	public void setQsj(Date qsj) {
		this.qsj = qsj;
	}

	@Column(name = "zsj")
	public Date getZsj() {
		return zsj;
	}

	public void setZsj(Date zsj) {
		this.zsj = zsj;
	}

	@Column(name = "operationmen")
	public String getOperationmen() {
		return operationmen;
	}

	public void setOperationmen(String operationmen) {
		this.operationmen = operationmen;
	}

	@Column(name = "ctime")
	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	@Column(name = "receive_type")
	public Integer getReceiveType() {
		return receiveType;
	}

	public void setReceiveType(Integer receiveType) {
		this.receiveType = receiveType;
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

	@Column(name = "agentcompany")
	public String getAgentcompany() {
		return agentcompany;
	}

	public void setAgentcompany(String agentcompany) {
		this.agentcompany = agentcompany;
	}

	@Column(name = "signcity")
	public String getSigncity() {
		return signcity;
	}

	public void setSigncity(String signcity) {
		this.signcity = signcity;
	}

	@Column(name = "buildmoney")
	public String getBuildmoney() {
		return buildmoney;
	}

	public void setBuildmoney(String buildmoney) {
		this.buildmoney = buildmoney;
	}

	@Column(name = "project_profile")
	public String getProjectProfile() {
		return projectProfile;
	}

	public void setProjectProfile(String projectProfile) {
		this.projectProfile = projectProfile;
	}
	
	@Column(name = "next_operator_id")
	public String getNextOperatorId() {
		return nextOperatorId;
	}

	public void setNextOperatorId(String nextOperatorId) {
		this.nextOperatorId = nextOperatorId;
	}
	
	@Column(name = "proNo")
	public String getProNo() {
		return proNo;
	}

	public void setProNo(String proNo) {
		this.proNo = proNo;
	}
	
}