package com.yhcrt.healthcloud.security.entity;

import com.yhcrt.healthcloud.memberBack.entity.MemberDeviceBack;

public class Location {
    private Integer locationId;

    private String imei;

    private String longitude;

    private String latitude;

    private String addr;

    private String locationTime;
    
    private String name;
    
    private MemberDeviceBack memberDevice;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public MemberDeviceBack getMemberDevice() {
		return memberDevice;
	}

	public void setMemberDevice(MemberDeviceBack memberDevice) {
		this.memberDevice = memberDevice;
	}

	public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getLocationTime() {
        return locationTime;
    }

    public void setLocationTime(String locationTime) {
        this.locationTime = locationTime == null ? null : locationTime.trim();
    }
}