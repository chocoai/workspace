package com.whty.ebp.api.model;

import java.io.Serializable;

public class OutSideNoteData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6374704589636830607L;

	private String userId;
	private String id;
	private String userName;
	private String platformCode;
	private String orgId;
	private String orgName;
	private String loginPlatformCode;
	private String dataFileUrl;

	public String getDataFileUrl() {
		return dataFileUrl;
	}

	public void setDataFileUrl(String dataFileUrl) {
		this.dataFileUrl = dataFileUrl;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getLoginPlatformCode() {
		return loginPlatformCode;
	}

	public void setLoginPlatformCode(String loginPlatformCode) {
		this.loginPlatformCode = loginPlatformCode;
	}

}
