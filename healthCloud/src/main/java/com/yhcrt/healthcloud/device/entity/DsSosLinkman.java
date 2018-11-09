package com.yhcrt.healthcloud.device.entity;

public class DsSosLinkman {
    private Integer cid;

    private String imei;

    private String lankman;

    private String phoneNum;

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

    public String getLankman() {
        return lankman;
    }

    public void setLankman(String lankman) {
        this.lankman = lankman == null ? null : lankman.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }
}