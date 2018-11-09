package com.yhcrt.weihu.bbs.entity.base;

import java.io.Serializable;

public abstract class BaseBbsConcern implements Serializable {

	public static String REF = "BbsConcern";
	public static String PROP_ID = "id";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_CREATER_ID = "creater";
	public static String PROP_CONCERN_USER_ID = "concernUser";
	
	public BaseBbsConcern (){
		initialize ();
	}
	
	public BaseBbsConcern (java.lang.Integer id){
		this.setId(id);
		initialize();
	}
	
	public BaseBbsConcern (
			java.lang.Integer id,
			java.util.Date createTime,
			com.yhcrt.weihu.bbs.entity.BbsUser creater,
			com.yhcrt.weihu.bbs.entity.BbsUser concernUser){
		
		this.setId(id);
		this.setCreater(creater);
		this.setCreateTime(createTime);
		this.setConcernUser(concernUser);
		initialize();
	}
	
	protected void initialize () {}
	
	private int hashCode = Integer.MIN_VALUE;
	
	// primary key
	private java.lang.Integer id;

	// fields
	private java.util.Date createTime;
	
	// many to one
	private com.yhcrt.weihu.bbs.entity.BbsUser creater;
	private com.yhcrt.weihu.bbs.entity.BbsUser concernUser;
	
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

	public com.yhcrt.weihu.bbs.entity.BbsUser getCreater() {
		return creater;
	}

	public void setCreater(com.yhcrt.weihu.bbs.entity.BbsUser creater) {
		this.creater = creater;
	}

	public com.yhcrt.weihu.bbs.entity.BbsUser getConcernUser() {
		return concernUser;
	}

	public void setConcernUser(com.yhcrt.weihu.bbs.entity.BbsUser concernUser) {
		this.concernUser = concernUser;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.yhcrt.weihu.bbs.entity.BbsConcern)) return false;
		else {
			com.yhcrt.weihu.bbs.entity.BbsConcern bbsTopic = (com.yhcrt.weihu.bbs.entity.BbsConcern) obj;
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
