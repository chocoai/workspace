/**
 * 
 */
package com.whty.assis.devicemanage.model;

import com.whty.assis.base.model.BaseModel;

/**
 * @author zhangzheng
 * @date 2018年5月9日
 */
public class AppWhitelist extends BaseModel {

	private static final long serialVersionUID = 3165759349514005241L;

	private Integer appWhitelistRuleId;

	private Integer appInfoId;

	private String appInfoName;

	public Integer getAppWhitelistRuleId() {
		return appWhitelistRuleId;
	}

	public void setAppWhitelistRuleId(Integer appWhitelistRuleId) {
		this.appWhitelistRuleId = appWhitelistRuleId;
	}

	public Integer getAppInfoId() {
		return appInfoId;
	}

	public void setAppInfoId(Integer appInfoId) {
		this.appInfoId = appInfoId;
	}

	public String getAppInfoName() {
		return appInfoName;
	}

	public void setAppInfoName(String appInfoName) {
		this.appInfoName = appInfoName;
	}

}
