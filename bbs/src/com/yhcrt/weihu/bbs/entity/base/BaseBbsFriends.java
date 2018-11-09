package com.yhcrt.weihu.bbs.entity.base;

import java.io.Serializable;

public abstract class BaseBbsFriends  implements Serializable {

	public static String REF = "BbsFriends";
	public static String PROP_ID = "id";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_USER_ID = "user";
	public static String PROP_FRIENDS_ID = "friends";

	public BaseBbsFriends () {
		initialize();
	}

	public BaseBbsFriends (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	public BaseBbsFriends (
		java.lang.Integer id,
		java.util.Date createTime,
		com.yhcrt.weihu.bbs.entity.BbsUser user,
		com.yhcrt.weihu.bbs.entity.BbsUser friend) {

		this.setId(id);
		this.setCreateTime(createTime);
		this.setUser(user);
		this.setFriend(friend);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.util.Date createTime;
	
	// many to one
	private com.yhcrt.weihu.bbs.entity.BbsUser user;
	private com.yhcrt.weihu.bbs.entity.BbsUser friend;


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

	public com.yhcrt.weihu.bbs.entity.BbsUser getUser() {
		return user;
	}

	public void setUser(com.yhcrt.weihu.bbs.entity.BbsUser user) {
		this.user = user;
	}

	public com.yhcrt.weihu.bbs.entity.BbsUser getFriend() {
		return friend;
	}

	public void setFriend(com.yhcrt.weihu.bbs.entity.BbsUser friend) {
		this.friend = friend;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.yhcrt.weihu.bbs.entity.BbsCollection)) return false;
		else {
			com.yhcrt.weihu.bbs.entity.BbsFriends bbsFriends = (com.yhcrt.weihu.bbs.entity.BbsFriends) obj;
			if (null == this.getId() || null == bbsFriends.getId()) return false;
			else return (this.getId().equals(bbsFriends.getId()));
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