package com.yhcrt.weihu.bbs.entity.base;

import java.io.Serializable;

public abstract class BaseBbsSendCaptcha implements Serializable {

	public static String REF = "BbsConcern";
	public static String PROP_ID = "id";
	public static String PROP_EXPIRT_TIME = "expireTime";
	public static String PROP_CREATER_ID = "creater";
	public static String PROP_CAPTCHA = "captcha";
	public static String PROP_BUSINESS_TYPE = "businessType";
	public static String PROP_TARGET = "target";
	
	public BaseBbsSendCaptcha (){
		initialize ();
	}
	
	public BaseBbsSendCaptcha (java.lang.Integer id){
		this.setId(id);
		initialize();
	}
	
	public BaseBbsSendCaptcha (
			java.lang.Integer id,
			java.lang.String captcha,
			java.util.Date expireTime,
			java.lang.String target,
			java.lang.String businessType,
			com.yhcrt.weihu.bbs.entity.BbsUser creater){
		
		this.setId(id);
		this.setCaptcha(captcha);
		this.setExpireTime(expireTime);
		this.setTarget(target);
		this.setBusinessType(businessType);
		this.setCreater(creater);
		initialize();
	}
	
	protected void initialize () {}
	
	private int hashCode = Integer.MIN_VALUE;
	
	// primary key
	private java.lang.Integer id;

	// fields
	private java.util.Date expireTime;
	private java.lang.String target;
	private java.lang.String businessType;
	private java.lang.String captcha;
	
	// many to one
	private com.yhcrt.weihu.bbs.entity.BbsUser creater;
	
	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.util.Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(java.util.Date expireTime) {
		this.expireTime = expireTime;
	}

	public java.lang.String getTarget() {
		return target;
	}

	public void setTarget(java.lang.String target) {
		this.target = target;
	}

	public java.lang.String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(java.lang.String businessType) {
		this.businessType = businessType;
	}

	public java.lang.String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(java.lang.String captcha) {
		this.captcha = captcha;
	}

	public com.yhcrt.weihu.bbs.entity.BbsUser getCreater() {
		return creater;
	}

	public void setCreater(com.yhcrt.weihu.bbs.entity.BbsUser creater) {
		this.creater = creater;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.yhcrt.weihu.bbs.entity.BbsSendCaptcha)) return false;
		else {
			com.yhcrt.weihu.bbs.entity.BbsSendCaptcha bbsTopic = (com.yhcrt.weihu.bbs.entity.BbsSendCaptcha) obj;
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
