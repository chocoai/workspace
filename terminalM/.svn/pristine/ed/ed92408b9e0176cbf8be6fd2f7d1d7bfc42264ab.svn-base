/**
 * 
 */
package com.whty.assis.basicdata.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.basicdata.dao.OrderTerminalDeviceTypeDao;
import com.whty.assis.basicdata.model.OrderTerminalDeviceType;
import com.whty.assis.basicdata.service.OrderTerminalDeviceTypeService;
import com.whty.common.util.SysConstants;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年4月3日
 */
@Service
public class OrderTerminalDeviceTypeServiceImpl implements OrderTerminalDeviceTypeService {

	@Autowired
	private OrderTerminalDeviceTypeDao orderTerminalDeviceTypeDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.OrderTerminalDeviceTypeService#
	 * saveOrderTerminalDeviceType(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String saveOrderTerminalDeviceType(HttpServletRequest request) {

		String orderId = request.getParameter("orderId");
		String terminalDeviceTypeId = request.getParameter("terminalDeviceTypeId");
		String quan = request.getParameter("quan");

		OrderTerminalDeviceType bean = new OrderTerminalDeviceType();
		bean.setOrderId(Integer.valueOf(orderId));
		bean.setTerminalDeviceTypeId(Integer.valueOf(terminalDeviceTypeId));
		bean.setQuan(Integer.valueOf(quan));

		orderTerminalDeviceTypeDao.save(bean);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.OrderTerminalDeviceTypeService#
	 * listByCondition(java.util.Map)
	 */
	@Override
	public List<OrderTerminalDeviceType> listByCondition(Map<String, Object> paramMap) {
		return orderTerminalDeviceTypeDao.listByCondition(paramMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.OrderTerminalDeviceTypeService#
	 * listByConditionMap(java.util.Map)
	 */
	@Override
	public List<Map<String, Object>> listByConditionByOrderId(Integer orderId) {// 查询订单下的设备数量
		return orderTerminalDeviceTypeDao.listByConditionByOrderId(orderId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.OrderTerminalDeviceTypeService#
	 * updateOrderTerminalDeviceType(com.whty.assis.basicdata.model.
	 * OrderTerminalDeviceType)
	 */
	@Override
	public String updateOrderTerminalDeviceType(OrderTerminalDeviceType bean) {

		orderTerminalDeviceTypeDao.update(bean);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.OrderTerminalDeviceTypeService#
	 * deleteOrderTerminalDeviceType(java.lang.Integer)
	 */
	@Override
	public String deleteOrderTerminalDeviceType(Integer id) {

		orderTerminalDeviceTypeDao.deleteById(id);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.OrderTerminalDeviceTypeService#
	 * queryOrderTerminalDeviceTypePage(java.util.Map)
	 */
	@Override
	public HandlerResult queryOrderTerminalDeviceTypePage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(orderTerminalDeviceTypeDao.listByCondition(paramMap));
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.OrderTerminalDeviceTypeService#
	 * queryOrderTerminalDeviceTypePage(java.util.Map,
	 * com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult queryOrderTerminalDeviceTypePage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(orderTerminalDeviceTypeDao.listByCondition(paraMap));
		rs.setPage(page);
		return rs;
	}

	/* (non-Javadoc)
	 * @see com.whty.assis.basicdata.service.OrderTerminalDeviceTypeService#getTotalQuanByOrderAndType(java.util.Map)
	 */
	@Override
	public Integer getTotalQuanByOrderAndType(Map<String, Object> aaParam) {
		return orderTerminalDeviceTypeDao.getTotalQuanByOrderAndType(aaParam);
	}

}
