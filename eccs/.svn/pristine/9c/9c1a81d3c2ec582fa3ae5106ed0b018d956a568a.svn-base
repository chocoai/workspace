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
 * 客户信息
 * 
 * @author lenovo
 * 
 */
@Entity
@Table(name = "t_cu_customer", catalog = "eccs")
public class T_Customer implements Serializable {

	private static final long serialVersionUID = 9046991159586891176L;

	private Integer id;

	private Integer cusNature; // 客户性质 1.委托单位 2.建设单位 3施工单位. 4.设计单位

	private Integer cusType; // 客户类别 1.企业客户 2.政府客户 3.其它

	private String cusName;

	private String ogCode; // 组织机构代码

	private Integer cusLevel; // 客户信用级别 1.A 2.B 3.C 4.D

	private String lega; // 法人代表

	private String cusHomepage;

	private String postCode;

	private String fax;

	private String bankAccount; // 开户银行

	private String accountOpening; // 开户账号

	private String email;

	private String officePhone;

	private String address;

	private String remark;

	private User user;

	private Date rtime;

	private Date ctime;

	private Integer status;

	private T_Contact contacts;

	private List<T_Contact> contactlist;

	public T_Customer() {
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

	@Column(name = "cus_nature")
	public Integer getCusNature() {
		return cusNature;
	}

	public void setCusNature(Integer cusNature) {
		this.cusNature = cusNature;
	}

	@Column(name = "cus_type")
	public Integer getCusType() {
		return cusType;
	}

	public void setCusType(Integer cusType) {
		this.cusType = cusType;
	}

	@Column(name = "cus_name", length = 100)
	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	@Column(name = "og_code", length = 100)
	public String getOgCode() {
		return ogCode;
	}

	public void setOgCode(String ogCode) {
		this.ogCode = ogCode;
	}

	@Column(name = "cus_level")
	public Integer getCusLevel() {
		return cusLevel;
	}

	public void setCusLevel(Integer cusLevel) {
		this.cusLevel = cusLevel;
	}

	@Column(name = "lega", length = 50)
	public String getLega() {
		return lega;
	}

	public void setLega(String lega) {
		this.lega = lega;
	}

	@Column(name = "cus_homepage", length = 200)
	public String getCusHomepage() {
		return cusHomepage;
	}

	public void setCusHomepage(String cusHomepage) {
		this.cusHomepage = cusHomepage;
	}

	@Column(name = "postcode", length = 30)
	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@Column(name = "fax", length = 30)
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "bankaccount", length = 100)
	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Column(name = "accountopening", length = 100)
	public String getAccountOpening() {
		return accountOpening;
	}

	public void setAccountOpening(String accountOpening) {
		this.accountOpening = accountOpening;
	}

	@Column(name = "email", length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "officephone", length = 30)
	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "remark", length = 500)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	// 1对多
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "rtime", length = 19)
	public Date getRtime() {
		return rtime;
	}

	public void setRtime(Date rtime) {
		this.rtime = rtime;
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
	@JoinColumn(name = "t_contacts_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public T_Contact getContacts() {
		return contacts;
	}

	public void setContacts(T_Contact contacts) {
		this.contacts = contacts;
	}

	@Transient
	public List<T_Contact> getContactlist() {
		return contactlist;
	}

	public void setContactlist(List<T_Contact> contactlist) {
		this.contactlist = contactlist;
	}

}
