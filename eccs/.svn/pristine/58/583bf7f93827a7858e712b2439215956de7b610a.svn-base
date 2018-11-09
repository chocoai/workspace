package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.smart.model.ProjectType;
import com.smart.dao.ProjectTypeDao;

/**
 * ProjectTypeService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class ProjectTypeService {

	@Autowired
	private ProjectTypeDao projectTypeDao;

	public List<ProjectType> getList() {
		String hql = "from ProjectType o where o.status = 1";
		return projectTypeDao.getList(hql);
	}

	// ====================== 基本 C R U D 方法 ===========================
	public ProjectType get(int id) {
		return projectTypeDao.get(id);
	}

	public void save(ProjectType entity) {
		projectTypeDao.save(entity);
	}

	public void update(ProjectType entity) {
		projectTypeDao.update(entity);
	}

	public void delete(int id) {
		projectTypeDao.delete(id);
	}

	public List<ProjectType> getAll() {
		return projectTypeDao.getAll();
	}

}
