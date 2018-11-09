package com.yhcrt.iHealthCloud.entity;

public class CmsContent {
    private Integer contentId;

    private Integer channelId;

    private String title;

    private String subtitle;

    private String author;

    private String releaseTime;

    private String origin;

    private String fromUrl;

    private String titleImg;

    private String keyword;

    private String tag;

    private String description;

    private String contentType;

    private String contentTpl;

    private String contentMobileTpl;

    private Integer isRecommend;

    private Integer allowComment;

    private Integer status;

    private String createUser;

    private Integer topLevel;

    private Integer readAmount;

    private Integer orderNum;

    private String remark;

    private String content;
    
	private Integer isSmallpro;  //0不推荐 1推荐
    
    private CmsChannel channel;

	public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle == null ? null : subtitle.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime == null ? null : releaseTime.trim();
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }

    public String getFromUrl() {
        return fromUrl;
    }

    public void setFromUrl(String fromUrl) {
        this.fromUrl = fromUrl == null ? null : fromUrl.trim();
    }

    public String getTitleImg() {
        return titleImg == null ? "" : titleImg.trim();
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg == null ? null : titleImg.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType == null ? null : contentType.trim();
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

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Integer getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(Integer allowComment) {
        this.allowComment = allowComment;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Integer getTopLevel() {
        return topLevel;
    }

    public void setTopLevel(Integer topLevel) {
        this.topLevel = topLevel;
    }

    public Integer getReadAmount() {
        return readAmount;
    }

    public void setReadAmount(Integer readAmount) {
        this.readAmount = readAmount;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
    

    public CmsChannel getChannel() {
		return channel;
	}

	public void setChannel(CmsChannel channel) {
		this.channel = channel;
	}

	public Integer getIsSmallpro() {
		return isSmallpro;
	}

	public void setIsSmallpro(Integer isSmallpro) {
		this.isSmallpro = isSmallpro;
	}
	
}