package com.fxzhj.model;

import java.io.Serializable;
import java.util.Date;

public class Node implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private Long id;

    //节点id
    private String nodeId;
    
    //节点名称
    private String nodeName;

    //父节点id
    private String parentId;

    //节点属性id
    private Long propertyId;

    //是否叶节点(0:否 1:是)
    private Integer isNode;
    
    //节点状态(0:空闲  1:绑定)
    private Integer nodeStatus;
    
    //节点分组id
    private Long groupId;

    //补充字段
    private String field1;
    private String field2;
    private String field3;

    private Date createTime;
    private Integer createUser;
    private Date updateTime;
    private Integer updateUser;
    //时间转化为字符串
    private String createTimeStr;	
    private String updateTimeStr;

    
    private String nodeUrl;	//节点路径
    private String propertyName;	//属性名称
    private Integer propertyType;	//属性类型
    private Integer propertyStatus;	//属性状态
    private String groupName;	//分组名称
    private String descpt;	//分组描述
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Long getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(Long propertyId) {
		this.propertyId = propertyId;
	}
	public Integer getIsNode() {
		return isNode;
	}
	public void setIsNode(Integer isNode) {
		this.isNode = isNode;
	}
	public Integer getNodeStatus() {
		return nodeStatus;
	}
	public void setNodeStatus(Integer nodeStatus) {
		this.nodeStatus = nodeStatus;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public String getField1() {
		return field1;
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}
	public String getField3() {
		return field3;
	}
	public void setField3(String field3) {
		this.field3 = field3;
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
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public String getUpdateTimeStr() {
		return updateTimeStr;
	}
	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}
	public String getNodeUrl() {
		return nodeUrl;
	}
	public void setNodeUrl(String nodeUrl) {
		this.nodeUrl = nodeUrl;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public Integer getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(Integer propertyType) {
		this.propertyType = propertyType;
	}
	public Integer getPropertyStatus() {
		return propertyStatus;
	}
	public void setPropertyStatus(Integer propertyStatus) {
		this.propertyStatus = propertyStatus;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getDescpt() {
		return descpt;
	}
	public void setDescpt(String descpt) {
		this.descpt = descpt;
	}
    
}