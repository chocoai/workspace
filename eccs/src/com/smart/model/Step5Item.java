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
 * Step5Item entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "step5_item", catalog = "eccs")
public class Step5Item implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private Integer id;

	private Step5 step5;

	private String company;// 单位

	private String companyRen;// 法人

	private Date ctime;// 创建时间

	private Integer status;

	public Step5Item() {
		this.status = 0;
		this.ctime = new Date();
	}

	public Step5Item(String company) {
		this.company = company;
		this.status = 0;
		this.ctime = new Date();
	}

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
	@JoinColumn(name = "step5_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Step5 getStep5() {
		return step5;
	}

	public void setStep5(Step5 step5) {
		this.step5 = step5;
	}

	@Column(name = "company", length = 64)
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "company_ren", length = 64)
	public String getCompanyRen() {
		return companyRen;
	}

	public void setCompanyRen(String companyRen) {
		this.companyRen = companyRen;
	}

	@Column(name = "ctime")
	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}