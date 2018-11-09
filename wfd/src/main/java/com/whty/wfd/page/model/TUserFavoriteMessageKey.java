package com.whty.wfd.page.model;

public class TUserFavoriteMessageKey {
	/**
	 * 用户ID
	 */
	private Integer userId;

	/**
	 * 帖子主键
	 */
	private String postMessageId;

	/**
	 * 用户ID
	 * 
	 * @return user_id 用户ID
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 用户ID
	 * 
	 * @param userId
	 *            用户ID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 帖子主键
	 * 
	 * @return post_message_id 帖子主键
	 */
	public String getPostMessageId() {
		return postMessageId;
	}

	/**
	 * 帖子主键
	 * 
	 * @param postMessageId
	 *            帖子主键
	 */
	public void setPostMessageId(String postMessageId) {
		this.postMessageId = postMessageId;
	}
}