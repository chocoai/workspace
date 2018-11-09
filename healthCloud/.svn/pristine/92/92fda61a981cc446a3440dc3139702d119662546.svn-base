package com.yhcrt.healthcloud.system.entity;

import com.yhcrt.healthcloud.util.DictUtil;

public class SysRes {
	
	private Integer resId;

	private String resName;

	private String resCode;
	
	private String permission;

	private String parentResId;

	private String resUrl;

	private String resType;

	private Integer orderNum;

	private Integer isGlobal;

	private String createTime;

	private String createUser;

	private Integer status;

	private String remark;
	
	private SysRes parentSysRes;
	
	private Integer icon;    //二期新增，是否显示icon

	public Integer getResId() {
		return resId;
	}

	public void setResId(Integer resId) {
		this.resId = resId;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName == null ? null : resName.trim();
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode == null ? null : resCode.trim();
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getParentResId() {
		return parentResId;
	}

	public void setParentResId(String parentResId) {
		this.parentResId = parentResId == null ? null : parentResId.trim();
	}

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl == null ? null : resUrl.trim();
	}

	public String getResType() {
		return resType;
	}
	
	public String getResTypeView() {
		return DictUtil.viewByCode(DictUtil.RES_TYPE, getResType());
	}

	public void setResType(String resType) {
		this.resType = resType == null ? null : resType.trim();
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getIsGlobal() {
		return isGlobal;
	}
	
	public String getIsGlobalView() {
		return DictUtil.viewByCode(DictUtil.IS_GLOBAL, getIsGlobal().toString());
	}

	public void setIsGlobal(Integer isGlobal) {
		this.isGlobal = isGlobal;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime == null ? null : createTime.trim();
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser == null ? null : createUser.trim();
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
	
	public SysRes getParentSysRes() {
		return parentSysRes;
	}

	public void setParentSysRes(SysRes parentSysRes) {
		this.parentSysRes = parentSysRes;
	}

	public Integer getIcon() {
		return icon;
	}

	public void setIcon(Integer icon) {
		this.icon = icon;
	}

}