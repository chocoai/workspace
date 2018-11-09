package com.whty.assis.api.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mchange.v1.util.StringTokenizerUtils;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.whty.assis.api.model.ApiUser;
import com.whty.assis.api.model.HdktEventCount;
import com.whty.assis.api.model.HdktLoginLog;
import com.whty.assis.api.model.HdktTerminalLinkCount;
import com.whty.assis.api.service.ClientUserService;
import com.whty.assis.api.utils.HttpUtils;
import com.whty.assis.base.exception.BusinessException;
import com.whty.assis.demo.controller.SsoftLicenceController;
import com.whty.assis.demo.dao.TaUserDao;
import com.whty.assis.demo.model.Ta_user;
import com.whty.assis.demo.model.WidgetLog;
import com.whty.assis.manage.model.BaseProperty;
import com.whty.assis.manage.service.BasePropertyService;
import com.whty.common.cache.data.CacheDataUtils;
import com.whty.common.cache.data.GetCacheBaseData;
import com.whty.common.mongodb.MongoDBBaseDao;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.Constants;
import com.whty.common.util.GUIDGenerator;
import com.whty.common.util.SysConfig;
import com.whty.common.util.TimeUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class ClientUserServiceImpl implements ClientUserService {

	// private static Logger logger =
	// Logger.getLogger(ClientUserServiceImpl.class);

	private static Logger logger = LoggerFactory.getLogger(ClientUserServiceImpl.class);

	@Autowired
	private TaUserDao taUserDao;

	// @Resource(name = "coursecenterMongoDBBaseDao")
	// private CoursecenterMongoDBBaseDao coursecenterMongoDBBaseDao;

	@Autowired
	private BasePropertyService basePropertyService;

	@Resource(name = "mongoDBBaseDao")
	MongoDBBaseDao mongoDBBaseDao;

	@Override
	public List<WidgetLog> getOp() {
		return taUserDao.getOp();
	}

	/**
	 * 验证并同步用户到管理后台
	 * 
	 * @throws BusinessException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map syncClientUser(ApiUser apiUser, Map respMap) throws BusinessException {
		// 查询用户是否存在
		Map map = new HashMap();
		map.put("user_id", apiUser.getUserId());
		map.put("platform_id", apiUser.getPlatformCode());// 教育云平台编码
		Ta_user ta_user = taUserDao.getUserByParam(map);
		// 判断用户帐号权限状态
		if (null != ta_user && ta_user.getStatus().equals("1")) {
			// 0有效，1禁用
			respMap.put("ueserinfo", null);
			respMap.put("result", Constants.ACCOUNT_IS_DISABLE);
			respMap.put("resultMsg", Constants.MSG_ACCOUNT_IS_DISABLE);
			return respMap;
		}
		// 同步用户
		if (null != ta_user) {
			ta_user = CommonFunction.apiUserToTA_user(apiUser);
			taUserDao.updateUser(ta_user);
		} else {
			ta_user = CommonFunction.apiUserToTA_user(apiUser);
			taUserDao.addUser(ta_user);
		}
		return respMap;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Ta_user getTaUserByParam(Map map) {
		return taUserDao.getUserByParam(map);
	}

	@Override
	public void syncClientUser(ApiUser apiUser, Ta_user ta_user) {
		// 同步用户
		if (null != ta_user) {
			ta_user = CommonFunction.apiUserToTA_user(apiUser);
			taUserDao.updateUser(ta_user);
		} else {
			ta_user = CommonFunction.apiUserToTA_user(apiUser);
			taUserDao.addUser(ta_user);
		}
		// 调用web端接口同步用户信息
		syncUserToWeb(apiUser);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void syncClientUser(Map param) {
		ApiUser apiUser = new ApiUser();
		// 必填字段
		apiUser.setUserId((String) param.get("userId"));
		apiUser.setAccount((String) param.get("account"));
		apiUser.setLoginPlatformCode((String) param.get("loginPlatformCode"));
		apiUser.setPlatformCode((String) param.get("userPlatformCode"));

		// 非必填字段
		apiUser.setAreaId((null == param.get("areaId") || param.get("areaId").toString().trim().equals("null")) ? null
				: (String) param.get("areaId"));
		apiUser.setAreaName((null == param.get("areaName") || param.get("areaName").toString().trim().equals("null"))
				? null : (String) param.get("areaName"));
		apiUser.setOrgId((null == param.get("orgaId") || param.get("orgaId").toString().trim().equals("null")) ? null
				: (String) param.get("orgaId"));
		apiUser.setOrgName((null == param.get("orgaName") || param.get("orgaName").toString().trim().equals("null"))
				? null : (String) param.get("orgaName"));
		apiUser.setRealName((null == param.get("realName") || param.get("realName").toString().trim().equals("null"))
				? null : (String) param.get("realName"));
		apiUser.setEmail((null == param.get("email") || param.get("email").toString().trim().equals("null")) ? null
				: (String) param.get("email"));
		apiUser.setMobilePhone(
				(null == param.get("mobilNumber") || param.get("mobilNumber").toString().trim().equals("null")) ? null
						: (String) param.get("mobilNumber"));
		apiUser.setBirthday((null == param.get("birthday") || param.get("birthday").toString().trim().equals("null"))
				? null : (String) param.get("birthday"));
		apiUser.setGender((null == param.get("gender") || param.get("gender").toString().trim().equals("null")) ? null
				: (String) param.get("gender"));
		apiUser.setUserType((null == param.get("userType") || param.get("userType").toString().trim().equals("null"))
				? null : (String) param.get("userType"));
		apiUser.setIdCardno(
				(null == param.get("idCardNumber") || param.get("idCardNumber").toString().trim().equals("null")) ? null
						: (String) param.get("idCardNumber"));
		apiUser.setProvinceName(
				(null == param.get("provinceName") || param.get("provinceName").toString().trim().equals("null")) ? null
						: (String) param.get("provinceName"));
		apiUser.setCityName((null == param.get("cityName") || param.get("cityName").toString().trim().equals("null"))
				? null : (String) param.get("cityName"));
		apiUser.setAreaName((null == param.get("areaName") || param.get("areaName").toString().trim().equals("null"))
				? null : (String) param.get("areaName"));
		apiUser.setUsessionid(
				(null == param.get("userSessionId") || param.get("userSessionId").toString().trim().equals("null"))
						? null : (String) param.get("userSessionId"));

		// 获取用户信息
		Map map = new HashMap();
		map.put("user_id", apiUser.getUserId());
		map.put("platform_id", apiUser.getPlatformCode());// 教育云用户所属平台编码

		if (apiUser.getPlatformCode() != null) {

			// if ("33".equals(apiUser.getPlatformCode().substring(0, 2))) {
			// String endTime =
			// TimeUtils.date2String(TimeUtils.getCurrentEndTime(),
			// TimeUtils.TIME_FORMAT);
			//
			// Calendar startCal = Calendar.getInstance();
			// startCal.set(Calendar.DATE, startCal.get(Calendar.DATE) - 7);
			// startCal.set(Calendar.HOUR_OF_DAY, 0);
			// startCal.set(Calendar.MINUTE, 0);
			// startCal.set(Calendar.SECOND, 0);
			// startCal.set(Calendar.MILLISECOND, 0);
			//
			// String startTime = TimeUtils.date2String(startCal.getTime(),
			// TimeUtils.TIME_FORMAT);
			//
			// Map<String, Object> userLogingLogParam = new HashMap<String,
			// Object>();
			//
			// userLogingLogParam.put("startTime", startTime);
			// userLogingLogParam.put("endTime", endTime);
			// userLogingLogParam.put("userId", apiUser.getUserId());
			// userLogingLogParam.put("userPlatformCode",
			// apiUser.getPlatformCode());
			//
			// String jiFen =
			// SysConfig.getStrValue("zj.jifen.shouke.action.login");
			//
			// String urltt = SysConfig.getStrValue("jifen.shouke");
			// Map<String, Object> paramtt = new HashMap<String, Object>();
			// paramtt.put("usercode", apiUser.getUserId());
			// paramtt.put("typecode", jiFen);
			// paramtt.put("comeFrom", "1");
			// paramtt.put("relatedId", SysConfig.getStrValue("zj.app.code"));
			// logger.info(paramtt.toString() + "添加积分");
			//
			// try {
			// String resulttt;
			// resulttt = HttpUtils.getInstance().httpPost(urltt,
			// JSONObject.fromObject(paramtt).toString());
			// System.out.println(resulttt);
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			// }

			//
			// Map<String, Object> saveUsePlatformCodeParam = new
			// HashMap<String,
			// Object>();
			// saveUsePlatformCodeParam.put("loginPlatformCode",
			// apiUser.getLoginPlatformCode());
			// saveUsePlatformCodeParam.put("userPlatformCode",
			// apiUser.getPlatformCode());
			// saveUsePlatformCodeParam.put("userType", apiUser.getUserType());
			// saveUsePlatformCodeParam.put("account", apiUser.getAccount());
			// saveUsePlatformCodeParam.put("orgName", apiUser.getOrgName());
			// saveUsePlatformCodeParam.put("orgId", apiUser.getOrgId());
			// saveUsePlatformCodeParam.put("realName", apiUser.getRealName());
			// saveUsePlatformCodeParam.put("userId", apiUser.getUserId());
			// taUserDao.saveUserLoginLog(saveUsePlatformCodeParam);
			//
			// }
			logger.info(apiUser.getLoginPlatformCode() + ":" + apiUser.getPlatformCode() + ":" + apiUser.getRealName());
			// if (apiUser.getLoginPlatformCode() != null) {

			// 添加积分
			try {
				logger.info("添加积分");
				Map<String, Object> jfparam = new HashMap<String, Object>();

				jfparam.put("userId", apiUser.getUserId());
				jfparam.put("loginPlatformCode", apiUser.getLoginPlatformCode());
				jfparam.put("platformCode", apiUser.getPlatformCode());

				logger.info(JSONObject.fromObject(jfparam).toString());
				String loginJfUrl = SysConfig.getStrValue("jifen.login.url");
				logger.info(loginJfUrl);
				String s = HttpUtils.getInstance().httpPost(loginJfUrl, JSONObject.fromObject(jfparam).toString());
				System.out.println("result:" + s);

				Map<String, Object> saveUsePlatformCodeParam = new HashMap<String, Object>();
				saveUsePlatformCodeParam.put("loginPlatformCode", apiUser.getLoginPlatformCode());
				saveUsePlatformCodeParam.put("userPlatformCode", apiUser.getPlatformCode());
				saveUsePlatformCodeParam.put("userType", apiUser.getUserType());
				saveUsePlatformCodeParam.put("account", apiUser.getAccount());
				saveUsePlatformCodeParam.put("orgName", apiUser.getOrgName());
				saveUsePlatformCodeParam.put("orgId", apiUser.getOrgId());
				saveUsePlatformCodeParam.put("realName", apiUser.getRealName());
				saveUsePlatformCodeParam.put("userId", apiUser.getUserId());
				taUserDao.saveUserLoginLog(saveUsePlatformCodeParam);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		Ta_user ta_user = getTaUserByParam(map);
		// 同步用户
		if (null != ta_user) {
			ta_user = CommonFunction.apiUserToTA_user(apiUser);
			taUserDao.updateUser(ta_user);
		} else {
			ta_user = CommonFunction.apiUserToTA_user(apiUser);
			taUserDao.addUser(ta_user);
		}
		// 调用web端接口同步用户信息
		// syncUserToWeb(apiUser);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void syncUserToWeb(ApiUser apiUser) {
		Map map = new HashMap();
		String httpUrl = GetCacheBaseData.getPropertyValue("web_kc_url", "888888")
				+ "coursecenter_api/userOpt/addUserOperation";
		if (null != httpUrl && !httpUrl.trim().equals("")) {
			try {
				map.put("areaId", apiUser.getAreaId());
				map.put("areaName", apiUser.getAreaName());
				map.put("orgaId", apiUser.getOrgId() == null ? "暂无" : apiUser.getOrgId());
				map.put("orgaName", apiUser.getOrgName() == null ? "暂无" : apiUser.getOrgName());
				map.put("userName", apiUser.getRealName());
				map.put("userCode", apiUser.getAccount());
				map.put("platformCode", apiUser.getPlatformCode());
				BaseProperty baseProperty = (BaseProperty) CacheDataUtils.getObjectData("BaseProperty",
						"platform_url_" + apiUser.getPlatformCode() + "_entity");
				map.put("platformName", null == baseProperty ? "暂无" : baseProperty.getPlatform_name());
				map.put("email", apiUser.getEmail());
				map.put("mobnum", apiUser.getMobilePhone());
				map.put("birthday", apiUser.getBirthday());
				map.put("gender", apiUser.getGender());
				map.put("userType", apiUser.getUserType());
				map.put("idCardno", apiUser.getIdCardno());
				map.put("province", apiUser.getProvinceName());
				map.put("city", apiUser.getCityName());
				map.put("area", apiUser.getAreaName());
				map.put("userSessionId", apiUser.getUsessionid());
				map.put("loginPlatformCode", apiUser.getLoginPlatformCode());
				map.put("areaId", apiUser.getAreaId() == null ? "暂无" : apiUser.getAreaId());
				map.put("areaName", apiUser.getAreaName() == null ? "暂无" : apiUser.getAreaName());
				map.put("userId", apiUser.getUserId());
				// map.put("loginCount", ta_user.getLogin_count());
				// map.put("lastLoginTime",CommonFunction.getTimeSampleString(new
				// Date()));

				HttpUtils.getInstance().httpPost(httpUrl, JSONObject.fromObject(map).toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			logger.info("登录同步用户信息接口地址为空！");
		}
	}

	@Override
	public void powerOff(Map map) {
		taUserDao.powerOff(map);

	}

	@Override
	public List<WidgetLog> getWidgetLog(Map map) {
		return taUserDao.getWidgetLog(map);
	}

	private void addJF(String platform, String userId, Integer currentHour, Integer useTaking) {
		String userPlatformCodett = platform;
		String userIdtt = userId;
		String actionNumtt = SysConfig.getStrValue("zj.jifen.shouke.action.num");
		if (userPlatformCodett != null && !"".equals(userPlatformCodett)) {
			if ("33".equals(userPlatformCodett.substring(0, 2))) {
				if (currentHour >= 7 && currentHour <= 17) {
					String urltt = SysConfig.getStrValue("jifen.shouke");
					Map<String, Object> paramtt = new HashMap<String, Object>();
					paramtt.put("usercode", userIdtt);
					paramtt.put("typecode", actionNumtt);
					paramtt.put("comeFrom", "1");
					paramtt.put("relatedId", SysConfig.getStrValue("zj.app.code"));
					try {
						logger.info(paramtt.toString() + "添加积分");
						String resulttt = HttpUtils.getInstance().httpPost(urltt,
								JSONObject.fromObject(paramtt).toString());

						System.out.println(resulttt);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void saveWidgetInfoLog(Map map) {

		Calendar cal = Calendar.getInstance();
		int currentHour = cal.get(Calendar.HOUR_OF_DAY);

		JSONObject json = JSONObject.fromObject(map);
		logger.info(json.toString());

		// System.out.println(json.toString());

		String operationSystemInfo = null;
		String machineInfo = null;
		String terminalVersion = null;
		String isTransfer = "0";
		String operationSystemsType = null;
		String cpuInfo = null;
		String memory = null;
		String userId = null;
		Integer loginSource = null;
		Integer loginTaking = null;
		Integer useTaking = null;
		String loginPlatformCode = null;
		String orgName = null;
		String chapterId = null;
		String chapterName = null;
		String subjectId = null;
		String subjectName = null;
		String editionId = null;
		String editionName = null;
		String userName = null;
		String platformCode = null;
		String orgId = null;
		String classType = null;
		String classId = null;
		String cpuSn = null;
		String monthBoardSn = null;
		String diskSn = null;
		String biosSn = null;
		String userType = null;
		String provinceCode = null;
		String provinceName = null;
		String cityCode = null;
		String cityName = null;
		String account = null;
		String mac = null;
		String courseNum = null;

		String aamAddress = null;

		if (map.get("operationSystemInfo") != null) {
			operationSystemInfo = map.get("operationSystemInfo").toString();
		}

		if (map.get("operationSystemInfo") != null) {
			machineInfo = map.get("machineInfo").toString();
		}

		if (map.get("terminalVersion") != null) {
			terminalVersion = map.get("terminalVersion").toString();
		}

		if (map.get("operationSystemsType") != null) {
			operationSystemsType = map.get("operationSystemsType").toString();
		}

		if (map.get("cpuInfo") != null) {
			cpuInfo = map.get("cpuInfo").toString();
		}

		if (map.get("memory") != null) {
			memory = map.get("memory").toString();
		}

		if (map.get("userId") != null) {
			userId = map.get("userId").toString();
		}

		if (map.get("userName") != null) {
			userName = map.get("userName").toString();
		}

		if (map.get("orgName") != null) {
			orgName = map.get("orgName").toString();
		}

		if (map.get("orgName") != null) {
			orgId = map.get("orgName").toString();
		}

		if (map.get("orgName") != null) {
			loginSource = Integer.valueOf(map.get("loginSource").toString());
		}

		if (map.get("orgName") != null) {
			loginTaking = Integer.valueOf(map.get("loginTaking").toString());
		}

		if (map.get("useTaking") != null) {
			if (map.get("useTaking") instanceof Integer) {
				useTaking = Integer.valueOf(map.get("useTaking").toString());
			}

			if (map.get("useTaking") instanceof Double) {
				useTaking = Double.valueOf(map.get("useTaking").toString()).intValue();
			}
		}

		if (map.get("loginPlatformCode") != null) {
			loginPlatformCode = map.get("loginPlatformCode").toString();
		}

		if (map.get("orgName") != null) {
			orgName = map.get("orgName").toString();
		}

		if (map.get("chapterId") != null) {
			chapterId = map.get("chapterId").toString();
		}

		if (map.get("chapterName") != null) {
			chapterName = map.get("chapterName").toString();
		}

		if (map.get("subjectId") != null) {
			subjectId = map.get("subjectId").toString();
		}

		if (map.get("subjectName") != null) {
			subjectName = map.get("subjectName").toString();
		}

		if (map.get("editionId") != null) {
			editionId = map.get("editionId").toString();
		}

		if (map.get("editionName") != null) {
			editionName = map.get("editionName").toString();
		}

		if (map.get("userName") != null) {
			userName = map.get("userName").toString();
		}

		if (map.get("platformCode") != null) {
			platformCode = map.get("platformCode").toString();
		}

		if (map.get("orgId") != null) {
			orgId = map.get("orgId").toString();
		}

		if (map.get("classType") != null) {
			classType = map.get("classType").toString();
		}

		if (map.get("classId") != null) {
			classId = map.get("classId").toString();
		}

		if (map.get("userId") != null) {
			userId = map.get("userId").toString();
		}

		if (map.get("cpuSn") != null) {
			cpuSn = map.get("cpuSn").toString();
		}

		if (map.get("monthBoardSn") != null) {
			monthBoardSn = map.get("monthBoardSn").toString();
		}

		if (map.get("biosSn") != null) {
			biosSn = map.get("biosSn").toString();

		}

		if (map.get("diskSn") != null) {
			diskSn = map.get("diskSn").toString();
		}

		if (map.get("mac") != null) {
			mac = map.get("mac").toString();
		}

		if (map.get("courseNum") != null && !"null".equals(map.get("courseNum").toString().trim())) {
			// System.out.println(map.get("courseNum"));
			courseNum = map.get("courseNum").toString();
		}

		// logger.info("保存TerminalUseLog");
		// 为方便终端统计，ppt、edux页数统计单独设计一个list，统计是做一个widget_id转换 1转成 41 导入ppt页数
		// 2转换成42导入edux页数
		JSONArray pageCountList = json.optJSONArray("pageCount");

		if (pageCountList != null) {
			// Iterator<JSONObject> pageCountIter = pageCountList.iterator();

			for (int i = 0; i < pageCountList.size(); i++) {

				JSONObject pageCount = pageCountList.optJSONObject(i);

				String type = pageCount.optString("type");

				if ("1".equals(type)) {
					pageCount.put("widgetId", "41");
				}

				if ("2".equals(type)) {
					pageCount.put("widgetId", "42");
				}

				pageCount.put("id", GUIDGenerator.getUUID32());
				pageCount.put("operationSystemInfo", map.get("operationSystemInfo"));
				pageCount.put("machineInfo", map.get("machineInfo"));
				pageCount.put("terminalVersion", map.get("terminalVersion"));
				pageCount.put("isTransfer", "0");// 是否移动到统计表

				pageCount.put("operationSystemsType", map.get("operationSystemsType"));
				pageCount.put("cpuInfo", map.get("cpuInfo"));
				pageCount.put("memory", map.get("memory"));

				pageCount.put("useCount", pageCount.optString("pageNum"));

				if (StringUtils.isEmpty(courseNum)) {
					// System.out.println("1");
					if (StringUtils.isNotEmpty(pageCount.optString("courseNum"))) {
						// System.out.println("2");
						courseNum = pageCount.optString("courseNum");
					}
				}

				if (StringUtils.isEmpty(orgId)) {
					if (StringUtils.isNotEmpty(pageCount.optString("orgId"))) {
						orgId = pageCount.optString("orgId");
					}
				}

				if (StringUtils.isEmpty(userId)) {
					if (StringUtils.isNotEmpty(pageCount.optString("userId"))) {
						userId = pageCount.optString("userId");
					}
				}

				if (StringUtils.isEmpty(platformCode)) {
					if (StringUtils.isNotEmpty(pageCount.optString("platformCode"))) {
						platformCode = pageCount.optString("platformCode");
					}
				}

				if (StringUtils.isEmpty(classType)) {
					if (StringUtils.isNotEmpty(pageCount.optString("classType"))) {
						classType = pageCount.optString("classType");
					}
				}

				if (StringUtils.isEmpty(classId)) {
					if (StringUtils.isNotEmpty(pageCount.optString("classId"))) {
						classId = pageCount.optString("classId");
					}
				}

				if (!"".equals(map.get("userId"))) {
					pageCount.put("userId", map.get("userId"));
				}

				if (!"".equals(map.get("loginSource"))) {
					pageCount.put("loginSource", map.get("loginSource"));
				}

				if (!"".equals(map.get("loginTaking"))) {
					pageCount.put("loginTaking", map.get("loginTaking"));
				}

				if (!"".equals(map.get("useTaking"))) {
					pageCount.put("useTaking", map.get("useTaking"));
				}

				if (!"".equals(map.get("loginPlatformCode"))) {
					pageCount.put("loginPlatformCode", map.get("loginPlatformCode"));
				}

				if (!"".equals(map.get("orgName"))) {
					pageCount.put("orgName", map.get("orgName"));
				}

				if (!"".equals(map.get("chapterId"))) {
					pageCount.put("chapterId", map.get("chapterId"));
				}

				if (!"".equals(map.get("chapterName"))) {
					pageCount.put("chapterName", map.get("chapterName"));
				}

				if (!"".equals(map.get("subjectId"))) {
					pageCount.put("subjectId", map.get("subjectId"));
				}

				if (!"".equals(map.get("subjectName"))) {
					pageCount.put("subjectName", map.get("subjectName"));
				}

				if (!"".equals(map.get("editionId"))) {
					pageCount.put("editionId", map.get("editionId"));
				}

				if (!"".equals(map.get("editionName"))) {
					pageCount.put("editionName", map.get("editionName"));
				}

				if (!"".equals(map.get("userName"))) {
					pageCount.put("userName", map.get("userName"));
				}

				if (!"".equals(map.get("platformCode"))) {
					pageCount.put("platformCode", map.get("platformCode"));
				}

				if (!"".equals(map.get("orgId"))) {
					pageCount.put("orgId", map.get("orgId"));
				}

				if (!"".equals(map.get("classType"))) {
					pageCount.put("classType", map.get("classType"));
				}

				if (!"".equals(map.get("classId"))) {
					pageCount.put("classId", map.get("classId"));
				}

				if (!"".equals(map.get("userId"))) {
					pageCount.put("userId", map.get("userId"));
				}

				if (!"".equals(map.get("cpuSn"))) {
					pageCount.put("cpuSn", map.get("cpuSn"));
				}

				if (!"".equals(map.get("monthBoardSn"))) {
					pageCount.put("monthBoardSn", map.get("monthBoardSn"));
				}

				if (!"".equals(map.get("biosSn"))) {
					pageCount.put("biosSn", map.get("biosSn"));
				}

				if (!"".equals(map.get("diskSn"))) {
					pageCount.put("diskSn", map.get("diskSn"));
				}

				String classIdObject = pageCount.optString("classId");
				String classTypeObject = pageCount.optString("classType");

				if (classIdObject == null || "".equals(classIdObject) || "null".equals(classIdObject)) {
					pageCount.put("classId", "");
				}
				if (classTypeObject == null || "".equals(classTypeObject) || "null".equals(classTypeObject)) {
					pageCount.put("classType", "");
				}

				// 获取用户名和学校名称，区域编码登信息
				if (pageCount.get("userId") != null && pageCount.get("platformCode") != null) {
					platformCode = pageCount.optString("platformCode");
					userId = pageCount.optString("userId");

					if (aamAddress == null) {
						aamAddress = GetCacheBaseData.getPropertyValue("platform_url", platformCode);
					}
					if (aamAddress == null) {
						aamAddress = basePropertyService.getPropertyValue("platform_url", platformCode);
					}
					String url = aamAddress + "user/" + userId;
					String resp;
					try {
						resp = HttpUtils.getInstance().httpGet(url, 30000, 30000);
						if (logger.isDebugEnabled()) {
							logger.debug("信息返回:" + resp);
						}
						if (resp != null && !"".equals(resp)) {
							JSONObject respJson = JSONObject.fromObject(resp);
							if ("000000".equals(respJson.getString("result"))) {
								JSONObject userObject = (JSONObject) respJson.get("userinfo");
								pageCount.put("userName",
										userObject.get("name") == null ? "" : userObject.get("name").toString());
								pageCount.put("orgName", userObject.get("organame") == null ? ""
										: userObject.get("organame").toString());
								pageCount.put("areaCode", userObject.optString("citycode"));

								pageCount.put("userType", userObject.optString("usertype"));
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				try {
					taUserDao.saveWidgetInfoLog(pageCount);
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					WidgetLog widgetLogBean = new WidgetLog();
					widgetLogBean.setCreateTime(new Date());
					BeanUtils.copyProperties(widgetLogBean, pageCount);

					// if ("1".equals(widgetLogBean.getWidgetId()) &&
					// (widgetLogBean.getUserId() != null)) {
					// coursecenterMongoDBBaseDao.add(widgetLogBean);
					// }
					mongoDBBaseDao.add(widgetLogBean);
					logger.info("--保存到mongodb");
				} catch (IllegalAccessException | InvocationTargetException e) {

					e.printStackTrace();
				}
				// coursecenterMongoDBBaseDao.add(pageCount);//保存数据到教学助手数据库
			}

		}

		JSONArray list = json.getJSONArray("list");
		Iterator<JSONObject> iter = list.iterator();
		while (iter.hasNext()) {
			JSONObject widgetLog = (JSONObject) iter.next();
			widgetLog.put("id", GUIDGenerator.getUUID32());

			widgetLog.put("operationSystemInfo", operationSystemInfo);
			widgetLog.put("machineInfo", machineInfo);
			widgetLog.put("terminalVersion", terminalVersion);
			widgetLog.put("isTransfer", "0");// 是否移动到统计表

			widgetLog.put("operationSystemsType", operationSystemsType);
			widgetLog.put("cpuInfo", cpuInfo);
			widgetLog.put("memory", memory);

			if (!"".equals(map.get("userId"))) {
				widgetLog.put("userId", map.get("userId"));
			}

			if (loginPlatformCode != null) {
				widgetLog.put("loginPlatformCode", loginPlatformCode);
			} else {
				if (map.get("loginPlatformCode") != null) {
					widgetLog.put("loginPlatformCode", map.get("loginPlatformCode"));
				}
			}

			if (platformCode != null) {
				widgetLog.put("platformCode", platformCode);
			} else {
				if (map.get("platformCode") != null) {
					widgetLog.put("platformCode", map.get("platformCode"));
				}
			}

			if (!"".equals(map.get("loginSource"))) {
				widgetLog.put("loginSource", map.get("loginSource"));
			}

			if (!"".equals(map.get("loginTaking"))) {
				widgetLog.put("loginTaking", map.get("loginTaking"));
			}

			if (!"".equals(map.get("useTaking"))) {
				widgetLog.put("useTaking", map.get("useTaking"));
			}

			if (!"".equals(map.get("loginPlatformCode"))) {
				widgetLog.put("loginPlatformCode", map.get("loginPlatformCode"));
			}

			if (!"".equals(map.get("orgName"))) {
				widgetLog.put("orgName", map.get("orgName"));
			}

			if (!"".equals(map.get("chapterId"))) {
				widgetLog.put("chapterId", map.get("chapterId"));
			}

			if (!"".equals(map.get("chapterName"))) {
				widgetLog.put("chapterName", map.get("chapterName"));
			}

			if (!"".equals(map.get("subjectId"))) {
				widgetLog.put("subjectId", map.get("subjectId"));
			}

			if (!"".equals(map.get("subjectName"))) {
				widgetLog.put("subjectName", map.get("subjectName"));
			}

			if (!"".equals(map.get("editionId"))) {
				widgetLog.put("editionId", map.get("editionId"));
			}

			if (!"".equals(map.get("editionName"))) {
				widgetLog.put("editionName", map.get("editionName"));
			}

			if (!"".equals(map.get("userName"))) {
				widgetLog.put("userName", map.get("userName"));
			}

			if (!"".equals(map.get("platformCode"))) {
				widgetLog.put("platformCode", map.get("platformCode"));
			}

			if (!"".equals(map.get("orgId"))) {
				widgetLog.put("orgId", map.get("orgId"));
			}

			if (!"".equals(map.get("classType"))) {
				widgetLog.put("classType", map.get("classType"));
			}

			if (!"".equals(map.get("classId"))) {
				widgetLog.put("classId", map.get("classId"));
			}

			if (!"".equals(map.get("userId"))) {
				widgetLog.put("userId", map.get("userId"));
			}

			if (!"".equals(map.get("cpuSn"))) {
				widgetLog.put("cpuSn", map.get("cpuSn"));
			}

			if (!"".equals(map.get("monthBoardSn"))) {
				widgetLog.put("monthBoardSn", map.get("monthBoardSn"));
			}

			if (!"".equals(map.get("biosSn"))) {
				widgetLog.put("biosSn", map.get("biosSn"));
			}

			if (!"".equals(map.get("diskSn"))) {
				widgetLog.put("diskSn", map.get("diskSn"));
			}

			if (!"".equals(map.get("courseNum"))) {
				widgetLog.put("courseNum", map.get("courseNum"));
			}

			if (StringUtils.isEmpty(operationSystemInfo)) {
				operationSystemInfo = widgetLog.optString("operationSystemInfo");
			}

			if (StringUtils.isEmpty(machineInfo)) {
				machineInfo = widgetLog.optString("machineInfo");
			}

			if (StringUtils.isEmpty(terminalVersion)) {
				terminalVersion = widgetLog.optString("terminalVersion");
			}

			if (StringUtils.isEmpty(operationSystemsType)) {
				operationSystemsType = widgetLog.optString("operationSystemsType");
			}

			if (StringUtils.isEmpty(cpuInfo)) {
				cpuInfo = widgetLog.optString("cpuInfo");
			}

			if (StringUtils.isEmpty(memory)) {
				memory = widgetLog.optString("memory");
			}

			if (StringUtils.isEmpty(userId)) {
				userId = widgetLog.optString("userId");
			}

			if (StringUtils.isEmpty(loginPlatformCode)) {
				loginPlatformCode = widgetLog.optString("loginPlatformCode");
			}

			if (StringUtils.isEmpty(orgName)) {
				orgName = widgetLog.optString("orgName");
			}

			if (StringUtils.isEmpty(chapterId)) {
				chapterId = widgetLog.optString("chapterId");
			}

			if (StringUtils.isEmpty(chapterName)) {
				chapterName = widgetLog.optString("chapterName");
			}

			if (StringUtils.isEmpty(subjectId)) {
				subjectId = widgetLog.optString("subjectId");
			}

			if (StringUtils.isEmpty(subjectName)) {
				subjectName = widgetLog.optString("subjectName");
			}

			if (StringUtils.isEmpty(editionId)) {
				editionId = widgetLog.optString("editionId");
			}

			if (StringUtils.isEmpty(editionName)) {
				editionName = widgetLog.optString("editionName");
			}

			if (StringUtils.isEmpty(userName)) {
				userName = widgetLog.optString("userName");
			}

			if (StringUtils.isEmpty(platformCode)) {
				platformCode = widgetLog.optString("platformCode");
			}

			if (StringUtils.isEmpty(orgId)) {
				orgId = widgetLog.optString("orgId");
			}

			if (StringUtils.isEmpty(classType)) {
				classType = widgetLog.optString("classType");
			}

			if (StringUtils.isEmpty(classId)) {
				classId = widgetLog.optString("classId");
			}

			if (StringUtils.isEmpty(cpuSn)) {
				cpuSn = widgetLog.optString("cpuSn");
			}

			if (StringUtils.isEmpty(monthBoardSn)) {
				monthBoardSn = widgetLog.optString("monthBoardSn");
			}

			if (StringUtils.isEmpty(diskSn)) {
				diskSn = widgetLog.optString("diskSn");
			}

			if (StringUtils.isEmpty(biosSn)) {
				biosSn = widgetLog.optString("biosSn");
			}

			if (StringUtils.isEmpty(userType)) {
				userType = widgetLog.optString("userType");
			}

			if (StringUtils.isEmpty(provinceCode)) {
				provinceCode = widgetLog.optString("provinceCode");
			}

			if (StringUtils.isEmpty(provinceName)) {
				provinceName = widgetLog.optString("provinceName");
			}

			if (StringUtils.isEmpty(cityCode)) {
				cityCode = widgetLog.optString("cityCode");
			}

			if (StringUtils.isEmpty(cityName)) {
				cityName = widgetLog.optString("cityName");
			}

			if (StringUtils.isEmpty(account)) {
				account = widgetLog.optString("account");
			}

			if (StringUtils.isEmpty(mac)) {
				mac = widgetLog.optString("mac");
			}

			if (StringUtils.isEmpty(courseNum)) {
				courseNum = widgetLog.optString("courseNum");
			}

			if (StringUtils.isNotEmpty(courseNum)) {
				widgetLog.put("courseNum", courseNum);
			}

			String widgetId = widgetLog.optString("widgetId");

			classId = widgetLog.optString("classId");
			classType = widgetLog.optString("classType");

			if (widgetId != null && !"".equals(widgetId)) {

				Integer useTakingtt = 0;
				if (widgetLog.get("useTaking") instanceof Integer) {
					useTakingtt = Integer.valueOf(map.get("useTaking").toString());
				}

				if (widgetLog.get("useTaking") instanceof Double) {
					useTakingtt = Double.valueOf(map.get("useTaking").toString()).intValue();
				}
				if (useTakingtt == null || useTakingtt == 0) {
					addJF(widgetLog.optString("platformCode"), widgetLog.optString("userId"), currentHour, useTakingtt);
				}

				if (useTakingtt >= 0) {
					// 有效时长
					Long yxuseTaking = 10 * 60L;
					logger.info("useTaking:" + useTakingtt);
					String userPlatformCodettt = platformCode;
					String userIdttt = userId;
					String actionNumttt = SysConfig.getStrValue("zj.jifen.shouke.action.num");
					// 浙江帐号 并且一堂课使用时长大于15分钟
					if (userPlatformCodettt != null && !"".equals(userPlatformCodettt)) {
						if (useTaking != null) {
							if (useTaking == 0) {
								if (currentHour >= 7 && currentHour <= 17) {
									String urlttt = SysConfig.getStrValue("jifen.shouke");
									Map<String, Object> paramttt = new HashMap<String, Object>();
									paramttt.put("usercode", userIdttt);
									paramttt.put("typecode", actionNumttt);
									paramttt.put("comeFrom", "1");
									paramttt.put("relatedId", SysConfig.getStrValue("zj.app.code"));
									try {
										logger.info(paramttt.toString() + "添加积分");
										String resultttt = HttpUtils.getInstance().httpPost(urlttt,
												JSONObject.fromObject(paramttt).toString());
										System.out.println(resultttt);
									} catch (Exception e) {
										e.printStackTrace();
									}

								}
							}
						}

						if ("33".equals(userPlatformCodettt.substring(0, 2)) && (useTakingtt > yxuseTaking)) {
							if (currentHour >= 7 && currentHour <= 17) {
								String urlttt = SysConfig.getStrValue("jifen.shouke");
								Map<String, Object> paramttt = new HashMap<String, Object>();
								paramttt.put("usercode", userIdttt);
								paramttt.put("typecode", actionNumttt);
								paramttt.put("comeFrom", "1");
								paramttt.put("relatedId", SysConfig.getStrValue("zj.app.code"));
								try {
									logger.info(paramttt.toString() + "添加积分");
									String resultttt = HttpUtils.getInstance().httpPost(urlttt,
											JSONObject.fromObject(paramttt).toString());
									System.out.println(resultttt);
								} catch (Exception e) {
									e.printStackTrace();
								}

							}
						}
					}

				} else {
					logger.info("useTaking:" + useTakingtt);
					widgetLog.put("useTaking", 0);
				}

				if ("1".equals(widgetId)) {// 如果是登录，则统计登录来源和耗时

					if (widgetLog.get("loginSource") != null && !"".equals(widgetLog.get("loginSource"))) {
						loginSource = Integer.valueOf(widgetLog.get("loginSource").toString());
					} else {
						loginSource = (Integer) map.get("loginSource");
					}

					if (widgetLog.get("loginTaking") != null && !"".equals(widgetLog.get("loginTaking"))) {
						loginTaking = Integer.valueOf(widgetLog.get("loginSource").toString());
					} else {
						loginTaking = (Integer) map.get("loginTaking");
					}

					if (loginSource == null || "".equals(loginSource) || "null".equals(loginSource)) {
						widgetLog.put("loginSource", "");
					} else {
						widgetLog.put("loginSource", loginSource);
					}
					if (loginTaking == null || "".equals(loginTaking) || "null".equals(loginTaking)) {
					} else {
						widgetLog.put("loginTaking", loginTaking);
					}

					if (widgetLog.optString("useTaking") == null || "".equals(widgetLog.optString("useTaking"))) {
						// widgetLog.put("useTaking", 0);
					} else {
						logger.info(map.get("useTaking").getClass().toString());

						if (widgetLog.get("useTaking") instanceof Integer) {
							useTaking = Integer.valueOf(map.get("useTaking").toString());
						}

						if (widgetLog.get("useTaking") instanceof Double) {
							useTaking = Double.valueOf(map.get("useTaking").toString()).intValue();
						}

						if (useTaking == null || useTaking == 0) {

							userId = widgetLog.optString("userId");
							String actionNum = SysConfig.getStrValue("zj.jifen.shouke.action.num");
							if ("33".equals(platformCode.substring(0, 2))) {
								if (currentHour >= 7 && currentHour <= 17) {
									String url = SysConfig.getStrValue("jifen.shouke");
									Map<String, Object> param = new HashMap<String, Object>();
									param.put("usercode", userId);
									param.put("typecode", actionNum);
									param.put("comeFrom", "1");
									param.put("relatedId", SysConfig.getStrValue("zj.app.code"));
									try {
										logger.info(param.toString() + "添加积分");
										String result = HttpUtils.getInstance().httpPost(url,
												JSONObject.fromObject(param).toString());
										System.out.println(result);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
						}

						if (useTaking >= 0) {

							// 有效时长
							Long yxuseTaking = 10 * 60L;

							logger.info("useTaking:" + useTaking);
							widgetLog.put("useTaking", useTaking);

							// platformCode =
							// widgetLog.optString("platformCode");
							userId = widgetLog.optString("userId");
							String actionNum = SysConfig.getStrValue("zj.jifen.shouke.action.num");
							// 浙江帐号 并且一堂课使用时长大于15分钟
							if ("33".equals(platformCode.substring(0, 2)) && (useTaking > yxuseTaking)) {
								if (currentHour >= 7 && currentHour <= 17) {
									String url = SysConfig.getStrValue("jifen.shouke");
									Map<String, Object> param = new HashMap<String, Object>();
									param.put("usercode", userId);
									param.put("typecode", actionNum);
									param.put("comeFrom", "1");
									param.put("relatedId", SysConfig.getStrValue("zj.app.code"));
									try {

										logger.info(param.toString() + "添加积分");

										String result = HttpUtils.getInstance().httpPost(url,
												JSONObject.fromObject(param).toString());

										System.out.println(result);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}

						} else {
							logger.info("useTaking:" + useTaking);
							widgetLog.put("useTaking", 0);
						}

					}
				}
			}

			if (classId == null || "".equals(classId) || "null".equals(classId)) {
				widgetLog.put("classId", "");
			}
			if (classType == null || "".equals(classType) || "null".equals(classType)) {
				widgetLog.put("classType", "");
			}

			if (StringUtils.isEmpty(platformCode)) {
				if (StringUtils.isNotEmpty(widgetLog.optString("platformCode"))) {
					userId = widgetLog.optString("platformCode");
				}
			}

			if (StringUtils.isEmpty(userId)) {
				if (StringUtils.isNotEmpty(widgetLog.optString("userId"))) {
					userId = widgetLog.optString("userId");
				}
			}

			if (aamAddress == null) {
				if ("33".equals(platformCode.substring(0, 2))) {
					aamAddress = GetCacheBaseData.getPropertyValue("platform_url", "330000");
				} else {
					aamAddress = GetCacheBaseData.getPropertyValue("platform_url", platformCode);
				}

			}

			if (aamAddress == null) {
				aamAddress = basePropertyService.getPropertyValue("platform_url", platformCode);
			}
			String url = aamAddress + "user/" + userId;
			String resp;
			try {
				resp = HttpUtils.getInstance().httpGet(url, 30000, 30000);
				if (logger.isDebugEnabled()) {
					logger.debug("信息返回:" + resp);
				}
				if (resp != null && !"".equals(resp)) {
					JSONObject respJson = JSONObject.fromObject(resp);
					if ("000000".equals(respJson.getString("result"))) {
						JSONObject userObject = (JSONObject) respJson.get("userinfo");

						if (StringUtils.isEmpty(userName)) {
							userName = userObject.optString("name");
						}

						if (StringUtils.isEmpty(orgName)) {
							orgName = userObject.optString("organame");
						}

						if (StringUtils.isEmpty(userType)) {
							userType = userObject.optString("usertype");
						}

						if (StringUtils.isEmpty(userName)) {
							userName = userObject.optString("name");
						}

						if (StringUtils.isEmpty(account)) {
							account = userObject.optString("account");
						}

						if (StringUtils.isEmpty(cityName)) {
							cityName = userObject.optString("cityname");
						}

						if (StringUtils.isEmpty(cityCode)) {
							cityCode = userObject.optString("citycode");
						}

						if (StringUtils.isEmpty(provinceCode)) {
							provinceCode = userObject.optString("provicecode");
						}

						if (StringUtils.isEmpty(provinceName)) {
							provinceName = userObject.optString("provicename");
						}

						if (StringUtils.isEmpty(orgName)) {
							orgName = userObject.optString("organame");
						}

						if (StringUtils.isEmpty(orgId)) {
							orgId = userObject.optString("orgaid");
						}

						widgetLog.put("userName",
								userObject.get("name") == null ? "" : userObject.get("name").toString());
						widgetLog.put("orgName",
								userObject.get("organame") == null ? "" : userObject.get("organame").toString());
						widgetLog.put("areaCode", userObject.optString("citycode"));
						widgetLog.put("userType", userObject.optString("usertype"));
						widgetLog.put("account", userObject.optString("account"));
						widgetLog.put("cityName", userObject.optString("cityname"));
						widgetLog.put("cityCode", userObject.optString("citycode"));
						widgetLog.put("provinceCode", userObject.optString("provincecode"));
						widgetLog.put("provinceName", userObject.optString("provincename"));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// }
			taUserDao.saveWidgetInfoLog(widgetLog);

			try {

				if ("1".equals(widgetLog.optString("widgetId"))) {
					WidgetLog widgetLogBean = new WidgetLog();
					widgetLogBean.setCreateTime(new Date());
					widgetLogBean.setLoginPlatformCode(loginPlatformCode);
					BeanUtils.copyProperties(widgetLogBean, widgetLog); // 教学助手抽取表
					mongoDBBaseDao.add(widgetLogBean);
				} else {
					HdktEventCount hdktEventCount = new HdktEventCount();

					hdktEventCount.setTerminalVersion(terminalVersion);
					hdktEventCount.setAccount(account);
					hdktEventCount.setCityCode(cityCode);
					hdktEventCount.setBiosSn(biosSn);
					hdktEventCount.setChapterId(chapterId);
					hdktEventCount.setCityCode(cityCode);
					hdktEventCount.setCityName(cityName);
					hdktEventCount.setClassId(classId);
					hdktEventCount.setClassType(classType);
					hdktEventCount.setCourseNum(courseNum);
					hdktEventCount.setCpuSn(cpuSn);
					hdktEventCount.setDiskSn(diskSn);
					hdktEventCount.setEditionId(editionId);
					hdktEventCount.setLoginPlatformCode(loginPlatformCode);
					hdktEventCount.setMac(mac);
					hdktEventCount.setOrgId(orgId);
					hdktEventCount.setOrgName(orgName);
					hdktEventCount.setProvinceId(provinceCode);
					hdktEventCount.setProvinceName(provinceName);
					hdktEventCount.setSubjectId(subjectId);
					hdktEventCount.setUserId(userId);
					hdktEventCount.setUserName(userName);
					hdktEventCount.setUserType(userType);

					hdktEventCount.setPlatformCode(platformCode);
					hdktEventCount.setEventTime(TimeUtils.date2String(new Date(), TimeUtils.TIME_FORMAT));
					hdktEventCount.setCreateTime(TimeUtils.date2String(new Date(), TimeUtils.TIME_FORMAT));
					hdktEventCount.setEventId(widgetLog.optString("widgetId"));
					changeEvent(hdktEventCount);

					int useCount = widgetLog.optInt("useCount");

					for (int i = 0; i < useCount; i++) {
						// DB db = mongoDBBaseDao.getMongoTemplate().getDb();
						// Mongo mongo = db.getMongo();
						// System.out.println(mongo.toString());
						// System.out.println(db.toString());
						mongoDBBaseDao.add(hdktEventCount);

						logger.info("------------保存hdktEventCount:" + hdktEventCount.getUserId());
					}

				}
				logger.info("保存到mongodb---");
			} catch (IllegalAccessException | InvocationTargetException e) {

				e.printStackTrace();
			}

		}

		HdktLoginLog hdktLoginLog = new HdktLoginLog();

		hdktLoginLog.setAccount(account);
		hdktLoginLog.setCityCode(cityCode);
		hdktLoginLog.setBiosSn(biosSn);
		hdktLoginLog.setChapterId(chapterId);
		hdktLoginLog.setCityCode(cityCode);
		hdktLoginLog.setCityName(cityName);
		hdktLoginLog.setClassId(classId);
		hdktLoginLog.setClassType(classType);
		hdktLoginLog.setCourseNum(courseNum);
		hdktLoginLog.setCpuSn(cpuSn);
		hdktLoginLog.setDiskSn(diskSn);
		hdktLoginLog.setEditionId(editionId);
		hdktLoginLog.setLoginPlatformCode(loginPlatformCode);

		hdktLoginLog.setTerminalVersion(terminalVersion);

		if (loginSource != null) {
			hdktLoginLog.setLoginSource(loginSource.toString());
		}

		if (loginTaking != null) {
			hdktLoginLog.setLoginTaking(loginTaking.toString());
		}

		hdktLoginLog.setPlatformCode(platformCode);
		hdktLoginLog.setMac(mac);
		hdktLoginLog.setOrgId(orgId);
		hdktLoginLog.setOrgName(orgName);
		hdktLoginLog.setProvinceId(provinceCode);
		hdktLoginLog.setProvinceName(provinceName);
		hdktLoginLog.setSubjectId(subjectId);
		hdktLoginLog.setUserId(userId);
		hdktLoginLog.setUserName(userName);
		hdktLoginLog.setUserType(userType);
		hdktLoginLog.setCreateTime(TimeUtils.date2String(new Date(), TimeUtils.TIME_FORMAT));

		if (useTaking != null) {
			hdktLoginLog.setUseTaking(useTaking.toString());
		}
		DB db = mongoDBBaseDao.getMongoTemplate().getDb();
		Mongo mongo = db.getMongo();
		System.out.println(mongo.toString());
		System.out.println(db.toString());
		mongoDBBaseDao.add(hdktLoginLog);
		logger.info("----------保存hdktLoginLog:" + hdktLoginLog.getUserId());
		// logger.info("保存widgetInfoLog");
		// 统计终端连接情况

		JSONArray terminalLinkInfoList = json.optJSONArray("terminalLinkInfo");

		// JSONArray terminalLinkInfoList =
		// json.getJSONArray("terminalLinkInfo");

		if (terminalLinkInfoList != null) {

			for (int i = 0; i < terminalLinkInfoList.size(); i++) {
				JSONObject terminalLog = terminalLinkInfoList.getJSONObject(i);
				terminalLog.put("id", GUIDGenerator.getUUID32());
				terminalLog.put("hdktVersion", map.get("terminalVersion"));
				terminalLog.put("isTransfer", "0");

				if (null != map.get("platformCode")) {
					terminalLog.put("platformCode", map.get("platformCode"));
				}

				if (null != map.get("orgId")) {
					terminalLog.put("orgId", map.get("orgId"));
				}

				if (null != map.get("classId")) {
					terminalLog.put("classId", map.get("classId"));
				}
				if (null != map.get("classType")) {
					terminalLog.put("classType", map.get("classType"));
				}

				if (null != map.get("courseNum")) {
					terminalLog.put("courseNum", map.get("courseNum"));
				}

				int linkNum = terminalLog.optInt("linkNum");

				if (linkNum > 0) {
					// 保存到mongodb
					HdktTerminalLinkCount hdktTerminalLinkCount = new HdktTerminalLinkCount();

					hdktTerminalLinkCount.setTerminalVersion(terminalVersion);
					hdktTerminalLinkCount.setAccount(account);
					hdktTerminalLinkCount.setBiosSn(biosSn);
					hdktTerminalLinkCount.setChapterId(chapterId);
					hdktTerminalLinkCount.setCityCode(cityCode);
					hdktTerminalLinkCount.setCityName(cityName);
					hdktTerminalLinkCount.setClassId(classId);
					hdktTerminalLinkCount.setClassType(classType);
					hdktTerminalLinkCount.setCourseNum(courseNum);
					hdktTerminalLinkCount.setCpuSn(cpuSn);
					hdktTerminalLinkCount.setDiskSn(diskSn);
					hdktTerminalLinkCount.setEditionId(editionId);
					hdktTerminalLinkCount.setLinkNum(Integer.valueOf(linkNum).toString());

					hdktTerminalLinkCount.setPlatformCode(platformCode);

					hdktTerminalLinkCount.setLoginPlatformCode(loginPlatformCode);
					hdktTerminalLinkCount.setMac(mac);
					hdktTerminalLinkCount.setOrgId(orgId);
					hdktTerminalLinkCount.setOrgName(orgName);
					hdktTerminalLinkCount.setProvinceId(provinceCode);
					hdktTerminalLinkCount.setProvinceName(provinceName);

					hdktTerminalLinkCount.setSubjectId(subjectId);
					hdktTerminalLinkCount
							.setTerminalType(Integer.valueOf(terminalLog.optInt("terminalType")).toString());
					hdktTerminalLinkCount.setUserId(userId);
					hdktTerminalLinkCount.setUserName(userName);

					hdktTerminalLinkCount.setCreateTime(TimeUtils.date2String(new Date(), TimeUtils.TIME_FORMAT));

					mongoDBBaseDao.add(hdktTerminalLinkCount);
					logger.info("------保存hdktTerminalLinkCount:" + hdktTerminalLinkCount.getUserId());
					taUserDao.saveTerminalUseLog(terminalLog);
				}
			}

		}

	}

	private void changeEvent(HdktEventCount hdktEventCount) {
		String widgetId = hdktEventCount.getEventId();

		switch (Integer.valueOf(widgetId)) {
		case 1:
			hdktEventCount.setEventId("00103");
			break;
		case 2:
			break;
		case 3:
			hdktEventCount.setEventId("00109");
			break;
		case 4:
			hdktEventCount.setEventId("0011B");
			break;
		case 5:
			hdktEventCount.setEventId("00501");
			break;
		case 6:
			hdktEventCount.setEventId("00601");
			break;
		case 7:
			hdktEventCount.setEventId("00901");
			break;
		case 8:
			hdktEventCount.setEditionId("01001");
			break;
		case 9:
			hdktEventCount.setEventId("00E01");
			break;
		case 10:
			hdktEventCount.setEventId("00A01");
			break;
		case 11:
			hdktEventCount.setEventId("00B01");
			break;
		case 12:
			hdktEventCount.setEventId("00C01");
			break;
		case 13:
			hdktEventCount.setEventId("01201");
			break;
		case 14:
			hdktEventCount.setEventId("01301");
			break;
		case 15:
			hdktEventCount.setEventId("01401");
			break;
		case 16:
			hdktEventCount.setEventId("01504");
			break;
		case 17:
			hdktEventCount.setEventId("01505");
			break;
		case 18:
			hdktEventCount.setEventId("01602");
			break;
		case 19:
			hdktEventCount.setEventId("01603");
			break;
		case 20:
			hdktEventCount.setEventId("01901");
			break;
		case 21:
			hdktEventCount.setEventId("01A01");
			break;
		case 22:
			hdktEventCount.setEventId("01B01");
			break;
		case 23:
			hdktEventCount.setEventId("01C01");
			break;
		case 24:
			hdktEventCount.setEditionId("01D01");
			break;
		case 25:
			hdktEventCount.setEditionId("01F01");
			break;
		case 26:
			hdktEventCount.setEditionId("02001");
			break;
		case 27:
			hdktEventCount.setEditionId("02101");
			break;
		case 28:
			hdktEventCount.setEditionId("02201");
			break;
		case 29:
			hdktEventCount.setEditionId("02301");
			break;
		case 30:
			hdktEventCount.setEditionId("02303");
			break;
		case 31:
			hdktEventCount.setEventId("0022A");
			break;
		case 32:
			hdktEventCount.setEventId("00229");
			break;
		case 33:
			hdktEventCount.setEventId("02C01");
			break;
		case 34:
			hdktEventCount.setEditionId("03101");
			break;
		case 35:
			hdktEventCount.setEventId("02E1B");
			break;
		case 36:
			break;
		case 37:
			hdktEventCount.setEventId("03001");
			break;
		case 38:
			hdktEventCount.setEventId("02502");
			break;
		case 39:
			hdktEventCount.setEventId("02503");
			break;
		case 40:
			hdktEventCount.setEventId("01E02");
			break;
		default:
			break;
		}

	}

	@Override
	public void updateWidgetLog(Map map) {
		taUserDao.updateWidgetLog(map);
	}

	@Override
	public void saveInstallLog(Map map) {
		taUserDao.saveInstallLog(map);
	}

	@Override
	public String visitCount(Map map) {
		return taUserDao.visitCount(map);
	}

	@Override
	public String listOgra(Map map, String start, String end, String url) {
		map.put("start", start);
		map.put("end", end);
		String answer = null;
		try {
			answer = HttpUtils.getInstance().httpPost(url, JSONObject.fromObject(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answer;
	}

	@Override
	public String listClassesInfo(Map<String, Object> param, String start, String end, String url) {
		param.put("start", start);
		param.put("end", end);
		String answer = null;
		try {
			answer = HttpUtils.getInstance().httpPost(url, JSONObject.fromObject(param).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answer;
	}

	@Override
	public void saveInstallMac(Map<String, Object> paramMap) {
		taUserDao.saveInstallMac(paramMap);

	}

}
