/**
 * 
 */
package com.whty.assis.mall.model;

import com.whty.assis.base.model.BaseModel;

/**
 * @author zhangzheng
 * @date 2018年4月18日
 */
public class AppImg extends BaseModel {

	private static final long serialVersionUID = -5339190511023363722L;

	private String url;

	private Integer appId;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

}
