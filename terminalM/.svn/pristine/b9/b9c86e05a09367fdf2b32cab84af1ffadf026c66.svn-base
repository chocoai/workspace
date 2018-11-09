/**
 * 
 */
package com.whty.assis.devicemanage.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.whty.assis.devicemanage.model.UseTimeRule;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年4月21日
 */
public interface UseTimeRuleService {

	public String saveUseTimeRule(HttpServletRequest request);

	public List<UseTimeRule> listByCondition(Map<String, Object> paramMap);

	public String updateUseTimeRule(UseTimeRule bean);

	public String updateUseTimeRule(HttpServletRequest request);

	public String deleteUseTimeRule(Integer id);

	public HandlerResult queryUseTimeRulePage(Map<String, Object> paramMap);

	/**
	 * @param paraMap
	 * @param page
	 * @return
	 */
	public HandlerResult queryUseTimeRulePage(Map<String, Object> paraMap, PageContext page);

	/**
	 * @param request
	 * @return
	 */
	public UseTimeRule detailUseTimeRule(HttpServletRequest request);

	/**
	 * @param valueOf
	 * @return
	 */
	public List<UseTimeRule> listBySchoolClassRule(Integer valueOf);

	public HandlerResult queryBySchoolClassRule(Integer schoolClassId, PageContext page);

	public UseTimeRule loadById(Integer useTimeRuleId);
}
