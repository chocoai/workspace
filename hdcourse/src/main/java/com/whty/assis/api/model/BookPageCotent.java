package com.whty.assis.api.model;

import java.util.List;

public class BookPageCotent {
	private String userid;
	private String name;
	private String type;
	private String schoolId;
	private String schoolName;

	private List<BookPageTeach> classlist;

	public List<BookPageTeach> getClasslist() {
		return classlist;
	}

	public void setClasslist(List<BookPageTeach> classlist) {
		this.classlist = classlist;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

}
