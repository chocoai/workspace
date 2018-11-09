package com.whty.mxbj.api.model;

import java.util.Date;

import com.whty.mxbj.base.model.BaseModel;

/**
 * 笔固件升级
 * 
 * @author zhangzheng
 *
 */
public class PenFirmvare extends BaseModel {

	private static final long serialVersionUID = -6257702283581445170L;

	private String id;
	private String version;
	private String penModel;
	private String memo;
	private String status;
	private String downUrl;

	private Date createTime;
	private Date updateTime;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPenModel() {
		return penModel;
	}

	public void setPenModel(String penModel) {
		this.penModel = penModel;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDownUrl() {
		return downUrl;
	}

	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}
}
