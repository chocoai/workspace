/**
 * 
 */
package com.whty.ebp.manage.service;

import java.util.Map;

import com.whty.ebp.manage.model.SchoolApp;
import com.whty.page.util.HandlerResult;

import net.sf.json.JSONArray;

/**
 * @author zhangzheng
 * @date 2018年10月23日
 */
public interface SchoolAppService {

	/**
	 * @param paramMap
	 * @return
	 */
	HandlerResult querySchoolAppPage(Map<String, Object> paramMap);

	public void save(SchoolApp bean);

	public void update(SchoolApp bean);

	public SchoolApp detail(Integer id);

	/**
	 * @param url
	 * @param platformCode
	 * @param province
	 * @param city
	 * @param orgName
	 * @return
	 */
	JSONArray listSchool(String url, String platformCode, String province, String city, String orgName);
	
	public String listOgra(Map<String, Object> map, String start, String end, String url);

	/**
	 * @param valueOf
	 */
	void delete(Integer valueOf);
}
