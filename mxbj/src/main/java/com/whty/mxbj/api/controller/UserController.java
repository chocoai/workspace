package com.whty.mxbj.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.whty.mxbj.api.model.User;
import com.whty.mxbj.api.service.BasePropertyService;
import com.whty.mxbj.api.service.UserService;
import com.whty.mxbj.base.controller.BaseController;
import com.whty.mxbj.base.exception.BusinessException;
import com.whty.mxbj.common.constants.ResultConstants;
import com.whty.mxbj.common.constants.SysConstants;
import com.whty.mxbj.common.utils.CommonFunction;
import com.whty.mxbj.common.utils.HttpUtils;
import com.whty.mxbj.common.utils.SysConfigUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//@RestController
@Controller
@RequestMapping("api/user")
public class UserController extends BaseController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private BasePropertyService basePopertyService;

	private Map<String, Object> resultMap;

	@RequestMapping(value = "searchPhoneNumber")
	public void searchPhoneNumber(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		resultMap = new HashMap<String, Object>();
		Map<String, Object> params = null;
		try {
			params = userService.searchPhoneNumberCheckParam(body);
			String phoneNumber = params.get("phoneNumber").toString();
			
			User user = userService.searchPhoneNumber(params);

			if (user == null) {//去教育云找用户
				String aamTyUrl = SysConfigUtils.getStrValue("huijiaoyun_url");
				Map<String, Object> ssmap = new HashMap<String, Object>();
				ssmap.put("account", phoneNumber);
				String aamTyUrlAnswer = HttpUtils.getInstance().httpPost(aamTyUrl, JSONObject.fromObject(ssmap).toString());
				String aamTyUrlResult = JSONObject.fromObject(aamTyUrlAnswer).optString("result");
				if ("000000".equals(aamTyUrlResult)) {// 一个平台，直接登录
					resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
					resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_USER_RE);
					printJson(response, resultMap);
					return ;
				} else if ("301000".equals(aamTyUrlResult)) {// 多个平台，让用户选择平台
					resultMap.put(ResultConstants.RESULT, SysConstants.MULTI_USER_PLATFORM_CODE);
					resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MULTI_USER_PLATFORM_MESSAGE);
					JSONArray dataArrJson = JSONObject.fromObject(aamTyUrlAnswer).optJSONArray("data");
					resultMap.put("data", dataArrJson);
					printJson(response, resultMap);
					return ;
				} else if ("301001".equals(aamTyUrlResult)) {// 不属于任何平台
					resultMap.put(ResultConstants.RESULT, SysConstants.USER_NULL_CODE);
					resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_USER_NULL);
					printJson(response, resultMap);
					return ;

				}
				resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
				resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_USER_NULL);
			} else {
				resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
				resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_USER_RE);
			}
		} catch (Exception e) {
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
			e.printStackTrace();
		}
		printJson(response, resultMap);
	}

	@RequestMapping(value = "setUserConfig", method = RequestMethod.POST)
	public void setUserConfig(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		resultMap = new HashMap<String, Object>();
		Map<String, Object> params = null;

		try {
			params = userService.setUserConfigCheckParam(body);
			userService.setUserConfig(params);

			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
			e.printStackTrace();
		}
		printJson(response, resultMap);
		// return JSONObject.fromObject(resultMap).toString();
	}

	@RequestMapping(value = "getUserConfig", method = RequestMethod.POST)
	public void getUserConfig(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		resultMap = new HashMap<String, Object>();
		Map<String, Object> params = null;

		try {
			params = userService.getUserConfigCheckParam(body);
			resultMap = userService.getUserConfig(params);

			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
			e.printStackTrace();
		}
		printJson(response, resultMap);
		// return JSONObject.fromObject(resultMap).toString();
	}

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public void test(HttpServletRequest request, HttpServletResponse response) {
		String aamURL = basePopertyService.getPropertyValue("login_url", "888888");
		System.out.println(aamURL);
	}

	@RequestMapping(value = "getInfo", method = RequestMethod.POST)
	public void getInfo(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		resultMap = new HashMap<String, Object>();
		Map<String, String> params = null;

		try {
			params = getInfoCheckParam(body);
			resultMap = userService.userInfo(params);

			resultMap.put("notesVersion", SysConstants.NOTES_VERSION);
		} catch (Exception e) {
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
			e.printStackTrace();
		}
		printJson(response, resultMap);
		// return JSONObject.fromObject(resultMap).toString();

	}

	private Map<String, String> getInfoCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}

		Map<String, String> para = new HashMap<String, String>();
		para.put("userId", reqJson.getString("userId"));
		para.put("deviceId", reqJson.getString("deviceId"));
		para.put("deviceType", reqJson.getString("deviceType"));
		para.put("userPlatformCode", reqJson.getString("userPlatformCode"));
		para.put("loginPlatformCode", reqJson.getString("loginPlatformCode"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public void login2(HttpServletRequest request,HttpServletResponse response,@RequestBody String body){
		resultMap = new HashMap<String, Object>();
		Map<String, String> params = null;
		try {
			params = login2CheckParam(body);
			resultMap = userService.login2(params);
			resultMap.put("notesVersion", SysConstants.NOTES_VERSION);
		} catch (Exception e) {
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		printJson(response, resultMap);
	}
	
	private Map<String, String> login2CheckParam(String body) throws BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}

		logger.info(body);
		Map<String, String> para = new HashMap<String, String>();
		para.put("userAccount", reqJson.getString("userAccount"));
		para.put("pwd", reqJson.getString("pwd"));

		para.put("deviceType", reqJson.getString("deviceType"));

		//para.put("loginPlatformCode", reqJson.getString("loginPlatformCode"));

		// 必填字段检查
		CommonFunction.checkParam(para);
		
		para.put("deviceId", reqJson.optString("deviceId"));
		para.put("loginPlatformCode", reqJson.optString("loginPlatformCode"));
		return para;
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param response
	 * @param body
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		resultMap = new HashMap<String, Object>();
		Map<String, String> params = null;
		try {
			params = loginCheckParam(body);
			resultMap = userService.login(params);
			resultMap.put("notesVersion", SysConstants.NOTES_VERSION);
		} catch (Exception e) {
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
			e.printStackTrace();
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		printJson(response, resultMap);
	}

	private Map<String, String> loginCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}

		logger.info(body);
		Map<String, String> para = new HashMap<String, String>();
		para.put("userAccount", reqJson.getString("userAccount"));
		para.put("loginType", reqJson.getString("loginType"));
		para.put("pwd", reqJson.getString("pwd"));

		para.put("deviceType", reqJson.getString("deviceType"));

		para.put("loginPlatformCode", reqJson.getString("loginPlatformCode"));

		// 必填字段检查
		CommonFunction.checkParam(para);
		para.put("deviceId", reqJson.getString("deviceId"));

		return para;
	}

	@RequestMapping(value = "rePwd2", method = RequestMethod.POST)
	public void rePwd2(HttpServletRequest request,HttpServletResponse response,@RequestBody String body){
		Map<String, String> params = null;
		resultMap = new HashMap<String, Object>();
		try {
			params = rePwd2CheckParam(body);
			resultMap = userService.rePwd2(params);
		} catch (Exception e) {
			String fullStackTrace = ResultConstants.FAIL_MESSAGE;
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, fullStackTrace);
			e.printStackTrace();
		}
		printJson(response, resultMap);
	}
	
	private Map<String, String> rePwd2CheckParam(String body) throws IOException, BusinessException{
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}

		Map<String, String> para = new HashMap<String, String>();
		para.put("phoneNumber", reqJson.getString("phoneNumber"));
		para.put("verifyCode", reqJson.getString("verifyCode"));
		para.put("newPwd", reqJson.getString("newPwd"));
		para.put("platformCode", reqJson.getString("platformCode"));
		
		// 必填字段检查
		CommonFunction.checkParam(para);
		// para.put("userId", reqJson.getString("userId"));
		return para;
	}

	/**
	 * 忘记密码,重置密码
	 * 
	 * @param request
	 * @param response
	 * @param body
	 */
	@RequestMapping(value = "rePwd", method = RequestMethod.POST)
	public void rePwd(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		Map<String, String> params = null;
		resultMap = new HashMap<String, Object>();
		try {
			params = rePwdCheckParam(body);
			resultMap = userService.rePwd(params);
		} catch (Exception e) {
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
			e.printStackTrace();
		}
		printJson(response, resultMap);
		// return JSONObject.fromObject(resultMap).toString();
	}

	private Map<String, String> rePwdCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}

		Map<String, String> para = new HashMap<String, String>();
		para.put("phoneNumber", reqJson.getString("phoneNumber"));
		para.put("verifyCode", reqJson.getString("verifyCode"));
		para.put("newPwd", reqJson.getString("newPwd"));

		// 必填字段检查
		CommonFunction.checkParam(para);
		para.put("source", reqJson.getString("source"));
		para.put("platformCode", reqJson.getString("platformCode"));
		// para.put("userId", reqJson.getString("userId"));
		return para;
	}

	/**
	 * 密码更改
	 * 
	 * @param request
	 * @param response
	 * @param body
	 */
	@RequestMapping(value = "modifyPwd", method = RequestMethod.POST)
	public void modifyPwd(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		Map<String, String> params = null;
		resultMap = new HashMap<String, Object>();
		try {
			params = modifyPwdCheckParam(body);
			resultMap = userService.modifyPwd(params);
		} catch (Exception e) {
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
			e.printStackTrace();
		}
		printJson(response, resultMap);
		// return JSONObject.fromObject(resultMap).toString();
	}

	private Map<String, String> modifyPwdCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}

		Map<String, String> para = new HashMap<String, String>();
		// para.put("phoneNumber", reqJson.getString("phoneNumber"));

		para.put("userId", reqJson.getString("userId"));
		para.put("userPlatformCode", reqJson.getString("userPlatformCode"));
		para.put("oldPwd", reqJson.getString("oldPwd"));
		para.put("newPwd", reqJson.getString("newPwd"));

		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	/**
	 * 邮箱修改
	 * 
	 * @param request
	 * @param response
	 * @param body
	 */
	@RequestMapping(value = "modifyEmail", method = RequestMethod.POST)
	public void modifyEmail(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		Map<String, String> params = null;
		resultMap = new HashMap<String, Object>();
		try {
			params = modifyEmailCheckParam(body);
			resultMap = userService.modifyEmail(params);
		} catch (Exception e) {
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
			e.printStackTrace();
		}
		printJson(response, resultMap);
		// return JSONObject.fromObject(resultMap).toString();
	}

	private Map<String, String> modifyEmailCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}

		Map<String, String> para = new HashMap<String, String>();
		para.put("userId", reqJson.getString("userId"));
		para.put("email", reqJson.getString("email"));
		para.put("userPlatformCode", reqJson.getString("userPlatformCode"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}


	/**
	 * 忘记密码获取验证码
	 * @param request
	 * @param response
	 * @param body
	 */
	@RequestMapping(value = "getVerifyCode2", method = RequestMethod.POST)
	public void getVerifyCode2(HttpServletRequest request,HttpServletResponse response,@RequestBody String body){
		Map<String, String> params = null;
		resultMap = new HashMap<String, Object>();
		try {
			params = getVerifyCodeCheckParam2(body);
			resultMap = userService.getVerifyCode2(params);
		} catch (Exception e) {
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
			e.printStackTrace();
		}
		printJson(response, resultMap);
	}
	
	private Map<String, String> getVerifyCodeCheckParam2(String body) throws IOException, BusinessException{
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}

		Map<String, String> para = new HashMap<String, String>();
		para.put("phoneNumber", reqJson.getString("phoneNumber"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		para.put("platformCode", reqJson.optString("platformCode"));
		return para;
	}

	/**
	 * 注册获取验证码
	 * 
	 * @param request
	 * @param response
	 * @param body
	 */
	@RequestMapping(value = "getVerifyCode", method = RequestMethod.POST)
	public void getVerifyCode(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		Map<String, String> params = null;
		resultMap = new HashMap<String, Object>();
		try {
			params = getVerifyCodeCheckParam(body);
			resultMap = userService.getVerifyCode(params);
		} catch (Exception e) {
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
			e.printStackTrace();
		}
		printJson(response, resultMap);
		// return JSONObject.fromObject(resultMap).toString();
	}

	private Map<String, String> getVerifyCodeCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}

		Map<String, String> para = new HashMap<String, String>();
		para.put("phoneNumber", reqJson.getString("phoneNumber"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		para.put("platformCode", reqJson.optString("platformCode"));
		return para;
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public void register(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
		Map<String, String> params = null;
		resultMap = new HashMap<String, Object>();
		try {
			params = registerCheckParam(body);
			resultMap = userService.register(params);
		} catch (Exception e) {
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
			e.printStackTrace();
		}
		printJson(response, resultMap);
		// return JSONObject.fromObject(resultMap).toString();
	}

	private Map<String, String> registerCheckParam(String body) throws IOException, BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}

		Map<String, String> para = new HashMap<String, String>();
		para.put("phoneNumber", reqJson.getString("phoneNumber"));
		para.put("verifyCode", reqJson.getString("verifyCode"));
		para.put("pwd", reqJson.getString("pwd"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

}
