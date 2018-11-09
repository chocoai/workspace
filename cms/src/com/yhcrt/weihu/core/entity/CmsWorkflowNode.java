package com.yhcrt.weihu.core.entity;

import com.yhcrt.weihu.core.entity.base.BaseCmsWorkflowNode;



public class CmsWorkflowNode extends BaseCmsWorkflowNode {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public CmsWorkflowNode () {
		super();
	}

	/**
	 * Constructor for required fields
	 */
	public CmsWorkflowNode (
		com.yhcrt.weihu.core.entity.CmsRole role,
		boolean countersign) {

		super (
			role,
			countersign);
	}

/*[CONSTRUCTOR MARKER END]*/


}