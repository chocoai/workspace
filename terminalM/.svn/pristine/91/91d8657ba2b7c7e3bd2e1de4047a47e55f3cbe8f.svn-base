/**
 * 
 */
package com.whty.assis.basicdata.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.whty.assis.basicdata.model.OrderSchool;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年4月3日
 */
public interface OrderSchoolService {
	
	public String saveOrderSchool(HttpServletRequest request);

	public List<OrderSchool> listByCondition(Map<String, Object> paramMap);

	public String updateOrderSchool(OrderSchool bean);

	public String deleteOrderSchool(Integer id);

	public HandlerResult queryOrderSchoolPage(Map<String, Object> paramMap);

	public HandlerResult queryOrderSchoolPage(Map<String, Object> paraMap, PageContext page);

	/**
	 * @param orderTerminalDeviceTypeParam
	 * @return
	 */
	public List<OrderSchool> listByOrder(Map<String, Object> orderTerminalDeviceTypeParam);

}
