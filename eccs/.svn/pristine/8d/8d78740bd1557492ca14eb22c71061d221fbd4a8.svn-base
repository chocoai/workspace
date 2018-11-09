package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.ProjectInfo;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.ProjectInfoDao;

/**
 * ProjectContactService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class ProjectInfoService {

	@Autowired
	private ProjectInfoDao projectInfoDao;

	// ====================== 基本 C R U D 方法 ===========================
	public ProjectInfo get(int id) {
		return projectInfoDao.get(id);
	}

	public void save(ProjectInfo entity) {
		projectInfoDao.save(entity);
	}

	public void update(ProjectInfo entity) {
		projectInfoDao.update(entity);
	}

	public void delete(int id) {
		projectInfoDao.delete(id);
	}

	public List<ProjectInfo> getAll() {
		return projectInfoDao.getAll();
	}

	public Page<ProjectInfo> getAll(int pageNo, int pageSize,
			String projectName) {
		StringBuilder hql = new StringBuilder();
		hql.append("from ProjectInfo o where o.status = 1 or o.status = 2");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(projectName)) {
			hql.append(" and o.proname like ? ");
			paramList.add("%" + projectName + "%");
		}
		hql.append("order by o.id desc");
		return projectInfoDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
	}

	public Page<ProjectInfo> getPage(int pageNo, int pageSize,
			String projectName, String bidWay) {
		StringBuilder hql = new StringBuilder(
				"from ProjectInfo o where o.status <> -1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(projectName)) {
			hql.append(" and o.proname like ? ");
			paramList.add("%" + projectName + "%");
		}
		if (!StringUtil.isBlank(bidWay)) {
			hql.append("and o.bidway = ? ");
			paramList.add(bidWay);
		}
		hql.append("order by o.id desc");
		return projectInfoDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
	}
}
