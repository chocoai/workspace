package com.whty.assis.manage.model;

import java.util.Date;

import com.whty.assis.base.model.BaseModel;

public class PlatformInfo extends BaseModel {

	private static final long serialVersionUID = 3657585747094643155L;

	private String platformCode;
	private String platformName;
	private Date updateTime;

	public String getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
