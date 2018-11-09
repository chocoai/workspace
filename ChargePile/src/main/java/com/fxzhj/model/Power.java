package com.fxzhj.model;

import java.io.Serializable;

public class Power implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Long id;

    //设备id
    private Long deviceId;

    //创建时间
    private String endDate;

    //当天用电量
    private Float todayKwh;

    //总用电量
    private Float kwh;
    
    //界面显示字段
    private String name;	//设备名称
    private String deviceNo;	//设备编号
    private Integer status;	//设备状态
    private String siteName;	//投放点名称
    //设备所属区域
    private String commName;
    private String areaName;

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getCommName() {
		return commName;
	}

	public void setCommName(String commName) {
		this.commName = commName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    public Float getTodayKwh() {
        return todayKwh;
    }

    public void setTodayKwh(Float todayKwh) {
        this.todayKwh = todayKwh;
    }

    public Float getKwh() {
        return kwh;
    }

    public void setKwh(Float kwh) {
        this.kwh = kwh;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", endDate=").append(endDate);
        sb.append(", todayKwh=").append(todayKwh);
        sb.append(", kwh=").append(kwh);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}