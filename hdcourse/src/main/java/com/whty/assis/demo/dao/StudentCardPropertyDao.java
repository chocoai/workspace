package com.whty.assis.demo.dao;

import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.demo.model.StudentCardProperty;

public interface StudentCardPropertyDao extends IBaseDao<StudentCardProperty> {

	String getStudentCardConfig(Map<String, Object> param);

}
