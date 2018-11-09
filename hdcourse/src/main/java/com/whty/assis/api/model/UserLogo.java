package com.whty.assis.api.model;

public class UserLogo {

	private String logotype; // 头像类型 1：小 2：中 3：大
	private String logourl; // 头像相对链接地址

	public String getLogotype() {
		return logotype;
	}

	public void setLogotype(String logotype) {
		this.logotype = logotype;
	}

	public String getLogourl() {
		return logourl;
	}

	public void setLogourl(String logourl) {
		this.logourl = logourl;
	}

}
