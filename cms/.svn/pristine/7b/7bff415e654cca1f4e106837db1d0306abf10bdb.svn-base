package com.yhcrt.weihu.cms.entity.assist;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.yhcrt.weihu.cms.entity.assist.base.BaseCmsTT;

public class CmsTT extends BaseCmsTT {
	private static final long serialVersionUID = 1L;

	public void init() {
		
		if (getChecked() == null) {
			setChecked(false);
		}
		if (getRecommend() == null) {
			setRecommend(false);
		}
		if (getReadCount() == null) {
			setReadCount(0);
		}
		if (getCreateTime() == null) {
			setCreateTime(new Timestamp(System.currentTimeMillis()));
		}
		if (getPictures() == null) {
			setPictures(new ArrayList<TTPicture>());
		}
	}
    
	public void addToPictures(String path, String desc) {
		List<TTPicture> list = getPictures();
		if (list == null) {
			list = new ArrayList<TTPicture>();
			setPictures(list);
		}
		TTPicture cp = new TTPicture();
		cp.setImgPath(path);
		cp.setDescription(desc);
		list.add(cp);
	}
	public TTPicture getPictureByNo(int pageNo) {
		List<TTPicture> list = getPictures();
		if (pageNo >= 1 && list != null && list.size() >= pageNo) {
			return list.get(pageNo - 1);
		} else {
			return null;
		}
	}
	public String[]getPicPaths(){
		List<TTPicture>pics=getPictures();
		if(pics==null||pics.size()<=0){
			return null;
		}
		String[]picPaths=new String[pics.size()];
		for(int i=0;i<picPaths.length;i++){
			picPaths[i]=pics.get(i).getImgPath();
		}
		return picPaths;
	}
	/* [CONSTRUCTOR MARKER BEGIN] */
	public CmsTT() {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsTT(java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CmsTT(java.lang.Integer id,
			com.yhcrt.weihu.core.entity.CmsUser user,
			com.yhcrt.weihu.cms.entity.assist.CmsTType type,
			com.yhcrt.weihu.core.entity.CmsSite site,
			java.lang.Integer demand,
			java.lang.String title,
			java.lang.String name,
			java.lang.String contact,
			java.lang.String description,
			java.util.Date expTime,
			java.util.Date createTime,
			java.lang.Integer readCount,
			java.lang.Boolean checked,
			java.lang.Boolean recommend,
			java.lang.Integer isAdmin) {

		super(id,user,type,site,demand,title,name,contact,description,expTime,createTime,readCount,checked,recommend,isAdmin);
	}

	/* [CONSTRUCTOR MARKER END] */

}