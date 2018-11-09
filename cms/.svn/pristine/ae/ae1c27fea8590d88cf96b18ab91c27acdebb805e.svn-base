package com.yhcrt.weihu.cms.entity.assist;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import com.yhcrt.weihu.cms.entity.assist.base.BaseCmsComment;
import com.yhcrt.weihu.common.util.StrUtils;

public class CmsComment extends BaseCmsComment {
	private static final long serialVersionUID = 1L;

	public String getText() {
		return getCommentExt().getText();
	}

	public String getTextHtml() {
		return StrUtils.txt2htm(getText());
	}

	public String getReply() {
		return getCommentExt().getReply();
	}

	public String getReplayHtml() {
		return StrUtils.txt2htm(getReply());
	}

	public String getIp() {
		return getCommentExt().getIp();
	}
	/**
	 * 获得节点列表。从父节点到自身。
	 * 
	 * @return
	 */
	public List<CmsComment> getNodeList() {
		LinkedList<CmsComment> list = new LinkedList<CmsComment>();
		CmsComment node = this;
		while (node != null) {
			list.addFirst(node);
			node = node.getParent();
		}
		return list;
	}
	public void init() {
		if (getDowns() == null) {
			setDowns(0);
		}
		if (getUps() == null) {
			setUps(0);
		}
		if (getCommts() == null) {
			setCommts(0);
		}
		if (getChecked() == null) {
			setChecked(false);
		}
		if (getRecommend() == null) {
			setRecommend(false);
		}
		if (getCreateTime() == null) {
			setCreateTime(new Timestamp(System.currentTimeMillis()));
		}
	}

	/* [CONSTRUCTOR MARKER BEGIN] */
	public CmsComment () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public CmsComment (java.lang.Integer id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public CmsComment (
		java.lang.Integer id,
		com.yhcrt.weihu.cms.entity.main.Content content,
		com.yhcrt.weihu.core.entity.CmsSite site,
		com.yhcrt.weihu.cms.entity.assist.CmsBaoLiao baoliao,
		java.util.Date createTime,
		java.lang.Integer commts,
		java.lang.Integer ups,
		java.lang.Integer downs,
		java.lang.Boolean recommend,
		java.lang.Boolean checked) {

		super (
			id,
			content,
			site,
			baoliao,
			createTime,
			commts,
			ups,
			downs,
			recommend,
			checked);
	}

	/* [CONSTRUCTOR MARKER END] */

}