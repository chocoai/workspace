package com.whty.mxbj.api.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.whty.mxbj.api.dao.NoteTemplateDao;
import com.whty.mxbj.api.dao.PenDao;
import com.whty.mxbj.api.model.NoteTemplate;
import com.whty.mxbj.api.model.Pen;
import com.whty.mxbj.api.service.PenService;
import com.whty.mxbj.base.exception.BusinessException;
import com.whty.mxbj.common.constants.ResultConstants;
import com.whty.mxbj.common.utils.CommonFunction;

import net.sf.json.JSONObject;

@Component("penService")
public class PenServiceImpl implements PenService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PenDao penDao;

	@Autowired
	private NoteTemplateDao noteTemplateDao;

	@Override
	public List<Map<String, Object>> getNoteTemplate() {
		List<Map<String, Object>> resultMapList = new ArrayList<Map<String, Object>>();

		List<NoteTemplate> noteTemplateList = noteTemplateDao.listByCondition(new HashMap<String, Object>());

		for (NoteTemplate bean : noteTemplateList) {
			Map<String, Object> map = new HashMap<String, Object>();

			if (bean.getNoteId() != null) {
				map.put("noteId", bean.getNoteId());
			}

			if (bean.getNoteName() != null) {
				map.put("noteName", bean.getNoteName());
			}

			if (bean.getPages() != null) {
				map.put("pages", bean.getPages());
			}
			if (bean.getStartPageId() != null) {
				map.put("startPageId", bean.getStartPageId());
			}
			if (bean.getWidth() != null) {
				map.put("width", bean.getWidth());
			}

			if (bean.getHeight() != null) {
				map.put("height", bean.getHeight());
			}

			if (bean.getStartX() != null) {
				map.put("startX", bean.getStartX());
			}
			if (bean.getStartY() != null) {
				map.put("startY", bean.getStartY());
			}
			if (bean.getCoverImageUrl() != null) {
				map.put("coverImageUrl", bean.getCoverImageUrl());
			}
			if (bean.getOddeventIssame() != null) {
				map.put("oddeventIssame", bean.getOddeventIssame());
			}
			if (bean.getOddPageUrl() != null) {
				map.put("oddPageUrl", bean.getOddPageUrl());
			}

			if (bean.getEvenPageUrl() != null) {
				map.put("evenPageUrl", bean.getEvenPageUrl());
			}

			if (bean.getHeadpageUrl() != null) {
				map.put("headpageUrl", bean.getHeadpageUrl());
			}

			if (bean.getHeadpageId() != null) {
				map.put("headpageId", bean.getHeadpageId());
			}

			Map<String, Object> actionRect = new HashMap<String, Object>();
			actionRect.put("startX", bean.getActionRectStartX());
			actionRect.put("startY", bean.getActionRectStartY());
			actionRect.put("endX", bean.getActionRectEndX());
			actionRect.put("endY", bean.getActionRectEndY());

			if (bean.getActionRectStartX() != null) {
				map.put("actionRect", actionRect);
			}
			resultMapList.add(map);
		}
		return resultMapList;
	}

	@Override
	public Map<String, Object> bind(Map<String, Object> param) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (!param.get("penMac").toString().contains(":")) {
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
			return resultMap;
		}

		// Map<String, Object> penParam = new HashMap<String, Object>();
		List<Pen> penList = penDao.listByCondition(param);

		// 已经绑定了，不再绑定，也不提示错误
		if (penList != null && penList.size() != 0) {
			String isBind = (String) param.get("isBind");
			if (isBind == null) {
				resultMap.put(ResultConstants.RESULT, ResultConstants.MAC_USED_CODE);
				resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.MAC_USED__MESSAGE);
				return resultMap;
			}
		}

		Map<String, Object> penParam = new HashMap<String, Object>();
		penParam.put("userId", param.get("userId").toString());
		penParam.put("userPlatformCode", param.get("userPlatformCode"));
		penDao.deletePen(penParam);

		Pen bean = new Pen();
		bean.setMac(param.get("penMac").toString());
		if (param.get("penVersion") != null) {
			bean.setVersion(param.get("penVersion").toString());
		}
		bean.setBindTime(new Date());
		bean.setCreateTime(new Date());
		bean.setUserId(param.get("userId").toString());
		bean.setUserPlatformCode(param.get("userPlatformCode").toString());
		penDao.save(bean);
		resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
		resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		return resultMap;
	}

	@Override
	public void unbind(Map<String, Object> param) {
		Map<String, Object> penParam = new HashMap<String, Object>();
		penParam.put("userId", param.get("userId"));
		penParam.put("userPlatformCode", param.get("userPlatformCode"));

		Pen pen = penDao.getPenByUserAccountAndPlatform(penParam);

		if (pen == null)
			return;

		// param.put("mac", pen.getMac());

		// Map<String,Object> penParam = new HashMap<String,Object>();
		// penParam.put(key, value);
		penDao.deletePen(param);
	}

	@Override
	public Map<String, Object> bindCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		System.out.println(body);
		Map<String, Object> para = new HashMap<String, Object>();

		para.put("penMac", reqJson.get("penMac"));
		para.put("userId", reqJson.get("userId"));
		para.put("userPlatformCode", reqJson.get("userPlatformCode"));

		// 必填字段检查
		CommonFunction.checkParam(para);

		// para.put("penVersion", reqJson.get("penVersion"));
		para.put("isBind", reqJson.get("isBind"));
		return para;
	}

	@Override
	public Map<String, Object> unbindCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;

		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);

		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}

		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.get("userId"));
		para.put("userPlatformCode", reqJson.get("userPlatformCode"));
		CommonFunction.checkParam(para);
		return para;
	}

}
