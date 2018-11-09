package com.whty.wfd.page.model;

import java.util.Date;
import java.util.List;

import com.whty.wfd.common.utils.RelativeDateFormat;

public class TPostMessage {

	/**
	 * 
	 */
	private String id;

	/**
	 * 帖子主键
	 */
	private String platePostId;

	/**
	 * 评论人
	 */
	private Integer userId;
	private String userName;

	/**
	 * 信息状态 0未读 1已读
	 */
	private Boolean isRead;

	/**
	 * 是否置顶(0:否；1:是)
	 */
	private Boolean isTop;
	/**
	 * 是否删除（0否1是）
	 */
	private Boolean isDelete;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 回复内容
	 */
	private String content;

	/**
	 * @对象
	 */
	private Integer receiver;
	private String receiverName;

	private String postContent;

	private List<TPostMessageImg> tPostMessageImgs;

	private String messageName;

	private String createTimeStr;

	private String logoUrl;

	private Date favoriteTime;

	private String audioUrl;

	private String imgUrl;

	private List<TPostMessageAudio> tPostMessageAudios;

	public String getAudioUrl() {
		return audioUrl;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public List<TPostMessageAudio> gettPostMessageAudios() {
		return tPostMessageAudios;
	}

	public void settPostMessageAudios(List<TPostMessageAudio> tPostMessageAudios) {
		this.tPostMessageAudios = tPostMessageAudios;
	}

	public Date getFavoriteTime() {
		return favoriteTime;
	}

	public void setFavoriteTime(Date favoriteTime) {
		this.favoriteTime = favoriteTime;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Integer getReceiver() {
		return receiver;
	}

	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getMessageName() {
		return messageName;
	}

	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public List<TPostMessageImg> gettPostMessageImgs() {
		return tPostMessageImgs;
	}

	public void settPostMessageImgs(List<TPostMessageImg> tPostMessageImgs) {
		this.tPostMessageImgs = tPostMessageImgs;
	}

	public Boolean getDelete() {
		return isDelete;
	}

	public void setDelete(Boolean delete) {
		isDelete = delete;
	}

	/**
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	private String userType;

	private Integer likeCount;

	private List<TPostMessageImg> messageImgList;

	private String relativeCreateTime;

	public String getRelativeCreateTime() {
		return RelativeDateFormat.format(createTime);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public List<TPostMessageImg> getMessageImgList() {
		return messageImgList;
	}

	public void setMessageImgList(List<TPostMessageImg> messageImgList) {
		this.messageImgList = messageImgList;
	}

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
	 * 评论人
	 * 
	 * @return user_id 评论人
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 评论人
	 * 
	 * @param userId
	 *            评论人
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	 * 是否置顶(0:否；1:是)
	 * 
	 * @return is_top 是否置顶(0:否；1:是)
	 */
	public Boolean getIsTop() {
		return isTop;
	}

	/**
	 * 是否置顶(0:否；1:是)
	 * 
	 * @param isTop
	 *            是否置顶(0:否；1:是)
	 */
	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
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

	/**
	 * 回复内容
	 * 
	 * @return content 回复内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 回复内容
	 * 
	 * @param content
	 *            回复内容
	 */
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}