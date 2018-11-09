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
import com.whty.assis.devicemanage.dao.BrowserTagDao;
import com.whty.assis.devicemanage.dao.BrowserTagRuleDao;
import com.whty.assis.devicemanage.dao.SchoolClassRuleDao;
import com.whty.assis.devicemanage.model.BrowserTag;
import com.whty.assis.devicemanage.model.BrowserTagRule;
import com.whty.assis.devicemanage.model.SchoolClassRule;
import com.whty.assis.devicemanage.service.BrowserTagRuleService;
import com.whty.common.util.SysConfig;
import com.whty.common.util.SysConstants;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author zhangzheng
 * @date 2018年4月24日
 */
@Service
public class BrowserTagRuleServiceImpl implements BrowserTagRuleService {

	@Autowired
	private BrowserTagRuleDao browserTagRuleDao;

	@Autowired
	private BrowserTagDao browserTagDao;

	@Autowired
	private IdDao idDao;

	@Autowired
	private SchoolClassRuleDao schoolClassRuleDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.BrowserTagRuleService#
	 * saveBrowserTagRule(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String saveBrowserTagRule(HttpServletRequest request) {
		String name = request.getParameter("name");
		String memo = request.getParameter("memo");

		// String[] tagList = request.getParameterValues("tagList");

		String tagListStr = request.getParameter("tagList");

		JSONArray tagList = JSONArray.fromObject(tagListStr);

		Map<String, Object> idParam = new HashMap<String, Object>(2);
		idParam.put("databaseName", SysConfig.getStrValue("databaseName"));
		idParam.put("tableName", SysConfig.getStrValue("t_browser_tag_rule"));

		Integer browserTagRuleId = idDao.getId(idParam);

		BrowserTagRule bean = new BrowserTagRule();

		bean.setName(name);
		bean.setCreateTime(new Date());
		bean.setUpdateTime(new Date());
		bean.setMemo(memo);

		if (tagList != null) {
			for (int i = 0; i < tagList.size(); i++) {
				BrowserTag browserTag = new BrowserTag();

				JSONObject tagJson = tagList.optJSONObject(i);

				browserTag.setBrowserTagRuleId(browserTagRuleId);

				browserTag.setLogo(tagJson.optString("logo"));
				browserTag.setUrl(tagJson.optString("url"));
				browserTag.setName(tagJson.optString("name"));
				browserTag.setGroupNum(tagJson.optString("groupNum"));
				browserTag.setGroupName(tagJson.optString("groupName"));

				browserTagDao.save(browserTag);
			}
		}

		browserTagRuleDao.save(bean);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.devicemanage.service.BrowserTagRuleService#listByCondition
	 * (java.util.Map)
	 */
	@Override
	public List<BrowserTagRule> listByCondition(Map<String, Object> paramMap) {
		return browserTagRuleDao.listByCondition(paramMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.BrowserTagRuleService#
	 * deleteBrowserTagRule(java.lang.Integer)
	 */
	@Override
	public String deleteBrowserTagRule(Integer id) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("browserTagRuleId", Integer.valueOf(id));
		List<SchoolClassRule> schoolClassRuleList = schoolClassRuleDao.listByCondition(param);

		if (schoolClassRuleList != null && schoolClassRuleList.size() > 0) {
			return "该规则被使用，不能删除";
		}
		browserTagRuleDao.deleteById(id);

		browserTagDao.deleteByRuleId(id);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.BrowserTagRuleService#
	 * queryBrowserTagRulePage(java.util.Map)
	 */
	@Override
	public HandlerResult queryBrowserTagRulePage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(browserTagRuleDao.listByCondition(paramMap));
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.BrowserTagRuleService#
	 * queryBrowserTagRulePage(java.util.Map, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult queryBrowserTagRulePage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(browserTagRuleDao.listByCondition(paraMap));
		rs.setPage(page);
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.BrowserTagRuleService#
	 * updateBrowserTagRule(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String updateBrowserTagRule(HttpServletRequest request) {
		String ruleId = request.getParameter("ruleId");

		browserTagDao.deleteByRuleId(Integer.valueOf(ruleId));

		String name = request.getParameter("name");
		String memo = request.getParameter("memo");

		String tagListStr = request.getParameter("tagList");
		JSONArray tagList = JSONArray.fromObject(tagListStr);
		BrowserTagRule bean = browserTagRuleDao.loadById(Integer.valueOf(ruleId));

		bean.setName(name);
		bean.setMemo(memo);
		bean.setUpdateTime(new Date());

		if (tagList != null) {
			for (int i = 0; i < tagList.size(); i++) {
				BrowserTag browserTag = new BrowserTag();

				JSONObject tagJson = tagList.optJSONObject(i);

				browserTag.setBrowserTagRuleId(bean.getId());
				browserTag.setLogo(tagJson.optString("logo"));
				browserTag.setUrl(tagJson.optString("url"));
				browserTag.setName(tagJson.optString("name"));
				browserTag.setGroupNum(tagJson.optString("groupNum"));
				browserTag.setGroupName(tagJson.optString("groupName"));

				browserTagDao.save(browserTag);
			}
		}

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.BrowserTagRuleService#
	 * detailBrowserTagRule(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public BrowserTagRule detailBrowserTagRule(HttpServletRequest request) {
		String ruleId = request.getParameter("id");

		BrowserTagRule bean = browserTagRuleDao.loadById(Integer.valueOf(ruleId));

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("browserTagRuleId", ruleId);
		List<BrowserTag> BrowserTagList = browserTagDao.listByCondition(param);

		bean.setBrowserTagList(BrowserTagList);

		return bean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.BrowserTagRuleService#
	 * listBySchoolClassRule(java.util.Map)
	 */
	@Override
	public List<BrowserTagRule> listBySchoolClassRule(Integer schoolClassId) {
		return browserTagRuleDao.listBySchoolClassRule(schoolClassId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.BrowserTagRuleService#
	 * queryBySchoolClassRule(java.lang.Integer, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult queryBySchoolClassRule(Integer schoolClassId, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(browserTagRuleDao.listBySchoolClassRule(schoolClassId));
		rs.setPage(page);
		return rs;
	}

}
