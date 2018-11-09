package com.whty.assis.api.service.impl;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.whty.assis.api.dao.WidgetLogDao;
import com.whty.assis.api.model.HdktEventCount;
import com.whty.assis.api.model.HdktLoginLog;
import com.whty.assis.api.model.HdktTerminalLinkCount;
import com.whty.assis.api.service.DataMoveService;
import com.whty.assis.api.utils.HttpUtils;
import com.whty.assis.manage.service.BasePropertyService;
import com.whty.common.cache.data.CacheInit;
import com.whty.common.cache.data.GetCacheBaseData;
import com.whty.common.mongodb.MongoDBBaseDao;
import com.whty.common.util.SysConstants;
import com.whty.common.util.TimeUtils;

import net.sf.json.JSONObject;

/*
 * 数据迁移
 */
@Service
public class DataMoveServiceImpl implements DataMoveService {

	@Autowired
	private BasePropertyService basePropertyService;

	@Autowired
	private WidgetLogDao widgetLogDao;

	@Resource(name = "mongoDBBaseDao")
	MongoDBBaseDao mongoDBBaseDao;

	public void test() {
		System.out.println(widgetLogDao.test());

	}

	@Override
	public void saveHdktEventCount() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startTime", "2018-05-26 20:00:00");
		param.put("endTime", "2018-05-26 20:30:00");
		List<Map<String, Object>> resultUseCountList = widgetLogDao.queryWidget(param);
		for (Map<String, Object> map : resultUseCountList) {

			String cpuSn = null;
			String classType = null;
			String loginPlatformCode = null;
			String courseNum = null;
			String cityName = null;
			String diskSn = null;
			String mac = null;
			String userId = null;
			String userName = null;
			String orgName = null;
			String subjectId = null;
			String editionId = null;
			String provinceName = null;
			// String eventTime = null;
			String orgId = null;
			String eventId = null;
			String classId = null;
			String biosSn = null;
			String account = null;
			String chapterId = null;
			String userType = null;
			String cityCode = null;
			String provinceId = null;
			String terminalVersion = null;
			// String loginSource = null;
			// String loginTaking = null;
			// String useTaking = null;
			String origin = "1";
			String platformCode = null;
			String useCount = null;

			if (map.get("CPU_SN") != null) {
				cpuSn = map.get("CPU_SN").toString();
			}
			if (map.get("CLASS_TYPE") != null) {
				classType = map.get("CLASS_TYPE").toString();
			}
			if (map.get("LOGIN_PLATFORM_CODE") != null) {
				loginPlatformCode = map.get("LOGIN_PLATFORM_CODE").toString();
			}
			if (map.get("COURSE_NUM") != null) {
				courseNum = map.get("COURSE_NUM").toString();
			}
			if (map.get("DISK_SN") != null) {
				diskSn = map.get("DISK_SN").toString();
			}
			if (map.get("BIOS_SN") != null) {
				biosSn = map.get("BIOS_SN").toString();
			}
			if (map.get("SUBJECT_ID") != null) {
				subjectId = map.get("SUBJECT_ID").toString();
			}
			if (map.get("EDITION_ID") != null) {
				editionId = map.get("EDITION_ID").toString();
			}
			if (map.get("CLASS_ID") != null) {
				classId = map.get("CLASS_ID").toString();
			}
			if (map.get("CHAPTER_ID") != null) {
				chapterId = map.get("CHAPTER_ID").toString();
			}
			if (map.get("USER_TYPE") != null) {
				userType = map.get("USER_TYPE").toString();
			}
			if (map.get("WIDGET_ID") != null) {
				eventId = map.get("WIDGET_ID").toString();
			}
			if (map.get("ORG_ID") != null) {
				orgId = map.get("ORG_ID").toString();
			}
			if (map.get("USER_ID") != null) {
				userId = map.get("USER_ID").toString();
			}
			if (map.get("TERMINAL_VERSION") != null) {
				terminalVersion = map.get("TERMINAL_VERSION").toString();
			}

			// eventTime = map.get("create_time").toString();
			// provinceId = map.get("platform_code").toString();
			// proviceName = map.get("login_count").toString();
			// cityCode = map.get("create_time").toString();
			// cityName = map.get("org_id").toString();
			// orgName = map.get("platform_code").toString();
			// userName = map.get("create_time").toString();
			// account = map.get("platform_code").toString();
			if (map.get("PLATFORM_CODE") != null) {
				platformCode = map.get("PLATFORM_CODE").toString();
			}
			StringBuffer keySb = new StringBuffer();
			keySb.append(SysConstants.BASE_PROPERTY_CACHE_KEY).append(SysConstants.CACHE_KEY_SEPERATER)
					.append(CacheInit.AAM_URL_KEY).append(SysConstants.CACHE_KEY_SEPERATER).append(platformCode);
			System.out.println(keySb.toString());

			if ("33".equals(platformCode.substring(0, 2))) {
				platformCode = "330000";
			}
			String aamAddress = GetCacheBaseData.getPropertyValue("platform_url", platformCode);
			if (aamAddress == null) {
				aamAddress = basePropertyService.getPropertyValue("platform_url", platformCode);
			} else {
				String url = aamAddress + "user/" + userId;
				String resp = null;
				try {
					resp = HttpUtils.getInstance().httpGet(url, 3000, 3000);
					if (resp != null && !"".equals(resp)) {
						JSONObject respJson = JSONObject.fromObject(resp);

						if ("000000".equals(respJson.getString("result"))) {
							JSONObject userObject = (JSONObject) respJson.get("userinfo");
							provinceId = userObject.optString("provicecode");
							provinceName = userObject.optString("provicename");
							cityCode = userObject.optString("citycode");
							cityName = userObject.optString("cityname");
							userName = userObject.optString("name");
							orgName = userObject.optString("organame");
							account = userObject.optString("account");
						}
					}
					// loginSource = map.get("login_source").toString();
					// loginTaking = map.get("login_taking").toString();
					// useTaking = map.get("use_taking").toString();

					HdktEventCount hdktEventCount = new HdktEventCount();
					hdktEventCount.setCpuSn(cpuSn);
					hdktEventCount.setClassType(classType);
					hdktEventCount.setLoginPlatformCode(loginPlatformCode);
					hdktEventCount.setCourseNum(courseNum);
					hdktEventCount.setCityName(cityName);
					hdktEventCount.setDiskSn(diskSn);
					hdktEventCount.setMac(mac);
					hdktEventCount.setUserId(userId);
					hdktEventCount.setUserName(userName);
					hdktEventCount.setOrgName(orgName);
					hdktEventCount.setSubjectId(subjectId);
					hdktEventCount.setEditionId(editionId);
					hdktEventCount.setProvinceName(provinceName);
					hdktEventCount.setEventTime(TimeUtils.date2String(new Date(), TimeUtils.TIME_FORMAT));
					hdktEventCount.setOrgId(orgId);
					hdktEventCount.setEventId(eventId);
					hdktEventCount.setClassId(classId);
					hdktEventCount.setBiosSn(biosSn);
					hdktEventCount.setAccount(account);
					hdktEventCount.setChapterId(chapterId);
					hdktEventCount.setUserType(userType);
					hdktEventCount.setCityCode(cityCode);
					hdktEventCount.setProvinceId(provinceId);
					hdktEventCount.setTerminalVersion(terminalVersion);
					hdktEventCount.setCreateTime(TimeUtils.date2String(new Date(), TimeUtils.TIME_FORMAT));
					hdktEventCount.setOrigin(origin);
					changeEvent(hdktEventCount);

					DB db = mongoDBBaseDao.getMongoTemplate().getDb();
					Mongo mongo = db.getMongo();
					System.out.println(mongo.toString());
					System.out.println(db.toString());

					if (map.get("USE_COUNT") != "") {
						useCount = String.valueOf(map.get("USE_COUNT"));
					}
					int num = (int) Double.parseDouble(useCount);
					for (int i = 1; i <= num; i++) {
						mongoDBBaseDao.add(hdktEventCount);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("moveOver");

	}

	@Override
	public void saveHdktLoginLog() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startTime", "2018-05-26 20:00:00");
		param.put("endTime", "2018-05-26 20:30:00");
		List<Map<String, Object>> resultUseCountList = widgetLogDao.queryWidget(param);
		for (Map<String, Object> map : resultUseCountList) {
			String cpuSn = null;
			String classType = null;
			String loginPlatformCode = null;
			String courseNum = null;
			String cityName = null;
			String diskSn = null;
			String mac = null;
			String userId = null;
			String userName = null;
			String provinceName = null;
			String orgName = null;
			String subjectId = null;
			String editionId = null;
			String loginSource = null;
			String loginTaking = null;
			String orgId = null;
			String useTaking = null;
			String classId = null;
			String biosSn = null;
			String account = null;
			String chapterId = null;
			String userType = null;
			String cityCode = null;
			String provinceId = null;
			String terminalVersion = null;
			String origin = "1";
			String platformCode = null;

			if (map.get("CPU_SN") != null) {
				cpuSn = map.get("CPU_SN").toString();
			}
			if (map.get("CLASS_TYPE") != null) {
				classType = map.get("CLASS_TYPE").toString();
			}
			if (map.get("LOGIN_PLATFORM_CODE") != null) {
				loginPlatformCode = map.get("LOGIN_PLATFORM_CODE").toString();
			}
			if (map.get("COURSE_NUM") != null) {
				courseNum = map.get("COURSE_NUM").toString();
			}
			if (map.get("DISK_SN") != null) {
				diskSn = map.get("DISK_SN").toString();
			}
			if (map.get("BIOS_SN") != null) {
				biosSn = map.get("BIOS_SN").toString();
			}
			if (map.get("SUBJECT_ID") != null) {
				subjectId = map.get("SUBJECT_ID").toString();
			}
			if (map.get("EDITION_ID") != null) {
				editionId = map.get("EDITION_ID").toString();
			}
			if (map.get("LOGIN_SOURCE") != null) {
				orgId = map.get("LOGIN_SOURCE").toString();
			}
			if (map.get("LOGIN_TAKING") != null) {
				orgId = map.get("LOGIN_TAKING").toString();
			}
			if (map.get("ORG_ID") != null) {
				orgId = map.get("ORG_ID").toString();
			}
			if (map.get("USE_TAKING") != null) {
				orgId = map.get("USE_TAKING").toString();
			}
			if (map.get("CLASS_ID") != null) {
				classId = map.get("CLASS_ID").toString();
			}
			if (map.get("CHAPTER_ID") != null) {
				chapterId = map.get("CHAPTER_ID").toString();
			}
			if (map.get("USER_TYPE") != null) {
				userType = map.get("USER_TYPE").toString();
			}
			if (map.get("USER_ID") != null) {
				userId = map.get("USER_ID").toString();
			}
			if (map.get("TERMINAL_VERSION") != null) {
				terminalVersion = map.get("TERMINAL_VERSION").toString();
			}

			// provinceId = map.get("platform_code").toString();
			// proviceName = map.get("login_count").toString();
			// cityCode = map.get("create_time").toString();
			// cityName = map.get("org_id").toString();
			// orgName = map.get("platform_code").toString();
			// userName = map.get("create_time").toString();
			// account = map.get("platform_code").toString();
			if (map.get("PLATFORM_CODE") != null) {
				platformCode = map.get("PLATFORM_CODE").toString();
			}
			StringBuffer keySb = new StringBuffer();
			keySb.append(SysConstants.BASE_PROPERTY_CACHE_KEY).append(SysConstants.CACHE_KEY_SEPERATER)
					.append(CacheInit.AAM_URL_KEY).append(SysConstants.CACHE_KEY_SEPERATER).append(platformCode);
			System.out.println(keySb.toString());
			if ("33".equals(platformCode.substring(0, 2))) {
				platformCode = "330000";
			}
			String aamAddress = GetCacheBaseData.getPropertyValue("platform_url", platformCode);
			if (aamAddress == null) {
				aamAddress = basePropertyService.getPropertyValue("platform_url", platformCode);
			} else {
				String url = aamAddress + "user/" + userId;
				String resp = null;
				try {
					resp = HttpUtils.getInstance().httpGet(url, 3000, 3000);
					if (resp != null && !"".equals(resp)) {
						JSONObject respJson = JSONObject.fromObject(resp);

						if ("000000".equals(respJson.getString("result"))) {
							JSONObject userObject = (JSONObject) respJson.get("userinfo");
							provinceId = userObject.optString("provicecode");
							provinceName = userObject.optString("provicename");
							cityCode = userObject.optString("citycode");
							cityName = userObject.optString("cityname");
							userName = userObject.optString("name");
							orgName = userObject.optString("organame");
							account = userObject.optString("account");
						}
					}

					HdktLoginLog hdktLoginLog = new HdktLoginLog();
					hdktLoginLog.setCpuSn(cpuSn);
					hdktLoginLog.setClassType(classType);
					hdktLoginLog.setLoginPlatformCode(loginPlatformCode);
					hdktLoginLog.setCourseNum(courseNum);
					hdktLoginLog.setCityName(cityName);
					hdktLoginLog.setDiskSn(diskSn);
					hdktLoginLog.setMac(mac);
					hdktLoginLog.setUserId(userId);
					hdktLoginLog.setUserName(userName);
					hdktLoginLog.setOrgName(orgName);
					hdktLoginLog.setSubjectId(subjectId);
					hdktLoginLog.setEditionId(editionId);
					hdktLoginLog.setProvinceName(provinceName);
					hdktLoginLog.setLoginSource(loginSource);
					hdktLoginLog.setLoginTaking(loginTaking);
					hdktLoginLog.setOrgId(orgId);
					hdktLoginLog.setUseTaking(useTaking);
					hdktLoginLog.setClassId(classId);
					hdktLoginLog.setBiosSn(biosSn);
					hdktLoginLog.setAccount(account);
					hdktLoginLog.setChapterId(chapterId);
					hdktLoginLog.setUserType(userType);
					hdktLoginLog.setCityCode(cityCode);
					hdktLoginLog.setProvinceId(provinceId);
					hdktLoginLog.setTerminalVersion(terminalVersion);
					hdktLoginLog.setCreateTime(TimeUtils.date2String(new Date(), TimeUtils.TIME_FORMAT));
					hdktLoginLog.setOrigin(origin);

					DB db = mongoDBBaseDao.getMongoTemplate().getDb();
					Mongo mongo = db.getMongo();
					System.out.println(mongo.toString());
					System.out.println(db.toString());

					mongoDBBaseDao.add(hdktLoginLog);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("moveOver");

	}

	@Override
	public void saveHdktPageCount() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startTime", "2018-05-01");
		param.put("endTime", "2018-05-25");
		List<Map<String, Object>> resultUseCountList = widgetLogDao.queryWidget(param);
		for (Map<String, Object> map : resultUseCountList) {
			String cpuSn = null;
			String classType = null;
			String loginPlatformCode = null;
			String courseNum = null;
			String cityName = null;
			String diskSn = null;
			String mac = null;
			String pageType = null;
			String userId = null;
			String userName = null;
			String provinceName = null;
			String orgName = null;
			String subjectId = null;
			String editionId = null;
			String orgId = null;
			String pageNum = null;
			String classId = null;
			String biosSn = null;
			String account = null;
			String chapterId = null;
			String userType = null;
			String cityCode = null;
			String provinceId = null;
			String terminalVersion = null;
			String origin = "1";
			String platformCode = null;
			String useCount = null;

			if (map.get("CPU_SN") != null) {
				cpuSn = map.get("CPU_SN").toString();
			}
			if (map.get("CLASS_TYPE") != null) {
				classType = map.get("CLASS_TYPE").toString();
			}
			if (map.get("LOGIN_PLATFORM_CODE") != null) {
				loginPlatformCode = map.get("LOGIN_PLATFORM_CODE").toString();
			}
			if (map.get("COURSE_NUM") != null) {
				courseNum = map.get("COURSE_NUM").toString();
			}
			if (map.get("DISK_SN") != null) {
				diskSn = map.get("DISK_SN").toString();
			}
			if (map.get("BIOS_SN") != null) {
				biosSn = map.get("BIOS_SN").toString();
			}
			if (map.get("SUBJECT_ID") != null) {
				subjectId = map.get("SUBJECT_ID").toString();
			}
			if (map.get("EDITION_ID") != null) {
				editionId = map.get("EDITION_ID").toString();
			}
			if (map.get("LOGIN_SOURCE") != null) {
				orgId = map.get("LOGIN_SOURCE").toString();
			}
			if (map.get("LOGIN_TAKING") != null) {
				orgId = map.get("LOGIN_TAKING").toString();
			}
			if (map.get("ORG_ID") != null) {
				orgId = map.get("ORG_ID").toString();
			}
			if (map.get("USE_TAKING") != null) {
				orgId = map.get("USE_TAKING").toString();
			}
			if (map.get("CLASS_ID") != null) {
				classId = map.get("CLASS_ID").toString();
			}
			if (map.get("CHAPTER_ID") != null) {
				chapterId = map.get("CHAPTER_ID").toString();
			}
			if (map.get("USER_TYPE") != null) {
				userType = map.get("USER_TYPE").toString();
			}
			if (map.get("USER_ID") != null) {
				userId = map.get("USER_ID").toString();
			}
			if (map.get("TERMINAL_VERSION") != null) {
				terminalVersion = map.get("TERMINAL_VERSION").toString();
			}

			// provinceId = map.get("platform_code").toString();
			// proviceName = map.get("login_count").toString();
			// cityCode = map.get("create_time").toString();
			// cityName = map.get("org_id").toString();
			// orgName = map.get("platform_code").toString();
			// userName = map.get("create_time").toString();
			// account = map.get("platform_code").toString();
			if (map.get("PLATFORM_CODE") != null) {
				platformCode = map.get("PLATFORM_CODE").toString();
			}
			System.out.println(platformCode);
			StringBuffer keySb = new StringBuffer();
			keySb.append(SysConstants.BASE_PROPERTY_CACHE_KEY).append(SysConstants.CACHE_KEY_SEPERATER)
					.append(CacheInit.AAM_URL_KEY).append(SysConstants.CACHE_KEY_SEPERATER).append(platformCode);
			System.out.println(keySb.toString());

			String aamAddress = GetCacheBaseData.getPropertyValue("platform_url", platformCode);
			if (aamAddress == null) {
				aamAddress = basePropertyService.getPropertyValue("platform_url", platformCode);
			} else {
				String url = aamAddress + "user/" + userId;
				String resp = null;
				try {
					resp = HttpUtils.getInstance().httpGet(url, 3000, 3000);
					if (resp != null && !"".equals(resp)) {
						JSONObject respJson = JSONObject.fromObject(resp);

						if ("000000".equals(respJson.getString("result"))) {
							JSONObject userObject = (JSONObject) respJson.get("userinfo");
							provinceId = userObject.optString("provicecode");
							provinceName = userObject.optString("provicename");
							cityCode = userObject.optString("citycode");
							cityName = userObject.optString("cityname");
							userName = userObject.optString("name");
							orgName = userObject.optString("organame");
							account = userObject.optString("account");
						}
					}

					HdktLoginLog hdktLoginLog = new HdktLoginLog();
					hdktLoginLog.setCpuSn(cpuSn);
					hdktLoginLog.setClassType(classType);
					hdktLoginLog.setLoginPlatformCode(loginPlatformCode);
					hdktLoginLog.setCourseNum(courseNum);
					hdktLoginLog.setCityName(cityName);
					hdktLoginLog.setDiskSn(diskSn);
					hdktLoginLog.setMac(mac);
					hdktLoginLog.setUserId(userId);
					hdktLoginLog.setUserName(userName);
					hdktLoginLog.setOrgName(orgName);
					hdktLoginLog.setSubjectId(subjectId);
					hdktLoginLog.setEditionId(editionId);
					hdktLoginLog.setProvinceName(provinceName);
					hdktLoginLog.setOrgId(orgId);
					hdktLoginLog.setClassId(classId);
					hdktLoginLog.setBiosSn(biosSn);
					hdktLoginLog.setAccount(account);
					hdktLoginLog.setChapterId(chapterId);
					hdktLoginLog.setUserType(userType);
					hdktLoginLog.setCityCode(cityCode);
					hdktLoginLog.setProvinceId(provinceId);
					hdktLoginLog.setTerminalVersion(terminalVersion);
					hdktLoginLog.setCreateTime(TimeUtils.date2String(new Date(), TimeUtils.TIME_FORMAT));
					hdktLoginLog.setOrigin(origin);

					if (map.get("USE_COUNT") != "") {
						useCount = String.valueOf(map.get("USE_COUNT"));
					}
					int num = (int) Double.parseDouble(useCount);
					if (num <= 0) {
						continue;
					}
					for (int i = 1; i <= num; i++) {
						// mongoDBBaseDao.add(hdktLoginLog);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("moveOver");
	}

	@Override
	public void saveHdktTerminalLinkCount() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startTime", "2018-05-26 20:00:00");
		param.put("endTime", "2018-05-26 23:00:00");
		List<Map<String, Object>> resultUseCountList = widgetLogDao.queryTerminal(param);
		for (Map<String, Object> map : resultUseCountList) {
			String cpuSn = null;
			String classType = null;
			String loginPlatformCode = null;
			String courseNum = null;
			String cityName = null;
			String diskSn = null;
			String mac = null;
			String linkNum = null;
			String userId = null;
			String userName = null;
			String orgName = null;
			String subjectId = null;
			String editionId = null;
			String provinceName = null;
			String orgId = null;
			String terminalType = null;
			String classId = null;
			String biosSn = null;
			String account = null;
			String chapterId = null;
			String userType = null;
			String cityCode = null;
			String provinceId = null;
			String terminalVersion = null;
			String origin = "1";
			String platformCode = null;

			if (map.get("CLASS_TYPE") != null) {
				classType = map.get("CLASS_TYPE").toString();
			}
			if (map.get("COURSE_NUM") != null) {
				courseNum = map.get("COURSE_NUM").toString();
			}
			if (map.get("CLASS_ID") != null) {
				classId = map.get("CLASS_ID").toString();
			}
			if (map.get("LINK_NUM") != null) {
				linkNum = map.get("LINK_NUM").toString();
			}
			if (map.get("TERMINAL_TYPE") != null) {
				terminalType = map.get("TERMINAL_TYPE").toString();
			}
			if (map.get("ORG_ID") != null) {
				orgId = map.get("ORG_ID").toString();
			}
			if (map.get("USER_ID") != null) {
				userId = map.get("USER_ID").toString();
			}
			if (map.get("HDKT_VERSION") != null) {
				terminalVersion = map.get("HDKT_VERSION").toString();
			}

			// provinceId = map.get("platform_code").toString();
			// proviceName = map.get("login_count").toString();
			// cityCode = map.get("create_time").toString();
			// cityName = map.get("org_id").toString();
			// orgName = map.get("platform_code").toString();
			// userName = map.get("create_time").toString();
			// account = map.get("platform_code").toString();
			if (map.get("PLATFORM_CODE") != null) {
				platformCode = map.get("PLATFORM_CODE").toString();
			}
			System.out.println(platformCode);
			StringBuffer keySb = new StringBuffer();
			keySb.append(SysConstants.BASE_PROPERTY_CACHE_KEY).append(SysConstants.CACHE_KEY_SEPERATER)
					.append(CacheInit.AAM_URL_KEY).append(SysConstants.CACHE_KEY_SEPERATER).append(platformCode);
			System.out.println(keySb.toString());
			if ("33".equals(platformCode.substring(0, 2))) {
				platformCode = "330000";
			}
			String aamAddress = GetCacheBaseData.getPropertyValue("platform_url", platformCode);
			if (aamAddress == null) {
				aamAddress = basePropertyService.getPropertyValue("platform_url", platformCode);
			} else {
				String url = aamAddress + "user/" + userId;
				String resp = null;
				try {
					resp = HttpUtils.getInstance().httpGet(url, 3000, 3000);
					if (resp != null && !"".equals(resp)) {
						JSONObject respJson = JSONObject.fromObject(resp);

						if ("000000".equals(respJson.getString("result"))) {
							JSONObject userObject = (JSONObject) respJson.get("userinfo");
							provinceId = userObject.optString("provicecode");
							provinceName = userObject.optString("provicename");
							cityCode = userObject.optString("citycode");
							cityName = userObject.optString("cityname");
							userName = userObject.optString("name");
							orgName = userObject.optString("organame");
							account = userObject.optString("account");
						}
					}

					HdktTerminalLinkCount hdktTerminalLinkCount = new HdktTerminalLinkCount();
					hdktTerminalLinkCount.setCpuSn(cpuSn);
					hdktTerminalLinkCount.setClassType(classType);
					hdktTerminalLinkCount.setLoginPlatformCode(loginPlatformCode);
					hdktTerminalLinkCount.setCourseNum(courseNum);
					hdktTerminalLinkCount.setCityName(cityName);
					hdktTerminalLinkCount.setDiskSn(diskSn);
					hdktTerminalLinkCount.setMac(mac);
					hdktTerminalLinkCount.setLinkNum(linkNum);
					hdktTerminalLinkCount.setUserId(userId);
					hdktTerminalLinkCount.setUserName(userName);
					hdktTerminalLinkCount.setOrgName(orgName);
					hdktTerminalLinkCount.setSubjectId(subjectId);
					hdktTerminalLinkCount.setEditionId(editionId);
					hdktTerminalLinkCount.setProvinceName(provinceName);
					hdktTerminalLinkCount.setOrgId(orgId);
					hdktTerminalLinkCount.setTerminalType(terminalType);
					hdktTerminalLinkCount.setClassId(classId);
					hdktTerminalLinkCount.setBiosSn(biosSn);
					hdktTerminalLinkCount.setAccount(account);
					hdktTerminalLinkCount.setChapterId(chapterId);
					hdktTerminalLinkCount.setUserType(userType);
					hdktTerminalLinkCount.setCityCode(cityCode);
					hdktTerminalLinkCount.setProvinceId(provinceId);
					hdktTerminalLinkCount.setTerminalVersion(terminalVersion);
					hdktTerminalLinkCount.setCreateTime(TimeUtils.date2String(new Date(), TimeUtils.TIME_FORMAT));
					hdktTerminalLinkCount.setOrigin(origin);

					DB db = mongoDBBaseDao.getMongoTemplate().getDb();
					Mongo mongo = db.getMongo();
					System.out.println(mongo.toString());
					System.out.println(db.toString());

					mongoDBBaseDao.add(hdktTerminalLinkCount);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("moveOver");

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
}
