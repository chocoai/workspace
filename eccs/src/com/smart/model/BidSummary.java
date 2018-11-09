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
 * BidSummary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "bid_summary", catalog = "eccs")
public class BidSummary implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Project project;

	private String projectName;

	private BidPlan bidPlan;

	private String bidDeptName;

	private Date openDate;

	private String projectLevel;

	private String area;

	private String bidResult;

	private Double inBidAmount;

	private Double bidCompensateAmount;

	private Integer isPaid;

	private Double bidDirectAmount;

	private Double bidPersionAmount;

	private String bidContent;

	private String bondDeptName;

	private Date bondPayDate;

	private Double bondAmount;

	private Date bondReturnDate;

	private Double bondReturnAmount;

	private Double bondNoreturnAmount;

	private User user;

	private Dept dept;

	private Date ctime;

	private Integer status;

	// Constructors

	/** default constructor */
	public BidSummary() {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bid_plan_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public BidPlan getBidPlan() {
		return bidPlan;
	}

	public void setBidPlan(BidPlan bidPlan) {
		this.bidPlan = bidPlan;
	}

	@Column(name = "project_name", length = 100)
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "bid_dept_name", length = 20)
	public String getBidDeptName() {
		return this.bidDeptName;
	}

	public void setBidDeptName(String bidDeptName) {
		this.bidDeptName = bidDeptName;
	}

	@Column(name = "open_date", length = 19)
	public Date getOpenDate() {
		return this.openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	@Column(name = "project_level", length = 0)
	public String getProjectLevel() {
		return this.projectLevel;
	}

	public void setProjectLevel(String projectLevel) {
		this.projectLevel = projectLevel;
	}

	@Column(name = "area", length = 0)
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "bid_result", length = 0)
	public String getBidResult() {
		return this.bidResult;
	}

	public void setBidResult(String bidResult) {
		this.bidResult = bidResult;
	}

	@Column(name = "in_bid_amount", precision = 22, scale = 0)
	public Double getInBidAmount() {
		return this.inBidAmount;
	}

	public void setInBidAmount(Double inBidAmount) {
		this.inBidAmount = inBidAmount;
	}

	@Column(name = "bid_compensate_amount", precision = 22, scale = 0)
	public Double getBidCompensateAmount() {
		return this.bidCompensateAmount;
	}

	public void setBidCompensateAmount(Double bidCompensateAmount) {
		this.bidCompensateAmount = bidCompensateAmount;
	}

	@Column(name = "is_paid")
	public Integer getIsPaid() {
		return this.isPaid;
	}

	public void setIsPaid(Integer isPaid) {
		this.isPaid = isPaid;
	}

	@Column(name = "bid_direct_amount", precision = 22, scale = 0)
	public Double getBidDirectAmount() {
		return this.bidDirectAmount;
	}

	public void setBidDirectAmount(Double bidDirectAmount) {
		this.bidDirectAmount = bidDirectAmount;
	}

	@Column(name = "bid_persion_amount", precision = 22, scale = 0)
	public Double getBidPersionAmount() {
		return this.bidPersionAmount;
	}

	public void setBidPersionAmount(Double bidPersionAmount) {
		this.bidPersionAmount = bidPersionAmount;
	}

	@Column(name = "bid_content", length = 1000)
	public String getBidContent() {
		return this.bidContent;
	}

	public void setBidContent(String bidContent) {
		this.bidContent = bidContent;
	}

	@Column(name = "bond_dept_name", length = 50)
	public String getBondDeptName() {
		return this.bondDeptName;
	}

	public void setBondDeptName(String bondDeptName) {
		this.bondDeptName = bondDeptName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "bond_pay_date", length = 10)
	public Date getBondPayDate() {
		return this.bondPayDate;
	}

	public void setBondPayDate(Date bondPayDate) {
		this.bondPayDate = bondPayDate;
	}

	@Column(name = "bond_amount", precision = 22, scale = 0)
	public Double getBondAmount() {
		return this.bondAmount;
	}

	public void setBondAmount(Double bondAmount) {
		this.bondAmount = bondAmount;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "bond_return_date", length = 10)
	public Date getBondReturnDate() {
		return this.bondReturnDate;
	}

	public void setBondReturnDate(Date bondReturnDate) {
		this.bondReturnDate = bondReturnDate;
	}

	@Column(name = "bond_return_amount", precision = 22, scale = 0)
	public Double getBondReturnAmount() {
		return this.bondReturnAmount;
	}

	public void setBondReturnAmount(Double bondReturnAmount) {
		this.bondReturnAmount = bondReturnAmount;
	}

	@Column(name = "bond_noreturn_amount", precision = 22, scale = 0)
	public Double getBondNoreturnAmount() {
		return this.bondNoreturnAmount;
	}

	public void setBondNoreturnAmount(Double bondNoreturnAmount) {
		this.bondNoreturnAmount = bondNoreturnAmount;
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

}