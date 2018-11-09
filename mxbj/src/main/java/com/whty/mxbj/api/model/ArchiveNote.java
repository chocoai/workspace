package com.whty.mxbj.api.model;

import java.util.Date;

import org.apache.commons.codec.binary.Base64;

import com.whty.mxbj.base.model.BaseModel;
import com.whty.mxbj.common.utils.NoteType;

public class ArchiveNote extends BaseModel {

	private static final long serialVersionUID = 2318090462236175291L;

	private String archiveNoteId;
	private String noteName;
	private String noteId;
	private Date lastEditTime;
	// private String pages;
	// private String startPageId;
	private Date startTime;
	private Date endTime;
	private String userId;
	private String userPlatformCode;
	private String subjectId;
	private String subjectName;
	private String archiveTime;
	private String platformName;
	private String userName;
	private String account;
	private String accountType;
	private String phoneNumber;
	private String coverImageUrl;
	private String readerImageUrl;
	private String shareMnoteAddBase64;
	private String notType;

	public void setReaderImageUrl(String readerImageUrl) {
		this.readerImageUrl = readerImageUrl;
	}

	public String getReaderImageUrl() {
		if (readerImageUrl == null) {
			return "http://whty.bj.bcebos.com/mxbj/readerimageurl/471x600.png";
		}

		return readerImageUrl;
	}

	public String getNotType() {
		return NoteType.ARCHIVE_NOTE;
	}

	public String getShareMnoteAddBase64() {
		String noteType = getNotType();
		StringBuffer sb = new StringBuffer();
		sb.append(noteType).append("#").append(userPlatformCode).append("#").append(userId).append("#").append(noteId)
				.append("#").append(getCoverImageUrl());
		String value = sb.toString();
		byte[] b = Base64.encodeBase64(value.getBytes(), true);
		String id = new String(b);
		return "mnote.html?id=" + id;
	}

	public void setShareMnoteAddBase64(String shareMnoteAddBase64) {
		this.shareMnoteAddBase64 = shareMnoteAddBase64;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getCoverImageUrl() {
		if (coverImageUrl == null) {
			coverImageUrl = "http://whty.bj.bcebos.com/mnote/notesconfig/0_0_0_cover.png";
		}
		return coverImageUrl;
	}

	public void setCoverImageUrl(String coverImageUrl) {
		this.coverImageUrl = coverImageUrl;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getArchiveTime() {
		return archiveTime;
	}

	public void setArchiveTime(String archiveTime) {
		this.archiveTime = archiveTime;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
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

	public String getArchiveNoteId() {
		return archiveNoteId;
	}

	public void setArchiveNoteId(String archiveNoteId) {
		this.archiveNoteId = archiveNoteId;
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

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
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

	// public String getPages() {
	// return pages;
	// }

	// public void setPages(String pages) {
	// this.pages = pages;
	// }

	// public String getStartPageId() {
	// return startPageId;
	// }

	// public void setStartPageId(String startPageId) {
	// this.startPageId = startPageId;
	// }

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
