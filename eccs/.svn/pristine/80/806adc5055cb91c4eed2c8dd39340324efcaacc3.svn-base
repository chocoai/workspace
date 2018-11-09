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
 * 招聘计划
 * 
 * @author lenovo
 * 
 */
@Entity
@Table(name = "t_hr_recruitment", catalog = "eccs")
public class T_hrrecruitment implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Dept dept_id;// 登记部门//需求

	private String rdate;// 登记时间

	private String demand;// 岗位需求

	private String remark;// 备注

	private User user_id;// 登记人

	private Dept hdept_id;// 处理部门

	private Date ctime;

	private Integer status;

	private T_hropinion t_hropinion;

	public T_hrrecruitment() {
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dept_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Dept getDept_id() {
		return dept_id;
	}

	public void setDept_id(Dept dept_id) {
		this.dept_id = dept_id;
	}

	@Column(name = "rdate")
	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	@Column(name = "demand")
	public String getDemand() {
		return demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hdept_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Dept getHdept_id() {
		return hdept_id;
	}

	public void setHdept_id(Dept hdept_id) {
		this.hdept_id = hdept_id;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_hropinion")
	@NotFound(action = NotFoundAction.IGNORE)
	public T_hropinion getT_hropinion() {
		return t_hropinion;
	}

	public void setT_hropinion(T_hropinion t_hropinion) {
		this.t_hropinion = t_hropinion;
	}

}
