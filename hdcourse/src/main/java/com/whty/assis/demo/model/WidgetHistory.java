package com.whty.assis.demo.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 控件使用历史
 * 
 * @author zhangzheng
 *
 */
public class WidgetHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1273879565094708651L;

	private String id;
	private Integer useCount;
	private Integer userCount;
	private String userId;
	private String orgId;
	private String platformCode;
	private String machineInfo;
	private String widgetId;
	private String widgetName;
	private String memory;
	private String classId;
	private String cpuInfo;
	private Date createTime;
	private String operationSystemInfo;
	private String operationSystemType;
	private String classType;
	private String terminalVersion;
	private String courseNum;
	private String orgName;
	private String userName;
	private String areaCode;
	private Integer year;
	private Integer month;
	private String previousUseCount;// 上月使用次数

	private String loginSource;
	private Integer loginTaking;

	private String rate;// 增长率

	private String className;
	private String eventId;
	private String eventName;

	private String description;

	private String areaName;

	public String getLoginSource() {
		return loginSource;
	}

	public void setLoginSource(String loginSource) {
		this.loginSource = loginSource;
	}

	public Integer getLoginTaking() {
		return loginTaking;
	}

	public void setLoginTaking(Integer loginTaking) {
		this.loginTaking = loginTaking;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getPreviousUseCount() {
		return previousUseCount;
	}

	public void setPreviousUseCount(String previousUseCount) {
		this.previousUseCount = previousUseCount;
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}

	public String getTerminalVersion() {
		return terminalVersion;
	}

	public void setTerminalVersion(String terminalVersion) {
		this.terminalVersion = terminalVersion;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getUseCount() {
		return useCount;
	}

	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getPlatformCode() {
		return platformCode;
	}

	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}

	public String getMachineInfo() {
		return machineInfo;
	}

	public void setMachineInfo(String machineInfo) {
		this.machineInfo = machineInfo;
	}

	public String getWidgetId() {
		return widgetId;
	}

	public void setWidgetId(String widgetId) {
		this.widgetId = widgetId;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getCpuInfo() {
		return cpuInfo;
	}

	public void setCpuInfo(String cpuInfo) {
		this.cpuInfo = cpuInfo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getOperationSystemInfo() {
		return operationSystemInfo;
	}

	public void setOperationSystemInfo(String operationSystemInfo) {
		this.operationSystemInfo = operationSystemInfo;
	}

	public String getOperationSystemType() {
		return operationSystemType;
	}

	public void setOperationSystemType(String operationSystemType) {
		this.operationSystemType = operationSystemType;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getWidgetName() {
		return widgetName;
	}

	public void setWidgetName(String widgetName) {
		this.widgetName = widgetName;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

}
