package com.whty.assis.basicdata.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.basicdata.model.School;
import com.whty.assis.basicdata.model.Student;
import com.whty.assis.basicdata.model.Teacher;

public interface StudentDao extends IBaseDao<Student>{

	void saveStudent(Student student);

	void updateStudent(Student student);

	/**
	 * @param paraMap
	 * @return
	 */
	List<Map<String, Object>> listByConditionByAreaCode(Map<String, Object> paraMap);

	/**
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> listStudentMap(Map<String, Object> param);

}
