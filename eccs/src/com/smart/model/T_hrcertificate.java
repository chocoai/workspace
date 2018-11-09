package com.smart.model;

import java.io.Serializable;
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
 * 证书
 * 
 * @author lenovo
 * 
 */
@Entity
@Table(name = "t_hr_certificate", catalog = "eccs")
public class T_hrcertificate implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;// 证书名称

	private String no;// 证书编号

	private String major;// 专业

	private String grade;// 等级

	private String issuing_unit;// 签发单位

	private Date idate;// 签发日期

	private String validity_period;// 有效期

	private String remark;// 备注

	private Date ctime;// 创建日期

	private Integer status;// 是否可用状态

	private T_hremployee t_hremployee;// 人员信息表

	public T_hrcertificate() {
		ctime = new Date();
		status = 1;
	}

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

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "no")
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	@Column(name = "major")
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(name = "grade")
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Column(name = "issuing_unit")
	public String getIssuing_unit() {
		return issuing_unit;
	}

	public void setIssuing_unit(String issuing_unit) {
		this.issuing_unit = issuing_unit;
	}

	@Column(name = "idate")
	public Date getIdate() {
		return idate;
	}

	public void setIdate(Date idate) {
		this.idate = idate;
	}

	@Column(name = "validity_period")
	public String getValidity_period() {
		return validity_period;
	}

	public void setValidity_period(String validity_period) {
		this.validity_period = validity_period;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public T_hremployee getT_hremployee() {
		return t_hremployee;
	}

	public void setT_hremployee(T_hremployee t_hremployee) {
		this.t_hremployee = t_hremployee;
	}

}
