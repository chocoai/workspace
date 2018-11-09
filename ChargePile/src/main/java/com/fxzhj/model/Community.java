package com.fxzhj.model;

import java.io.Serializable;

public class Community implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Integer communityId;

    private Integer areaId;

    private Integer districtId;

    private String name;

    private String address;

    private Byte communityType;

    private Byte status;

    private Byte allowRegister;

    private Integer sort;

    private Integer createTime;

    private Integer emptyHouse;

    private Byte taichuanIsdoor;

    private Integer introImg;

    private String longitude;

    private String latitude;

    private String intro;

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Byte getCommunityType() {
        return communityType;
    }

    public void setCommunityType(Byte communityType) {
        this.communityType = communityType;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getAllowRegister() {
        return allowRegister;
    }

    public void setAllowRegister(Byte allowRegister) {
        this.allowRegister = allowRegister;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Integer getEmptyHouse() {
        return emptyHouse;
    }

    public void setEmptyHouse(Integer emptyHouse) {
        this.emptyHouse = emptyHouse;
    }

    public Byte getTaichuanIsdoor() {
        return taichuanIsdoor;
    }

    public void setTaichuanIsdoor(Byte taichuanIsdoor) {
        this.taichuanIsdoor = taichuanIsdoor;
    }

    public Integer getIntroImg() {
        return introImg;
    }

    public void setIntroImg(Integer introImg) {
        this.introImg = introImg;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", communityId=").append(communityId);
        sb.append(", areaId=").append(areaId);
        sb.append(", districtId=").append(districtId);
        sb.append(", name=").append(name);
        sb.append(", address=").append(address);
        sb.append(", communityType=").append(communityType);
        sb.append(", status=").append(status);
        sb.append(", allowRegister=").append(allowRegister);
        sb.append(", sort=").append(sort);
        sb.append(", createTime=").append(createTime);
        sb.append(", emptyHouse=").append(emptyHouse);
        sb.append(", taichuanIsdoor=").append(taichuanIsdoor);
        sb.append(", introImg=").append(introImg);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", intro=").append(intro);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}