package com.whty.mxbj.api.model;

import com.whty.mxbj.base.model.BaseModel;

public class NoteTemplate extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String noteId;
	private String noteName;
	private Integer pages;
	private Integer startPageId;
	private Double width;
	private Double height;
	private Double startX;
	private Double startY;
	private String coverImageUrl;
	private Boolean oddeventIssame;
	private String oddPageUrl;
	private String evenPageUrl;
	private String headpageUrl;
	private String headpageId;
	private Double actionRectStartX;
	private Double actionRectStartY;
	private Double actionRectEndX;
	private Double actionRectEndY;

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

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Integer getStartPageId() {
		return startPageId;
	}

	public void setStartPageId(Integer startPageId) {
		this.startPageId = startPageId;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getStartX() {
		return startX;
	}

	public void setStartX(Double startX) {
		this.startX = startX;
	}

	public Double getStartY() {
		return startY;
	}

	public void setStartY(Double startY) {
		this.startY = startY;
	}

	public String getCoverImageUrl() {
		return coverImageUrl;
	}

	public void setCoverImageUrl(String coverImageUrl) {
		this.coverImageUrl = coverImageUrl;
	}

	public Boolean getOddeventIssame() {
		return oddeventIssame;
	}

	public void setOddeventIssame(Boolean oddeventIssame) {
		this.oddeventIssame = oddeventIssame;
	}

	public String getOddPageUrl() {
		return oddPageUrl;
	}

	public void setOddPageUrl(String oddPageUrl) {
		this.oddPageUrl = oddPageUrl;
	}

	public String getEvenPageUrl() {
		return evenPageUrl;
	}

	public void setEvenPageUrl(String evenPageUrl) {
		this.evenPageUrl = evenPageUrl;
	}

	public String getHeadpageUrl() {
		return headpageUrl;
	}

	public void setHeadpageUrl(String headpageUrl) {
		this.headpageUrl = headpageUrl;
	}

	public String getHeadpageId() {
		return headpageId;
	}

	public void setHeadpageId(String headpageId) {
		this.headpageId = headpageId;
	}

	public Double getActionRectStartX() {
		return actionRectStartX;
	}

	public void setActionRectStartX(Double actionRectStartX) {
		this.actionRectStartX = actionRectStartX;
	}

	public Double getActionRectStartY() {
		return actionRectStartY;
	}

	public void setActionRectStartY(Double actionRectStartY) {
		this.actionRectStartY = actionRectStartY;
	}

	public Double getActionRectEndX() {
		return actionRectEndX;
	}

	public void setActionRectEndX(Double actionRectEndX) {
		this.actionRectEndX = actionRectEndX;
	}

	public Double getActionRectEndY() {
		return actionRectEndY;
	}

	public void setActionRectEndY(Double actionRectEndY) {
		this.actionRectEndY = actionRectEndY;
	}

}
