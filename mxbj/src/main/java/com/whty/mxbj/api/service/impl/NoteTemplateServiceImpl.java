package com.whty.mxbj.api.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.whty.mxbj.api.dao.NoteTemplateDao;
import com.whty.mxbj.api.model.NoteTemplate;
import com.whty.mxbj.api.service.NoteTemplateService;

@Component("noteTemplateService")
public class NoteTemplateServiceImpl implements NoteTemplateService {

	@Autowired
	private NoteTemplateDao noteTemplateDao;

//	@Override
//	public HandlerResult queryNoteTemplate(Map<String, Object> para) {
//		HandlerResult rs = new HandlerResult();
//		List<NoteTemplate> resultList = noteTemplateDao.listByCondition(para);
//		rs.setResultList(resultList);
//		return rs;
//	}

	@Override
	public NoteTemplate getNoteTemplateById(String noteId) {
		return noteTemplateDao.loadNoteTemplate(noteId);
	}

	@Override
	public void save(NoteTemplate bean) {
		noteTemplateDao.save(bean);
	}

	@Override
	public void update(NoteTemplate bean) {
		noteTemplateDao.update(bean);
	}

	@Override
	public void deleteNoteTemplate(Map<String, Object> para) {
		
//		deleteNoteTemplateById
		
		noteTemplateDao.deleteNoteTemplate(para);
	}

}
