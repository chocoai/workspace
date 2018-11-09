package com.yhcrt.healthcloud.health.entity;

public class HdSleep {
    private Integer cid;

    private String imei;

    private Integer memberId;

    private Double deepSleepDuration;

    private String dataDate;

    private String uploadTime;

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

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Double getDeepSleepDuration() {
        return deepSleepDuration;
    }

    public void setDeepSleepDuration(Double deepSleepDuration) {
        this.deepSleepDuration = deepSleepDuration;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate == null ? null : dataDate.trim();
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime == null ? null : uploadTime.trim();
    }
}