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
 * 单据表
 * 
 * @author lenovo
 *
 */
@Entity
@Table(name = "t_li_borrow", catalog = "eccs")
public class T_liborrow implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String documents;// 单据no

	private String returntime;// 归还日期

	private Date gh_date;// 实际归还日期

	private User gh_userid;// 归还人

	private String remarks;// 备注

	private User user;// 登记人

	private String rtime;// 登记时间

	private Date ctime;// 创建时间

	private Integer status;// 状态

	private Integer state;// 归还状态,1.归还,2.未归还

	private T_handle handle;// 处理表

	private Dept sdeptid;// 保管部门

	public T_liborrow() {
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

	@Column(name = "t_documents")
	public String getDocuments() {
		return documents;
	}

	public void setDocuments(String documents) {
		this.documents = documents;
	}

	@Column(name = "t_returntime")
	public String getReturntime() {
		return returntime;
	}

	public void setReturntime(String returntime) {
		this.returntime = returntime;
	}

	@Column(name = "t_remarks")
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	@Column(name = "rtime")
	public String getRtime() {
		return rtime;
	}

	public void setRtime(String rtime) {
		this.rtime = rtime;
	}

	@Column(name = "ctime")
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

	@Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "handle_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public T_handle getHandle() {
		return handle;
	}

	public void setHandle(T_handle handle) {
		this.handle = handle;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sdeptid")
	@NotFound(action = NotFoundAction.IGNORE)
	public Dept getSdeptid() {
		return sdeptid;
	}

	public void setSdeptid(Dept sdeptid) {
		this.sdeptid = sdeptid;
	}

	@Column(name = "gh_date")
	public Date getGh_date() {
		return gh_date;
	}

	public void setGh_date(Date gh_date) {
		this.gh_date = gh_date;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "gh_userid")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getGh_userid() {
		return gh_userid;
	}

	public void setGh_userid(User gh_userid) {
		this.gh_userid = gh_userid;
	}

}
