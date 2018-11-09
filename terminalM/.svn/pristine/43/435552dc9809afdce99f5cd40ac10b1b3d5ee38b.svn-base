/**
 * 
 */
package com.whty.assis.basicdata.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.whty.assis.basicdata.model.OrderTerminalDeviceType;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年4月3日
 */
public interface OrderTerminalDeviceTypeService {
	
	public String saveOrderTerminalDeviceType(HttpServletRequest request);

	public List<OrderTerminalDeviceType> listByCondition(Map<String, Object> paramMap);

	public List<Map<String, Object>> listByConditionByOrderId(Integer orderId);

	public String updateOrderTerminalDeviceType(OrderTerminalDeviceType bean);

	public String deleteOrderTerminalDeviceType(Integer id);

	public HandlerResult queryOrderTerminalDeviceTypePage(Map<String, Object> paramMap);

	/**
	 * @param paraMap
	 * @param page
	 * @return
	 */
	public HandlerResult queryOrderTerminalDeviceTypePage(Map<String, Object> paraMap, PageContext page);

	/**
	 * @param aaParam
	 * @return
	 */
	public Integer getTotalQuanByOrderAndType(Map<String, Object> aaParam);

}
