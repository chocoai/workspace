/**
 * 
 */
package com.whty.assis.basicdata.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.whty.assis.basicdata.model.Order;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年4月1日
 */
public interface OrderService {

	public String saveOrder(HttpServletRequest request);

	public List<Order> listByCondition(Map<String, Object> paramMap);

	public String updateOrder(Order bean);

	public String deleteOrder(Integer id);

	public HandlerResult queryOrderPage(Map<String, Object> paramMap);

	/**
	 * @param paraMap
	 * @param page
	 * @return
	 */
	public HandlerResult queryOrderPage(Map<String, Object> paraMap, PageContext page);

	/**
	 * @param para
	 * @return
	 */
//	public List<OrderSchool> listOrderSchool(Map<String, Object> para);

	/**
	 * @param valueOf
	 * @return
	 */
	public Order getOrderById(Integer valueOf);

	/**
	 * @param orderId
	 * @return
	 */
	public String createDevice(String orderId);

	/**
	 * @param request
	 * @return
	 */
	public String deleteOrder(HttpServletRequest request);

	/**
	 * @param request
	 * @return
	 */
	public String addSchoolDeviceNum(HttpServletRequest request);

	/**
	 * @param request
	 * @return
	 */
	public String updateSchoolTerminalDevice(HttpServletRequest request);

	/**
	 * @param request
	 * @return
	 */
	public String deleteOrderSchool(HttpServletRequest request);
}
