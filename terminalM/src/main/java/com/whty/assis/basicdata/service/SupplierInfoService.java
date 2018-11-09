package com.whty.assis.basicdata.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.whty.assis.basicdata.model.SupplierInfo;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

public interface SupplierInfoService {

	public String saveSupplierInfo(HttpServletRequest request);

	public String deleteSupplierInfo(Integer id);

	public List<SupplierInfo> listByCondition(Map<String, Object> paramMap);

	public HandlerResult querySupplierInfoPage(Map<String, Object> paramMap);

	public void updateSupplierInfo(SupplierInfo bean);

	/**
	 * @param paraMap
	 * @param page
	 * @return
	 */
	public HandlerResult querySupplierInfoPage(Map<String, Object> paraMap, PageContext page);

	/**
	 * @param id
	 * @return
	 */
	public SupplierInfo loadById(Integer id);

}
