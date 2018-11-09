/**
 * 
 */
package com.whty.assis.devicemanage.model;

import java.util.List;

import com.whty.assis.base.model.BaseModel;

/**
 * @author zhangzheng
 * @date 2018年4月24日
 */
public class BrowserWhitelistRule extends BaseModel {

	private static final long serialVersionUID = 4896385594122480418L;

	private String name;

	private String memo;

	private Integer creator;

	private String creatorName;

	private List<BrowserWhitelist> browserWhitelist;

	private Integer urlTotal;

	public Integer getUrlTotal() {
		return urlTotal;
	}

	public void setUrlTotal(Integer urlTotal) {
		this.urlTotal = urlTotal;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public List<BrowserWhitelist> getBrowserWhitelist() {
		return browserWhitelist;
	}

	public void setBrowserWhitelist(List<BrowserWhitelist> browserWhitelist) {
		this.browserWhitelist = browserWhitelist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
