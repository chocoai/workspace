package com.whty.wfd.common.excel;

import java.io.Serializable;

public class TeacherUseLog implements Serializable {

	private static final long serialVersionUID = -5238726606124723919L;

	private String user_name;
	private String subject_name;
	private String org_name;
	private String use_taking;
	private String count;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}

	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

	public String getUse_taking() {
		return use_taking;
	}

	public void setUse_taking(String use_taking) {
		this.use_taking = use_taking;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}
