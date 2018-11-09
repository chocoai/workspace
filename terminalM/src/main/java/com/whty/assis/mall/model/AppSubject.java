/**
 * 
 */
package com.whty.assis.mall.model;

import com.whty.assis.base.model.BaseModel;

/**
 * @author zhangzheng
 * @date 2018年4月18日
 */
public class AppSubject extends BaseModel {

	private static final long serialVersionUID = 1323827278442591305L;

	private Integer appId;
	
	private Integer subjectId;

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

}
