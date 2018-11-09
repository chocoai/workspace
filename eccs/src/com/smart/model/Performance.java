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
 * Account entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "performance", catalog = "eccs")
public class Performance implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	private Integer id;

	private String wenhao;

	private Project project;

	private T_Customer customer;

	private Date ctime;

	private String signmen;

	private String job;

	private String contractno;

	private Double charge;

	private String daozhang;

	private String prove;

	private Double bbonus;

	private Double coefficient;

	private Double abonus;

	private String remark;

	private String compilemen;

	private String auditmen;

	private String approval;

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public T_Customer getCustomer() {
		return customer;
	}

	public void setCustomer(T_Customer customer) {
		this.customer = customer;
	}

	@Column(name = "ctime")
	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	@Column(name = "signmen")
	public String getSignmen() {
		return signmen;
	}

	public void setSignmen(String signmen) {
		this.signmen = signmen;
	}

	@Column(name = "job")
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	@Column(name = "contractno")
	public String getContractno() {
		return contractno;
	}

	public void setContractno(String contractno) {
		this.contractno = contractno;
	}

	@Column(name = "charge")
	public Double getCharge() {
		return charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	@Column(name = "daozhang")
	public String getDaozhang() {
		return daozhang;
	}

	public void setDaozhang(String daozhang) {
		this.daozhang = daozhang;
	}

	@Column(name = "prove")
	public String getProve() {
		return prove;
	}

	public void setProve(String prove) {
		this.prove = prove;
	}

	@Column(name = "bbonus")
	public Double getBbonus() {
		return bbonus;
	}

	public void setBbonus(Double bbonus) {
		this.bbonus = bbonus;
	}

	@Column(name = "coefficient")
	public Double getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(Double coefficient) {
		this.coefficient = coefficient;
	}

	@Column(name = "abonus")
	public Double getAbonus() {
		return abonus;
	}

	public void setAbonus(Double abonus) {
		this.abonus = abonus;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	@Column(name = "approval")
	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	@Column(name = "wenhao")
	public String getWenhao() {
		return wenhao;
	}

	public void setWenhao(String wenhao) {
		this.wenhao = wenhao;
	}

}