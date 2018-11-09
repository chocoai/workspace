package com.yhcrt.weihu.bbs.entity;

import java.util.Date;

import com.yhcrt.weihu.bbs.entity.base.BaseBbsAlbum;

public class BbsAlbum extends BaseBbsAlbum{

	private static final long serialVersionUID = 1L;
	
	public void init(){
		if(getCreateTime()==null){
			setCreateTime(new Date());
		}
		if(getStatus()==null){
			setStatus(0);
		}
		if(getVisitAuth()==null){
			setVisitAuth(0);
		}
		if(getCount()==null){
			setCount(0);
		}
		if(getPrimeCount()==null){
			setPrimeCount(0);
		}
		if(getCommentCount()==null){
			setCommentCount(0);
		}
		if(getViewCount()==null){
			setViewCount(0);
		}
	}
	
	public BbsAlbum(){
		super();
		init();
	}
	
	public BbsAlbum(java.lang.Integer id){
		super(id);
		init();
	}
	
	public BbsAlbum(
			java.lang.Integer id,
			java.util.Date createTime,
			com.yhcrt.weihu.bbs.entity.BbsUser creater,
			java.lang.String name,
			java.lang.String description,
			java.lang.String imgUrl,
			java.lang.Integer visitAuth,
			java.lang.Integer status,
			java.lang.Integer count,
			java.lang.Integer primeCount,
			java.lang.Integer commentCount,
			java.lang.Integer viewCount){
		
		this.setId(id);
		this.setCreateTime(createTime);
		this.setCreater(creater);
		this.setName(name);
		this.setDescription(description);
		this.setImgUrl(imgUrl);
		this.setVisitAuth(visitAuth);
		this.setStatus(status);
		this.setCount(count);
		this.setPrimeCount(primeCount);
		this.setCommentCount(commentCount);
		this.setViewCount(viewCount);
		init();
	}

}
