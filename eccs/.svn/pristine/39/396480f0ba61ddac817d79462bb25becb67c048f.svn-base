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
 * 印章借用
 * 
 * @author lenovo
 * 
 */
@Entity
@Table(name = "t_seal_borrow", catalog = "eccs")
public class T_sealBorrow implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Dept dept_id;// 登记部门

	private Dept sealdept_id;// 印章主管部门

	private User user_id;// 登记人

	private Project project_id;// 项目

	private Integer type;// 印章类型1,公章 2,合同章 3,项目章 4,其他

	private Integer count;// 盖章份数

	private String detail;// 用章内容

	private String return_date;// 归还日期

	private Date gh_date;// 实际归还日期

	private User gh_userid;// 实际归还人

	private String rtime;// 登记日期

	private Date ctime;// 创建日期

	private Integer status;// 是否可用状态

	private Integer state;// 归还状态0,未处理 1,已归还 2,未归还

	private T_sealOption option_id;// 印章处理表

	public T_sealBorrow() {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Dept getDept_id() {
		return dept_id;
	}

	public void setDept_id(Dept dept_id) {
		this.dept_id = dept_id;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Project getProject_id() {
		return project_id;
	}

	public void setProject_id(Project project_id) {
		this.project_id = project_id;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "count")
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Column(name = "detail")
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Column(name = "return_date")
	public String getReturn_date() {
		return return_date;
	}

	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}

	@Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "ctime")
	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	@Column(name = "rtime")
	public String getRtime() {
		return rtime;
	}

	public void setRtime(String rtime) {
		this.rtime = rtime;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "option_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public T_sealOption getOption_id() {
		return option_id;
	}

	public void setOption_id(T_sealOption option_id) {
		this.option_id = option_id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sealdept_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Dept getSealdept_id() {
		return sealdept_id;
	}

	public void setSealdept_id(Dept sealdept_id) {
		this.sealdept_id = sealdept_id;
	}

	@Column(name = "gh_date")
	public Date getGh_date() {
		return gh_date;
	}

	public void setGh_date(Date gh_date) {
		this.gh_date = gh_date;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "gh_userid")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getGh_userid() {
		return gh_userid;
	}

	public void setGh_userid(User gh_userid) {
		this.gh_userid = gh_userid;
	}

}
