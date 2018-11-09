package com.whty.assis.demo.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.api.virtualclassmodel.ClassModel;
import com.whty.assis.api.virtualclassmodel.Group;
import com.whty.assis.api.virtualclassmodel.GroupType;
import com.whty.assis.api.virtualclassmodel.Student;
import com.whty.assis.api.virtualclassmodel.Teacher;
import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.demo.model.Demo;

/**
 * DemoDao
 * 
 * @author shijiapeng
 * @date 2015年8月11日 下午4:24:39
 */
public interface DemoDao extends IBaseDao<Demo> {

	public List listByConditionListPage(Map paramMap) throws Exception;

}
