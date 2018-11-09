package com.yhcrt.weihu.cms.entity.assist.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the jc_friendlink_ctg table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="jc_friendlink_ctg"
 */

public abstract class BaseCmsTType  implements Serializable {

	public static String REF = "CmsTType";
	public static String PROP_NAME = "name";
	public static String PROP_SITE = "site";
	public static String PROP_ID = "id";


	// constructors
	public BaseCmsTType () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsTType (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseCmsTType (
		java.lang.Integer id,
		com.yhcrt.weihu.core.entity.CmsSite site,
		java.lang.String name) {

		this.setId(id);
		this.setSite(site);
		this.setName(name);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String name;
	private com.yhcrt.weihu.core.entity.CmsSite site;


	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="friendlinkctg_id"
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




	public com.yhcrt.weihu.core.entity.CmsSite getSite() {
		return site;
	}

	public void setSite(com.yhcrt.weihu.core.entity.CmsSite site) {
		this.site = site;
	}

	/**
	 * Return the value associated with the column: friendlinkctg_name
	 */
	public java.lang.String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: friendlinkctg_name
	 * @param name the friendlinkctg_name value
	 */
	public void setName (java.lang.String name) {
		this.name = name;
	}


	

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.yhcrt.weihu.cms.entity.assist.CmsFriendlinkCtg)) return false;
		else {
			com.yhcrt.weihu.cms.entity.assist.CmsFriendlinkCtg cmsFriendlinkCtg = (com.yhcrt.weihu.cms.entity.assist.CmsFriendlinkCtg) obj;
			if (null == this.getId() || null == cmsFriendlinkCtg.getId()) return false;
			else return (this.getId().equals(cmsFriendlinkCtg.getId()));
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