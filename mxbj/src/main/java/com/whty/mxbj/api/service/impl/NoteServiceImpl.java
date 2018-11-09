package com.whty.mxbj.api.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.DigestException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.whty.mxbj.common.utils.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.whty.mxbj.api.comparator.ArchiveNotePageComparator;
import com.whty.mxbj.api.comparator.NoteComparator;
import com.whty.mxbj.api.dao.ArchiveNoteDao;
import com.whty.mxbj.api.dao.ArchiveNotePageDao;
import com.whty.mxbj.api.dao.NoteDao;
import com.whty.mxbj.api.dao.NoteTemplateDao;
import com.whty.mxbj.api.dao.PageDao;
import com.whty.mxbj.api.dao.TextbookNoteDao;
import com.whty.mxbj.api.dao.UserDao;
import com.whty.mxbj.api.model.ArchiveNote;
import com.whty.mxbj.api.model.ArchiveNotePage;
import com.whty.mxbj.api.model.Note;
import com.whty.mxbj.api.model.NoteTemplate;
import com.whty.mxbj.api.model.Page;
import com.whty.mxbj.api.model.TextbookNote;
import com.whty.mxbj.api.model.UserConfig;
import com.whty.mxbj.api.service.BasePropertyService;
import com.whty.mxbj.api.service.NoteService;
import com.whty.mxbj.base.exception.BusinessException;
import com.whty.mxbj.base.service.IRedisService;
import com.whty.mxbj.common.constants.SysConstants;
import com.whty.mxbj.common.thread.ThreadPoolUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component("noteService")
public class NoteServiceImpl implements NoteService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private NoteDao noteDao;

	@Autowired
	private PageDao pageDao;

	@Autowired
	private ArchiveNoteDao archiveNoteDao;

	@Autowired
	private ArchiveNotePageDao archiveNotePageDao;

	@Autowired
	private NoteTemplateDao noteTemplateDao;

	@Autowired
	private BasePropertyService basePropertyService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private IRedisService redisService;

	@Autowired
	private TextbookNoteDao textbookNoteDao;

	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public Map<String, Object> pageDataSubmitCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}

		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.get("userId"));
		para.put("pageList", reqJson.get("pageList"));
		para.put("userPlatformCode", reqJson.get("userPlatformCode"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	@Override
	public Map<String, Object> archiveListCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.get("userId"));
		para.put("userPlatformCode", reqJson.get("userPlatformCode"));
		// para.put("token", reqJson.get("token"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	@Override
	public List<Map<String, Object>> getNotes(Map<String, Object> map) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		List<Note> noteList = noteDao.listByCondition(map);

		// 实现createTime降序排列
		Collections.sort(noteList, new NoteComparator());

		Map<String, Object> noteResultMap;
		for (Note bean : noteList) {
			noteResultMap = new HashMap<String, Object>();
			noteResultMap.put("noteId", bean.getNoteId());
			noteResultMap.put("noteName", bean.getNoteName());

			if (bean.getSubjectId() != null) {
				noteResultMap.put("subjectId", bean.getSubjectId());
			}
			if (bean.getSubjectName() != null) {
				noteResultMap.put("subjectName", bean.getSubjectName());
			}

			noteResultMap.put("createTime",
					TimeUtils.date2String(bean.getCreateTime(), TimeUtils.STR_DATETIME_PATTERN));
			resultList.add(noteResultMap);
		}
		return resultList;
	}

	@Override
	public void pagetDataSubmit(Map<String, Object> map) {
		String pageJsonStr = map.get("pageList").toString();

		JSONArray pageJsonArray = JSONArray.fromObject(pageJsonStr);

		if (pageJsonArray == null || pageJsonArray.size() == 0)
			return;

		Map<String, Object> pageMap = null;
		for (int i = 0; i < pageJsonArray.size(); i++) {

			Page page = null;
			JSONObject pageJsonObject = pageJsonArray.getJSONObject(i);

			pageMap = new HashMap<String, Object>();

			pageMap.put("userId", map.get("userId").toString());
			pageMap.put("pageId", pageJsonObject.optString("pageId"));
			pageMap.put("noteId", pageJsonObject.optString("noteId"));
			pageMap.put("userPlatformCode", map.get("userPlatformCode").toString());
			pageMap.put("mediaType", pageJsonObject.get("mediaType").toString());

			Note note = noteDao.loadNote(pageMap);
			// 没有找到本子则创建本子
			if (note == null) {
				NoteTemplate noteTemplate = noteTemplateDao.loadNoteTemplate(pageJsonObject.optString("noteId"));
				String noteName = "笔记本";
				if (noteTemplate != null) {
					noteName = noteTemplate.getNoteName();
				}

				note = new Note();

				note.setNoteId(pageJsonObject.optString("noteId"));
				note.setNoteName(noteName);
				note.setUserId(map.get("userId").toString());
				note.setUserPlatformCode(map.get("userPlatformCode").toString());
				note.setCreateTime(new Date());
				note.setUpdateTime(new Date());
				noteDao.save(note);

			}

			note.setUpdateTime(new Date());
			noteDao.update(note);

			List<Page> pageList = pageDao.listByCondition(pageMap);

			if (pageList != null && pageList.size() > 0) {
				page = pageList.get(0);
			}

			if (page == null) {
				page = new Page();
				page.setPageId(Integer.parseInt(pageJsonObject.optString("pageId")));
				page.setNoteId(pageJsonObject.optString("noteId"));
				page.setPageFileUrl(pageJsonObject.optString("pageFileUrl"));
				page.setMediaType(pageJsonObject.optInt("mediaType"));
				page.setLastEditTime(new Date(Long.valueOf(pageJsonObject.get("lastModified").toString())));
				page.setUserId(map.get("userId").toString());
				page.setCreateTime(new Date());
				page.setUserPlatformCode(map.get("userPlatformCode").toString());
				pageDao.save(page);
			} else {
				page.setUserId(map.get("userId").toString());
				page.setUserPlatformCode(map.get("userPlatformCode").toString());
				page.setPageId(Integer.parseInt(pageJsonObject.optString("pageId")));
				page.setNoteId(pageJsonObject.optString("noteId"));
				page.setPageFileUrl(pageJsonObject.optString("pageFileUrl"));
				page.setMediaType(pageJsonObject.optInt("mediaType"));
				page.setLastEditTime(new Date(Long.valueOf(pageJsonObject.get("lastModified").toString())));
				page.setUpdateTime(new Date());
				pageDao.update(page);
			}
		}
	}

	@Override
	public Map<String, Object> archiveNoteCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.get("userId"));
		para.put("pageList", reqJson.get("pageList"));
		para.put("noteId", reqJson.get("noteId"));
		para.put("userPlatformCode", reqJson.get("userPlatformCode"));
		// para.put("noteId", reqJson.get("noteId"));
		// 必填字段检查
		CommonFunction.checkParam(para);

		para.put("archiveTime", reqJson.get("archiveTime"));
		// para.put("pageList", reqJson.get("pageList"));

		return para;
	}

	@Override
	public void archiveNote(Map<String, Object> map) {
		String pageJsonStr = map.get("pageList").toString();

		JSONArray pageJsonArray = JSONArray.fromObject(pageJsonStr);

		String noteId = map.get("noteId").toString();
		Map<String, Object> noteParam = new HashMap<String, Object>();
		noteParam.put("userId", map.get("userId").toString());
		noteParam.put("noteId", noteId);
		noteParam.put("userPlatformCode", map.get("userPlatformCode").toString());
		Note note = noteDao.loadNote(noteParam);

		String archiveNoteId = CommonFunction.getUUID32();
		if (note == null) {
			ArchiveNote archiveNoteBean = new ArchiveNote();
			archiveNoteBean.setArchiveNoteId(archiveNoteId);
			archiveNoteBean.setNoteId(noteId);
			archiveNoteBean.setUserId(map.get("userId").toString());
			archiveNoteBean.setStartTime(new Date());
			archiveNoteBean.setEndTime(new Date());
			archiveNoteBean.setCreateTime(new Date());
			archiveNoteBean.setLastEditTime(new Date());

			Object archiveTime = map.get("archiveTime");
			if (archiveTime != null && (archiveTime instanceof String) && !"".equals(archiveTime))
				archiveNoteBean.setArchiveTime(archiveTime.toString());

			NoteTemplate noteTemplate = noteTemplateDao.loadNoteTemplate(noteId);
			String noteName = null;
			if (noteTemplate != null) {
				noteName = noteTemplate.getNoteName();
			}

			archiveNoteBean.setNoteName(noteName);

			archiveNoteBean.setUserPlatformCode(map.get("userPlatformCode").toString());
			archiveNoteDao.save(archiveNoteBean);
		} else {
			ArchiveNote archiveNoteBean = new ArchiveNote();
			archiveNoteBean.setArchiveNoteId(archiveNoteId);
			archiveNoteBean.setNoteId(noteId);
			archiveNoteBean.setUserId(map.get("userId").toString());
			archiveNoteBean.setStartTime(note.getCreateTime());// 创建时间就是开始时间
			archiveNoteBean.setEndTime(new Date());// 归档时间就是当前时间
			archiveNoteBean.setCreateTime(new Date());
			archiveNoteBean.setLastEditTime(note.getLastEditTime());// 最后更新时间
			archiveNoteBean.setNoteName(note.getNoteName());
			archiveNoteBean.setUserPlatformCode(map.get("userPlatformCode").toString());

			if (note.getSubjectId() != null) {
				archiveNoteBean.setSubjectId(note.getSubjectId());
			}

			String subjectId = note.getSubjectId();
			if (subjectId != null && !"".equals(subjectId))
				archiveNoteBean.setSubjectId(subjectId);

			Object archiveTime = map.get("archiveTime");
			if (archiveTime != null && (archiveTime instanceof String) && !"".equals(archiveTime))
				archiveNoteBean.setArchiveTime(archiveTime.toString());

			archiveNoteDao.save(archiveNoteBean);
			noteDao.deleteNote(note);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userId", map.get("userId").toString());
			param.put("noteId", noteId);
			param.put("userPlatformCode", map.get("userPlatformCode").toString());
			pageDao.deletePage(param);
		}

		if (pageJsonArray != null && pageJsonArray.size() > 0) {
			for (int i = 0; i < pageJsonArray.size(); i++) {
				JSONObject pageJsonObject = pageJsonArray.optJSONObject(i);

				// 保存归档页面
				ArchiveNotePage archiveNotePageBean = new ArchiveNotePage();
				archiveNotePageBean.setArchiveNoteId(archiveNoteId);
				archiveNotePageBean.setNoteId(noteId);
				archiveNotePageBean.setPageFileUrl(pageJsonObject.optString("pageFileUrl"));
				archiveNotePageBean.setPageId(Integer.parseInt(pageJsonObject.optString("pageId")));
				archiveNotePageBean
						.setLastEditTime(new Date(Long.valueOf(pageJsonObject.get("lastModified").toString())));
				archiveNotePageBean.setCreateTime(new Date());
				archiveNotePageBean.setMediaType(pageJsonObject.optInt("mediaType"));
				archiveNotePageBean.setArchiveNotePageId(CommonFunction.getUUID32());
				archiveNotePageBean.setUserId(map.get("userId").toString());

				archiveNotePageBean.setUserPlatformCode(map.get("userPlatformCode").toString());

				archiveNotePageDao.save(archiveNotePageBean);
			}
		}
	}

	@Override
	public Map<String, Object> addCheckParam(String body) throws IOException, BusinessException {
		// logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.get("userId"));
		para.put("noteId", reqJson.get("noteId"));
		para.put("userPlatformCode", reqJson.get("userPlatformCode"));
		// 检查list字段
		CommonFunction.checkParam(para);
		para.put("noteName", reqJson.get("noteName"));
		para.put("subjectId", reqJson.get("subjectId"));
		return para;
	}

	@Override
	public void saveOrUpdate(Map<String, Object> map) {
		Map<String, Object> noteParam = new HashMap<String, Object>();
		noteParam.put("noteId", map.get("noteId").toString());
		noteParam.put("userId", map.get("userId").toString());
		noteParam.put("userPlatformCode", map.get("userPlatformCode").toString());

		if (map.get("noteName") == null) {
			NoteTemplate noteTemplate = noteTemplateDao.loadById(map.get("noteId").toString());

			noteParam.put("noteName", noteTemplate.getNoteName());
		}

		Note note = noteDao.loadNote(noteParam);

		if (note == null) {
			note = new Note();
			note.setUserId(map.get("userId").toString());
			note.setNoteId(map.get("noteId").toString());
			note.setNoteName(map.get("noteName").toString());
			note.setCreateTime(new Date());
			note.setUpdateTime(new Date());
			note.setUserPlatformCode(map.get("userPlatformCode").toString());
			note.setUpdateTime(new Date());
			if (map.get("subjectId") != null) {
				note.setSubjectId(map.get("subjectId").toString());
			}

			noteDao.save(note);
		} else {
			// note.setUserId(map.get("userId").toString());
			// note.setNoteId(map.get("noteId").toString());
			// note.setNoteName(map.get("noteName").toString());
			// note.setUserPlatformCode(map.get("userPlatformCode").toString());
			// note.setUpdateTime(new Date());
			//
			// if (map.get("subjectId") != null) {
			// note.setSubjectId(map.get("subjectId").toString());
			// }
			// noteDao.update(note);
		}

		shareEdu(map.get("userId").toString(), map.get("userPlatformCode").toString());
	}

	@Override
	public Map<String, Object> removeCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.get("userId"));
		para.put("token", reqJson.get("token"));
		para.put("noteId", reqJson.get("noteId"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	@Override
	public Map<String, Object> modifyCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.get("userId"));
		para.put("noteId", reqJson.get("noteId"));
		para.put("noteName", reqJson.get("noteName"));
		para.put("userPlatformCode", reqJson.get("userPlatformCode"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	@Override
	public void modifyName(Map<String, Object> map) {
		Note bean = new Note();
		bean.setUserId(map.get("userId").toString());
		bean.setNoteId(map.get("noteId").toString());
		bean.setNoteName(map.get("noteName").toString());
		bean.setUpdateTime(new Date());
		bean.setUserPlatformCode(map.get("userPlatformCode").toString());
		noteDao.update(bean);
	}

	@Override
	public Map<String, Object> listCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.get("userId"));
		para.put("userPlatformCode", reqJson.get("userPlatformCode"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	@Override
	public Map<String, Object> archivePageListCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.get("userId"));
		para.put("archiveNoteId", reqJson.get("archiveNoteId"));
		para.put("userPlatformCode", reqJson.get("userPlatformCode"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	@Override
	public List<Map<String, Object>> getArchivePageList(Map<String, Object> map) {
		ArchiveNote archiveNote = archiveNoteDao.loadById(map.get("archiveNoteId").toString());

		if (archiveNote == null)
			return null;

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

		Map<String, Object> pageMap = null;

		map.put("mediaType", new Integer(1));
		List<ArchiveNotePage> pageList = archiveNotePageDao.listByCondition(map);
		// 按照pageId升序
		Collections.sort(pageList, new ArchiveNotePageComparator());

		for (ArchiveNotePage bean : pageList) {

			NoteTemplate noteTemplate = noteTemplateDao.loadNoteTemplate(bean.getNoteId());
			pageMap = new HashMap<String, Object>();

			if (noteTemplate != null) {
				int startPageId = noteTemplate.getStartPageId();
				int pageId = bean.getPageId();
				pageMap.put("pageNum", pageId - startPageId + 1);
			} else {
				pageMap.put("pageNum", bean.getPageId());
			}

			pageMap.put("pageId", bean.getPageId());
			pageMap.put("pageImageUrl", bean.getPageFileUrl());
			pageMap.put("lastModified", bean.getLastEditTime().getTime());
			resultList.add(pageMap);
		}
		return resultList;
	}

	@Override
	public List<Map<String, Object>> getArchiveNotes(Map<String, Object> map) {
		// map.put("mediaType", "1");
		List<ArchiveNote> archiveNoteList = archiveNoteDao.listByCondition(map);
		List<Map<String, Object>> reusltList = new ArrayList<Map<String, Object>>();
		Map<String, Object> archiveNoteMap = null;
		for (ArchiveNote bean : archiveNoteList) {
			archiveNoteMap = new HashMap<String, Object>();
			archiveNoteMap.put("startTime", bean.getStartTime().getTime());
			archiveNoteMap.put("noteName", bean.getNoteName());
			archiveNoteMap.put("noteId", bean.getNoteId());
			archiveNoteMap.put("endTime", bean.getEndTime().getTime());
			archiveNoteMap.put("archiveNoteId", bean.getArchiveNoteId());
			archiveNoteMap.put("subjectId", bean.getSubjectId());
			archiveNoteMap.put("subjectName", bean.getSubjectName());
			archiveNoteMap.put("archiveTime", bean.getArchiveTime());
			reusltList.add(archiveNoteMap);
		}
		return reusltList;
	}

	@Override
	public Map<String, Object> addBatchCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.get("userId"));
		para.put("userPlatformCode", reqJson.get("userPlatformCode"));
		para.put("noteList", reqJson.get("noteList"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	@Override
	public void shareToCloud(Map<String, Object> map) {
		String userId = map.get("userId").toString();
		String userPlatformCode = map.get("userPlatformCode").toString();

		Map<String, Object> userConfigParams = new HashMap<String, Object>();
		userConfigParams.put("userId", map.get("userId"));
		userConfigParams.put("userPlatformCode", map.get("userPlatformCode"));
		userConfigParams.put("synCloudLastTime", new Date());

		UserConfig userConfig = userDao.getUserConfig(userConfigParams);

		if (userConfig == null) {// 默认不同步用户数据
			return;
		}

		if (!userConfig.getIsSynCloud()) {
			return;
		}

		userDao.updateUserConfig(userConfigParams);

		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("userId", map.get("userId"));
		pageMap.put("userPlatformCode", map.get("userPlatformCode"));
		pageMap.put("mediaType", 1);

		String mxbjDirId = getMXBJDirId(userId, userPlatformCode, "0", SysConstants.PRODUCT_NAME);// 墨香笔记项目目录编码

		List<Map<String, Object>> mxbjSubDirList = getSubDirByDirId(userId, userPlatformCode, mxbjDirId);// 获取墨香笔记所有子文件夹

		// 删除文件和文件目录
		for (int i = 0; i < mxbjSubDirList.size(); i++) {
			Map<String, Object> subDirMap = mxbjSubDirList.get(i);

			String dirId = subDirMap.get("dirId").toString();
			String isFolder = subDirMap.get("isFolder").toString();

			if ("0".equals(isFolder)) {
				List<Map<String, Object>> fileList = getSubDirByDirId(userId, userPlatformCode, dirId);// 只找到第二层
				for (int j = 0; j < fileList.size(); j++) {
					Map<String, Object> fileMap = fileList.get(j);
					String fileId = fileMap.get("dirId").toString();
					if ("1".equals(fileMap.get("isFolder").toString())) {// 删除文件
						Map<String, Object> resourceMap = new HashMap<String, Object>();
						resourceMap.put("dirId", fileId);
						resourceMap.put("ownerType", "0");
						resourceMap.put("userId", userId);
						HttpUtils.getInstance().cmsHttpPost(
								basePropertyService.getPropertyValue("cmsUrl", userPlatformCode), "/removeResource",
								JSONObject.fromObject(resourceMap).toString());
					}
				}
			}
			// String url = + ;// 删除目录
			Map<String, Object> resourceMap = new HashMap<String, Object>();
			resourceMap.put("dirId", dirId);
			resourceMap.put("ownerType", "0");
			resourceMap.put("userId", userId);
			HttpUtils.getInstance().cmsHttpPost(basePropertyService.getPropertyValue("cmsUrl", userPlatformCode),
					"/removeResource", JSONObject.fromObject(resourceMap).toString());
		}

		// String url = + ;// 创建文件夹

		List<Note> noteList = noteDao.listByCondition(pageMap);
		for (Note note : noteList) {

			if ("0_0_0".equals(note.getNoteId()))
				continue;

			// 笔记本文件夹名称
			String noteDirName = note.getNoteName() + "-"
					+ TimeUtils.date2String(note.getCreateTime(), TimeUtils.TIME_FORMAT) + note.getNoteId();
			pageMap.put("noteId", note.getNoteId());
			List<Page> notePagelst = pageDao.listByCondition(pageMap);

			if (notePagelst == null || notePagelst.size() == 0)
				continue;

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("dirName", noteDirName);
			param.put("parentId", mxbjDirId);
			param.put("ownerType", "0");
			param.put("userId", userId);

			String answer = HttpUtils.getInstance().cmsHttpPost(
					basePropertyService.getPropertyValue("cmsUrl", userPlatformCode), "/createFolder",
					JSONObject.fromObject(param).toString());

			String result = JSONObject.fromObject(answer).optString("result");

			boolean isNullDir = true;
			if ("000000".equals(result)) {
				JSONObject data = JSONObject.fromObject(answer).optJSONObject("data");
				String dirId = data.optString("dirId");
				// 上传文件
				for (int i = 0; i < notePagelst.size(); i++) {
					Page pageBean = notePagelst.get(i);

					// 没有对应的实体文件，不同步
					Map<String, Object> pageDataParam = new HashMap<String, Object>();
					pageDataParam.put("userId", pageBean.getUserId());
					pageDataParam.put("userPlatformCode", pageBean.getUserPlatformCode());
					pageDataParam.put("pageId", pageBean.getPageId());
					pageDataParam.put("mediaType", 0);
					pageDataParam.put("noteId", pageBean.getNoteId());

					Page pageData = pageDao.getPage(pageDataParam);

					if (pageData == null)
						continue;

					try {
						HttpURLConnection.setFollowRedirects(false);
						HttpURLConnection con;
						con = (HttpURLConnection) new URL(pageBean.getPageFileUrl()).openConnection();
						con.setRequestMethod("HEAD");
						if (con.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND)
							continue;

						NoteTemplate noteTemplate = noteTemplateDao.loadNoteTemplate(pageBean.getNoteId());

						String fileName = null;
						if (noteTemplate != null) {
							int startPageId = noteTemplate.getStartPageId();
							int pageId = pageBean.getPageId();
							fileName = pageId - startPageId + 1 + ".png";
						} else {
							fileName = pageBean.getPageId().toString() + ".png";
						}

						String createDiskReseResult = createDiskRes(dirId, fileName, userId, userPlatformCode,
								pageBean.getPageFileUrl());
						isNullDir = false;
						System.out.println(createDiskReseResult);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if (isNullDir) {
					Map<String, Object> resourceMap = new HashMap<String, Object>();
					resourceMap.put("dirId", dirId);
					resourceMap.put("ownerType", "0");
					resourceMap.put("userId", userId);
					HttpUtils.getInstance().cmsHttpPost(
							basePropertyService.getPropertyValue("cmsUrl", userPlatformCode), "/removeResource",
							JSONObject.fromObject(resourceMap).toString());
				}
			}
		}

		Map<String, Object> pa = new HashMap<String, Object>();
		pa.put("userId", userId);
		pa.put("userPlatformCode", userPlatformCode);
		List<ArchiveNote> archiveNoteList = archiveNoteDao.listByCondition(pa);

		Map<String, Object> archiveNotePageMap = new HashMap<String, Object>();
		for (ArchiveNote archiveNote : archiveNoteList) {

			if ("0_0_0".equals(archiveNote.getNoteId()))
				continue;

			// 归档笔记本文件夹名称
			String noteDirName = "[归档]" + archiveNote.getNoteName() + "-"
					+ TimeUtils.date2String(archiveNote.getCreateTime(), TimeUtils.TIME_FORMAT)
					+ archiveNote.getNoteId();

			archiveNotePageMap.put("archiveNoteId", archiveNote.getArchiveNoteId());
			archiveNotePageMap.put("mediaType", "1");
			List<ArchiveNotePage> notePagelst = archiveNotePageDao.listByCondition(archiveNotePageMap);

			if (notePagelst == null || notePagelst.size() == 0)
				continue;

			// List<Page> notePagelst = notePage.getValue();
			// 创建目录
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("dirName", noteDirName);
			param.put("parentId", mxbjDirId);
			param.put("ownerType", "0");
			param.put("userId", userId);

			// String answer = HttpUtils.getInstance().cmsHttpPost(url,
			// JSONObject.fromObject(param).toString());

			String answer = HttpUtils.getInstance().cmsHttpPost(
					basePropertyService.getPropertyValue("cmsUrl", userPlatformCode), "/createFolder",
					JSONObject.fromObject(param).toString());

			boolean isNullDir = true;

			String result = JSONObject.fromObject(answer).optString("result");
			if ("000000".equals(result)) {
				JSONObject data = JSONObject.fromObject(answer).optJSONObject("data");
				String dirId = data.optString("dirId");

				// 上传文件
				for (int i = 0; i < notePagelst.size(); i++) {
					ArchiveNotePage archiveNotePageBean = notePagelst.get(i);

					Map<String, Object> archiveNotePageDataParam = new HashMap<String, Object>();

					archiveNotePageDataParam.put("archiveNoteId", archiveNotePageBean.getArchiveNoteId());
					archiveNotePageDataParam.put("userId", archiveNotePageBean.getUserId());
					archiveNotePageDataParam.put("noteId", archiveNotePageBean.getNoteId());
					archiveNotePageDataParam.put("userPlatformCode", archiveNotePageBean.getUserPlatformCode());
					archiveNotePageDataParam.put("mediaType", 0);
					archiveNotePageDataParam.put("pageId", archiveNotePageBean.getPageId());

					List<ArchiveNotePage> archiveNotePage = archiveNotePageDao
							.listByCondition(archiveNotePageDataParam);

					if (archiveNotePage == null || archiveNotePage.size() == 0)
						continue;

					try {
						HttpURLConnection.setFollowRedirects(false);
						HttpURLConnection con;
						con = (HttpURLConnection) new URL(archiveNotePageBean.getPageFileUrl()).openConnection();
						con.setRequestMethod("HEAD");
						if (con.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND)
							continue;

						NoteTemplate noteTemplate = noteTemplateDao.loadNoteTemplate(archiveNotePageBean.getNoteId());

						String fileName = null;
						if (noteTemplate != null) {
							int startPageId = noteTemplate.getStartPageId();
							int pageId = archiveNotePageBean.getPageId();
							fileName = pageId - startPageId + 1 + ".png";
						} else {
							fileName = archiveNotePageBean.getPageId().toString() + ".png";
						}

						String createDiskReseResult = createDiskRes(dirId, fileName, userId, userPlatformCode,
								archiveNotePageBean.getPageFileUrl());
						isNullDir = false;
						System.out.println(createDiskReseResult);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					// Long fid =
					// uploadify(archiveNotePageBean.getPageFileUrl(),
					// userPlatformCode, userId,
					// archiveNotePageBean.getPageId() + ".png",
					// Long.toString(new Date().getTime()));// 上传文件,并且获取fid
					//
					// if (fid == null)
					// continue;
					// 创建资源
					// String createResourceResult = createResource(fid,
					// dirId,
					// archiveNotePageBean.getPageId().toString() + ".png",
					// userId, userPlatformCode);

				}

				if (isNullDir) {
					Map<String, Object> resourceMap = new HashMap<String, Object>();
					resourceMap.put("dirId", dirId);
					resourceMap.put("ownerType", "0");
					resourceMap.put("userId", userId);
					HttpUtils.getInstance().cmsHttpPost(
							basePropertyService.getPropertyValue("cmsUrl", userPlatformCode), "/removeResource",
							JSONObject.fromObject(resourceMap).toString());
				}
			}
		}
	}

	private String createDiskRes(String parentId, String title, String userId, String userPlatformCode, String imgUrl) {
		String fileUrl = SysConfigUtils.getStrValue("showImg_url") + imgUrl;
		Map<String, Object> paramss = new HashMap<String, Object>();
		// paramss.put("fid", "-1");
		paramss.put("title", title);
		paramss.put("userId", userId);
		paramss.put("resForm", "2");
		paramss.put("ownerType", "0");
		paramss.put("resSource", "0");
		paramss.put("url", fileUrl);
		paramss.put("parentId", parentId);
		paramss.put("platformCode", userPlatformCode);
		String answer = HttpUtils.getInstance().cmsHttpPost(
				basePropertyService.getPropertyValue("cmsUrl", userPlatformCode), "/createDiskRes",
				JSONObject.fromObject(paramss).toString());
		return answer;
	}

	private String createResource(Long fid, String parentId, String title, String userId, String userPlatformCode) {
		String answer = null;
		// String url = basePropertyService.getPropertyValue("cmsUrl",
		// userPlatformCode) + "/createResource";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("fid", fid);
		param.put("title", title);
		param.put("userId", userId);
		param.put("resForm", "1");
		param.put("ownerType", "0");
		param.put("resSource", "0");
		param.put("parentId", parentId);

		answer = HttpUtils.getInstance().cmsHttpPost(basePropertyService.getPropertyValue("cmsUrl", userPlatformCode),
				"/createDiskRes", JSONObject.fromObject(param).toString());

		// answer = HttpUtils.getInstance().httpPost(url,
		// JSONObject.fromObject(param).toString());
		return answer;
	}

	public String listResource(String userId, String platformCode, String parentId, int curPage, int numPerPage) {
		// String url = basePropertyService.getPropertyValue("cmsUrl",
		// platformCode) + "/listResources";

		JSONObject pageJsonObject = new JSONObject();
		pageJsonObject.put("curPage", curPage);
		pageJsonObject.put("numPerPage", numPerPage);

		JSONObject queryJsonObject = new JSONObject();
		queryJsonObject.put("parentId", parentId);
		queryJsonObject.put("userId", userId);
		queryJsonObject.put("ownerType", "0");

		JSONObject paramJsonObject = new JSONObject();
		paramJsonObject.put("page", pageJsonObject);
		paramJsonObject.put("query", queryJsonObject);

		String answer = HttpUtils.getInstance().cmsHttpPost(
				basePropertyService.getPropertyValue("cmsUrl", platformCode), "/listResources",
				paramJsonObject.toString());

		// String answer = HttpUtils.getInstance().httpPost(url,
		// paramJsonObject.toString());
		return answer;
	}

	// public String queryResource(String userId, String platformCode, String
	// parentId, int pageNum, int pageSize,
	// Boolean isFolder) {
	// String url = basePropertyService.getPropertyValue("cmsUrl", platformCode)
	// + "/queryResources";
	// Map<String, Object> resourceMap = new HashMap<String, Object>();
	// resourceMap.put("diskType", 0);
	// resourceMap.put("uid", userId);
	// resourceMap.put("parentCatalogId", parentId);
	// resourceMap.put("pageNum", 1);
	// resourceMap.put("pageSize", 10);
	// resourceMap.put("isFolder", true);
	// String answer = HttpUtils.getInstance().httpPost(url,
	// JSONObject.fromObject(resourceMap).toString());
	// return answer;
	// }

	// public void deleteAllMXBJDir() {
	//
	// }

	/**
	 * 获取指定文件夹的子目录和文件
	 * 
	 * @param userId
	 * @param platformCode
	 * @param parentId
	 * @return
	 */
	public List<Map<String, Object>> getSubDirByDirId(String userId, String platformCode, String parentId) {
		// Map<String, Object> dirMap = new HashMap<String, Object>();
		List<Map<String, Object>> ss = new ArrayList<Map<String, Object>>();

		int curPage = 1;
		int numPerPage = 10;
		// 归档目录
		String answer = listResource(userId, platformCode, parentId, curPage, numPerPage);
		String result = JSONObject.fromObject(answer).optString("result");
		if ("000000".equals(result)) {
			JSONObject dataJsonObject = JSONObject.fromObject(answer).optJSONObject("data");

			int count = dataJsonObject.optInt("count");

			int totalPageNum = (count + numPerPage - 1) / numPerPage;
			for (; curPage <= totalPageNum; curPage++) {
				String answerStr = listResource(userId, platformCode, parentId, curPage, numPerPage);
				JSONObject currentPageDataJsonObject = JSONObject.fromObject(answerStr).optJSONObject("data");
				JSONArray dataJsonArray = currentPageDataJsonObject.optJSONArray("list");
				if (dataJsonArray.size() > 0) {
					for (int i = 0; i < dataJsonArray.size(); i++) {
						Map<String, Object> map = new HashMap<String, Object>();
						JSONObject data = dataJsonArray.optJSONObject(i);
						map.put("dirId", data.optString("dirId"));
						map.put("dirName", data.optString("dirName"));
						map.put("isFolder", data.optString("isFolder"));
						ss.add(map);
					}
				}
			}
		}
		return ss;
	}

	/**
	 * 获取用户墨香笔记根目录
	 * 
	 * @param userId
	 * @param platformCode
	 * @return
	 */
	public String getMXBJDirId(String userId, String platformCode, String parentId, String dirName) {
		String dirId = null;
		String answer = listResource(userId, platformCode, parentId, 0, 10);
		String result = JSONObject.fromObject(answer).optString("result");

		if ("000000".equals(result)) {
			JSONObject dataJsonObject = JSONObject.fromObject(answer).optJSONObject("data");

			if (dataJsonObject != null) {
				JSONArray listJsonObject = dataJsonObject.optJSONArray("list");

				if (listJsonObject != null && listJsonObject.size() > 0) {
					for (int i = 0; i < listJsonObject.size(); i++) {
						JSONObject dirJsonObject = listJsonObject.optJSONObject(i);
						if (dirJsonObject != null) {
							if (SysConstants.PRODUCT_NAME.equals(dirJsonObject.opt("dirName"))) {
								dirId = dirJsonObject.optString("dirId");
								break;
							}
						}
					}
				}
			}
		}

		/**
		 * 没有目录则创建墨香笔记的目录
		 */
		if (dirId == null) {
			dirId = createFolder(userId, platformCode, parentId, dirName);
		}

		return dirId;
	}

	/**
	 * 创建文件夹
	 * 
	 * @param userId
	 * @param platformCode
	 * @return 目录文件夹的id
	 */
	public String createFolder(String userId, String platformCode, String parentId, String dirName) {
		String dirId = null;
		Map<String, Object> mm = new HashMap<String, Object>();
		mm.put("dirName", dirName);
		mm.put("parentId", parentId);
		mm.put("ownerType", "0");
		mm.put("userId", userId);

		// String url = basePropertyService.getPropertyValue("cmsUrl",
		// platformCode) + "/createFolder";
		// String answer = HttpUtils.getInstance().httpPost(url,
		// JSONObject.fromObject(mm).toString());

		String answer = HttpUtils.getInstance().cmsHttpPost(
				basePropertyService.getPropertyValue("cmsUrl", platformCode), "/createFolder",
				JSONObject.fromObject(mm).toString());

		String result = JSONObject.fromObject(answer).optString("result");

		if ("000000".equals(result)) {
			JSONObject data = JSONObject.fromObject(answer).optJSONObject("data");
			dirId = data.optString("dirId");
		}
		return dirId;
	}

	public Map<String, Object> shareToCloudCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.get("userId"));
		para.put("userPlatformCode", reqJson.get("userPlatformCode"));
		// para.put("noteList", reqJson.get("noteList"));
		// para.put("archiveNoteList", reqJson.get("archiveNoteList"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	@Override
	public void saveNotesBatch(Map<String, Object> map) {
		List<Map<String, Object>> noteList = (List<Map<String, Object>>) map.get("noteList");
		String userId = map.get("userId").toString();
		String userPlatformCode = map.get("userPlatformCode").toString();
		for (Map<String, Object> note : noteList) {
			note.put("userId", userId);
			note.put("userPlatformCode", userPlatformCode);
			saveOrUpdate(note);
		}
		shareEdu(userId, userPlatformCode);
	}

	@Override
	public Map<String, Object> modifyNoteListCheckParam(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.get("userId"));
		para.put("userPlatformCode", reqJson.get("userPlatformCode"));
		para.put("noteList", reqJson.get("noteList"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	public File downloadFile(String remoteFilePath, String localFilePath) {
		URL urlfile = null;
		HttpURLConnection httpUrl = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		File f = null;
		try {
			f = new File(localFilePath);
			urlfile = new URL(remoteFilePath);
			httpUrl = (HttpURLConnection) urlfile.openConnection();
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			bos = new BufferedOutputStream(new FileOutputStream(f));
			int len = 2048;
			byte[] b = new byte[len];
			while ((len = bis.read(b)) != -1) {
				bos.write(b, 0, len);
			}
			bos.flush();
			bis.close();
			httpUrl.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return f;
	}

	/**
	 * 上传文件到cms并返回fid 先从imageUrl 下载文件，然后在上传到cms
	 * 
	 * @param imageUrl
	 * @return
	 * @throws IOException
	 */
	public Long uploadify(String imageUrl, String platformCode, String userId, String fileName, String randomName)
			throws IOException {
		Long fid = null;
		try {
			String file_path_pre = SysConfigUtils.getStrValue("file_path_pre");

			// String fileName = Long.toString(new Date().getTime());

			// String filePath = file_path_pre + fileName;
			String filePath = file_path_pre + randomName;
			File file = downloadFile(imageUrl, filePath);

			// 上传文件
			HttpPost httpPost = new HttpPost(
					basePropertyService.getPropertyValue("cms_ft", platformCode) + "/uploadify");

			// ContentType contentType = ContentType.create("text/plain",
			// Consts.UTF_8);

			FileBody fileData = new FileBody(file);

			StringBody comment = new StringBody(userId, ContentType.TEXT_PLAIN);
			StringBody fileNameBody = new StringBody(fileName, ContentType.TEXT_PLAIN);

			HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("fileData", fileData)
					.addPart("userId", comment).addPart("fileName", fileNameBody).build();

			httpPost.setEntity(reqEntity);

			CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = httpclient.execute(httpPost);
			HttpEntity resEntity = response.getEntity();
			String ret = null;
			if (resEntity != null) {
				ret = EntityUtils.toString(resEntity);
			}
			EntityUtils.consume(resEntity);
			response.close();
			httpclient.close();
			System.out.println(ret);
			JSONObject jsonObject = JSONObject.fromObject(ret);
			fid = jsonObject.optLong("fid");
			// 用完删除文件
			FileUtils.deleteFile(file);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return fid;
	}

	@Override
	public void modifyNoteList(Map<String, Object> params) {
		String userId = params.get("userId").toString();
		String userPlatformCode = params.get("userPlatformCode").toString();
		JSONArray jsonArray = (JSONArray) params.get("noteList");
		if (null == jsonArray || jsonArray.size() == 0)
			return;

		Map<String, Object> noteMap = new HashMap<String, Object>();
		noteMap.put("userId", userId);
		noteMap.put("userPlatformCode", userPlatformCode);

		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject noteJsonObject = jsonArray.getJSONObject(i);
			String noteId = noteJsonObject.getString("noteId");
			noteMap.put("noteId", noteId);
			Note note = noteDao.loadNote(noteMap);
			if (null == note) { // 增加
				Note n = new Note();
				n.setCreateTime(new Date());

				n.setUserPlatformCode(userPlatformCode);
				n.setUserId(userId);
				n.setNoteId(noteId);

				n.setNoteName(noteJsonObject.getString("noteName"));

				if (noteJsonObject.getString("subjectId") != null) {
					n.setSubjectId(noteJsonObject.getString("subjectId"));
				}

				noteDao.save(n);
			} else { // 更新
				note.setUpdateTime(new Date());
				note.setNoteName(noteJsonObject.getString("noteName"));
				note.setSubjectId(noteJsonObject.getString("subjectId"));
				noteDao.update(note);
			}
		}
	}

	@Override
	public Map<String, Object> activateNoticeCheckParam(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("archiveNoteId", reqJson.get("archiveNoteId"));

		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	@Override
	public void activateNotice(Map<String, Object> map) {
		String archiveNoteId = map.get("archiveNoteId").toString();

		ArchiveNote bean = archiveNoteDao.loadById(archiveNoteId);

		if (bean == null)
			return;

		final String userId = bean.getUserId();
		final String userPlatformCode = bean.getUserPlatformCode();

		shareEdu(userId, userPlatformCode);

		archiveNoteDao.deleteById(archiveNoteId);
		archiveNotePageDao.deleteByArchiveNoteId(archiveNoteId);

	}

	@Override
	public Map<String, Object> synErrorNoteCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.get("userId"));
		para.put("userPlatformCode", reqJson.get("userPlatformCode"));
		para.put("noteList", reqJson.get("noteList"));

		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	@Override
	public void synErrorNote(Map<String, Object> map) {
		String userId = map.get("userId").toString();
		// String userPlatformCode = map.get("userPlatformCode").toString();

		String noteListStr = map.get("noteList").toString();

		JSONArray noteJsonArray = JSONArray.fromObject(noteListStr);

		// JSONArray noteJsonArray = (JSONArray) map.get("noteList");

		if (noteJsonArray.size() <= 0)
			return;

		JSONArray dataArray = new JSONArray();
		for (int i = 0; i < noteJsonArray.size(); i++) {
			JSONObject jsonObject = noteJsonArray.getJSONObject(i);
			String usercode = userId;
			String ctime = jsonObject.optString("ctime");
			String subject_code = jsonObject.optString("subjectCode");
			String subject_name = jsonObject.optString("subjectName");
			String source_code = jsonObject.optString("sourceCode");
			String source_name = jsonObject.optString("sourceName");
			String paper_code = jsonObject.optString("paperCode");
			String paper_name = jsonObject.optString("paperName");
			String q_type_code = jsonObject.optString("qTypeCode");
			String q_type_name = jsonObject.optString("qTypeName");
			// String q_type = jsonObject.optString("qType");
			String img_path = jsonObject.optString("imgPath");
			String q_code = jsonObject.optString("qCode");

			JSONObject valueParam = new JSONObject();
			valueParam.put("usercode", usercode);
			valueParam.put("ctime", ctime);
			valueParam.put("subject_code", subject_code);
			valueParam.put("subject_name", subject_name);
			valueParam.put("source_code", source_code);
			valueParam.put("source_name", source_name);
			valueParam.put("paper_code", paper_code);
			valueParam.put("paper_name", paper_name);
			valueParam.put("q_type_code", q_type_code);
			valueParam.put("q_type_name", q_type_name);
			valueParam.put("q_type", "1");
			valueParam.put("img_path", img_path);
			valueParam.put("q_code", q_code);

			// valueParam.put("usercode", "8f7c9c049f574daf999db40ee468ebcc");
			// valueParam.put("ctime", "20170331170654");
			// valueParam.put("subject_code", "jcsub18");
			// valueParam.put("subject_name", "1");
			// valueParam.put("source_code", "SP1488953161722");
			// valueParam.put("source_name", "墨香笔记");
			// valueParam.put("paper_code", "jcsub18BJ20170331");
			// valueParam.put("paper_name", "美术错题2017-03-31 10:38:04");
			// valueParam.put("q_type_code", "t3");
			// valueParam.put("q_type_name", "解答题");
			// valueParam.put("q_type", "1");
			// valueParam.put("img_path",
			// "http://whty.bj.bcebos.com/mnote/8f7c9c049f574daf999db40ee468ebcc420100/errorbooks/jcsub18PZ20170331/2_1.png");
			// valueParam.put("q_code", "2_1");

			dataArray.add(valueParam);
		}
		String rop_app_key = SysConfigUtils.getStrValue("rop.app.key");
		String rop_app_value = SysConfigUtils.getStrValue("rop.app.value");
		String rop_app_version = SysConfigUtils.getStrValue("rop.app.verion");
		try {
			Map<String, Object> aa = new HashMap<String, Object>();
			aa.put("appKey", rop_app_key);
			aa.put("messageFormat", "json");
			aa.put("method", "errornote.addbatch");
			aa.put("v", rop_app_version);
			String key = new StringBuffer().append(rop_app_value).append(Sha1.getOrderByLexicographic(aa))
					.append(rop_app_value).toString();
			String sign = Sha1.SHA1(key);

			StringBuffer sb = new StringBuffer();
			sb.append("appKey=").append(rop_app_key).append("&messageFormat=json&method=errornote.addbatch&v=")
					.append(rop_app_version).append("&sign=").append(sign).append("&data=")
					.append(URLEncoder.encode(dataArray.toString(), "UTF-8"));
			String rop_url = SysConfigUtils.getStrValue("rop.url") + "?" + sb.toString();

			String result = HttpUtils.getInstance().httpGet(rop_url);
			System.out.println(result);
		} catch (DigestException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> userConfigParams = new HashMap<String, Object>();
		userConfigParams.put("userId", map.get("userId"));
		userConfigParams.put("userPlatformCode", map.get("userPlatformCode"));
		userConfigParams.put("synErrorNoteLastTime", new Date());

		UserConfig userConfig = userDao.getUserConfig(userConfigParams);

		if (userConfig == null) {
			userConfigParams.put("isSynErrorNote", true);
			userConfigParams.put("isSynCloud", false);
			userConfigParams.put("createTime", new Date());
			userDao.saveUserConfig(userConfigParams);
		} else {
			userDao.updateUserConfig(userConfigParams);
		}
	}

	@Override
	public void shareEdu(final String userId, final String userPlatformCode) {
		ThreadPoolUtils.execute(new Runnable() {
			public void run() {
				try {
					List<String> ss = redisService.getList("mxbj", String.class);

					if (ss == null)
						ss = new ArrayList<String>();

					String value = userPlatformCode + "|" + userId;
					logger.info("----------" + value + "----------");
					ss.add(value);

					Set<String> hs = new HashSet<String>(ss);// 去重
					List<String> eg = new ArrayList<String>(hs);
					redisService.setList("mxbj", eg);

					// noteService.shareToCloud(map);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}

	@Override
	public void removeNote(Map<String, Object> map) {
		String noteIds = map.get("noteId").toString();
		String[] noteIdArr = noteIds.split(",");

		final String userId = map.get("userId").toString();
		final String userPlatformCode = map.get("userPlatformCode").toString();

		for (final String noteId : noteIdArr) {
			List<Note> notelst = noteDao.listByCondition(map);

			if (notelst == null || notelst.size() == 0)
				continue;

			final Note note = notelst.get(0);

			noteDao.deleteNote(note);

			Map<String, Object> pageMap = new HashMap<String, Object>();
			pageMap.put("userId", userId);
			pageMap.put("userPlatformCode", userPlatformCode);
			pageMap.put("noteId", noteId);

			pageDao.deletePage(map);
		}
		shareEdu(userId, userPlatformCode);// 同步一次数据
	}

	public void deleteEduDir(Note note) {
		String userId = note.getUserId();
		String userPlatformCode = note.getUserPlatformCode();
		String mxbjDirId = getMXBJDirId(userId, userPlatformCode, "0", SysConstants.PRODUCT_NAME);// 墨香笔记项目目录编码

		List<Map<String, Object>> mxbjSubDirList = getSubDirByDirId(userId, userPlatformCode, mxbjDirId);// 获取墨香笔记所有子文件夹

		for (int i = 0; i < mxbjSubDirList.size(); i++) {
			Map<String, Object> subDirMap = mxbjSubDirList.get(i);
			String dirId = subDirMap.get("dirId").toString();
			String isFolder = subDirMap.get("isFolder").toString();
			String dirName = subDirMap.get("dirName").toString();

			if ("0".equals(isFolder)) {

				String noteDirName = note.getNoteName() + "-"
						+ TimeUtils.date2String(note.getCreateTime(), TimeUtils.TIME_FORMAT);

				if (dirName.equals(noteDirName)) {
					// 先删除文件夹下面的文件
					List<Map<String, Object>> fileList = getSubDirByDirId(userId, userPlatformCode, dirId);// 只找到第二层
					for (int j = 0; j < fileList.size(); j++) {
						Map<String, Object> fileMap = fileList.get(j);
						String fileId = fileMap.get("dirId").toString();
						if ("1".equals(fileMap.get("isFolder").toString())) {// 删除文件
							// String url =
							// + ;// 删除文件夹
							Map<String, Object> resourceMap = new HashMap<String, Object>();
							resourceMap.put("dirId", fileId);
							resourceMap.put("ownerType", "0");
							resourceMap.put("userId", userId);
							// HttpUtils.getInstance().cmsHttpPost(url,
							// JSONObject.fromObject(resourceMap).toString());
							String b = HttpUtils.getInstance().cmsHttpPost(
									basePropertyService.getPropertyValue("cmsUrl", userPlatformCode), "/removeResource",
									JSONObject.fromObject(resourceMap).toString());
							System.out.println(b);
							// String result =
							// JSONObject.fromObject(answer).optString("result");
						}
					}

					Map<String, Object> resourceMap = new HashMap<String, Object>();
					resourceMap.put("dirId", dirId);
					resourceMap.put("ownerType", "0");
					resourceMap.put("userId", userId);
					String s = HttpUtils.getInstance().cmsHttpPost(
							basePropertyService.getPropertyValue("cmsUrl", userPlatformCode), "/removeResource",
							JSONObject.fromObject(resourceMap).toString());
					System.out.println(s);
					break;
				}
			}
		}
	}

	public void deleteArchiveEduDir(ArchiveNote archiveNote) {
		String userId = archiveNote.getUserId();
		String userPlatformCode = archiveNote.getUserPlatformCode();
		String mxbjDirId = getMXBJDirId(userId, userPlatformCode, "0", SysConstants.PRODUCT_NAME);// 墨香笔记项目目录编码
		List<Map<String, Object>> mxbjSubDirList = getSubDirByDirId(userId, userPlatformCode, mxbjDirId);// 获取墨香笔记所有子文件夹

		for (int i = 0; i < mxbjSubDirList.size(); i++) {
			Map<String, Object> subDirMap = mxbjSubDirList.get(i);
			String dirId = subDirMap.get("dirId").toString();
			String isFolder = subDirMap.get("isFolder").toString();
			String dirName = subDirMap.get("dirName").toString();

			if ("0".equals(isFolder)) {
				String noteDirName = "[归档]" + archiveNote.getNoteName() + "-"
						+ TimeUtils.date2String(archiveNote.getCreateTime(), TimeUtils.TIME_FORMAT);

				List<Map<String, Object>> fileList = getSubDirByDirId(userId, userPlatformCode, dirId);// 只找到第二层
				for (int j = 0; j < fileList.size(); j++) {
					Map<String, Object> fileMap = fileList.get(j);
					String fileId = fileMap.get("dirId").toString();
					if ("1".equals(fileMap.get("isFolder").toString())) {// 删除文件
						// String url =
						// + ;// 删除文件夹
						Map<String, Object> resourceMap = new HashMap<String, Object>();
						resourceMap.put("dirId", fileId);
						resourceMap.put("ownerType", "0");
						resourceMap.put("userId", userId);
						// HttpUtils.getInstance().cmsHttpPost(url,
						// JSONObject.fromObject(resourceMap).toString());
						String b = HttpUtils.getInstance().cmsHttpPost(
								basePropertyService.getPropertyValue("cmsUrl", userPlatformCode), "/removeResource",
								JSONObject.fromObject(resourceMap).toString());
						System.out.println(b);
					}
				}

				if (dirName.equals(noteDirName)) {
					Map<String, Object> resourceMap = new HashMap<String, Object>();
					resourceMap.put("dirId", dirId);
					resourceMap.put("ownerType", "0");
					resourceMap.put("userId", userId);
					HttpUtils.getInstance().cmsHttpPost(
							basePropertyService.getPropertyValue("cmsUrl", userPlatformCode), "/removeResource",
							JSONObject.fromObject(resourceMap).toString());
					break;
				}
			}
		}
	}

	@Override
	public Map<String, Object> removeNoteCheckParam(String body) throws BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.get("userId"));
		para.put("userPlatformCode", reqJson.get("userPlatformCode"));
		para.put("noteId", reqJson.get("noteId"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	@Override
	public Map<String, Object> removeArchiveNoteCheckParam(String body) throws BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("archiveNoteId", reqJson.get("archiveNoteId"));

		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	@Override
	public void removeArchiveNote(Map<String, Object> map) {
		String archiveNoteIds = map.get("archiveNoteId").toString();
		String[] archiveNoteIdArr = archiveNoteIds.split(",");

		for (final String archiveNoteId : archiveNoteIdArr) {
			final ArchiveNote archiveNote = archiveNoteDao.loadById(archiveNoteId);

			final String userId = archiveNote.getUserId();
			final String userPlatformCode = archiveNote.getUserPlatformCode();

			archiveNoteDao.deleteById(archiveNoteId);
			archiveNotePageDao.deleteByArchiveNoteId(archiveNoteId);

			shareEdu(userId, userPlatformCode);
		}
	}

	@Override
	public Map<String, Object> pageDataDeleteCheckParam(String body) throws BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.optString("userId"));
		para.put("noteId", reqJson.optString("noteId"));
		para.put("userPlatformCode", reqJson.optString("userPlatformCode"));
		para.put("pageIds", reqJson.get("pageIds"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	@Override
	public void pageDataDelete(Map<String, Object> map) {
		String pageJsonStr = map.get("pageIds").toString();

		JSONArray pageJsonArray = JSONArray.fromObject(pageJsonStr);

		List<String> list = JSONArray.toList(pageJsonArray);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", map.get("userId"));
		param.put("noteId", map.get("noteId"));
		param.put("userPlatformCode", map.get("userPlatformCode"));
		param.put("list", list);

		Note note = noteDao.loadNote(param);

		if (note != null) {
			noteDao.update(note);
		}

		pageDao.deletePage(param);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.mxbj.api.service.NoteService#getNoteCheckParam(java.lang.String)
	 */
	@Override
	public Map<String, Object> getNoteCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.optString("userId"));
		para.put("loginPlatformCode", reqJson.optString("loginPlatformCode"));
		para.put("textBookId", reqJson.optString("textBookId"));

		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.mxbj.api.service.NoteService#getNote(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> getNote(Map<String, Object> map) {
		List<TextbookNote> list = textbookNoteDao.listByCondition(map);

		List<Map<String, Object>> param = new ArrayList<Map<String, Object>>();

		for (TextbookNote textbookNote : list) {
			Map<String, Object> object = new HashMap<String, Object>();

			object.put("noteType", textbookNote.getNoteType());
			object.put("coverImageUrl", textbookNote.getCoverImageUrl());
			object.put("noteName", textbookNote.getNoteName());
			object.put("noteId", textbookNote.getNoteId());
			object.put("archiveNoteId", textbookNote.getArchiveNoteId());
			param.add(object);
		}
		return param;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.mxbj.api.service.NoteService#getTextbookCheckParam(java.lang.
	 * String)
	 */
	@Override
	public Map<String, Object> getTextbookCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.optString("userId"));
		para.put("loginPlatformCode", reqJson.optString("loginPlatformCode"));

		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.mxbj.api.service.NoteService#getTextbook(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> getTextbook(Map<String, Object> map) {
		List<TextbookNote> list = textbookNoteDao.listByCondition(map);

		List<Map<String, Object>> param = new ArrayList<Map<String, Object>>();

		for (TextbookNote textbookNote : list) {
			Map<String, Object> object = new HashMap<String, Object>();

			object.put("textBookId", textbookNote.getTextBookId());
			object.put("textBookName", textbookNote.getTextBookName());

			param.add(object);
		}
		return param;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.mxbj.api.service.NoteService#getPageCheckParam(java.lang.String)
	 */
	@Override
	public Map<String, Object> getPageCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.optString("userId"));
		para.put("loginPlatformCode", reqJson.optString("loginPlatformCode"));
		para.put("noteType", reqJson.optString("noteType"));
		para.put("noteId", reqJson.optString("noteId"));

		// 必填字段检查
		CommonFunction.checkParam(para);
		para.put("archiveNoteId", reqJson.optString("archiveNoteId"));
		return para;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.mxbj.api.service.NoteService#getPage(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> getPage(Map<String, Object> map) {
		String noteType = map.get("noteType").toString();

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		if (noteType.equals("1")) {
			Map<String, Object> pageParam = new HashMap<String, Object>();
			pageParam.put("noteId", map.get("noteId").toString());
			pageParam.put("userId", map.get("userId").toString());
			pageParam.put("userPlatformCode", map.get("loginPlatformCode").toString());
			pageParam.put("mediaType", 1);
			List<Page> list = pageDao.listByCondition(pageParam);

			for (Page page : list) {
				Map<String, Object> pageMap = new HashMap<String, Object>();
				pageMap.put("pageId", page.getPageId());
				pageMap.put("url", page.getPageFileUrl());
				result.add(pageMap);
			}

		} else if (noteType.equals("2")) {
			Map<String, Object> archiveNotePageMap = new HashMap<String, Object>();

			archiveNotePageMap.put("archiveNoteId", map.get("archiveNoteId"));

			List<ArchiveNotePage> list = archiveNotePageDao.listByCondition(archiveNotePageMap);

			for (ArchiveNotePage bean : list) {
				Map<String, Object> pageMap = new HashMap<String, Object>();
				pageMap.put("pageId", bean.getPageId());
				pageMap.put("url", bean.getPageFileUrl());
				result.add(pageMap);
			}

		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.mxbj.api.service.NoteService#setTextBookCheckParam(java.lang.
	 * String)
	 */
	@Override
	public Map<String, Object> setTextBookCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.optString("userId"));
		para.put("loginPlatformCode", reqJson.optString("loginPlatformCode"));
		para.put("noteType", reqJson.optString("noteType"));
		para.put("noteId", reqJson.optString("noteId"));
		para.put("textBookName", reqJson.optString("textBookName"));
		para.put("textBookId", reqJson.optString("textBookId"));

		para.put("planId", reqJson.optString("planId"));
		para.put("planName", reqJson.optString("planName"));
		para.put("pushedId", reqJson.optString("pushedId"));

		// 必填字段检查
		CommonFunction.checkParam(para);
		para.put("archiveNoteId", reqJson.optString("archiveNoteId"));
		return para;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.mxbj.api.service.NoteService#setTextBook(java.util.Map)
	 */
	@Override
	public void setTextBook(Map<String, Object> map) {
		String userId = map.get("userId").toString();
		String loginPlatformCode = map.get("loginPlatformCode").toString();
		String textBookId = map.get("textBookId").toString();
		String textBookName = map.get("textBookName").toString();
		String noteId = map.get("noteId").toString();
		String planId = map.get("planId").toString();
		String pushedId = map.get("pushedId").toString();
		String planName = map.get("planName").toString();
		String noteType = map.get("noteType").toString();
	
		
		TextbookNote bean = new TextbookNote();
		bean.setUserId(userId);
		bean.setLoginPlatformCode(loginPlatformCode);
		bean.setTextBookId(textBookId);
		bean.setTextBookName(textBookName);
		bean.setNoteId(noteId);
		bean.setPlanId(planId);
		bean.setPlanName(planName);
		bean.setPushedId(pushedId);
		bean.setNoteType(noteType);
		
		if(map.get("archiveNoteId")!=null){
			bean.setArchiveNoteId(map.get("archiveNoteId").toString());
		}
		
		bean.setCreateTime(new Date());
		bean.setUpdateTime(new Date());
		textbookNoteDao.save(bean);
		
	}

	@Override
	public JSONArray shareNote(String userID, String loginPlatformCode) {

		List<ArchiveNote> archiveNotes=archiveNoteDao.queryByUserId(userID,loginPlatformCode);
		List<Note> notes = noteDao.queryByUserId(userID,loginPlatformCode);
		JSONArray jsonArray = new JSONArray();
		if(archiveNotes.size()==0 && notes.size()==0){
			return jsonArray;
		}
		if(notes.size()>0){
			for(Note entity:notes){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("type",NoteType.NOTE);
				jsonObject.put("noteId",entity.getNoteId());
				jsonObject.put("noteName",entity.getNoteName());
				jsonObject.put("createTime",sdf.format(entity.getCreateTime()));
				jsonObject.put("coverImageUrl",entity.getCoverImageUrl());
				jsonArray.add(jsonObject);
			}
		}
		if(archiveNotes.size()>0){
			for(ArchiveNote entity:archiveNotes){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("type", NoteType.ARCHIVE_NOTE);
				jsonObject.put("archiveNoteId",entity.getArchiveNoteId());
				jsonObject.put("noteName",entity.getNoteName());
				jsonObject.put("createTime",sdf.format(entity.getCreateTime()));
				jsonObject.put("coverImageUrl",entity.getCoverImageUrl());
				jsonArray.add(jsonObject);
			}
		}
		return jsonArray;
	}

	@Override
	public JSONArray shareNotePage(String userId, String loginPlatformCode, String noteId) {
		List<Page> pages = pageDao.queryByUserId(userId,loginPlatformCode,noteId);
		JSONArray jsonArray = new JSONArray();
		if(pages.size()==0){
			return jsonArray;
		}

		if(pages.size()>0){
			for(Page entity:pages){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", GUIDGenerator.getGUID());
				jsonObject.put("pageTotal",pages.size());
				jsonObject.put("pageNum",entity.getPageId());
				jsonObject.put("pageFileUrl",entity.getPageFileUrl());
				jsonObject.put("createTime",sdf.format(entity.getCreateTime()));
				jsonArray.add(jsonObject);
			}
		}
		return jsonArray;
	}

	@Override
	public JSONArray shareANotePage(String userId, String loginPlatformCode,String archiveNoteId) {
		List<ArchiveNotePage> archiveNotePages = archiveNotePageDao.queryByArchiveNoteId(userId,loginPlatformCode,archiveNoteId);
		JSONArray jsonArray = new JSONArray();
		if(archiveNotePages.size()==0){
			return jsonArray;
		}

		if(archiveNotePages.size()>0){
			for(ArchiveNotePage entity:archiveNotePages){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id",entity.getArchiveNotePageId());
				jsonObject.put("pageTotal",archiveNotePages.size());
				jsonObject.put("pageNum",entity.getPageId());
				jsonObject.put("pageFileUrl",entity.getPageFileUrl());
				jsonObject.put("createTime",sdf.format(entity.getCreateTime()));
				jsonArray.add(jsonObject);
			}
		}
		return jsonArray;
	}

}
