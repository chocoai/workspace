/**
 * 
 */
package com.whty.mxbj.api.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhangzheng
 * @date 2018年7月24日
 */
public class LessonTree implements Serializable {

	private static final long serialVersionUID = -6269819787456750820L;

	private String id;
	private String text;
	private Integer sortNumber;
	private boolean isLession;
	List<LessonTree> subTree;

	public boolean isLession() {
		return isLession;
	}

	public void setLession(boolean isLession) {
		this.isLession = isLession;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}

	public List<LessonTree> getSubTree() {
		return subTree;
	}

	public void setSubTree(List<LessonTree> subTree) {
		this.subTree = subTree;
	}

}
