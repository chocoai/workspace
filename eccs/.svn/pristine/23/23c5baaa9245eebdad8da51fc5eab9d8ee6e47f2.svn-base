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
 * Project entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "project", catalog = "eccs")
public class Project implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String no; // 项目编号

	private String name; // 项目名称

	private Date recordDate; // 立项日期

	private Dept senderDept; // 委托单位

	private T_Customer customer;// 委托单位为客户

	private Date senderDate; // 委托日期
	// private Dept handleDept; // -----重要 项目1---15流程此字段过滤

	private String handleDept; // -----重要 项目1---15流程此字段过滤

	private String handleManagerId; // 项目负责人

	// private User nextWorker; // ---该项目下一步工作的处理人 (如果为空,则下一步工作
	// 由任务分配表(step3Worker表)的人都可以处理)

	private String scaleType; // 项目规模

	private Double projectSize; // 1大型 2中型 3小型

	private ServiceType serviceType; // 咨询类型

	private EditorialType editorialType; // 编审类型(业务范围)

	private ProjectType projectType; // 工程类别（项目类别）
	// private ProjectNature projectNature;//工程性质

	private UrgentType urgentType; // 紧急程度

	private Dept receiveDept; // ---重要,投标 合同 此字段过滤

	private User receiveManager; // ---经营负责人

	private String recordName; // 立项人

	private Integer receiveType; // 是否需要投标 0表示不需要投标(直接委托) 1表示需要投标

	private Integer doContact; // 是否需要签合同 0表示不需要签(不需要签合同则一定不需要投标) 1表示需要签

	private Integer bidResult; // 中标结果 0未中标 1 中标 2待审 3直接委托

	private String remark; // 备注

	private User createUser; // 创建人

	private Dept dept; // 创建人所属部门

	private Date ctime; // 创建时间

	private Integer status; // 0代表删除 1代表未启动 2 代表已启动

	// private Integer step; // -5项目立项 -4投标策划 -3投标总结 -2 合同评审 -1合同登记

	private StepTemplete stepTemplete;

	private Double totalinvoice;// 累计已开票

	private Double noinvoice;// 未开票

	private Double receivables;// 实际应收

	private String contacton;// 合同编号

	private Integer performanceid;// 绩效标示 -1标示未做绩效，1标示已做绩效

//	private String nextOperator; // 下一环节处理人

	private ProjectInfo projectInfo; // 对应的项目信息

	private Boolean isOperator;// 当前用户是否可以处理

	private String processType; // 流程类型(0表示先经营管理后项目实施，1表示经营管理和项目实施可同时独立进行)

	private String pmOperator; // 项目管理人员分配处理人,可为多个用户(多个用户ID)

	private ProceStepDef currentStep; // 项目管理中存放当前环节
	
	private String projectProfile;	// 工程概况

	// Constructors

	/** default constructor */
	public Project() {
		ctime = new Date();
		status = 1;
		totalinvoice = 0.00;
		noinvoice = 0.00;
		receivables = 0.00;
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
	@JoinColumn(name = "projectInfoId")
	@NotFound(action = NotFoundAction.IGNORE)
	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public T_Customer getCustomer() {
		return customer;
	}

	public void setCustomer(T_Customer customer) {
		this.customer = customer;
	}

//	@Column(name = "next_operator_id")
//	public String getNextOperator() {
//		return nextOperator;
//	}
//
//	public void setNextOperator(String nextOperator) {
//		this.nextOperator = nextOperator;
//	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_dept_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Dept getSenderDept() {
		return senderDept;
	}

	public void setSenderDept(Dept senderDept) {
		this.senderDept = senderDept;
	}

	@Column(name = "handle_dept_id")
	public String getHandleDept() {
		return handleDept;
	}

	public void setHandleDept(String handleDept) {
		this.handleDept = handleDept;
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
	@JoinColumn(name = "service_type_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	@Column(name = "docontact")
	public Integer getDoContact() {
		return doContact;
	}

	public void setDoContact(Integer doContact) {
		this.doContact = doContact;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "urgent_type_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public UrgentType getUrgentType() {
		return urgentType;
	}

	public void setUrgentType(UrgentType urgentType) {
		this.urgentType = urgentType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receive_dept_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Dept getReceiveDept() {
		return receiveDept;
	}

	public void setReceiveDept(Dept receiveDept) {
		this.receiveDept = receiveDept;
	}

	@Column(name = "no", length = 100)
	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	@Column(name = "name", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "record_date", length = 10)
	public Date getRecordDate() {
		return this.recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "sender_date", length = 10)
	public Date getSenderDate() {
		return this.senderDate;
	}

	public void setSenderDate(Date senderDate) {
		this.senderDate = senderDate;
	}

	@Column(name = "scale_type", length = 200)
	public String getScaleType() {
		return this.scaleType;
	}

	public void setScaleType(String scaleType) {
		this.scaleType = scaleType;
	}

	@Column(name = "project_size", precision = 22, scale = 0)
	public Double getProjectSize() {
		return this.projectSize;
	}

	public void setProjectSize(Double projectSize) {
		this.projectSize = projectSize;
	}

	@Column(name = "record_name", length = 20)
	public String getRecordName() {
		return this.recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	@Column(name = "receive_type")
	public Integer getReceiveType() {
		return this.receiveType;
	}

	public void setReceiveType(Integer receiveType) {
		this.receiveType = receiveType;
	}

	@Column(name = "bid_result")
	public Integer getBidResult() {
		return this.bidResult;
	}

	public void setBidResult(Integer bidResult) {
		this.bidResult = bidResult;
	}

	@Column(name = "remark", length = 1000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "create_user_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
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

	// @Column(name = "step")
	// public Integer getStep() {
	// return step;
	// }
	//
	// public void setStep(Integer step) {
	// this.step = step;
	// }

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "step_templete_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public StepTemplete getStepTemplete() {
		return stepTemplete;
	}

	public void setStepTemplete(StepTemplete stepTemplete) {
		this.stepTemplete = stepTemplete;
	}

	@Column(name = "handle_manager_id")
	public String getHandleManagerId() {
		return handleManagerId;
	}

	public void setHandleManagerId(String handleManagerId) {
		this.handleManagerId = handleManagerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receive_manager_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getReceiveManager() {
		return receiveManager;
	}

	public void setReceiveManager(User receiveManager) {
		this.receiveManager = receiveManager;
	}

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "next_worker_id")
	// @NotFound(action = NotFoundAction.IGNORE)
	// public User getNextWorker() {
	// return nextWorker;
	// }
	//
	// public void setNextWorker(User nextWorker) {
	// this.nextWorker = nextWorker;
	// }

	@Transient
	public Double getTotalinvoice() {
		return totalinvoice;
	}

	public void setTotalinvoice(Double totalinvoice) {
		this.totalinvoice = totalinvoice;
	}

	@Transient
	public Double getNoinvoice() {
		return noinvoice;
	}

	public void setNoinvoice(Double noinvoice) {
		this.noinvoice = noinvoice;
	}

	@Transient
	public Double getReceivables() {
		return receivables;
	}

	public void setReceivables(Double receivables) {
		this.receivables = receivables;
	}

	@Transient
	public String getContacton() {
		return contacton;
	}

	public void setContacton(String contacton) {
		this.contacton = contacton;
	}

	@Column(name = "performance_id")
	public Integer getPerformanceid() {
		return performanceid;
	}

	public void setPerformanceid(Integer performanceid) {
		this.performanceid = performanceid;
	}

	@Transient
	public Boolean getIsOperator() {
		return isOperator;
	}

	public void setIsOperator(Boolean isOperator) {
		this.isOperator = isOperator;
	}

	@Column(name = "process_type")
	public String getProcessType() {
		return this.processType;
	}

	@Column(name = "pm_operator_id")
	public String getPmOperator() {
		return this.pmOperator;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public void setPmOperator(String pmOperator) {
		this.pmOperator = pmOperator;
	}

	@Transient
	public ProceStepDef getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(ProceStepDef currentStep) {
		this.currentStep = currentStep;
	}

	@Column(name = "project_profile")
	public String getProjectProfile() {
		return this.projectProfile;
	}

	public void setProjectProfile(String projectProfile) {
		this.projectProfile = projectProfile;
	}

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "project_nature_id")
	// public ProjectNature getProjectNature() {
	// return projectNature;
	// }
	//
	// public void setProjectNature(ProjectNature projectNature) {
	// this.projectNature = projectNature;
	// }
}