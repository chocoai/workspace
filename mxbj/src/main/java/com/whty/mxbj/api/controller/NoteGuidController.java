package com.whty.mxbj.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.mxbj.api.model.NoteGuidKey;
import com.whty.mxbj.api.service.NoteGuidKeyService;
import com.whty.mxbj.base.controller.BaseController;
import com.whty.mxbj.base.exception.BusinessException;
import com.whty.mxbj.base.service.IRedisService;
import com.whty.mxbj.common.constants.ResultConstants;

import net.sf.json.JSONObject;

//@RestController
@RequestMapping("api/noteGuid")
@Controller
public class NoteGuidController extends BaseController {

	@Autowired
	private NoteGuidKeyService noteGuidKeyService;
	private JSONObject resultMap;

	@Autowired
	private IRedisService redisService;

	/**
	 * @param request
	 * @param response
	 * @param body
	 */
	@RequestMapping(value = "set")
	public void set(HttpServletRequest request, HttpServletResponse response) {
		List<String> ss = new ArrayList<String>();
		ss.add("aaaallkjbalkasdf|888888");
//		ss.add("aaaallkjbalkasdf|999999");
		redisService.setList("mxbj", ss);
		System.out.println("1");
	}

	/**
	 * @param request
	 * @param response
	 * @param body
	 */
	@RequestMapping(value = "getRedis")
	public void getRedis(HttpServletRequest request, HttpServletResponse response) {
		List<String> ss = new ArrayList<String>();

		ss = redisService.getList("mxbj", String.class);

		for (String s : ss) {
			System.err.println(s);
		}

		System.out.println("1");
	}

	@RequestMapping(value = "upload")
	public void upload(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		resultMap = new JSONObject();
		try {
			Map<String, Object> map = noteGuidKeyService.uploadCheckParam(body);
			List<NoteGuidKey> s = noteGuidKeyService.listByCondition(map);

			if (s != null && s.size() > 0) {
				NoteGuidKey bean = s.get(0);
				bean.setGuidKeys(map.get("guidKeys").toString());
				noteGuidKeyService.update(bean);
			} else {
				NoteGuidKey bean = new NoteGuidKey();
				bean.setUserId(map.get("userId").toString());
				bean.setEverNoteId(map.get("everNoteId").toString());
				bean.setUserPlatformCode(map.get("userPlatformCode").toString());
				bean.setGuidKeys(map.get("guidKeys").toString());
				noteGuidKeyService.save(bean);
			}
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		} catch (IOException e) {
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
			e.printStackTrace();
		} catch (BusinessException e) {
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
			e.printStackTrace();
		}
		printJson(response, resultMap);
		// return resultMap.toString();
	}

	@RequestMapping(value = "get")
	public void get(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		resultMap = new JSONObject();
		try {
			Map<String, Object> map = noteGuidKeyService.getCheckParam(body);
			List<NoteGuidKey> s = noteGuidKeyService.listByCondition(map);

			if (s != null && s.size() > 0) {
				NoteGuidKey bean = s.get(0);

				resultMap.put("userId", bean.getUserId());
				resultMap.put("userPlatformCode", bean.getUserPlatformCode());
				resultMap.put("everNoteId", bean.getEverNoteId());
				resultMap.put("guidKeys", bean.getGuidKeys().toString());
			}
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		} catch (IOException e) {
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, "IO异常");
			e.printStackTrace();
		} catch (BusinessException e) {
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
			e.printStackTrace();
		}
		printJson(response, resultMap);
		// return resultMap.toString();
	}

}
