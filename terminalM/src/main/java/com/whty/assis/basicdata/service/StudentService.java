package com.whty.assis.basicdata.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.basicdata.model.School;
import com.whty.assis.basicdata.model.Student;
import com.whty.assis.basicdata.model.Teacher;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

public interface StudentService {

	public void saveStudent(Student student);

	public List<Student> listByCondition(Map<String, Object> paramMap);

	public List<Map<String, Object>> listStudentMap(Map<String, Object> param);

	public HandlerResult queryStudentPage(Map<String, Object> paramMap, PageContext page);

	public void updateStudent(Student student);

	/**
	 * @param paraMap
	 * @return
	 */
	public List<Map<String, Object>> listByConditionByAreaCode(Map<String, Object> paraMap);

//	public List<Map<String, Object>> listSchoolAreaTree(Map<String, Object> paraMap);

}
