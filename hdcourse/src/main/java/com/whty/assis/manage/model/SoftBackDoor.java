/**
 * 
 */
package com.whty.assis.manage.model;

import java.util.Date;

/**
 * @author zhangzheng
 * @date 2018年9月14日
 */
public class SoftBackDoor {

	private Integer id;
	private String versionCode;
	private Integer softType;
	private String downUrl;
	private String md5;
	private Date createTime;
	private Date updateTime;
	private String status;
	private String scope;

	private String cbt;
	private String zipCodeVersion;

	private String filePath;

	private String bosStatus;

	public String getBosStatus() {
		return bosStatus;
	}

	public void setBosStatus(String bosStatus) {
		this.bosStatus = bosStatus;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getCbt() {
		return cbt;
	}

	public void setCbt(String cbt) {
		this.cbt = cbt;
	}

	public String getZipCodeVersion() {
		return zipCodeVersion;
	}

	public void setZipCodeVersion(String zipCodeVersion) {
		this.zipCodeVersion = zipCodeVersion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public Integer getSoftType() {
		return softType;
	}

	public void setSoftType(Integer softType) {
		this.softType = softType;
	}

	public String getDownUrl() {
		return downUrl;
	}

	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

}
