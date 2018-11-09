package com.whty.ebp.manage.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.common.util.CommonFunction;
import com.whty.common.util.GUIDGenerator;
import com.whty.ebp.manage.dao.UserOptLogDao;
import com.whty.ebp.manage.model.UserOptLog;
import com.whty.ebp.manage.service.UserOptLogService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class UserOptLogServiceImpl implements UserOptLogService {
	private static final Logger logger = LoggerFactory.getLogger(UserOptLogServiceImpl.class);
	@Autowired
	private UserOptLogDao userOptLogDao;

	@Override
	public void saveUserOptLog(Map<String, Object> map) {

		JSONArray typesArray = (JSONArray) map.get("data");

		String batchNum = GUIDGenerator.getGUID();// 批次编号
		logger.info(typesArray.toString());
		for (int i = 0; i < typesArray.size(); i++) {
			JSONObject jsonObject = typesArray.getJSONObject(i);
			UserOptLog bean = new UserOptLog();
			bean.setId(GUIDGenerator.getGUID());
			bean.setUserId(map.get("userId").toString());

			if (map.get("platformCode") != null) {
				bean.setPlatformCode(map.get("platformCode").toString());
			}

			bean.setLoginPlatformCode(map.get("loginPlatformCode").toString());
			bean.setDataType(jsonObject.getString("dataType"));
			bean.setUserSessionId(map.get("userSessionId").toString());
			bean.setNum(Integer.valueOf(jsonObject.getString("num")));
			bean.setBatch(batchNum);
			bean.setApplyVersion(map.get("applyVersion").toString());
			bean.setExceptionInfo(map.get("exceptionInfo").toString());
			bean.setCourseNum(map.get("courseNum").toString());

			
//			if("14".equals(jsonObject.getString("dataType"))){
//				
//				if ("33".equals(bean.getPlatformCode().substring(0, 2))) {
//				
//
//					String jiFen = SysConfig.getStrValue("zj.jifen.shouke.action.login");
//				
//
//					String urltt = SysConfig.getStrValue("jifen.shouke");
//					Map<String, Object> paramtt = new HashMap<String, Object>();
//					paramtt.put("usercode",bean.getUserId());
//					paramtt.put("typecode", jiFen);
//					paramtt.put("comeFrom", "1");
//					paramtt.put("checkTypeDesc", "移动授课");
//					paramtt.put("relatedId", SysConfig.getStrValue("zj.app.code"));
//					logger.info(paramtt.toString() + "添加积分");
//
//					try {
//						String resulttt;
//						
//						resulttt = HttpUtils.getInstance().httpPost(urltt, JSONObject.fromObject(paramtt).toString());
//						System.out.println(resulttt);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//				
//			}
			
			
			try {
				if (map.get("exceptionTime").toString().contains("_")) {
					String[] exceptionTimeArr = map.get("exceptionTime").toString().split("_");
					String exceptionTimeStr = exceptionTimeArr[0] + "-" + exceptionTimeArr[1] + "-"
							+ exceptionTimeArr[2];
					Date startTime = CommonFunction.getSampleDate(exceptionTimeStr);
					bean.setExceptionTime(startTime);
				} else {
					Date exceptionTime = CommonFunction.getSampleTimeString(map.get("exceptionTime").toString());
					bean.setExceptionTime(exceptionTime);
				}
			} catch (Exception e) {
				String[] exceptionTimeArr = map.get("exceptionTime").toString().split("_");
				String exceptionTimeStr = exceptionTimeArr[0] + "-" + exceptionTimeArr[1] + "-" + exceptionTimeArr[2];
				Date startTime = CommonFunction.getSampleDate(exceptionTimeStr);
				bean.setExceptionTime(startTime);
			}

			bean.setMobileBrand(map.get("mobileBrand").toString());
			bean.setMobileID(map.get("mobileID").toString());
			bean.setSystemVersion(map.get("systemVersion").toString());
			bean.setTerminalType(map.get("terminalType").toString());

			try {
				if (map.get("startTime").toString().contains("_")) {
					String[] startTimeArr = map.get("startTime").toString().split("_");
					String strTimeStr = startTimeArr[0] + "-" + startTimeArr[1] + "-" + startTimeArr[2];
					Date startTime = CommonFunction.getSampleDate(strTimeStr);
					bean.setStartTime(startTime);
				} else {
					Date startTime = CommonFunction.getSampleDate(map.get("startTime").toString());
					bean.setStartTime(startTime);
				}
			} catch (Exception e) {
				String[] startTimeArr = map.get("startTime").toString().split("_");
				String strTimeStr = startTimeArr[0] + "-" + startTimeArr[1] + "-" + startTimeArr[2];
				Date startTime = CommonFunction.getSampleDate(strTimeStr);
				bean.setStartTime(startTime);
				// e.printStackTrace();
			}
			try {
				if (map.get("endTime").toString().contains("_")) {
					String[] endTimeArr = map.get("startTime").toString().split("_");
					String endTimeStr = endTimeArr[0] + "-" + endTimeArr[1] + "-" + endTimeArr[2];
					// System.out.println(endTimeStr);
					Date endTime = CommonFunction.getSampleDate(endTimeStr);
					bean.setEndTime(endTime);
				} else {
					Date endTime = CommonFunction.getSampleDate(map.get("endTime").toString());
					bean.setEndTime(endTime);
				}
			} catch (Exception e) {
				String[] endTimeArr = map.get("startTime").toString().split("_");
				String endTimeStr = endTimeArr[0] + "-" + endTimeArr[1] + "-" + endTimeArr[2];
				// System.out.println(endTimeStr);
				Date endTime = CommonFunction.getSampleDate(endTimeStr);
				bean.setEndTime(endTime);
				// e.printStackTrace();
			}
			userOptLogDao.saveUserOptLog(bean);
		}
	}

	@Override
	public List<UserOptLog> getDataNum(Map map) {
		String endDateStr = map.get("myDate").toString();
		Date endDate = CommonFunction.getSampleTimeString(endDateStr);

		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);

		String startDateStr = CommonFunction.getTimeSampleString(cal.getTime());

		map.put("startDateStr", startDateStr);
		map.put("endDateStr", endDateStr);

		// List<UserOptLog> ss = userOptLogDao.test();

		List<UserOptLog> s = userOptLogDao.getDataNum(map);

		return s;
	}

	@Override
	public List<UserOptLog> getUserCount(Map map) {
		return userOptLogDao.getUserCount(map);
	}

	@Override
	public List<UserOptLog> getDataNum2(Map map) {
		String endDateStr = map.get("myDate").toString();
		Date endDate = CommonFunction.getSampleTimeString(endDateStr);

		Calendar cal = Calendar.getInstance();
		cal.setTime(endDate);

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);

		String startDateStr = CommonFunction.getTimeSampleString(cal.getTime());

		map.put("startDateStr", startDateStr);
		map.put("endDateStr", endDateStr);

		// List<UserOptLog> ss = userOptLogDao.test();

		List<UserOptLog> s = userOptLogDao.getDataNum2(map);

		return s;
	}
}
