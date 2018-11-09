package com.fxzhj.model;

import java.io.Serializable;

public class Area implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Integer id;

    //记录父节点id
    private Integer parentId;

    //省市区名称
    private String name;

    private Byte sort;

    private Byte deep;

    private String region;

    //默认全部启用
    private Integer areaStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getSort() {
        return sort;
    }

    public void setSort(Byte sort) {
        this.sort = sort;
    }

    public Byte getDeep() {
        return deep;
    }

    public void setDeep(Byte deep) {
        this.deep = deep;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public Integer getAreaStatus() {
        return areaStatus;
    }

    public void setAreaStatus(Integer areaStatus) {
        this.areaStatus = areaStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", name=").append(name);
        sb.append(", sort=").append(sort);
        sb.append(", deep=").append(deep);
        sb.append(", region=").append(region);
        sb.append(", areaStatus=").append(areaStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}