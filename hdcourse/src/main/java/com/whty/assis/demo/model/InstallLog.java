package com.whty.assis.demo.model;

import java.io.Serializable;
import java.util.Date;

import com.whty.common.util.CommonFunction;

public class InstallLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String area;
	private String school;
	private String className;
	private String cpu;
	private String terminalVersion;
	private String memery;
	private String disk;
	private String mac;
	private String remarks;
	private Date createTime;
	private String version;
	private String areaCode;
	private String schoolCode;
	private String classCode;
	private String userName;
	private String userId;
	private String freedisk;
	private String createTimeStr;

	public String getCreateTimeStr() {
		createTimeStr = CommonFunction.getDateSampleString(createTime, CommonFunction.DEFAULT_TIME_SAMPLE_FORMAT);
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getTerminalVersion() {
		return terminalVersion;
	}

	public void setTerminalVersion(String terminalVersion) {
		this.terminalVersion = terminalVersion;
	}

	public String getMemery() {
		return memery;
	}

	public void setMemery(String memery) {
		this.memery = memery;
	}

	public String getDisk() {
		return disk;
	}

	public void setDisk(String disk) {
		this.disk = disk;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getSchoolCode() {
		return schoolCode;
	}

	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFreedisk() {
		return freedisk;
	}

	public void setFreedisk(String freedisk) {
		this.freedisk = freedisk;
	}

}
