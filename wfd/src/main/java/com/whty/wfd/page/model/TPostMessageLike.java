package com.whty.wfd.page.model;

import java.util.Date;

public class TPostMessageLike extends TPostMessageLikeKey {
	/**
	 * 信息状态 0未读 1已读
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

	private String postId;

	private String postContent;

	private String creatorName;

	private String replyContent;

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	/**
	 * 信息状态 0未读 1已读
	 * 
	 * @return is_read 信息状态 0未读 1已读
	 */
	public Boolean getIsRead() {
		return isRead;
	}

	/**
	 * 信息状态 0未读 1已读
	 * 
	 * @param isRead
	 *            信息状态 0未读 1已读
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