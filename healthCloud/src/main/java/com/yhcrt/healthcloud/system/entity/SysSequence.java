package com.yhcrt.healthcloud.system.entity;

public class SysSequence {
    private String cid;

    private String sequName;

    private String sequDesc;

    private Integer sequValue;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getSequName() {
        return sequName;
    }

    public void setSequName(String sequName) {
        this.sequName = sequName == null ? null : sequName.trim();
    }

    public String getSequDesc() {
        return sequDesc;
    }

    public void setSequDesc(String sequDesc) {
        this.sequDesc = sequDesc == null ? null : sequDesc.trim();
    }

    public Integer getSequValue() {
        return sequValue;
    }

    public void setSequValue(Integer sequValue) {
        this.sequValue = sequValue;
    }
}