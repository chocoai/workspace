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
 * 证照处理信息
 * 
 * @author lenovo
 *
 */
@Entity
@Table(name = "t_li_handle", catalog = "eccs")
public class T_handle implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer suggestion;// 处理意见1,准许 2不准,3占用

	private String tremarks;// 备注

	private User user;// 登记人

	private Date rtime;// 登记时间

	private Date ctime;// 创建时间

	private Integer status;// 状态

	public T_handle() {
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

	@Column(name = "t_suggestion")
	public Integer getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(Integer suggestion) {
		this.suggestion = suggestion;
	}

	@Column(name = "t_tremarks")
	public String getTremarks() {
		return tremarks;
	}

	public void setTremarks(String tremarks) {
		this.tremarks = tremarks;
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

	@Column(name = "rtime", length = 19)
	public Date getRtime() {
		return rtime;
	}

	public void setRtime(Date rtime) {
		this.rtime = rtime;
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
