package com.whty.ebp.manage.model;

import java.io.Serializable;
import java.util.Date;

public class FlatModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2890313343552233045L;

	private String id;
	private String modelCode;
	private String memo;
	private Date createTime;
	private Date updateTime;

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
