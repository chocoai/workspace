/**
 * 
 */
package com.whty.assis.basicdata.model;

import com.whty.assis.base.model.BaseModel;

/**
 * @author zhangzheng
 * @date 2018年3月29日
 */
public class SchoolLocationLayer extends BaseModel {

	private static final long serialVersionUID = -8483762897254964587L;
	
	
	private Integer schoolLocationId;
	private Integer layer;

	public Integer getSchoolLocationId() {
		return schoolLocationId;
	}

	public void setSchoolLocationId(Integer schoolLocationId) {
		this.schoolLocationId = schoolLocationId;
	}

	public Integer getLayer() {
		return layer;
	}

	public void setLayer(Integer layer) {
		this.layer = layer;
	}

}
