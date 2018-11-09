package com.whty.mxbj.api.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.whty.mxbj.api.model.Upgrade;
import com.whty.mxbj.api.service.UpgradeService;
import com.whty.mxbj.api.service.YnoteService;
import com.whty.mxbj.base.controller.BaseController;
import com.whty.mxbj.common.constants.ResultConstants;
import com.whty.mxbj.common.thread.ThreadPoolUtils;
import com.whty.mxbj.common.utils.HttpUtils;
import com.whty.mxbj.common.utils.SysConfigUtils;

import net.sf.json.JSONObject;

//@RestController
@Controller
@RequestMapping("api/app")
public class AppController extends BaseController {
	private JSONObject resultMap;

	@Autowired
	private UpgradeService upgradeService;

	@Autowired
	private YnoteService ynoteService;

	@RequestMapping(value = "showImg", method = RequestMethod.GET)
	public String showImg(HttpServletRequest request, HttpServletResponse response, Model model) {
		String imgUrl = request.getParameter("img_url");
		model.addAttribute("imgUrl", imgUrl);
		return "showImg";
	}

	@RequestMapping(value = "getYnoteAccess", method = RequestMethod.GET)
	public void getYnoteAccess(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);

		String accessToken = reqJson.optString("accessToken");
		System.out.println(accessToken);
	}

	@RequestMapping(value = "pageYnote", method = RequestMethod.GET)
	public String pageYnote(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("code");
		System.out.println(code);
		return "download2";
	}

	public static void main(String[] args) throws Exception {
		String url = "https://note.youdao.com/oauth/access2?client_id=d1015bd81fc34855fdf3c8e28f6a111e&client_secret=9b90c7dfcc7f2098c62716cc43c4c537&grant_type=authorization_code&redirect_uri=http://mxbj.huijiaoyun.com/mxbj/api/app/getYnoteAccess&code=0c3665a9894b4c7df1f093b989915aae";
		String s = HttpUtils.getInstance().httpGet(url);
		System.out.println(s);
	}

	@RequestMapping(value = "synYnote/{userId}/{userPlatformCode}", method = RequestMethod.GET)
	public void synYnote(HttpServletRequest request, HttpServletResponse response, @PathVariable final String userId,
			@PathVariable final String userPlatformCode) {
		resultMap = new JSONObject();
		String clientId = SysConfigUtils.getStrValue("consumerKey");
		String clientSecret = SysConfigUtils.getStrValue("consumerSecret");
		String grantType = "authorization_code";
		String redirectUri = "http://mxbj.huijiaoyun.com/mxbj/api/app/getYnoteAccess";
		String code = request.getParameter("code");

		logger.info(code);
		String url = "https://note.youdao.com/oauth" + "/access2?client_id=" + clientId + "&client_secret="
				+ clientSecret + "&grant_type=" + grantType + "&redirect_uri=" + redirectUri + "&code=" + code;

		try {
			String s = HttpUtils.getInstance().httpGet(url);

			JSONObject result = JSONObject.fromObject(s);
			logger.info(result.toString());
			final String accessToken = result.optString("accessToken");

			if (accessToken == null || "".equals(accessToken)) {
				printText(response, result.toString());
			}

			ThreadPoolUtils.execute(new Runnable() {
				public void run() {
					ynoteService.synYnote(accessToken, userId, userPlatformCode);
				}
			});

		} catch (Exception e) {
			printText(response, "mnote://youdaoauth error");
			e.printStackTrace();
		}
//		resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
//		resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
//		printJson(response, resultMap);
		printText(response, "mnote://youdaoauth success");
	}

	@RequestMapping(value = "synYnote2", method = RequestMethod.GET)
	public void synYnote(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String userPlatformCode = request.getParameter("userPlatformCode");

		String clientId = "d1015bd81fc34855fdf3c8e28f6a111e";
		String clientSecret = "9b90c7dfcc7f2098c62716cc43c4c537";
		String grantType = "authorization_code";
		String redirectUri = "http://mxbj.huijiaoyun.com/mxbj/api/app/getYnoteAccess";
		String code = request.getParameter("code");

		String url = "https://note.youdao.com/oauth" + "/access2?client_id=" + clientId + "&client_secret="
				+ clientSecret + "&grant_type=" + grantType + "&redirect_uri=" + redirectUri + "&code=" + code;

		try {
			String s = HttpUtils.getInstance().httpGet(url);
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "upgrade")
	public void upgrade(HttpServletRequest request, HttpServletResponse response) {
		resultMap = new JSONObject();
		List<Upgrade> beanList = upgradeService.queryUpgrade(new HashMap<String, Object>());
		Upgrade bean = beanList.get(0);
		if (bean.getStatus().equals("1")) {
			resultMap.put("downLoadUrl", bean.getDownLoadUrl());
			resultMap.put("version", bean.getSoftVersion());
			resultMap.put("description", bean.getDescription());
		}
		resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
		resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		printJson(response, resultMap);
	}
	
	@RequestMapping(value = "padUpgrade")
	public void padUpgrade(HttpServletRequest request, HttpServletResponse response) {
		resultMap = new JSONObject();
		List<Upgrade> beanList = upgradeService.queryPadUpgrade(new HashMap<String, Object>());
		Upgrade bean = beanList.get(0);
		if (bean.getStatus().equals("0")) {
			resultMap.put("downLoadUrl", bean.getDownLoadUrl());
			resultMap.put("version", bean.getSoftVersion());
			resultMap.put("description", bean.getDescription());
		}
		resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
		resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		printJson(response, resultMap);
	}
	
	
//	@RequestMapping(value = "padUpgrade")
//	public void padUpgrade(HttpServletRequest request, HttpServletResponse response) {
//		resultMap = new JSONObject();
//		List<Upgrade> beanList = upgradeService.queryUpgrade(new HashMap<String, Object>());
//		Upgrade bean = beanList.get(0);
//		if (bean.getStatus().equals("1")) {
//			resultMap.put("downLoadUrl", bean.getDownLoadUrl());
//			resultMap.put("version", bean.getSoftVersion());
//			resultMap.put("description", bean.getDescription());
//		}
//		resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
//		resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
//		printJson(response, resultMap);
//	}
	
}
