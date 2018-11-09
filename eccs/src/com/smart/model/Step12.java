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
 * Step12 entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "step12", catalog = "eccs")
public class Step12 implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Project project;

	private String projectName;

	private String fileName;

	private String receiverDeptName;

	private String writerDescription;

	private String writerName;

	private Date writerDate;

	private String leaderView;

	private String officeMasterView1;

	private String officeMasterName1;

	private Date officeMasterDate1;

	private String officeMasterView2;

	private String officeMasterName2;

	private Date officeMasterDate2;

	private String deptMasterView;

	private String deptMasterName;

	private Date deptMasterDate;

	private String compMasterView;

	private String compMasterName;

	private Date compMasterDate;

	private Dept dept;

	private User user;

	private Date ctime;

	private Integer status;

	private Date confirmTime; // 任务提交时间 --新增

	private User nextWorker;// 下一步处理人 --新增

	private Integer step;// 步骤 1、审核；2、审定

	@Column(name = "confirm_time")
	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	// Constructors

	/** default constructor */
	public Step12() {
		ctime = new Date();
		status = 1;
		step = 1;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Column(name = "project_name", length = 50)
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(name = "file_name", length = 50)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "receiver_dept_name", length = 50)
	public String getReceiverDeptName() {
		return this.receiverDeptName;
	}

	public void setReceiverDeptName(String receiverDeptName) {
		this.receiverDeptName = receiverDeptName;
	}

	@Column(name = "writer_description", length = 1000)
	public String getWriterDescription() {
		return this.writerDescription;
	}

	public void setWriterDescription(String writerDescription) {
		this.writerDescription = writerDescription;
	}

	@Column(name = "writer_name", length = 50)
	public String getWriterName() {
		return this.writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "writer_date", length = 10)
	public Date getWriterDate() {
		return this.writerDate;
	}

	public void setWriterDate(Date writerDate) {
		this.writerDate = writerDate;
	}

	@Column(name = "leader_view", length = 1000)
	public String getLeaderView() {
		return this.leaderView;
	}

	public void setLeaderView(String leaderView) {
		this.leaderView = leaderView;
	}

	@Column(name = "office_master_view2")
	public String getOfficeMasterView2() {
		return officeMasterView2;
	}

	public void setOfficeMasterView2(String officeMasterView2) {
		this.officeMasterView2 = officeMasterView2;
	}

	@Column(name = "office_master_name2", length = 50)
	public String getOfficeMasterName2() {
		return this.officeMasterName2;
	}

	public void setOfficeMasterName2(String officeMasterName2) {
		this.officeMasterName2 = officeMasterName2;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "office_master_date2", length = 10)
	public Date getOfficeMasterDate2() {
		return this.officeMasterDate2;
	}

	public void setOfficeMasterDate2(Date officeMasterDate2) {
		this.officeMasterDate2 = officeMasterDate2;
	}

	@Column(name = "office_master_view1")
	public String getOfficeMasterView1() {
		return officeMasterView1;
	}

	public void setOfficeMasterView1(String officeMasterView1) {
		this.officeMasterView1 = officeMasterView1;
	}

	@Column(name = "office_master_name1", length = 50)
	public String getOfficeMasterName1() {
		return this.officeMasterName1;
	}

	public void setOfficeMasterName1(String officeMasterName1) {
		this.officeMasterName1 = officeMasterName1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "office_master_date1", length = 10)
	public Date getOfficeMasterDate1() {
		return this.officeMasterDate1;
	}

	public void setOfficeMasterDate1(Date officeMasterDate1) {
		this.officeMasterDate1 = officeMasterDate1;
	}

	@Column(name = "dept_master_view", length = 1000)
	public String getDeptMasterView() {
		return this.deptMasterView;
	}

	public void setDeptMasterView(String deptMasterView) {
		this.deptMasterView = deptMasterView;
	}

	@Column(name = "dept_master_name", length = 50)
	public String getDeptMasterName() {
		return this.deptMasterName;
	}

	public void setDeptMasterName(String deptMasterName) {
		this.deptMasterName = deptMasterName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dept_master_date", length = 10)
	public Date getDeptMasterDate() {
		return this.deptMasterDate;
	}

	public void setDeptMasterDate(Date deptMasterDate) {
		this.deptMasterDate = deptMasterDate;
	}

	@Column(name = "comp_master_view", length = 1000)
	public String getCompMasterView() {
		return this.compMasterView;
	}

	public void setCompMasterView(String compMasterView) {
		this.compMasterView = compMasterView;
	}

	@Column(name = "comp_master_name", length = 50)
	public String getCompMasterName() {
		return this.compMasterName;
	}

	public void setCompMasterName(String compMasterName) {
		this.compMasterName = compMasterName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "comp_master_date", length = 10)
	public Date getCompMasterDate() {
		return this.compMasterDate;
	}

	public void setCompMasterDate(Date compMasterDate) {
		this.compMasterDate = compMasterDate;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "next_worker_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getNextWorker() {
		return nextWorker;
	}

	public void setNextWorker(User nextWorker) {
		this.nextWorker = nextWorker;
	}

	@Column(name = "step")
	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

}