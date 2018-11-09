/**
 * 
 */
package com.whty.assis.devicemanage.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.basicdata.model.SchoolClassAppWhitelistRule;
import com.whty.assis.basicdata.model.SchoolClassBrowserTagRule;
import com.whty.assis.basicdata.model.SchoolClassBrowserWhitelistRule;
import com.whty.assis.basicdata.model.SchoolClassUrlBlacklistRule;
import com.whty.assis.basicdata.model.SchoolClassUsetimeRule;
import com.whty.assis.devicemanage.dao.SchoolClassRuleDao;
import com.whty.assis.devicemanage.model.SchoolClassAppNetBlacklist;
import com.whty.assis.devicemanage.model.SchoolClassRule;
import com.whty.assis.devicemanage.service.SchoolClassRuleService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年5月3日
 */
@Service
public class SchoolClassRuleServiceImpl implements SchoolClassRuleService {

	@Autowired
	private SchoolClassRuleDao schoolClassRuleDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#
	 * saveSchoolClassRule(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String saveSchoolClassRule(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#
	 * listByCondition(java.util.Map)
	 */
	@Override
	public List<SchoolClassRule> listByCondition(Map<String, Object> paramMap) {
		return schoolClassRuleDao.listByCondition(paramMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#
	 * deleteSchoolClassRule(java.lang.Integer)
	 */
	@Override
	public String deleteSchoolClassRule(Integer id) {

		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#
	 * querySchoolClassRulePage(java.util.Map)
	 */
	@Override
	public HandlerResult querySchoolClassRulePage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(schoolClassRuleDao.listByCondition(paramMap));
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#
	 * querySchoolClassRulePage(java.util.Map, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult querySchoolClassRulePage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(schoolClassRuleDao.listByCondition(paraMap));
		rs.setPage(page);
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#
	 * updateSchoolClassRule(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String updateSchoolClassRule(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#
	 * detailSchoolClassRule(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public SchoolClassRule detailSchoolClassRule(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.devicemanage.service.SchoolClassRuleService#listGradeTree(
	 * java.lang.Integer)
	 */
	@Override
	public List<Map<String, Object>> listGradeTree(Integer schoolId) {
		return schoolClassRuleDao.listGradeTree(schoolId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#
	 * getBySchoolClassId(java.lang.Integer)
	 */
	@Override
	public SchoolClassRule getBySchoolClassId(Integer schoolClassId) {
		return schoolClassRuleDao.getBySchoolClassId(schoolClassId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#
	 * updateSchoolClassRule(com.whty.assis.devicemanage.model.SchoolClassRule)
	 */
	@Override
	public String updateSchoolClassRule(SchoolClassRule bean) {
		schoolClassRuleDao.update(bean);
		return "success";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#
	 * saveSchoolClassRule(com.whty.assis.devicemanage.model.SchoolClassRule)
	 */
	@Override
	public String saveSchoolClassRule(SchoolClassRule bean) {
		schoolClassRuleDao.save(bean);
		return "success";
	}

	/* (non-Javadoc)
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#deleteSchoolClassRule(com.whty.assis.devicemanage.model.SchoolClassRule)
	 */
	@Override
	public void deleteSchoolClassRule(SchoolClassRule bean) {
		schoolClassRuleDao.deleteSchoolClassRule(bean);
	}

	/* (non-Javadoc)
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#saveSchoolClassUsetimeRule(com.whty.assis.basicdata.model.SchoolClassUsetimeRule)
	 */
	@Override
	public void saveSchoolClassUsetimeRule(SchoolClassUsetimeRule bean) {
		schoolClassRuleDao.saveSchoolClassUsetimeRule(bean);
	}

	/* (non-Javadoc)
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#saveSchoolClassBrowserWhitelistRule(com.whty.assis.basicdata.model.SchoolClassBrowserWhitelistRule)
	 */
	@Override
	public void saveSchoolClassBrowserWhitelistRule(SchoolClassBrowserWhitelistRule bean) {
		schoolClassRuleDao.saveSchoolClassBrowserWhitelistRule(bean);
	}

	/* (non-Javadoc)
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#saveSchoolClassAppWhitelistRule(com.whty.assis.basicdata.model.SchoolClassAppWhitelistRule)
	 */
	@Override
	public void saveSchoolClassAppWhitelistRule(SchoolClassAppWhitelistRule bean) {
		schoolClassRuleDao.saveSchoolClassAppWhitelistRule(bean);
		
	}

	/* (non-Javadoc)
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#saveSchoolClassUrlBlacklistRule(com.whty.assis.basicdata.model.SchoolClassUrlBlacklistRule)
	 */
	@Override
	public void saveSchoolClassUrlBlacklistRule(SchoolClassUrlBlacklistRule bean) {
		schoolClassRuleDao.saveSchoolClassUrlBlacklistRule(bean);
		
	}

	/* (non-Javadoc)
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#saveSchoolClassBrowserTagRule(com.whty.assis.basicdata.model.SchoolClassBrowserTagRule)
	 */
	@Override
	public void saveSchoolClassBrowserTagRule(SchoolClassBrowserTagRule bean) {
		schoolClassRuleDao.saveSchoolClassBrowserTagRule(bean);
	}
	
	/* (non-Javadoc)
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#deleteSchoolClassBrowserWhitelistRuleBySchoolClassAndRuleId(java.util.Map)
	 */
	@Override
	public void deleteSchoolClassBrowserWhitelistRuleBySchoolClassAndRuleId(Map<String, Object> param) {
		schoolClassRuleDao.deleteSchoolClassBrowserWhitelistRuleBySchoolClassAndRuleId(param);
		
	}

	/* (non-Javadoc)
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#deleteSchoolClassUrlBlacklistRuleBySchoolClassAndRule(java.util.Map)
	 */
	@Override
	public void deleteSchoolClassUrlBlacklistRuleBySchoolClassAndRule(Map<String, Object> param) {
		schoolClassRuleDao.deleteSchoolClassUrlBlacklistRuleBySchoolClassAndRule(param);
	}

	/* (non-Javadoc)
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#deleteAppWhitelistRuleBySchoolClassAndRule(java.util.Map)
	 */
	@Override
	public void deleteAppWhitelistRuleBySchoolClassAndRule(Map<String, Object> param) {
		schoolClassRuleDao.deleteAppWhitelistRuleBySchoolClassAndRule(param);
		
	}

	/* (non-Javadoc)
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#deleteSchoolClassUsetimeRuleBySchoolClassAndRule(java.util.Map)
	 */
	@Override
	public void deleteSchoolClassUsetimeRuleBySchoolClassAndRule(Map<String, Object> param) {
		schoolClassRuleDao.deleteSchoolClassUsetimeRuleBySchoolClassAndRule(param);
		
	}

	/* (non-Javadoc)
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#deleteSchoolClassBrowserTagRuleBySchoolClassAndRule(java.util.Map)
	 */
	@Override
	public void deleteSchoolClassBrowserTagRuleBySchoolClassAndRule(Map<String, Object> param) {
		schoolClassRuleDao.deleteSchoolClassBrowserTagRuleBySchoolClassAndRule(param);
		
	}

	/* (non-Javadoc)
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#deleteSchoolClassAppNetBlacklistBySchoolClassAndRule(java.util.Map)
	 */
	@Override
	public void deleteSchoolClassAppNetBlacklistBySchoolClassAndRule(Map<String, Object> appNetBlacklistParam) {
		schoolClassRuleDao.deleteSchoolClassAppNetBlacklistBySchoolClassAndRule(appNetBlacklistParam);
	}

	/* (non-Javadoc)
	 * @see com.whty.assis.devicemanage.service.SchoolClassRuleService#saveSchoolClassAppNetBlacklist(com.whty.assis.devicemanage.model.SchoolClassAppNetBlacklist)
	 */
	@Override
	public void saveSchoolClassAppNetBlacklist(SchoolClassAppNetBlacklist bean) {
		schoolClassRuleDao.saveSchoolClassAppNetBlacklist(bean);
	}

}
