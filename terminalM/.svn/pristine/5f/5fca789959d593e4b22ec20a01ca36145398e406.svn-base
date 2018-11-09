package com.whty.assis.basicdata.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.basicdata.model.School;
import com.whty.assis.basicdata.model.Teacher;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

public interface TeacherService {

	public void saveTeacher(Teacher teacher);

	public List<Teacher> listByCondition(Map<String, Object> paramMap);

	public List<Map<String, Object>> listTeacherMap(Map<String, Object> param);

	public HandlerResult queryTeacherPage(Map<String, Object> paramMap, PageContext page);

	public void updateTeacher(Teacher teacher);

	/**
	 * @param paraMap
	 * @return
	 */
	public List<Map<String, Object>> listByConditionByAreaCode(Map<String, Object> paraMap);

//	public List<Map<String, Object>> listSchoolAreaTree(Map<String, Object> paraMap);

}
