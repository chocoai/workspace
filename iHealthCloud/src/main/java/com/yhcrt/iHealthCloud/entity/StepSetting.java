package com.yhcrt.iHealthCloud.entity;

public class StepSetting {
    private Integer cid;

    private Integer memberId;

    private Integer targetStep;

    private String updateTime;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getTargetStep() {
        return targetStep;
    }

    public void setTargetStep(Integer targetStep) {
        this.targetStep = targetStep;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
}