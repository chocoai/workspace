package com.whty.assis.demo.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.whty.common.util.*;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.whty.assis.api.model.LocalNetworkInfo;
import com.whty.assis.api.model.ScanCodeUserInfo;
import com.whty.assis.api.utils.HttpUtils;
import com.whty.assis.demo.dao.AreaDao;
import com.whty.assis.demo.dao.LoginHistoryDao;
import com.whty.assis.demo.dao.TaUserDao;
import com.whty.assis.demo.dao.TerminalUseHistoryDao;
import com.whty.assis.demo.dao.WidgetHistoryDao;
import com.whty.assis.demo.model.AreaMonthActivityCount;
import com.whty.assis.demo.model.AreaMonthUsageCount;
import com.whty.assis.demo.model.AreaMonthUseTakingCount;
import com.whty.assis.demo.model.LoginHistory;
import com.whty.assis.demo.model.OrgMonthActivityCount;
import com.whty.assis.demo.model.OrgMonthUsageCount;
import com.whty.assis.demo.model.OrgMonthUseTakingCount;
import com.whty.assis.demo.model.TerminalUseHistory;
import com.whty.assis.demo.model.TerminalUseLog;
import com.whty.assis.demo.model.UserMonthActivityCount;
import com.whty.assis.demo.model.UserMonthUsageCount;
import com.whty.assis.demo.model.UserMonthUseTakingCount;
import com.whty.assis.demo.model.WidgetHistory;
import com.whty.assis.demo.model.WidgetLog;
import com.whty.assis.demo.service.LoginHistoryService;
import com.whty.assis.manage.service.BasePropertyService;
import com.whty.common.cache.data.GetCacheBaseData;
import com.whty.common.mongodb.MongoDBBaseDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class LoginHistoryServiceImpl implements LoginHistoryService {

	private static final Log logger = LogFactory.getLog(LoginHistoryServiceImpl.class);
	@Resource(name = "mongoDBBaseDao")
	MongoDBBaseDao mongoDBBaseDao;
	@Autowired
	private LoginHistoryDao loginHistoryDao;

	@Autowired
	private TaUserDao taUserDao;

	@Autowired
	private AreaDao areaDao;

	@Autowired
	private WidgetHistoryDao widgetHistoryDao;

	@Autowired
	private TerminalUseHistoryDao terminalUseHistoryDao;

	@Autowired
	private BasePropertyService basePropertyService;

	@Override
	public List<LoginHistory> saveWidgetLogToHistory(List<WidgetLog> widgetLogList) {

		List<LoginHistory> list = new ArrayList<LoginHistory>();
		// 将登录数据同步到历史表
		for (WidgetLog bean : widgetLogList) {
			LoginHistory loginHistory = new LoginHistory();

			List<String> idList = new ArrayList<String>();
			String aamAddress = GetCacheBaseData.getPropertyValue("platform_url", bean.getPlatformCode());

			if (aamAddress == null) {
				aamAddress = basePropertyService.getPropertyValue("platform_url", bean.getPlatformCode());
			}

			String url = aamAddress + "user/" + bean.getUserId();

			String resp;
			try {
				resp = HttpUtils.getInstance().httpGet(url, 30000, 30000);
				// System.out.println(resp);
				if (logger.isDebugEnabled()) {
					logger.debug("信息返回:" + resp);
				}

				if (resp != null && !"".equals(resp)) {
					JSONObject respJson = JSONObject.fromObject(resp);
					if ("000000".equals(respJson.getString("result"))) {
						JSONObject userObject = (JSONObject) respJson.get("userinfo");

						// loginHistory.setAreaCode(
						// userObject.get("areacode") == null ? "" :
						// userObject.get("areacode").toString().trim());

						if (userObject.get("orgaid") != null && !"".equals(userObject.get("orgaid"))) {
							// String ograAddressUrl = aamAddress + "ogra/get";
							// List<String> ograList = new ArrayList<String>();
							// ograList.add(userObject.get("orgaid").toString());

							// Map<String, Object> map = new HashMap<String,
							// Object>();

							// map.put("orgaids", ograList);

							// String ograResp =
							// HttpUtils.getInstance().httpPost(ograAddressUrl,
							// JsonUtils.objTojson(map));
							// System.err.println(ograResp);
							// if (ograResp != null && !"".equals(ograResp)) {
							// JSONObject OgraRespJson =
							// JSONObject.fromObject(ograResp);
							// if
							// ("000000".equals(OgraRespJson.getString("result")))
							// {
							// JSONArray orgList =
							// OgraRespJson.getJSONArray("orgalist");
							//
							// JSONObject orgObject = (JSONObject)
							// orgList.get(0);
							//
							// if
							// (!orgObject.optString("areacode").equals(userObject.optString("citycode")))
							// {
							// System.out.println(userObject.optString("citycode")
							// + ":"
							// + orgObject.optString("areacode"));
							// }
							//
							// loginHistory.setAreaCode(orgObject.get("areacode")
							// == null ? ""
							// : orgObject.get("areacode").toString());
							//
							// }
							// }

						}
						if (userObject.optString("citycode") == null) {
							System.err.println("空了");
						}
						loginHistory.setAreaCode(userObject.optString("citycode"));

						loginHistory.setClassId(bean.getClassId());
						loginHistory.setClassType(bean.getClassType());
						loginHistory.setPlatformCode(bean.getPlatformCode());
						loginHistory.setId(GUIDGenerator.getUUID32());
						loginHistory
								.setOrgId(userObject.get("orgaid") == null ? "" : userObject.get("orgaid").toString());
						loginHistory.setOrgName(
								userObject.get("organame") == null ? "" : userObject.get("organame").toString());
						loginHistory.setRunNumber(bean.getUseCount());
						loginHistory.setUserId(bean.getUserId());
						loginHistory
								.setUserName(userObject.get("name") == null ? "" : userObject.get("name").toString());
						loginHistory.setLoginSource(bean.getLoginSource());
						loginHistory.setLoginTaking(bean.getLoginTaking());
						loginHistory.setCourseNum(bean.getCourseNum());
						// TODO 临时写死
						// Calendar cal = Calendar.getInstance();
						// cal.add(Calendar.MONTH, -1);
						//
						// loginHistory.setCreateTime(cal.getTime());// 保存上月的时间

						loginHistory.setCreateTime(new Date());
						loginHistory.setUseTaking(bean.getUseTaking());

						loginHistoryDao.addLoginHistory(loginHistory);

						list.add(loginHistory);
						idList.add(bean.getId());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 更新同步后的数据
			Map<String, Object> paramap = new HashMap<String, Object>();
			paramap.put("idList", idList);
			paramap.put("transfer", "1");
			if (idList.size() > 0) {
				taUserDao.updateWidgetLog(paramap);
			}
		}

		return list;
	}

	@Override
	public List<AreaMonthUsageCount> getAreaMonthUsageCount(Map<String, Object> param) {
		return loginHistoryDao.getAreaMonthUsageCount(param);
	}

	public List<AreaMonthActivityCount> getAreaMonthActivityCount(Map<String, Object> param) {
		return loginHistoryDao.getAreaMonthActivityCount(param);
	}

	public List<LoginHistory> getTeacherUseCountByCurrentMonth() {
		Map<String, Object> parm = new HashMap<String, Object>();

		Date startTime = TimeUtil.getCurrentMonthStartDate();
		Date endTime = TimeUtil.getCurrentMonthEndDate();

		parm.put("startTime", CommonFunction.getDateSampleString(startTime));
		parm.put("endTime", CommonFunction.getDateSampleString(endTime));

		// parm.put("startTime", "2016-11-01");
		// parm.put("endTime", "2016-11-31");

		return loginHistoryDao.getTeacherUseCountByCurrentMonth(parm);

	}

	public List<LoginHistory> getAreaUseCountByCurrentMonth() {
		Map<String, Object> parm = new HashMap<String, Object>();

		Date startTime = TimeUtil.getCurrentMonthStartDate();
		Date endTime = TimeUtil.getCurrentMonthEndDate();

		parm.put("startTime", CommonFunction.getDateSampleString(startTime));
		parm.put("endTime", CommonFunction.getDateSampleString(endTime));

		// parm.put("startTime", "2016-11-01");
		// parm.put("endTime", "2016-11-31");

		return loginHistoryDao.getAreaUseCountByCurrentMonth(parm);

	}

	@Override
	public List<LoginHistory> getOrgUseCountByCurrentMonth() {
		Map<String, Object> parm = new HashMap<String, Object>();

		Date startTime = TimeUtil.getCurrentMonthStartDate();
		Date endTime = TimeUtil.getCurrentMonthEndDate();

		parm.put("startTime", CommonFunction.getDateSampleString(startTime));
		parm.put("endTime", CommonFunction.getDateSampleString(endTime));

		// parm.put("startTime", "2016-11-01");
		// parm.put("endTime", "2016-11-31");

		return loginHistoryDao.getOrgUseCountByCurrentMonth(parm);
	}

	@Override
	public void updateAreaMonthUsageCount(AreaMonthUsageCount bean) {
		loginHistoryDao.updateAreaMonthUsageCount(bean);
	}

	@Override
	public void saveAreaMonthUsageCount(AreaMonthUsageCount bean) {
		loginHistoryDao.saveAreaMonthUsageCount(bean);
	}

	public void saveAreaMonthActivityCount(AreaMonthActivityCount bean) {
		loginHistoryDao.saveAreaMonthActivityCount(bean);
	}

	public void updateAreaMonthActivityCount(AreaMonthActivityCount bean) {
		loginHistoryDao.updateAreaMonthActivityCount(bean);
	}

	public List<OrgMonthUsageCount> getOrgMonthUsageCount(Map<String, Object> param) {
		return loginHistoryDao.getOrgMonthUsageCount(param);
	}

	public List<OrgMonthActivityCount> getOrgMonthActivityCount(Map<String, Object> param) {
		return loginHistoryDao.getOrgMonthActivityCount(param);
	}

	public void saveOrgMonthUsageCount(OrgMonthUsageCount bean) {
		loginHistoryDao.saveOrgMonthUsageCount(bean);
	}

	public void updateOrgMonthUsageCount(OrgMonthUsageCount bean) {
		loginHistoryDao.updateOrgMonthUsageCount(bean);
	}

	public void saveOrgMonthActivityCount(OrgMonthActivityCount bean) {
		loginHistoryDao.saveOrgMonthActivityCount(bean);
	}

	public void updateOrgMonthActivityCount(OrgMonthActivityCount bean) {
		loginHistoryDao.updateOrgMonthActivityCount(bean);
	}

	/**
	 * 统计任务
	 */
	@Override
	public void countTask() {
		// 得到当月未统计的用户
		Map<String, Object> parm = new HashMap<String, Object>();
		Date startTime = TimeUtil.getCurrentMonthStartDate();
		Date endTime = TimeUtil.getCurrentMonthEndDate();

		parm.put("startTime", CommonFunction.getDateSampleString(startTime));
		parm.put("endTime", CommonFunction.getDateSampleString(endTime));

		// parm.put("startTime", "2017-04-20");
		// parm.put("endTime", "2017-04-20");

		List<WidgetLog> widgetLogList = taUserDao.getWidgetLogNotLoginTransfer(parm);

		// // 保存登录日志到历史库
		List<LoginHistory> loginHistoryList = saveWidgetLogToHistory(widgetLogList);

		// List<UserMonthUsageCount> userCountList = countUserNum3();
		// countOrgNum3(userCountList);
		// countAreaNum3(userCountList);
		//
		// List<UserMonthActivityCount> userActivityList = countUserActivity3();
		// countOrgActivity3();
		// countAreaActivity3();

		// for (String s : DataController.set) {
		// System.out.println(s);
		// }

		// 使用次数
		countAreaNum();
		countOrgNum();
		countUserNum();

		// 登录时长 命名有歧义
		countUserActivity();
		countOrgActivity2();
		countAreaActivity2();

		// 使用时长
		countAreaUseTaking();
		countOrgUseTaking();
		countUserUseTaking();

		// countOrgNum2();

		// countAreaActivity();
		// countOrgActivity();

		// 保存数据报历史表
		saveWidgetLogToHistory();

		saveTerminalUseHistory();

		// 保存日志到历史表
		saveLoingHistoryToFiling(loginHistoryList);
	}

	// public void countOrgNum3(List<UserMonthUsageCount> userCountList){
	//
	// }

	// -----------------------------------------------使用时长 start
	// -------------------------------------------
	/**
	 * 统计用户使用时长,活跃度
	 */
	private void countUserUseTaking() {
		// 获得区域当月使用次数
		List<LoginHistory> loginHistoryList = getUserUseTakingByCurrentMonth();// 当月使用时长

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		// int year = 2016;
		// int month = 11;

		for (LoginHistory bean : loginHistoryList) {

			// 找到学校对应的月统计信息
			Map<String, Object> param = new HashMap<String, Object>();
			// param.put("orgId", bean.getOrgId());

			param.put("areaCode", bean.getAreaCode());
			param.put("year", year);
			param.put("month", month);
			param.put("platformCode", bean.getPlatformCode());
			param.put("orgId", bean.getOrgId());
			param.put("orgName", bean.getOrgName());
			param.put("userId", bean.getUserId());
			param.put("userName", bean.getUserName());
			List<UserMonthUseTakingCount> countList = getUserMonthUseTakingCount(param);
			saveOrUpdateUserMonthUseTakingCount(countList, bean, year, month);
		}
	}

	private List<UserMonthUseTakingCount> getUserMonthUseTakingCount(Map<String, Object> param) {
		return loginHistoryDao.getUserMonthUseTakingCount(param);
	}

	private void saveOrUpdateUserMonthUseTakingCount(List<UserMonthUseTakingCount> countList, LoginHistory loginHistory,
			int year, int month) {

		if (countList != null && countList.size() > 0) {// 更新操作
			UserMonthUseTakingCount bean = countList.get(0);

			if (bean.getUserId().equals(loginHistory.getUserId()) && bean.getOrgId().equals(loginHistory.getOrgId())) {

				if (bean.getUseTaking() == 0) {
					bean.setUseTaking(0);
				}

				if (loginHistory.getUseTaking() == 0) {
					loginHistory.setUseTaking(0);
				}

				bean.setUseTaking(bean.getUseTaking() + loginHistory.getUseTaking());
				// TODO 重新计算增长率
				if (bean.getPreviousUseTaking() != 0) {
					long rateNum = bean.getUseTaking() - bean.getPreviousUseTaking();
					BigDecimal bigDecimal = new BigDecimal(rateNum);
					BigDecimal bigDecimal2 = new BigDecimal(bean.getPreviousUseTaking());

					String rate = calculateRate(bigDecimal, bigDecimal2);
					bean.setRate(rate);
				}
				updateUserMonthUseTakingCount(bean);
			}
		} else {// 直接保存
			UserMonthUseTakingCount bean = new UserMonthUseTakingCount();

			if (loginHistory.getUseTaking() == 0) {
				bean.setUseTaking(0);
			} else {
				bean.setUseTaking(loginHistory.getUseTaking());
			}

			bean.setAreaCode(loginHistory.getAreaCode());
			bean.setMonth(month);
			bean.setYear(year);

			bean.setPlatformCode(loginHistory.getPlatformCode());
			bean.setCreateTime(new Date());
			bean.setUserId(loginHistory.getUserId());
			bean.setUserName(loginHistory.getUserName());
			bean.setOrgId(loginHistory.getOrgId());
			bean.setOrgName(loginHistory.getOrgName());

			// 找到上月的使用次数
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);

			Map<String, Object> param = new HashMap<String, Object>();
			// 找到这个学校上月的数据

			param.put("areaCode", bean.getAreaCode());
			param.put("year", cal.get(Calendar.YEAR));
			param.put("month", cal.get(Calendar.MONTH) + 1);

			// param.put("year", 2016);
			// param.put("month", 10);

			param.put("platformCode", bean.getPlatformCode());
			param.put("orgId", bean.getOrgId());
			// 找到上月本区域的数据
			List<AreaMonthUseTakingCount> privBean = getAreaMonthUseTakingCount(param);
			// 如果找到则计算增长率，否则设置增长率为0
			if (privBean != null && privBean.size() > 0) {

				if (privBean.get(0).getUseTaking() == 0) {
					privBean.get(0).setUseTaking(0);
				}

				if (loginHistory.getUseTaking() == 0) {
					loginHistory.setUseTaking(0);
				}

				bean.setPreviousUseTaking((privBean.get(0).getUseTaking()));
				/**
				 * 
				 * 增长的部分除以原本的数目就是增长率。一般用百分比表示。
				 * 比如现在的某城市今年年初人口1000万，年末1006万，那么增长率就是增长的6万除以1000万，增长率=6/1000=0.
				 * 6%
				 */
				bean.setPreviousUseTaking(privBean.get(0).getUseTaking());
				long rateNum = loginHistory.getUseTaking() - privBean.get(0).getUseTaking();
				BigDecimal bigDecimal = new BigDecimal(rateNum);
				BigDecimal bigDecimal2 = new BigDecimal(privBean.get(0).getUseTaking());

				String rate = calculateRate(bigDecimal, bigDecimal2);
				bean.setRate(rate);
			} else {
				bean.setRate("0");
			}
			saveUserMonthUseTakingCount(bean);
		}
	}

	public void updateUserMonthUseTakingCount(UserMonthUseTakingCount bean) {
		loginHistoryDao.updateUserMonthUseTakingCount(bean);
	}

	public void saveUserMonthUseTakingCount(UserMonthUseTakingCount bean) {
		loginHistoryDao.saveUserMonthUseTakingCount(bean);
	}

	/**
	 * 统计学校使用时长，活跃度
	 */
	private void countOrgUseTaking() {
		// 获得区域当月使用次数
		List<LoginHistory> loginHistoryList = getOrgUseTakingByCurrentMonth();// 当月使用时长

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		// int year = 2016;
		// int month = 11;

		for (LoginHistory bean : loginHistoryList) {

			// 找到学校对应的月统计信息
			Map<String, Object> param = new HashMap<String, Object>();
			// param.put("orgId", bean.getOrgId());

			param.put("areaCode", bean.getAreaCode());
			param.put("year", year);
			param.put("month", month);
			param.put("platformCode", bean.getPlatformCode());
			param.put("orgId", bean.getOrgId());
			param.put("orgName", bean.getOrgName());

			List<OrgMonthUseTakingCount> countList = getOrgMonthUseTakingCount(param);
			saveOrUpdateOrgMonthUseTakingCount(countList, bean, year, month);
		}

	}

	public List<OrgMonthUseTakingCount> getOrgMonthUseTakingCount(Map<String, Object> param) {
		return loginHistoryDao.getOrgMonthUseTakingCount(param);
	}

	public void saveOrUpdateOrgMonthUseTakingCount(List<OrgMonthUseTakingCount> countList, LoginHistory loginHistory,
			int year, int month) {
		if (countList != null && countList.size() > 0) {// 更新操作
			OrgMonthUseTakingCount bean = countList.get(0);

			if (bean.getAreaCode() != null && bean.getPlatformCode() != null && loginHistory.getAreaCode() != null
					&& loginHistory.getPlatformCode() != null && bean.getOrgId() != null
					&& loginHistory.getOrgId() != null) {

				if (bean.getOrgId().equals(loginHistory.getOrgId())
						&& bean.getAreaCode().equals(loginHistory.getAreaCode())
						&& bean.getPlatformCode().equals(loginHistory.getPlatformCode())) {

					if (bean.getUseTaking() == 0) {
						bean.setUseTaking(0);
					}

					if (loginHistory.getUseTaking() == 0) {
						loginHistory.setUseTaking(0);
					}

					bean.setUseTaking(bean.getUseTaking() + loginHistory.getUseTaking());
					// TODO 重新计算增长率
					if (bean.getPreviousUseTaking() != 0) {
						long rateNum = bean.getUseTaking() - bean.getPreviousUseTaking();
						BigDecimal bigDecimal = new BigDecimal(rateNum);
						BigDecimal bigDecimal2 = new BigDecimal(bean.getPreviousUseTaking());

						String rate = calculateRate(bigDecimal, bigDecimal2);
						bean.setRate(rate);
					}
					updateOrgMonthUseTakingCount(bean);
				}
			}
		} else {// 直接保存
			OrgMonthUseTakingCount bean = new OrgMonthUseTakingCount();

			if (loginHistory.getUseTaking() == 0) {
				bean.setUseTaking(0);
			} else {
				bean.setUseTaking(loginHistory.getUseTaking());
			}

			bean.setAreaCode(loginHistory.getAreaCode());
			bean.setMonth(month);
			bean.setYear(year);

			bean.setPlatformCode(loginHistory.getPlatformCode());
			bean.setCreateTime(new Date());
			bean.setOrgId(loginHistory.getOrgId());
			bean.setOrgName(loginHistory.getOrgName());
			// 找到上月的使用次数
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);

			Map<String, Object> param = new HashMap<String, Object>();
			// 找到这个学校上月的数据

			param.put("areaCode", bean.getAreaCode());
			param.put("year", cal.get(Calendar.YEAR));
			param.put("month", cal.get(Calendar.MONTH) + 1);

			// param.put("year", 2016);
			// param.put("month", 10);

			param.put("platformCode", bean.getPlatformCode());
			param.put("orgId", bean.getOrgId());
			// 找到上月本区域的数据
			List<AreaMonthUseTakingCount> privBean = getAreaMonthUseTakingCount(param);
			// 如果找到则计算增长率，否则设置增长率为0
			if (privBean != null && privBean.size() > 0) {

				if (privBean.get(0).getUseTaking() == 0) {
					privBean.get(0).setUseTaking(0);
				}

				if (loginHistory.getUseTaking() == 0) {
					loginHistory.setUseTaking(0);
				}

				bean.setPreviousUseTaking((privBean.get(0).getUseTaking()));
				/**
				 * 
				 * 增长的部分除以原本的数目就是增长率。一般用百分比表示。
				 * 比如现在的某城市今年年初人口1000万，年末1006万，那么增长率就是增长的6万除以1000万，增长率=6/1000=0.
				 * 6%
				 * 
				 */
				bean.setPreviousUseTaking(privBean.get(0).getUseTaking());
				long rateNum = loginHistory.getUseTaking() - privBean.get(0).getUseTaking();
				BigDecimal bigDecimal = new BigDecimal(rateNum);
				BigDecimal bigDecimal2 = new BigDecimal(privBean.get(0).getUseTaking());

				String rate = calculateRate(bigDecimal, bigDecimal2);
				bean.setRate(rate);
			} else {
				bean.setRate("0");
			}
			saveOrgMonthUseTakingCount(bean);
		}
	}

	private void updateOrgMonthUseTakingCount(OrgMonthUseTakingCount bean) {
		loginHistoryDao.updateOrgMonthUseTakingCount(bean);
	}

	private void saveOrgMonthUseTakingCount(OrgMonthUseTakingCount bean) {
		loginHistoryDao.saveOrgMonthUseTakingCount(bean);
	}

	/**
	 * 统计区域使用时长,活跃度
	 */
	private void countAreaUseTaking() {

		// 获得区域当月使用次数
		List<LoginHistory> loginHistoryList = getOrgUseTakingByCurrentMonth();// 当月使用时长

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		// int year = 2016;
		// int month = 11;

		for (LoginHistory bean : loginHistoryList) {
			// 找到学校对应的月统计信息
			Map<String, Object> param = new HashMap<String, Object>();
			// param.put("orgId", bean.getOrgId());
			param.put("areaCode", bean.getAreaCode());
			param.put("year", year);
			param.put("month", month);
			param.put("platformCode", bean.getPlatformCode());
			param.put("orgId", bean.getOrgId());
			param.put("orgName", bean.getOrgName());
			List<AreaMonthUseTakingCount> countList = getAreaMonthUseTakingCount(param);
			saveOrUpdateAreaMonthUseTakingCount(countList, bean, year, month);
		}
	}

	/**
	 * 当月使用时长列表
	 * 
	 * @param param
	 * @return
	 */
	private List<AreaMonthUseTakingCount> getAreaMonthUseTakingCount(Map<String, Object> param) {
		return loginHistoryDao.getAreaMonthUseTakingCount(param);
	}

	private void saveOrUpdateAreaMonthUseTakingCount(List<AreaMonthUseTakingCount> countList, LoginHistory loginHistory,
			int year, int month) {
		if (countList != null && countList.size() > 0) {// 更新操作
			AreaMonthUseTakingCount bean = countList.get(0);

			if (bean.getAreaCode() != null && bean.getPlatformCode() != null && loginHistory.getAreaCode() != null
					&& loginHistory.getPlatformCode() != null && bean.getOrgId() != null
					&& loginHistory.getOrgId() != null) {

				if (bean.getOrgId().equals(loginHistory.getOrgId())
						&& bean.getAreaCode().equals(loginHistory.getAreaCode())
						&& bean.getPlatformCode().equals(loginHistory.getPlatformCode())) {

					if (bean.getUseTaking() == 0) {
						bean.setUseTaking(0);
					}

					if (loginHistory.getUseTaking() == 0) {
						loginHistory.setUseTaking(0);
					}

					bean.setUseTaking(bean.getUseTaking() + loginHistory.getUseTaking());
					// TODO 重新计算增长率
					if (bean.getPreviousUseTaking() != 0) {
						long rateNum = bean.getUseTaking() - bean.getPreviousUseTaking();
						BigDecimal bigDecimal = new BigDecimal(rateNum);
						BigDecimal bigDecimal2 = new BigDecimal(bean.getPreviousUseTaking());

						String rate = calculateRate(bigDecimal, bigDecimal2);
						bean.setRate(rate);
					}
					updateAreaMonthUseTakingCount(bean);
				}
			}
		} else {// 直接保存
			AreaMonthUseTakingCount bean = new AreaMonthUseTakingCount();
			if (loginHistory.getUseTaking() == 0) {
				bean.setUseTaking(0);
			} else {
				bean.setUseTaking(loginHistory.getUseTaking());
			}
			bean.setAreaCode(loginHistory.getAreaCode());
			bean.setMonth(month);
			bean.setYear(year);

			bean.setPlatformCode(loginHistory.getPlatformCode());
			bean.setCreateTime(new Date());
			bean.setOrgId(loginHistory.getOrgId());
			bean.setOrgName(loginHistory.getOrgName());
			// 找到上月的使用次数
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);

			Map<String, Object> param = new HashMap<String, Object>();
			// 找到这个学校上月的数据

			param.put("areaCode", bean.getAreaCode());
			param.put("year", cal.get(Calendar.YEAR));
			param.put("month", cal.get(Calendar.MONTH) + 1);

			// param.put("year", 2016);
			// param.put("month", 10);

			param.put("platformCode", bean.getPlatformCode());
			param.put("orgId", bean.getOrgId());
			// 找到上月本区域的数据
			List<AreaMonthUseTakingCount> privBean = getAreaMonthUseTakingCount(param);
			// 如果找到则计算增长率，否则设置增长率为0
			if (privBean != null && privBean.size() > 0) {

				if (privBean.get(0).getUseTaking() == 0) {
					privBean.get(0).setUseTaking(0);
				}

				if (loginHistory.getUseTaking() == 0) {
					loginHistory.setUseTaking(0);
				}

				bean.setPreviousUseTaking((privBean.get(0).getUseTaking()));
				/**
				 * 
				 * 增长的部分除以原本的数目就是增长率。一般用百分比表示。
				 * 比如现在的某城市今年年初人口1000万，年末1006万，那么增长率就是增长的6万除以1000万，增长率=6/1000=0.
				 * 6%
				 * 
				 */
				bean.setPreviousUseTaking(privBean.get(0).getUseTaking());
				long rateNum = loginHistory.getUseTaking() - privBean.get(0).getUseTaking();
				BigDecimal bigDecimal = new BigDecimal(rateNum);
				BigDecimal bigDecimal2 = new BigDecimal(privBean.get(0).getUseTaking());

				String rate = calculateRate(bigDecimal, bigDecimal2);
				bean.setRate(rate);
			} else {
				bean.setRate("0");
			}

			saveAreaMonthUseTakingCount(bean);
		}
	}

	public void saveAreaMonthUseTakingCount(AreaMonthUseTakingCount bean) {
		loginHistoryDao.saveAreaMonthUseTakingCount(bean);
	}

	public void updateAreaMonthUseTakingCount(AreaMonthUseTakingCount bean) {
		loginHistoryDao.updateAreaMonthUseTakingCount(bean);
	}

	private List<LoginHistory> getUserUseTakingByCurrentMonth() {
		Map<String, Object> parm = new HashMap<String, Object>();

		Date startTime = TimeUtil.getCurrentMonthStartDate();
		Date endTime = TimeUtil.getCurrentMonthEndDate();

		parm.put("startTime", CommonFunction.getDateSampleString(startTime));
		parm.put("endTime", CommonFunction.getDateSampleString(endTime));

		// parm.put("startTime", "2016-11-01");
		// parm.put("endTime", "2016-11-31");
		return loginHistoryDao.getUserUseTakingByCurrentMonth(parm);
	}

	/**
	 * 从临时表获取当月使用时长
	 * 
	 * @return
	 */
	private List<LoginHistory> getOrgUseTakingByCurrentMonth() {
		Map<String, Object> parm = new HashMap<String, Object>();

		Date startTime = TimeUtil.getCurrentMonthStartDate();
		Date endTime = TimeUtil.getCurrentMonthEndDate();

		parm.put("startTime", CommonFunction.getDateSampleString(startTime));
		parm.put("endTime", CommonFunction.getDateSampleString(endTime));

		// parm.put("startTime", "2016-11-01");
		// parm.put("endTime", "2016-11-31");
		return loginHistoryDao.getOrgUseTakingByCurrentMonth(parm);
	}

	// -----------------------------------------------使用时长 end
	// -------------------------------------------

	List<UserMonthUsageCount> countUserNum3() {
		List<LoginHistory> loginHistoryList = getUserCountByCurrentMonth();
		List<UserMonthUsageCount> useCountList = new ArrayList<UserMonthUsageCount>();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		for (LoginHistory bean : loginHistoryList) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userId", bean.getUserId());
			param.put("orgId", bean.getOrgId());
			param.put("year", year);
			param.put("month", month);
			param.put("areaCode", bean.getAreaCode());
			param.put("platformCode", bean.getPlatformCode());
			List<UserMonthUsageCount> countList = getUserMonthUsageCount(param);
			UserMonthUsageCount eg = saveOrUpdateUserMonthUsageCount3(countList, bean, year, month);
			if (eg != null) {
				useCountList.add(eg);
			}
		}
		return useCountList;
	}

	public void countAreaActivity2() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		// int year = 2016;
		// int month = 11;

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("year", year);
		param.put("month", month);

		// 先删除本月数据，在重新统计
		// loginHistoryDao.deleteOrgMonthUsage(param);

		loginHistoryDao.deleteAreaMonthActivity(param);

		// 当月用户使用统计
		// List<OrgMonthUsageCount> countList = countOrgMonthUsage(param);

		List<AreaMonthActivityCount> countList = countAreaMonthActivity(param);

		// 分组
		Map<String, List<AreaMonthActivityCount>> resultMap = new HashMap<String, List<AreaMonthActivityCount>>();
		for (AreaMonthActivityCount bean : countList) {
			if (resultMap.containsKey(bean.getAreaCode())) {
				resultMap.get(bean.getAreaCode()).add(bean);
			} else {
				List<AreaMonthActivityCount> list = new ArrayList<AreaMonthActivityCount>();
				list.add(bean);
				resultMap.put(bean.getAreaCode(), list);
			}
		}

		// 对数据进行学校分组统计
		// 计算班级使用率和班级排名
		Iterator it = resultMap.keySet().iterator();

		while (it.hasNext()) {

			String key;
			String value;
			key = it.next().toString();
			// 以班级为一组排序
			List<AreaMonthActivityCount> list = resultMap.get(key);
			Collections.sort(list, new Comparator<AreaMonthActivityCount>() {

				@Override
				public int compare(AreaMonthActivityCount bean1, AreaMonthActivityCount bean2) {

					if (bean1.getLoginTaking() == null) {
						bean1.setLoginTaking(0);
					}

					if (bean2.getLoginTaking() == null) {
						bean2.setLoginTaking(0);
					}
					return bean2.getLoginTaking().compareTo(bean1.getLoginTaking());
				}
			});

			// 统计总次数
			List<AreaMonthActivityCount> list2 = new ArrayList<AreaMonthActivityCount>();
			for (int i = 0; i < list.size(); i++) {
				AreaMonthActivityCount eg = (AreaMonthActivityCount) list.get(i);

				if (eg.getClassLoginTakingCount() == null) {
					eg.setClassLoginTakingCount(0);
				}

				if (eg.getLoginTaking() == null) {
					eg.setLoginTaking(0);
				}

				eg.setClassLoginTakingCount(eg.getClassLoginTakingCount().intValue() + eg.getLoginTaking());// 学校总次数
				eg.setOrgLoginTakingRanking(i + 1);
				list2.add(eg);
			}

			// 计算百分比
			for (AreaMonthActivityCount bean : list2) {
				// 计算班级使用百分比

				BigDecimal bigDecimal = new BigDecimal(bean.getLoginTaking());
				BigDecimal bigDecimal2 = new BigDecimal(bean.getClassLoginTakingCount());
				// String rate = BigDecimalUtils
				// .getPrettyNumber(bigDecimal.divide(bigDecimal2,2,BigDecimal.ROUND_HALF_EVEN).multiply(new
				// BigDecimal(100)).toString());
				// bean.setRate(rate);
				String rate = calculateRate(bigDecimal, bigDecimal2);
				// bean.setOrgPercent(rate);// 计算班级百分比

				bean.setAreaPercent(rate);
				saveAreaMonthActivityCount(bean);
			}

		}
	}

	private List<AreaMonthActivityCount> countAreaMonthActivity(Map<String, Object> param) {
		return loginHistoryDao.countAreaMonthActivity(param);
	}

	/**
	 * 统计学校活跃度
	 */
	public void countOrgActivity2() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		// int year = 2016;
		// int month = 11;

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("year", year);
		param.put("month", month);

		// 先删除本月数据，在重新统计
		// loginHistoryDao.deleteOrgMonthUsage(param);

		loginHistoryDao.deleteOrgMonthActivity(param);

		// 当月用户使用统计
		// List<OrgMonthUsageCount> countList = countOrgMonthUsage(param);

		List<OrgMonthActivityCount> countList = countOrgMonthActivity(param);

		// 分组
		Map<String, List<OrgMonthActivityCount>> resultMap = new HashMap<String, List<OrgMonthActivityCount>>();
		for (OrgMonthActivityCount bean : countList) {
			if (resultMap.containsKey(bean.getOrgId())) {
				resultMap.get(bean.getOrgId()).add(bean);
			} else {
				List<OrgMonthActivityCount> list = new ArrayList<OrgMonthActivityCount>();
				list.add(bean);
				resultMap.put(bean.getOrgId(), list);
			}
		}

		// 对数据进行学校分组统计
		// 计算班级使用率和班级排名
		Iterator it = resultMap.keySet().iterator();

		while (it.hasNext()) {

			String key;
			String value;
			key = it.next().toString();
			// 以班级为一组排序
			List<OrgMonthActivityCount> list = resultMap.get(key);
			Collections.sort(list, new Comparator<OrgMonthActivityCount>() {
				@Override
				public int compare(OrgMonthActivityCount bean1, OrgMonthActivityCount bean2) {

					if (bean1.getLoginTaking() == null) {
						bean1.setLoginTaking(0);
					}

					if (bean2.getLoginTaking() == null) {
						bean2.setLoginTaking(0);
					}

					return bean2.getLoginTaking().compareTo(bean1.getLoginTaking());
				}
			});

			// 统计总次数
			List<OrgMonthActivityCount> list2 = new ArrayList<OrgMonthActivityCount>();
			for (int i = 0; i < list.size(); i++) {
				OrgMonthActivityCount eg = (OrgMonthActivityCount) list.get(i);

				if (eg.getClassLoginTakingCount() == null) {
					eg.setClassLoginTakingCount(0);
				}

				if (eg.getLoginTaking() == null) {
					eg.setLoginTaking(0);
				}

				eg.setClassLoginTakingCount(eg.getClassLoginTakingCount().intValue() + eg.getLoginTaking());// 学校总次数
				eg.setClassRanking(i + 1);
				list2.add(eg);
			}

			// 计算百分比
			for (OrgMonthActivityCount bean : list2) {
				// 计算班级使用百分比

				BigDecimal bigDecimal = new BigDecimal(bean.getLoginTaking());
				BigDecimal bigDecimal2 = new BigDecimal(bean.getClassLoginTakingCount());
				String rate = calculateRate(bigDecimal, bigDecimal2);
				bean.setOrgPercent(rate);// 计算班级百分比

				// saveOrgMonthUsageCount(bean);
				saveOrgMonthActivityCount(bean);
				// updateOrgMonthUsageCount(bean);
			}

		}
	}

	/**
	 * 保存终端使用日志到历史库
	 */
	private void saveTerminalUseHistory() {
		Map<String, Object> param = new HashMap<String, Object>();
		Date startTime = TimeUtil.getCurrentMonthStartDate();
		Date endTime = TimeUtil.getCurrentMonthEndDate();

		// 找到当月未同步的数据
		param.put("startTime", CommonFunction.getDateSampleString(startTime));
		param.put("endTime", CommonFunction.getDateSampleString(endTime));

		// param.put("startTime", "2016-11-01");
		// param.put("endTime", "2016-11-31");

		List<TerminalUseLog> terminalUseLogList = terminalUseHistoryDao.getTerminalUseLogCountData(param);

		for (TerminalUseLog bean : terminalUseLogList) {
			TerminalUseHistory terminalUseHistory = new TerminalUseHistory();
			bean.setIsTransfer("1");
			terminalUseHistoryDao.updateTerminalUseLog(bean);

			try {
				BeanUtilsBean.getInstance().copyProperties(terminalUseHistory, bean);

				String aamAddress = GetCacheBaseData.getPropertyValue("platform_url",
						terminalUseHistory.getPlatformCode());

				if (aamAddress == null) {
					aamAddress = basePropertyService.getPropertyValue("platform_url", bean.getPlatformCode());
				}

				String url = aamAddress + "user/" + terminalUseHistory.getUserId();

				String resp;

				resp = HttpUtils.getInstance().httpGet(url, 30000, 30000);

				if (resp != null && !"".equals(resp)) {
					JSONObject respJson = JSONObject.fromObject(resp);
					if ("000000".equals(respJson.getString("result"))) {
						JSONObject userObject = (JSONObject) respJson.get("userinfo");

						if (userObject.get("orgaid") != null && !"".equals(userObject.get("orgaid"))) {

							String ograAddressUrl = aamAddress + "ogra/get";
							List<String> ograList = new ArrayList<String>();
							ograList.add(userObject.get("orgaid").toString());

							Map<String, Object> map = new HashMap<String, Object>();

							map.put("orgaids", ograList);

							String ograResp = HttpUtils.getInstance().httpPost(ograAddressUrl,
									JsonUtils.objTojson(map));

							if (ograResp != null && !"".equals(ograResp)) {
								JSONObject OgraRespJson = JSONObject.fromObject(ograResp);
								if ("000000".equals(OgraRespJson.getString("result"))) {
									JSONArray orgList = OgraRespJson.getJSONArray("orgalist");
									JSONObject orgObject = (JSONObject) orgList.get(0);
									terminalUseHistory.setAreaCode(orgObject.get("areacode") == null ? ""
											: orgObject.get("areacode").toString());
								}
							}
							terminalUseHistory.setOrgId(
									userObject.get("orgaid") == null ? "" : userObject.get("orgaid").toString());
							terminalUseHistory.setOrgName(
									userObject.get("organame") == null ? "" : userObject.get("organame").toString());
							terminalUseHistory.setUserName(
									userObject.get("name") == null ? "" : userObject.get("name").toString());
						}
					}
				}

				Calendar cal = Calendar.getInstance();

				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH) + 1;

				// terminalUseHistory.setYear(2016);
				// terminalUseHistory.setMonth(11);

				terminalUseHistory.setYear(year);
				terminalUseHistory.setMonth(month);
				terminalUseHistory.setId(GUIDGenerator.getUUID32());
				terminalUseHistoryDao.saveTerminalUseHistory(terminalUseHistory);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * 保存控件使用日志到历史库
	 */
	private void saveWidgetLogToHistory() {
		Map<String, Object> param = new HashMap<String, Object>();

		Date startTime = TimeUtil.getCurrentMonthStartDate();
		Date endTime = TimeUtil.getCurrentMonthEndDate();

		// 找到当月未同步的数据

		param.put("startTime", CommonFunction.getDateSampleString(startTime));
		param.put("endTime", CommonFunction.getDateSampleString(endTime));

		// param.put("startTime", "2017-04-20");
		// param.put("endTime", "2017-04-31");

		param.put("isTransfer", "0");
		List<WidgetLog> widgetLogList = taUserDao.getWidgetLog(param);

		for (WidgetLog bean : widgetLogList) {
			WidgetHistory widgetHistory = new WidgetHistory();
			List<String> idList = new ArrayList<String>();
			try {

				BeanUtilsBean.getInstance().copyProperties(widgetHistory, bean);

				String aamAddress = GetCacheBaseData.getPropertyValue("platform_url", widgetHistory.getPlatformCode());

				if (aamAddress == null) {
					aamAddress = basePropertyService.getPropertyValue("platform_url", bean.getPlatformCode());
				}

				String url = aamAddress + "user/" + bean.getUserId();

				String resp;

				resp = HttpUtils.getInstance().httpGet(url, 30000, 30000);

				if (resp != null && !"".equals(resp)) {
					JSONObject respJson = JSONObject.fromObject(resp);
					if ("000000".equals(respJson.getString("result"))) {
						JSONObject userObject = (JSONObject) respJson.get("userinfo");

						if (userObject.get("orgaid") != null && !"".equals(userObject.get("orgaid"))) {

							// String ograAddressUrl = aamAddress + "ogra/get";
							// List<String> ograList = new ArrayList<String>();
							// ograList.add(userObject.get("orgaid").toString());

							// Map<String, Object> map = new HashMap<String,
							// Object>();

							// map.put("orgaids", ograList);

							// String ograResp =
							// HttpUtils.getInstance().httpPost(ograAddressUrl,
							// JsonUtils.objTojson(map));

							// if (ograResp != null && !"".equals(ograResp)) {
							// JSONObject OgraRespJson =
							// JSONObject.fromObject(ograResp);
							// if
							// ("000000".equals(OgraRespJson.getString("result")))
							// {
							// JSONArray orgList =
							// OgraRespJson.getJSONArray("orgalist");
							// JSONObject orgObject = (JSONObject)
							// orgList.get(0);
							// widgetHistory.setAreaCode(orgObject.get("areacode")
							// == null ? ""
							// : orgObject.get("areacode").toString());
							// }
							// }
							widgetHistory.setOrgId(
									userObject.get("orgaid") == null ? "" : userObject.get("orgaid").toString());
							widgetHistory.setOrgName(
									userObject.get("organame") == null ? "" : userObject.get("organame").toString());

							// widgetHistory.setOrgId(userObject.optString("orgaid")
							// == null ? ""
							// : userObject.optString("orgaid").toString());
							// widgetHistory.setOrgName(userObject.optString("organame")
							// == null ? ""
							// : userObject.optString("organame").toString());
						}
						widgetHistory
								.setUserName(userObject.get("name") == null ? "" : userObject.get("name").toString());
						widgetHistory.setAreaCode(userObject.optString("citycode"));
					}
				}

				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH) + 1;

				// year = 2017;
				// month = 4;

				widgetHistory.setYear(year);
				widgetHistory.setMonth(month);
				widgetHistory.setId(GUIDGenerator.getUUID32());

				widgetHistoryDao.saveWidgetHistory(widgetHistory);

				idList.add(bean.getId());
				// 更新同步后的数据
				Map<String, Object> paramap = new HashMap<String, Object>();
				paramap.put("idList", idList);
				paramap.put("transfer", "1");
				if (idList.size() > 0) {
					taUserDao.updateWidgetLog(paramap);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 统计学校使用次数
	 */
	public void countOrgNum2() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		// int year = 2016;
		// int month = 11;

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("year", year);
		param.put("month", month);

		// 先删除本月数据，在重新统计
		loginHistoryDao.deleteOrgMonthUsage(param);

		// 当月用户使用统计
		List<OrgMonthUsageCount> countList = countOrgMonthUsage(param);

		// 分组
		Map<String, List<OrgMonthUsageCount>> resultMap = new HashMap<String, List<OrgMonthUsageCount>>();
		for (OrgMonthUsageCount bean : countList) {
			if (resultMap.containsKey(bean.getOrgId())) {
				resultMap.get(bean.getOrgId()).add(bean);
			} else {
				List<OrgMonthUsageCount> list = new ArrayList<OrgMonthUsageCount>();
				list.add(bean);
				resultMap.put(bean.getOrgId(), list);
			}
		}

		// 对数据进行学校分组统计
		// 计算班级使用率和班级排名
		Iterator it = resultMap.keySet().iterator();

		while (it.hasNext()) {

			String key;
			String value;
			key = it.next().toString();
			// 以班级为一组排序
			List<OrgMonthUsageCount> list = resultMap.get(key);
			Collections.sort(list, new Comparator<OrgMonthUsageCount>() {
				@Override
				public int compare(OrgMonthUsageCount bean1, OrgMonthUsageCount bean2) {
					return bean2.getUseCount().compareTo(bean1.getUseCount());
				}
			});

			// 统计总次数
			List<OrgMonthUsageCount> list2 = new ArrayList<OrgMonthUsageCount>();
			for (int i = 0; i < list.size(); i++) {
				OrgMonthUsageCount eg = (OrgMonthUsageCount) list.get(i);
				if (eg.getOrgCount() == null) {
					eg.setOrgCount(0);
				}
				eg.setOrgCount(eg.getOrgCount().intValue() + eg.getUseCount());// 学校总次数
				eg.setClassRanking(i + 1);
				list2.add(eg);
			}

			// 计算百分比
			for (OrgMonthUsageCount bean : list2) {
				// 计算班级使用百分比

				BigDecimal bigDecimal = new BigDecimal(bean.getUseCount());
				BigDecimal bigDecimal2 = new BigDecimal(bean.getOrgCount());
				// String rate = BigDecimalUtils
				// .getPrettyNumber(bigDecimal.divide(bigDecimal2,2,BigDecimal.ROUND_HALF_EVEN).multiply(new
				// BigDecimal(100)).toString());
				// bean.setRate(rate);
				String rate = calculateRate(bigDecimal, bigDecimal2);
				bean.setOrgPercent(rate);// 计算班级百分比

				saveOrgMonthUsageCount(bean);
				// updateOrgMonthUsageCount(bean);
			}

		}

		// 汇总统计学校使用信息

	}

	public List<OrgMonthActivityCount> countOrgMonthActivity(Map<String, Object> param) {
		return loginHistoryDao.countOrgMonthActivity(param);
	}

	private List<OrgMonthUsageCount> countOrgMonthUsage(Map<String, Object> param) {
		return loginHistoryDao.countOrgMonthUsage(param);
	}

	private List<LoginHistory> getUserActivityCount() {
		Map<String, Object> parm = new HashMap<String, Object>();

		Date startTime = TimeUtil.getCurrentMonthStartDate();
		Date endTime = TimeUtil.getCurrentMonthEndDate();

		parm.put("startTime", CommonFunction.getDateSampleString(startTime));
		parm.put("endTime", CommonFunction.getDateSampleString(endTime));

		// parm.put("startTime", "2016-11-01");
		// parm.put("endTime", "2016-11-31");
		return loginHistoryDao.getUserActivityCountByCurrentMonth(parm);
	}

	/**
	 * 统计用户活跃度
	 */
	public void countUserActivity() {
		List<LoginHistory> loginHistoryList = getUserActivityCount();

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		// int year = 2016;
		// int month = 11;

		for (LoginHistory bean : loginHistoryList) {
			Map<String, Object> param = new HashMap<String, Object>();

			param.put("userId", bean.getUserId());
			param.put("orgId", bean.getOrgId());
			param.put("year", year);
			param.put("month", month);
			// param.put("platformCode", bean.getPlatformCode());
			param.put("areaCode", bean.getAreaCode());
			param.put("platformCode", bean.getPlatformCode());

			List<UserMonthActivityCount> countList = getUserMonthActivityCount(param);

			saveOrUpdateUserMonthActivityCount(countList, bean, year, month);
		}

		// Map<String, Object> param = new HashMap<String, Object>();
		//
		// param.put("year", year);
		// param.put("month", month);
		//
		// List<UserMonthActivityCount> countList =
		// getUserMonthActivityCount(param);
		//
		// // 分组
		// Map<String, List<UserMonthActivityCount>> resultMap = new
		// HashMap<String, List<UserMonthActivityCount>>();
		// for (UserMonthActivityCount eg : countList) {
		// if(eg.getClass()!=null){
		// if (resultMap.containsKey(eg.getClassId())) {
		// resultMap.get(eg.getClassId()).add(eg);
		// } else {
		// List<UserMonthActivityCount> list = new
		// ArrayList<UserMonthActivityCount>();
		// list.add(eg);
		// resultMap.put(eg.getClassId(), list);
		// }
		// }
		//
		//
		// }
		//
		// // 计算班级使用率和班级排名
		// Iterator it = resultMap.keySet().iterator();
		//
		// while (it.hasNext()) {
		// String key;
		// String value;
		// key = it.next().toString();
		// // 以班级为一组排序
		// List<UserMonthActivityCount> list = resultMap.get(key);
		// Collections.sort(list, new Comparator<UserMonthActivityCount>() {
		// @Override
		// public int compare(UserMonthActivityCount bean1,
		// UserMonthActivityCount bean2) {
		// if (bean1.getLoginTaking() == null) {
		// bean1.setLoginTaking(0);
		// }
		// if (bean2.getLoginTaking() == null) {
		// bean2.setLoginTaking(0);
		// }
		// return bean2.getLoginTaking().compareTo(bean1.getLoginTaking());
		// }
		// });
		//
		// List<UserMonthActivityCount> list2 = new
		// ArrayList<UserMonthActivityCount>();
		// for (int i = 0; i < list.size(); i++) {
		// UserMonthActivityCount eg = (UserMonthActivityCount) list.get(i);
		//
		// if (eg.getClassLoginTakingCount() == null) {
		// eg.setClassLoginTakingCount(0);
		// }
		//
		// if (eg.getLoginTaking() == null) {
		// eg.setLoginTaking(0);
		// }
		//
		// eg.setClassLoginTakingCount(eg.getClassLoginTakingCount().intValue()
		// + eg.getLoginTaking().intValue());// 班级总时长
		// eg.setRanking(i + 1);
		// list2.add(eg);
		// }
		//
		// for (UserMonthActivityCount bean : list2) {
		// updateUserMonthActivityCount(bean);
		// }
		// }

	}

	// private List<UserMonthActivityCount> getUserActivityCount(Map<String,
	// Object> param) {
	// // TODO Auto-generated method stub
	// return null;
	// }

	private void saveOrUpdateUserMonthActivityCount(List<UserMonthActivityCount> countList, LoginHistory loginHistory,
			int year, int month) {
		if (countList != null && countList.size() > 0) {// 更新操作
			UserMonthActivityCount bean = countList.get(0);

			if (bean.getUserId().equals(loginHistory.getUserId()) && bean.getOrgId().equals(loginHistory.getOrgId())) {

				if (bean.getLoginTaking() == null) {
					bean.setLoginTaking(0);
				}
				if (loginHistory.getLoginTaking() == null) {
					loginHistory.setLoginTaking(0);
				}

				bean.setLoginTaking(bean.getLoginTaking() + loginHistory.getLoginTaking());
				// 上月活跃度不为空，则重新计算增长率
				if (bean.getPreviousLoginTaking() != null) {
					int rateNum = bean.getLoginTaking().intValue() - bean.getPreviousLoginTaking().intValue();
					BigDecimal bigDecimal = new BigDecimal(rateNum);
					BigDecimal bigDecimal2 = new BigDecimal(bean.getPreviousLoginTaking());

					String rate = calculateRate(bigDecimal, bigDecimal2);
					bean.setRate(rate);
				}
			}
			bean.setUpdateTime(new Date());
			updateUserMonthActivityCount(bean);
		} else {// 直接保存
			UserMonthActivityCount bean = new UserMonthActivityCount();
			bean.setAreaCode(loginHistory.getAreaCode());
			bean.setMonth(month);
			bean.setYear(year);
			bean.setPlatformCode(loginHistory.getPlatformCode());
			bean.setUserId(loginHistory.getUserId());
			bean.setUserName(loginHistory.getUserName());
			bean.setOrgId(loginHistory.getOrgId());
			bean.setOrgName(loginHistory.getOrgName());
			bean.setCreateTime(new Date());

			bean.setLoginTaking(loginHistory.getLoginTaking());

			// 找到上月的使用次数
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);

			Map<String, Object> param = new HashMap<String, Object>();
			// 找到这个学校上月的数据

			// param.put("orgId", bean.getOrgId());
			param.put("areaCode", bean.getAreaCode());

			// param.put("year", 2016);
			// param.put("month", 10);

			param.put("year", cal.get(Calendar.YEAR));
			param.put("month", cal.get(Calendar.MONTH) + 1);
			param.put("platformCode", bean.getPlatformCode());
			param.put("userId", bean.getUserId());
			param.put("orgId", bean.getOrgId());

			// 获取老师所教班级
			// List<UserClass> userClassList =
			// getUserClass(loginHistory.getOrgId(), loginHistory.getUserId(),
			// loginHistory.getAreaCode(), loginHistory.getPlatformCode());

			// for (UserClass userClass : userClassList) {
			// bean.setClassId(userClass.getClassId());
			// bean.setClassName(userClass.getClassName());

			// 找到上月
			List<UserMonthActivityCount> privBean = getUserMonthActivityCount(param);

			if (privBean != null && privBean.size() > 0) {

				if (privBean.get(0).getLoginTaking() == null) {
					privBean.get(0).setLoginTaking(0);
				}

				if (loginHistory.getLoginTaking() == null) {
					loginHistory.setLoginTaking(0);
				}

				bean.setPreviousLoginTaking(privBean.get(0).getLoginTaking().intValue());
				int rateNum = loginHistory.getLoginTaking().intValue() - privBean.get(0).getLoginTaking().intValue();
				BigDecimal bigDecimal = new BigDecimal(rateNum);
				BigDecimal bigDecimal2 = new BigDecimal(privBean.get(0).getLoginTaking());

				String rate = calculateRate(bigDecimal, bigDecimal2);
				bean.setRate(rate);
			} else {
				bean.setRate("0");
			}
			saveUserMonthActivityCount(bean);

			// }
		}
	}

	private void saveUserMonthActivityCount(UserMonthActivityCount bean) {
		loginHistoryDao.saveUserMonthActivityCount(bean);

	}

	private void updateUserMonthActivityCount(UserMonthActivityCount bean) {
		loginHistoryDao.updateUserMonthActivityCount(bean);

	}

	private List<UserMonthActivityCount> getUserMonthActivityCount(Map<String, Object> param) {
		return loginHistoryDao.getUserMonthActivityCount(param);
	}

	public void countUserNum() {
		List<LoginHistory> loginHistoryList = getUserCountByCurrentMonth();

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		// int year = 2016;
		// int month = 11;

		for (LoginHistory bean : loginHistoryList) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userId", bean.getUserId());
			param.put("orgId", bean.getOrgId());
			param.put("year", year);
			param.put("month", month);
			param.put("areaCode", bean.getAreaCode());
			param.put("platformCode", bean.getPlatformCode());
			List<UserMonthUsageCount> countList = getUserMonthUsageCount(param);
			saveOrUpdateUserMonthUsageCount(countList, bean, year, month);
		}

		// Map<String, Object> param = new HashMap<String, Object>();
		// param.put("year", year);
		// param.put("month", month);
		// 当月数据
		// List<UserMonthUsageCount> countList = getUserMonthUsageCount(param);

		// 分组
		// Map<String, List<UserMonthUsageCount>> resultMap = new
		// HashMap<String, List<UserMonthUsageCount>>();
		// for (UserMonthUsageCount eg : countList) {
		// if (resultMap.containsKey(eg.getClassId())) {
		// resultMap.get(eg.getClassId()).add(eg);
		// } else {
		// List<UserMonthUsageCount> list = new
		// ArrayList<UserMonthUsageCount>();
		// list.add(eg);
		// resultMap.put(eg.getClassId(), list);
		// }
		// }

		// 计算班级使用率和班级排名
		// Iterator it = resultMap.keySet().iterator();
		//
		// while (it.hasNext()) {
		// String key;
		// String value;
		// key = it.next().toString();
		// // 以班级为一组排序
		// List<UserMonthUsageCount> list = resultMap.get(key);
		// Collections.sort(list, new Comparator<UserMonthUsageCount>() {
		// @Override
		// public int compare(UserMonthUsageCount bean1, UserMonthUsageCount
		// bean2) {
		// return bean2.getUseCount().compareTo(bean1.getUseCount());
		// }
		// });

		// List<UserMonthUsageCount> list2 = new
		// ArrayList<UserMonthUsageCount>();
		// for (int i = 0; i < list.size(); i++) {
		// UserMonthUsageCount eg = (UserMonthUsageCount) list.get(i);
		//
		// if (eg.getClassCount() == null) {
		// eg.setClassCount(0);
		// }
		//
		// eg.setClassCount(eg.getClassCount().intValue() + eg.getUseCount());//
		// 班级总次数
		// eg.setClassRanking(i + 1);
		// list2.add(eg);
		// }

		// for (UserMonthUsageCount bean : list2) {
		// // 计算班级使用百分比
		//
		// BigDecimal bigDecimal = new BigDecimal(bean.getUseCount());
		// BigDecimal bigDecimal2 = new BigDecimal(bean.getClassCount());
		// // String rate = BigDecimalUtils
		// //
		// .getPrettyNumber(bigDecimal.divide(bigDecimal2,2,BigDecimal.ROUND_HALF_EVEN).multiply(new
		// // BigDecimal(100)).toString());
		// // bean.setRate(rate);
		// String rate = calculateRate(bigDecimal, bigDecimal2);
		// bean.setClassPercent(rate);// 计算班级百分比
		//
		// updateUserMonthUsageCount(bean);
		// }
		// }

	}

	public UserMonthUsageCount saveOrUpdateUserMonthUsageCount3(List<UserMonthUsageCount> countList,
			LoginHistory loginHistory, int year, int month) {
		UserMonthUsageCount eg = null;
		if (countList != null && countList.size() > 0) {// 更新操作
			UserMonthUsageCount bean = countList.get(0);

			if (bean.getUserId().equals(loginHistory.getUserId()) && bean.getOrgId().equals(loginHistory.getOrgId())) {
				bean.setUseCount(bean.getUseCount() + loginHistory.getRunNumber());
				bean.setUpdateTime(new Date());

				// List<UserClass> userClassList =
				// getUserClass(loginHistory.getOrgId(),
				// loginHistory.getUserId(),
				// loginHistory.getAreaCode(), loginHistory.getPlatformCode());

				// for (UserClass userClass : userClassList) {
				// if (bean.getClass().equals(userClass.getClass())) {
				// 计算增长率
				if (bean.getPreviousUseCount() != null) {
					int rateNum = bean.getUseCount() - bean.getPreviousUseCount();
					BigDecimal bigDecimal = new BigDecimal(rateNum);
					BigDecimal bigDecimal2 = new BigDecimal(bean.getPreviousUseCount());
					// String rate = BigDecimalUtils
					// .getPrettyNumber(bigDecimal.divide(bigDecimal2,2,BigDecimal.ROUND_HALF_EVEN).multiply(new
					// BigDecimal(100)).toString());
					// bean.setRate(rate);

					String rate = calculateRate(bigDecimal, bigDecimal2);
					bean.setRate(rate);
				}
				updateUserMonthUsageCount(bean);
				eg = bean;
				// }
				// }
			}
		} else {// 直接保存
			UserMonthUsageCount bean = new UserMonthUsageCount();
			bean.setUserId(loginHistory.getUserId());
			bean.setOrgId(loginHistory.getOrgId());
			bean.setOrgName(loginHistory.getOrgName());
			bean.setAreaCode(loginHistory.getAreaCode());
			bean.setMonth(month);
			bean.setYear(year);
			bean.setUseCount(loginHistory.getRunNumber());
			bean.setUserName(loginHistory.getUserName());
			bean.setPlatformCode(loginHistory.getPlatformCode());
			bean.setCreateTime(new Date());
			// 找到上月的使用次数
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);

			Map<String, Object> param = new HashMap<String, Object>();
			// 找到这个学校上月的数据
			param.put("orgId", bean.getOrgId());
			param.put("year", cal.get(Calendar.YEAR));
			param.put("month", cal.get(Calendar.MONTH) + 1);

			// param.put("year", 2016);
			// param.put("month", 10);

			param.put("areaCode", bean.getAreaCode());
			param.put("platformCode", bean.getPlatformCode());

			// 计算上月使用率
			List<UserMonthUsageCount> privBean = getUserMonthUsageCount(param);
			if (privBean != null && privBean.size() > 0) {
				bean.setPreviousUseCount(privBean.get(0).getUseCount());
				/**
				 * 增长的部分除以原本的数目就是增长率。一般用百分比表示。
				 * 比如现在的某城市今年年初人口1000万，年末1006万，那么增长率就是增长的6万除以1000万，增长率=6/
				 * 1000=0. 6%
				 */
				int rateNum = loginHistory.getRunNumber() - privBean.get(0).getUseCount();
				BigDecimal bigDecimal = new BigDecimal(rateNum);
				BigDecimal bigDecimal2 = new BigDecimal(privBean.get(0).getUseCount());
				String rate = calculateRate(bigDecimal, bigDecimal2);
				bean.setRate(rate);
			} else {
				bean.setRate("0");
			}
			saveUserMonthUsageCount(bean);
			eg = bean;
		}
		// }
		return eg;
	}

	public void saveOrUpdateUserMonthUsageCount(List<UserMonthUsageCount> countList, LoginHistory loginHistory,
			int year, int month) {
		if (countList != null && countList.size() > 0) {// 更新操作
			UserMonthUsageCount bean = countList.get(0);

			if (bean.getUserId().equals(loginHistory.getUserId()) && bean.getOrgId().equals(loginHistory.getOrgId())) {
				bean.setUseCount(bean.getUseCount() + loginHistory.getRunNumber());
				bean.setUpdateTime(new Date());

				// List<UserClass> userClassList =
				// getUserClass(loginHistory.getOrgId(),
				// loginHistory.getUserId(),
				// loginHistory.getAreaCode(), loginHistory.getPlatformCode());

				// for (UserClass userClass : userClassList) {
				// if (bean.getClass().equals(userClass.getClass())) {
				// 计算增长率
				if (bean.getPreviousUseCount() != null) {
					int rateNum = bean.getUseCount() - bean.getPreviousUseCount();
					BigDecimal bigDecimal = new BigDecimal(rateNum);
					BigDecimal bigDecimal2 = new BigDecimal(bean.getPreviousUseCount());
					// String rate = BigDecimalUtils
					// .getPrettyNumber(bigDecimal.divide(bigDecimal2,2,BigDecimal.ROUND_HALF_EVEN).multiply(new
					// BigDecimal(100)).toString());
					// bean.setRate(rate);

					String rate = calculateRate(bigDecimal, bigDecimal2);
					bean.setRate(rate);
				}
				updateUserMonthUsageCount(bean);
				// }
				// }
			}
		} else {// 直接保存
			UserMonthUsageCount bean = new UserMonthUsageCount();
			bean.setUserId(loginHistory.getUserId());
			bean.setOrgId(loginHistory.getOrgId());
			bean.setOrgName(loginHistory.getOrgName());
			bean.setAreaCode(loginHistory.getAreaCode());
			bean.setMonth(month);
			bean.setYear(year);
			bean.setUseCount(loginHistory.getRunNumber());
			bean.setUserName(loginHistory.getUserName());
			bean.setPlatformCode(loginHistory.getPlatformCode());
			bean.setCreateTime(new Date());
			// 找到上月的使用次数
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);

			Map<String, Object> param = new HashMap<String, Object>();
			// 找到这个学校上月的数据

			param.put("orgId", bean.getOrgId());
			param.put("year", cal.get(Calendar.YEAR));
			param.put("month", cal.get(Calendar.MONTH) + 1);

			// param.put("year", 2016);
			// param.put("month", 10);

			param.put("areaCode", bean.getAreaCode());
			param.put("platformCode", bean.getPlatformCode());

			// 获取老师所教班级
			// List<UserClass> userClassList =
			// getUserClass(loginHistory.getOrgId(), loginHistory.getUserId(),
			// loginHistory.getAreaCode(), loginHistory.getPlatformCode());

			// for (UserClass userClass : userClassList) {
			// bean.setClassId(userClass.getClassId());
			// bean.setClassName(userClass.getClassName());
			// bean.setClassPercent(classPercent);
			// 最后排序
			List<UserMonthUsageCount> privBean = getUserMonthUsageCount(param);
			if (privBean != null && privBean.size() > 0) {
				bean.setPreviousUseCount(privBean.get(0).getUseCount());
				/**
				 * 增长的部分除以原本的数目就是增长率。一般用百分比表示。
				 * 比如现在的某城市今年年初人口1000万，年末1006万，那么增长率就是增长的6万除以1000万，增长率=6/
				 * 1000=0. 6%
				 */
				int rateNum = loginHistory.getRunNumber() - privBean.get(0).getUseCount();
				BigDecimal bigDecimal = new BigDecimal(rateNum);
				BigDecimal bigDecimal2 = new BigDecimal(privBean.get(0).getUseCount());
				// String rate = BigDecimalUtils
				// .getPrettyNumber(bigDecimal.divide(bigDecimal2,2,BigDecimal.ROUND_HALF_EVEN).multiply(new
				// BigDecimal(100)).toString());
				// bean.setRate(rate);
				String rate = calculateRate(bigDecimal, bigDecimal2);
				bean.setRate(rate);
			} else {
				bean.setRate("0");
			}
			saveUserMonthUsageCount(bean);
		}
		// }
	}

	/**
	 * 获取教师所教班级
	 * 
	 * @param orgId
	 * @param userId
	 * @param areaCode
	 * @param platformCode
	 * @return
	 */
	// private List<UserClass> getUserClass(String orgId, String userId, String
	// areaCode, String platformCode) {
	// String aamAddress = GetCacheBaseData.getPropertyValue("platform_url",
	// platformCode);
	// String url = aamAddress + "user/" + userId;
	//
	// List<UserClass> list = new ArrayList<UserClass>();
	// String resultResp;
	// try {
	// resultResp = HttpUtils.getInstance().httpGet(url);
	// if (resultResp != null && !"".equals(resultResp)) {
	// JSONObject resultJson = JSONObject.fromObject(resultResp);
	// if ("000000".equals(resultJson.getString("result"))) {
	// // userinfo
	// JSONObject userinfo = resultJson.optJSONObject("userinfo");
	// if (userinfo != null) {
	// JSONArray teachesubjectlist = userinfo.optJSONArray("teachesubjectlist");
	// if (teachesubjectlist != null && teachesubjectlist.size() > 0) {
	// for (int i = 0; i < teachesubjectlist.size(); i++) {
	// if (list.size() > 1) {
	// break;
	// }
	// JSONObject teacheSubjct = (JSONObject) teachesubjectlist.get(i);
	//
	// String teacheClassId = teacheSubjct.optString("classid");
	// String teacheOrgId = teacheSubjct.optString("orgaId");
	//
	// if (teacheClassId != null && teacheOrgId != null) {
	//
	// if (teacheOrgId.equals(orgId)) {
	// UserClass bean = new UserClass();
	//
	// bean.setClassId(teacheClassId);
	// bean.setClassName(teacheSubjct.optString("classname"));
	// bean.setClassNum(teacheSubjct.optInt("classNum"));
	// list.add(bean);
	// }
	// }
	// }
	// }
	//
	// }
	//
	// }
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// return list;
	// }

	private void saveUserMonthUsageCount(UserMonthUsageCount bean) {
		loginHistoryDao.saveUserMonthUsageCount(bean);

	}

	private void updateUserMonthUsageCount(UserMonthUsageCount bean) {
		loginHistoryDao.updateUserMonthUsageCount(bean);

	}

	private List<UserMonthUsageCount> getUserMonthUsageCount(Map<String, Object> param) {
		return loginHistoryDao.getUserMonthUsageCount(param);
	}

	public void countAreaActivity() {
		List<LoginHistory> loginHistoryList = getOrgUseCountByCurrentMonth();

		// getOrgMonthUsageCount(param)

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		for (LoginHistory bean : loginHistoryList) {
			// 找到学校对应的月统计信息
			Map<String, Object> param = new HashMap<String, Object>();

			param.put("areaCode", bean.getAreaCode());
			param.put("year", year);
			param.put("month", month);
			param.put("platformCode", bean.getPlatformCode());
			List<AreaMonthActivityCount> countList = getAreaMonthActivityCount(param);
			saveOrUpdateAreaMonthActivityCount(countList, bean, year, month);
		}
	}

	private void saveOrUpdateAreaMonthActivityCount(List<AreaMonthActivityCount> countList, LoginHistory loginHistory,
			int year, int month) {
		// if (countList != null && countList.size() > 0) {// 更新操作
		// AreaMonthActivityCount bean = countList.get(0);
		//
		// if (bean.getAreaCode().equals(loginHistory.getAreaCode())
		// && bean.getPlatformCode().equals(loginHistory.getPlatformCode())) {
		//
		// if (bean.getOrgId().equals(loginHistory.getOrgId())
		// && bean.getAreaCode().equals(loginHistory.getAreaCode())
		// && bean.getPlatformCode().equals(loginHistory.getPlatformCode())) {
		//
		// if(bean.getLoginTaking()==null){
		// bean.setLoginTaking(0);
		// }
		//
		// if(loginHistory.getLoginTaking()==null){
		// loginHistory.setLoginTaking(0);
		// }
		//
		//
		//
		//
		// // 计算活跃度,查询该区域下的
		//// List<OrgMonthActivityCount> orgActivityCountList =
		// getOrgActivityCount(year, month,
		//// loginHistory.getAreaCode(), loginHistory.getPlatformCode());
		//
		//// if (orgActivityCountList != null && orgActivityCountList.size() >
		// 0) {
		//// OrgMonthActivityCount orgActivityCount =
		// orgActivityCountList.get(0);
		////
		//// int orgNum = getOrgNum(bean.getAreaCode(), bean.getPlatformCode());
		//// // 机构数量
		//// BigDecimal b1 = new BigDecimal(orgActivityCount.getUseActivity());
		//// // 该区域下学校活跃度总和
		//// BigDecimal b2 = new BigDecimal(orgNum);
		//// String useActivity = BigDecimalUtils.getPrettyNumber(
		//// b1.divide(b2, 2, BigDecimal.ROUND_HALF_EVEN).multiply(new
		// BigDecimal(100)).toString());
		////
		//// bean.setUseActivity(useActivity);// 用户活跃度
		//// }
		//
		// if(bean.getPreviousLoginTaking())
		//
		// // 上月活跃度不为空，则重新计算增长率
		// if (bean.getPreviousUseActivity() != null) {
		// int rateNum = Integer.valueOf(bean.getUseActivity())
		// - Integer.valueOf(bean.getPreviousUseActivity());
		// BigDecimal bigDecimal = new BigDecimal(rateNum);
		// BigDecimal bigDecimal2 = new
		// BigDecimal(bean.getPreviousUseActivity());
		// String rate = calculateRate(bigDecimal, bigDecimal2);
		// bean.setRate(rate);
		// }
		// // }
		//
		// bean.setUpdateTime(new Date());
		// updateAreaMonthActivityCount(bean);
		//
		// }
		//
		// }
		//
		// } else {// 直接保存
		// AreaMonthActivityCount bean = new AreaMonthActivityCount();
		// bean.setAreaCode(loginHistory.getAreaCode());
		// bean.setMonth(month);
		// bean.setYear(year);
		// bean.setPlatformCode(loginHistory.getPlatformCode());
		// // bean.setUseCount(loginHistory.getRunNumber());
		// bean.setCreateTime(new Date());
		//
		// // 计算活跃度
		//
		// // if (loginHistory.getRunNumber() >= 3) {
		// // bean.setActivityOrgNum(1);// 活跃用户数
		// // } else {
		// // bean.setActivityOrgNum(0);
		// // }
		//
		// // 查询该区域下的学校活跃比例的总数 /学校总数
		// List<OrgMonthActivityCount> orgActivityCountList =
		// getOrgActivityCount(year, month,
		// loginHistory.getAreaCode(), loginHistory.getPlatformCode());
		//
		// if (orgActivityCountList != null && orgActivityCountList.size() > 0)
		// {
		// OrgMonthActivityCount orgActivityCount = orgActivityCountList.get(0);
		//
		// int orgNum = getOrgNum(bean.getAreaCode(), bean.getPlatformCode());
		//
		// // TODO 计算活跃度
		// // 计算活跃度,查询该学校下的所有老师数量，在和活跃的老师的数量做比较，得到活跃度
		// BigDecimal b1 = new BigDecimal(orgActivityCount.getUseActivity());
		// // 该区域下学校活跃度总和
		// BigDecimal b2 = new BigDecimal(orgNum);
		// String useActivity = BigDecimalUtils.getPrettyNumber(
		// b1.divide(b2, 2, BigDecimal.ROUND_HALF_EVEN).multiply(new
		// BigDecimal(100)).toString());
		//
		// bean.setUseActivity(useActivity);// 用户活跃度
		// } else {
		// bean.setUseActivity("0");
		// }
		//
		// // 找到上月的使用次数
		// Calendar cal = Calendar.getInstance();
		// cal.add(Calendar.MONTH, -1);
		//
		// Map<String, Object> param = new HashMap<String, Object>();
		// // 找到这个学校上月的数据
		// // param.put("orgId", bean.getOrgId());
		// param.put("areaCode", bean.getAreaCode());
		// param.put("year", cal.get(Calendar.YEAR));
		// param.put("month", cal.get(Calendar.MONTH) + 1);
		// param.put("platformCode", bean.getPlatformCode());
		//
		// List<AreaMonthActivityCount> privBean =
		// getAreaMonthActivityCount(param);
		// // TODO 增长率
		// if (privBean != null && privBean.size() > 0) {
		// bean.setPreviousUseActivity(privBean.get(0).getUserActivityCount());
		// // 上月的活跃度
		// int rateNum = Integer.valueOf(bean.getUseActivity())
		// - Integer.valueOf(privBean.get(0).getUseActivity());
		// BigDecimal bigDecimal = new BigDecimal(rateNum);
		// BigDecimal bigDecimal2 = new
		// BigDecimal(privBean.get(0).getUseActivity());
		// String rate = calculateRate(bigDecimal, bigDecimal2);
		// bean.setRate(rate);
		// } else {
		// bean.setRate("0");
		// }
		// saveAreaMonthActivityCount(bean);
		// }
	}

	/**
	 * 查询本区域所有学校活跃度之和
	 * 
	 * @param year
	 * @param month
	 * @param areaCode
	 * @param platformCode
	 * @return
	 */
	public List<OrgMonthActivityCount> getOrgActivityCount(int year, int month, String areaCode, String platformCode) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("year", year);
		param.put("month", month);
		param.put("areaCode", areaCode);
		param.put("platformCode", platformCode);
		return loginHistoryDao.getOrgActivityCount(param);

	}

	// 计算增长率
	public String calculateRate(BigDecimal b1, BigDecimal b2) {
		if (b2.toString().equals("0"))
			return "0";

		String rate = BigDecimalUtils
				.getPrettyNumber(b1.divide(b2, 2, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(100)).toString());

		return rate;
	}

	/**
	 * 统计学校活跃度
	 */
	public void countOrgActivity() {
		// List<LoginHistory> loginHistoryList = getOrgUseCountByCurrentMonth();

		List<LoginHistory> loginHistoryList = getUserCountByCurrentMonth();

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		for (LoginHistory bean : loginHistoryList) {

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("orgId", bean.getOrgId());
			param.put("areaCode", bean.getAreaCode());
			param.put("year", year);
			param.put("month", month);
			param.put("platform", bean.getPlatformCode());

			List<OrgMonthActivityCount> countList = getOrgMonthActivityCount(param);

			saveOrUpdateOrgMonthActivityCount(countList, bean, year, month);
		}
	}

	// public Integer getOrgNum(String areaCode, String platformCode) {
	// Integer num = null;
	// // 根据学校区域获取对应的省份 和市区 编码
	// Map<String, Object> areaParam = new HashMap<String, Object>();
	// areaParam.put("areaCode", areaCode);
	//
	// List<Area> areaList = areaDao.getArea(areaParam);
	//
	// String provincecode = null;
	//
	// if (areaList != null && areaList.size() > 0) {
	// Area area = areaList.get(0);
	// provincecode = area.getParentId();
	// } else {
	// return null;
	// }
	//
	// // 根据平台编码查询 该区域下的所有学校
	// String aamAddress = GetCacheBaseData.getPropertyValue("platform_url",
	// platformCode);
	// String url = aamAddress + "area/querySchoolInfoRep";
	// Map<String, Object> map = new HashMap<String, Object>();
	// map.put("type", "1");// 1.学校 2.机构
	// map.put("provincecode", provincecode);
	// map.put("areacode", areaCode);
	// map.put("start", "0");
	// map.put("end", "10");
	//
	// try {
	// Thread.sleep(1500);
	// } catch (InterruptedException e1) {
	// // TODO Auto-generated catch block
	// e1.printStackTrace();
	// }
	//
	// String resultResp;
	// try {
	// resultResp = HttpUtils.getInstance().httpPost(url,
	// JsonUtils.objTojson(map));
	// // System.out.println("resultResp" + resultResp);
	// if (resultResp != null && !"".equals(resultResp)) {
	// JSONObject resultJson = JSONObject.fromObject(resultResp);
	// if ("000000".equals(resultJson.getString("result"))) {
	//
	// num = Integer.valueOf(resultJson.optString("count"));
	// }
	// }
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// return num;
	//
	// }

	/**
	 * 获取学校老师的数量
	 * 
	 * @param orgId
	 * @return
	 */
	// public int getTeacherNum(String orgId, String platformCode) {
	// String aamAddress = GetCacheBaseData.getPropertyValue("platform_url",
	// platformCode);
	//
	// String url = aamAddress + "school/statistics/" + orgId;
	// int teacherNum = 0;
	// String resp;
	// try {
	// resp = HttpUtils.getInstance().httpGet(url, 30000, 30000);
	//
	// if (logger.isDebugEnabled()) {
	// logger.debug("信息返回:" + resp);
	// }
	//
	// if (resp != null && !"".equals(resp)) {
	// JSONObject respJson = JSONObject.fromObject(resp);
	// if ("000000".equals(respJson.getString("result"))) {
	// String teachercount = respJson.getString("teachercount");
	//
	// if (teachercount != null) {
	// teacherNum = Integer.valueOf(teachercount.toString());
	// }
	// }
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// return teacherNum;
	//
	// }

	/**
	 * 统计学校月活跃度
	 * 
	 * @param countList
	 * @param loginHistory
	 * @param year
	 * @param month
	 */
	private void saveOrUpdateOrgMonthActivityCount(List<OrgMonthActivityCount> countList, LoginHistory loginHistory,
			int year, int month) {
		// if (countList != null && countList.size() > 0) {// 更新操作
		// OrgMonthActivityCount bean = countList.get(0);
		// // bean.setUseCount(loginHistory.getRunNumber());
		//
		// if (loginHistory.getAreaCode().equals(bean.getAreaCode()) &&
		// loginHistory.getOrgId().equals(bean.getOrgId())
		// && loginHistory.getPlatformCode().equals(bean.getPlatformCode())) {
		//
		// // 次数大于3，则活跃度+1
		// if (loginHistory.getRunNumber() >= 3) {
		// // 活跃用户数 加1
		// bean.setActivityUserNum(bean.getActivityUserNum() + 1);
		// // 计算活跃度,查询该学校下的所有老师数量，在和活跃的老师的数量做比较，得到活跃度
		// int teacherNum = getTeacherNum(bean.getOrgId(),
		// bean.getPlatformCode());
		//
		// BigDecimal b1 = new BigDecimal(bean.getActivityUserNum());
		// BigDecimal b2 = new BigDecimal(teacherNum);
		// String useActivity = BigDecimalUtils.getPrettyNumber(
		// b1.divide(b2, 2, BigDecimal.ROUND_HALF_EVEN).multiply(new
		// BigDecimal(100)).toString());
		//
		// bean.setUseActivity(useActivity);// 用户活跃度
		//
		// // 上月活跃度不为空，则重新计算增长率
		// if (bean.getPreviousUseActivity() != null) {
		// int rateNum = Integer.valueOf(bean.getUseActivity())
		// - Integer.valueOf(bean.getPreviousUseActivity());
		// BigDecimal bigDecimal = new BigDecimal(rateNum);
		// BigDecimal bigDecimal2 = new
		// BigDecimal(bean.getPreviousUseActivity());
		// // String rate = BigDecimalUtils.getPrettyNumber(
		// //
		// bigDecimal.divide(bigDecimal2,2,BigDecimal.ROUND_HALF_EVEN).multiply(new
		// // BigDecimal(100)).toString());
		// // bean.setRate(rate);
		//
		// String rate = calculateRate(bigDecimal, bigDecimal2);
		// bean.setRate(rate);
		// }
		// }
		// }
		//
		// bean.setUpdateTime(new Date());
		// updateOrgMonthActivityCount(bean);
		// } else {// 直接保存
		// OrgMonthActivityCount bean = new OrgMonthActivityCount();
		// bean.setOrgId(loginHistory.getOrgId());
		// bean.setOrgName(loginHistory.getOrgName());
		// bean.setAreaCode(loginHistory.getAreaCode());
		// bean.setMonth(month);
		// bean.setYear(year);
		// bean.setPlatformCode(loginHistory.getPlatformCode());
		// bean.setCreateTime(new Date());
		// // bean.setUseCount(loginHistory.getRunNumber());
		// // 找到上月的使用次数
		// Calendar cal = Calendar.getInstance();
		// cal.add(Calendar.MONTH, -1);
		//
		// Map<String, Object> param = new HashMap<String, Object>();
		// // 找到这个学校上月的数据
		//
		// param.put("orgId", bean.getOrgId());
		// param.put("year", cal.get(Calendar.YEAR));
		// param.put("month", cal.get(Calendar.MONTH) + 1);
		// param.put("platform", bean.getPlatformCode());
		//
		// if (loginHistory.getRunNumber() >= 3) {
		// bean.setActivityUserNum(1);// 活跃用户数
		// } else {
		// bean.setActivityUserNum(0);
		// }
		//
		// // TODO 计算活跃度
		// // 计算活跃度,查询该学校下的所有老师数量，在和活跃的老师的数量做比较，得到活跃度
		// int teacherNum = getTeacherNum(bean.getOrgId(),
		// bean.getPlatformCode());
		//
		// BigDecimal b1 = new BigDecimal(bean.getActivityUserNum());
		// BigDecimal b2 = new BigDecimal(teacherNum);
		// String useActivity = BigDecimalUtils.getPrettyNumber(
		// b1.divide(b2, 2, BigDecimal.ROUND_HALF_EVEN).multiply(new
		// BigDecimal(100)).toString());
		//
		// bean.setUseActivity(useActivity);// 用户活跃度
		//
		// // TODO 增长率
		// List<OrgMonthActivityCount> privBean =
		// getOrgMonthActivityCount(param);
		// if (privBean != null && privBean.size() > 0) {
		// bean.setPreviousUseActivity(privBean.get(0).getUseActivity());//
		// 上月的活跃度
		//
		// int rateNum = Integer.valueOf(bean.getUseActivity())
		// - Integer.valueOf(privBean.get(0).getUseActivity());
		//
		// BigDecimal bigDecimal = new BigDecimal(rateNum);
		//
		// BigDecimal bigDecimal2 = new
		// BigDecimal(privBean.get(0).getUseActivity());
		//
		// // String rate = BigDecimalUtils
		// //
		// .getPrettyNumber(bigDecimal.divide(bigDecimal2,2,BigDecimal.ROUND_HALF_EVEN).multiply(new
		// // BigDecimal(100)).toString());
		//
		// String rate = calculateRate(bigDecimal, bigDecimal2);
		//
		// bean.setRate(rate);
		// } else {
		// bean.setRate("0");
		// }
		// saveOrgMonthActivityCount(bean);
		// }
	}

	/**
	 * 保存到存档表，并删除原来的数据,保证查询效率
	 */
	public void saveLoingHistoryToFiling(List<LoginHistory> loginHistoryList) {
		for (LoginHistory bean : loginHistoryList) {
			loginHistoryDao.saveLoginHistoryFiling(bean);
			loginHistoryDao.deleteById(bean.getId());
		}
	}

	public List<LoginHistory> getUserCountByCurrentMonth() {
		Map<String, Object> parm = new HashMap<String, Object>();

		Date startTime = TimeUtil.getCurrentMonthStartDate();
		Date endTime = TimeUtil.getCurrentMonthEndDate();

		parm.put("startTime", CommonFunction.getDateSampleString(startTime));
		parm.put("endTime", CommonFunction.getDateSampleString(endTime));

		// parm.put("startTime", "2016-11-01");
		// parm.put("endTime", "2016-11-31");

		return loginHistoryDao.getUserCountByCurrentMonth(parm);
	}

	private void countOrgNum() {
		// List<LoginHistory> loginHistoryList = getUserCountByCurrentMonth();

		List<LoginHistory> loginHistoryList = getOrgUseCountByCurrentMonth();

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		for (LoginHistory bean : loginHistoryList) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("orgId", bean.getOrgId());
			param.put("year", year);
			param.put("month", month);
			param.put("platformCode", bean.getPlatformCode());
			param.put("areaCode", bean.getAreaCode());

			List<OrgMonthUsageCount> countList = getOrgMonthUsageCount(param);

			saveOrUpdateOrgMonthUsageCount(countList, bean, year, month);
		}
	}

	private void saveOrUpdateOrgMonthUsageCount(List<OrgMonthUsageCount> countList, LoginHistory loginHistory, int year,
			int month) {
		if (countList != null && countList.size() > 0) {// 更新操作
			OrgMonthUsageCount bean = countList.get(0);

			if (bean.getOrgId().equals(loginHistory.getOrgId()) && bean.getAreaCode().equals(loginHistory.getAreaCode())
					&& bean.getPlatformCode().equals(loginHistory.getPlatformCode())) {
				bean.setUseCount(bean.getUseCount() + loginHistory.getRunNumber());
				bean.setUpdateTime(new Date());

				// TODO 计算增长率
				if (bean.getPreviousUseCount() != null) {
					int rateNum = bean.getUseCount() - bean.getPreviousUseCount();
					BigDecimal bigDecimal = new BigDecimal(rateNum);
					BigDecimal bigDecimal2 = new BigDecimal(bean.getPreviousUseCount());
					String rate = calculateRate(bigDecimal, bigDecimal2);
					bean.setRate(rate);
				}
				updateOrgMonthUsageCount(bean);
			}

		} else {// 直接保存
			OrgMonthUsageCount bean = new OrgMonthUsageCount();
			bean.setOrgId(loginHistory.getOrgId());
			bean.setOrgName(loginHistory.getOrgName());
			bean.setAreaCode(loginHistory.getAreaCode());
			bean.setMonth(month);
			bean.setYear(year);
			bean.setUseCount(loginHistory.getRunNumber());
			bean.setPlatformCode(loginHistory.getPlatformCode());
			bean.setCreateTime(new Date());

			// 找到上月的使用次数
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);

			Map<String, Object> param = new HashMap<String, Object>();
			// 找到这个学校上月的数据
			param.put("orgId", bean.getOrgId());

			param.put("year", 2016);
			param.put("month", 11);

			// param.put("year", cal.get(Calendar.YEAR));
			// param.put("month", cal.get(Calendar.MONTH) + 1);

			param.put("areaCode", bean.getAreaCode());
			param.put("platformCode", bean.getPlatformCode());

			List<OrgMonthUsageCount> privBean = getOrgMonthUsageCount(param);

			if (privBean != null && privBean.size() > 0) {
				bean.setPreviousUseCount(privBean.get(0).getUseCount());

				/**
				 *
				 * 增长的部分除以原本的数目就是增长率。一般用百分比表示。
				 * 比如现在的某城市今年年初人口1000万，年末1006万，那么增长率就是增长的6万除以1000万，增长率=6/1000=0.
				 * 6%
				 *
				 */
				int rateNum = loginHistory.getRunNumber() - privBean.get(0).getUseCount();
				BigDecimal bigDecimal = new BigDecimal(rateNum);
				BigDecimal bigDecimal2 = new BigDecimal(privBean.get(0).getUseCount());
				// String rate = BigDecimalUtils
				//
				// .getPrettyNumber(bigDecimal.divide(bigDecimal2,2,BigDecimal.ROUND_HALF_EVEN).multiply(new
				// BigDecimal(100)).toString());
				// bean.setRate(rate);

				String rate = calculateRate(bigDecimal, bigDecimal2);
				bean.setRate(rate);
			} else {
				bean.setRate("0");
			}
			saveOrgMonthUsageCount(bean);
		}

	}

	/**
	 * 统计区域次数
	 */
	private void countAreaNum() {
		// 获得区域当月使用次数
		List<LoginHistory> loginHistoryList = getOrgUseCountByCurrentMonth();

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		// int year = 2016;
		// int month = 11;

		for (LoginHistory bean : loginHistoryList) {

			// 找到学校对应的月统计信息
			Map<String, Object> param = new HashMap<String, Object>();
			// param.put("orgId", bean.getOrgId());

			param.put("areaCode", bean.getAreaCode());
			param.put("year", year);
			param.put("month", month);
			param.put("platformCode", bean.getPlatformCode());
			param.put("orgId", bean.getOrgId());
			List<AreaMonthUsageCount> countList = getAreaMonthUsageCount(param);
			saveOrUpdateAreaMonthUsageCount(countList, bean, year, month);
		}
	}

	private void saveOrUpdateAreaMonthUsageCount(List<AreaMonthUsageCount> countList, LoginHistory loginHistory,
			int year, int month) {
		if (countList != null && countList.size() > 0) {// 更新操作
			AreaMonthUsageCount bean = countList.get(0);

			if (bean.getAreaCode() != null && bean.getPlatformCode() != null && loginHistory.getAreaCode() != null
					&& loginHistory.getPlatformCode() != null) {
				if (bean.getOrgId().equals(loginHistory.getOrgId())
						&& bean.getAreaCode().equals(loginHistory.getAreaCode())
						&& bean.getPlatformCode().equals(loginHistory.getPlatformCode())) {

					bean.setUseCount(bean.getUseCount() + loginHistory.getRunNumber());
					bean.setUpdateTime(new Date());

					// Integer orgNum = getOrgNum(bean.getAreaCode(),
					// bean.getPlatformCode());// 区域学校数量

					// if (orgNum != null) {
					// BigDecimal b1 = new
					// BigDecimal(loginHistory.getRunNumber());
					// BigDecimal b2 = new BigDecimal(orgNum);
					//
					// String usePer = calculateRate(b1, b2);
					//
					// bean.setAreaPercent(usePer);
					// } else {
					// bean.setAreaPercent("0");
					// }

					// TODO 重新计算增长率
					if (bean.getPreviousUseCount() != null) {
						int rateNum = bean.getUseCount() - bean.getPreviousUseCount();
						BigDecimal bigDecimal = new BigDecimal(rateNum);
						BigDecimal bigDecimal2 = new BigDecimal(bean.getPreviousUseCount());
						// String rate = BigDecimalUtils.getPrettyNumber(
						// bigDecimal.divide(bigDecimal2,2,BigDecimal.ROUND_HALF_EVEN).multiply(new
						// BigDecimal(100)).toString());
						// bean.setRate(rate);

						String rate = calculateRate(bigDecimal, bigDecimal2);
						bean.setRate(rate);
					}
					updateAreaMonthUsageCount(bean);
				}
			}

		} else {// 直接保存
			AreaMonthUsageCount bean = new AreaMonthUsageCount();
			bean.setAreaCode(loginHistory.getAreaCode());
			bean.setMonth(month);
			bean.setYear(year);
			bean.setUseCount(loginHistory.getRunNumber());
			bean.setPlatformCode(loginHistory.getPlatformCode());
			bean.setCreateTime(new Date());

			bean.setOrgId(loginHistory.getOrgId());
			bean.setOrgName(loginHistory.getOrgName());
			// 统计占区域百分比
			// Integer orgNum = getOrgNum(loginHistory.getAreaCode(),
			// loginHistory.getPlatformCode());// 区域学校数量
			//
			// if (orgNum != null) {
			// BigDecimal b1 = new BigDecimal(loginHistory.getRunNumber());
			// BigDecimal b2 = new BigDecimal(orgNum);
			//
			// if (b2.toString().equals("0")) {
			//
			// String usePer = calculateRate(b1, b2);
			//
			// // bean.setUseActivity(useActivity);// 用户活跃度
			//
			// bean.setAreaPercent(usePer);
			// }
			//
			// } else {
			// bean.setAreaPercent("0");
			// }

			// 找到上月的使用次数
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);

			Map<String, Object> param = new HashMap<String, Object>();
			// 找到这个学校上月的数据

			param.put("areaCode", bean.getAreaCode());
			param.put("year", cal.get(Calendar.YEAR));
			param.put("month", cal.get(Calendar.MONTH) + 1);

			// param.put("year", 2016);
			// param.put("month", 10);

			param.put("platformCode", bean.getPlatformCode());
			param.put("orgId", bean.getOrgId());
			// 找到上月本区域的数据
			List<AreaMonthUsageCount> privBean = getAreaMonthUsageCount(param);
			// 如果找到则计算增长率，否则设置增长率为0
			if (privBean != null && privBean.size() > 0) {
				bean.setPreviousUseCount(privBean.get(0).getUseCount());
				/**
				 * 
				 * 增长的部分除以原本的数目就是增长率。一般用百分比表示。
				 * 比如现在的某城市今年年初人口1000万，年末1006万，那么增长率就是增长的6万除以1000万，增长率=6/1000=0.
				 * 6%
				 * 
				 */
				int rateNum = loginHistory.getRunNumber() - privBean.get(0).getUseCount();
				BigDecimal bigDecimal = new BigDecimal(rateNum);
				BigDecimal bigDecimal2 = new BigDecimal(privBean.get(0).getUseCount());
				String rate = calculateRate(bigDecimal, bigDecimal2);
				bean.setRate(rate);
			} else {
				bean.setRate("0");
			}
			saveAreaMonthUsageCount(bean);
		}
	}

	class UserClass {
		private String classId;
		private String className;
		private Integer classNum;// 学生人数

		public Integer getClassNum() {
			return classNum;
		}

		public void setClassNum(Integer classNum) {
			this.classNum = classNum;
		}

		public String getClassId() {
			return classId;
		}

		public void setClassId(String classId) {
			this.classId = classId;
		}

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}
	}

	@Override
	public void clearScanCodeData() {
		List<ScanCodeUserInfo> scanCodeUserInfos = mongoDBBaseDao.getMongoTemplate().findAll(ScanCodeUserInfo.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		/*
		 * 前一天的此时此刻
		 */
		long campareTime = new Date().getTime() - (24 * 3600 * 1000);

		logger.info("start clear the data of scancode");
		for (ScanCodeUserInfo item : scanCodeUserInfos) {
			try {
				String guid = item.getGuid();
				String createTimeStr = item.getCreateTime();
				long createTime = sdf.parse(createTimeStr).getTime();
				if (createTime > campareTime)
					continue;
				Query query = new Query(Criteria.where("guid").is(guid));
				mongoDBBaseDao.getMongoTemplate().remove(query, ScanCodeUserInfo.class);
				/*
				 * 清除guid对应 的localNetworkInfo表
				 */
				mongoDBBaseDao.getMongoTemplate().findAllAndRemove(query, LocalNetworkInfo.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.info("finish clear the data of scancode finish");
	}
}