package com.whty.mxbj.api.model;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.whty.mxbj.base.model.BaseModel;
import com.whty.mxbj.common.utils.NoteType;

/**
 * 笔记本
 * 
 * @author zhangzheng
 *
 */
public class Note extends BaseModel {

	private static final long serialVersionUID = 157036040158564180L;

	private String userId;
	private String noteId;
	private String noteName;
	private Date lastEditTime;
	private String userPlatformCode;
	private String subjectId;
	private String subjectName;
	private String platformName;
	private String userName;
	private String account;
	private String accountType;
	private String phoneNumber;
	private String coverImageUrl;
	private String readerImageUrl;
	private String shareMnoteAddBase64;
	private String notType;

	public String getReaderImageUrl() {
		if(readerImageUrl==null){
			return "http://whty.bj.bcebos.com/mxbj/readerimageurl/471x600.png";
		}
		return readerImageUrl;
	}

	public void setReaderImageUrl(String readerImageUrl) {
		this.readerImageUrl = readerImageUrl;
	}

	public String getNotType() {
		return NoteType.NOTE;
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

	public String getCoverImageUrl() {
		if (coverImageUrl == null) {
			coverImageUrl = "http://whty.bj.bcebos.com/mnote/notesconfig/0_0_0_cover.png";
		}

		return coverImageUrl;
	}

	public void setCoverImageUrl(String coverImageUrl) {
		this.coverImageUrl = coverImageUrl;
	}

	public String getPhoneNumber() {
		return phoneNumber;
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

	private List<Page> pageList;
	// private String width;
	// private String height;
	// private String startX;
	// private String startY;
	// private String coverImagePath;
	// private boolean oddEvenIssame;
	// private String oddPageImagePath;
	// private String evenPageImagePath;
	// private String actionRectStartX;
	// private String actionRectStartY;
	// private String actionRectEndX;
	// private String actionRectEndY;

	// public String getCoverImagePath() {
	// return coverImagePath;
	// }
	//
	// public void setCoverImagePath(String coverImagePath) {
	// this.coverImagePath = coverImagePath;
	// }
	//
	// public String getOddPageImagePath() {
	// return oddPageImagePath;
	// }
	//
	// public void setOddPageImagePath(String oddPageImagePath) {
	// this.oddPageImagePath = oddPageImagePath;
	// }
	//
	// public boolean isOddEvenIssame() {
	// return oddEvenIssame;
	// }
	//
	// public void setOddEvenIssame(boolean oddEvenIssame) {
	// this.oddEvenIssame = oddEvenIssame;
	// }
	//
	// public String getEvenPageImagePath() {
	// return evenPageImagePath;
	// }
	//
	// public void setEvenPageImagePath(String evenPageImagePath) {
	// this.evenPageImagePath = evenPageImagePath;
	// }
	//
	// public String getActionRectStartX() {
	// return actionRectStartX;
	// }
	//
	// public void setActionRectStartX(String actionRectStartX) {
	// this.actionRectStartX = actionRectStartX;
	// }
	//
	// public String getActionRectStartY() {
	// return actionRectStartY;
	// }
	//
	// public void setActionRectStartY(String actionRectStartY) {
	// this.actionRectStartY = actionRectStartY;
	// }
	//
	// public String getActionRectEndX() {
	// return actionRectEndX;
	// }
	//
	// public void setActionRectEndX(String actionRectEndX) {
	// this.actionRectEndX = actionRectEndX;
	// }
	//
	// public String getActionRectEndY() {
	// return actionRectEndY;
	// }
	//
	// public void setActionRectEndY(String actionRectEndY) {
	// this.actionRectEndY = actionRectEndY;
	// }

	// public String getWidth() {
	// return width;
	// }
	//
	// public void setWidth(String width) {
	// this.width = width;
	// }
	//
	// public String getHeight() {
	// return height;
	// }
	//
	// public void setHeight(String height) {
	// this.height = height;
	// }
	//
	// public String getStartX() {
	// return startX;
	// }
	//
	// public void setStartX(String startX) {
	// this.startX = startX;
	// }
	//
	// public String getStartY() {
	// return startY;
	// }
	//
	// public void setStartY(String startY) {
	// this.startY = startY;
	// }

	// public String getIsDelete() {
	// return isDelete;
	// }
	//
	// public void setIsDelete(String isDelete) {
	// this.isDelete = isDelete;
	// }

	// public String getPages() {
	// return pages;
	// }

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

	// public void setPages(String pages) {
	// this.pages = pages;
	// }

	public List<Page> getPageList() {
		return pageList;
	}

	public void setPageList(List<Page> pageList) {
		this.pageList = pageList;
	}

	// public String getStartPageId() {
	// return startPageId;
	// }

	// public void setStartPageId(String startPageId) {
	// this.startPageId = startPageId;
	// }

	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	public String getNoteName() {
		return noteName;
	}

	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

}
