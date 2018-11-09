/**
 * 
 */
package com.whty.assis.devicemanage.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.whty.assis.devicemanage.model.UrlBlackList;
import com.whty.assis.devicemanage.model.UrlBlackListRule;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年4月21日
 */
public interface UrlBlackListRuleService {
	
	public String saveUrlBlackListRule(HttpServletRequest request);

	public List<UrlBlackListRule> listByCondition(Map<String, Object> paramMap);


	public String deleteUrlBlackListRule(Integer id);

	public HandlerResult queryUrlBlackListRulePage(Map<String, Object> paramMap);

	/**
	 * @param paraMap
	 * @param page
	 * @return
	 */
	public HandlerResult queryUrlBlackListRulePage(Map<String, Object> paraMap, PageContext page);

	/**
	 * @param request
	 * @return
	 */
	public String updateUrlBlackList(HttpServletRequest request);

	/**
	 * @param request
	 * @return
	 */
	public UrlBlackListRule detailUrlBlackList(HttpServletRequest request);

	/**
	 * @param valueOf
	 * @return
	 */
	public List<UrlBlackListRule> listBySchoolClassRule(Integer valueOf);

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
	public List<UrlBlackList> getUrlBlackListByRuleId(Integer urlBlackListRuleId);

	/**
	 * @param valueOf
	 * @return
	 */
	public List<UrlBlackList> getUrlBlackListBySchooClassId(Integer valueOf);
}