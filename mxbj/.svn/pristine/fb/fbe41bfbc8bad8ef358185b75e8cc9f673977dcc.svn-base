package com.whty.mxbj.api.model;

import java.util.Date;

import org.apache.commons.codec.binary.Base64;

import com.whty.mxbj.base.model.BaseModel;

public class BaseNote extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String noteName;
	private String noteId;
	private String userPlatformCode;
	private String userId;
	private String coverImageUrl;// 首页图片地址
	private String shareMnoteAddBase64;// 墨香笔记共享路径

	private String readerImageUrl;

	private Date startTime;
	private Date endTime;

	private String noteType;

	private Date showUpdateTime;

	public Date getShowUpdateTime() {
		if (updateTime != null) {
			return updateTime;
		} else {
			return createTime;
		}
	}

	public String getShareMnoteAddBase64() {
		String noteType = getNoteType();
		StringBuffer sb = new StringBuffer();
		sb.append(noteType).append("#").append(userPlatformCode).append("#").append(userId).append("#").append(noteId)
				.append("#").append(getCoverImageUrl());
		String value = sb.toString();
		byte[] b = Base64.encodeBase64(value.getBytes(), true);
		String id = new String(b);
		return "mnote.html?id=" + id;
	}

	public String getNoteType() {
		return noteType;
	}

	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}

	public String getCoverImageUrl() {
		if (coverImageUrl == null) {
			coverImageUrl = "http://whty.bj.bcebos.com/mnote/notesconfig/0_0_0_cover.png";
		}
		return coverImageUrl;
	}

	public String getNoteName() {
		return noteName;
	}

	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	public String getUserPlatformCode() {
		return userPlatformCode;
	}

	public void setUserPlatformCode(String userPlatformCode) {
		this.userPlatformCode = userPlatformCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setCoverImageUrl(String coverImageUrl) {
		this.coverImageUrl = coverImageUrl;
	}

	public void setShareMnoteAddBase64(String shareMnoteAddBase64) {
		this.shareMnoteAddBase64 = shareMnoteAddBase64;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
