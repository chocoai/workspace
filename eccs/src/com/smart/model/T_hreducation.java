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
 * 学历信息
 * 
 * @author lenovo
 * 
 */
@Entity
@Table(name = "t_hr_education", catalog = "eccs")
public class T_hreducation implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String college;// 毕业院校

	private String education;// 学历

	private Date gdate;// 毕业时间

	private Date ctime;// 创建日期

	private Integer status;// 是否可用状态

	private T_hremployee t_hremployee;// 人员信息表

	public T_hreducation() {
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

	@Column(name = "t_college")
	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	@Column(name = "education")
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Column(name = "gdate")
	public Date getGdate() {
		return gdate;
	}

	public void setGdate(Date gdate) {
		this.gdate = gdate;
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
