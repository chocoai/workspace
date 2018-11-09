package com.whty.wfd.page.model;

import java.util.Date;

public class TPostMessageAudio {
	/**
	 * 
	 */
	private Integer id;

	/**
	 * 
	 */
	private String postMessageId;

	/**
	 * 
	 */
	private String downUrl;

	/**
	 * 
	 */
	private Date createTime;

	/**
	 * 
	 */
	private Date updateTime;

	private Integer audioUsetaking;

	public Integer getAudioUsetaking() {
		return audioUsetaking;
	}

	public void setAudioUsetaking(Integer audioUsetaking) {
		this.audioUsetaking = audioUsetaking;
	}

	/**
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 
	 * @return post_message_id
	 */
	public String getPostMessageId() {
		return postMessageId;
	}

	/**
	 * 
	 * @param postMessageId
	 */
	public void setPostMessageId(String postMessageId) {
		this.postMessageId = postMessageId == null ? null : postMessageId.trim();
	}

	/**
	 * 
	 * @return down_url
	 */
	public String getDownUrl() {
		return downUrl;
	}

	/**
	 * 
	 * @param downUrl
	 */
	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl == null ? null : downUrl.trim();
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
}