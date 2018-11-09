package com.yhcrt.weihu.bbs.entity.base;

import java.io.Serializable;

public abstract class BaseBbsCollection  implements Serializable {

	public static String REF = "BbsCollection";
	public static String PROP_ID = "id";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_CREATER_ID = "creater";
	public static String PROP_TOPIC = "topic";
	public static String PROP_FORUM = "forum";

	public BaseBbsCollection () {
		initialize();
	}

	public BaseBbsCollection (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	public BaseBbsCollection (
		java.lang.Integer id,
		java.util.Date createTime,
		com.yhcrt.weihu.bbs.entity.BbsUser creater,
		com.yhcrt.weihu.bbs.entity.BbsTopic topic,
		com.yhcrt.weihu.bbs.entity.BbsForum forum) {

		this.setId(id);
		this.setCreateTime(createTime);
		this.setCreater(creater);
		this.setTopic(topic);
		this.setForum(forum);
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
	private com.yhcrt.weihu.bbs.entity.BbsTopic topic;
	private com.yhcrt.weihu.bbs.entity.BbsForum forum;


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

	public com.yhcrt.weihu.bbs.entity.BbsTopic getTopic() {
		return topic;
	}

	public void setTopic(com.yhcrt.weihu.bbs.entity.BbsTopic topic) {
		this.topic = topic;
	}
	
	public com.yhcrt.weihu.bbs.entity.BbsForum getForum() {
		return forum;
	}

	public void setForum(com.yhcrt.weihu.bbs.entity.BbsForum forum) {
		this.forum = forum;
	}

	/**
	 * Return the value associated with the column: creater_id
	 */
	public com.yhcrt.weihu.bbs.entity.BbsUser getCreater () {
		return creater;
	}

	/**
	 * Set the value related to the column: creater_id
	 * @param creater the creater_id value
	 */
	public void setCreater (com.yhcrt.weihu.bbs.entity.BbsUser creater) {
		this.creater = creater;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.yhcrt.weihu.bbs.entity.BbsCollection)) return false;
		else {
			com.yhcrt.weihu.bbs.entity.BbsCollection bbsTopic = (com.yhcrt.weihu.bbs.entity.BbsCollection) obj;
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