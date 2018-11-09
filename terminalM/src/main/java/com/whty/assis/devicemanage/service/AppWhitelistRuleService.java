/**
 * 
 */
package com.whty.assis.devicemanage.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.whty.assis.devicemanage.model.AppWhitelistRule;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年5月9日
 */
public interface AppWhitelistRuleService {

	/**
	 * 
	 * @param request
	 * @return
	 */
	public String saveAppWhitelistRule(HttpServletRequest request);

	/**
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<AppWhitelistRule> listByCondition(Map<String, Object> paramMap);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public String deleteAppWhitelistRule(Integer id);

	/**
	 * 
	 * @param paramMap
	 * @return
	 */
	public HandlerResult queryAppWhitelistRulePage(Map<String, Object> paramMap);

	/**
	 * @param paraMap
	 * @param page
	 * @return
	 */
	public HandlerResult queryAppWhitelistRulePage(Map<String, Object> paraMap, PageContext page);

	/**
	 * @param request
	 * @return
	 */
	public String updateAppWhitelistRule(HttpServletRequest request);

	/**
	 * @param request
	 * @return
	 */
	public AppWhitelistRule detailAppWhitelistRule(HttpServletRequest request);

	public HandlerResult queryBySchoolClassRule(Integer schoolClassId, PageContext page);

	/**
	 * @param schoolClassId
	 * @return
	 */
	public List<AppWhitelistRule> listBySchoolClassRule(Integer schoolClassId);
}
