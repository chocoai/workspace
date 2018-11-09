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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Requisition entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "requisition", catalog = "eccs")
public class Requisition implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Project project;

	private String serviceIds;// 咨询类型

	private String contacton;// 合同编号

	private String serviceContentIds;// 服务内容

	private String requisitionTypeIds;// 清款类别

	private Double amount;// 申请金额

	private String bankInfo;// 银行信息

	private String invoiceInfo;// 发票

	private String recordUserName;

	private Date requisitionDate;// 申请时间

	private String requisitionDeptName;// 申请部门

	private User user;

	private Dept dept;

	private Date ctime;

	private Integer status;

	private String reportNo;

	private String payCustomerName;// 清款单位

	private String payType;// 付费方式
	
	private String nextOperatorId;// 付费方式

	// Constructors

	/** default constructor */
	public Requisition() {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(name = "service_ids", length = 50)
	public String getServiceIds() {
		return this.serviceIds;
	}

	public void setServiceIds(String serviceIds) {
		this.serviceIds = serviceIds;
	}

	@Column(name = "contact_on", length = 50)
	public String getContacton() {
		return contacton;
	}

	public void setContacton(String contacton) {
		this.contacton = contacton;
	}

	@Column(name = "service_content_ids", length = 100)
	public String getServiceContentIds() {
		return this.serviceContentIds;
	}

	public void setServiceContentIds(String serviceContentIds) {
		this.serviceContentIds = serviceContentIds;
	}

	@Column(name = "requisition_type_ids", length = 50)
	public String getRequisitionTypeIds() {
		return this.requisitionTypeIds;
	}

	public void setRequisitionTypeIds(String requisitionTypeIds) {
		this.requisitionTypeIds = requisitionTypeIds;
	}

	@Column(name = "amount", precision = 22, scale = 0)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "bank_info", length = 200)
	public String getBankInfo() {
		return this.bankInfo;
	}

	public void setBankInfo(String bankInfo) {
		this.bankInfo = bankInfo;
	}

	@Column(name = "invoice_info", length = 200)
	public String getInvoiceInfo() {
		return this.invoiceInfo;
	}

	public void setInvoiceInfo(String invoiceInfo) {
		this.invoiceInfo = invoiceInfo;
	}

	@Column(name = "record_user_name", length = 20)
	public String getRecordUserName() {
		return this.recordUserName;
	}

	public void setRecordUserName(String recordUserName) {
		this.recordUserName = recordUserName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "requisition_date", length = 10)
	public Date getRequisitionDate() {
		return this.requisitionDate;
	}

	public void setRequisitionDate(Date requisitionDate) {
		this.requisitionDate = requisitionDate;
	}

	@Column(name = "requisition_dept_name", length = 20)
	public String getRequisitionDeptName() {
		return this.requisitionDeptName;
	}

	public void setRequisitionDeptName(String requisitionDeptName) {
		this.requisitionDeptName = requisitionDeptName;
	}

	@ManyToOne(fetch = FetchType.EAGER)
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

	@Column(name = "pay_no")
	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	@Column(name = "pay_customer_name")
	public String getPayCustomerName() {
		return payCustomerName;
	}

	public void setPayCustomerName(String payCustomerName) {
		this.payCustomerName = payCustomerName;
	}

	@Column(name = "pay_type")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	@Column(name = "next_operator_id")
	public String getNextOperatorId() {
		return nextOperatorId;
	}

	public void setNextOperatorId(String nextOperatorId) {
		this.nextOperatorId = nextOperatorId;
	}

	
}