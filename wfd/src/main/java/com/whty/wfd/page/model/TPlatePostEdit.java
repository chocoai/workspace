package com.whty.wfd.page.model;

import java.util.Date;

public class TPlatePostEdit {
	/**
	 * 主键
	 */
	private String id;

	/**
	 * 板块主键
	 */
	private String plateId;

	/**
	 * 创建者
	 */
	private Integer creator;

	/**
	 * @用户的ID集合
	 */
	private String atUserId;

	/**
	 * 
	 */
	private Date createTime;

	/**
	 * 
	 */
	private Date updateTime;

	/**
	 * 帖子内容
	 */
	private String content;

	/**
	 * 主键
	 * 
	 * @return id 主键
	 */
	public String getId() {
		return id;
	}

	/**
	 * 主键
	 * 
	 * @param id
	 *            主键
	 */
	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	/**
	 * 板块主键
	 * 
	 * @return plate_id 板块主键
	 */
	public String getPlateId() {
		return plateId;
	}

	/**
	 * 板块主键
	 * 
	 * @param plateId
	 *            板块主键
	 */
	public void setPlateId(String plateId) {
		this.plateId = plateId == null ? null : plateId.trim();
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
	 * @用户的ID集合
	 * @return at_user_id @用户的ID集合
	 */
	public String getAtUserId() {
		return atUserId;
	}

	/**
	 * @用户的ID集合
	 * @param atUserId
	 * 			@用户的ID集合
	 */
	public void setAtUserId(String atUserId) {
		this.atUserId = atUserId == null ? null : atUserId.trim();
	}

	/**
	 * 
	 * @return create_time
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 
	 * @return update_time
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 
	 * @param updateTime
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 帖子内容
	 * 
	 * @return content 帖子内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 帖子内容
	 * 
	 * @param content
	 *            帖子内容
	 */
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}