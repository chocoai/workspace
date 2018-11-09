package com.whty.assis.api.virtualclassmodel;

import java.io.Serializable;
import java.util.Date;

public class Teacher implements Serializable {

	private String id;
	private String userId;
	private String name;
	private String platformCode;
	private String type;
	private String schoolId;
	private String schoolName;
	private boolean cacheLoaded;
	private boolean cloudLoaded;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
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

	// public List<ClassModel> getClassList() {
	// return classList;
	// }
	//
	// public void setClassList(List<ClassModel> classList) {
	// this.classList = classList;
	// }

	public boolean isCloudLoaded() {
		return cloudLoaded;
	}

	public boolean isCacheLoaded() {
		return cacheLoaded;
	}

	public void setCacheLoaded(boolean cacheLoaded) {
		this.cacheLoaded = cacheLoaded;
	}

	public void setCloudLoaded(boolean cloudLoaded) {
		this.cloudLoaded = cloudLoaded;
	}

}
