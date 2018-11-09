package com.yhcrt.weihu.cms.entity.assist.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_friendlink table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_tt"
 */

public abstract class BaseCmsTT  implements Serializable {

	public static String REF = "CmsTT";
	public static String PROP_ISADMIN = "isAdmin";
	public static String PROP_RECOMMEND = "recommend";
	public static String PROP_CHECKED = "checked";
	public static String PROP_EXP_TIME = "expTime";
	public static String PROP_READCOUNT = "readCount";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_DESCRIPTION = "description";
	public static String PROP_CONTACT = "contact";
	public static String PROP_TITLE = "title";
	public static String PROP_NAME = "name";
	public static String PROP_DEMAND = "demand";
	public static String PROP_SITE = "site";
	public static String PROP_ADMIN = "admin";
	public static String PROP_TYPE = "type";
	public static String PROP_USER = "user";
	public static String PROP_ID = "id";


	// constructors
	public BaseCmsTT () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsTT (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCmsTT (
		java.lang.Integer id,
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

		this.setId(id);
		this.setType(type);
		this.setUser(user);
		this.setSite(site);
		this.setDemand(demand);
		this.setName(name);
		this.setTitle(title);
		this.setContact(contact);
		this.setDescription(description);
		this.setExpTime(expTime);
		this.setCreateTime(createTime);
		this.setReadCount(readCount);
		this.setChecked(checked);
		this.setRecommend(recommend);
		this.setIsAdmin(isAdmin);
		initialize();
	}

	protected void initialize () {
		
	}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.Integer demand;
	private java.lang.String name;
	private java.lang.String title;
	private java.lang.String contact;
	private java.lang.String description;
	private java.util.Date expTime;
	private java.util.Date createTime;
	private java.lang.Integer readCount;
	private java.lang.Boolean checked;
	private java.lang.Boolean recommend;
	private java.lang.Integer isAdmin;
	// many to one
	private com.yhcrt.weihu.cms.entity.assist.CmsTType type;
	private com.yhcrt.weihu.core.entity.CmsUser user;
	private com.yhcrt.weihu.core.entity.CmsUser admin;
	private com.yhcrt.weihu.core.entity.CmsSite site;
	
	private java.util.List<com.yhcrt.weihu.cms.entity.assist.TTPicture> pictures;
	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="friendlink_id"
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

	/**
	 * Return the value associated with the column: site_name
	 */
	public java.lang.String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: site_name
	 * @param name the site_name value
	 */
	public void setName (java.lang.String name) {
		this.name = name;
	}

	public java.lang.Integer getDemand() {
		return demand;
	}

	public void setDemand(java.lang.Integer demand) {
		this.demand = demand;
	}

	public java.lang.String getTitle() {
		return title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	public java.lang.Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(java.lang.Integer readCount) {
		this.readCount = readCount;
	}

	public java.lang.String getContact() {
		return contact;
	}

	public void setContact(java.lang.String contact) {
		this.contact = contact;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.util.Date getExpTime() {
		return expTime;
	}

	public void setExpTime(java.util.Date expTime) {
		this.expTime = expTime;
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

	public com.yhcrt.weihu.cms.entity.assist.CmsTType getType() {
		return type;
	}

	public void setType(com.yhcrt.weihu.cms.entity.assist.CmsTType type) {
		this.type = type;
	}

	public com.yhcrt.weihu.core.entity.CmsUser getUser() {
		return user;
	}

	public void setUser(com.yhcrt.weihu.core.entity.CmsUser user) {
		this.user = user;
	}

	public com.yhcrt.weihu.core.entity.CmsUser getAdmin() {
		return admin;
	}

	public void setAdmin(com.yhcrt.weihu.core.entity.CmsUser admin) {
		this.admin = admin;
	}

	public com.yhcrt.weihu.core.entity.CmsSite getSite() {
		return site;
	}

	public void setSite(com.yhcrt.weihu.core.entity.CmsSite site) {
		this.site = site;
	}

	public java.util.List<com.yhcrt.weihu.cms.entity.assist.TTPicture> getPictures() {
		return pictures;
	}

	public void setPictures(java.util.List<com.yhcrt.weihu.cms.entity.assist.TTPicture> pictures) {
		this.pictures = pictures;
	}

	public java.lang.Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(java.lang.Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.yhcrt.weihu.cms.entity.assist.CmsTT)) return false;
		else {
			com.yhcrt.weihu.cms.entity.assist.CmsTT cmsTT = (com.yhcrt.weihu.cms.entity.assist.CmsTT) obj;
			if (null == this.getId() || null == cmsTT.getId()) return false;
			else return (this.getId().equals(cmsTT.getId()));
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