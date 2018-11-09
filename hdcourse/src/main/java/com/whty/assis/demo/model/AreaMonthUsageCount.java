package com.whty.assis.demo.model;

import java.io.Serializable;
import java.util.Date;

public class AreaMonthUsageCount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3946868332530381552L;

	private String orgId;
	private String orgName;

	private Integer orgUseRanking;// 排行
	private String areaPercent;// 使用占区域百分比

	private String areaCode;
	private String areaName;
	private Integer useCount;
	private Integer year;
	private Integer month;
	private Date createTime;
	private Date updateTime;
	private String rate;
	private Integer previousUseCount;

	private String platformCode;
	private String platformName;

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

	public Integer getOrgUseRanking() {
		return orgUseRanking;
	}

	public void setOrgUseRanking(Integer orgUseRanking) {
		this.orgUseRanking = orgUseRanking;
	}

	public String getAreaPercent() {
		return areaPercent;
	}

	public void setAreaPercent(String areaPercent) {
		this.areaPercent = areaPercent;
	}

	public String getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
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

	public Integer getPreviousUseCount() {
		return previousUseCount;
	}

	public void setPreviousUseCount(Integer previousUseCount) {
		this.previousUseCount = previousUseCount;
	}

}
