package com.smart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.ProjectSummaryDao;
import com.smart.model.ProjectInfo;
import com.smart.model.ProjectSummary;
import com.smart.util.Page;
import com.smart.util.StringUtil;

/**
 * ProjectSummaryService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class ProjectSummaryService {
	
	
	@Autowired
	private ProjectSummaryDao projectSummaryDao;
	
	
	
	
	
	//====================== 基本 C R U D 方法  ===========================
	public ProjectSummary get(Integer id) {
		return projectSummaryDao.get(id);
	}
	
	public void save(ProjectSummary entity) {
		projectSummaryDao.save(entity);
	}
	
	public void update(ProjectSummary entity) {
		projectSummaryDao.update(entity);
	}
	
	public void delete(Integer id) {
		projectSummaryDao.delete(id);
	}
	
	public List<ProjectSummary> getAll() {
		return projectSummaryDao.getAll();
	}

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param cusName
	 * @param proname
	 * @param agentcompany 
	 * @return
	 */
	public Page<ProjectSummary> getPage(int pageNo, int pageSize, String cusName,
			String proname, String agentcompany) {
		StringBuilder hql = new StringBuilder();
		hql.append("from ProjectSummary o where o.status <> 0 ");
		List<Object> paramList = new ArrayList<Object>();
		if (null != cusName) {
			hql.append("and o.projectInfo.bidmen.cusName like ? ");
			paramList.add("%" + cusName + "%");
		}
		if (!StringUtil.isBlank(proname)) {
			hql.append("and o.projectInfo.proname like ? ");
			paramList.add("%" + proname + "%");
		}
		if (!StringUtil.isBlank(agentcompany)) {
			hql.append("and o.projectInfo.agentcompany like ? ");
			paramList.add("%" + agentcompany + "%");
		}
		hql.append("order by o.id desc");
		return projectSummaryDao.getPage(pageNo, pageSize, hql.toString(), paramList);
	}
	
	/**
	 * @param projectInfo
	 * @return
	 */
	public ProjectSummary getSummaryByProjectInfo(ProjectInfo projectInfo) {
		String hql = "from ProjectSummary o where o.status <> 0 and o.projectInfo like ? ";
		return projectSummaryDao.getUnique(hql, projectInfo);
	}
	
}

