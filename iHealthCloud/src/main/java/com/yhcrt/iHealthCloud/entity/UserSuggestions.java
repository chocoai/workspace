package com.yhcrt.iHealthCloud.entity;

public class UserSuggestions {
	
    private Integer cid;

    private String userId;

    private String suggestionContent;

    private String suggestionTime;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid == null ? null : cid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getSuggestionContent() {
        return suggestionContent;
    }

    public void setSuggestionContent(String suggestionContent) {
        this.suggestionContent = suggestionContent == null ? null : suggestionContent.trim();
    }

    public String getSuggestionTime() {
        return suggestionTime;
    }

    public void setSuggestionTime(String suggestionTime) {
        this.suggestionTime = suggestionTime == null ? null : suggestionTime.trim();
    }
}