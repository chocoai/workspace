package com.smart.dao;

import java.util.List;

import com.smart.dao.BaseDao;
import com.smart.model.Dept;
import com.smart.model.DeptUser;

/**
 * DeptUserDao. @author Auto Tools by 充满智慧的威哥
 */
public interface DeptUserDao extends BaseDao<DeptUser, Integer> {

	/**
	 * 获取此用户所在的所有部门
	 * 
	 * @param userId
	 * @return
	 */
	public List<Dept> getDeptByUser(int userId);
}
