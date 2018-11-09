package com.whty.assis.api.model;

import java.io.Serializable;

public class TUserBackground implements Serializable {

	private static final long serialVersionUID = 7581645743294655770L;

	private String backgroundCode;
	private String platformCode;
	private String loginPlatformCode;
	private String userId;
	private String createTime;
	private String updateTime;

	public String getBackgroundCode() {
		return backgroundCode;
	}

	public void setBackgroundCode(String backgroundCode) {
		this.backgroundCode = backgroundCode;
	}

	public String getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

	public String getLoginPlatformCode() {
		return loginPlatformCode;
	}

	public void setLoginPlatformCode(String loginPlatformCode) {
		this.loginPlatformCode = loginPlatformCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}
