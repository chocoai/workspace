package com.yhcrt.weihu.cms.entity.assist;

import com.yhcrt.weihu.cms.entity.assist.base.BaseCmsMessage;
import com.yhcrt.weihu.common.util.StrUtils;



public class CmsMessage extends BaseCmsMessage {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public CmsMessage () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsMessage (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CmsMessage (
		java.lang.Integer id,
		com.yhcrt.weihu.core.entity.CmsUser msgReceiverUser,
		com.yhcrt.weihu.core.entity.CmsUser msgSendUser,
		com.yhcrt.weihu.core.entity.CmsSite site,
		java.lang.String msgTitle,
		java.lang.Boolean msgStatus,
		java.lang.Integer msgBox) {

		super (
			id,
			msgReceiverUser,
			msgSendUser,
			site,
			msgTitle,
			msgStatus,
			msgBox);
	}
	public String getTitleHtml() {
		return StrUtils.txt2htm(getMsgTitle());
	}
	public String getContentHtml() {
		return StrUtils.txt2htm(getMsgContent());
	}


/*[CONSTRUCTOR MARKER END]*/


}