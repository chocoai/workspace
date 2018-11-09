package com.yhcrt.weihu.cms.entity.assist;

import java.sql.Timestamp;

import com.yhcrt.weihu.cms.entity.assist.base.BaseCmsChange;



public class CmsChange extends BaseCmsChange {
	private static final long serialVersionUID = 1L;

    public void init() {
    	
		if (getStatus() == null) {
			setStatus(0);
		}
		if(getChangeTime()== null){
		   setChangeTime(new Timestamp(System.currentTimeMillis()));
		}
		
	}
/*[CONSTRUCTOR MARKER BEGIN]*/
	public CmsChange () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsChange (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CmsChange (
		java.lang.Integer id,
		com.yhcrt.weihu.cms.entity.main.Content content,
		com.yhcrt.weihu.core.entity.CmsUser user,
		java.lang.String title,
		java.lang.String name,
		java.lang.String contact,
		java.util.Date changeTime,
		java.lang.String address,
		java.lang.String bz,
		java.lang.Integer status) {

		super (
			id,
			content,
			user,
			title,
			name,contact,changeTime,address,bz,status);
	}

/*[CONSTRUCTOR MARKER END]*/


}