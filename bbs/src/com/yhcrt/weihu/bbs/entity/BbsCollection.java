package com.yhcrt.weihu.bbs.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.yhcrt.weihu.bbs.entity.base.BaseBbsCollection;

public class BbsCollection extends BaseBbsCollection{
	
	private static final long serialVersionUID = 1L;
	
	public void init() {
		Date now = new Timestamp(System.currentTimeMillis());
		if(getCreateTime()==null){
			setCreateTime(now);
		}
		if(getTopic()==null){
			setTopic(null);
		}
		if(getForum()==null){
			setForum(null);
		}
	}
	
	public BbsCollection (){
		super();
	}
	
	public BbsCollection(java.lang.Integer id){
		super(id);
	}
	
	public BbsCollection(
			java.lang.Integer id,
			java.util.Date createTime,
			com.yhcrt.weihu.bbs.entity.BbsUser creater,
			com.yhcrt.weihu.bbs.entity.BbsTopic topic,
			com.yhcrt.weihu.bbs.entity.BbsForum forum){
		this.setId(id);
		this.setCreateTime(createTime);
		this.setCreater(creater);
		this.setTopic(topic);
		this.setForum(forum);
		initialize();
	}
	
}
