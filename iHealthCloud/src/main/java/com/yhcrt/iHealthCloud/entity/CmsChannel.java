package com.yhcrt.iHealthCloud.entity;

public class CmsChannel {
    private Integer channelId;

    private Integer parentId;

    private String channelName;

    private String channelDesc;

    private String channelUrl;

    private String openType;

    private String titleImg;

    private String channelTpl;

    private String channelMobileTpl;

    private String contentTpl;

    private String contentMobileTpl;

    private Integer status;

    private String allowComment;

    private String createTime;

    private Integer orderNum;
    
    private CmsChannel parentChannel;

	public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    public String getChannelDesc() {
        return channelDesc;
    }

    public void setChannelDesc(String channelDesc) {
        this.channelDesc = channelDesc == null ? null : channelDesc.trim();
    }

    public String getChannelUrl() {
        return channelUrl;
    }

    public void setChannelUrl(String channelUrl) {
        this.channelUrl = channelUrl == null ? null : channelUrl.trim();
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType == null ? null : openType.trim();
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg == null ? null : titleImg.trim();
    }

    public String getChannelTpl() {
        return channelTpl;
    }

    public void setChannelTpl(String channelTpl) {
        this.channelTpl = channelTpl == null ? null : channelTpl.trim();
    }

    public String getChannelMobileTpl() {
        return channelMobileTpl;
    }

    public void setChannelMobileTpl(String channelMobileTpl) {
        this.channelMobileTpl = channelMobileTpl == null ? null : channelMobileTpl.trim();
    }

    public String getContentTpl() {
        return contentTpl;
    }

    public void setContentTpl(String contentTpl) {
        this.contentTpl = contentTpl == null ? null : contentTpl.trim();
    }

    public String getContentMobileTpl() {
        return contentMobileTpl;
    }

    public void setContentMobileTpl(String contentMobileTpl) {
        this.contentMobileTpl = contentMobileTpl == null ? null : contentMobileTpl.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(String allowComment) {
        this.allowComment = allowComment == null ? null : allowComment.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public CmsChannel getParentChannel() {
		return parentChannel;
	}

	public void setParentChannel(CmsChannel parentChannel) {
		this.parentChannel = parentChannel;
	}
}