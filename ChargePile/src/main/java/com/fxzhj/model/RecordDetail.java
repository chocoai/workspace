package com.fxzhj.model;

import java.io.Serializable;
import java.util.Date;

public class RecordDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    
	private Long id;

    //充电记录表id
    private Long recordId;

    //设备id
    private Long deviceId;

    //端口号id
    private Long portId;

    //用户
    private String userAccount;

    //充电开始时间
    private Date startTime;

    //预计充电结束时间
    private Date endTime;

    //电压
    private Float voltage;

    //电流
    private Float electricity;

    //功率
    private Float power;

    //电量
    private Float kwh;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getPortId() {
        return portId;
    }

    public void setPortId(Long portId) {
        this.portId = portId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Float getVoltage() {
        return voltage;
    }

    public void setVoltage(Float voltage) {
        this.voltage = voltage;
    }

    public Float getElectricity() {
        return electricity;
    }

    public void setElectricity(Float electricity) {
        this.electricity = electricity;
    }

    public Float getPower() {
        return power;
    }

    public void setPower(Float power) {
        this.power = power;
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
        sb.append(", recordId=").append(recordId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", portId=").append(portId);
        sb.append(", userAccount=").append(userAccount);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", voltage=").append(voltage);
        sb.append(", electricity=").append(electricity);
        sb.append(", power=").append(power);
        sb.append(", kwh=").append(kwh);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}