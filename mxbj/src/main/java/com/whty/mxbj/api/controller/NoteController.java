package com.whty.mxbj.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whty.mxbj.common.utils.CommonFunction;
import com.whty.mxbj.common.utils.NoteType;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.whty.mxbj.api.model.TextbookNote;
import com.whty.mxbj.api.service.NoteService;
import com.whty.mxbj.api.thread.ShareToCloudThread;
import com.whty.mxbj.base.controller.BaseController;
import com.whty.mxbj.base.exception.BusinessException;
import com.whty.mxbj.base.service.IRedisService;
import com.whty.mxbj.common.constants.ResultConstants;
import com.whty.mxbj.common.thread.ThreadPoolFactory;
import com.whty.mxbj.common.thread.ThreadPoolUtils;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

//@RestController
@RequestMapping("api/note")
@Controller
public class NoteController extends BaseController {

	@Autowired
	private NoteService noteService;

	@Autowired
	private IRedisService redisService;

	private JSONObject resultMap;

	
	@RequestMapping(value="setTextBook")
	public void setTextBook(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		
		Map<String, Object> map;
		resultMap = new JSONObject();
		try {
			map = noteService.setTextBookCheckParam(body);
			noteService.setTextBook(map);
			
//			resultMap.put("pageList", list);
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
		}
		printJson(response, resultMap);
	}
	
	@RequestMapping(value="getPage")
	public void getPage(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		
		Map<String, Object> map;
		resultMap = new JSONObject();
		try {
			map = noteService.getPageCheckParam(body);
			List<Map<String,Object>> list = noteService.getPage(map);
			
			resultMap.put("pageList", list);
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
		}
		printJson(response, resultMap);
	}
	
	@RequestMapping(value="getTextbook")
	public void getTextbook(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		
		Map<String, Object> map;
		resultMap = new JSONObject();
		try {
			map = noteService.getTextbookCheckParam(body);
			List<Map<String,Object>> list = noteService.getTextbook(map);
			
			resultMap.put("textBookList", list);
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
		}
		printJson(response, resultMap);
		
	}
	
	@RequestMapping(value="getNote")
	public void getNote(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		
		Map<String, Object> map;
		resultMap = new JSONObject();
		try {
			map = noteService.getNoteCheckParam(body);
			List<Map<String,Object>> list = noteService.getNote(map);
			resultMap.put("noteList", list);
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
		}
		printJson(response, resultMap);
		
	}

	/**
	 * 查看当前用户的笔记和归档笔记List
	 * @param request
	 * @param response
	 * @param body（userId,loginPlatformCode）
	 */
	@RequestMapping(value = "shareNote",method = RequestMethod.POST)
	public void shareNote(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		resultMap = new JSONObject();
		try{
			logger.info(body);
			JSONObject reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
			// 参数检验
			if (!CommonFunction.isNotNull(reqJson)) {
				throw new BusinessException("参数不存在");
			}
			Map<String, Object> para = new HashMap<String, Object>();
			para.put("userId", reqJson.optString("userId"));
			para.put("loginPlatformCode", reqJson.optString("loginPlatformCode"));
			// 必填字段检查
			CommonFunction.checkParam(para);
			JSONArray jsonArray = noteService.shareNote(para.get("userId").toString(),para.get("loginPlatformCode").toString());
			resultMap.put("data", jsonArray);
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		}catch (BusinessException e) {
			e.printStackTrace();
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, e.getMessage());
		}catch (Exception e){
			e.printStackTrace();
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
		}
		printJson(response, resultMap);
	}

	/**
	 * 查看当前用户的笔记和归档笔记详情
	 * @param request
	 * @param response
	 * @param body（userId,loginPlatformCode,type）
	 */
	@RequestMapping(value = "shareNotePage",method = RequestMethod.POST)
	public void shareNotePage(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		resultMap = new JSONObject();
		try{
			logger.info(body);
			JSONObject reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
			// 参数检验
			if (!CommonFunction.isNotNull(reqJson)) {
				throw new BusinessException("参数不存在");
			}
			Map<String, Object> para = new HashMap<String, Object>();
			para.put("type", reqJson.optString("type"));
			// 必填字段检查
			CommonFunction.checkParam(para);
			JSONArray jsonArray = new JSONArray();
			if(para.get("type").toString().equals(NoteType.NOTE)){
				Map<String, Object> paraNOTE = new HashMap<String, Object>();
				paraNOTE.put("userId", reqJson.optString("userId"));
				paraNOTE.put("loginPlatformCode", reqJson.optString("loginPlatformCode"));
				paraNOTE.put("noteId", reqJson.optString("noteId"));
				// 必填字段检查
				CommonFunction.checkParam(paraNOTE);
				jsonArray = noteService.shareNotePage(paraNOTE.get("userId").toString(),
						paraNOTE.get("loginPlatformCode").toString(),paraNOTE.get("noteId").toString());
			}
			if(para.get("type").toString().equals(NoteType.ARCHIVE_NOTE)){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("userId", reqJson.optString("userId"));
				param.put("loginPlatformCode", reqJson.optString("loginPlatformCode"));
				param.put("archiveNoteId", reqJson.optString("archiveNoteId"));
				// 必填字段检查
				CommonFunction.checkParam(param);
				jsonArray = noteService.shareANotePage(param.get("userId").toString(),param.get("loginPlatformCode").toString()
				,param.get("archiveNoteId").toString());
			}

			resultMap.put("data", jsonArray);
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		}catch (BusinessException e) {
			e.printStackTrace();
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, e.getMessage());
		}catch (Exception e){
			e.printStackTrace();
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
		}
		printJson(response, resultMap);
	}

	@RequestMapping(value = "test")
	public void test(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		Map<String, Object> map;
		resultMap = new JSONObject();

		try {
			map = noteService.pageDataSubmitCheckParam(body);
			noteService.shareToCloud(map);
			// ThreadPoolManager.excutePool("ShareToCloudTask", new
			// ShareToCloudTask(map));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("111");
		// return resultMap.toString();
	}

	@RequestMapping(value = "removeArchiveNote", method = RequestMethod.POST)
	public void removeArchiveNote(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		Map<String, Object> map;
		resultMap = new JSONObject();
		try {
			map = noteService.removeArchiveNoteCheckParam(body);
			noteService.removeArchiveNote(map);

			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
		}
		printJson(response, resultMap);
	}

	/**
	 * 删除笔记本
	 * 
	 * @param response
	 * @param request
	 * @param body
	 */
	@RequestMapping(value = "removeNote", method = RequestMethod.POST)
	public void removeNote(HttpServletResponse response, HttpServletRequest request, @RequestBody String body) {
		Map<String, Object> map;
		resultMap = new JSONObject();
		try {
			map = noteService.removeNoteCheckParam(body);
			noteService.removeNote(map);

			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
		}
		printJson(response, resultMap);
		;
		// return resultMap.toString();
	}

	@RequestMapping(value = "synErrorNote", method = RequestMethod.POST)
	public void synErrorNote(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		final Map<String, Object> map;

		resultMap = new JSONObject();
		try {
			map = noteService.synErrorNoteCheckParam(body);
			// noteService.synErrorNote(map);
			// 分享到错题本
			ThreadPoolUtils.execute(new Runnable() {
				public void run() {
					try {
						noteService.synErrorNote(map);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			});

			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
		}
		printJson(response, resultMap);
		// return resultMap.toString();
	}

	/**
	 * 分享笔记本到我的云盘
	 * 
	 * @param request
	 * @param response
	 * @param body
	 */
	@RequestMapping(value = "shareToCloud")
	public void shareToCloud(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		final Map<String, Object> map;

		resultMap = new JSONObject();
		try {
			map = noteService.shareToCloudCheckParam(body);
			String userId = map.get("userId").toString();
			String userPlatformCode = map.get("userPlatformCode").toString();
			noteService.shareEdu(userId, userPlatformCode);

			// noteService.shareToCloud(map);
			// 分享到我的云盘

			// ShareToCloudThread shareToCloudTask = new
			// ShareToCloudThread(map);

			// ThreadPoolFactory.getInstance().execute(shareToCloudTask);

			// ThreadPoolUtils.execute(new Runnable() {
			// public void run() {
			// try {
			// noteService.shareToCloud(map);
			// } catch (Exception ex) {
			// ex.printStackTrace();
			// }
			// }
			// });

			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);

		} catch (Exception e) {
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
			e.printStackTrace();
		}
		printJson(response, resultMap);
		// return resultMap.toString();
	}

	/**
	 * 激活通知接口
	 * 
	 * @param request
	 * @param response
	 * @param body
	 */
	@RequestMapping(value = "activateNotice")
	public void activateNotice(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		Map<String, Object> map;

		resultMap = new JSONObject();
		try {
			map = noteService.activateNoticeCheckParam(body);
			noteService.activateNotice(map);
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);

		} catch (Exception e) {
			e.printStackTrace();
			String fullStackTrace = ResultConstants.FAIL_MESSAGE;
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, fullStackTrace);
		}
		printJson(response, resultMap);
		// return resultMap.toString();
	}

	@RequestMapping(value = "addBatch", method = RequestMethod.POST)
	public void addBatch(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		Map<String, Object> map;

		resultMap = new JSONObject();
		try {
			map = noteService.addBatchCheckParam(body);
			noteService.saveNotesBatch(map);
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);

		} catch (Exception e) {
			e.printStackTrace();
			String fullStackTrace = ResultConstants.FAIL_MESSAGE;
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, fullStackTrace);
		}
		printJson(response, resultMap);
		// return resultMap.toString();
	}

	/**
	 * 修改笔记本名字
	 * 
	 * @param request
	 * @param response
	 * @param body
	 */
	@RequestMapping(value = "modifyName", method = RequestMethod.POST)
	public void modifyName(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		Map<String, Object> map;

		resultMap = new JSONObject();
		try {
			map = noteService.modifyCheckParam(body);

			String noteName = map.get("noteName").toString();

			map.remove("noteName");
			List<Map<String, Object>> noteList = noteService.getNotes(map);

			if (noteList == null || noteList.size() == 0) {
				resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
				resultMap.put(ResultConstants.RESULT_MSG, "未找到笔记本");
			} else {
				map.put("noteName", noteName);
				noteService.modifyName(map);
				resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
				resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
			}

		} catch (Exception e) {
			e.printStackTrace();
			String fullStackTrace = ResultConstants.FAIL_MESSAGE;
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, fullStackTrace);
		}
		printJson(response, resultMap);
		// return resultMap.toString();
	}

	/**
	 * 添加笔记本信息
	 * 
	 * @param request
	 * @param response
	 * @param body
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public void add(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		Map<String, Object> map;
		resultMap = new JSONObject();
		try {

			map = noteService.addCheckParam(body);
			noteService.saveOrUpdate(map);
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			String fullStackTrace = ResultConstants.FAIL_MESSAGE;
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, fullStackTrace);
		}
		printJson(response, resultMap);
		// return resultMap.toString();
	}

	/**
	 * 获取个人笔记本列表
	 * 
	 * @param response
	 * @param request
	 * @param body
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public void list(HttpServletResponse response, HttpServletRequest request, @RequestBody String body) {
		Map<String, Object> map;
		resultMap = new JSONObject();
		try {
			map = noteService.listCheckParam(body);

			List<Map<String, Object>> noteList = noteService.getNotes(map);
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
			resultMap.put("noteList", noteList);
		} catch (Exception e) {
			e.printStackTrace();
			String fullStackTrace = ExceptionUtils.getFullStackTrace(e);
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, fullStackTrace);
		}
		printJson(response, resultMap);
		// return resultMap.toString();
	}

	/**
	 * 笔记数据上传
	 * 
	 * @param request
	 * @param response
	 * @param body
	 */
	@RequestMapping(value = "pageDataSubmit", method = RequestMethod.POST)
	public void pageDataSubmit(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		final Map<String, Object> map;
		resultMap = new JSONObject();
		try {
			map = noteService.pageDataSubmitCheckParam(body);

			noteService.pagetDataSubmit(map);

			final String userId = map.get("userId").toString();
			final String userPlatformCode = map.get("userPlatformCode").toString();

			noteService.shareEdu(userId, userPlatformCode);

			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			String fullStackTrace = ResultConstants.FAIL_MESSAGE;
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, fullStackTrace);
		}
		printJson(response, resultMap);
		// return resultMap.toString();
	}

	/**
	 * 收藏列表
	 * 
	 * @param request
	 * @param response
	 * @param body
	 */
	@RequestMapping(value = "archive/list", method = RequestMethod.POST)
	public void archiveList(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		Map<String, Object> map;
		resultMap = new JSONObject();
		try {
			map = noteService.archiveListCheckParam(body);
			List<Map<String, Object>> noteList = noteService.getArchiveNotes(map);
			resultMap.put("noteList", noteList);
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();

			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
		}
		printJson(response, resultMap);
		// return resultMap.toString();
	}

	@RequestMapping(value = "archive/pageList", method = RequestMethod.POST)
	public void archivePageList(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		Map<String, Object> map;
		resultMap = new JSONObject();

		try {
			map = noteService.archivePageListCheckParam(body);
			List<Map<String, Object>> pageList = noteService.getArchivePageList(map);
			resultMap.put("pageList", pageList);
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
		}
		printJson(response, resultMap);
		// return resultMap.toString();
	}

	@RequestMapping(value = "pageDataDelete", method = RequestMethod.POST)
	public void pageDataDelete(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		Map<String, Object> map;

		resultMap = new JSONObject();
		try {
			System.out.println(body);
			map = noteService.pageDataDeleteCheckParam(body);
			// noteService.archiveNote(map);

			// final String userId = map.get("userId").toString();
			// final String userPlatformCode =
			// map.get("userPlatformCode").toString();
			//
			noteService.pageDataDelete(map);

			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
		}
		printJson(response, resultMap);

	}

	/**
	 * 收藏笔记本
	 * 
	 * @param request
	 * @param response
	 * @param body
	 */
	@RequestMapping(value = "archiveNote", method = RequestMethod.POST)
	public void archiveNote(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		Map<String, Object> map;

		resultMap = new JSONObject();
		try {
			System.out.println(body);
			map = noteService.archiveNoteCheckParam(body);
			noteService.archiveNote(map);

			final String userId = map.get("userId").toString();
			final String userPlatformCode = map.get("userPlatformCode").toString();

			noteService.shareEdu(userId, userPlatformCode);

			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
		}
		printJson(response, resultMap);
	}

	/**
	 * 批量更新笔记本
	 * 
	 * @param request
	 * @param response
	 * @param body
	 */
	@RequestMapping(value = "modifyNoteList", method = RequestMethod.POST)
	public void modifyNoteList(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		Map<String, Object> param;

		resultMap = new JSONObject();
		try {
			param = noteService.modifyNoteListCheckParam(body);
			noteService.modifyNoteList(param);

			final String userId = param.get("userId").toString();
			final String userPlatformCode = param.get("userPlatformCode").toString();

			noteService.shareEdu(userId, userPlatformCode);

			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
		}
		printJson(response, resultMap);
		// return resultMap.toString();
	}

}
