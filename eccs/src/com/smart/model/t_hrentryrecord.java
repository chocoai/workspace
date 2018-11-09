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
 * 入职
 * 
 * @author lenovo
 * 
 */
@Entity
@Table(name = "t_hr_entry_record", catalog = "eccs")
public class t_hrentryrecord implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String edate;// 入职时间

	private String post;// 岗位描述

	private Integer field;// 工作性质1,全职 2,兼职

	private Date labor_date;// 劳动合同有效期

	private Date ss_date;// 社保缴费日期

	private String ss_base;// 社保缴费基数

	private Date af_date;// 公积金缴费日期

	private String af_base;// 公积金缴费基数

	private String archives;// 档案所在地

	private String fdate;// 转正时间

	private String qdate;// 离职时间

	private Date ctime;// 创建日期

	private Integer status;// 是否可用状态

	private T_hremployee t_hremployee;// 人员信息表

	private User fuser;// 转正操作人

	private String frtime;// 转正操作时间

	private User quser;// 离职操作人

	private String qrtime;// 离职操作时间

	public t_hrentryrecord() {
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

	@Column(name = "edate")
	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	@Column(name = "post")
	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	@Column(name = "field")
	public Integer getField() {
		return field;
	}

	public void setField(Integer field) {
		this.field = field;
	}

	@Column(name = "labor_date")
	public Date getLabor_date() {
		return labor_date;
	}

	public void setLabor_date(Date labor_date) {
		this.labor_date = labor_date;
	}

	@Column(name = "ss_date")
	public Date getSs_date() {
		return ss_date;
	}

	public void setSs_date(Date ss_date) {
		this.ss_date = ss_date;
	}

	@Column(name = "ss_base")
	public String getSs_base() {
		return ss_base;
	}

	public void setSs_base(String ss_base) {
		this.ss_base = ss_base;
	}

	@Column(name = "af_date")
	public Date getAf_date() {
		return af_date;
	}

	public void setAf_date(Date af_date) {
		this.af_date = af_date;
	}

	@Column(name = "af_base")
	public String getAf_base() {
		return af_base;
	}

	public void setAf_base(String af_base) {
		this.af_base = af_base;
	}

	@Column(name = "archives")
	public String getArchives() {
		return archives;
	}

	public void setArchives(String archives) {
		this.archives = archives;
	}

	@Column(name = "fdate")
	public String getFdate() {
		return fdate;
	}

	public void setFdate(String fdate) {
		this.fdate = fdate;
	}

	@Column(name = "qdate")
	public String getQdate() {
		return qdate;
	}

	public void setQdate(String qdate) {
		this.qdate = qdate;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public T_hremployee getT_hremployee() {
		return t_hremployee;
	}

	public void setT_hremployee(T_hremployee t_hremployee) {
		this.t_hremployee = t_hremployee;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fuser_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getFuser() {
		return fuser;
	}

	public void setFuser(User fuser) {
		this.fuser = fuser;
	}

	@Column(name = "frtime")
	public String getFrtime() {
		return frtime;
	}

	public void setFrtime(String frtime) {
		this.frtime = frtime;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "quser_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getQuser() {
		return quser;
	}

	public void setQuser(User quser) {
		this.quser = quser;
	}

	@Column(name = "qrtime")
	public String getQrtime() {
		return qrtime;
	}

	public void setQrtime(String qrtime) {
		this.qrtime = qrtime;
	}

}
