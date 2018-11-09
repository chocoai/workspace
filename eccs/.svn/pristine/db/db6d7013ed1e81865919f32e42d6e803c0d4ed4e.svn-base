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
 * Invoice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "invoice", catalog = "eccs")
public class Invoice implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Project project;

	private String paidUnit;

	private Double invoiceYesAmount;

	private Double nvoiceNoIamount;

	private Double shouldAmount;

	private Double invoiceAmount;

	private String invoiceNo;

	private String invoiceContent;

	private Date invoiceDate;

	private String useName;

	private User user;

	private Dept dept;

	private Date ctime;

	private Integer status;

	// Constructors

	/** default constructor */
	public Invoice() {
		status = 1;
		ctime = new Date();
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

	@Column(name = "paid_unit", length = 50)
	public String getPaidUnit() {
		return this.paidUnit;
	}

	public void setPaidUnit(String paidUnit) {
		this.paidUnit = paidUnit;
	}

	@Column(name = "invoice_yes_amount", precision = 22, scale = 0)
	public Double getInvoiceYesAmount() {
		return this.invoiceYesAmount;
	}

	public void setInvoiceYesAmount(Double invoiceYesAmount) {
		this.invoiceYesAmount = invoiceYesAmount;
	}

	@Column(name = "nvoice_no_iamount", precision = 22, scale = 0)
	public Double getNvoiceNoIamount() {
		return this.nvoiceNoIamount;
	}

	public void setNvoiceNoIamount(Double nvoiceNoIamount) {
		this.nvoiceNoIamount = nvoiceNoIamount;
	}

	@Column(name = "should_amount", precision = 22, scale = 0)
	public Double getShouldAmount() {
		return this.shouldAmount;
	}

	public void setShouldAmount(Double shouldAmount) {
		this.shouldAmount = shouldAmount;
	}

	@Column(name = "invoice_amount", precision = 22, scale = 0)
	public Double getInvoiceAmount() {
		return this.invoiceAmount;
	}

	public void setInvoiceAmount(Double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	@Column(name = "invoice_no", length = 50)
	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	@Column(name = "invoice_content", length = 500)
	public String getInvoiceContent() {
		return this.invoiceContent;
	}

	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "invoice_date", length = 10)
	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	@Column(name = "use_name", length = 20)
	public String getUseName() {
		return this.useName;
	}

	public void setUseName(String useName) {
		this.useName = useName;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "ctime", length = 10)
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

}