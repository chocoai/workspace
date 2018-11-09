package com.whty.mxbj.page.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.util.StringUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.JsonArray;
import com.mysql.jdbc.StringUtils;
import com.whty.mxbj.api.dao.ArchiveNoteDao;
import com.whty.mxbj.api.dao.ArchiveNotePageDao;
import com.whty.mxbj.api.dao.NoteDao;
import com.whty.mxbj.api.dao.NoteTemplateDao;
import com.whty.mxbj.api.dao.PageDao;
import com.whty.mxbj.api.dao.TextbookNoteDao;
import com.whty.mxbj.api.dao.UserDao;
import com.whty.mxbj.api.model.ArchiveNote;
import com.whty.mxbj.api.model.ArchiveNotePage;
import com.whty.mxbj.api.model.BaseNote;
import com.whty.mxbj.api.model.LessonTree;
import com.whty.mxbj.api.model.NoteTemplate;
import com.whty.mxbj.api.model.Page;
import com.whty.mxbj.api.model.TextbookNote;
import com.whty.mxbj.api.model.User;
import com.whty.mxbj.api.service.BasePropertyService;
import com.whty.mxbj.base.controller.BaseController;
import com.whty.mxbj.common.utils.HttpUtils;
import com.whty.mxbj.common.utils.NoteType;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 我的笔记本
 * 
 * @author zhangzheng
 *
 */
@RequestMapping(value = "/myMnote")
@Controller
public class MyMnoteController extends BaseController {

	Logger logger = LoggerFactory.getLogger(MyMnoteController.class);

	@Autowired
	private NoteDao noteDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private PageDao pageDao;

	@Autowired
	private ArchiveNotePageDao archiveNotePageDao;

	@Autowired
	private BasePropertyService basePropertyService;

	@Autowired
	private NoteTemplateDao noteTemplateDao;

	@Autowired
	private TextbookNoteDao textbookNoteDao;

	@Autowired
	private ArchiveNoteDao archiveNoteDao;

	private String parseSuffix(String url) {
		Pattern pattern = Pattern.compile("\\S*[?]\\S*");
		Matcher matcher = pattern.matcher(url);

		String[] spUrl = url.toString().split("/");
		int len = spUrl.length;
		String endUrl = spUrl[len - 1];

		if (matcher.find()) {
			String[] spEndUrl = endUrl.split("\\?");
			return spEndUrl[0].split("\\.")[1];
		}
		return endUrl.split("\\.")[1];
	}

	@RequestMapping("synLessonRes")
	public void synLessonRes(HttpServletRequest request, HttpServletResponse response, Model model) {

		final String pushedId = request.getParameter("pushedId");
		final String textBookId = request.getParameter("textBookId");
		final String textBookName = request.getParameter("textBookName");
		final String planId = request.getParameter("planId");
		final String userId = request.getParameter("userId");
		final String loginPlatformCode = request.getParameter("loginPlatformCode");
		final String noteId = request.getParameter("noteId");
		final String noteType = request.getParameter("noteType");

		final String lessonId = request.getParameter("lessonId");

		if (org.apache.commons.lang.StringUtils.isEmpty(lessonId))
			return;

		final String userName = request.getParameter("userName");
		// 数据保存
		final String[] pageUrls = request.getParameterValues("pageUrl");

		if (pageUrls == null || pageUrls.length == 0)
			return;

		TextbookNote textbookNote = new TextbookNote();

		textbookNote.setUserId(userId);
		textbookNote.setLoginPlatformCode(loginPlatformCode);
		textbookNote.setTextBookId(textBookId);
		textbookNote.setTextBookName(textBookName);
		textbookNote.setCreateTime(new Date());
		textbookNote.setUpdateTime(new Date());
		textbookNote.setPlanId(planId);
		textbookNote.setPushedId(pushedId);
		textbookNote.setNoteType(noteType);

		if (NoteType.ARCHIVE_NOTE.equals(noteType)) {
			ArchiveNote archiveNote = archiveNoteDao.loadById(noteId);

			textbookNote.setArchiveNoteId(archiveNote.getArchiveNoteId());
			textbookNote.setNoteId(archiveNote.getNoteId());

		} else if (NoteType.NOTE.equals(noteType)) {
			textbookNote.setNoteId(noteId);
		}

		textbookNoteDao.save(textbookNote);

		new Thread(new Runnable() {
			@Override
			public void run() {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("userId", userId);
				param.put("loginPlatformCode", loginPlatformCode);
				param.put("lessonId", lessonId);
				param.put("userName", userName);

				List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();

				for (int i = 0; i < pageUrls.length; i++) {
					String qqq = pageUrls[i];
					// String qqq =
					// "http://whty.bj.bcebos.com/mnote/04af6a6de95541ce85cd362d242020c39999999/notebooks/3_212_0/2.png";

					Map<String, Object> pageParam = new HashMap<String, Object>();
					pageParam.put("resPreUrl", qqq);
					pageParam.put("resDownloadUrl", qqq);
					pageParam.put("resExt", parseSuffix(qqq));

					try {
						CloseableHttpClient httpclient = HttpClients.createDefault();
						HttpGet httpGet = new HttpGet(qqq);
						CloseableHttpResponse response1 = httpclient.execute(httpGet);
						System.out.println(response1.getStatusLine());
						HttpEntity httpEntity = response1.getEntity();
						// long contentLength = httpEntity.getContentLength();
						InputStream is = httpEntity.getContent();
						// 根据InputStream 下载文件
						ByteArrayOutputStream output = new ByteArrayOutputStream();
						byte[] buffer = new byte[4096];
						int r = 0;
						// long totalRead = 0;
						while ((r = is.read(buffer)) > 0) {
							output.write(buffer, 0, r);
							// totalRead += r;

						}
						// file_path_pre
						String filePath = com.whty.mxbj.common.utils.SysConfigUtils.getStrValue("file_path_pre");
						filePath = filePath + java.io.File.separator + userId + java.io.File.separator
								+ System.currentTimeMillis();

						File file = new File(filePath);

						if (!file.exists()) {
							// 先得到文件的上级目录，并创建上级目录，在创建文件
							file.getParentFile().mkdir();
							try {
								// 创建文件
								file.createNewFile();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}

						FileOutputStream fos = new FileOutputStream(filePath);
						output.writeTo(fos);
						output.flush();
						output.close();
						fos.close();
						response1.close();
						EntityUtils.consume(httpEntity);

						pageParam.put("resName", file.getName());
						pageParam.put("size", file.length());

						res.add(pageParam);
					} catch (IOException e) {
						e.printStackTrace();
					} finally {

					}
				}
				param.put("res", res);
				String url = com.whty.mxbj.common.utils.SysConfigUtils.getStrValue("syn_lesson_res");
				String result = HttpUtils.getInstance().httpPost(url, JSONObject.fromObject(param).toString());
				System.out.println(result);
			}

		}).start();

	}

	/**
	 * 获取用户教材
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("lessonTree")
	public void lessonTree(HttpServletRequest request, HttpServletResponse response, Model model) {

		 String pushedId = request.getParameter("pushedId");
		 String loginPlatformCode = request.getParameter("loginPlatformCode");

//		String pushedId = "72339E1EBB3C3931E0539406A8C01083";
//		String loginPlatformCode = "888888";

		Map<String, Object> lessonTreeParam = new HashMap<String, Object>();

		lessonTreeParam.put("pushedId", pushedId);
		lessonTreeParam.put("loginPlatformCode", loginPlatformCode);

		String url = com.whty.mxbj.common.utils.SysConfigUtils.getStrValue("lesson_true");
		String result = HttpUtils.getInstance().httpPost(url, JSONObject.fromObject(lessonTreeParam).toString());

		JSONObject resultJson = JSONObject.fromObject(result);

		List<LessonTree> lessonTreeList = new ArrayList<LessonTree>();
		// JSONArray resultJsonData = new JSONArray();
		if (!"".equals(resultJson.optString("result"))) {

			JSONArray jsonData = resultJson.optJSONArray("data");

			// resultJsonData = jsonData;
			for (int i = 0; i < jsonData.size(); i++) {
				JSONObject data = jsonData.optJSONObject(i);

				lessonTreeList.add(getTree(data));

			}
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", lessonTreeList);
		System.out.println(resultMap);
		printJson(response, resultMap);

	}

	LessonTree getTree(JSONObject node) {
		JSONArray lessonList = node.optJSONArray("lessonList");
		JSONArray childChapterList = node.optJSONArray("childChapterList");
		LessonTree bean = new LessonTree();

		bean.setId(node.optString("chapterId"));
		bean.setText(node.optString("chapterName"));
		String isCms = node.optString("isCms");
		if (isCms.equals("")) {
			bean.setLession(true);
		} else {
			bean.setLession(false);
		}

		List<LessonTree> subTree = new ArrayList<LessonTree>();

		if (lessonList != null && lessonList.size() > 0) {

			for (int i = 0; i < lessonList.size(); i++) {
				JSONObject lessonObject = lessonList.optJSONObject(i);

				LessonTree lessonTree = getTree(lessonObject);

				if (childChapterList != null & childChapterList.size() > 0) {
					List<LessonTree> childChapterTree = new ArrayList<LessonTree>();

					for (int j = 0; j < childChapterList.size(); j++) {
						JSONObject chpaterObject = childChapterList.optJSONObject(j);

						LessonTree chpaterTree = getTree(chpaterObject);
						childChapterTree.add(chpaterTree);
					}
					lessonTree.setSubTree(childChapterTree);
				}
				subTree.add(lessonTree);
			}

		} else {
			if (childChapterList != null & childChapterList.size() > 0) {
				// List<LessonTree> childChapterTree = new
				// ArrayList<LessonTree>();
				for (int j = 0; j < childChapterList.size(); j++) {
					JSONObject chpaterObject = childChapterList.optJSONObject(j);
					LessonTree chpater = new LessonTree();
					chpater.setId(chpaterObject.optString("chapterId"));
					chpater.setText(chpaterObject.optString("chapterName"));
					chpater.setLession(false);

					subTree.add(chpater);
				}

			}
		}

		bean.setSubTree(subTree);

		return bean;
	}

	/**
	 * 笔记本阅读器
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("mnote.html")
	public String mnote(HttpServletRequest request, HttpServletResponse response, Model model) {

		String id = request.getParameter("id");

		String value = null;

		try {
			value = new String(Base64.decodeBase64(id.getBytes()));

			String[] valueArr = value.split("#");

			String noteType = valueArr[0];// 笔记本类型
			String userPlatformCode = valueArr[1];
			String userId = valueArr[2];
			String noteId = valueArr[3];
			String coverImageUrl = valueArr[4];
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userPlatformCode", userPlatformCode);
			param.put("userId", userId);
			param.put("mediaType", 1);

			int startPageId = -1;
			NoteTemplate noteTemplate = noteTemplateDao.loadNoteTemplate(noteId);

			if (noteTemplate != null) {
				startPageId = noteTemplate.getStartPageId();
			}

			String title = null;
			List<?> pageList = null;
			if (NoteType.NOTE.equals(noteType)) {
				param.put("noteId", noteId);
				pageList = pageDao.listByCondition(param);
				title = NoteType.NOTE;

			} else if (NoteType.ARCHIVE_NOTE.equals(noteType)) {
				param.put("archiveNoteId", noteId);
				pageList = archiveNotePageDao.listByCondition(param);
				title = NoteType.ARCHIVE_NOTE;

			}

			List<Object> resultPageList = new ArrayList<Object>();
			for (Object object : pageList) {
				if (object instanceof Page) {
					Page page = (Page) object;

					if (startPageId == -1) {
						NoteTemplate noteTemplate2 = noteTemplateDao.loadNoteTemplate(page.getNoteId());
						if (noteTemplate2 != null) {
							startPageId = noteTemplate2.getStartPageId();
						} else {
							startPageId = 0;
						}
					}

					page.setPageId(page.getPageId() - startPageId + 1);
					resultPageList.add(page);
				}

				if (object instanceof ArchiveNotePage) {
					ArchiveNotePage page = (ArchiveNotePage) object;

					if (startPageId == -1) {
						NoteTemplate noteTemplate2 = noteTemplateDao.loadNoteTemplate(page.getNoteId());
						if (noteTemplate2 != null) {
							startPageId = noteTemplate2.getStartPageId();
						} else {
							startPageId = 0;
						}
					}
					page.setPageId(page.getPageId() - startPageId + 1);
					resultPageList.add(page);
				}
			}

			// TODO 测试环境先写死
			Map<String, Object> myListParam = new HashMap<String, Object>();
//			myListParam.put("userId", "e3205552d4e44f04a7aabcbcbed83af4");
//			myListParam.put("loginPlatformCode", "888888");

			myListParam.put("userId", userId);
			myListParam.put("loginPlatformCode", userPlatformCode);
			
			String url = com.whty.mxbj.common.utils.SysConfigUtils.getStrValue("plan_myList_url");
			String result = HttpUtils.getInstance().httpPost(url, JSONObject.fromObject(myListParam).toString());

			JSONObject resultJson = JSONObject.fromObject(result);

			List<Map<String, Object>> textBookList = new ArrayList<Map<String, Object>>();
			if (resultJson.optString("result").equals("000000")) {

				JSONArray jsonArray = resultJson.optJSONArray("data");

				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject data = jsonArray.optJSONObject(i);

					if (!"".equals(data.optString("planName"))) {
						Map<String, Object> textbookParam = new HashMap<String, Object>();
						textbookParam.put("planName", data.optString("planName"));
						textbookParam.put("planId", data.optString("planId"));
						textbookParam.put("pushedId", data.optString("pushedId"));

						textbookParam.put("textBookId", data.optString("textbookId"));
						textbookParam.put("textBookName", data.optString("textbookName"));

						textBookList.add(textbookParam);
					}
				}
			}

			Map<String, Object> userParam = new HashMap<String, Object>();
			userParam.put("userId", userId);
			userParam.put("userPlatformCode", userPlatformCode);
			User user = userDao.loadUserByUserIdAndPlatform(userParam);

			model.addAttribute("userName", user.getUserName());

			model.addAttribute("loginPlatformCode", userPlatformCode);
			model.addAttribute("textBookList", textBookList);
			model.addAttribute("title", title);

			model.addAttribute("userId", userId);

			// if (NoteType.NOTE.equals(noteType)) {
			// model.addAttribute("noteId", noteId);
			// } else if (NoteType.ARCHIVE_NOTE.equals(noteType)) {
			// model.addAttribute("archiveNoteId", noteId);
			// }
			model.addAttribute("noteId", noteId);
			model.addAttribute("noteType", noteType);
			model.addAttribute("coverImageUrl", coverImageUrl);
			model.addAttribute("pageList", resultPageList);
			return "myMnote/mnote";

		} catch (Exception e) {
			e.printStackTrace();
			return "myMnote/mnoteError";
		}

	}

	@RequestMapping("list.html")
	public String myMnote(HttpServletRequest request, HttpServletResponse response, Model model) {

		try {
			String noteType = request.getParameter("noteType");
			String userId = request.getParameter("userId");
			String userPlatformCode = request.getParameter("userPlatformCode");
			String usesessionId = request.getParameter("usesessionId");

			String aamUrl = basePropertyService.getPropertyValue("aam_url", userPlatformCode);

			// String aamtyHeartBeat = aamUrl + "account/heartbeat/" +
			// usesessionId;
			// String answer = HttpUtils.getInstance().httpGet(aamtyHeartBeat);
			// String result =
			// JSONObject.fromObject(answer).optString("result");

			String pageNum = request.getParameter("pageNum");
			String pageSize = request.getParameter("pageSize");

			int pageNumInt = 1;
			int pageSizeInt = 8;
			if (pageNum != null) {
				pageNumInt = Integer.valueOf(pageNum);
			}

			if (pageSize != null) {
				pageSizeInt = Integer.valueOf(pageSize);
			}

			PageHelper.startPage(pageNumInt, pageSizeInt);

			// if (!"000000".equals(result)) {
			// logger.info(userId + " 未登录!");
			// return "myMnote/mnoteError";
			// }

			String name = request.getParameter("name");

			if (noteType != null && noteType.equals("0")) {
				noteType = null;
			}

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userId", userId);
			paramMap.put("userPlatformCode", userPlatformCode);
			paramMap.put("noteName", name);
			paramMap.put("noteType", noteType);

			List<BaseNote> allNote = noteDao.findAllNote(paramMap);

			User user = userDao.loadUserByUserIdAndPlatform(paramMap);
			boolean isFind = false;
			if (user != null)
				isFind = true;

			model.addAttribute("name", name);
			model.addAttribute("isFind", isFind);
			model.addAttribute("pageNum", pageNumInt);
			model.addAttribute("pageSize", pageSizeInt);
			model.addAttribute("pageInfo", new PageInfo<BaseNote>(allNote));
			model.addAttribute("noteList", allNote);
			model.addAttribute("userId", userId);
			model.addAttribute("noteType", noteType);
			model.addAttribute("usesessionId", usesessionId);
			// model.addAttribute("archiveNoteList", archiveNoteList);
			// model.addAttribute("total", noteList.size() +
			// archiveNoteList.size());
			model.addAttribute("userPlatformCode", userPlatformCode);
			return "myMnote/list";
		} catch (Exception e) {
			e.printStackTrace();
			return "myMnote/mnoteError";
		}

	}

	/**
	 * 返回学期列表或返回栏目名称
	 * 根据url返回
	 */
	@ResponseBody
	@RequestMapping(value = "getShareTerm",method = RequestMethod.POST)
	public void getShareTerm(HttpServletResponse response,String url,String userId,String loginPlatformCode){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userId",userId);
		jsonObject.put("loginPlatformCode",loginPlatformCode);
		String res = HttpUtils.getInstance().httpPost(url,jsonObject.toString());
		JSONObject jsonObject2 = new JSONObject();
		if(StringUtil.isNotEmpty(res)){
			jsonObject2 = JSONObject.fromObject(res);
		}
		printText(response,jsonObject2.toString());
	}

	/**
	 *返回主题列表
	 */
	@ResponseBody
	@RequestMapping(value = "getShareTitleList",method = RequestMethod.POST)
	public void getShareTitleList(HttpServletResponse response,String url,String userId,String termid,String typeid){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userId",userId);
		jsonObject.put("termid",termid);
		jsonObject.put("typeid",typeid);
		String res = HttpUtils.getInstance().httpPost(url,jsonObject.toString());
		JSONObject jsonObject2 = new JSONObject();
		if(StringUtil.isNotEmpty(res)){
			jsonObject2 = JSONObject.fromObject(res);
		}
		printText(response,jsonObject2.toString());
	}

    /**
     * 笔记同步
     */
    public void noteAsync
}
