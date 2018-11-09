package com.whty.wfd.page.model;

import java.util.Date;

public class TPlatePostEditAudio {
	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 备用帖子ID
	 */
	private String platePostEditId;

	/**
	 * 备用帖子语音地址
	 */
	private String audioUrl;

	/**
	 * 备用帖子语音时长
	 */
	private Integer audioUsetaking;

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
	 * 备用帖子ID
	 * 
	 * @return plate_post_edit_id 备用帖子ID
	 */
	public String getPlatePostEditId() {
		return platePostEditId;
	}

	/**
	 * 备用帖子ID
	 * 
	 * @param platePostEditId
	 *            备用帖子ID
	 */
	public void setPlatePostEditId(String platePostEditId) {
		this.platePostEditId = platePostEditId == null ? null : platePostEditId.trim();
	}

	/**
	 * 备用帖子语音地址
	 * 
	 * @return audio_url 备用帖子语音地址
	 */
	public String getAudioUrl() {
		return audioUrl;
	}

	/**
	 * 备用帖子语音地址
	 * 
	 * @param audioUrl
	 *            备用帖子语音地址
	 */
	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl == null ? null : audioUrl.trim();
	}

	/**
	 * 备用帖子语音时长
	 * 
	 * @return audio_usetaking 备用帖子语音时长
	 */
	public Integer getAudioUsetaking() {
		return audioUsetaking;
	}

	/**
	 * 备用帖子语音时长
	 * 
	 * @param audioUsetaking
	 *            备用帖子语音时长
	 */
	public void setAudioUsetaking(Integer audioUsetaking) {
		this.audioUsetaking = audioUsetaking;
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