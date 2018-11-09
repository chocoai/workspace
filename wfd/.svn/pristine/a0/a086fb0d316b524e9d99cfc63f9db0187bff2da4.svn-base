package com.whty.wfd.page.service;

import com.whty.wfd.page.model.TPlate;
import com.whty.wfd.page.model.TUser;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface PlateService {

	// 查询所有版块
	List<TPlate> getPlates();

	// 版块中移除老师
	void removeTeacher(String plateId, Integer teacherId);

	// 创建版块
	void createPlate(TPlate tPlate);

	// 标识删除版块
	void deletePlate(String plateId);

	/**
	 * @param request
	 */
	void save(HttpServletRequest request);

	/**
	 * @param plateId
	 */
	void delete(String plateId);

	/**
	 * @param plateId
	 * @param teacherList
	 */
	void addTeacherToPlate(String plateId, String teacherList, String userId);

	/**
	 * @param user
	 * @return
	 */
	List<TUser> getAllTeacherList(TUser user);

	public List<TUser> addMemcache(Map<String, Object> param, String aamUrl, Integer schoolId);
}
