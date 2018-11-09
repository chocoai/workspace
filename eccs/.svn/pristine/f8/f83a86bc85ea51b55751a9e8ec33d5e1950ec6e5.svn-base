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
 * 借据证照关系
 */
@Entity
@Table(name = "t_borrow_registration", catalog = "eccs")
public class T_borrow_registration implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private T_liborrow borrow;// 借据表

	private T_liregistration registration;// 证照表

	private Integer status;// 状态

	public T_borrow_registration() {
		super();
		status = 1;
	}

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
	@JoinColumn(name = "t_borrow_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public T_liborrow getBorrow() {
		return borrow;
	}

	public void setBorrow(T_liborrow borrow) {
		this.borrow = borrow;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_registration_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public T_liregistration getRegistration() {
		return registration;
	}

	public void setRegistration(T_liregistration registration) {
		this.registration = registration;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
