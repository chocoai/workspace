package com.yhcrt.weihu.core.entity;

import com.yhcrt.weihu.core.entity.base.BaseCmsWorkflowRecord;



public class CmsWorkflowRecord extends BaseCmsWorkflowRecord {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public CmsWorkflowRecord () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsWorkflowRecord (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CmsWorkflowRecord (
		java.lang.Integer id,
		com.yhcrt.weihu.core.entity.CmsSite site,
		com.yhcrt.weihu.core.entity.CmsWorkflowEvent event,
		com.yhcrt.weihu.core.entity.CmsUser user,
		java.util.Date recordTime,
		java.lang.Integer type) {

		super (
			id,
			site,
			event,
			user,
			recordTime,
			type);
	}

/*[CONSTRUCTOR MARKER END]*/


}