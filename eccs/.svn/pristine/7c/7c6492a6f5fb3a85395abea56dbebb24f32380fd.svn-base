package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.smart.model.ProjectNature;
import com.smart.dao.ProjectNatureDao;

/**
 * ProjectNatureService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class ProjectNatureService {

	@Autowired
	private ProjectNatureDao projectNatureDao;

	// ====================== 基本 C R U D 方法 ===========================
	public ProjectNature get(int id) {
		return projectNatureDao.get(id);
	}

	public void save(ProjectNature entity) {
		projectNatureDao.save(entity);
	}

	public void update(ProjectNature entity) {
		projectNatureDao.update(entity);
	}

	public void delete(int id) {
		projectNatureDao.delete(id);
	}

	public List<ProjectNature> getAll() {
		return projectNatureDao.getAll();
	}

}
