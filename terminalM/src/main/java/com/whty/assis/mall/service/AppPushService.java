/**
 * 
 */
package com.whty.assis.mall.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.whty.assis.mall.model.AppPush;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date   2018年4月18日
 */
public interface AppPushService {
	
	public String saveAppPush(HttpServletRequest request);

	public String saveAppPush(AppPush bean);
	
	public String deleteAppPush(Integer id);

	public List<AppPush> listByCondition(Map<String, Object> paramMap);

	public HandlerResult queryAppPushPage(Map<String, Object> paramMap);

	public void updateAppPush(AppPush bean);

	/**
	 * @param paraMap
	 * @param page
	 * @return
	 */
	public HandlerResult queryAppPushPage(Map<String, Object> paraMap, PageContext page);

	/**
	 * @param request
	 * @return
	 */
	public String rePushApp(HttpServletRequest request);

	/**
	 * @param request
	 * @return
	 */
	public String deleteAppPush(HttpServletRequest request);
}
