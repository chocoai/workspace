package com.smart.dao;

import com.smart.dao.BaseDao;
import com.smart.model.Project;
import com.smart.util.Page;

/**
 * ProjectDao. @author Auto Tools by 充满智慧的威哥
 */
public interface ProjectDao extends BaseDao<Project, Integer> {
	/**
	 * 项目管理中处理过的项目
	 */
	public Page<Project> getPageHistory(Integer pageNo, Integer pageSize,
			String no, String name, Integer userId);

	/**
	 * 项目管理中所有的项目
	 */
	public Page<Project> getPageProject(Integer pageNo, Integer pageSize,
			String no, String name, Integer serviceTypeId);

	public String getProjectLast();
}
