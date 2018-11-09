package com.whty.assis.demo.model;

import java.io.Serializable;
import java.util.Date;

import com.whty.common.util.CommonFunction;

public class OrgMonthActivityCount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4260276726820460268L;

	private String orgId;
	private String orgName;
	private String rate;
	private Date createTime;
	private Date updateTime;
	private Integer previousLoginTaking;
	private Integer year;
	private Integer month;
	private String areaCode;
	private String platformCode;
	private Integer loginTaking;
	private Integer classRanking;
	private String orgPercent;
	private Integer classLoginTakingCount;
	private String classId;
	private String className;
	private String platformName;
	private String areaName;
	private String loginTakingStr;
	private String previousLoginTakingStr;

	public void setLoginTakingStr(String loginTakingStr) {
		this.loginTakingStr = loginTakingStr;
	}

	public void setPreviousLoginTakingStr(String previousLoginTakingStr) {
		this.previousLoginTakingStr = previousLoginTakingStr;
	}

	public String getPreviousLoginTakingStr() {
		if (previousLoginTaking != null) {
			return CommonFunction.formatDuring(Long.valueOf(previousLoginTaking));
		} else {
			return null;
		}
	}

	public String getLoginTakingStr() {
		if (loginTaking != null) {
			return CommonFunction.formatDuring(Long.valueOf(loginTaking));
		} else {
			return null;
		}
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

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Integer getClassLoginTakingCount() {
		return classLoginTakingCount;
	}

	public void setClassLoginTakingCount(Integer classLoginTakingCount) {
		this.classLoginTakingCount = classLoginTakingCount;
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

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
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

	public Integer getPreviousLoginTaking() {
		return previousLoginTaking;
	}

	public void setPreviousLoginTaking(Integer previousLoginTaking) {
		this.previousLoginTaking = previousLoginTaking;
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

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

	public Integer getLoginTaking() {
		return loginTaking;
	}

	public void setLoginTaking(Integer loginTaking) {
		this.loginTaking = loginTaking;
	}

	public Integer getClassRanking() {
		return classRanking;
	}

	public void setClassRanking(Integer classRanking) {
		this.classRanking = classRanking;
	}

	public String getOrgPercent() {
		return orgPercent;
	}

	public void setOrgPercent(String orgPercent) {
		this.orgPercent = orgPercent;
	}

}