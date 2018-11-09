/**
 * 
 */
package com.whty.assis.api.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.whty.assis.api.model.AppTools;
import com.whty.assis.base.exception.BusinessException;

import net.sf.json.JSONArray;

/**
 * @author zhangzheng
 * @date 2018年8月7日
 */
public interface AppToolsService {

	/**
	 * @param body
	 * @return
	 */
	Map<String, Object> checkgetTools(String body) throws BusinessException;

	List<AppTools> listByCondition(Map<String, Object> param) throws BusinessException;

	/**
	 * @param para
	 */
	JSONArray getTools(Map<String, Object> para);

	/**
	 * @param appTools
	 */
	void addAppTools(AppTools appTools);

	/**
	 * @param request
	 */
	void updateAppTools(HttpServletRequest request);

	/**
	 * @param asList
	 */
	void deleteAppTools(List<String> asList);

}
