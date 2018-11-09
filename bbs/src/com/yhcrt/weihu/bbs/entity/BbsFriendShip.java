package com.yhcrt.weihu.bbs.entity;

import java.util.Date;

import com.yhcrt.weihu.bbs.entity.base.BaseBbsFriendShip;



public class BbsFriendShip extends BaseBbsFriendShip {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 申请中
	 */
	public static final int APPLYING = 0;
	/**
	 * 接受
	 */
	public static final int ACCEPT = 1;
	/**
	 * 拒绝
	 */
	public static final int REFUSE = 2;
	
	public void init(){
		if(getStatus()==null){
			setStatus(APPLYING);
		}
		if(getCreateTime()==null){
			setCreateTime(new Date());
		}
	}

/*[CONSTRUCTOR MARKER BEGIN]*/
	public BbsFriendShip () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public BbsFriendShip (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public BbsFriendShip (
		java.lang.Integer id,
		com.yhcrt.weihu.bbs.entity.BbsUser user,
		com.yhcrt.weihu.bbs.entity.BbsUser friend,
		java.lang.Integer status,
		java.util.Date createTime,
		java.lang.String applyDesc,
		java.lang.String managerDesc) {

		super(id, user, friend, status, createTime, applyDesc, managerDesc);
	}

/*[CONSTRUCTOR MARKER END]*/


}