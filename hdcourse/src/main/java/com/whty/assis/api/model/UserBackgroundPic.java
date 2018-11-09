package com.whty.assis.api.model;

import java.io.Serializable;

public class UserBackgroundPic implements Serializable {

	private static final long serialVersionUID = 6731620534115975633L;
	private String resourceId;
	private String userBackgroundCode;
	private String md5;
	private String resourceName;
	private String downUrl;
	private String createTime;
	private Integer streach;
	private Integer sortNum;

	private String thumbnail;

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Integer getStreach() {
		return streach;
	}

	public void setStreach(Integer streach) {
		this.streach = streach;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getUserBackgroundCode() {
		return userBackgroundCode;
	}

	public void setUserBackgroundCode(String userBackgroundCode) {
		this.userBackgroundCode = userBackgroundCode;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getDownUrl() {
		return downUrl;
	}

	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
