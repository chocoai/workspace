/**
 * 
 */
package com.whty.assis.devicemanage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.basicdata.dao.IdDao;
import com.whty.assis.devicemanage.dao.AppWhitelistDao;
import com.whty.assis.devicemanage.dao.AppWhitelistRuleDao;
import com.whty.assis.devicemanage.dao.SchoolClassRuleDao;
import com.whty.assis.devicemanage.model.AppWhitelist;
import com.whty.assis.devicemanage.model.AppWhitelistRule;
import com.whty.assis.devicemanage.model.SchoolClassRule;
import com.whty.assis.devicemanage.service.AppWhitelistRuleService;
import com.whty.assis.sysres.model.TaManageUserInfo;
import com.whty.common.util.SysConfig;
import com.whty.common.util.SysConstants;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年5月9日
 */
@Service
public class AppWhitelistRuleServiceImpl implements AppWhitelistRuleService {

	@Autowired
	private AppWhitelistRuleDao appWhitelistRuleDao;

	@Autowired
	private IdDao idDao;

	@Autowired
	private AppWhitelistDao appWhitelistDao;

	@Autowired
	private SchoolClassRuleDao schoolClassRuleDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.AppWhitelistRuleService#
	 * saveAppWhitelistRule(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String saveAppWhitelistRule(HttpServletRequest request) {
		String name = request.getParameter("name");
		String memo = request.getParameter("memo");

		String[] appIdList = request.getParameterValues("appIdList");

		Map<String, Object> idParam = new HashMap<String, Object>(2);
		idParam.put("databaseName", SysConfig.getStrValue("databaseName"));
		idParam.put("tableName", SysConfig.getStrValue("t_app_whitelist"));

		Integer appWhitelistRuleId = idDao.getId(idParam);

		AppWhitelistRule bean = new AppWhitelistRule();

		bean.setName(name);
		bean.setCreateTime(new Date());
		bean.setUpdateTime(new Date());
		bean.setMemo(memo);

		TaManageUserInfo mUser = (TaManageUserInfo) request.getSession().getAttribute(SysConstants.SESSION_USER);
		bean.setCreator(mUser.getId());// TODO

		if (appIdList != null) {
			for (int i = 0; i < appIdList.length; i++) {
				AppWhitelist appWhitelist = new AppWhitelist();

				appWhitelist.setAppInfoId(Integer.valueOf(appIdList[i]));
				appWhitelist.setAppWhitelistRuleId(appWhitelistRuleId);

				appWhitelistDao.save(appWhitelist);
			}
		}
		appWhitelistRuleDao.save(bean);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.AppWhitelistRuleService#
	 * listByCondition(java.util.Map)
	 */
	@Override
	public List<AppWhitelistRule> listByCondition(Map<String, Object> paramMap) {
		return appWhitelistRuleDao.listByCondition(paramMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.AppWhitelistRuleService#
	 * deleteAppWhitelistRule(java.lang.Integer)
	 */
	@Override
	public String deleteAppWhitelistRule(Integer id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("appWhitelistRuleId", Integer.valueOf(id));
		List<SchoolClassRule> schoolClassRuleList = schoolClassRuleDao.listByCondition(param);

		if (schoolClassRuleList != null && schoolClassRuleList.size() > 0) {
			return "该规则被使用，不能删除";
		}

		appWhitelistRuleDao.deleteById(id);

		appWhitelistDao.deleteByRuleId(id);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.AppWhitelistRuleService#
	 * queryAppWhitelistRulePage(java.util.Map)
	 */
	@Override
	public HandlerResult queryAppWhitelistRulePage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(appWhitelistRuleDao.listByCondition(paramMap));
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.AppWhitelistRuleService#
	 * queryAppWhitelistRulePage(java.util.Map, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult queryAppWhitelistRulePage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(appWhitelistRuleDao.listByCondition(paraMap));
		rs.setPage(page);
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.AppWhitelistRuleService#
	 * updateAppWhitelistRule(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String updateAppWhitelistRule(HttpServletRequest request) {
		String ruleId = request.getParameter("ruleId");

		appWhitelistDao.deleteByRuleId(Integer.valueOf(ruleId));

		String name = request.getParameter("name");
		String memo = request.getParameter("memo");

		String[] appIdList = request.getParameterValues("appIdList");

		AppWhitelistRule bean = appWhitelistRuleDao.loadById(Integer.valueOf(ruleId));

		bean.setName(name);
		bean.setMemo(memo);
		bean.setUpdateTime(new Date());

		if (appIdList != null) {
			for (int i = 0; i < appIdList.length; i++) {

				AppWhitelist appWhitelist = new AppWhitelist();

				appWhitelist.setAppInfoId(Integer.valueOf(appIdList[i]));
				appWhitelist.setAppWhitelistRuleId(bean.getId());

				appWhitelistDao.save(appWhitelist);
			}
		}

		appWhitelistRuleDao.update(bean);
		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.AppWhitelistRuleService#
	 * detailAppWhitelistRule(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public AppWhitelistRule detailAppWhitelistRule(HttpServletRequest request) {
		String ruleId = request.getParameter("id");

		AppWhitelistRule bean = appWhitelistRuleDao.loadById(Integer.valueOf(ruleId));

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("appWhitelistRuleId", ruleId);
		List<AppWhitelist> appWhitelist = appWhitelistDao.listByCondition(param);

		bean.setAppWhitelist(appWhitelist);

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.AppWhitelistRuleService#
	 * listBySchoolClassRule(java.lang.Integer)
	 */
	@Override
	public HandlerResult queryBySchoolClassRule(Integer schoolClassId, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(appWhitelistRuleDao.listBySchoolClassRule(schoolClassId));
		rs.setPage(page);
		return rs;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.AppWhitelistRuleService#
	 * listBySchoolClassRule(java.lang.Integer)
	 */
	@Override
	public List<AppWhitelistRule> listBySchoolClassRule(Integer schoolClassId) {
		return appWhitelistRuleDao.listBySchoolClassRule(schoolClassId);
	}

}
