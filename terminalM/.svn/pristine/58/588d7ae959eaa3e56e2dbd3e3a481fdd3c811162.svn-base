/**
 * 
 */
package com.whty.assis.devicemanage.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.whty.assis.devicemanage.model.AppNetBlacklist;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date   2018年6月14日
 */
public interface AppNetBlacklistService {
	/**
	 * 
	 * @param request
	 * @return
	 */
	public String saveAppNetBlacklist(HttpServletRequest request);

	/**
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<AppNetBlacklist> listByCondition(Map<String, Object> paramMap);

	/**
	 * 
	 * @param id
	 * @return
	 */
	public String deleteAppNetBlacklist(Integer id);

	/**
	 * 
	 * @param paramMap
	 * @return
	 */
	public HandlerResult queryAppNetBlacklistPage(Map<String, Object> paramMap);

	/**
	 * @param paraMap
	 * @param page
	 * @return
	 */
	public HandlerResult queryAppNetBlacklistPage(Map<String, Object> paraMap, PageContext page);

	/**
	 * @param request
	 * @return
	 */
	public String updateAppNetBlacklist(HttpServletRequest request);

	/**
	 * @param request
	 * @return
	 */
	public AppNetBlacklist detailAppNetBlacklist(HttpServletRequest request);

	public HandlerResult queryBySchoolClassRule(Integer schoolClassId, PageContext page);

	/**
	 * @param schoolClassId
	 * @return
	 */
	public List<AppNetBlacklist> listBySchoolClassRule(Integer schoolClassId);

}
