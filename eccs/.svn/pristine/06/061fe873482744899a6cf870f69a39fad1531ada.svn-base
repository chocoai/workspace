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
@Table(name = "step5_log", catalog = "eccs")
public class Step5Logo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private Integer id;

	private Step5 step5;

	private User user;// 勘察人

	private Dept dept;// 勘察部门

	private String logoNext;// 勘察内容

	private Date ctime;// 勘察时间

	private Integer status;// 勘察状态 0 ：正常

	public Step5Logo() {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	@Column(name = "logo_next")
	public String getLogoNext() {
		return logoNext;
	}

	public void setLogoNext(String logoNext) {
		this.logoNext = logoNext;
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