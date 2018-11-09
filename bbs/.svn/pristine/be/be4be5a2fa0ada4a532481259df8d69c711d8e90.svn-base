package com.yhcrt.weihu.bbs.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.yhcrt.weihu.bbs.entity.base.BaseBbsConcern;

public class BbsConcern extends BaseBbsConcern{

	private static final long serialVersionUID = 1L;
	
	public void init() {
		Date now = new Timestamp(System.currentTimeMillis());
		if(getCreateTime()==null){
			setCreateTime(now);
		}
	}
	
	public BbsConcern (){
		super();
	}
	
	public BbsConcern (java.lang.Integer id){
		super(id);
	}
	
	public BbsConcern (
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
	
}
