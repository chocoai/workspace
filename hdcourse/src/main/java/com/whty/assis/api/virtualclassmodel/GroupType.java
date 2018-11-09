package com.whty.assis.api.virtualclassmodel;

import java.util.Date;

/**
 * 分组类型
 * 
 * @author zhangzheng
 *
 */
public class GroupType {

	private String typeId;
	private String classGroupClassId;
	private int sortNum;
	private String typeName;
	private Date createTime;
	private Date updateTime;

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getClassGroupClassId() {
		return classGroupClassId;
	}

	public void setClassGroupClassId(String classGroupClassId) {
		this.classGroupClassId = classGroupClassId;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
