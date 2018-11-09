package com.whty.wfd.page.model;

public class TPostLikeKey {
	/**
	 * 帖子主键
	 */
	private String platePostId;

	/**
	 * 用户主键
	 */
	private Integer userId;

	/**
	 * 帖子主键
	 * 
	 * @return plate_post_id 帖子主键
	 */
	public String getPlatePostId() {
		return platePostId;
	}

	/**
	 * 帖子主键
	 * 
	 * @param platePostId
	 *            帖子主键
	 */
	public void setPlatePostId(String platePostId) {
		this.platePostId = platePostId;
	}

	/**
	 * 用户主键
	 * 
	 * @return user_id 用户主键
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 用户主键
	 * 
	 * @param userId
	 *            用户主键
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}