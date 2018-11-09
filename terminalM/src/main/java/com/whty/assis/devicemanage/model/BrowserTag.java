/**
 * 
 */
package com.whty.assis.devicemanage.model;

import com.whty.assis.base.model.BaseModel;

/**
 * @author zhangzheng
 * @date 2018年4月24日
 */
public class BrowserTag extends BaseModel {

	private static final long serialVersionUID = -7669275740496266610L;

	private String url;
	
	private String name;
	
	private String groupNum;
	
	private String groupName;
	
	private Integer browserTagRuleId;

	private String logo;

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getUrl() {

		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(String groupNum) {
		this.groupNum = groupNum;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getBrowserTagRuleId() {
		return browserTagRuleId;
	}

	public void setBrowserTagRuleId(Integer browserTagRuleId) {
		this.browserTagRuleId = browserTagRuleId;
	}

}
