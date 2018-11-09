package com.fxzhj.model;

import java.io.Serializable;

public class RangeDevice implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Long id;

    //设备id
    private Long deviceId;

    //投放站点名称
    private String siteName;
    
    //物业电话
    private String telPhone;
    
    //经, 纬度
    private String lng;
    private String lat;
    
    //起始~终止时间段
    private String startTime;
    private String endTime;
    
    //接受前台传入字段
    private String ids;	//设备id拼接字段
    private String deep;	//根据deep判断保存区域
    private Integer communityId;	//区域号
    
    public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getDeep() {
		return deep;
	}

	public void setDeep(String deep) {
		this.deep = deep;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getCommunityId() {
		return communityId;
	}

	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}