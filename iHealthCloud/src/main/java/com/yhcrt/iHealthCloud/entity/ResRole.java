package com.yhcrt.iHealthCloud.entity;

public class ResRole extends ResRoleKey {
    private String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}