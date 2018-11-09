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
 * 招聘岗位清单
 * 
 * @author lenovo
 * 
 */
@Entity
@Table(name = "t_hr_recruitment_item", catalog = "eccs")
public class T_hrrecruitmentitem implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String major;// 招聘专业

	private String post;// 岗位

	private Integer no;// 招聘人数

	private String remark;// 备注

	private T_hrrecruitment t_hrrecruitment;

	private Date ctime;

	private Integer status;

	public T_hrrecruitmentitem() {
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

	@Column(name = "major")
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(name = "post")
	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	@Column(name = "no")
	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "t_recruitment_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public T_hrrecruitment getT_hrrecruitment() {
		return t_hrrecruitment;
	}

	public void setT_hrrecruitment(T_hrrecruitment t_hrrecruitment) {
		this.t_hrrecruitment = t_hrrecruitment;
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
