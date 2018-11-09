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
 * 联系人
 * 
 * @author lenovo
 *
 */
@Entity
@Table(name = "t_cu_contacts", catalog = "eccs")
public class T_Contact implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String contact; // 联系人

	private String deptName;

	private String post;

	private String phone;

	private String telephone;

	private String email;

	private String qq;

	private Integer defContact; // 默认联系人 1,默认联系人 0，不是

	private String remark;

	private Date ctime;

	private Integer status;

	private T_Customer t_Customer;

	public T_Contact() {
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

	@Column(name = "contact", length = 50)
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name = "dept", length = 50)
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "post", length = 50)
	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	@Column(name = "phone", length = 50)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "telephone", length = 50)
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "qq", length = 20)
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "con_default")
	public Integer getDefContact() {
		return defContact;
	}

	public void setDefContact(Integer defContact) {
		this.defContact = defContact;
	}

	@Column(name = "remark", length = 500)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_customer_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public T_Customer getT_Customer() {
		return t_Customer;
	}

	public void setT_Customer(T_Customer t_Customer) {
		this.t_Customer = t_Customer;
	}

}
