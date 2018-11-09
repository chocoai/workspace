/**
 * 
 */
package com.whty.assis.mall.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.basicdata.dao.IdDao;
import com.whty.assis.mall.dao.AppPushDao;
import com.whty.assis.mall.model.AppPush;
import com.whty.assis.mall.service.AppPushService;
import com.whty.assis.sysres.model.TaManageUserInfo;
import com.whty.common.thread.AppPushThread;
import com.whty.common.util.SysConfig;
import com.whty.common.util.SysConstants;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年4月18日
 */

@Service
public class AppPushServiceImpl implements AppPushService {

	@Autowired
	private AppPushDao appPushDao;

	@Autowired
	private IdDao idDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.mall.service.AppPushService#saveAppPush(javax.servlet.http
	 * .HttpServletRequest)
	 */
	@Override
	public String saveAppPush(HttpServletRequest request) {
		String[] appId = request.getParameterValues("appId");

		String provinceCode = request.getParameter("provinceCode");
		String cityCode = request.getParameter("cityCode");
		String areaCode = request.getParameter("areaCode");
		String schoolId = request.getParameter("schoolId");
		String classId = request.getParameter("classId");

		if (provinceCode.equals("-1")) {
			provinceCode = null;
		}
		if (cityCode.equals("-1")) {
			cityCode = null;
		}

		if (areaCode.equals("-1")) {
			areaCode = null;
		}

		if (schoolId.equals("-1")) {
			schoolId = null;
		}

		if (classId.equals("-1")) {
			classId = null;
		}
		for (int i = 0; i < appId.length; i++) {
			AppPush bean = new AppPush();

			Map<String, Object> idParam = new HashMap<String, Object>(2);
			idParam.put("databaseName", SysConfig.getStrValue("databaseName"));
			idParam.put("tableName", SysConfig.getStrValue("t_app_push"));
			Integer appPushId = idDao.getId(idParam);

			bean.setCreateTime(new Date());
			bean.setUpdateTime(new Date());
			bean.setAppId(Integer.valueOf(appId[i]));

			if (StringUtils.isNotEmpty(schoolId) && !"null".equals(schoolId)) {
				bean.setSchoolId(Integer.valueOf(schoolId));
			}

			if (StringUtils.isNotEmpty(classId) && !"null".equals(classId)) {
				bean.setClassId(Integer.valueOf(classId));
			}

			bean.setProvinceCode(provinceCode);
			bean.setCityCode(cityCode);
			bean.setAreaCode(areaCode);
			TaManageUserInfo mUser = (TaManageUserInfo) request.getSession().getAttribute(SysConstants.SESSION_USER);
			bean.setCreator(mUser.getId());// TODO
			appPushDao.save(bean);

			bean.setId(appPushId);// 必须先保存在设置id值,保持数据一致

			AppPushThread.add(bean);

		}
		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.mall.service.AppPushService#deleteAppPush(java.lang.
	 * Integer)
	 */
	@Override
	public String deleteAppPush(Integer id) {
		appPushDao.deleteById(id);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.mall.service.AppPushService#listByCondition(java.util.Map)
	 */
	@Override
	public List<AppPush> listByCondition(Map<String, Object> paramMap) {
		return appPushDao.listByCondition(paramMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.mall.service.AppPushService#queryAppPushPage(java.util.
	 * Map)
	 */
	@Override
	public HandlerResult queryAppPushPage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(appPushDao.listByCondition(paramMap));
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.mall.service.AppPushService#updateAppPush(com.whty.assis.
	 * mall.model.AppPush)
	 */
	@Override
	public void updateAppPush(AppPush bean) {
		appPushDao.update(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.mall.service.AppPushService#queryAppPushPage(java.util.
	 * Map, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult queryAppPushPage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(appPushDao.listByCondition(paraMap));
		rs.setPage(page);
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.mall.service.AppPushService#saveAppPush(com.whty.assis.
	 * mall.model.AppPush)
	 */
	@Override
	public String saveAppPush(AppPush bean) {
		appPushDao.save(bean);
		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.mall.service.AppPushService#rePushApp(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	public String rePushApp(HttpServletRequest request) {
		String appId = request.getParameter("appId");

		AppPush bean = appPushDao.loadById(Integer.valueOf(appId));

		AppPushThread.add(bean);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.mall.service.AppPushService#deleteAppPush(javax.servlet.
	 * http.HttpServletRequest)
	 */
	@Override
	public String deleteAppPush(HttpServletRequest request) {
		String appId = request.getParameter("appId");

		AppPush bean = appPushDao.loadById(Integer.valueOf(appId));

		appPushDao.deleteById(Integer.valueOf(appId));

		AppPushThread.add(bean);

		return SysConstants.SUCCESS;
	}

}
