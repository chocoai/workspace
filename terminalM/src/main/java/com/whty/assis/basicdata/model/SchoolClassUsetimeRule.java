/**
 * 
 */
package com.whty.assis.basicdata.model;

import com.whty.assis.base.model.BaseModel;

/**
 * @author zhangzheng
 * @date 2018年6月5日
 */
public class SchoolClassUsetimeRule extends BaseModel {

	
	private static final long serialVersionUID = 8536469700326573746L;
	
	private Integer schoolClassId;
	
	private Integer usetimeRuleId;

	public Integer getSchoolClassId() {
		return schoolClassId;
	}

	public void setSchoolClassId(Integer schoolClassId) {
		this.schoolClassId = schoolClassId;
	}

	public Integer getUsetimeRuleId() {
		return usetimeRuleId;
	}

	public void setUsetimeRuleId(Integer usetimeRuleId) {
		this.usetimeRuleId = usetimeRuleId;
	}

}
