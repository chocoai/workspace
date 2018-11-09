package com.yhcrt.healthcloud.system.entity;

import com.yhcrt.healthcloud.util.DictUtil;

public class Area {
    private Integer areaId;

    private Integer parentId;

    private String areaName;

    private String fullName;

    private String areaType;

    private String areaCode;

    private String areaDesc;

    private String region;

    private Integer orderNum;

    private Integer status;

    private String createTime;
    
    private Area parentArea;
    
	public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    public String getAreaType() {
        return areaType;
    }
    
    public String getAreaTypeView(){
    	return DictUtil.viewByCode(DictUtil.AREA_TYPE, getAreaType());
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType == null ? null : areaType.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getAreaDesc() {
        return areaDesc;
    }

    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc == null ? null : areaDesc.trim();
    }

    public String getRegion() {
        return region;
    }
    
    public String getRegionView() {
    	return DictUtil.viewByCode(DictUtil.REGION, getRegion());

    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Area getParentArea() {
		return parentArea;
	}

	public void setParentArea(Area parentArea) {
		this.parentArea = parentArea;
	}
}