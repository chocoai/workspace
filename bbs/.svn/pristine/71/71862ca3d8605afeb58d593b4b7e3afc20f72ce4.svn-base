package com.yhcrt.weihu.bbs.entity.base;

import java.io.Serializable;

public abstract class BaseBbsPointDetail implements Serializable{

	public static String REF = "BbsPointDetail";
	public static String PROP_ID = "id";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_CREATER_ID = "creater";
	public static String PROP_DESCRIPTION = "description";
	public static String PROP_OPERATION = "operation";
	public static String PROP_POINTS = "points";
	public static String PROP_TYPE = "type";
	
	public BaseBbsPointDetail(){
		initialize ();
	}
	
	public BaseBbsPointDetail(java.lang.Integer id){
		this.setId(id);
		initialize();
	}
	
	public BaseBbsPointDetail(
			java.lang.Integer id,
			com.yhcrt.weihu.bbs.entity.BbsUser creater,
			java.util.Date createTime,
			java.lang.Integer points,
			java.lang.String operation,
			java.lang.String description,
			java.lang.String type){
		this.setId(id);
		this.setCreater(creater);
		this.setCreateTime(createTime);
		this.setPoints(points);
		this.setOperation(operation);
		this.setDescription(description);
		this.setType(type);
		initialize();
	}
	
	protected void initialize () {}
	
	private int hashCode = Integer.MIN_VALUE;
	
	// primary key
	private java.lang.Integer id;

	// fields
	private java.util.Date createTime;
	private java.lang.Integer points;
	private java.lang.String description;
	private java.lang.String operation;
	private java.lang.String type;
	
	// many to one
	private com.yhcrt.weihu.bbs.entity.BbsUser creater;

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.lang.Integer getPoints() {
		return points;
	}

	public void setPoints(java.lang.Integer points) {
		this.points = points;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.String getOperation() {
		return operation;
	}

	public void setOperation(java.lang.String operation) {
		this.operation = operation;
	}

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public com.yhcrt.weihu.bbs.entity.BbsUser getCreater() {
		return creater;
	}

	public void setCreater(com.yhcrt.weihu.bbs.entity.BbsUser creater) {
		this.creater = creater;
	}
	
	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.yhcrt.weihu.bbs.entity.BbsPointDetail)) return false;
		else {
			com.yhcrt.weihu.bbs.entity.BbsPointDetail bbsTopic = (com.yhcrt.weihu.bbs.entity.BbsPointDetail) obj;
			if (null == this.getId() || null == bbsTopic.getId()) return false;
			else return (this.getId().equals(bbsTopic.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}
	
}
