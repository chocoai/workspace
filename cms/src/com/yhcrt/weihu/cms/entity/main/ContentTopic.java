package com.yhcrt.weihu.cms.entity.main;

import java.io.Serializable;

public class ContentTopic implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Integer topicId;

    private Integer topicChannelId;

    public ContentTopic() {
	super();
    }

    public ContentTopic(Integer topicId, Integer topicChannelId) {
	super();
	this.topicId = topicId;
	this.topicChannelId = topicChannelId;
    }

    public Integer getTopicId() {
	return this.topicId;
    }

    public Integer getTopicChannelId() {
	return this.topicChannelId;
    }

    public void setTopicId(Integer topicId) {
	this.topicId = topicId;
    }

    public void setTopicChannelId(Integer topicChannelId) {
	this.topicChannelId = topicChannelId;
    }

}