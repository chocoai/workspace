package com.whty.util;

import java.io.Serializable;
import java.util.Map;

/**
 * \* User: zjd
 * \* Date: 2018/7/6
 * \* Time: 14:13
 * \* Description:
 * \
 */
public class DeviceStatusListener implements Serializable {

    private static final long serialVersionUID = 3052315625300855402L;
    private String deviceType;
    private String iotId;
    private String action;
    private String productKey;
    private String gmtCreate;
    private String deviceName;
    private Map<String, Object> status;

    @Override
    public String toString() {
        return "DeviceStatusListener{" +
                "deviceType='" + deviceType + '\'' +
                ", iotId='" + iotId + '\'' +
                ", action='" + action + '\'' +
                ", productKey='" + productKey + '\'' +
                ", gmtCreate='" + gmtCreate + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", status=" + status +
                '}';
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getIotId() {
        return iotId;
    }

    public void setIotId(String iotId) {
        this.iotId = iotId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Map<String, Object> getStatus() {
        return status;
    }

    public void setStatus(Map<String, Object> status) {
        this.status = status;
    }
}