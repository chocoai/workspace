/**
 * 
 */
package com.whty.assis.basicdata.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.basicdata.dao.OrderDao;
import com.whty.assis.basicdata.dao.OrderSchoolDao;
import com.whty.assis.basicdata.dao.OrderSchoolTerminalDeviceDao;
import com.whty.assis.basicdata.dao.OrderTerminalDeviceTypeDao;
import com.whty.assis.basicdata.model.Order;
import com.whty.assis.basicdata.model.OrderSchool;
import com.whty.assis.basicdata.model.OrderSchoolTerminalDevice;
import com.whty.assis.basicdata.model.OrderTerminalDeviceType;
import com.whty.assis.basicdata.service.OrderSchoolTerminalDeviceService;
import com.whty.common.util.SysConstants;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年4月3日
 */
@Service
public class OrderSchoolTerminalDeviceServiceImpl implements OrderSchoolTerminalDeviceService {

	@Autowired
	private OrderSchoolTerminalDeviceDao orderSchoolTerminalDeviceDao;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderSchoolDao orderSchoolDao;

	@Autowired
	private OrderTerminalDeviceTypeDao orderTerminalDeviceTypeDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.OrderSchoolTerminalDeviceService#
	 * saveOrderSchoolTerminalDevice(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String saveOrderSchoolTerminalDevice(HttpServletRequest request) {

		String orderSchoolId = request.getParameter("orderSchoolId");

		OrderSchool orderSchool = orderSchoolDao.loadById(Integer.valueOf(orderSchoolId));

		if (StringUtils.isEmpty(orderSchool.getLicenceCode())) {
			return "该学校还未生成授权码";
		}

		String terminalDeviceTypeId = request.getParameter("terminalDeviceTypeId");
		String quan = request.getParameter("quan");
		String orderId = request.getParameter("orderId");

		Map<String, Object> orderSchoolTerminalDeviceParam = new HashMap<String, Object>();
		orderSchoolTerminalDeviceParam.put("orderId", orderId);
		orderSchoolTerminalDeviceParam.put("terminalDeviceTypeId", terminalDeviceTypeId);

		OrderTerminalDeviceType orderSchoolTerminalDevice = orderTerminalDeviceTypeDao
				.loadByOrderAndTerminalDeviceTypeId(orderSchoolTerminalDeviceParam);

		Integer terminlTotalQuan = orderSchoolTerminalDevice.getQuan();
		Integer schoolIdTerminalTotalQuan = orderSchoolTerminalDeviceDao.getTotalQuanBy(orderSchoolTerminalDeviceParam);

		if (schoolIdTerminalTotalQuan != null) {
			if (schoolIdTerminalTotalQuan >= terminlTotalQuan) {
				return "设备数量不能大于订单的总数";
			}
		}

		if (Integer.valueOf(quan) > terminlTotalQuan) {
			return "设备数量不能大于订单设备数";
		}

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("orderSchoolId", orderSchoolId);
		param.put("terminalDeviceTypeId", terminalDeviceTypeId);
		param.put("orderId", orderId);

		OrderSchoolTerminalDevice bean = orderSchoolTerminalDeviceDao.getByOrderSchoolTerminal(param);

		if (bean == null) {// 创建
			bean = new OrderSchoolTerminalDevice();
			bean.setOrderSchoolId(Integer.valueOf(orderSchoolId));
			bean.setTerminalDeviceTypeId(Integer.valueOf(terminalDeviceTypeId));
			bean.setQuan(Integer.valueOf(quan));
			bean.setOrderId(Integer.valueOf(orderId));
			bean.setCreateTime(new Date());
			bean.setUpdateTime(new Date());

		} else {// 更新
			bean.setQuan(Integer.valueOf(quan));

			orderSchoolTerminalDeviceDao.update(bean);
		}

		Order order = orderDao.getOrderById(Integer.valueOf(orderId));

		order.setUpdateTime(new Date());
		order.setStatus(2);

		orderSchoolTerminalDeviceDao.save(bean);
		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.OrderSchoolTerminalDeviceService#
	 * listByCondition(java.util.Map)
	 */
	@Override
	public List<OrderSchoolTerminalDevice> listByCondition(Map<String, Object> paramMap) {
		return orderSchoolTerminalDeviceDao.listByCondition(paramMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.OrderSchoolTerminalDeviceService#
	 * updateOrderSchoolTerminalDevice(com.whty.assis.basicdata.model.
	 * OrderSchoolTerminalDevice)
	 */
	@Override
	public String updateOrderSchoolTerminalDevice(OrderSchoolTerminalDevice bean) {
		orderSchoolTerminalDeviceDao.update(bean);
		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.OrderSchoolTerminalDeviceService#
	 * deleteOrderSchoolTerminalDevice(java.lang.Integer)
	 */
	@Override
	public String deleteOrderSchoolTerminalDevice(Integer id) {
		orderSchoolTerminalDeviceDao.deleteById(id);
		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.OrderSchoolTerminalDeviceService#
	 * queryOrderSchoolTerminalDevicePage(java.util.Map)
	 */
	@Override
	public HandlerResult queryOrderSchoolTerminalDevicePage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(orderSchoolTerminalDeviceDao.listByCondition(paramMap));
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.OrderSchoolTerminalDeviceService#
	 * queryOrderSchoolTerminalDevicePage(java.util.Map,
	 * com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult queryOrderSchoolTerminalDevicePage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(orderSchoolTerminalDeviceDao.listByCondition(paraMap));
		rs.setPage(page);
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.OrderSchoolTerminalDeviceService#
	 * listByConditionBySchoolAndOrder(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<Map<String, Object>> listByConditionBySchoolAndOrder(Integer schoolId, Integer orderId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("orderId", orderId);
		param.put("schoolId", schoolId);
		return orderSchoolTerminalDeviceDao.listByConditionBySchoolAndOrder(param);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.OrderSchoolTerminalDeviceService#
	 * getTotalQuanBy(java.util.Map)
	 */
	@Override
	public Integer getTotalQuanBy(Map<String, Object> orderTerminalDeviceTypeParam) {
		return orderSchoolTerminalDeviceDao.getTotalQuanBy(orderTerminalDeviceTypeParam);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.OrderSchoolTerminalDeviceService#
	 * listCountByOrderAndSchool(java.util.Map)
	 */
	@Override
	public List<OrderSchoolTerminalDevice> listCountByOrderAndSchool(Map<String, Object> param) {
		return orderSchoolTerminalDeviceDao.listCountByOrderAndSchool(param);
	}

}
