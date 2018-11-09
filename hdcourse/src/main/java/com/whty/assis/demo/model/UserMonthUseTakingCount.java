package com.whty.assis.demo.model;

import java.io.Serializable;
import java.util.Date;

public class UserMonthUseTakingCount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7817288040764941715L;

	private String areaCode;
	private Integer year;
	private Integer month;
	private Date createTime;
	private Date updateTime;
	private String rate;
	private long previousUseTaking;
	private long useTaking;
	private String userId;
	private String userName;
	private String orgId;
	private String orgName;
	private String platformCode;
	private String platformName;
	private String useTakingStr;
	private String previousUseTakingStr;
	private String areaName;
	private Integer useCount;

	public Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getUseTakingStr() {
		return useTakingStr;
	}

	public void setUseTakingStr(String useTakingStr) {
		this.useTakingStr = useTakingStr;
	}

	public String getPreviousUseTakingStr() {
		return previousUseTakingStr;
	}

	public void setPreviousUseTakingStr(String previousUseTakingStr) {
		this.previousUseTakingStr = previousUseTakingStr;
	}

	public String getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
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

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public long getPreviousUseTaking() {
		return previousUseTaking;
	}

	public void setPreviousUseTaking(long previousUseTaking) {
		this.previousUseTaking = previousUseTaking;
	}

	public long getUseTaking() {
		return useTaking;
	}

	public void setUseTaking(long useTaking) {
		this.useTaking = useTaking;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

}
