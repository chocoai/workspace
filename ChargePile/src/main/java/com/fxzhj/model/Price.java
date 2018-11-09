package com.fxzhj.model;

import java.io.Serializable;

public class Price implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Long id;

    //区域id
    private Integer areaId;
    
    //具体小区id
    private Integer communityId;

    //分钟数
    private Integer mins;

    //费用
    private Float price;

    //计费类型（先付费，充完付费）
    private Integer type;
    
    //电价状态
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getCommunityId() {
		return communityId;
	}

	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}

	public Integer getMins() {
        return mins;
    }

    public void setMins(Integer mins) {
        this.mins = mins;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}