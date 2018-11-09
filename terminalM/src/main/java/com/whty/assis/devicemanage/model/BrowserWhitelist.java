/**
 * 
 */
package com.whty.assis.devicemanage.model;

import com.whty.assis.base.model.BaseModel;

/**
 * @author zhangzheng
 * @date 2018年4月24日
 */
public class BrowserWhitelist extends BaseModel {

	private static final long serialVersionUID = 7525456331675665974L;

	private String url;

	private Integer browserWhitelistRuleId;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getBrowserWhitelistRuleId() {
		return browserWhitelistRuleId;
	}

	public void setBrowserWhitelistRuleId(Integer browserWhitelistRuleId) {
		this.browserWhitelistRuleId = browserWhitelistRuleId;
	}

}
