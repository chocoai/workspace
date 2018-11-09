/**
 * 
 */
package com.whty.assis.mall.model;

import com.whty.assis.base.model.BaseModel;

/**
 * @author zhangzheng
 * @date 2018年4月18日
 */
public class AppGrade extends BaseModel {

	
	private static final long serialVersionUID = -3819294674309526174L;
	
	private Integer appId;
	private Integer gradeId;

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

}
