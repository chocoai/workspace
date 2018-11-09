package com.yhcrt.weihu.bbs.entity;

import java.sql.Timestamp;
import java.util.Date;

import com.yhcrt.weihu.bbs.entity.base.BaseBbsPointDetail;

public class BbsPointDetail extends BaseBbsPointDetail{

	private static final long serialVersionUID = 1L;

	public void init(){
		Date now = new Timestamp(System.currentTimeMillis());
		if(getCreateTime()==null){
			setCreateTime(now);
		}
	}
	
	public BbsPointDetail(){
		super();
	}
	
	public BbsPointDetail(java.lang.Integer id){
		super(id);
	}
	
	public BbsPointDetail(
			java.lang.Integer id,
			com.yhcrt.weihu.bbs.entity.BbsUser creater,
			java.util.Date createTime,
			java.lang.Integer points,
			java.lang.String operation,
			java.lang.String description,
			java.lang.String type){
		this.setId(id);
		this.setCreater(creater);
		this.setCreateTime(createTime);
		this.setPoints(points);
		this.setOperation(operation);
		this.setDescription(description);
		this.setType(type);
		initialize();
	}
	
}
