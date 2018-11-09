package com.yhcrt.healthcloud.health.entity;

public class BloodGlucoseType {
    private Integer cid;

    private Integer bgType;

    private String typeValue;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getBgType() {
        return bgType;
    }

    public void setBgType(Integer bgType) {
        this.bgType = bgType;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue == null ? null : typeValue.trim();
    }
}