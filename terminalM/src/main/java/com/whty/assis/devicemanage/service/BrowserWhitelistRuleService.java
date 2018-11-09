/**
 * 
 */
package com.whty.assis.devicemanage.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.whty.assis.devicemanage.model.BrowserWhitelist;
import com.whty.assis.devicemanage.model.BrowserWhitelistRule;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年4月24日
 */
public interface BrowserWhitelistRuleService {
	public String saveBrowserWhitelistRule(HttpServletRequest request);

	public List<BrowserWhitelistRule> listByCondition(Map<String, Object> paramMap);

	public String deleteBrowserWhitelistRule(Integer id);

	public HandlerResult queryBrowserWhitelistRulePage(Map<String, Object> paramMap);

	/**
	 * @param paraMap
	 * @param page
	 * @return
	 */
	public HandlerResult queryBrowserWhitelistRulePage(Map<String, Object> paraMap, PageContext page);

	/**
	 * @param request
	 * @return
	 */
	public String updateBrowserWhitelistRule(HttpServletRequest request);

	/**
	 * @param request
	 * @return
	 */
	public BrowserWhitelistRule detailBrowserWhitelistRule(HttpServletRequest request);

	/**
	 * @param paramMap
	 * @return
	 */
	public List<BrowserWhitelistRule> listBySchoolClassRule(Integer schoolClassId);

	/**
	 * @param valueOf
	 * @param page
	 * @return
	 */
	public HandlerResult queryBySchoolClassRule(Integer valueOf, PageContext page);

	/**
	 * @param valueOf
	 * @return
	 */
	public List<BrowserWhitelist> getwhiteListBySchoolClassId(Integer valueOf);

	/**
	 * @param valueOf
	 * @return
	 */
	public List<BrowserWhitelist> getBroserWhitelistByRuleId(Integer valueOf);
}