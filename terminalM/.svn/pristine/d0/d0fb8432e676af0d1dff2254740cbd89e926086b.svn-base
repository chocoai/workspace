/**
 * 
 */
package com.whty.assis.basicdata.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.whty.assis.basicdata.model.Brand;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年3月31日
 */
public interface BrandService {
	
	public String saveBrand(HttpServletRequest request);

	public List<Brand> listByCondition(Map<String, Object> paramMap);

	public String updateBrand(Brand bean);

	public String deleteBrand(Integer id);

	public HandlerResult queryBrandPage(Map<String, Object> paramMap) ;

	/**
	 * @param paraMap
	 * @param page
	 * @return
	 */
	public HandlerResult queryBrandPage(Map<String, Object> paraMap, PageContext page);

	/**
	 * @param valueOf
	 * @return
	 */
	public Brand loadById(Integer id);
}
