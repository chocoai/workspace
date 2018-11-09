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
import com.whty.assis.devicemanage.dao.BrowserWhitelistDao;
import com.whty.assis.devicemanage.dao.BrowserWhitelistRuleDao;
import com.whty.assis.devicemanage.dao.SchoolClassRuleDao;
import com.whty.assis.devicemanage.model.BrowserWhitelist;
import com.whty.assis.devicemanage.model.BrowserWhitelistRule;
import com.whty.assis.devicemanage.model.SchoolClassRule;
import com.whty.assis.devicemanage.service.BrowserWhitelistRuleService;
import com.whty.assis.sysres.model.TaManageUserInfo;
import com.whty.common.util.SysConfig;
import com.whty.common.util.SysConstants;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年4月24日
 */
@Service
public class BrowserWhitelistRuleServiceImpl implements BrowserWhitelistRuleService {

	@Autowired
	private BrowserWhitelistRuleDao browserWhitelistRuleDao;

	@Autowired
	private BrowserWhitelistDao browserWhitelistDao;

	@Autowired
	private IdDao idDao;

	@Autowired
	private SchoolClassRuleDao schoolClassRuleDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.BrowserWhitelistRuleService#
	 * saveBrowserWhitelistRule(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String saveBrowserWhitelistRule(HttpServletRequest request) {
		String name = request.getParameter("name");
		String memo = request.getParameter("memo");

		String[] urlList = request.getParameterValues("urlList");

		Map<String, Object> idParam = new HashMap<String, Object>(2);
		idParam.put("databaseName", SysConfig.getStrValue("databaseName"));
		idParam.put("tableName", SysConfig.getStrValue("t_browser_whitelist_rule"));

		Integer browserWhitelistRuleId = idDao.getId(idParam);

		BrowserWhitelistRule bean = new BrowserWhitelistRule();
		bean.setName(name);
		bean.setCreateTime(new Date());
		bean.setUpdateTime(new Date());
		bean.setMemo(memo);
		TaManageUserInfo mUser = (TaManageUserInfo) request.getSession().getAttribute(SysConstants.SESSION_USER);
		bean.setCreator(mUser.getId());

		if (urlList != null) {
			for (int i = 0; i < urlList.length; i++) {
				BrowserWhitelist browserWhitelist = new BrowserWhitelist();
				browserWhitelist.setUrl(urlList[i]);
				browserWhitelist.setBrowserWhitelistRuleId(browserWhitelistRuleId);
				browserWhitelistDao.save(browserWhitelist);
			}
		}
		browserWhitelistRuleDao.save(bean);
		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.BrowserWhitelistRuleService#
	 * listByCondition(java.util.Map)
	 */
	@Override
	public List<BrowserWhitelistRule> listByCondition(Map<String, Object> paramMap) {
		return browserWhitelistRuleDao.listByCondition(paramMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.BrowserWhitelistRuleService#
	 * deleteBrowserWhitelistRule(java.lang.Integer)
	 */
	@Override
	public String deleteBrowserWhitelistRule(Integer id) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("browserWhitelistRuleId", Integer.valueOf(id));
		List<SchoolClassRule> schoolClassRuleList = schoolClassRuleDao.listByCondition(param);

		if (schoolClassRuleList != null && schoolClassRuleList.size() > 0) {
			return "该规则被使用，不能删除";
		}

		browserWhitelistRuleDao.deleteById(id);

		browserWhitelistDao.deleteByRuleId(id);

		return SysConstants.SUCCESS;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.BrowserWhitelistRuleService#
	 * queryBrowserWhitelistRulePage(java.util.Map)
	 */
	@Override
	public HandlerResult queryBrowserWhitelistRulePage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(browserWhitelistRuleDao.listByCondition(paramMap));
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.BrowserWhitelistRuleService#
	 * queryBrowserWhitelistRulePage(java.util.Map, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult queryBrowserWhitelistRulePage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(browserWhitelistRuleDao.listByCondition(paraMap));
		rs.setPage(page);
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.BrowserWhitelistRuleService#
	 * updateBrowserWhitelistRule(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String updateBrowserWhitelistRule(HttpServletRequest request) {
		String ruleId = request.getParameter("ruleId");

		browserWhitelistDao.deleteByRuleId(Integer.valueOf(ruleId));

		String name = request.getParameter("name");
		String memo = request.getParameter("memo");

		String[] urlList = request.getParameterValues("urlList");

		BrowserWhitelistRule bean = browserWhitelistRuleDao.loadById(Integer.valueOf(ruleId));

		bean.setName(name);
		bean.setMemo(memo);
		bean.setUpdateTime(new Date());

		if (urlList != null) {
			for (int i = 0; i < urlList.length; i++) {
				BrowserWhitelist browserWhitelist = new BrowserWhitelist();
				browserWhitelist.setUrl(urlList[i]);
				browserWhitelist.setBrowserWhitelistRuleId(Integer.valueOf(ruleId));
				browserWhitelistDao.save(browserWhitelist);
			}
		}

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.BrowserWhitelistRuleService#
	 * detailBrowserWhitelistRule(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public BrowserWhitelistRule detailBrowserWhitelistRule(HttpServletRequest request) {
		String ruleId = request.getParameter("id");

		BrowserWhitelistRule bean = browserWhitelistRuleDao.loadById(Integer.valueOf(ruleId));

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("browserWhitelistRuleId", ruleId);
		List<BrowserWhitelist> browserWhitelist = browserWhitelistDao.listByCondition(param);
		bean.setBrowserWhitelist(browserWhitelist);

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.BrowserWhitelistRuleService#
	 * listBySchoolClassRule(java.lang.Integer)
	 */
	@Override
	public List<BrowserWhitelistRule> listBySchoolClassRule(Integer schoolClassId) {
		return browserWhitelistRuleDao.listBySchoolClassRule(schoolClassId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.BrowserWhitelistRuleService#
	 * queryBySchoolClassRule(java.lang.Integer, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult queryBySchoolClassRule(Integer valueOf, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(browserWhitelistRuleDao.listBySchoolClassRule(valueOf));
		rs.setPage(page);
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.BrowserWhitelistRuleService#
	 * getwhiteListBySchoolClassId(java.lang.Integer)
	 */
	@Override
	public List<BrowserWhitelist> getwhiteListBySchoolClassId(Integer schoolId) {
		return browserWhitelistDao.getwhiteListBySchoolClassId(schoolId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.BrowserWhitelistRuleService#
	 * getBroserWhitelistByRuleId(java.lang.Integer)
	 */
	@Override
	public List<BrowserWhitelist> getBroserWhitelistByRuleId(Integer browserWhitelistRuleId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("browserWhitelistRuleId", browserWhitelistRuleId);
		return browserWhitelistDao.listByCondition(param);
	}

}