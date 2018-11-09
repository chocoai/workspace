/**
 * 
 */
package com.whty.assis.basicdata.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.basicdata.model.OrderSchoolTerminalDevice;
import com.whty.assis.basicdata.model.OrderTerminalDeviceType;

/**
 * @author zhangzheng
 * @date   2018年4月3日
 */
public interface OrderTerminalDeviceTypeDao extends IBaseDao<OrderTerminalDeviceType>{

	/**
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> listByConditionByOrderId(Integer orderId);

	/**
	 * @param id
	 */
	void deleteByOrder(Integer orderId);

	/**
	 * @param orderSchoolTerminalDeviceParam
	 * @return
	 */
	OrderTerminalDeviceType loadByOrderAndTerminalDeviceTypeId(Map<String, Object> orderSchoolTerminalDeviceParam);

	/**
	 * @param aaParam
	 * @return
	 */
	Integer getTotalQuanByOrderAndType(Map<String, Object> aaParam);

	
	
}
