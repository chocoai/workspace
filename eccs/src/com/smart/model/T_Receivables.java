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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * 收款信息
 * 
 * @author lenovo
 *
 */

@Entity
@Table(name = "t_fi_receivables", catalog = "eccs")
public class T_Receivables implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Project project; // 项目

	private T_Invoice invoice; // 开票信息

	private Double arrivalAmount; // 本次到账金额

	private Date arrivalDate; // 到账日期

	private String remark; // 备注

	private User user; // 登记人

	private Date rdate; // 登记日期

	private Date ctime;

	private Integer status;

	private Double receivable;// 未收款

	private Double Cumulatives;// 累计收款

	/** default constructor */
	public T_Receivables() {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_invoice_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public T_Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(T_Invoice invoice) {
		this.invoice = invoice;
	}

	@Column(name = "arrival_amount")
	public Double getArrivalAmount() {
		return arrivalAmount;
	}

	public void setArrivalAmount(Double arrivalAmount) {
		this.arrivalAmount = arrivalAmount;
	}

	@Column(name = "arrival_date", length = 10)
	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	@Column(name = "remark", length = 500)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
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
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Transient
	public Double getCumulatives() {
		return Cumulatives;
	}

	public void setCumulatives(Double cumulatives) {
		Cumulatives = cumulatives;
	}

	@Transient
	public Double getReceivable() {
		return receivable;
	}

	public void setReceivable(Double receivable) {
		this.receivable = receivable;
	}

}
