package com.whty.assis.manage.model;

import java.io.Serializable;

public class HdCode implements Serializable {

	private static final long serialVersionUID = -586702171685638886L;

	private Integer id;
	private Integer pid;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}