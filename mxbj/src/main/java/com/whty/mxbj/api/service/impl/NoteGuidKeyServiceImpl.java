package com.whty.mxbj.api.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.whty.mxbj.api.dao.NoteGuidKeyDao;
import com.whty.mxbj.api.model.NoteGuidKey;
import com.whty.mxbj.api.service.NoteGuidKeyService;
import com.whty.mxbj.base.exception.BusinessException;
import com.whty.mxbj.common.utils.CommonFunction;

import net.sf.json.JSONObject;

@Component("noteGuidKey")
public class NoteGuidKeyServiceImpl implements NoteGuidKeyService {

	@Autowired
	private NoteGuidKeyDao noteGuidKeyDao;

	@Override
	public void save(NoteGuidKey bean) {
		noteGuidKeyDao.save(bean);
	}

	@Override
	public void update(NoteGuidKey bean) {
		noteGuidKeyDao.update(bean);
	}

	@Override
	public List<NoteGuidKey> listByCondition(Map<String, Object> param) {
		return noteGuidKeyDao.listByCondition(param);
	}

	
	@Override
	public Map<String, Object> getCheckParam(String body) throws IOException, BusinessException {
		
		JSONObject reqJson = null;

		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);

		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}

		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.get("userId"));
		para.put("userPlatformCode", reqJson.get("userPlatformCode"));
		para.put("everNoteId", reqJson.get("everNoteId"));
		CommonFunction.checkParam(para);
		return para;
	}
	
	
	@Override
	public Map<String, Object> uploadCheckParam(String body) throws IOException, BusinessException {
		
		JSONObject reqJson = null;

		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);

		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}

		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.get("userId"));
		para.put("userPlatformCode", reqJson.get("userPlatformCode"));
		para.put("everNoteId", reqJson.get("everNoteId"));
		para.put("guidKeys", reqJson.get("guidKeys"));
		CommonFunction.checkParam(para);
		return para;
	}
	
	
	
	
	
	
}
