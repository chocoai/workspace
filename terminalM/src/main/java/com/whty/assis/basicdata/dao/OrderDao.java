/**
 * 
 */
package com.whty.assis.basicdata.dao;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.basicdata.model.Order;
import com.whty.assis.basicdata.model.OrderTerminalDeviceType;

/**
 * 订单
 * @author zhangzheng
 * @date   2018年3月31日
 */
public interface OrderDao extends IBaseDao<Order>{

	/**
	 * @param bean
	 */
	void saveTerminalDeviceType(OrderTerminalDeviceType bean);

	/**
	 * @param id
	 * @return
	 */
	Order getOrderById(Integer id);

}
