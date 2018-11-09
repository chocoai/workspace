package com.smart.model;

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

import com.smart.util.AppUtil;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user", catalog = "eccs")
public class User implements java.io.Serializable {

	public static final String SUPERADMIN = "admin";

	private static final long serialVersionUID = 1L;
	// Fields

	private Integer id;

	private String username;

	private String password;

	private String name;

	private Integer sex;

	private String tel;

	private User user;

	private Dept dept;

	private Date ctime;

	private Integer status;

	private String comid;

	private String lastRoleId; // 最后一次登录角色ID

	private List<Dept> deptList; // 此用户所在所有部门

	// Constructors

	/** default constructor */
	public User() {
		sex = 1;
		ctime = new Date();
		status = 1;
	}

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

	@Column(name = "username", length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "tel")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sex")
	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(name = "ctime", length = 19)
	public Date getCtime() {
		return this.ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "last_role_id")
	public String getLastRoleId() {
		return lastRoleId;
	}

	public void setLastRoleId(String lastRoleId) {
		this.lastRoleId = lastRoleId;
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

	@Transient
	public String getSexStr() {
		return sex == 1 ? "先生" : "女士";
	}

	@Transient
	public List<Dept> getDeptList() {
		return deptList;
	}

	public void setDeptList(List<Dept> deptList) {
		this.deptList = deptList;
	}

	@Column(name = "comid")
	public String getComid() {
		return comid;
	}

	public void setComid(String comid) {
		this.comid = comid;
	}

	@Transient
	public boolean isAdmin() {
		if (getUsername().equalsIgnoreCase(SUPERADMIN)
				|| getUsername().equalsIgnoreCase(AppUtil.SUPERADMIN)) {
			return true;
		}
		return false;
	}

}