package com.smart.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * @Description:
 * @author raopanfeng
 * @date 2016-3-20 上午09:22:27
 */

@Entity
@Table(catalog = "eccs", name = "calendar")
public class Calendar implements Serializable {

	private static final long serialVersionUID = 7153607810856309877L;

	private String calId;

	private String calTitle;

	private int calType; // 日程类型，0表示个人日程，1表示部门日程

	private String calContent;

	private Date calStartTime;

	private Date calEndTime;

	private String calAddress;

	private String calPerson;

	private User user; // 日程创建者

	private Dept dept; // 日程创建者所属部门

	private Date calCreateTime;

	@Id
	@Column(name = "calId", length = 16)
	public String getCalId() {
		return calId;
	}

	public void setCalId(String calId) {
		this.calId = calId;
	}

	@Column(name = "calTitle", length = 128)
	public String getCalTitle() {
		return calTitle;
	}

	public void setCalTitle(String calTitle) {
		this.calTitle = calTitle;
	}

	@Column(name = "calType")
	public int getCalType() {
		return calType;
	}

	public void setCalType(int calType) {
		this.calType = calType;
	}

	@Column(name = "calContent", length = 512)
	public String getCalContent() {
		return calContent;
	}

	public void setCalContent(String calContent) {
		this.calContent = calContent;
	}

	@Column(name = "calStartTime", length = 19)
	public Date getCalStartTime() {
		return calStartTime;
	}

	public void setCalStartTime(Date calStartTime) {
		this.calStartTime = calStartTime;
	}

	@Column(name = "calEndTime", length = 19)
	public Date getCalEndTime() {
		return calEndTime;
	}

	public void setCalEndTime(Date calEndTime) {
		this.calEndTime = calEndTime;
	}

	@Column(name = "calAddress", length = 64)
	public String getCalAddress() {
		return calAddress;
	}

	public void setCalAddress(String calAddress) {
		this.calAddress = calAddress;
	}

	@Column(name = "calPerson", length = 64)
	public String getCalPerson() {
		return calPerson;
	}

	public void setCalPerson(String calPerson) {
		this.calPerson = calPerson;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "calUserId")
	@NotFound(action = NotFoundAction.IGNORE)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "calDeptId")
	@NotFound(action = NotFoundAction.IGNORE)
	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	@Column(name = "calCreateTime", length = 19)
	public Date getCalCreateTime() {
		return calCreateTime;
	}

	public void setCalCreateTime(Date calCreateTime) {
		this.calCreateTime = calCreateTime;
	}

}
