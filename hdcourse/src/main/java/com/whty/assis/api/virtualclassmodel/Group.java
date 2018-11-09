package com.whty.assis.api.virtualclassmodel;

public class Group {

	private String groupId;// 分组id
	private String classGroupTypeId;// 分组类型id
	private String groupName;
	private int sortNum;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getClassGroupTypeId() {
		return classGroupTypeId;
	}

	public void setClassGroupTypeId(String classGroupTypeId) {
		this.classGroupTypeId = classGroupTypeId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

}
