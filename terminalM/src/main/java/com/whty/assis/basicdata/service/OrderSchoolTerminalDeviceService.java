/**
 * 
 */
package com.whty.assis.basicdata.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.whty.assis.basicdata.model.OrderSchoolTerminalDevice;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date   2018年4月3日
 */
public interface OrderSchoolTerminalDeviceService {
	
	public String saveOrderSchoolTerminalDevice(HttpServletRequest request);

	public List<OrderSchoolTerminalDevice> listByCondition(Map<String, Object> paramMap);

	public String updateOrderSchoolTerminalDevice(OrderSchoolTerminalDevice bean);

	public String deleteOrderSchoolTerminalDevice(Integer id);

	public HandlerResult queryOrderSchoolTerminalDevicePage(Map<String, Object> paramMap);

	public HandlerResult queryOrderSchoolTerminalDevicePage(Map<String, Object> paraMap, PageContext page);

	/**
	 * @param valueOf
	 * @param valueOf2
	 * @return
	 */
	public List<Map<String, Object>> listByConditionBySchoolAndOrder(Integer schoolId, Integer orderId);

	/**
	 * @param orderTerminalDeviceTypeParam
	 * @return
	 */
	public Integer getTotalQuanBy(Map<String, Object> orderTerminalDeviceTypeParam);

	/**
	 * @param param
	 * @return
	 */
	public List<OrderSchoolTerminalDevice> listCountByOrderAndSchool(Map<String, Object> param);



}
