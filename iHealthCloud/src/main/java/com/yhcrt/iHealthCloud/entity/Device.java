package com.yhcrt.iHealthCloud.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class Device {
	
	@JSONField(serialize = false)
	private Integer deviceId;

	private String deviceName;

	private String imei;
	
	@JSONField(serialize = false)
	private Integer orgId;

	private String deviceCategory;

	private String deviceType;

	private String sim;

	private Integer isUse;
	
	@JSONField(serialize = false)
	private Integer orderNum;
	
	@JSONField(serialize = false)
	private Integer createUser;
	
	@JSONField(serialize = false)
	private String createTime;
	
	@JSONField(serialize = false)
	private String updateTime;
	
	@JSONField(serialize = false)
	private Integer status;
	
	@JSONField(serialize = false)
	private String remark;

	@JSONField(serialize = false)
	private Organization org;

	private String orgName;

	public String getOrgName() {
		orgName = getOrg() != null ? getOrg().getOrgName() : "";
		return orgName;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName == null ? null : deviceName.trim();
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei == null ? null : imei.trim();
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getDeviceCategory() {
		return deviceCategory;
	}

	public void setDeviceCategory(String deviceCategory) {
		this.deviceCategory = deviceCategory == null ? null : deviceCategory.trim();
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType == null ? null : deviceType.trim();
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim == null ? null : sim.trim();
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime == null ? null : createTime.trim();
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime == null ? null : updateTime.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

}