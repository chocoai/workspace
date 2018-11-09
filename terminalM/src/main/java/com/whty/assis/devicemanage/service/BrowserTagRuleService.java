/**
 * 
 */
package com.whty.assis.devicemanage.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.whty.assis.devicemanage.model.BrowserTagRule;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年4月24日
 */
public interface BrowserTagRuleService {

	public String saveBrowserTagRule(HttpServletRequest request);

	public List<BrowserTagRule> listByCondition(Map<String, Object> paramMap);

	public String deleteBrowserTagRule(Integer id);

	public HandlerResult queryBrowserTagRulePage(Map<String, Object> paramMap);

	/**
	 * @param paraMap
	 * @param page
	 * @return
	 */
	public HandlerResult queryBrowserTagRulePage(Map<String, Object> paraMap, PageContext page);

	/**
	 * @param request
	 * @return
	 */
	public String updateBrowserTagRule(HttpServletRequest request);

	/**
	 * @param request
	 * @return
	 */
	public BrowserTagRule detailBrowserTagRule(HttpServletRequest request);

	/**
	 * @param paramMap
	 * @return
	 */
	public List<BrowserTagRule> listBySchoolClassRule(Integer schoolClassId);

	/**
	 * @param valueOf
	 * @param page
	 * @return
	 */
	public HandlerResult queryBySchoolClassRule(Integer schoolClassId, PageContext page);
}
