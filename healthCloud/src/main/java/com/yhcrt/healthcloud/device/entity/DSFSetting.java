package com.yhcrt.healthcloud.device.entity;

public class DSFSetting {
    private Integer cid;

    private String imei;

    private Integer dataType;

    private Integer sampleFrequency;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getSampleFrequency() {
        return sampleFrequency;
    }

    public void setSampleFrequency(Integer sampleFrequency) {
        this.sampleFrequency = sampleFrequency;
    }
}