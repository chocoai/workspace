package com.fxzhj.model;

import java.io.Serializable;
import java.util.Date;

public class NodeCode implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id;

    //绑定二维码路径
    private String nodeUrl;

    //二维码id
    private Long codeId;

    //节点描述id
    private Long describeId;

    //绑定类型
    private Integer type;

    private String field2;

    private String field3;

    private Date createTime;
    private Integer createUser;
    private Date updateTime;
    private Integer updateUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNodeUrl() {
        return nodeUrl;
    }

    public void setNodeUrl(String nodeUrl) {
        this.nodeUrl = nodeUrl == null ? null : nodeUrl.trim();
    }

    public Long getCodeId() {
        return codeId;
    }

    public void setCodeId(Long codeId) {
        this.codeId = codeId;
    }

    public Long getDescribeId() {
        return describeId;
    }

    public void setDescribeId(Long describeId) {
        this.describeId = describeId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2 == null ? null : field2.trim();
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3 == null ? null : field3.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

}