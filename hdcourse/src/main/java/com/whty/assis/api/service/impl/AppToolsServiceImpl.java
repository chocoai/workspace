/**
 * 
 */
package com.whty.assis.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.api.dao.AppToolsDao;
import com.whty.assis.api.model.AppTools;
import com.whty.assis.api.service.AppToolsService;
import com.whty.assis.base.exception.BusinessException;
import com.whty.common.util.CommonFunction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author zhangzheng
 * @date 2018年8月7日
 */
@Service
public class AppToolsServiceImpl implements AppToolsService {

	@Autowired
	private AppToolsDao appToolsDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.api.service.AppToolsService#checkgetTools(java.lang.
	 * String)
	 */
	@Override
	public Map<String, Object> checkgetTools(String body) throws BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.get("userId"));
		para.put("platformCode", reqJson.get("platformCode"));
		para.put("loginPlatformCode", reqJson.get("loginPlatformCode"));
		// 必填字段检查
		CommonFunction.checkParam(para);

		para.put("version", reqJson.get("version"));

		return para;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.api.service.AppToolsService#getTools(java.util.Map)
	 */
	@Override
	public JSONArray getTools(Map<String, Object> para) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		JSONArray jsonArray = new JSONArray();
		List<AppTools> appTools = new ArrayList<AppTools>();
		if (para.get("version") == null) {
			appTools = appToolsDao.listByConditionByCode(paramMap);
		} else {
			paramMap.put("status", "1");
			appTools = appToolsDao.listByCondition(paramMap);
		}

		if (para.get("userId") != null) {
			String userId = para.get("userId").toString().trim();
			if (StringUtils.isNotEmpty(userId)) {
				if (userId.equals("191ac780686340f59d9f61dad257aa27")) {
					appTools = appToolsDao.listByCondition(new HashMap<String, Object>());
				}
			}
		}

		for (AppTools bean : appTools) {

			if (para.get("userId") != null) {
				String userId = para.get("userId").toString().trim();
				if (StringUtils.isNotEmpty(userId)) {
					if (!userId.equals("191ac780686340f59d9f61dad257aa27")) {
						if (bean.getStatus() == 0)
							continue;
					}
				}
			}

			if (bean.getIsParent() == 0) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", bean.getId() == null ? "" : bean.getId());
				jsonObj.put("name", bean.getAppName() == null ? "" : bean.getAppName());
				jsonObj.put("icon", bean.getIcon() == null ? "" : bean.getIcon());
				jsonObj.put("clickIcon", bean.getClickIcon() == null ? "" : bean.getClickIcon());
				jsonObj.put("description", bean.getDescription() == null ? "" : bean.getDescription());
				jsonObj.put("subjectId", bean.getSubFileExt() == null ? "" : bean.getSubFileExt());
				// jsonObj.put("needlogin", bean.isNeedlogin());
				jsonObj.put("modelCode", bean.getModelCode() == null ? "" : bean.getModelCode());
				jsonObj.put("behaviorcode", bean.getBehaviorcode() == null ? "" : bean.getBehaviorcode());
				JSONArray subArray = new JSONArray();

				for (AppTools subBean : appTools) {

					if (subBean.getParentId().equals(bean.getId())) {
						JSONObject subObj = new JSONObject();

						subObj.put("id", subBean.getId() == null ? "" : subBean.getId());
						subObj.put("name", subBean.getAppName() == null ? "" : subBean.getAppName());
						subObj.put("subFileExt", subBean.getSubFileExt() == null ? "" : subBean.getSubFileExt());
						// subObj.put("version", subBean.getVersion() == null ?
						// "" : subBean.getVersion());

						if (subBean.getHdktVersion() == null) {
							subObj.put("hdktVersion", "");
						} else {
							String[] str = subBean.getHdktVersion().split(",");

							JSONArray s = new JSONArray();
							for (int i = 0; i < str.length; i++) {
								s.add(str[i]);
							}

							subObj.put("hdktVersion", str);
						}

						subObj.put("version", subBean.getVersion() == null ? "" : subBean.getVersion());

						subObj.put("downUrl", subBean.getDownUrl() == null ? "" : subBean.getDownUrl());
						subObj.put("icon", subBean.getIcon() == null ? "" : subBean.getIcon());
						subObj.put("clickIcon", subBean.getClickIcon() == null ? "" : subBean.getClickIcon());
						subObj.put("fileSize", subBean.getFileSize() == null ? "" : subBean.getFileSize());
						subObj.put("description", subBean.getDescription() == null ? "" : subBean.getDescription());
						subObj.put("subjectId", subBean.getSubjectId() == null ? "" : subBean.getSubjectId());
						subObj.put("isFolder", subBean.isFolder());
						subObj.put("modelCode", subBean.getModelCode() == null ? "" : subBean.getModelCode());
						subObj.put("sorNum", subBean.getSortNum() == null ? "" : subBean.getSortNum());
						subObj.put("typeName", subBean.getTypeName() == null ? "" : subBean.getTypeName());
						subObj.put("behaviorcode", subBean.getBehaviorcode() == null ? "" : subBean.getBehaviorcode());
						// subObj.put("needlogin", subBean.isNeedlogin());
						subArray.add(subObj);
					}
				}
				jsonObj.put("appList", subArray);
				jsonArray.add(jsonObj);
			}
		}
		return jsonArray;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.api.service.AppToolsService#listByCondition(java.util.Map)
	 */
	@Override
	public List<AppTools> listByCondition(Map<String, Object> param) throws BusinessException {
		return appToolsDao.listByCondition(param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.api.service.AppToolsService#addAppTools(com.whty.assis.api
	 * .model.AppTools)
	 */
	@Override
	public void addAppTools(AppTools appTools) {
		appToolsDao.save(appTools);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.api.service.AppToolsService#updateAppTools(javax.servlet.
	 * http.HttpServletRequest)
	 */
	@Override
	public void updateAppTools(HttpServletRequest request) {
		String id = request.getParameter("id");
		String appName = request.getParameter("appName");
		String sortNum = request.getParameter("sortNum");
		String downUrl = request.getParameter("downUrl");
		String icon = request.getParameter("icon");
		String fileSize = request.getParameter("fileSize");
		String description = request.getParameter("description");
		String subjectId = request.getParameter("subjectId");
		String isFolder = request.getParameter("isFolder");
		String modelCode = request.getParameter("modelCode");
		String subFileExt = request.getParameter("subFileExt");
		String version = request.getParameter("version");
		String status = request.getParameter("status");
		String needlogin = request.getParameter("needlogin");

		String typeName = request.getParameter("typeName");

		String behaviorcode = request.getParameter("behaviorcode");
		String[] hdktVersion = request.getParameterValues("hdktVersion");
		AppTools bean = new AppTools();

		bean.setId(Integer.valueOf(id));

		bean.setAppName(appName);
		if (StringUtils.isNotEmpty(sortNum)) {
			bean.setSortNum(Integer.valueOf(sortNum));
		}
		bean.setDownUrl(downUrl);
		bean.setIcon(icon);
		bean.setTypeName(typeName);
		bean.setBehaviorcode(behaviorcode);

		String str1 = StringUtils.join(hdktVersion, ",");
		bean.setHdktVersion(str1);

		if (StringUtils.isNotEmpty(fileSize)) {
			bean.setFileSize(Integer.valueOf(fileSize));
		}

		bean.setDescription(description);
		bean.setSubFileExt(subFileExt);
		bean.setVersion(version);
		bean.setSubjectId(subjectId);
		bean.setStatus(Integer.valueOf(status));
		bean.setModelCode(modelCode);
		if ("1".equals(isFolder)) {
			bean.setFolder(true);
		} else {
			bean.setFolder(false);
		}

		if ("1".equals(needlogin)) {
			bean.setNeedlogin(true);
		} else {
			bean.setNeedlogin(false);
		}

		bean.setCreateTime(new Date());
		bean.setUpdateTime(new Date());
		appToolsDao.update(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.api.service.AppToolsService#deleteAppTools(java.util.List)
	 */
	@Override
	public void deleteAppTools(List<String> asList) {
		for (String id : asList) {
			appToolsDao.deleteById(Integer.valueOf(id));
		}
	}

}
