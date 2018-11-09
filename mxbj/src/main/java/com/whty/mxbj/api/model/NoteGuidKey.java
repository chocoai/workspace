package com.whty.mxbj.api.model;

import com.whty.mxbj.base.model.BaseModel;

public class NoteGuidKey extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String userPlatformCode;
	private String everNoteId;
	private String guidKeys;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPlatformCode() {
		return userPlatformCode;
	}

	public void setUserPlatformCode(String userPlatformCode) {
		this.userPlatformCode = userPlatformCode;
	}

	public String getEverNoteId() {
		return everNoteId;
	}

	public void setEverNoteId(String everNoteId) {
		this.everNoteId = everNoteId;
	}

	public String getGuidKeys() {
		return guidKeys;
	}

	public void setGuidKeys(String guidKeys) {
		this.guidKeys = guidKeys;
	}

}
