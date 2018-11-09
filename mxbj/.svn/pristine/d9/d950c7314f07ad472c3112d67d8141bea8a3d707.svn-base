package com.whty.mxbj.api.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import com.whty.mxbj.base.exception.BusinessException;

public interface NoteService {

	public List<Map<String, Object>> getNotes(Map<String, Object> map);

	public void archiveNote(Map<String, Object> map);

	public Map<String, Object> archiveListCheckParam(String body) throws IOException, BusinessException;

	public Map<String, Object> pageDataSubmitCheckParam(String body) throws IOException, BusinessException;

	public void pagetDataSubmit(Map<String, Object> map) throws IOException, BusinessException;

	public Map<String, Object> archiveNoteCheckParam(String body) throws IOException, BusinessException;

	public Map<String, Object> addCheckParam(String body) throws IOException, BusinessException;

	public void saveOrUpdate(Map<String, Object> map);

	public Map<String, Object> removeCheckParam(String body) throws IOException, BusinessException;

	// public void remove(Map<String, Object> map);

	public Map<String, Object> modifyCheckParam(String body) throws IOException, BusinessException;

	public void modifyName(Map<String, Object> map);

	public Map<String, Object> listCheckParam(String body) throws IOException, BusinessException;

	public Map<String, Object> archivePageListCheckParam(String body) throws IOException, BusinessException;

	public List<Map<String, Object>> getArchivePageList(Map<String, Object> map);

	public List<Map<String, Object>> getArchiveNotes(Map<String, Object> map);

	public Map<String, Object> addBatchCheckParam(String body) throws IOException, BusinessException;

	public void saveNotesBatch(Map<String, Object> map);

	public Map<String, Object> modifyNoteListCheckParam(String body) throws IOException, BusinessException;

	public void modifyNoteList(Map<String, Object> params);

	public Map<String, Object> shareToCloudCheckParam(String body) throws IOException, BusinessException;

	public void shareToCloud(Map<String, Object> map);

	public Map<String, Object> activateNoticeCheckParam(String body) throws IOException, BusinessException;

	public void activateNotice(Map<String, Object> map);

	public Map<String, Object> synErrorNoteCheckParam(String body) throws IOException, BusinessException;

	public void synErrorNote(Map<String, Object> map);

	String listResource(String userId, String platformCode, String parentId, int curPage, int numPerPage);

	// public HandlerResult queryNote(Map<String, Object> para);

	public void removeNote(Map<String, Object> map);

	public Map<String, Object> removeNoteCheckParam(String body) throws BusinessException;

	public Map<String, Object> removeArchiveNoteCheckParam(String body) throws BusinessException;

	public void removeArchiveNote(Map<String, Object> map);

	public void shareEdu(final String userId, final String userPlatformCode);

	public Map<String, Object> pageDataDeleteCheckParam(String body) throws BusinessException;

	public void pageDataDelete(Map<String, Object> map);

	/**
	 * @param body
	 * @return
	 */
	public Map<String, Object> getNoteCheckParam(String body) throws IOException, BusinessException;

	/**
	 * @param map
	 */
	public List<Map<String, Object>> getNote(Map<String, Object> map);

	/**
	 * @param body
	 * @return
	 */
	public Map<String, Object> getTextbookCheckParam(String body) throws IOException, BusinessException;

	/**
	 * @param map
	 */
	public List<Map<String, Object>> getTextbook(Map<String, Object> map);

	/**
	 * @param body
	 * @return
	 */
	public Map<String, Object> getPageCheckParam(String body) throws IOException, BusinessException;

	/**
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getPage(Map<String, Object> map);

	/**
	 * @param body
	 * @return
	 */
	public Map<String, Object> setTextBookCheckParam(String body) throws IOException, BusinessException;

	/**
	 * @param map
	 */
	public void setTextBook(Map<String, Object> map);

	JSONArray shareNote(String userID, String loginPlatformCode);

	JSONArray shareNotePage(String userId, String loginPlatformCode,String noteId);

	JSONArray shareANotePage(String userId, String loginPlatformCode,String archiveNoteId);
}
