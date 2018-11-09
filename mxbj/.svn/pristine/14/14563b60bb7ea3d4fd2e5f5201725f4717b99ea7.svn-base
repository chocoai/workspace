package com.whty.mxbj.api.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.whty.mxbj.api.model.NoteGuidKey;
import com.whty.mxbj.base.exception.BusinessException;

public interface NoteGuidKeyService {

	public void save(NoteGuidKey bean);

	public void update(NoteGuidKey bean);

	public List<NoteGuidKey> listByCondition(Map<String, Object> param);

	public Map<String, Object> uploadCheckParam(String body) throws IOException, BusinessException;

	public Map<String, Object> getCheckParam(String body) throws IOException, BusinessException;
}
