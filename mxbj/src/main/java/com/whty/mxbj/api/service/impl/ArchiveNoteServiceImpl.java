package com.whty.mxbj.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.whty.mxbj.api.dao.ArchiveNoteDao;
import com.whty.mxbj.api.dao.ArchiveNotePageDao;
import com.whty.mxbj.api.model.ArchiveNote;
import com.whty.mxbj.api.model.ArchiveNotePage;
import com.whty.mxbj.api.service.ArchiveNoteService;
import com.whty.mxbj.common.utils.HttpUtils;
import com.whty.mxbj.common.utils.SysConfigUtils;

import net.sf.json.JSONObject;

@Component("archiveNote")
public class ArchiveNoteServiceImpl implements ArchiveNoteService {

	@Autowired
	private ArchiveNoteDao archiveNoteDao;

	@Autowired
	private ArchiveNotePageDao archiveNotePageDao;

	private String getDate(String url) {
		String[] urlArr = url.split("/");

		String noteIdDate = urlArr[urlArr.length - 2];

		String[] noteIdDateArr = noteIdDate.split("_");
		return noteIdDateArr[noteIdDateArr.length - 1];
	}

	// private String reStr(String url, String date) {
	// String[] urlArr = url.split("/");
	//
	// String noteIdDate = urlArr[urlArr.length - 2];
	//
	// String[] noteIdDateArr = noteIdDate.split("_");
	//
	// noteIdDateArr[noteIdDateArr.length - 1] = date;
	//
	// StringBuffer newNoteIdDate = new StringBuffer();
	// for (int i = 0; i < noteIdDateArr.length; i++) {
	// newNoteIdDate.append(noteIdDateArr[i] + "_");
	// }
	//
	// urlArr[urlArr.length - 2] = newNoteIdDate.toString().substring(0,
	// newNoteIdDate.length() - 1);
	//
	// StringBuffer newUrl = new StringBuffer();
	// for (int i = 0; i < urlArr.length; i++) {
	// newUrl.append(urlArr[i] + "/");
	// }
	//
	// return newUrl.toString().substring(0, newUrl.length() - 1);
	// }

	@Override
	public void updatePageUrl() {
		List<ArchiveNote> archiveNoteList = archiveNoteDao.listByCondition(new HashMap<String, Object>());

		for (ArchiveNote bean : archiveNoteList) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("archiveNoteId", bean.getArchiveNoteId());
			List<ArchiveNotePage> pageList = archiveNotePageDao.listByCondition(param);
			// String archiveTime = bean.getArchiveTime();
			for (ArchiveNotePage page : pageList) {
				String pageUrl = page.getPageFileUrl();

				String date = getDate(pageUrl);

				if (!date.equals(bean.getArchiveTime())) {
					bean.setArchiveTime(date);
					archiveNoteDao.updateArchiveTime(bean);
				}

				// String newPageUrl = reStr(pageUrl, archiveTime);
				// page.setPageFileUrl(newPageUrl);

				// archiveNotePageDao.updateUrl(page);

			}

		}

	}

	@Override
	public void tes2() {
		String userId = "060d40feb379426eb4e89538f22d4699";
		int curPage = 1;
		int numPerPage = 10;

		JSONObject pageJsonObject = new JSONObject();
		pageJsonObject.put("curPage", curPage);
		pageJsonObject.put("numPerPage", numPerPage);

		JSONObject queryJsonObject = new JSONObject();
		queryJsonObject.put("parentId", 0);
		queryJsonObject.put("userId", userId);
		queryJsonObject.put("ownerType", "0");
		
		JSONObject paramJsonObject = new JSONObject();
		paramJsonObject.put("page", pageJsonObject);
		paramJsonObject.put("query", queryJsonObject);

		String answer = HttpUtils.getInstance().cmsHttpPost("http://res.t.zjle.cn:20017/cms-gateway", "/listResources",
				paramJsonObject.toString());

		// String answer =
		// HttpUtils.getInstance().cmsHttpPost("http://res.zjer.cn:20017",
		// "/listResources",
		// paramJsonObject.toString());
		System.out.println(answer);
		//
		String fileUrl = "https://ss3.baidu.com/-rVXeDTa2gU2pMbgoY3K/it/u=1441620011,1638521921&fm=202&mola=new&crop=v1";
		Map<String, Object> paramss = new HashMap<String, Object>();
		paramss.put("fid", "-1");
		paramss.put("title", "默默");
		paramss.put("userId", userId);
		paramss.put("resForm", "2");
		paramss.put("ownerType", "0");
		paramss.put("resSource", "0");
		paramss.put("url", fileUrl);
		paramss.put("parentId", 0);
		paramss.put("platformCode", "330000");
		//
		String answer2 = HttpUtils.getInstance().cmsHttpPost("http://res.t.zjle.cn:20017/cms-gateway",
				"/createDiskRes", JSONObject.fromObject(paramss).toString());
		System.out.println(answer2);

//		Map<String, Object> resourceMap = new HashMap<String, Object>();
//		resourceMap.put("dirId", "f02fb8fb72fd4968bc78dfeafbcb0f9a");
//		resourceMap.put("ownerType", "0");
//		resourceMap.put("userId", userId);
//		// HttpUtils.getInstance().cmsHttpPost(url,
//		// JSONObject.fromObject(resourceMap).toString());
//		String b = HttpUtils.getInstance().cmsHttpPost("http://res.t.zjle.cn:20017/cms-gateway", "/removeResource",
//				JSONObject.fromObject(resourceMap).toString());
//		System.out.println(b);

//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("dirName", "墨香笔记");
//		param.put("parentId", "0");
//		param.put("ownerType", "0");
//		param.put("userId", userId);
//
//		String answeraa = HttpUtils.getInstance().cmsHttpPost("http://res.t.zjle.cn:20017/cms-gateway", "/createFolder",
//				JSONObject.fromObject(param).toString());
//		System.out.println(answeraa);
	}

	// @Override
	// public HandlerResult queryArchiveNote(Map<String, Object> para) {
	// HandlerResult rs = new HandlerResult();
	// List<ArchiveNote> resultList = archiveNoteDao.listByCondition(para);
	// rs.setResultList(resultList);
	// return rs;
	// }

}
