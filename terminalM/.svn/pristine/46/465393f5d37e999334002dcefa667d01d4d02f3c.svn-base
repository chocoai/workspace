package com.whty.assis.basicdata.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.basicdata.dao.OrderDao;
import com.whty.assis.basicdata.dao.SupplierInfoDao;
import com.whty.assis.basicdata.model.Order;
import com.whty.assis.basicdata.model.SupplierInfo;
import com.whty.assis.basicdata.service.SupplierInfoService;
import com.whty.assis.sysres.model.TaManageUserInfo;
import com.whty.common.util.ChineseCharToEn;
import com.whty.common.util.SysConstants;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

@Service
public class SupplierInfoServiceImpl implements SupplierInfoService {

	@Autowired
	private SupplierInfoDao supplierInfoDao;

	@Autowired
	private OrderDao orderDao;

	public String deleteSupplierInfo(Integer id) {

		Map<String, Object> param = new HashMap<String, Object>(1);
		param.put("supplierId", id);

		List<Order> orderlst = orderDao.listByCondition(param);

		if (orderlst != null && orderlst.size() > 0) {
			return "0001";
		} else {
			supplierInfoDao.deleteById(id);
			return SysConstants.SUCCESS;
		}
	}

	@Override
	public String saveSupplierInfo(HttpServletRequest request) {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String contact = request.getParameter("contact");
		String contactTel = request.getParameter("contactTel");
		String terminalDeviceTypeId = request.getParameter("terminalDeviceTypeId");
		
		String memo = request.getParameter("memo");
		
		Map<String, Object> param = new HashMap<String, Object>(1);
		param.put("name", name);

		List<SupplierInfo> lst = supplierInfoDao.listByCondition(param);

		if (lst != null && lst.size() > 0) {
			return "供应商名称已经存在";
		} else {
			SupplierInfo bean = new SupplierInfo();
			bean.setName(name);
			bean.setAddress(address);
			bean.setContact(contact);
			bean.setContactTel(contactTel);
			bean.setCreateTime(new Date());
			bean.setUpdateTime(new Date());
			TaManageUserInfo mUser = (TaManageUserInfo) request.getSession().getAttribute(SysConstants.SESSION_USER);
			bean.setCreator(mUser.getId());// TODO 登录用户id
			bean.setMemo(memo);
			
			bean.setTerminalDeviceTypeId(Integer.valueOf(terminalDeviceTypeId));
			ChineseCharToEn cte = new ChineseCharToEn();
			
			bean.setSupplierNum(cte.getAllFirstLetter(name));//TODO 供应商编码
			
			supplierInfoDao.save(bean);
			return SysConstants.SUCCESS;
		}
	}

	
	
	@Override
	public List<SupplierInfo> listByCondition(Map<String, Object> paramMap) {
		return supplierInfoDao.listByCondition(paramMap);
	}

	@Override
	public HandlerResult querySupplierInfoPage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(supplierInfoDao.listByCondition(paramMap));
		return rs;
	}

	@Override
	public void updateSupplierInfo(SupplierInfo bean) {
		ChineseCharToEn cte = new ChineseCharToEn();
		bean.setSupplierNum(cte.getAllFirstLetter(bean.getName()));//TODO 供应商编码
		supplierInfoDao.update(bean);
	}

	/* (non-Javadoc)
	 * @see com.whty.assis.basicdata.service.SupplierInfoService#querySupplierInfoPage(java.util.Map, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult querySupplierInfoPage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(supplierInfoDao.listByCondition(paraMap));
		rs.setPage(page);
		return rs;
	}

	/* (non-Javadoc)
	 * @see com.whty.assis.basicdata.service.SupplierInfoService#loadById(java.lang.String)
	 */
	@Override
	public SupplierInfo loadById(Integer id) {
		return supplierInfoDao.loadById(id);
	}

}
