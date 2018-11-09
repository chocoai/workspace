package com.smart.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * 人员信息
 * 
 * @author lenovo
 * 
 */
@Entity
@Table(name = "t_hr_employee", catalog = "eccs")
public class T_hremployee implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;// 姓名

	private Dept dept_id;// 部门

	private Integer sex;// 性别1,男 2,女

	private Date birth;// 生日

	private String phone;// 电话

	private String major;// 专业

	private String emg_contact;// 紧急联系人

	private String email;// 邮箱

	private String id_card;// 身份证

	private User sys_account;// 系统账号

	private Integer sort_no;// 排序号

	private Integer qq;// qq

	private String we_chat;// 微信

	private String msn;// msn

	private String other;// 其它

	private String remark;// 备注

	private User user_id;// 登记人

	private String rtime;// 登记日期

	private Date ctime;// 创建日期

	private Integer status;// 是否可用状态

	private Integer state;// 在职状态0,试用 1,在职 2,离职

	private T_hreducation t_hreducation;

	private List<T_hrcertificate> hrcelist;

	private List<T_hreducation> hredlist;

	public T_hremployee() {
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dept_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Dept getDept_id() {
		return dept_id;
	}

	public void setDept_id(Dept dept_id) {
		this.dept_id = dept_id;
	}

	@Column(name = "sex")
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(name = "t_birth")
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "major")
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(name = "emg_contact")
	public String getEmg_contact() {
		return emg_contact;
	}

	public void setEmg_contact(String emg_contact) {
		this.emg_contact = emg_contact;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "id_card")
	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	@Column(name = "sort_no")
	public Integer getSort_no() {
		return sort_no;
	}

	public void setSort_no(Integer sort_no) {
		this.sort_no = sort_no;
	}

	@Column(name = "qq")
	public Integer getQq() {
		return qq;
	}

	public void setQq(Integer qq) {
		this.qq = qq;
	}

	@Column(name = "we_chat")
	public String getWe_chat() {
		return we_chat;
	}

	public void setWe_chat(String we_chat) {
		this.we_chat = we_chat;
	}

	@Column(name = "msn")
	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	@Column(name = "other")
	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
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

	@Column(name = "rtime")
	public String getRtime() {
		return rtime;
	}

	public void setRtime(String rtime) {
		this.rtime = rtime;
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

	@Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Transient
	public T_hreducation getT_hreducation() {
		return t_hreducation;
	}

	public void setT_hreducation(T_hreducation t_hreducation) {
		this.t_hreducation = t_hreducation;
	}

	@Transient
	public List<T_hrcertificate> getHrcelist() {
		return hrcelist;
	}

	public void setHrcelist(List<T_hrcertificate> hrcelist) {
		this.hrcelist = hrcelist;
	}

	@Transient
	public List<T_hreducation> getHredlist() {
		return hredlist;
	}

	public void setHredlist(List<T_hreducation> hredlist) {
		this.hredlist = hredlist;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sys_account")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getSys_account() {
		return sys_account;
	}

	public void setSys_account(User sys_account) {
		this.sys_account = sys_account;
	}

}
