/**
 * 
 */
package com.whty.assis.mall.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.whty.assis.mall.model.AppInfo;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date   2018年4月18日
 */
public interface AppInfoService {
	
	public AppInfo loadById(Integer id);

	public String saveAppInfo(HttpServletRequest request);

	public String deleteAppInfo(Integer id);

	public List<AppInfo> listByCondition(Map<String, Object> paramMap);

	public List<AppInfo> listByParam(Map<String,Object> paramMap);
	
	public HandlerResult queryAppInfoPage(Map<String, Object> paramMap);

	
	public String updateAppInfo(HttpServletRequest request);

	/**
	 * @param paraMap
	 * @param page
	 * @return
	 */
	public HandlerResult queryAppInfoPage(Map<String, Object> paraMap, PageContext page);
}
