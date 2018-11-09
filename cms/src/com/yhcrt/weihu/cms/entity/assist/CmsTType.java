package com.yhcrt.weihu.cms.entity.assist;

import com.yhcrt.weihu.cms.entity.assist.base.BaseCmsTType;



public class CmsTType extends BaseCmsTType {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public CmsTType () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsTType (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CmsTType (
		java.lang.Integer id,
		com.yhcrt.weihu.core.entity.CmsSite site,
		java.lang.String name) {

		super (id,site,name);
	}

/*[CONSTRUCTOR MARKER END]*/


}