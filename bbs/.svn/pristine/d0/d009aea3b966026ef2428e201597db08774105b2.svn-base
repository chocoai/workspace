package com.yhcrt.weihu.bbs.entity;

import java.util.Date;

import com.yhcrt.weihu.bbs.entity.base.BaseBbsAlbumImg;

public class BbsAlbumImg extends BaseBbsAlbumImg{

	private static final long serialVersionUID = 1L;
	
	public void init(){
		if(getCreateTime()==null){
			setCreateTime(new Date());
		}
		if(getStatus()==null){
			setStatus(0);
		}
		if(getPrimeCount()==null){
			setPrimeCount(0);
		}
		if(getCommentCount()==null){
			setCommentCount(0);
		}
	}
	
	public BbsAlbumImg(){
		super();
		init();
	}
	
	public BbsAlbumImg(java.lang.Integer id){
		super(id);
		init();
	}
	
	public BbsAlbumImg(
			java.lang.Integer id,
			java.util.Date createTime,
			com.yhcrt.weihu.bbs.entity.BbsUser creater,
			java.lang.String name,
			java.lang.String description,
			java.lang.Integer status,
			java.lang.String imgUrl,
			java.lang.Integer primeCount,
			java.lang.Integer commentCount,
			com.yhcrt.weihu.bbs.entity.BbsAlbum album){
		
		this.setId(id);
		this.setCreateTime(createTime);
		this.setCreater(creater);
		this.setName(name);
		this.setDescription(description);
		this.setStatus(status);
		this.setImgUrl(imgUrl);
		this.setPrimeCount(primeCount);
		this.setCommentCount(commentCount);
		this.setAlbum(album);
		init();
	}

}
