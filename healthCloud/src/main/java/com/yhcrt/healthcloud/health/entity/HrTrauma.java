package com.yhcrt.healthcloud.health.entity;

public class HrTrauma {
    private Integer cId;

    private Integer recordId;

    private String traumaName;

    private String traumaTime;

    private Integer status;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public String getTraumaName() {
        return traumaName;
    }

    public void setTraumaName(String traumaName) {
        this.traumaName = traumaName == null ? null : traumaName.trim();
    }

    public String getTraumaTime() {
        return traumaTime;
    }

    public void setTraumaTime(String traumaTime) {
        this.traumaTime = traumaTime == null ? null : traumaTime.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}