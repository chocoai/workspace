package com.whty.mxbj.api.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeoutException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.whty.apigateway.security.login.Constant;
import com.whty.apigateway.security.login.EncryptUtils;
import com.whty.mxbj.api.dao.UserDao;
import com.whty.mxbj.api.model.User;
import com.whty.mxbj.api.model.UserConfig;
import com.whty.mxbj.api.model.UserPen;
import com.whty.mxbj.api.service.BasePropertyService;
import com.whty.mxbj.api.service.NoteService;
import com.whty.mxbj.api.service.UserService;
import com.whty.mxbj.base.exception.BusinessException;
import com.whty.mxbj.base.service.BaseService;
import com.whty.mxbj.common.constants.ResultConstants;
import com.whty.mxbj.common.constants.SysConstants;
import com.whty.mxbj.common.utils.CommonFunction;
import com.whty.mxbj.common.utils.GUIDGenerator;
import com.whty.mxbj.common.utils.HttpUtils;
import com.whty.mxbj.common.utils.SysConfigUtils;
import com.whty.mxbj.common.utils.TimeUtils;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component("userService")
public class UserServiceImpl extends BaseService implements UserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MemcachedClient memcachedClient;

	@Autowired
	private UserDao userDao;

	@Autowired
	private BasePropertyService basePropertyService;

	@Autowired
	private NoteService noteService;

	@Override
	public Map<String, Object> login(Map<String, String> params) {
		Map<String, Object> resultMap = null;
		String loginType = params.get("loginType");

		if ("1".equals(loginType)) {
			// 慧教云登录
			resultMap = loginFromHuijiaoyun(params);
		} else if ("2".equals(loginType)) {
			// 手机登录
			resultMap = loginByPhoneNumber(params);
		}

		logger.info(resultMap.toString());
		return resultMap;
	}

	private Map<String, Object> loginEduYun(String phoneNumber, String encryPwd, String deviceId, String deviceType,
			String url, String loginPlatformCode, String aamtyUserPlatformCode) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Map<String, String> reqMap = new HashMap<String, String>();

		EncryptUtils eu = EncryptUtils.getInstance();

//		System.out.println(loginPlatformCode);
		if ("33".equals(loginPlatformCode.substring(0, 2))) {
			encryPwd = eu.decode(encryPwd, Constant.USER_SERCRETKEY);
			// encryPwd = MD5Util.getMD5(encryPwd);
		}
//		System.out.println();
		reqMap.put("account", phoneNumber);
		reqMap.put("password", encryPwd);
		reqMap.put("portaltype", "2");

		String answer = HttpUtils.getInstance().httpPost(url, JSONObject.fromObject(reqMap).toString());
		String result = JSONObject.fromObject(answer).getString("result");
		logger.info("login_huijiaoyun_result: " + result);

		if ("301001".equals(result)) {// 用户不存在
			resultMap.put(ResultConstants.RESULT, SysConstants.USER_NOT_EXIST);
			resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_USER_NOT_EXIST);
			return resultMap;
		}

		if("301003".equals(result)){
			resultMap.put(ResultConstants.RESULT, "301003");
			resultMap.put(ResultConstants.RESULT_MSG, "密码错误");
			return resultMap;
		}
		
		if (!("000000".equals(result))) {
			resultMap.put(ResultConstants.RESULT, SysConstants.LOGIN_FROM_HUIJIAOYUN_FAIL);
			resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_LOGIN_FROM_HUIJIAOYUN_FAIL);
			return resultMap;
		}
		// 教育云登录成功
		JSONObject userInfoObject = JSONObject.fromObject(answer).optJSONObject("userinfo");
		String personid = userInfoObject.optString("personid");
		// String userPlatformCode = userInfoObject.optString("platformCode");

		String userPlatformCode = aamtyUserPlatformCode;

		String userType = userInfoObject.optString("usertype");
		String email = userInfoObject.optString("email");
		String name = userInfoObject.optString("name");
		String account = userInfoObject.optString("account");

		if (null == email)
			email = "";

		Map<String, Object> userParam = new HashMap<String, Object>();
		userParam.put("userId", personid);
		userParam.put("userPlatformCode", userPlatformCode);

		noteService.shareEdu(personid, userPlatformCode);

		User user2 = userDao.loadUserByUserIdAndPlatform(userParam);
		if (null == user2) {
			// 密码是慧教云的密码，不能保存
			User u = new User();
			u.setUserId(personid);
			u.setAccountType("1"); // 1.代表慧教云帐号
			Date date = new Date();
			u.setCreateTime(date);
			u.setLastLoginTime(date);
			u.setUserType(userType);
			u.setLoginCount(1);
			u.setDeviceId(deviceId);
			u.setDeviceType(deviceType);
			u.setUserName(name);
			u.setEmail(email);
			u.setUserPlatformCode(userPlatformCode);
			u.setLoginPlatformCode(loginPlatformCode);
			userDao.save(u);

			resultMap.put("userPlatformCode", userPlatformCode);
			resultMap.put("userType", userType);
			resultMap.put("penMac", "");
			resultMap.put("email", email);
			resultMap.put("userId", personid);
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
			return resultMap;
		} else {
			Date date = new Date();
			String oldDeviceId = user2.getDeviceId();
			logger.info("oldDeviceId:" + oldDeviceId + " deviceId:" + deviceId);

			user2.setLoginCount(user2.getLoginCount() + 1);
			user2.setUpdateTime(date);
			user2.setLastLoginTime(date);
			user2.setUserName(name);

			user2.setAccount(account);

			// 这两个字段来自慧教云
			// 从数据库中取
			email = user2.getEmail();

			user2.setUserType(userType);
			// 这三个来自请求参数
			user2.setLoginPlatformCode(loginPlatformCode);
			user2.setDeviceId(deviceId);
			user2.setDeviceType(deviceType);

			userDao.update(user2);

			Map<String, Object> userPenParam = new HashMap<String, Object>();
			userPenParam.put("userId", user2.getUserId());
			userPenParam.put("userPlatformCode", user2.getUserPlatformCode());

			UserPen userPen = userDao.findUserPenByUserIdAndPlatform(userPenParam);
			if (null == userPen) {
				resultMap.put("penMac", "");
			} else {
				String mac = userPen.getMac();
				resultMap.put("penMac", null == mac ? "" : mac);
			}

			String userEmail = user2.getEmail();
			resultMap.put("email", null == userEmail ? "" : email);

			String type = user2.getUserType();
			resultMap.put("userType", null == type ? "" : type);

			resultMap.put("userId", user2.getUserId());
			resultMap.put("userPlatformCode", user2.getUserPlatformCode());
			if (oldDeviceId != null) {
				if (!oldDeviceId.equals(deviceId)) {
					resultMap.put(ResultConstants.RESULT, ResultConstants.DEVLCEID_CHANGE_CODE);
					resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.DEVICEID_CHANGE_MESSAGE);
					return resultMap;
				}
			}
			resultMap.put("userType", userType);
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
			return resultMap;
		}
	}

	private Map<String, Object> loginByPhoneNumber(Map<String, String> params) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String phoneNumber = params.get("userAccount");
		String encryPwd = params.get("pwd");
		String deviceId = params.get("deviceId");

		if (deviceId == null)
			deviceId = "";

		String deviceType = params.get("deviceType");
		User user = userDao.findUserByPhoneNumber(phoneNumber);
		String loginPlatformCode = params.get("loginPlatformCode");
		if (null == user) {
			// 未存在用户，则在教育云上找用户
			if (loginPlatformCode.equals(SysConfigUtils.getStrValue("mxbj_userPlatformCode"))) {
				// 查找用户平台
				String aamTyUrl = SysConfigUtils.getStrValue("huijiaoyun_url");
				Map<String, Object> ssmap = new HashMap<String, Object>();
				ssmap.put("account", phoneNumber);
				String aamTyUrlAnswer = HttpUtils.getInstance().httpPost(aamTyUrl,
						JSONObject.fromObject(ssmap).toString());
				String aamTyUrlResult = JSONObject.fromObject(aamTyUrlAnswer).optString("result");
				if ("000000".equals(aamTyUrlResult)) {// 一个平台，直接登录
					JSONObject dataJson = JSONObject.fromObject(aamTyUrlAnswer).optJSONObject("data");
					String loginUrl = dataJson.optString("loginUrl");
					String aamtyUserPlatformCode = dataJson.optString("platformCode");
					resultMap = loginEduYun(phoneNumber, encryPwd, deviceId, deviceType, loginUrl, loginPlatformCode,
							aamtyUserPlatformCode);
					return resultMap;
				} else if ("301000".equals(aamTyUrlResult)) {// 多个平台，让用户选择平台
					resultMap.put(ResultConstants.RESULT, SysConstants.MULTI_USER_PLATFORM_CODE);
					resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MULTI_USER_PLATFORM_MESSAGE);
					return resultMap;
				} else if ("040001".equals(aamTyUrlResult)) {
					resultMap.put(ResultConstants.RESULT, SysConstants.MULTI_USER_PLATFORM_CODE);
					resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MULTI_USER_PLATFORM_MESSAGE);
					return resultMap;
				} else if ("301001".equals(aamTyUrlResult)) {
					resultMap.put(ResultConstants.RESULT, SysConstants.USER_NOT_EXIST);
					resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_USER_NOT_EXIST);
					return resultMap;
				} else {
					resultMap.put(ResultConstants.RESULT, SysConstants.USER_NOT_EXIST);
					resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_LOGIN_FROM_HUIJIAOYUN_FAIL);
					return resultMap;
				}

			} else {
				// 登录指定平台
				String loginURL = basePropertyService.getPropertyValue("login_url", loginPlatformCode);
				resultMap = loginEduYun(phoneNumber, encryPwd, deviceId, deviceType, loginURL, loginPlatformCode,
						loginURL);
				return resultMap;
			}

		}

		EncryptUtils eu = EncryptUtils.getInstance();
		String md5pwd = eu.decode(encryPwd, Constant.USER_SERCRETKEY);
		String userPwd = user.getPwd();

		if (!md5pwd.equals(userPwd)) {
			resultMap.put(ResultConstants.RESULT, SysConstants.WRONG_PASSWARD);
			resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_WRONG_PASSWARD);
			return resultMap;
		}

		Date date = new Date();
		String oldDeviceId = user.getDeviceId();
		logger.info("oldDeviceId:" + oldDeviceId + " deviceId:" + deviceId);

		user.setUpdateTime(date);
		user.setLoginCount(user.getLoginCount() + 1);
		user.setLastLoginTime(date);

		// 这三个来自请求参数
		user.setDeviceId(deviceId);
		user.setDeviceType(deviceType);
		user.setLoginPlatformCode(params.get("loginPlatformCode"));

		userDao.update(user);

		Map<String, Object> userPenParam = new HashMap<String, Object>();
		userPenParam.put("userId", user.getUserId());
		userPenParam.put("userPlatformCode", user.getUserPlatformCode());

		UserPen userPen = userDao.findUserPenByUserIdAndPlatform(userPenParam);
		if (null == userPen) {
			resultMap.put("penMac", "");
		} else {
			String mac = userPen.getMac();
			resultMap.put("penMac", null == mac ? "" : mac);
		}

		String email = user.getEmail();
		resultMap.put("email", null == email ? "" : email);

		String userType = user.getUserType();
		resultMap.put("userType", null == userType ? "" : userType);

		resultMap.put("userId", user.getUserId());
		resultMap.put("userPlatformCode", user.getUserPlatformCode());
		if (oldDeviceId != null) {
			if (!oldDeviceId.equals(deviceId)) {
				resultMap.put(ResultConstants.RESULT, ResultConstants.DEVLCEID_CHANGE_CODE);
				resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.DEVICEID_CHANGE_MESSAGE);
				return resultMap;
			}
		}

		resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
		resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		return resultMap;
	}

	public Map<String, Object> findUser(User localUser, String deviceId, String loginPlatformCode, String deviceType) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Date date = new Date();
		String oldDeviceId = localUser.getDeviceId();
		logger.info("oldDeviceId:" + oldDeviceId + " deviceId:" + deviceId);

		localUser.setLoginCount(localUser.getLoginCount() + 1);
		localUser.setUpdateTime(date);
		localUser.setLastLoginTime(date);
		// localUser.setUserName(name);

		// localUser.setAccount(account);

		// 这两个字段来自慧教云
		// user.setEmail(email);
		// 从数据库中取
		// email = localUser.getEmail();

		// localUser.setUserType(userType);
		// 这三个来自请求参数
		localUser.setLoginPlatformCode(loginPlatformCode);
		localUser.setDeviceId(deviceId);
		localUser.setDeviceType(deviceType);

		userDao.update(localUser);

		Map<String, Object> userPenParam = new HashMap<String, Object>();
		userPenParam.put("userId", localUser.getUserId());
		userPenParam.put("userPlatformCode", localUser.getUserPlatformCode());

		UserPen userPen = userDao.findUserPenByUserIdAndPlatform(userPenParam);
		if (null == userPen) {
			resultMap.put("penMac", "");
		} else {
			String mac = userPen.getMac();
			resultMap.put("penMac", null == mac ? "" : mac);
		}

		// String userEmail = localUser.getEmail();
		// resultMap.put("email", null == userEmail ? "" : email);

		String type = localUser.getUserType();
		resultMap.put("userType", null == type ? "" : type);

		resultMap.put("userId", localUser.getUserId());
		resultMap.put("userPlatformCode", localUser.getUserPlatformCode());
		if (oldDeviceId != null) {
			if (!oldDeviceId.equals(deviceId)) {
				resultMap.put(ResultConstants.RESULT, ResultConstants.DEVLCEID_CHANGE_CODE);
				resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.DEVICEID_CHANGE_MESSAGE);
				return resultMap;
			}
		}

		resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
		resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		return resultMap;

	}

	private Map<String, Object> loginFromHuijiaoyun(Map<String, String> params) {
		String userAccount = params.get("userAccount");
		String entrypPWD = params.get("pwd");
		String deviceId = params.get("deviceId");
		String deviceType = params.get("deviceType");
		String loginPlatformCode = params.get("loginPlatformCode");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (loginPlatformCode == null) {
			resultMap.put(ResultConstants.RESULT, SysConstants.FILE_PLATFORM_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_PLATFORM_CODE);
			return resultMap;
		}

		// String aamURL = SysConfigUtils.getStrValue("aamURL");

		Map<String, String> reqMap = new HashMap<String, String>();

		EncryptUtils eu = EncryptUtils.getInstance();
		if ("33".equals(loginPlatformCode.substring(0, 2))) {
			entrypPWD = eu.decode(entrypPWD, Constant.USER_SERCRETKEY);
			// entrypPWD = MD5Util.getMD5(entrypPWD);
		}

		reqMap.put("account", userAccount);
		reqMap.put("password", entrypPWD);
		reqMap.put("portaltype", "1");

		String loginURL = basePropertyService.getPropertyValue("login_url", loginPlatformCode);
		String answer = HttpUtils.getInstance().httpPost(loginURL, JSONObject.fromObject(reqMap).toString());
		logger.info(answer);
		String result = JSONObject.fromObject(answer).getString("result");
		logger.info("login_huijiaoyun_result: " + result);
		// 慧教云登录失败
		if (!("000000".equals(result))) {
			if ("301001".equals(result)) {
				resultMap.put(ResultConstants.RESULT, SysConstants.LOGIN_FROM_HUIJIAOYUN_FAIL);
				resultMap.put(ResultConstants.RESULT_MSG, "该用户名已被注销，详情请联系平台管理员");
				return resultMap;
			}
			if("301003".equals(result)){
				resultMap.put(ResultConstants.RESULT, "301003");
				resultMap.put(ResultConstants.RESULT_MSG, "密码错误");
				return resultMap;
			}
			
			resultMap.put(ResultConstants.RESULT, SysConstants.LOGIN_FROM_HUIJIAOYUN_FAIL);
			resultMap.put(ResultConstants.RESULT_MSG, "登录异常，请联系平台管理员");
			return resultMap;
		}
		// 慧教云登录成功
		JSONObject userInfoObject = JSONObject.fromObject(answer).optJSONObject("userinfo");

		String personid = userInfoObject.optString("personid");
		// String userPlatformCode = userInfoObject.optString("platformCode");

		String userPlatformCode = loginPlatformCode;

		String userType = userInfoObject.optString("usertype");
		String email = userInfoObject.optString("email");
		String name = userInfoObject.optString("name");
		String account = userInfoObject.optString("account");
		if (null == email)
			email = "";
		Map<String, Object> userParam = new HashMap<String, Object>();
		userParam.put("userId", personid);
		userParam.put("userPlatformCode", userPlatformCode);

		noteService.shareEdu(personid, userPlatformCode);

		User user = userDao.loadUserByUserIdAndPlatform(userParam);

		if (null == user) {
			// 密码是慧教云的密码，不能保存
			User u = new User();
			u.setUserId(personid);
			u.setAccountType("1"); // 1.代表慧教云帐号
			Date date = new Date();
			u.setCreateTime(date);
			u.setLastLoginTime(date);
			u.setUserType(userType);
			u.setLoginCount(1);
			u.setDeviceId(deviceId);
			u.setDeviceType(deviceType);
			u.setUserName(name);
			u.setEmail(email);
			u.setUserPlatformCode(userPlatformCode);
			u.setLoginPlatformCode(loginPlatformCode);
			u.setAccount(account);
			userDao.save(u);

			resultMap.put("userPlatformCode", userPlatformCode);
			resultMap.put("userType", userType);
			resultMap.put("penMac", "");
			resultMap.put("email", email);
			resultMap.put("userId", personid);
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
			return resultMap;
		} else {

			Date date = new Date();
			String oldDeviceId = user.getDeviceId();
			logger.info("oldDeviceId:" + oldDeviceId + " deviceId:" + deviceId);

			user.setLoginCount(user.getLoginCount() + 1);
			user.setUpdateTime(date);
			user.setLastLoginTime(date);
			user.setUserName(name);

			user.setAccount(account);

			// 这两个字段来自慧教云
			// user.setEmail(email);
			// 从数据库中取
			email = user.getEmail();

			user.setUserType(userType);
			// 这三个来自请求参数
			user.setLoginPlatformCode(loginPlatformCode);
			user.setDeviceId(deviceId);
			user.setDeviceType(deviceType);

			userDao.update(user);

			Map<String, Object> userPenParam = new HashMap<String, Object>();
			userPenParam.put("userId", user.getUserId());
			userPenParam.put("userPlatformCode", user.getUserPlatformCode());

			UserPen userPen = userDao.findUserPenByUserIdAndPlatform(userPenParam);
			if (null == userPen) {
				resultMap.put("penMac", "");
			} else {
				String mac = userPen.getMac();
				resultMap.put("penMac", null == mac ? "" : mac);
			}

			String userEmail = user.getEmail();
			resultMap.put("email", null == userEmail ? "" : email);

			String type = user.getUserType();
			resultMap.put("userType", null == type ? "" : type);

			resultMap.put("userId", user.getUserId());
			resultMap.put("userPlatformCode", user.getUserPlatformCode());
			if (oldDeviceId != null) {
				if (!oldDeviceId.equals(deviceId)) {
					resultMap.put(ResultConstants.RESULT, ResultConstants.DEVLCEID_CHANGE_CODE);
					resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.DEVICEID_CHANGE_MESSAGE);
					return resultMap;
				}
			}
			resultMap.put("userType", userType);
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
			return resultMap;
		}

	}

	@Override
	public Map<String, Object> modifyPwd(Map<String, String> params) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		// String phoneNumber = params.get("phoneNumber");
		String userId = params.get("userId");
		String userPlatformCode = params.get("userPlatformCode");
		String oldPwd = params.get("oldPwd");
		String newPwd = params.get("newPwd");

		if (oldPwd.equals(newPwd)) {
			resultMap.put(ResultConstants.RESULT, SysConstants.TWO_PWD_EQ);
			resultMap.put(ResultConstants.RESULT_MSG, SysConstants.TWO_PWD_EQ_MESSAGE);
			return resultMap;
		}

		Map<String, Object> p = new HashMap<String, Object>();
		p.put("userId", userId);
		p.put("userPlatformCode", userPlatformCode);

		User user = userDao.loadUserByUserIdAndPlatform(p);

		if (null == user) {
			resultMap.put(ResultConstants.RESULT, SysConstants.USER_NOT_EXIST);
			resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_USER_NOT_EXIST);
			return resultMap;
		}

		EncryptUtils eu = EncryptUtils.getInstance();
		String md5pwd = eu.decode(oldPwd, Constant.USER_SERCRETKEY);
		String userPwd = user.getPwd();

		if (!md5pwd.equals(userPwd)) {
			resultMap.put(ResultConstants.RESULT, SysConstants.WRONG_PASSWARD);
			resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_WRONG_PASSWARD);
			return resultMap;
		}
		// 旧密码输入正确，更新mysql记录
		Date date = new Date();
		user.setUpdateTime(date);

		user.setPwd(eu.decode(newPwd, Constant.USER_SERCRETKEY));
		userDao.update(user);

		resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
		resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		return resultMap;
	}

	@Override
	public Map<String, Object> modifyEmail(Map<String, String> params) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String userId = params.get("userId");
		String email = params.get("email");
		String userPlatformCode = params.get("userPlatformCode");
		Map<String, Object> userParam = new HashMap<String, Object>();
		userParam.put("userId", userId);
		userParam.put("userPlatformCode", userPlatformCode);

		User user = userDao.loadUserByUserIdAndPlatform(userParam);
		if (null == user) {
			resultMap.put(ResultConstants.RESULT, SysConstants.USER_NOT_EXIST);
			resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_USER_NOT_EXIST);
			return resultMap;
		}
		Date date = new Date();
		user.setUpdateTime(date);
		user.setEmail(email);
		userDao.update(user);
		resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
		resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		return resultMap;
	}

	@Override
	public Map<String, Object> userInfo(Map<String, String> params) {
		String userId = params.get("userId");
		String userPlatformCode = params.get("userPlatformCode");
		String loginPlatformCode = params.get("loginPlatformCode");
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Map<String, Object> userParam = new HashMap<String, Object>();
		userParam.put("userId", userId);
		userParam.put("userPlatformCode", userPlatformCode);
		User user = userDao.loadUserByUserIdAndPlatform(userParam);

		String email = null;
		String userType = null;
		if (user == null) {
			String aamURL = basePropertyService.getPropertyValue("aam_url", loginPlatformCode);
			String aamUserInfoURL = aamURL + "user/" + userId;
			try {
				String answer = HttpUtils.getInstance().httpGet(aamUserInfoURL);
				String result = JSONObject.fromObject(answer).getString("result");

				if (!("000000".equals(result))) {
					resultMap.put(ResultConstants.RESULT, SysConstants.LOGIN_FROM_HUIJIAOYUN_FAIL);
					resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_LOGIN_FROM_HUIJIAOYUN_FAIL);
					return resultMap;
				}

				JSONObject userInfoObject = JSONObject.fromObject(answer).optJSONObject("userinfo");
				userType = userInfoObject.optString("usertype");
				email = userInfoObject.optString("email");

				User u = new User();
				u.setUserId(userId);
				u.setAccountType("1"); // 1.代表慧教云登录
				Date date = new Date();
				u.setCreateTime(date);
				u.setLastLoginTime(date);
				u.setUpdateTime(date);
				u.setUserType(userType);
				u.setLoginCount(1);
				u.setEmail(email);
				u.setDeviceId(params.get("deviceId"));
				u.setDeviceType(params.get("deviceType"));
				u.setLoginPlatformCode(loginPlatformCode);
				u.setUserPlatformCode(userPlatformCode);
				userDao.save(u);
			} catch (Exception e) {
				e.printStackTrace();
				resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
				resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
				return resultMap;
			}
		} else {
			Date date = new Date();
			user.setLoginCount(user.getLoginCount() + 1);
			user.setDeviceId(params.get("deviceId"));
			user.setDeviceType(params.get("deviceType"));
			user.setUpdateTime(date);
			user.setLastLoginTime(date);
			userDao.update(user);

			userType = user.getUserType();
		}

		Map<String, Object> userPenParam = new HashMap<String, Object>();
		userPenParam.put("userId", userId);
		userPenParam.put("userPlatformCode", userPlatformCode);
		UserPen userPen = userDao.findUserPenByUserIdAndPlatform(userPenParam);

		if (null == userPen) {
			resultMap.put("penMac", "");
		} else {
			String mac = userPen.getMac();
			resultMap.put("penMac", null == mac ? "" : mac);
		}

		resultMap.put("email", null == email ? "" : email);
		resultMap.put("userId", userId);
		resultMap.put("userPlatformCode", userPlatformCode);
		resultMap.put("userType", userType);

		resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
		resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		return resultMap;
	}

	public Map<String, Object> getMnoteVerifyCode(String phoneNumber)
			throws TimeoutException, InterruptedException, MemcachedException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		User user = userDao.findUserByPhoneNumber(phoneNumber);

		if (user == null) {
			String aamTyUrl = SysConfigUtils.getStrValue("huijiaoyun_url");
			Map<String, Object> ssmap = new HashMap<String, Object>();
			ssmap.put("account", phoneNumber);
			String aamTyUrlAnswer = HttpUtils.getInstance().httpPost(aamTyUrl, JSONObject.fromObject(ssmap).toString());
			String aamTyUrlResult = JSONObject.fromObject(aamTyUrlAnswer).optString("result");
			if ("000000".equals(aamTyUrlResult)) {// 一个平台，直接登录
				JSONObject dataJson = JSONObject.fromObject(aamTyUrlAnswer).optJSONObject("data");
				String aamtyUserPlatformCode = dataJson.optString("platformCode");
				return getEduYunVerifyCode2(phoneNumber, aamtyUserPlatformCode);
			} else if ("301000".equals(aamTyUrlResult)) {// 多个平台，让用户选择平台
				resultMap.put(ResultConstants.RESULT, SysConstants.MULTI_USER_PLATFORM_CODE);
				resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MULTI_USER_PLATFORM_MESSAGE);
				JSONArray dataArrJson = JSONObject.fromObject(aamTyUrlAnswer).optJSONArray("data");
				resultMap.put("data", dataArrJson);
				return resultMap;
			}
		}

		String key = new StringBuilder("verifyCode").append(SysConstants.CACHE_KEY_SEPERATER).append(phoneNumber)
				.toString();
		String verifyCode = memcachedClient.get(key);
		if (null != verifyCode) {

			// String shortMessageUrl =
			// SysConfigUtils.getStrValue("short_memmage_url");

			String shortMessageUrl = "http://192.168.50.145:10016/smsService/sms/sendSms";

			Map<String, Object> param = new HashMap<String, Object>();

			Map<String, Object> paramBody = new HashMap<String, Object>();
			// param.put("phoneNumber", phoneNumber);
			// param.put("content", "您好，您的墨香笔记验证码是:" + verifyCode + ",有效时间5分钟");

			param.put("action", "sendAliSms");

			paramBody.put("recNum", phoneNumber);
			paramBody.put("signName", "慧教云");
			paramBody.put("templateCode", "SMS_91880093");
			paramBody.put("paramString", "{\"actcode\":\"" + verifyCode + "\"}");

			param.put("body", paramBody);

			String answer = HttpUtils.getInstance().httpPost(shortMessageUrl, JSONObject.fromObject(param).toString());

			logger.info(answer);

			saveVerifyCodeParams(phoneNumber, verifyCode);

			String resultCode = JSONObject.fromObject(answer).getString("resultCode");
			logger.info("getVerifyCode_resultCode: " + resultCode);

			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);

			resultMap.put("verifyCode", verifyCode);
			resultMap.put("platformCode", SysConfigUtils.getStrValue("mxbj_userPlatformCode"));

			// resultMap.put(ResultConstants.RESULT,
			// SysConstants.VERIFY_CODE_HAS_SENDED);
			// resultMap.put(ResultConstants.RESULT_MSG,
			// SysConstants.MSG_VERIFY_CODE_HAS_SENDED);
			return resultMap;
		}

		verifyCode = generateRandomVerifyCode();

		// 保证1min之内，只能发送一次短信验证码, 特别注意单位为 second
		memcachedClient.set(key, 300, verifyCode);

		// String shortMessageUrl =
		// SysConfigUtils.getStrValue("short_memmage_url");
		// Map<String, Object> param = new HashMap<String, Object>();
		//
		// param.put("phoneNumber", phoneNumber);
		// param.put("content", "您好，您的墨香笔记验证码是:" + verifyCode + ",有效时间5分钟");

		String shortMessageUrl = "http://192.168.50.145:10016/smsService/sms/sendSms";

		Map<String, Object> param = new HashMap<String, Object>();

		Map<String, Object> paramBody = new HashMap<String, Object>();
		// param.put("phoneNumber", phoneNumber);
		// param.put("content", "您好，您的墨香笔记验证码是:" + verifyCode + ",有效时间5分钟");

		param.put("action", "sendAliSms");

		paramBody.put("recNum", phoneNumber);
		paramBody.put("signName", "慧教云");
		paramBody.put("templateCode", "SMS_91880093");
		paramBody.put("paramString", "{\"actcode\":\"" + verifyCode + "\"}");

		param.put("body", paramBody);

		boolean flag = false;

		try {
			for (int i = 0; i < 3; i++) {
				String answer = HttpUtils.getInstance().httpPost(shortMessageUrl,
						JSONObject.fromObject(param).toString());
				logger.info(answer);

				saveVerifyCodeParams(phoneNumber, verifyCode);
				String resultCode = JSONObject.fromObject(answer).getString("resCode");
				logger.info("getVerifyCode_resultCode: " + resultCode);
				if ("000000".equals(resultCode)) {
					flag = true;
					break;
				}
			}
		} catch (Exception e) {
			resultMap.put(ResultConstants.RESULT, SysConstants.VERIFY_CODE_SEND_EXCEPTION);
			resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_VERIFY_CODE_SEND_EXCEPTION);
			return resultMap;
		}

		if (!flag) {
			resultMap.put(ResultConstants.RESULT, SysConstants.VERIFY_CODE_SEND_FAIL);
			resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_VERIFY_CODE_SEND_FAIL);
			return resultMap;
		}

		resultMap.put("verifyCode", verifyCode);
		resultMap.put("platformCode", SysConfigUtils.getStrValue("mxbj_userPlatformCode"));
		resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
		resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		return resultMap;
	}

	public Map<String, Object> getEduYunVerifyCode(String phoneNumber, String platformCode) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String aamURL = basePropertyService.getPropertyValue("aam_url", platformCode);

		JSONObject phoneNumberJson = new JSONObject();
		phoneNumberJson.put("phone", phoneNumber);
		String queryPhoneActCodeAnswer = HttpUtils.getInstance().httpPost(aamURL + "/queryPhoneActCode",
				phoneNumberJson.toString());

		String result = JSONObject.fromObject(queryPhoneActCodeAnswer).getString("result");

		if ("000000".equals(result)) {
			JSONObject userPhoneInfoJsonObject = JSONObject.fromObject(queryPhoneActCodeAnswer)
					.optJSONObject("userPhoneInfo");
			String actCode = userPhoneInfoJsonObject.optString("actCode");

			// String shortMessageUrl =
			// SysConfigUtils.getStrValue("short_memmage_url");
			// Map<String, Object> param = new HashMap<String, Object>();
			//
			// param.put("phoneNumber", phoneNumber);
			// param.put("content", "您好，您的墨香笔记验证码是:" + actCode);

			String shortMessageUrl = "http://192.168.50.145:10016/smsService/sms/sendSms";

			Map<String, Object> param = new HashMap<String, Object>();

			Map<String, Object> paramBody = new HashMap<String, Object>();
			// param.put("phoneNumber", phoneNumber);
			// param.put("content", "您好，您的墨香笔记验证码是:" + verifyCode + ",有效时间5分钟");

			param.put("action", "sendAliSms");

			paramBody.put("recNum", phoneNumber);
			paramBody.put("signName", "慧教云");
			paramBody.put("templateCode", "SMS_91880093");
			paramBody.put("paramString", "{\"actcode\":\"" + actCode + "\"}");

			param.put("body", paramBody);
			boolean flag = false;

			try {
				// for (int i = 0; i < 3; i++) {
				String answer = HttpUtils.getInstance().httpPost(shortMessageUrl,
						JSONObject.fromObject(param).toString());

				saveVerifyCodeParams(phoneNumber, actCode);

				logger.info(answer);
				String resultCode = JSONObject.fromObject(answer).getString("resCode");
				logger.info("getVerifyCode_resultCode: " + resultCode);
				if ("000000".equals(resultCode)) {
					flag = true;
					// break;
				}
				if (!flag) {
					resultMap.put(ResultConstants.RESULT, SysConstants.VERIFY_CODE_SEND_FAIL);
					resultMap.put(ResultConstants.RESULT_MSG, JSONObject.fromObject(answer).getString("resultDesc"));
					return resultMap;
				}
				// }
			} catch (Exception e) {
				resultMap.put(ResultConstants.RESULT, SysConstants.VERIFY_CODE_SEND_EXCEPTION);
				resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_VERIFY_CODE_SEND_EXCEPTION);
				return resultMap;
			}

		}
		resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
		resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		return resultMap;
	}

	@Override
	public Map<String, Object> getVerifyCode(Map<String, String> params)
			throws TimeoutException, InterruptedException, MemcachedException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String phoneNumber = params.get("phoneNumber");
		String platformCode = params.get("platformCode");

		if (platformCode == null || "".equals(platformCode))
			platformCode = "9999999";

		if (SysConfigUtils.getStrValue("mxbj_userPlatformCode").equals(platformCode)) {// 墨香笔记的生产校验码流程
			resultMap = getMnoteVerifyCode(phoneNumber);
		} else {// 教育云生成校验码流程
			resultMap = getEduYunVerifyCode(phoneNumber, platformCode);
		}
		Map<String, Object> verifyCodeParams = new HashMap<String, Object>();
		verifyCodeParams.put("dateTime", TimeUtils.date2String(new Date(), TimeUtils.DAY_FORMAT_1));
		verifyCodeParams.put("phoneNumber", params.get("phoneNumber"));
		Integer count = userDao.getVerifyCodeCount(verifyCodeParams);
		int syCount = 7 - count;
		resultMap.put("suCount", syCount);
		return resultMap;
	}

	/**
	 * 生成6位数字验证码
	 * 
	 * @return String
	 */
	private String generateRandomVerifyCode() {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		random.setSeed(random.nextLong() + System.currentTimeMillis());
		for (int i = 0; i < 6; i++) {
			sb.append(random.nextInt(10));
		}
		return sb.toString();
	}

	/*
	 * 
	 * { "result" : "301001", "resultMsg" : "账号不存在！" }
	 * 
	 * { "result" : "000000", "userinfo" : { "personid" :
	 * "84196494509242a390089b2be8028f33", "account" : "15807172437" }, "desc" :
	 * "success", "data" : { "platformCode" : "420100", "platformName" :
	 * "武汉教育资源公共服务平台", "platformUrl" :
	 * "http://www.wuhaneduyun.cn:10013/aamif/rest", "loginUrl" :
	 * "http://www.wuhaneduyun.cn:10013/aamif/rest/commonAccount/login",
	 * "encryption" : "normal", "syncTime" : "2017-03-17 16:35:50" } }
	 */
	public static void main(String[] args) {
		String ss = "http://116.211.105.38:13022/aamty/allAccount/queryAccountPlatformInfo";
		// String ss
		// ="http://116.211.105.45:13022/aamty/allAccount/queryAccountPlatformInfo";
		Map<String, Object> ssmap = new HashMap<String, Object>();
		// ssmap.put("account", "hh141011");
		ssmap.put("account", "songwenping");
		// ssmap.put("account", "zuozhiwei");
		String result2 = HttpUtils.getInstance().httpPost(ss, JSONObject.fromObject(ssmap).toString());
		System.out.println(result2);
	}

	@Override
	public Map<String, Object> register(Map<String, String> params) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 先判断是否已经在本系统注册过,就不到慧教云去查找了
		String phoneNumber = params.get("phoneNumber");
		User userBean = userDao.findUserByPhoneNumber(phoneNumber);

		if (userBean != null) {
			resultMap.put(ResultConstants.RESULT, SysConstants.FILE_USER_REGISTER_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_USER_REGISTER);
			return resultMap;
		}

		String huijiaoyunUrl = SysConfigUtils.getStrValue("huijiaoyun_url");
		resultMap.put("account", phoneNumber);

		String answer = HttpUtils.getInstance().httpPost(huijiaoyunUrl, JSONObject.fromObject(resultMap).toString());
		String result = JSONObject.fromObject(answer).getString("result");
		if ("000000".equals(result) || "301000".equals(result)) {//
			// 先判断慧教育云是否有帐号
			resultMap.put(ResultConstants.RESULT, SysConstants.USER_EXIST_IN_HUIJIAOYUN);
			resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_USER_EXIST_IN_HUIJIAOYUN);
			return resultMap;
		} else if ("301001".equals(result)) {
			String verifyCode = params.get("verifyCode");
			String pwd = params.get("pwd");
			String key = new StringBuilder("verifyCode").append(SysConstants.CACHE_KEY_SEPERATER).append(phoneNumber)
					.toString();
			String memcachedVerifyCode = memcachedClient.get(key);
			// 1min内只能发送一次短信验证码
			if (null == memcachedVerifyCode || !verifyCode.equals(memcachedVerifyCode)) {
				resultMap.put(ResultConstants.RESULT, SysConstants.VERIFY_CODE_CHECK_FAIL);
				resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_VERIFY_CODE_CHECK_FAIL);
				return resultMap;
			}

			// 验证成功，保存数据到数据库中
			User user = new User();

			String userId = GUIDGenerator.getUUID32();
			user.setUserId(userId);

			// mxbj用户平台编码
			String mxbjUserPlatformCode = SysConfigUtils.getStrValue("mxbj_userPlatformCode");
			user.setUserPlatformCode(mxbjUserPlatformCode);

			EncryptUtils eu = EncryptUtils.getInstance();
			String md5pwd = eu.decode(pwd, Constant.USER_SERCRETKEY);
			user.setPwd(md5pwd);

			user.setAccountType("0"); // 0代表mxbj账号
			user.setPhoneNumber(phoneNumber);
			user.setCreateTime(new Date());
			user.setLoginCount(0);
			userDao.save(user);

			resultMap.put("userId", userId);
			resultMap.put("userPlatformCode", mxbjUserPlatformCode);
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
			return resultMap;
		}
		// 第三种状态暂且不明了
		return resultMap;
	}

	@Override
	public Map<String, Object> setUserConfigCheckParam(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		logger.info(body);
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("userId", reqJson.get("userId"));
		para.put("userPlatformCode", reqJson.get("userPlatformCode"));
		para.put("isSynErrorNote", reqJson.get("isSynErrorNote"));
		para.put("isSynCloud", reqJson.get("isSynCloud"));

		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	@Override
	public void setUserConfig(Map<String, Object> params) {
		UserConfig param = userDao.getUserConfig(params);
		if (param == null) {
			params.put("createTime", new Date());
			userDao.saveUserConfig(params);
		} else {
			params.put("updateTime", new Date());
			userDao.updateUserConfig(params);
		}

	}

	@Override
	public Map<String, Object> getUserConfigCheckParam(String body) throws IOException, BusinessException {
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
	public Map<String, Object> getUserConfig(Map<String, Object> params) {
		Map<String, Object> param = new HashMap<String, Object>();
		UserConfig resultMap = userDao.getUserConfig(params);
		if (resultMap == null) {
			param.put("userId", params.get("userId"));
			param.put("userPlatformCode", params.get("userPlatformCode"));
			param.put("isSynErrorNote", false);
			param.put("isSynCloud", false);
			param.put("createTime", new Date());
			userDao.saveUserConfig(param);
			param.remove("createTime");

		} else {
			param.put("isSynErrorNote", resultMap.getIsSynErrorNote());
			param.put("isSynCloud", resultMap.getIsSynCloud());

			if (resultMap.getSynErrorNoteLastTime() != null) {
				param.put("synErrorNoteLastTime", TimeUtils.date2String(resultMap.getSynErrorNoteLastTime(),
						TimeUtils.STR_DATETIME_PATTERN_LONG));
			} else {
				param.put("synErrorNoteLastTime", "");
			}

			if (resultMap.getSynCloudLastTime() != null) {
				param.put("synCloudLastTime",
						TimeUtils.date2String(resultMap.getSynCloudLastTime(), TimeUtils.STR_DATETIME_PATTERN_LONG));
			} else {
				param.put("synCloudLastTime", "");
			}

		}
		List<Map<String, Object>> platformNamelst = userDao.getSynCloudSchool(params);

		if (platformNamelst != null && platformNamelst.size() > 0) {
			Map<String, Object> p = platformNamelst.get(0);
			if (p != null && p.get("platform_name") != null) {
				String platformName = platformNamelst.get(0).get("platform_name").toString();
				param.put("platformName", platformName);
			} else {
				param.put("platformName", "");
			}
		} else {
			param.put("platformName", "");
		}
		return param;
	}

	public Map<String, Object> rePwdByMnote(String phoneNumber, String verifyCode, String newPwd) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		// User user = userDao.findUserByPhoneNumber(phoneNumber);

		// if(user==null){
		// resultMap.put(ResultConstants.RESULT,
		// SysConstants.VERIFY_CODE_CHECK_FAIL);
		// resultMap.put(ResultConstants.RESULT_MSG,
		// SysConstants.MSG_VERIFY_CODE_CHECK_FAIL);
		// return resultMap;
		// }

		// String pwd = params.get("pwd");
		String key = new StringBuilder("verifyCode").append(SysConstants.CACHE_KEY_SEPERATER).append(phoneNumber)
				.toString();

		try {
			logger.info(key);
			String memcachedVerifyCode = memcachedClient.get(key);
			logger.info(memcachedVerifyCode);
			// memcachedVerifyCode = "1";
			logger.info("2");
			if (memcachedVerifyCode == null) {
				resultMap.put(ResultConstants.RESULT, SysConstants.VERIFY_CODE_CHECK_FAIL);
				resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_VERIFY_CODE_CHECK_FAIL);
				return resultMap;
			}
			if (!verifyCode.equals(memcachedVerifyCode)) {
				resultMap.put(ResultConstants.RESULT, SysConstants.VERIFY_CODE_CHECK_FAIL);
				resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_VERIFY_CODE_CHECK_FAIL);
				return resultMap;
			}
			User user = userDao.findUserByPhoneNumber(phoneNumber);

			String aamTyUrl = SysConfigUtils.getStrValue("huijiaoyun_url");
			Map<String, Object> ssmap = new HashMap<String, Object>();
			ssmap.put("account", phoneNumber);
			String aamTyUrlAnswer = HttpUtils.getInstance().httpPost(aamTyUrl, JSONObject.fromObject(ssmap).toString());
			String aamTyUrlResult = JSONObject.fromObject(aamTyUrlAnswer).optString("result");

			if ("000000".equals(aamTyUrlResult) || "301000".equals(aamTyUrlResult)) {
				resultMap.put(ResultConstants.RESULT, SysConstants.USER_THRID_ACCOUNT);
				resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_USER_THRID_ACCOUNT);
				return resultMap;
			}

			if (null == user) {
				resultMap.put(ResultConstants.RESULT, SysConstants.USER_NOT_EXIST);
				resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_USER_NOT_EXIST);
				return resultMap;
			}
			// logger.info(newPwd);
			String encryPwd = newPwd;
			// logger.info(encryPwd);
			EncryptUtils eu = EncryptUtils.getInstance();
			String md5pwd = eu.decode(encryPwd, Constant.USER_SERCRETKEY);
			// String userPwd = user.getPwd();
			logger.info(md5pwd);
			user.setPwd(md5pwd);
			user.setUpdateTime(new Date());
			userDao.update(user);
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
		}
		return resultMap;

	}

	public Map<String, Object> rePwdByEduYun(String phoneNumber, String verifyCode, String newPwd,
			String platformCode) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String aamUrl = basePropertyService.getPropertyValue("aam_url", platformCode);

		JSONObject checkPhoneJson = new JSONObject();
		checkPhoneJson.put("phone", phoneNumber);
		checkPhoneJson.put("actCode", verifyCode);
		String checkPhoneAnswer = HttpUtils.getInstance().httpPost(aamUrl + "/checkPhoneActCode",
				checkPhoneJson.toString());

		String result = JSONObject.fromObject(checkPhoneAnswer).optString("result");
		if (!"000000".equals(result)) {
			resultMap.put(ResultConstants.RESULT, result);
			resultMap.put(ResultConstants.RESULT_MSG, JSONObject.fromObject(checkPhoneAnswer).optString("desc"));
			return resultMap;
		}

		JSONObject resetPasswordJson = new JSONObject();
		resetPasswordJson.put("account", phoneNumber);
		resetPasswordJson.put("actCode", verifyCode);
		resetPasswordJson.put("newpassword", newPwd);
		resetPasswordJson.put("type", "1");

		String resetPasswordAnswer = HttpUtils.getInstance().httpPost(aamUrl + "/person/account/actCode/resetpassword",
				resetPasswordJson.toString());
		String resetPasswordResult = JSONObject.fromObject(resetPasswordAnswer).optString("result");
		String resetPasswordDesc = JSONObject.fromObject(resetPasswordAnswer).optString("desc");
		resultMap.put(ResultConstants.RESULT, resetPasswordResult);
		resultMap.put(ResultConstants.RESULT_MSG, resetPasswordDesc);
		return resultMap;
	}

	@Override
	public Map<String, Object> rePwd(Map<String, String> params) {
		Map<String, Object> resultMap = null;
		String phoneNumber = params.get("phoneNumber").toLowerCase();
		String verifyCode = params.get("verifyCode");
		String newPwd = params.get("newPwd");
		String source = params.get("source");

		if (source == null || "".equals(source))
			source = "1";

		if ("1".equals(source)) {
			resultMap = rePwdByMnote(phoneNumber, verifyCode, newPwd);
		}

		if ("2".equals(source)) {
			String platformCode = params.get("platformCode");
			if (platformCode == null || "".equals(platformCode.trim())) {
				resultMap.put(ResultConstants.RESULT, SysConstants.FILE_PLATFORM_CODE);
				resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_PLATFORM_CODE);
				return resultMap;
			}

			resultMap = rePwdByEduYun(phoneNumber, verifyCode, newPwd, platformCode);
		}

		if (resultMap == null) {
			resultMap = new HashMap<String, Object>();
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, "重置密码失败");
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> searchPhoneNumberCheckParam(String body) throws IOException, BusinessException {
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("phoneNumber", reqJson.get("phoneNumber"));
		// 必填字段检查
		CommonFunction.checkParam(para);
		return para;
	}

	@Override
	public User searchPhoneNumber(Map<String, Object> params) {
		return userDao.findUserByPhoneNumber(params.get("phoneNumber").toString());
	}

	@Override
	public Map<String, Object> getVerifyCode2(Map<String, String> params)
			throws TimeoutException, InterruptedException, MemcachedException {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		String phoneNumber = params.get("phoneNumber");
		String platformCode = params.get("platformCode");

		if (platformCode == null || "".equals(platformCode))
			platformCode = "9999999";

		if (SysConfigUtils.getStrValue("mxbj_userPlatformCode").equals(platformCode)) {// 墨香笔记的生产校验码流程
			resultMap = getMnoteVerifyCode2(phoneNumber);
		} else {// 教育云生成校验码流程
			resultMap = getEduYunVerifyCode2(phoneNumber, platformCode);
		}

		// Date startTime = TimeUtils.getCurrentStartTime();
		// Date endTime = TimeUtils.getCurrentEndTime();
		Map<String, Object> verifyCodeParams = new HashMap<String, Object>();
		verifyCodeParams.put("dateTime", TimeUtils.date2String(new Date(), TimeUtils.DAY_FORMAT_1));
		verifyCodeParams.put("phoneNumber", params.get("phoneNumber"));
		Integer count = userDao.getVerifyCodeCount(verifyCodeParams);
		int syCount = 7 - count;
		resultMap.put("suCount", syCount);
		return resultMap;
	}

	private Map<String, Object> getEduYunVerifyCode2(String phoneNumber, String platformCode) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		String aamURL = basePropertyService.getPropertyValue("aam_url", platformCode);

		JSONObject phoneNumberJson = new JSONObject();
		phoneNumberJson.put("phone", phoneNumber);
		String queryPhoneActCodeAnswer = HttpUtils.getInstance().httpPost(aamURL + "/queryPhoneActCode",
				phoneNumberJson.toString());

		String result = JSONObject.fromObject(queryPhoneActCodeAnswer).getString("result");

		if ("000000".equals(result)) {
			JSONObject userPhoneInfoJsonObject = JSONObject.fromObject(queryPhoneActCodeAnswer)
					.optJSONObject("userPhoneInfo");
			String actCode = userPhoneInfoJsonObject.optString("actCode");

//			String shortMessageUrl = SysConfigUtils.getStrValue("short_memmage_url");
//			Map<String, Object> param = new HashMap<String, Object>();
//
//			param.put("phoneNumber", phoneNumber);
//			param.put("content", "您好，您的墨香笔记验证码是:" + actCode);

			String shortMessageUrl = "http://192.168.50.145:10016/smsService/sms/sendSms";

			Map<String, Object> param = new HashMap<String, Object>();

			Map<String, Object> paramBody = new HashMap<String, Object>();
			// param.put("phoneNumber", phoneNumber);
			// param.put("content", "您好，您的墨香笔记验证码是:" + verifyCode + ",有效时间5分钟");

			param.put("action", "sendAliSms");

			paramBody.put("recNum", phoneNumber);
			paramBody.put("signName", "慧教云");
			paramBody.put("templateCode", "SMS_91880093");
			paramBody.put("paramString", "{\"actcode\":\"" + actCode + "\"}");

			param.put("body", paramBody);
			
			boolean flag = false;

			try {
				// for (int i = 0; i < 3; i++) {
				String answer = HttpUtils.getInstance().httpPost(shortMessageUrl,
						JSONObject.fromObject(param).toString());

				saveVerifyCodeParams(phoneNumber, actCode);

				logger.info(answer);
				String resultCode = JSONObject.fromObject(answer).getString("resCode");
				logger.info("getVerifyCode_resultCode: " + resultCode);
				if ("000000".equals(resultCode)) {
					flag = true;
					// break;
				}
				if (!flag) {
					resultMap.put(ResultConstants.RESULT, SysConstants.VERIFY_CODE_SEND_FAIL);
					resultMap.put(ResultConstants.RESULT_MSG, JSONObject.fromObject(answer).getString("resultDesc"));
					return resultMap;
				}
				// }
			} catch (Exception e) {
				resultMap.put(ResultConstants.RESULT, SysConstants.VERIFY_CODE_SEND_EXCEPTION);
				resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_VERIFY_CODE_SEND_EXCEPTION);
				return resultMap;
			}

			resultMap.put("verifyCode", actCode);
			resultMap.put("platformCode", platformCode);
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		} else {
			resultMap.put(ResultConstants.RESULT, SysConstants.VERIFY_CODE_SEND_FAIL);
			resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_VERIFY_CODE_SEND_FAIL);
		}
		return resultMap;
	}

	private void saveVerifyCodeParams(String phoneNumber, String verifyCode) {
		Map<String, Object> saveVerifyCodeParams = new HashMap<String, Object>();
		saveVerifyCodeParams.put("id", GUIDGenerator.getUUID32());
		saveVerifyCodeParams.put("phoneNumber", phoneNumber);
		saveVerifyCodeParams.put("verifyCode", verifyCode);
		saveVerifyCodeParams.put("createTime", new Date());
		userDao.saveVerifyCodeLog(saveVerifyCodeParams);
	}

	private Map<String, Object> getMnoteVerifyCode2(String phoneNumber)
			throws TimeoutException, InterruptedException, MemcachedException {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		User user = userDao.findUserByPhoneNumber(phoneNumber);

		if (user == null) {
			// 去查询用户所属平台
			String aamTyUrl = SysConfigUtils.getStrValue("huijiaoyun_url");
			Map<String, Object> ssmap = new HashMap<String, Object>();
			ssmap.put("account", phoneNumber);
			String aamTyUrlAnswer = HttpUtils.getInstance().httpPost(aamTyUrl, JSONObject.fromObject(ssmap).toString());
			String aamTyUrlResult = JSONObject.fromObject(aamTyUrlAnswer).optString("result");
			if ("000000".equals(aamTyUrlResult)) {// 一个平台，直接登录
				JSONObject dataJson = JSONObject.fromObject(aamTyUrlAnswer).optJSONObject("data");
				String aamtyUserPlatformCode = dataJson.optString("platformCode");
				return getEduYunVerifyCode2(phoneNumber, aamtyUserPlatformCode);
			} else if ("301000".equals(aamTyUrlResult)) {// 多个平台，让用户选择平台
				resultMap.put(ResultConstants.RESULT, SysConstants.MULTI_USER_PLATFORM_CODE);
				resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MULTI_USER_PLATFORM_MESSAGE);
				JSONArray dataArrJson = JSONObject.fromObject(aamTyUrlAnswer).optJSONArray("data");
				resultMap.put("data", dataArrJson);
				return resultMap;
			} else if ("301001".endsWith(aamTyUrlResult)) {
				resultMap.put(ResultConstants.RESULT, SysConstants.USER_NOT_EXIST);
				resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_USER_NULL);
				JSONArray dataArrJson = JSONObject.fromObject(aamTyUrlAnswer).optJSONArray("data");
				resultMap.put("data", dataArrJson);
				return resultMap;
			}
		}

		String key = new StringBuilder("verifyCode").append(SysConstants.CACHE_KEY_SEPERATER).append(phoneNumber)
				.toString();
		String verifyCode = memcachedClient.get(key);
		if (null != verifyCode) {

//			String shortMessageUrl = SysConfigUtils.getStrValue("short_memmage_url");
//			Map<String, Object> param = new HashMap<String, Object>();
//
//			param.put("phoneNumber", phoneNumber);
//			param.put("content", "您好，您的墨香笔记验证码是:" + verifyCode + ",有效时间5分钟");

			String shortMessageUrl = "http://192.168.50.145:10016/smsService/sms/sendSms";

			Map<String, Object> param = new HashMap<String, Object>();

			Map<String, Object> paramBody = new HashMap<String, Object>();
			// param.put("phoneNumber", phoneNumber);
			// param.put("content", "您好，您的墨香笔记验证码是:" + verifyCode + ",有效时间5分钟");

			param.put("action", "sendAliSms");

			paramBody.put("recNum", phoneNumber);
			paramBody.put("signName", "慧教云");
			paramBody.put("templateCode", "SMS_91880093");
			paramBody.put("paramString", "{\"actcode\":\"" + verifyCode + "\"}");

			param.put("body", paramBody);
			String answer = HttpUtils.getInstance().httpPost(shortMessageUrl, JSONObject.fromObject(param).toString());
			String resultCode = JSONObject.fromObject(answer).getString("resultCode");

			saveVerifyCodeParams(phoneNumber, verifyCode);

			logger.info("getVerifyCode_resultCode: " + resultCode);

			resultMap.put("verifyCode", verifyCode);
			resultMap.put("platformCode", SysConfigUtils.getStrValue("mxbj_userPlatformCode"));
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);

			// resultMap.put(ResultConstants.RESULT,
			// SysConstants.VERIFY_CODE_HAS_SENDED);
			// resultMap.put(ResultConstants.RESULT_MSG,
			// SysConstants.MSG_VERIFY_CODE_HAS_SENDED);
			return resultMap;
		}

		verifyCode = generateRandomVerifyCode();

		// 保证1min之内，只能发送一次短信验证码, 特别注意单位为 second
		memcachedClient.set(key, 300, verifyCode);

//		String shortMessageUrl = SysConfigUtils.getStrValue("short_memmage_url");
//		Map<String, Object> param = new HashMap<String, Object>();
//
//		param.put("phoneNumber", phoneNumber);
//		param.put("content", "您好，您的墨香笔记验证码是:" + verifyCode + ",有效时间5分钟");

		String shortMessageUrl = "http://192.168.50.145:10016/smsService/sms/sendSms";

		Map<String, Object> param = new HashMap<String, Object>();

		Map<String, Object> paramBody = new HashMap<String, Object>();
		// param.put("phoneNumber", phoneNumber);
		// param.put("content", "您好，您的墨香笔记验证码是:" + verifyCode + ",有效时间5分钟");

		param.put("action", "sendAliSms");

		paramBody.put("recNum", phoneNumber);
		paramBody.put("signName", "慧教云");
		paramBody.put("templateCode", "SMS_91880093");
		paramBody.put("paramString", "{\"actcode\":\"" + verifyCode + "\"}");

		param.put("body", paramBody);
		
		boolean flag = false;

		try {
			for (int i = 0; i < 3; i++) {
				String answer = HttpUtils.getInstance().httpPost(shortMessageUrl,
						JSONObject.fromObject(param).toString());

				saveVerifyCodeParams(phoneNumber, verifyCode);

				logger.info(answer);
				String resultCode = JSONObject.fromObject(answer).getString("resCode");
				logger.info("getVerifyCode_resultCode: " + resultCode);
				if ("000000".equals(resultCode)) {
					flag = true;
					break;
				}
			}
		} catch (Exception e) {
			resultMap.put(ResultConstants.RESULT, SysConstants.VERIFY_CODE_SEND_EXCEPTION);
			resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_VERIFY_CODE_SEND_EXCEPTION);
			return resultMap;
		}

		if (!flag) {
			resultMap.put(ResultConstants.RESULT, SysConstants.VERIFY_CODE_SEND_FAIL);
			resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_VERIFY_CODE_SEND_FAIL);
			return resultMap;
		}
		resultMap.put("verifyCode", verifyCode);
		resultMap.put("platformCode", SysConfigUtils.getStrValue("mxbj_userPlatformCode"));
		resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
		resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		return resultMap;
	}

	@Override
	public Map<String, Object> rePwd2(Map<String, String> params) {
		Map<String, Object> resultMap = null;
		String phoneNumber = params.get("phoneNumber").toLowerCase();
		String verifyCode = params.get("verifyCode");
		String newPwd = params.get("newPwd");
		String platformCode = params.get("platformCode");

		if (SysConfigUtils.getStrValue("mxbj_userPlatformCode").equals(platformCode)) {
			resultMap = rePwdByMnote2(phoneNumber, verifyCode, newPwd);
		} else {
			resultMap = rePwdByEduYun(phoneNumber, verifyCode, newPwd, platformCode);
		}

		if (resultMap == null) {
			resultMap = new HashMap<String, Object>();
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, "修改密码失败");
		}

		return resultMap;
	}

	private Map<String, Object> rePwdByMnote2(String phoneNumber, String verifyCode, String newPwd) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		// String pwd = params.get("pwd");
		String key = new StringBuilder("verifyCode").append(SysConstants.CACHE_KEY_SEPERATER).append(phoneNumber)
				.toString();

		try {
			logger.info(key);
			String memcachedVerifyCode = memcachedClient.get(key);
			logger.info(memcachedVerifyCode);
			// memcachedVerifyCode = "1";
			logger.info("2");
			if (memcachedVerifyCode == null) {
				resultMap.put(ResultConstants.RESULT, SysConstants.VERIFY_CODE_CHECK_FAIL);
				resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_VERIFY_CODE_CHECK_FAIL);
				return resultMap;
			}
			if (!verifyCode.equals(memcachedVerifyCode)) {
				resultMap.put(ResultConstants.RESULT, SysConstants.VERIFY_CODE_CHECK_FAIL);
				resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_VERIFY_CODE_CHECK_FAIL);
				return resultMap;
			}
			User user = userDao.findUserByPhoneNumber(phoneNumber);

			// String aamTyUrl = SysConfigUtils.getStrValue("huijiaoyun_url");
			// Map<String, Object> ssmap = new HashMap<String, Object>();
			// ssmap.put("account", phoneNumber);
			// String aamTyUrlAnswer =
			// HttpUtils.getInstance().httpPost(aamTyUrl,
			// JSONObject.fromObject(ssmap).toString());
			// String aamTyUrlResult =
			// JSONObject.fromObject(aamTyUrlAnswer).optString("result");
			//
			// if ("000000".equals(aamTyUrlResult) ||
			// "301000".equals(aamTyUrlResult)) {
			// resultMap.put(ResultConstants.RESULT,
			// SysConstants.USER_THRID_ACCOUNT);
			// resultMap.put(ResultConstants.RESULT_MSG,
			// SysConstants.MSG_USER_THRID_ACCOUNT);
			// return resultMap;
			// }

			if (null == user) {
				resultMap.put(ResultConstants.RESULT, SysConstants.USER_NOT_EXIST);
				resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_USER_NOT_EXIST);
				return resultMap;
			}
			logger.info(newPwd);
			String encryPwd = newPwd;
			logger.info(encryPwd);
			EncryptUtils eu = EncryptUtils.getInstance();
			String md5pwd = eu.decode(encryPwd, Constant.USER_SERCRETKEY);
			// String userPwd = user.getPwd();
			logger.info(md5pwd);
			user.setPwd(md5pwd);
			user.setUpdateTime(new Date());
			userDao.update(user);
			resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put(ResultConstants.RESULT, ResultConstants.FAIL_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.FAIL_MESSAGE);
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> login2(Map<String, String> params) {
		String userAccount = params.get("userAccount");
		// String loginType = params.get("loginType");
		String pwd = params.get("pwd");
		String deviceType = params.get("deviceType");
		String deviceId = params.get("deviceId");
		String loginPlatformCode = params.get("loginPlatformCode");
		Map<String, Object> resultMap = new HashMap<String, Object>();

		// 有平台编码的教育云帐号登录流程
		if (loginPlatformCode != null && !"".equals(loginPlatformCode)) {// 如果平台编码为空，并且不等于9999999
																			// 到指定平台登录
			if (!SysConfigUtils.getStrValue("mxbj_userPlatformCode").equals(loginPlatformCode)) {// 登录指定教育云流程
				resultMap = loginFromHuijiaoyun(params);
				return resultMap;
			}
		}

		// ---------墨香笔记登录流程
		User user = userDao.findUserByPhoneNumber(userAccount);

		if (user != null) {
			resultMap = loginByPhoneNumber2(params, user);// 手机登录流程
			return resultMap;
		}

		// 没有平台编码情况的教育云帐号登录流程
		String aamTyUrl = SysConfigUtils.getStrValue("huijiaoyun_url");
		Map<String, Object> ssmap = new HashMap<String, Object>();
		ssmap.put("account", userAccount);
		String aamTyUrlAnswer = HttpUtils.getInstance().httpPost(aamTyUrl, JSONObject.fromObject(ssmap).toString());

		String aamTyUrlResult = JSONObject.fromObject(aamTyUrlAnswer).optString("result");
		if ("000000".equals(aamTyUrlResult)) {// 一个平台，直接登录
			JSONObject dataJson = JSONObject.fromObject(aamTyUrlAnswer).optJSONObject("data");
			String loginUrl = dataJson.optString("loginUrl");
			String aamtyUserPlatformCode = dataJson.optString("platformCode");
			resultMap = loginEduYun(userAccount, pwd, deviceId, deviceType, loginUrl, aamtyUserPlatformCode,
					aamtyUserPlatformCode);
			return resultMap;
		} else if ("301000".equals(aamTyUrlResult)) {// 多个平台，让用户选择平台
			resultMap.put(ResultConstants.RESULT, SysConstants.MULTI_USER_PLATFORM_CODE);
			resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MULTI_USER_PLATFORM_MESSAGE);
			JSONArray dataArrJson = JSONObject.fromObject(aamTyUrlAnswer).optJSONArray("data");
			resultMap.put("data", dataArrJson);// 将平台信息传给终端
			return resultMap;
		} else if ("301001".equals(aamTyUrlResult)) {
			resultMap.put(ResultConstants.RESULT, SysConstants.USER_NOT_EXIST);
			resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_USER_NOT_EXIST);
			return resultMap;
		} else {
			resultMap.put(ResultConstants.RESULT, SysConstants.USER_NOT_EXIST);
			resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_LOGIN_FROM_HUIJIAOYUN_FAIL);
			return resultMap;
		}
	}

	private Map<String, Object> loginByPhoneNumber2(Map<String, String> params, User user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// String phoneNumber = params.get("userAccount");
		String encryPwd = params.get("pwd");
		String deviceId = params.get("deviceId");

		if (deviceId == null)
			deviceId = "";

		String deviceType = params.get("deviceType");
		// User user = userDao.findUserByPhoneNumber(phoneNumber);

		EncryptUtils eu = EncryptUtils.getInstance();
		String md5pwd = eu.decode(encryPwd, Constant.USER_SERCRETKEY);
		String userPwd = user.getPwd();

		if (!md5pwd.equals(userPwd)) {
			resultMap.put(ResultConstants.RESULT, SysConstants.WRONG_PASSWARD);
			resultMap.put(ResultConstants.RESULT_MSG, SysConstants.MSG_WRONG_PASSWARD);
			return resultMap;
		}

		Date date = new Date();
		String oldDeviceId = user.getDeviceId();
		logger.info("oldDeviceId:" + oldDeviceId + " deviceId:" + deviceId);

		user.setUpdateTime(date);
		user.setLoginCount(user.getLoginCount() + 1);
		user.setLastLoginTime(date);

		// 这三个来自请求参数
		user.setDeviceId(deviceId);
		user.setDeviceType(deviceType);
		user.setLoginPlatformCode(params.get("loginPlatformCode"));

		userDao.update(user);

		Map<String, Object> userPenParam = new HashMap<String, Object>();
		userPenParam.put("userId", user.getUserId());
		userPenParam.put("userPlatformCode", user.getUserPlatformCode());

		UserPen userPen = userDao.findUserPenByUserIdAndPlatform(userPenParam);
		if (null == userPen) {
			resultMap.put("penMac", "");
		} else {
			String mac = userPen.getMac();
			resultMap.put("penMac", null == mac ? "" : mac);
		}

		String email = user.getEmail();
		resultMap.put("email", null == email ? "" : email);

		String userType = user.getUserType();
		resultMap.put("userType", null == userType ? "" : userType);

		resultMap.put("userId", user.getUserId());
		resultMap.put("userPlatformCode", user.getUserPlatformCode());
		if (oldDeviceId != null) {
			if (!oldDeviceId.equals(deviceId)) {
				resultMap.put(ResultConstants.RESULT, ResultConstants.DEVLCEID_CHANGE_CODE);
				resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.DEVICEID_CHANGE_MESSAGE);
				return resultMap;
			}
		}

		resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
		resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		return resultMap;
	}

}
