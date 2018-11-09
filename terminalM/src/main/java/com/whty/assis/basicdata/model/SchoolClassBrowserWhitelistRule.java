/**
 * 
 */
package com.whty.assis.basicdata.model;

import com.whty.assis.base.model.BaseModel;

/**
 * @author zhangzheng
 * @date 2018年6月5日
 */
public class SchoolClassBrowserWhitelistRule extends BaseModel {

	private static final long serialVersionUID = 5721033666188876069L;
	
	private Integer schoolClassId;
	
	private Integer browserWhitelistRuleId;

	public Integer getSchoolClassId() {
		return schoolClassId;
	}

	public void setSchoolClassId(Integer schoolClassId) {
		this.schoolClassId = schoolClassId;
	}

	public Integer getBrowserWhitelistRuleId() {
		return browserWhitelistRuleId;
	}

	public void setBrowserWhitelistRuleId(Integer browserWhitelistRuleId) {
		this.browserWhitelistRuleId = browserWhitelistRuleId;
	}

}
