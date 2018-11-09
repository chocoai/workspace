/**
 * 
 */
package com.whty.assis.basicdata.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.basicdata.model.OrderSchoolTerminalDevice;

/**
 * @author zhangzheng
 * @date   2018年4月3日
 */
public interface OrderSchoolTerminalDeviceDao extends IBaseDao<OrderSchoolTerminalDevice>{

	/**
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> listByConditionBySchoolAndOrder(Map<String, Object> param);

	/**
	 * @param id
	 */
	void deleteByOrder(Integer orderId);

	/**
	 * @param param
	 * @return
	 */
	OrderSchoolTerminalDevice getByOrderSchoolTerminal(Map<String, Object> param);

	/**
	 * @param orderSchoolTerminalDeviceParam
	 * @return
	 */
	OrderSchoolTerminalDevice loadByOrderAndTerminalDeviceTypeId(Map<String, Object> orderSchoolTerminalDeviceParam);

	/**
	 * @param orderSchoolTerminalDeviceParam
	 * @return
	 */
	Integer getTotalQuanBy(Map<String, Object> orderSchoolTerminalDeviceParam);

	/**
	 * @param param
	 * @return
	 */
	List<OrderSchoolTerminalDevice> listCountByOrderAndSchool(Map<String, Object> param);

	/**
	 * @param id
	 */
	void deletebyOrderSchoolId(int id);





}
