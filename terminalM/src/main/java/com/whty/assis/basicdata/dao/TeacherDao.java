package com.whty.assis.basicdata.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.basicdata.model.School;
import com.whty.assis.basicdata.model.Teacher;

public interface TeacherDao extends IBaseDao<Teacher>{

	void saveTeacher(Teacher teacher);

	void updateTeacher(Teacher teacher);

	/**
	 * @param paraMap
	 * @return
	 */
	List<Map<String, Object>> listByConditionByAreaCode(Map<String, Object> paraMap);

	/**
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> listTeacherMap(Map<String, Object> param);

}
