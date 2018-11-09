package com.smart.model;

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
 * RoleRes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "role_res", catalog = "eccs")
public class RoleRes implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private Integer id;

	private Role role;

	private Res res;

	private Integer status;

	// Constructors

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public RoleRes() {
		super();
		status = 1;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "res_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Res getRes() {
		return res;
	}

	public void setRes(Res res) {
		this.res = res;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}