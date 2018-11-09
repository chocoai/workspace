package com.yhcrt.iHealthCloud.entity;

public class ServicePrice {
    private Integer cid;

    private Integer serviceId;

    private Double price;

    private Double oriPrice;

    private String level;

    private String unit;

    private Integer type;

    private Integer serviceNum;

    private Integer serviceUnit;

    private Integer status;

    private String desct;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getOriPrice() {
        return oriPrice;
    }

    public void setOriPrice(Double oriPrice) {
        this.oriPrice = oriPrice;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(Integer serviceNum) {
        this.serviceNum = serviceNum;
    }

    public Integer getServiceUnit() {
        return serviceUnit;
    }

    public void setServiceUnit(Integer serviceUnit) {
        this.serviceUnit = serviceUnit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesct() {
        return desct;
    }

    public void setDesct(String desct) {
        this.desct = desct == null ? null : desct.trim();
    }
}