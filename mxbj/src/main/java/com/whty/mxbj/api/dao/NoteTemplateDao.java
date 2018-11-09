package com.whty.mxbj.api.dao;

import java.util.Map;

import com.whty.mxbj.api.model.NoteTemplate;
import com.whty.mxbj.base.dao.IBaseDao;

public interface NoteTemplateDao extends IBaseDao<NoteTemplate> {

	public NoteTemplate loadNoteTemplate(String noteId);

	public void deleteNoteTemplate(Map<String, Object> para);
}
