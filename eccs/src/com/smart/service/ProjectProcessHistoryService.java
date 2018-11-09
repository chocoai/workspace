/**
 * 
 */
package com.smart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.ProjectProcessHistoryDao;
import com.smart.model.ProjectProcessHistory;

/**
 * @Description:
 */
@Service
public class ProjectProcessHistoryService {

	@Autowired
	private ProjectProcessHistoryDao dao;

	@Autowired
	private UserService userService;

	public void save(ProjectProcessHistory entity) {
		dao.save(entity);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public void update(ProjectProcessHistory entity) {
		dao.update(entity);
	}

	public ProjectProcessHistory get(Integer id) {
		return dao.get(id);
	}

	public List<ProjectProcessHistory> getAll() {
		return dao.getAll();
	}

	public List<ProjectProcessHistory> getListByProjectId(Integer projectId) {
		List<ProjectProcessHistory> listpph = dao.getListByProjectId(projectId);
		for (ProjectProcessHistory pph : listpph) {
			pph.setNextHandlerName(
					userService.getUserNamesByUserIds(pph.getNextHandlers()));
		}
		return listpph;
	}
	
	public List<ProjectProcessHistory> getListByProjectInfoId(Integer ProjectInfoId) {
		List<ProjectProcessHistory> listpph = dao.getListByProjectInfoId(ProjectInfoId);
		for (ProjectProcessHistory pph : listpph) {
			pph.setNextHandlerName(
					userService.getUserNamesByUserIds(pph.getNextHandlers()));
		}
		return listpph;
	}

	public List<ProjectProcessHistory> getListByProjectIdAndUserId(
			Integer projectId, Integer userId) {
		StringBuilder hql = new StringBuilder();
		hql.append("from ProjectProcessHistory bean where bean.project.id = ?");
		hql.append(" and bean.operateUser.id = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(projectId);
		paramList.add(userId);
		hql.append(" order by bean.operateTime desc ");
		List<ProjectProcessHistory> pphList = dao.getList(hql.toString(),
				paramList);
		for (ProjectProcessHistory pph : pphList) {
			pph.setNextHandlerName(
					userService.getUserNamesByUserIds(pph.getNextHandlers()));
		}
		return pphList;
	}
}
