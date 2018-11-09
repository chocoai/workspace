package com.whty.assis.basicdata.model;

import com.whty.assis.base.model.BaseModel;

public class SchoolLocationArea extends BaseModel {

	private static final long serialVersionUID = -8281271483566620735L;

	private Integer schoolLocationId;
	private Integer layer;
	private Integer attributeType;
	private String number;
	private String area;
	private String description;
	private Integer creator;
	private String creatorName;
	private String name;
	private Integer schoolLocationLayerId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSchoolLocationLayerId() {
		return schoolLocationLayerId;
	}

	public void setSchoolLocationLayerId(Integer schoolLocationLayerId) {
		this.schoolLocationLayerId = schoolLocationLayerId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

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

	public Integer getAttributeType() {
		return attributeType;
	}

	public void setAttributeType(Integer attributeType) {
		this.attributeType = attributeType;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

}
