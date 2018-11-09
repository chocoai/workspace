package com.whty.mxbj.base.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Transient;

public abstract class BaseModel implements Serializable {
	private static final long serialVersionUID = -8021502096915657537L;

	protected Date createTime;
	protected Date updateTime;

	@Transient
	private Integer page = 1;

	@Transient
	private Integer rows = 10;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
