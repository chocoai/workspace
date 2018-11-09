package com.yhcrt.weihu.bbs.entity.base;

import java.io.Serializable;

public abstract class BaseBbsAlbumImg  implements Serializable {

	public static String REF = "BbsAlbumImg";
	public static String PROP_ID = "id";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_CREATER_ID = "creater";
	public static String PROP_NAME = "name";
	public static String PROP_DESCRIPTION = "description";
	public static String PROP_STATUS = "status";
	public static String PROP_IMG_URL = "imgUrl";
	public static String PROP_PRIME_COUNT = "primeCount";
	public static String PROP_COMMENT_COUNT = "commentCount";
	public static String PROP_ALBUM ="album";

	public BaseBbsAlbumImg () {
		initialize();
	}

	public BaseBbsAlbumImg (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	public BaseBbsAlbumImg (
		java.lang.Integer id,
		java.util.Date createTime,
		com.yhcrt.weihu.bbs.entity.BbsUser creater,
		java.lang.String name,
		java.lang.String description,
		java.lang.Integer status,
		java.lang.String imgUrl,
		java.lang.Integer primeCount,
		java.lang.Integer commentCount,
		com.yhcrt.weihu.bbs.entity.BbsAlbum album) {

		this.setId(id);
		this.setCreateTime(createTime);
		this.setCreater(creater);
		this.setName(name);
		this.setDescription(description);
		this.setStatus(status);
		this.setImgUrl(imgUrl);
		this.setPrimeCount(primeCount);
		this.setCommentCount(commentCount);
		this.setAlbum(album);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.util.Date createTime;
	private java.lang.String name;
	private java.lang.String description;
	private java.lang.Integer status;
	private java.lang.String imgUrl;
	private java.lang.Integer primeCount;
	private java.lang.Integer commentCount;
	
	// many to one
	private com.yhcrt.weihu.bbs.entity.BbsUser creater;
	private com.yhcrt.weihu.bbs.entity.BbsAlbum album;


	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getDescription() {
		return description;
	}

	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	public java.lang.Integer getStatus() {
		return status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	public java.lang.String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(java.lang.String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public java.lang.Integer getPrimeCount() {
		return primeCount;
	}

	public void setPrimeCount(java.lang.Integer primeCount) {
		this.primeCount = primeCount;
	}

	public java.lang.Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(java.lang.Integer commentCount) {
		this.commentCount = commentCount;
	}

	public com.yhcrt.weihu.bbs.entity.BbsAlbum getAlbum() {
		return album;
	}

	public void setAlbum(com.yhcrt.weihu.bbs.entity.BbsAlbum album) {
		this.album = album;
	}

	/**
	 * Return the value associated with the column: creater_id
	 */
	public com.yhcrt.weihu.bbs.entity.BbsUser getCreater () {
		return creater;
	}

	/**
	 * Set the value related to the column: creater_id
	 * @param creater the creater_id value
	 */
	public void setCreater (com.yhcrt.weihu.bbs.entity.BbsUser creater) {
		this.creater = creater;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.yhcrt.weihu.bbs.entity.BbsCollection)) return false;
		else {
			com.yhcrt.weihu.bbs.entity.BbsAlbum bbsAlbum = (com.yhcrt.weihu.bbs.entity.BbsAlbum) obj;
			if (null == this.getId() || null == bbsAlbum.getId()) return false;
			else return (this.getId().equals(bbsAlbum.getId()));
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