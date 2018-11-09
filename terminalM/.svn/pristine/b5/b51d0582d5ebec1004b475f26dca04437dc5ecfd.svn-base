/**
 * 
 */
package com.whty.assis.devicemanage.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.whty.assis.basicdata.model.SchoolClassAppWhitelistRule;
import com.whty.assis.basicdata.model.SchoolClassBrowserTagRule;
import com.whty.assis.basicdata.model.SchoolClassBrowserWhitelistRule;
import com.whty.assis.basicdata.model.SchoolClassUrlBlacklistRule;
import com.whty.assis.basicdata.model.SchoolClassUsetimeRule;
import com.whty.assis.devicemanage.model.SchoolClassAppNetBlacklist;
import com.whty.assis.devicemanage.model.SchoolClassRule;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年5月3日
 */
public interface SchoolClassRuleService {

	public String saveSchoolClassRule(HttpServletRequest request);

	public String saveSchoolClassRule(SchoolClassRule bean);

	public List<SchoolClassRule> listByCondition(Map<String, Object> paramMap);

	public String deleteSchoolClassRule(Integer id);

	public HandlerResult querySchoolClassRulePage(Map<String, Object> paramMap);

	/**
	 * @param paraMap
	 * @param page
	 * @return
	 */
	public HandlerResult querySchoolClassRulePage(Map<String, Object> paraMap, PageContext page);

	/**
	 * @param request
	 * @return
	 */
	public String updateSchoolClassRule(HttpServletRequest request);

	/**
	 * @param request
	 * @return
	 */
	public String updateSchoolClassRule(SchoolClassRule bean);

	/**
	 * @param request
	 * @return
	 */
	public SchoolClassRule detailSchoolClassRule(HttpServletRequest request);

	/**
	 * @param schoolId
	 * @return
	 */
	public List<Map<String, Object>> listGradeTree(Integer schoolId);

	/**
	 * @param valueOf
	 * @return
	 */
	public SchoolClassRule getBySchoolClassId(Integer schoolClassId);

	/**
	 * @param bean
	 */
	public void deleteSchoolClassRule(SchoolClassRule bean);

	/**
	 * @param bean
	 */
	public void saveSchoolClassUsetimeRule(SchoolClassUsetimeRule bean);

	/**
	 * @param bean
	 */
	public void saveSchoolClassBrowserWhitelistRule(SchoolClassBrowserWhitelistRule bean);

	/**
	 * @param bean
	 */
	public void saveSchoolClassAppWhitelistRule(SchoolClassAppWhitelistRule bean);

	/**
	 * @param bean
	 */
	public void saveSchoolClassUrlBlacklistRule(SchoolClassUrlBlacklistRule bean);

	/**
	 * @param bean
	 */
	public void saveSchoolClassBrowserTagRule(SchoolClassBrowserTagRule bean);

	/**
	 * @param param
	 */
	public void deleteSchoolClassBrowserWhitelistRuleBySchoolClassAndRuleId(Map<String, Object> param);

	/**
	 * @param param
	 */
	public void deleteSchoolClassUrlBlacklistRuleBySchoolClassAndRule(Map<String, Object> param);

	/**
	 * @param param
	 */
	public void deleteAppWhitelistRuleBySchoolClassAndRule(Map<String, Object> param);

	/**
	 * @param param
	 */
	public void deleteSchoolClassUsetimeRuleBySchoolClassAndRule(Map<String, Object> param);

	/**
	 * @param param
	 */
	public void deleteSchoolClassBrowserTagRuleBySchoolClassAndRule(Map<String, Object> param);

	/**
	 * @param appNetBlacklistParam
	 */
	public void deleteSchoolClassAppNetBlacklistBySchoolClassAndRule(Map<String, Object> appNetBlacklistParam);

	/**
	 * @param bean
	 */
	public void saveSchoolClassAppNetBlacklist(SchoolClassAppNetBlacklist bean);

}
