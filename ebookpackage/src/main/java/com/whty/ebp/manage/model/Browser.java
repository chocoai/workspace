package com.whty.ebp.manage.model;

import java.io.Serializable;
import java.util.Date;

public class Browser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String address;
	private String logo;
	private String groupId;
	private String groupName;
	private Date updateTime;
	private String version;
	private String memo;
	
	private String flatModelIds;
	private String flat_modelCodes;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getFlatModelIds() {
		return flatModelIds;
	}
	public void setFlatModelIds(String flatModelIds) {
		this.flatModelIds = flatModelIds;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getFlat_modelCodes() {
		return flat_modelCodes;
	}
	public void setFlat_modelCodes(String flat_modelCodes) {
		this.flat_modelCodes = flat_modelCodes;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}
