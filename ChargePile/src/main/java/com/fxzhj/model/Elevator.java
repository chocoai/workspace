package com.fxzhj.model;

import java.io.Serializable;
import java.util.Date;

public class Elevator implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;

    //电梯名称
    private String eleName;

    //绑定二维码
    private String regCode;

    //公司名称
    private String companyName;

    //小区id
    private Integer communityId;

    //电梯品牌
    private String eleBrand;

    private String nodeId;

    private String filed3;

    private Date createTime;
    private Integer createrUser;
    private String createTimeStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEleName() {
        return eleName;
    }

    public void setEleName(String eleName) {
        this.eleName = eleName == null ? null : eleName.trim();
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode == null ? null : regCode.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getEleBrand() {
        return eleBrand;
    }

    public void setEleBrand(String eleBrand) {
        this.eleBrand = eleBrand == null ? null : eleBrand.trim();
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId == null ? null : nodeId.trim();
    }

    public String getFiled3() {
        return filed3;
    }

    public void setFiled3(String filed3) {
        this.filed3 = filed3 == null ? null : filed3.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreaterUser() {
        return createrUser;
    }

    public void setCreaterUser(Integer createrUser) {
        this.createrUser = createrUser;
    }

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

}