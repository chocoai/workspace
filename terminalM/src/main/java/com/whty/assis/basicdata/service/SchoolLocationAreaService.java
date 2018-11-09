package com.whty.assis.basicdata.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.whty.assis.basicdata.model.SchoolLocationArea;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

public interface SchoolLocationAreaService {

	public String saveSchoolLocationArea(HttpServletRequest request);

	public List<SchoolLocationArea> listByCondition(Map<String, Object> paramMap);

	public HandlerResult querySchoolLocationAreaPage(Map<String, Object> paramMap);

	public void updateSchoolLocationArea(SchoolLocationArea bean);

	/**
	 * @param id
	 * @return
	 */
	public String deleteSchoolLocationArea(String id);

	/**
	 * @param paraMap
	 * @param page
	 * @return
	 */
	public HandlerResult querySchoolLocationAreaPage(Map<String, Object> paraMap, PageContext page);

	/**
	 * @param valueOf
	 * @return
	 */
	public SchoolLocationArea loadById(Integer id);

}
