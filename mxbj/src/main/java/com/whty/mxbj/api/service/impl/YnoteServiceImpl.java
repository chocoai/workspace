package com.whty.mxbj.api.service.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.mxbj.api.dao.ArchiveNoteDao;
import com.whty.mxbj.api.dao.ArchiveNotePageDao;
import com.whty.mxbj.api.dao.NoteDao;
import com.whty.mxbj.api.dao.NoteTemplateDao;
import com.whty.mxbj.api.dao.PageDao;
import com.whty.mxbj.api.model.ArchiveNote;
import com.whty.mxbj.api.model.ArchiveNotePage;
import com.whty.mxbj.api.model.Note;
import com.whty.mxbj.api.model.NoteTemplate;
import com.whty.mxbj.api.model.Page;
import com.whty.mxbj.api.service.YnoteService;
import com.whty.mxbj.common.utils.HttpUtils;
import com.whty.mxbj.common.utils.SysConfigUtils;
import com.whty.mxbj.common.utils.TimeUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class YnoteServiceImpl implements YnoteService {
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

	@Override
	public void synYnote(String accessToken, String userId, String userPlatformCode) {

		String userInfo;
		try {
			// userInfo = getUserInfo(accessToken);
			// JSONObject userInfoJson = JSONObject.fromObject(userInfo);

			// String mxbjPath = userInfoJson.optString("default_notebook");//
			String mxbjPath = null;
			// 默认笔记就是第三方笔记

			// String allMxbjSubNoteBook =
			// getSubNoteBookByNoteBookPath(accessToken,mxbjPath);

			String allnoteBook = getAllNoteBook(accessToken);// 获取所有笔记本

			// 先删除
			JSONArray jsonArray = JSONArray.fromObject(allnoteBook);
			// JSONArray jsonArray = JSONArray.fromObject(allMxbjSubNoteBook);
			// String mxbjPath = null;//墨香笔记本
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.optJSONObject(i);

				String path = jsonObject.optString("path");
				String name = jsonObject.optString("name");

				if (name.contains("墨香笔记")) {
					deleteNoteBook(path, accessToken);
				}

			}

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userId", userId);
			param.put("userPlatformCode", userPlatformCode);
			param.put("mediaType", 1);
			List<Note> noteList = noteDao.listByCondition(param);

			for (Note note : noteList) {
				// 笔记本文件夹名称
				String noteDirName = "墨香笔记-" + note.getNoteName() + "-"
						+ TimeUtils.date2String(note.getCreateTime(), TimeUtils.TIME_FORMAT);
				param.put("noteId", note.getNoteId());
				List<Page> notePagelst = pageDao.listByCondition(param);

				if (notePagelst == null || notePagelst.size() == 0)
					continue;

				// 创建笔记本
				String pathJson = createNoteBook(noteDirName, accessToken, mxbjPath);
				String path = JSONObject.fromObject(pathJson).optString("path");
				// 创建笔记
				for (Page pageBean : notePagelst) {
					NoteTemplate noteTemplate = noteTemplateDao.loadNoteTemplate(pageBean.getNoteId());
					String fileName = null;
					if (noteTemplate != null) {
						int startPageId = noteTemplate.getStartPageId();
						int pageId = pageBean.getPageId();
						fileName = pageId - startPageId + 1 + ".png";
					} else {
						fileName = pageBean.getPageId().toString() + ".png";
					}

					HttpURLConnection.setFollowRedirects(false);
					HttpURLConnection con;
					con = (HttpURLConnection) new URL(pageBean.getPageFileUrl()).openConnection();
					con.setRequestMethod("HEAD");
					if (con.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND)
						continue;

					String img = "<img src='" + pageBean.getPageFileUrl() + "'/ >";// 只有一张图片
					createNote(fileName, img, path, accessToken);
				}
			}

			Map<String, Object> pa = new HashMap<String, Object>();
			pa.put("userId", userId);
			pa.put("userPlatformCode", userPlatformCode);
			List<ArchiveNote> archiveNoteList = archiveNoteDao.listByCondition(pa);

			Map<String, Object> archiveNotePageMap = new HashMap<String, Object>();
			for (ArchiveNote archiveNote : archiveNoteList) {
				// 归档笔记本文件夹名称
				String noteDirName = "墨香笔记-" + "[归档]" + archiveNote.getNoteName() + "-"
						+ TimeUtils.date2String(archiveNote.getCreateTime(), TimeUtils.TIME_FORMAT);

				archiveNotePageMap.put("archiveNoteId", archiveNote.getArchiveNoteId());
				archiveNotePageMap.put("mediaType", "1");
				List<ArchiveNotePage> notePagelst = archiveNotePageDao.listByCondition(archiveNotePageMap);

				if (notePagelst == null || notePagelst.size() == 0)
					continue;

				// 创建笔记本
				String pathJson = createNoteBook(noteDirName, accessToken, mxbjPath);
				String path = JSONObject.fromObject(pathJson).optString("path");

				// 创建笔记
				for (ArchiveNotePage page : notePagelst) {

					HttpURLConnection.setFollowRedirects(false);
					HttpURLConnection con;
					con = (HttpURLConnection) new URL(page.getPageFileUrl()).openConnection();
					con.setRequestMethod("HEAD");
					if (con.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND)
						continue;

					String img = "<img src='" + page.getPageFileUrl() + "'/ >";// 只有一张图片
					createNote(page.getPageId().toString(), img, path, accessToken);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getUserInfo(String accessToken) {
		String ynoteApiUri = SysConfigUtils.getStrValue("ynote_api_uri") + "/yws/open/user/get.json";

		String getUserInfo = null;
		try {
			getUserInfo = HttpUtils.getInstance().yNoteHttpGet(ynoteApiUri, accessToken);
			logger.info(getUserInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getUserInfo;
	}

	public String getAllNoteBook(String accessToken) {
		String ynoteApiUri = SysConfigUtils.getStrValue("ynote_api_uri") + "/yws/open/notebook/all.json";
		String result = null;
		try {
			result = HttpUtils.getInstance().yNoteHttpPost(ynoteApiUri, null, accessToken);
			logger.info(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String createNoteBook(String noteName, String accessToken, String mxbjPath) {
		System.err.println("noteName:" + noteName + "accessToken:" + accessToken);
		String ynoteApiUri = SysConfigUtils.getStrValue("ynote_api_uri") + "/yws/open/notebook/create.json";
		String result = null;
		try {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("name", noteName));
			nvps.add(new BasicNameValuePair("path", mxbjPath));
			result = HttpUtils.getInstance().yNoteHttpPost(ynoteApiUri, nvps, accessToken);
			logger.info(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void deleteNoteBook(String notebook, String accessToken) {
		String ynoteApiUri = SysConfigUtils.getStrValue("ynote_api_uri") + "/yws/open/notebook/delete.json";
		// String result = null;
		try {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("notebook", notebook));
			String result = HttpUtils.getInstance().yNoteHttpPost(ynoteApiUri, nvps, accessToken);
			logger.info(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建笔记
	 * 
	 * @param title
	 * @param content
	 * @param notebook
	 * @param accessToken
	 * @return
	 */
	public String createNote(String title, String content, String notebook, String accessToken) {
		logger.info("title:" + title + " content:" + content + " notebook:" + notebook + " accessToken:" + accessToken);
		String ynoteApiUri = SysConfigUtils.getStrValue("ynote_api_uri") + "/yws/open/note/create.json";
		String result = null;
		try {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("title", title));
			nvps.add(new BasicNameValuePair("content", content));
			nvps.add(new BasicNameValuePair("notebook", notebook));

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("title", title);
			map.put("content", content);
			map.put("notebook", notebook);
			result = HttpUtils.getInstance().yNoteHttpPost(ynoteApiUri, nvps, accessToken,
					JSONObject.fromObject(map).toString(), title, content, notebook);

			logger.info(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void deleteNote(String path, String accessToken) {
		String ynoteApiUri = SysConfigUtils.getStrValue("ynote_api_uri") + "/yws/open/note/delete.json";
		// String result = null;
		try {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("path", path));
			String result = HttpUtils.getInstance().yNoteHttpPost(ynoteApiUri, nvps, accessToken);
			logger.info(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return result;
	}

	public String getSubNoteBookByNoteBookPath(String noteBookPath, String accessToken) {
		String ynoteApiUri = SysConfigUtils.getStrValue("ynote_api_uri") + "/yws/open/notebook/list.json";
		String result = null;
		try {
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("notebook", noteBookPath));
			result = HttpUtils.getInstance().yNoteHttpPost(ynoteApiUri, nvps, accessToken);
			logger.info(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
