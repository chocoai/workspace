package com.yhcrt.weihu.cms.entity.assist;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.yhcrt.weihu.cms.entity.assist.base.BaseCmsBaoLiao;
//import com.yhcrt.weihu.cms.entity.main.ContentPicture;



public class CmsBaoLiao extends BaseCmsBaoLiao {
	private static final long serialVersionUID = 1L;

	 public void init() {
	    	if (getReadCount() == null) {
	    		setReadCount(0);
			}
			if (getCommtCount() == null) {
				setCommtCount(0);
			}
	    	if (getDowns() == null) {
				setDowns(0);
			}
			if (getUps() == null) {
				setUps(0);
			}
			if (getChecked() == null) {
				setChecked(false);
			}
			if (getRecommend() == null) {
				setRecommend(false);
			}
			if (getState() == null) {
				setState(0);
			}
			if (getCreateTime() == null) {
				setCreateTime(new Timestamp(System.currentTimeMillis()));
			}
			if (getPictures() == null) {
				setPictures(new ArrayList<BaoLiaoPicture>());
			}
		}
	    
	public void addToPictures(String path, String desc) {
		List<BaoLiaoPicture> list = getPictures();
		if (list == null) {
			list = new ArrayList<BaoLiaoPicture>();
			setPictures(list);
		}
		BaoLiaoPicture cp = new BaoLiaoPicture();
		cp.setImgPath(path);
		cp.setDescription(desc);
		list.add(cp);
	}
	public BaoLiaoPicture getPictureByNo(int pageNo) {
		List<BaoLiaoPicture> list = getPictures();
		if (pageNo >= 1 && list != null && list.size() >= pageNo) {
			return list.get(pageNo - 1);
		} else {
			return null;
		}
	}
	public String[]getPicPaths(){
		List<BaoLiaoPicture>pics=getPictures();
		if(pics==null||pics.size()<=0){
			return null;
		}
		String[]picPaths=new String[pics.size()];
		for(int i=0;i<picPaths.length;i++){
			picPaths[i]=pics.get(i).getImgPath();
		}
		return picPaths;
	}
/*[CONSTRUCTOR MARKER BEGIN]*/
	public CmsBaoLiao () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsBaoLiao (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CmsBaoLiao (
			java.lang.Integer id,
			com.yhcrt.weihu.core.entity.CmsUser user,
			com.yhcrt.weihu.core.entity.CmsUser admin,
			com.yhcrt.weihu.core.entity.CmsSite site,
			java.lang.String tel,
			java.lang.String email,
			java.lang.String title,
			java.lang.String content,
			java.lang.String reply,
			java.util.Date replyTime,
			java.util.Date createTime,
			java.lang.Integer state,
			java.lang.Integer ups,
			java.lang.Integer downs,
			java.lang.Integer readCount,
			java.lang.Integer commtCount,
			java.lang.Boolean recommend,
			java.lang.Boolean checked) {

		super (
			id,user,admin,site,tel,email,title,content,reply,replyTime,
			createTime,state,ups,downs,readCount,commtCount,recommend,checked);
	}

/*[CONSTRUCTOR MARKER END]*/


}