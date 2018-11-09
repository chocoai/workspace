package com.yhcrt.weihu.bbs.entity.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jb_friendship table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jb_friendship"
 */

public abstract class BaseBbsFriendShip  implements Serializable {

	public static String REF = "BbsFriendShip";
	public static String PROP_USER = "user";
	public static String PROP_STATUS = "status";
	public static String PROP_ID = "id";
	public static String PROP_FRIEND = "friend";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_APPLY_DESC = "applyDesc";
	public static String PROP_MANAGER_DESC = "managerDesc";


	// constructors
	public BaseBbsFriendShip () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseBbsFriendShip (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseBbsFriendShip (
		java.lang.Integer id,
		com.yhcrt.weihu.bbs.entity.BbsUser user,
		com.yhcrt.weihu.bbs.entity.BbsUser friend,
		java.lang.Integer status,
		java.util.Date createTime,
		java.lang.String applyDesc,
		java.lang.String managerDesc) {

		this.setId(id);
		this.setUser(user);
		this.setFriend(friend);
		this.setStatus(status);
		this.setCreateTime(createTime);
		this.setApplyDesc(applyDesc);
		this.setManagerDesc(managerDesc);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.Integer status;
	private java.util.Date createTime;
	private java.lang.String applyDesc;
	private java.lang.String managerDesc;

	// many to one
	private com.yhcrt.weihu.bbs.entity.BbsUser user;
	private com.yhcrt.weihu.bbs.entity.BbsUser friend;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="friendlink_id"
     */
	public java.lang.Integer getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: status
	 */
	public java.lang.Integer getStatus () {
		return status;
	}

	/**
	 * Set the value related to the column: status
	 * @param status the status value
	 */
	public void setStatus (java.lang.Integer status) {
		this.status = status;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getApplyDesc() {
		return applyDesc;
	}

	public void setApplyDesc(java.lang.String applyDesc) {
		this.applyDesc = applyDesc;
	}

	public java.lang.String getManagerDesc() {
		return managerDesc;
	}

	public void setManagerDesc(java.lang.String managerDesc) {
		this.managerDesc = managerDesc;
	}

	/**
	 * Return the value associated with the column: user_id
	 */
	public com.yhcrt.weihu.bbs.entity.BbsUser getUser () {
		return user;
	}

	/**
	 * Set the value related to the column: user_id
	 * @param user the user_id value
	 */
	public void setUser (com.yhcrt.weihu.bbs.entity.BbsUser user) {
		this.user = user;
	}


	/**
	 * Return the value associated with the column: friend_id
	 */
	public com.yhcrt.weihu.bbs.entity.BbsUser getFriend () {
		return friend;
	}

	/**
	 * Set the value related to the column: friend_id
	 * @param friend the friend_id value
	 */
	public void setFriend (com.yhcrt.weihu.bbs.entity.BbsUser friend) {
		this.friend = friend;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.yhcrt.weihu.bbs.entity.BbsFriendShip)) return false;
		else {
			com.yhcrt.weihu.bbs.entity.BbsFriendShip bbsFriendShip = (com.yhcrt.weihu.bbs.entity.BbsFriendShip) obj;
			if (null == this.getId() || null == bbsFriendShip.getId()) return false;
			else return (this.getId().equals(bbsFriendShip.getId()));
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