package com.yhcrt.weihu.cms.entity.assist.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_baoliao table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_baoliao"
 */

public abstract class BaseCmsBaoLiao  implements Serializable {

	public static String REF = "CmsBaoLiao";
	public static String PROP_STATE = "state";
	public static String PROP_RECOMMEND = "recommend";
	public static String PROP_CHECKED = "checked";
	public static String PROP_UPS = "ups";
	public static String PROP_DOWNS = "downs";
	public static String PROP_READ_COUNT = "readCount";
	public static String PROP_COMMT_COUNT = "commtCount";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_REPLY_TIME = "replyTime";
	public static String PROP_REPLY = "reply";
	public static String PROP_SITE = "site";
	public static String PROP_USER = "user";
	public static String PROP_ADMIN = "admin";
	public static String PROP_CONTENT = "content";
	public static String PROP_TITLE = "title";
	public static String PROP_EMAIL = "email";
	public static String PROP_TEL = "tel";
	public static String PROP_ID = "id";


	// constructors
	public BaseCmsBaoLiao () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsBaoLiao (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCmsBaoLiao (
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

		this.setId(id);
		this.setTel(tel);
		this.setEmail(email);
		this.setTitle(title);
		this.setContent(content);
		this.setUser(user);
		this.setAdmin(admin);
		this.setSite(site);
		this.setReply(reply);
		this.setReplyTime(replyTime);
		this.setState(state);
		this.setCreateTime(createTime);
		this.setCommtCount(commtCount);
		this.setReadCount(readCount);
		this.setUps(ups);
		this.setDowns(downs);
		this.setChecked(checked);
		this.setRecommend(recommend);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String tel;
	private java.lang.String email;
	private java.lang.String title;
	private java.lang.String content;
	private java.lang.String reply;
	private java.util.Date replyTime;
	private java.lang.Integer state;
	private java.lang.Integer ups;
	private java.lang.Integer downs;
	private java.lang.Integer readCount;
	private java.lang.Integer commtCount;
	private java.util.Date createTime;
	private java.lang.Boolean checked;
	private java.lang.Boolean recommend;

	// many to one
	private com.yhcrt.weihu.core.entity.CmsUser user;
	private com.yhcrt.weihu.core.entity.CmsUser admin;
	private com.yhcrt.weihu.core.entity.CmsSite site;

	private java.util.List<com.yhcrt.weihu.cms.entity.assist.BaoLiaoPicture> pictures;
	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="job_apply_id"
     */
	public java.lang.Integer getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}

	
	public java.lang.String getTel() {
		return tel;
	}

	public void setTel(java.lang.String tel) {
		this.tel = tel;
	}

	public java.lang.String getEmail() {
		return email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.String getReply() {
		return reply;
	}

	public void setReply(java.lang.String reply) {
		this.reply = reply;
	}

	public java.util.Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(java.util.Date replyTime) {
		this.replyTime = replyTime;
	}

	public java.lang.Integer getState() {
		return state;
	}

	public void setState(java.lang.Integer state) {
		this.state = state;
	}

	public com.yhcrt.weihu.core.entity.CmsUser getAdmin() {
		return admin;
	}

	public void setAdmin(com.yhcrt.weihu.core.entity.CmsUser admin) {
		this.admin = admin;
	}

	/**
	 * Return the value associated with the column: user_id
	 */
	public com.yhcrt.weihu.core.entity.CmsUser getUser () {
		return user;
	}

	/**
	 * Set the value related to the column: user_id
	 * @param user the user_id value
	 */
	public void setUser (com.yhcrt.weihu.core.entity.CmsUser user) {
		this.user = user;
	}

	public java.lang.String getTitle() {
		return title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	public java.lang.String getContent() {
		return content;
	}

	public void setContent(java.lang.String content) {
		this.content = content;
	}

	public java.lang.Integer getUps() {
		return ups;
	}

	public void setUps(java.lang.Integer ups) {
		this.ups = ups;
	}

	public java.lang.Integer getDowns() {
		return downs;
	}

	public void setDowns(java.lang.Integer downs) {
		this.downs = downs;
	}

	public java.lang.Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(java.lang.Integer readCount) {
		this.readCount = readCount;
	}

	public java.lang.Integer getCommtCount() {
		return commtCount;
	}

	public void setCommtCount(java.lang.Integer commtCount) {
		this.commtCount = commtCount;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.lang.Boolean getChecked() {
		return checked;
	}

	public void setChecked(java.lang.Boolean checked) {
		this.checked = checked;
	}

	public java.lang.Boolean getRecommend() {
		return recommend;
	}

	public void setRecommend(java.lang.Boolean recommend) {
		this.recommend = recommend;
	}

	public com.yhcrt.weihu.core.entity.CmsSite getSite() {
		return site;
	}

	public void setSite(com.yhcrt.weihu.core.entity.CmsSite site) {
		this.site = site;
	}

	
	public java.util.List<com.yhcrt.weihu.cms.entity.assist.BaoLiaoPicture> getPictures() {
		return pictures;
	}

	public void setPictures(java.util.List<com.yhcrt.weihu.cms.entity.assist.BaoLiaoPicture> pictures) {
		this.pictures = pictures;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.yhcrt.weihu.cms.entity.assist.CmsBaoLiao)) return false;
		else {
			com.yhcrt.weihu.cms.entity.assist.CmsBaoLiao cmsBaoLiao = (com.yhcrt.weihu.cms.entity.assist.CmsBaoLiao) obj;
			if (null == this.getId() || null == cmsBaoLiao.getId()) return false;
			else return (this.getId().equals(cmsBaoLiao.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}