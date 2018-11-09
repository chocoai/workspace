package com.whty.wfd.page.model;

import java.util.Date;

public class TPlatePostEditImg {
	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 备用图片地址
	 */
	private String imgUrl;

	/**
	 * 用户发帖备用表ID
	 */
	private String platePostEditId;

	/**
	 * 创建者
	 */
	private Integer creator;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 主键
	 * 
	 * @return id 主键
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 主键
	 * 
	 * @param id
	 *            主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 备用图片地址
	 * 
	 * @return img_url 备用图片地址
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * 备用图片地址
	 * 
	 * @param imgUrl
	 *            备用图片地址
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl == null ? null : imgUrl.trim();
	}

	/**
	 * 用户发帖备用表ID
	 * 
	 * @return plate_post_edit_id 用户发帖备用表ID
	 */
	public String getPlatePostEditId() {
		return platePostEditId;
	}

	/**
	 * 用户发帖备用表ID
	 * 
	 * @param platePostEditId
	 *            用户发帖备用表ID
	 */
	public void setPlatePostEditId(String platePostEditId) {
		this.platePostEditId = platePostEditId == null ? null : platePostEditId.trim();
	}

	/**
	 * 创建者
	 * 
	 * @return creator 创建者
	 */
	public Integer getCreator() {
		return creator;
	}

	/**
	 * 创建者
	 * 
	 * @param creator
	 *            创建者
	 */
	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	/**
	 * 创建时间
	 * 
	 * @return create_time 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * 
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新时间
	 * 
	 * @return update_time 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * 
	 * @param updateTime
	 *            更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}