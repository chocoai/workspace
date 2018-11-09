/**
 * 
 */
package com.whty.assis.devicemanage.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.devicemanage.dao.AppNetBlacklistDao;
import com.whty.assis.devicemanage.model.AppNetBlacklist;
import com.whty.assis.devicemanage.service.AppNetBlacklistService;
import com.whty.assis.sysres.model.TaManageUserInfo;
import com.whty.common.util.SysConstants;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

import ch.qos.logback.core.sift.AppenderTracker;

/**
 * @author zhangzheng
 * @date 2018年6月14日
 */
@Service
public class AppNetBlacklistServiceImpl implements AppNetBlacklistService {

	@Autowired
	private AppNetBlacklistDao appNetBlacklistDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.AppNetBlacklistService#
	 * saveAppNetBlacklist(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String saveAppNetBlacklist(HttpServletRequest request) {
		String pkg = request.getParameter("pkg");
		String memo = request.getParameter("memo");
		String name = request.getParameter("name");
		String versionCode = request.getParameter("versionCode");
		String version = request.getParameter("version");
		String icon = request.getParameter("icon");
		AppNetBlacklist appNetBlacklist = new AppNetBlacklist();

		appNetBlacklist.setIcon(icon);
		appNetBlacklist.setCreateTime(new Date());
		appNetBlacklist.setUpdateTime(new Date());
		appNetBlacklist.setPkg(pkg);
		appNetBlacklist.setMemo(memo);
		appNetBlacklist.setName(name);
		appNetBlacklist.setVersion(version);
		appNetBlacklist.setVersionCode(versionCode);
		TaManageUserInfo mUser = (TaManageUserInfo) request.getSession().getAttribute(SysConstants.SESSION_USER);

		appNetBlacklist.setCreator(mUser.getId());

		appNetBlacklistDao.save(appNetBlacklist);
		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.AppNetBlacklistService#
	 * listByCondition(java.util.Map)
	 */
	@Override
	public List<AppNetBlacklist> listByCondition(Map<String, Object> paramMap) {

		return appNetBlacklistDao.listByCondition(paramMap);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.AppNetBlacklistService#
	 * deleteAppNetBlacklist(java.lang.Integer)
	 */
	@Override
	public String deleteAppNetBlacklist(Integer id) {

		appNetBlacklistDao.deleteById(id);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.AppNetBlacklistService#
	 * queryAppNetBlacklistPage(java.util.Map)
	 */
	@Override
	public HandlerResult queryAppNetBlacklistPage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(appNetBlacklistDao.listByCondition(paramMap));
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.AppNetBlacklistService#
	 * queryAppNetBlacklistPage(java.util.Map, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult queryAppNetBlacklistPage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(appNetBlacklistDao.listByCondition(paraMap));
		rs.setPage(page);
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.AppNetBlacklistService#
	 * updateAppNetBlacklist(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String updateAppNetBlacklist(HttpServletRequest request) {
		// String pkg = request.getParameter("pkg");
		// String memo = request.getParameter("memo");
		// String name = request.getParameter("name");
		// String versionCode = request.getParameter("versionCode");
		// String version = request.getParameter("version");
		// String id = request.getParameter("id");
		//

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.AppNetBlacklistService#
	 * detailAppNetBlacklist(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public AppNetBlacklist detailAppNetBlacklist(HttpServletRequest request) {
		String id = request.getParameter("id");
		AppNetBlacklist bean = appNetBlacklistDao.loadById(Integer.valueOf(id));
		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.AppNetBlacklistService#
	 * queryBySchoolClassRule(java.lang.Integer, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult queryBySchoolClassRule(Integer schoolClassId, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(appNetBlacklistDao.listBySchoolClassRule(schoolClassId));
		rs.setPage(page);
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.AppNetBlacklistService#
	 * listBySchoolClassRule(java.lang.Integer)
	 */
	@Override
	public List<AppNetBlacklist> listBySchoolClassRule(Integer schoolClassId) {
		return null;
	}

	

}
