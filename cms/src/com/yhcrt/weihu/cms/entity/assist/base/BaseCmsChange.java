package com.yhcrt.weihu.cms.entity.assist.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_job_apply table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_job_apply"
 */

public abstract class BaseCmsChange  implements Serializable {

	public static String REF = "CmsChange";
	public static String PROP_ADDRESS = "address";
	public static String PROP_TITLE = "title";
	public static String PROP_BZ = "bz";
	public static String PROP_STATUS = "status";
	public static String PROP_NAME = "name";
	public static String PROP_CONTACT = "contact";
	public static String PROP_USER = "user";
	public static String PROP_ID = "id";
	public static String PROP_CONTENT = "content";
	public static String PROP_CHANGE_TIME = "changeTime";


	// constructors
	public BaseCmsChange () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsChange (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCmsChange (
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

		this.setId(id);
		this.setContent(content);
		this.setUser(user);
		this.setTitle(title);
		this.setBz(bz);
		this.setName(name);
		this.setContact(contact);
		this.setAddress(address);
		this.setStatus(status);
		this.setChangeTime(changeTime);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String name;
	private java.lang.String contact;
	private java.lang.String bz;
	private java.lang.Integer status;
	private java.util.Date changeTime;
	private java.lang.String address;
	private java.lang.String title;
	// many to one
	private com.yhcrt.weihu.cms.entity.main.Content content;
	private com.yhcrt.weihu.core.entity.CmsUser user;



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




	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getTitle() {
		return title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	public java.lang.String getContact() {
		return contact;
	}

	public void setContact(java.lang.String contact) {
		this.contact = contact;
	}

	public java.lang.String getBz() {
		return bz;
	}

	public void setBz(java.lang.String bz) {
		this.bz = bz;
	}

	public java.lang.Integer getStatus() {
		return status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	public java.util.Date getChangeTime() {
		return changeTime;
	}

	public void setChangeTime(java.util.Date changeTime) {
		this.changeTime = changeTime;
	}

	public java.lang.String getAddress() {
		return address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	/**
	 * Return the value associated with the column: content_id
	 */
	public com.yhcrt.weihu.cms.entity.main.Content getContent () {
		return content;
	}

	/**
	 * Set the value related to the column: content_id
	 * @param content the content_id value
	 */
	public void setContent (com.yhcrt.weihu.cms.entity.main.Content content) {
		this.content = content;
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



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.yhcrt.weihu.cms.entity.assist.CmsChange)) return false;
		else {
			com.yhcrt.weihu.cms.entity.assist.CmsChange cmsChange = (com.yhcrt.weihu.cms.entity.assist.CmsChange) obj;
			if (null == this.getId() || null == cmsChange.getId()) return false;
			else return (this.getId().equals(cmsChange.getId()));
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