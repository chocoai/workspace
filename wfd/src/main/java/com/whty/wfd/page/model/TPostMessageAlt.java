package com.whty.wfd.page.model;

import java.util.Date;

public class TPostMessageAlt {
	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 被@的人的Id
	 */
	private Integer altUserId;

	/**
	 * 消息ID
	 */
	private String messageId;

	/**
	 * 是否已读
	 */
	private Boolean isRead;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
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
	 * 被@的人的Id
	 * 
	 * @return alt_user_id 被@的人的Id
	 */
	public Integer getAltUserId() {
		return altUserId;
	}

	/**
	 * 被@的人的Id
	 * 
	 * @param altUserId
	 *            被@的人的Id
	 */
	public void setAltUserId(Integer altUserId) {
		this.altUserId = altUserId;
	}

	/**
	 * 消息ID
	 * 
	 * @return message_id 消息ID
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * 消息ID
	 * 
	 * @param messageId
	 *            消息ID
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId == null ? null : messageId.trim();
	}

	/**
	 * 是否已读
	 * 
	 * @return is_read 是否已读
	 */
	public Boolean getIsRead() {
		return isRead;
	}

	/**
	 * 是否已读
	 * 
	 * @param isRead
	 *            是否已读
	 */
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
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
	 * 修改时间
	 * 
	 * @return update_time 修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间
	 * 
	 * @param updateTime
	 *            修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}