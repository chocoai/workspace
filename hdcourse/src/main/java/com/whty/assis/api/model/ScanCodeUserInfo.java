package com.whty.assis.api.model;

import java.io.Serializable;

public class ScanCodeUserInfo implements Serializable {

	private static final long serialVersionUID = -7623071876052199667L;
	/*
	 * 总共13个字段
	 */
	private String guid;

	private String account;
	private String password;
	private String userPlatformCode;
	private String loginPlatformCode;

	private String ip;
	private String port;
	private String ssid;

	private String entry;
	private String loginStatus;
	private String boardEntry;

	private String updateTime;
	private String createTime;

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserPlatformCode() {
		return userPlatformCode;
	}

	public void setUserPlatformCode(String userPlatformCode) {
		this.userPlatformCode = userPlatformCode;
	}

	public String getLoginPlatformCode() {
		return loginPlatformCode;
	}

	public void setLoginPlatformCode(String loginPlatformCode) {
		this.loginPlatformCode = loginPlatformCode;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getBoardEntry() {
		return boardEntry;
	}

	public void setBoardEntry(String boardEntry) {
		this.boardEntry = boardEntry;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
