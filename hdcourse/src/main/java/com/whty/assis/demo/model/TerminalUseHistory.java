package com.whty.assis.demo.model;

import java.io.Serializable;
import java.util.Date;

public class TerminalUseHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1597224312835902424L;

	private String terminalType;
	private Integer linkNum;
	private String userId;
	private String userName;
	private String orgId;
	private String orgName;
	private String platformCode;
	private Date createTime;
	private String courseNum;
	private String classId;
	private String hdktVersion;
	private String areaCode;
	private Integer year;
	private Integer month;
	private String id;

	private String classType;

	// 冗余字段
	// 1.电子书包 2.掌中黑板 3.学生数码笔 4.答题器
	private String dzsbCreateTime;
	private Integer dzsbLinkNum;

	// 掌中黑板
	private String zzhbCreateTime;
	private Integer zzhbLinkNum;

	// 学生数码比
	private String smbCreateTime;
	private Integer smbLinkNum;

	// 答题器
	private String dtqCreateTime;
	private Integer dtqLinkNum;

	private String countCreateTime;

	public Integer getDzsbLinkNum() {
		if (dzsbLinkNum == null) {
			return 0;
		} else {
			return dzsbLinkNum;
		}
	}

	public void setDzsbLinkNum(Integer dzsbLinkNum) {
		this.dzsbLinkNum = dzsbLinkNum;
	}

	public Integer getZzhbLinkNum() {
		if (zzhbLinkNum == null) {
			return 0;
		} else {
			return zzhbLinkNum;
		}
	}

	public void setZzhbLinkNum(Integer zzhbLinkNum) {
		this.zzhbLinkNum = zzhbLinkNum;
	}

	public Integer getSmbLinkNum() {
		if (smbLinkNum == null) {
			return 0;
		} else {
			return smbLinkNum;
		}
	}

	public void setSmbLinkNum(Integer smbLinkNum) {
		this.smbLinkNum = smbLinkNum;
	}

	public Integer getDtqLinkNum() {
		if (dtqLinkNum == null) {
			return 0;
		} else {
			return dtqLinkNum;
		}
	}

	public void setDtqLinkNum(Integer dtqLinkNum) {
		this.dtqLinkNum = dtqLinkNum;
	}

	public String getDzsbCreateTime() {
		return dzsbCreateTime;
	}

	public void setDzsbCreateTime(String dzsbCreateTime) {
		this.dzsbCreateTime = dzsbCreateTime;
	}

	public String getZzhbCreateTime() {
		return zzhbCreateTime;
	}

	public void setZzhbCreateTime(String zzhbCreateTime) {
		this.zzhbCreateTime = zzhbCreateTime;
	}

	public String getSmbCreateTime() {
		return smbCreateTime;
	}

	public void setSmbCreateTime(String smbCreateTime) {
		this.smbCreateTime = smbCreateTime;
	}

	public String getDtqCreateTime() {
		return dtqCreateTime;
	}

	public void setDtqCreateTime(String dtqCreateTime) {
		this.dtqCreateTime = dtqCreateTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}

	public Integer getLinkNum() {
		return linkNum;
	}

	public void setLinkNum(Integer linkNum) {
		this.linkNum = linkNum;
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

	public String getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getHdktVersion() {
		return hdktVersion;
	}

	public void setHdktVersion(String hdktVersion) {
		this.hdktVersion = hdktVersion;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getCountCreateTime() {
		if (this.getDtqCreateTime() != null) {
			countCreateTime = getDtqCreateTime();
		} else if (this.getZzhbCreateTime() != null) {
			countCreateTime = getZzhbCreateTime();
		} else if (this.getDzsbCreateTime() != null) {
			countCreateTime = getDzsbCreateTime();
		} else if (this.getSmbCreateTime() != null) {
			countCreateTime = getSmbCreateTime();
		}
		return countCreateTime;
	}

	public void setCountCreateTime(String countCreateTime) {
		this.countCreateTime = countCreateTime;
	}

}
