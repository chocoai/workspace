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
import com.whty.assis.devicemanage.dao.SchoolClassRuleDao;
import com.whty.assis.devicemanage.dao.UrlBlackListDao;
import com.whty.assis.devicemanage.dao.UrlBlackListRuleDao;
import com.whty.assis.devicemanage.model.SchoolClassRule;
import com.whty.assis.devicemanage.model.UrlBlackList;
import com.whty.assis.devicemanage.model.UrlBlackListRule;
import com.whty.assis.devicemanage.service.UrlBlackListRuleService;
import com.whty.common.util.SysConfig;
import com.whty.common.util.SysConstants;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年4月21日
 */
@Service
public class UrlBlackListRuleServiceImpl implements UrlBlackListRuleService {

	@Autowired
	private UrlBlackListRuleDao urlBlackListRuleDao;

	@Autowired
	private IdDao idDao;

	@Autowired
	private UrlBlackListDao urlBlackListDao;

	@Autowired
	private SchoolClassRuleDao schoolClassRuleDao;


	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.UrlBlackListRuleService
	 * saveUrlBlackListRule(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String saveUrlBlackListRule(HttpServletRequest request) {
		String name = request.getParameter("name");
		String memo = request.getParameter("memo");

		String[] urlList = request.getParameterValues("urlList");

		Map<String, Object> idParam = new HashMap<String, Object>(2);
		idParam.put("databaseName", SysConfig.getStrValue("databaseName"));
		idParam.put("tableName", SysConfig.getStrValue("t_url_blacklist_rule"));
		Integer urlBlackListRuleId = idDao.getId(idParam);

		UrlBlackListRule bean = new UrlBlackListRule();

		bean.setName(name);
		bean.setCreateTime(new Date());
		bean.setUpdateTime(new Date());
		bean.setMemo(memo);

		if (urlList != null) {
			for (int i = 0; i < urlList.length; i++) {
				UrlBlackList urlBlackList = new UrlBlackList();
				urlBlackList.setUrl(urlList[i]);
				urlBlackList.setUpdateTime(new Date());
				urlBlackList.setCreateTime(new Date());
				urlBlackList.setUrlBlackListRuleId(urlBlackListRuleId);
				urlBlackListDao.save(urlBlackList);
			}
		}

		urlBlackListRuleDao.save(bean);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.UrlBlackListRuleService#
	 * listByCondition(java.util.Map)
	 */
	@Override
	public List<UrlBlackListRule> listByCondition(Map<String, Object> paramMap) {
		return urlBlackListRuleDao.listByCondition(paramMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.UrlBlackListRuleService#
	 * deleteUrlBlackList(java.lang.Integer)
	 */
	@Override
	public String deleteUrlBlackListRule(Integer id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("urlBlacklistRuleId", Integer.valueOf(id));
		List<SchoolClassRule> schoolClassRuleList = schoolClassRuleDao.listByCondition(param);

		if (schoolClassRuleList != null && schoolClassRuleList.size() > 0) {
			return "该规则被使用，不能删除";
		}

		urlBlackListRuleDao.deleteById(id);

		urlBlackListDao.deleteByRuleId(id);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.UrlBlackListRuleService#
	 * queryUrlBlackListPage(java.util.Map)
	 */
	@Override
	public HandlerResult queryUrlBlackListRulePage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(urlBlackListRuleDao.listByCondition(paramMap));
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.UrlBlackListRuleService#
	 * queryUrlBlackListPage(java.util.Map, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult queryUrlBlackListRulePage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(urlBlackListRuleDao.listByCondition(paraMap));
		rs.setPage(page);
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.UrlBlackListRuleService#
	 * updateUrlBlackList(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String updateUrlBlackList(HttpServletRequest request) {
		String ruleId = request.getParameter("ruleId");

		urlBlackListDao.deleteByRuleId(Integer.valueOf(ruleId));

		String name = request.getParameter("name");
		String memo = request.getParameter("memo");

		String[] urlList = request.getParameterValues("urlList");

		UrlBlackListRule bean = urlBlackListRuleDao.loadById(Integer.valueOf(ruleId));

		bean.setName(name);
		bean.setMemo(memo);
		bean.setUpdateTime(new Date());

		if (urlList != null) {
			for (int i = 0; i < urlList.length; i++) {
				UrlBlackList urlBlackList = new UrlBlackList();
				urlBlackList.setUrl(urlList[i]);
				urlBlackList.setUpdateTime(new Date());
				urlBlackList.setCreateTime(new Date());
				urlBlackList.setUrlBlackListRuleId(Integer.valueOf(ruleId));
				urlBlackListDao.save(urlBlackList);
			}
		}

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.UrlBlackListRuleService#
	 * detailUrlBlackList(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public UrlBlackListRule detailUrlBlackList(HttpServletRequest request) {
		String ruleId = request.getParameter("id");

		UrlBlackListRule bean = urlBlackListRuleDao.loadById(Integer.valueOf(ruleId));

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("urlBlackListRuleId", ruleId);
		List<UrlBlackList> urlBlackList = urlBlackListDao.listByCondition(param);

		bean.setUrlList(urlBlackList);

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.UrlBlackListRuleService#
	 * listBySchoolClassRule(java.lang.Integer)
	 */
	@Override
	public List<UrlBlackListRule> listBySchoolClassRule(Integer schoolClassId) {
		return urlBlackListRuleDao.listBySchoolClassRule(schoolClassId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.UrlBlackListRuleService#
	 * queryBySchoolClassRule(java.lang.Integer, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult queryBySchoolClassRule(Integer valueOf, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(urlBlackListRuleDao.listBySchoolClassRule(valueOf));
		rs.setPage(page);
		return rs;
	}

	/* (non-Javadoc)
	 * @see com.whty.assis.devicemanage.service.UrlBlackListRuleService#getUrlBlackListBySchoolClassId(java.lang.Integer)
	 */
	@Override
	public List<UrlBlackList> getUrlBlackListByRuleId(Integer urlBlackListRuleId) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("urlBlackListRuleId", urlBlackListRuleId);
		return urlBlackListDao.listByCondition(param);		
	}

	/* (non-Javadoc)
	 * @see com.whty.assis.devicemanage.service.UrlBlackListRuleService#getUrlBlackListBySchooClassId(java.lang.Integer)
	 */
	@Override
	public List<UrlBlackList> getUrlBlackListBySchooClassId(Integer schoolClassId) {
		return urlBlackListDao.getUrlBlackListBySchooClassId(schoolClassId);
	}

}
