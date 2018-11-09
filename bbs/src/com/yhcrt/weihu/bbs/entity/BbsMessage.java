package com.yhcrt.weihu.bbs.entity;

import java.util.Date;

import com.yhcrt.weihu.bbs.entity.BbsMessage;
import com.yhcrt.weihu.bbs.entity.BbsMessageReply;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.entity.base.BaseBbsMessage;

public class BbsMessage extends BaseBbsMessage implements Cloneable{
	private static final long serialVersionUID = 1L;
	
	public static int MESSAGE_TYPE_MESSAGE=1;
	
	public static int MESSAGE_TYPE_GUESTBOOK=2;
	
	public static int MESSAGE_TYPE_GREET=3;
	
	public BbsMessage clone(){
		BbsMessage clone;
		try {
			clone = (BbsMessage) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("Clone not support?");
		}
		return clone;
	}
	
	public BbsMessage putDataAndClone(BbsUser sender, BbsUser receiver){
		Date now = new Date();
		setUser(sender);
		setSender(sender);
		setReceiver(receiver);
		setCreateTime(now);
		init();
		BbsMessage clone = clone();
		clone.setUser(receiver);
		return clone;
	}
	
	public BbsMessageReply createReply(){
		BbsMessageReply bean = new BbsMessageReply();
		bean.setContent(getContent());
		bean.setCreateTime(getCreateTime());
		bean.setMessage(this);
		bean.setSender(getSender());
		bean.setReceiver(getReceiver());
		return bean;
	}
	
	public void init(){
		if(getSys()==null){
			setSys(false);
		}
	}

/*[CONSTRUCTOR MARKER BEGIN]*/
	public BbsMessage () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public BbsMessage (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public BbsMessage (
		java.lang.Integer id,
		com.yhcrt.weihu.bbs.entity.BbsUser user,
		com.yhcrt.weihu.bbs.entity.BbsUser receiver,
		java.util.Date createTime,
		java.lang.Boolean sys) {

		super (
			id,
			user,
			receiver,
			createTime,
			sys);
	}

/*[CONSTRUCTOR MARKER END]*/


}