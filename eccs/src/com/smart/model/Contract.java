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
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Contract entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "contract", catalog = "eccs")
public class Contract implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	private Integer id;

	private Project project;

	private String no;

	private String name;

	private Integer signStatus;

	private Integer contractStatus;

	private Double diedAmount;

	private String projectPlace;

	private Integer isAdd;

	private String masterName;

	private Integer contractPay;

	private Double tempAmount;

	private Double allAmount;

	private Double splitAmount;

	private Double lastAmount;

	private Double payAmount;//

	private Dept contractDept; // 签约部门

	private User manager; // 签约人

	private Date contractSignDate;

	private Date contractReturnDate;

	private Double projectAmount;

	private Integer isDeposit;

	private Double depositAmount;

	private Integer isRecycle;

	private Date recycleDate;

	private String buildPlace;

	private String chargeRemark;

	private String designStageIds;

	private ServiceType serviceType; // 咨询类型

	private EditorialType editorialType; // 编审类型(业务范围)

	private ProjectType projectType; // 工程类别（项目类别）

	private ProjectNature projectNature;// 工程性质

	private User user;

	private Dept dept;

	private Date ctime;

	private Integer status;

	private Date planEndDate;

	private Integer serviceTime;

	private String projectInfo;

	private String remark;

	private boolean operable; // 处理权限

	// Constructors

	/** default constructor */
	public Contract() {
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

	@Column(name = "design_stage_ids")
	public String getDesignStageIds() {
		return designStageIds;
	}

	public void setDesignStageIds(String designStageIds) {
		this.designStageIds = designStageIds;
	}

	@Column(name = "plan_end_date")
	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}

	@Column(name = "service_time")
	public Integer getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Integer serviceTime) {
		this.serviceTime = serviceTime;
	}

	@Column(name = "project_info")
	public String getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(String projectInfo) {
		this.projectInfo = projectInfo;
	}

	@Column(name = "remark")
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dept_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
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
	@JoinColumn(name = "manager_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_type_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public ProjectType getProjectType() {
		return projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_nature_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public ProjectNature getProjectNature() {
		return projectNature;
	}

	public void setProjectNature(ProjectNature projectNature) {
		this.projectNature = projectNature;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_type_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "editorial_type_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public EditorialType getEditorialType() {
		return editorialType;
	}

	public void setEditorialType(EditorialType editorialType) {
		this.editorialType = editorialType;
	}

	@Column(name = "no", length = 100)
	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sign_status")
	public Integer getSignStatus() {
		return this.signStatus;
	}

	public void setSignStatus(Integer signStatus) {
		this.signStatus = signStatus;
	}

	@Column(name = "contract_status")
	public Integer getContractStatus() {
		return this.contractStatus;
	}

	public void setContractStatus(Integer contractStatus) {
		this.contractStatus = contractStatus;
	}

	@Column(name = "died_amount", precision = 22, scale = 0)
	public Double getDiedAmount() {
		return this.diedAmount;
	}

	public void setDiedAmount(Double diedAmount) {
		this.diedAmount = diedAmount;
	}

	@Column(name = "project_place", length = 100)
	public String getProjectPlace() {
		return this.projectPlace;
	}

	public void setProjectPlace(String projectPlace) {
		this.projectPlace = projectPlace;
	}

	@Column(name = "is_add")
	public Integer getIsAdd() {
		return this.isAdd;
	}

	public void setIsAdd(Integer isAdd) {
		this.isAdd = isAdd;
	}

	@Column(name = "master_name", length = 100)
	public String getMasterName() {
		return this.masterName;
	}

	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	@Column(name = "contract_pay")
	public Integer getContractPay() {
		return this.contractPay;
	}

	public void setContractPay(Integer contractPay) {
		this.contractPay = contractPay;
	}

	@Column(name = "temp_amount", precision = 22, scale = 0)
	public Double getTempAmount() {
		return this.tempAmount;
	}

	public void setTempAmount(Double tempAmount) {
		this.tempAmount = tempAmount;
	}

	@Column(name = "all_amount", precision = 22, scale = 0)
	public Double getAllAmount() {
		return this.allAmount;
	}

	public void setAllAmount(Double allAmount) {
		this.allAmount = allAmount;
	}

	@Column(name = "split_amount", precision = 22, scale = 0)
	public Double getSplitAmount() {
		return this.splitAmount;
	}

	public void setSplitAmount(Double splitAmount) {
		this.splitAmount = splitAmount;
	}

	@Column(name = "last_amount", precision = 22, scale = 0)
	public Double getLastAmount() {
		return this.lastAmount;
	}

	public void setLastAmount(Double lastAmount) {
		this.lastAmount = lastAmount;
	}

	@Column(name = "pay_amount", precision = 22, scale = 0)
	public Double getPayAmount() {
		return this.payAmount;
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contract_dept_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Dept getContractDept() {
		return contractDept;
	}

	public void setContractDept(Dept contractDept) {
		this.contractDept = contractDept;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "contract_sign_date", length = 10)
	public Date getContractSignDate() {
		return this.contractSignDate;
	}

	public void setContractSignDate(Date contractSignDate) {
		this.contractSignDate = contractSignDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "contract_return_date", length = 10)
	public Date getContractReturnDate() {
		return this.contractReturnDate;
	}

	public void setContractReturnDate(Date contractReturnDate) {
		this.contractReturnDate = contractReturnDate;
	}

	@Column(name = "project_amount", precision = 22, scale = 0)
	public Double getProjectAmount() {
		return this.projectAmount;
	}

	public void setProjectAmount(Double projectAmount) {
		this.projectAmount = projectAmount;
	}

	@Column(name = "is_deposit")
	public Integer getIsDeposit() {
		return this.isDeposit;
	}

	public void setIsDeposit(Integer isDeposit) {
		this.isDeposit = isDeposit;
	}

	@Column(name = "deposit_amount", precision = 22, scale = 0)
	public Double getDepositAmount() {
		return this.depositAmount;
	}

	public void setDepositAmount(Double depositAmount) {
		this.depositAmount = depositAmount;
	}

	@Column(name = "is_recycle")
	public Integer getIsRecycle() {
		return this.isRecycle;
	}

	public void setIsRecycle(Integer isRecycle) {
		this.isRecycle = isRecycle;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "recycle_date", length = 10)
	public Date getRecycleDate() {
		return this.recycleDate;
	}

	public void setRecycleDate(Date recycleDate) {
		this.recycleDate = recycleDate;
	}

	@Column(name = "build_place", length = 100)
	public String getBuildPlace() {
		return this.buildPlace;
	}

	public void setBuildPlace(String buildPlace) {
		this.buildPlace = buildPlace;
	}

	@Column(name = "charge_remark", length = 1000)
	public String getChargeRemark() {
		return this.chargeRemark;
	}

	public void setChargeRemark(String chargeRemark) {
		this.chargeRemark = chargeRemark;
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

	@Transient
	public boolean getOperable() {
		return this.operable;
	}

	public void setOperable(boolean operable) {
		this.operable = operable;
	}

}