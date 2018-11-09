/**
 * 
 */
package com.whty.wfd.page.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.whty.wfd.page.model.TUser;

import net.sf.json.JSONArray;

/**
 * @author zhangzheng
 * @date 2018年8月20日
 */
public interface ManagerService {

	public JSONArray getClassByUserId(Integer userId);

	public JSONArray getStudentByClassId(String aamClassId, Integer userId);

	public List<TUser> getStudentByStudentName(String studentName, Integer userId);

	/**
	 * @param request
	 */
	public void saveClass(HttpServletRequest request) throws Exception;

	/**
	 * @param classId
	 */
	public void deleteClass(String classId);

	/**
	 * @param classId
	 * @param studentList
	 */
	public void addStudentToClass(String classId, String studentListStr, TUser user);
}
