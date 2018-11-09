package com.yhcrt.weihu.bbs.entity;

import java.util.Date;

import com.yhcrt.weihu.bbs.entity.base.BaseBbsFriends;

public class BbsFriends extends BaseBbsFriends{

	private static final long serialVersionUID = 1L;
	
	public void init(){
		if(getCreateTime()==null){
			setCreateTime(new Date());
		}
	}
	
	public BbsFriends(){
		super();
		init();
	}
	
	public BbsFriends(java.lang.Integer id){
		super(id);
		init();
	}
	
	public BbsFriends(
			java.lang.Integer id,
			java.util.Date createTime,
			com.yhcrt.weihu.bbs.entity.BbsUser user,
			com.yhcrt.weihu.bbs.entity.BbsUser friend){
		
		this.setId(id);
		this.setCreateTime(createTime);
		this.setUser(user);
		this.setFriend(friend);
		init();
	}

}
