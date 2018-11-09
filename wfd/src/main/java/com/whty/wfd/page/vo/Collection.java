package com.whty.wfd.page.vo;

import java.util.Date;

/**
 * \* User: zjd \* Date: 2018/9/27 \* Time: 15:22 \* Description: \
 */
public class Collection {

	private String id;// ID 收藏ID根据类型来填写

	private Integer type;// 类型1是帖子；2是评论

	private Integer userId;// 用户主键

	private boolean isRead;// 是否已读

	private Date createTime;// 创建时间

	private Date updateTime;// 修改时间

	private String creatorName;// 创建者名字

	private String postContent;// 帖子的内容

	private String replyContent;// 回复内容

	private String replyPostContent;// 回复评论的帖子内容

	private String createTimeStr;// 创建时间String

	private String replyPostId;// 回复评论的帖子ID

	private String mlogoUrl;// 评论创建者图片

	private String plogoUrl;// 帖子创建者图片

	private String messageCreator;// 评论创建者

	private String postCreator;// 帖子创建者

	private String messageAudioUrl;// 评论语音

	private String messageImgUrl;// 评论图片

	public String getMessageAudioUrl() {
		return messageAudioUrl;
	}

	public void setMessageAudioUrl(String messageAudioUrl) {
		this.messageAudioUrl = messageAudioUrl;
	}

	public String getMessageImgUrl() {
		return messageImgUrl;
	}

	public void setMessageImgUrl(String messageImgUrl) {
		this.messageImgUrl = messageImgUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean read) {
		isRead = read;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyPostContent() {
		return replyPostContent;
	}

	public void setReplyPostContent(String replyPostContent) {
		this.replyPostContent = replyPostContent;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getReplyPostId() {
		return replyPostId;
	}

	public void setReplyPostId(String replyPostId) {
		this.replyPostId = replyPostId;
	}

	public String getMlogoUrl() {
		return mlogoUrl;
	}

	public void setMlogoUrl(String mlogoUrl) {
		this.mlogoUrl = mlogoUrl;
	}

	public String getPlogoUrl() {
		return plogoUrl;
	}

	public void setPlogoUrl(String plogoUrl) {
		this.plogoUrl = plogoUrl;
	}

	public String getMessageCreator() {
		return messageCreator;
	}

	public void setMessageCreator(String messageCreator) {
		this.messageCreator = messageCreator;
	}

	public String getPostCreator() {
		return postCreator;
	}

	public void setPostCreator(String postCreator) {
		this.postCreator = postCreator;
	}
}