package com.yhcrt.iHealthCloud.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class HrTrauma {
	
	@JSONField(serialize = false)
    private Integer cId;
	
	@JSONField(serialize = false)
    private Integer recordId;

    private String traumaName;

    private String traumaTime;

    @JSONField(serialize = false)
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