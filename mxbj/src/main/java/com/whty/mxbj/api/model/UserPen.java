package com.whty.mxbj.api.model;

/**
 * 登录查询penMac
 * 
 * 涉及t_user与t_pen笔，创建UserPen实体类与查询结果进行映射， 由于没有真实表，所以不需要序列化
 */

public class UserPen {
	/*
	 * User用户信息字段
	 */

	// 手机号
	private String userId;

	/*
	 * pen信息字段
	 */

	private String mac;
	private String status;
	private String userPlatformCode;

	public String getUserPlatformCode() {
		return userPlatformCode;
	}

	public void setUserPlatformCode(String userPlatformCode) {
		this.userPlatformCode = userPlatformCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

}
