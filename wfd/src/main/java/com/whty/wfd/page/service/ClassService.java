package com.whty.wfd.page.service;

import com.whty.wfd.page.model.TClass;
import com.whty.wfd.page.model.TClassPlate;
import com.whty.wfd.page.model.TClassUser;

import java.util.List;

public interface ClassService {

	// 查询所有班级
	List<TClass> getClassBy();

	// 创建班级并关联学生和关联模块
	void createClass(TClass tClass, TClassUser tClassUser, TClassPlate tClassPlate);

	// 添加学生
	void insertClassUser(Integer ClassId, Integer[] userId);

	// 删除班级，并删除班级与学生表的中的该班级的所有数据
	void deleteClass(String ClassId);

	// 取消关联模板
	void cancel(String ClassId, String PlateId);

	// 添加关联模板
	void relative(String ClassId, String PlateId);

}
