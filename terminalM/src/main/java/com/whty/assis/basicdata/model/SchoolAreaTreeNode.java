/**
 * 
 */
package com.whty.assis.basicdata.model;

import java.util.List;

/**
 * @author zhangzheng
 * @date 2018年4月17日
 */
public class SchoolAreaTreeNode {

	private String id;

	private String name;

	// 子节点
	private List<SchoolAreaTreeNode> subNodeList;

	public List<SchoolAreaTreeNode> getSubNodeList() {
		return subNodeList;
	}

	public void setSubNodeList(List<SchoolAreaTreeNode> subNodeList) {
		this.subNodeList = subNodeList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
