/**
 * 
 */
package com.whty.assis.basicdata.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.basicdata.dao.OrderSchoolDao;
import com.whty.assis.basicdata.model.OrderSchool;
import com.whty.assis.basicdata.service.OrderSchoolService;
import com.whty.common.util.SysConstants;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年4月3日
 */
@Service
public class OrderSchoolServiceImpl implements OrderSchoolService {

	@Autowired
	private OrderSchoolDao orderSchoolDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.OrderSchoolService#saveOrderSchool(javax
	 * .servlet.http.HttpServletRequest)
	 */
	@Override
	public String saveOrderSchool(HttpServletRequest request) {
		String orderId = request.getParameter("orderId");
		String schoolId = request.getParameter("schoolId");

//		String licenceCode = GUIDGenerator.getUUID32();// TODO 随机字符串，后期改成变编码生成
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orderId", orderId);
		param.put("schoolId", schoolId);
		List<OrderSchool> list = orderSchoolDao.listByCondition(param);
		
		if(list!=null && list.size()>0){
			return "已经存在相同的学校";
		}else{
			OrderSchool bean = new OrderSchool();
			bean.setOrderId(Integer.valueOf(orderId));
			bean.setSchoolId(Integer.valueOf(schoolId));
//			bean.setLicenceCode(licenceCode);
			bean.setUpdateTime(new Date());
			bean.setCreateTime(new Date());

			orderSchoolDao.save(bean);
			return SysConstants.SUCCESS;
		}
		

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.OrderSchoolService#listByCondition(java.
	 * util.Map)
	 */
	@Override
	public List<OrderSchool> listByCondition(Map<String, Object> paramMap) {
		return orderSchoolDao.listByCondition(paramMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.OrderSchoolService#updateOrderSchool(com
	 * .whty.assis.basicdata.model.OrderSchool)
	 */
	@Override
	public String updateOrderSchool(OrderSchool bean) {
		orderSchoolDao.update(bean);
		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.OrderSchoolService#deleteOrderSchool(
	 * java.lang.Integer)
	 */
	@Override
	public String deleteOrderSchool(Integer id) {
		orderSchoolDao.deleteById(id);
		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.OrderSchoolService#queryOrderSchoolPage(
	 * java.util.Map)
	 */
	@Override
	public HandlerResult queryOrderSchoolPage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(orderSchoolDao.listByCondition(paramMap));
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.OrderSchoolService#queryOrderSchoolPage(
	 * java.util.Map, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult queryOrderSchoolPage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(orderSchoolDao.listByCondition(paraMap));
		rs.setPage(page);
		return rs;
	}

	/* (non-Javadoc)
	 * @see com.whty.assis.basicdata.service.OrderSchoolService#listByOrder(java.util.Map)
	 */
	@Override
	public List<OrderSchool> listByOrder(Map<String, Object> orderTerminalDeviceTypeParam) {
		return orderSchoolDao.listByOrder(orderTerminalDeviceTypeParam);
	}

}
