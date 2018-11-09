package com.whty.mxbj.api.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.whty.mxbj.base.exception.BusinessException;

public interface PenService {

	public List<Map<String, Object>> getNoteTemplate();

	// 绑定
	public Map<String, Object> bind(Map<String, Object> param);

	// 解绑
	public void unbind(Map<String, Object> param);

	public Map<String, Object> bindCheckParam(String body) throws IOException, BusinessException;

	public Map<String, Object> unbindCheckParam(String body) throws IOException, BusinessException;

}
