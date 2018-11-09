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

/**
 * 发票信息
 * 
 * @author lenovo
 *
 */

@Entity
@Table(name = "t_fi_invoice", catalog = "eccs")
public class T_Invoice implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Project project; // 项目

	private String payCompany; // 付款单位

	private String invoiceNo; // 发票编号

	private Integer invoiceType; // 发票类型 1,普通发票 2,增值税专用发票 3,定额发票s

	private Double invoiceAmount; // 开票金额

	private Date invoiceDate; // 开票日期

	private String invoiceContent; // 发票内容

	private String remark; // 备注

	private User invoiceUser; // 发票领用人

	private Date userDate; // 领用日期

	private User user; // 登记人

	private String rdate; // 登记时间

	private Date ctime;

	private int status;

	private Integer reStatus; // 收款状态 1,未回款 2,已回款 3,部分回款

	private double rAccount; // 实际应收总额

	private double cAccount; // 累计已开票

	private double aAccount; // 已到账总额

	private double wAccount; // 未开票总额

	private Integer fistatu;// 发票状态1,正常 2,作废

	private User canceluser_id;// 作废人

	private Date canceltime;// 作废时间

	private List<T_Receivables> receivableslist;// 收款

	/** default constructor */
	public T_Invoice() {
		ctime = new Date();
		status = 1;
	}

	// Property accessors
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
	@JoinColumn(name = "project_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(name = "pay_company", length = 100)
	public String getPayCompany() {
		return payCompany;
	}

	public void setPayCompany(String payCompany) {
		this.payCompany = payCompany;
	}

	@Column(name = "invoice_no", length = 50)
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	@Column(name = "invoice_type")
	public Integer getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	@Column(name = "invoice_amount")
	public Double getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(Double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	@Column(name = "invoice_date", length = 10)
	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	@Column(name = "invoice_content", length = 500)
	public String getInvoiceContent() {
		return invoiceContent;
	}

	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	@Column(name = "remark", length = 500)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invoice_user")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getInvoiceUser() {
		return invoiceUser;
	}

	public void setInvoiceUser(User invoiceUser) {
		this.invoiceUser = invoiceUser;
	}

	@Column(name = "use_date", length = 10)
	public Date getUserDate() {
		return userDate;
	}

	public void setUserDate(Date userDate) {
		this.userDate = userDate;
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

	@Column(name = "rdate", length = 10)
	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	@Column(name = "ctime")
	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	@Column(name = "status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "re_status")
	public Integer getReStatus() {
		return reStatus;
	}

	public void setReStatus(Integer reStatus) {
		this.reStatus = reStatus;
	}

	@Transient
	public double getrAccount() {
		return rAccount;
	}

	public void setrAccount(double rAccount) {
		this.rAccount = rAccount;
	}

	@Transient
	public double getcAccount() {
		return cAccount;
	}

	public void setcAccount(double cAccount) {
		this.cAccount = cAccount;
	}

	@Transient
	public double getaAccount() {
		return aAccount;
	}

	public void setaAccount(double aAccount) {
		this.aAccount = aAccount;
	}

	@Transient
	public double getwAccount() {
		return wAccount;
	}

	public void setwAccount(double wAccount) {
		this.wAccount = wAccount;
	}

	@Column(name = "fistatu")
	public Integer getFistatu() {
		return fistatu;
	}

	public void setFistatu(Integer fistatu) {
		this.fistatu = fistatu;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "canceluser_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getCanceluser_id() {
		return canceluser_id;
	}

	public void setCanceluser_id(User canceluser_id) {
		this.canceluser_id = canceluser_id;
	}

	@Column(name = "canceltime")
	public Date getCanceltime() {
		return canceltime;
	}

	public void setCanceltime(Date canceltime) {
		this.canceltime = canceltime;
	}

	@Transient
	public List<T_Receivables> getReceivableslist() {
		return receivableslist;
	}

	public void setReceivableslist(List<T_Receivables> receivableslist) {
		this.receivableslist = receivableslist;
	}

}
