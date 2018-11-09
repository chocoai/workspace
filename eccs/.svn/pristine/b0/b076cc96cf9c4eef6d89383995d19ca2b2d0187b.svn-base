package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.ProjectContact;
import com.smart.dao.ProjectContactDao;

/**
 * ProjectContactService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class ProjectContactService {

	@Autowired
	private ProjectContactDao projectContactDao;

	public ProjectContact get(int id) {
		return projectContactDao.get(id);
	}

	public void save(ProjectContact entity) {
		projectContactDao.save(entity);
	}

	public void update(ProjectContact entity) {
		projectContactDao.update(entity);
	}

	public void delete(int id) {
		projectContactDao.delete(id);
	}

	public List<ProjectContact> getAll() {
		return projectContactDao.getAll();
	}

	public List<ProjectContact> getByProjectId(Integer projectid) {
		StringBuilder hql = new StringBuilder(
				"from ProjectContact o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (projectid == null) {
			return new ArrayList<ProjectContact>();
		} else {
			hql.append("and o.project.id = ?");
			paramList.add(projectid);
		}
		hql.append("order by o.id desc");
		return projectContactDao.getList(hql.toString(), paramList);
	}
}
