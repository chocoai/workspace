package com.smart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.ProjectTransferRecordDao;
import com.smart.model.ProjectInfo;
import com.smart.model.ProjectTransferRecord;
import com.smart.util.Page;
import com.smart.util.StringUtil;

/**
 * ProjectTransferRecordService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class ProjectTransferRecordService {
	
	
	@Autowired
	private ProjectTransferRecordDao projectTransferRecordDao;
	
	
	
	
	
	//====================== 基本 C R U D 方法  ===========================
	public ProjectTransferRecord get(Integer id) {
		return projectTransferRecordDao.get(id);
	}
	
	public void save(ProjectTransferRecord entity) {
		projectTransferRecordDao.save(entity);
	}
	
	public void update(ProjectTransferRecord entity) {
		projectTransferRecordDao.update(entity);
	}
	
	public void delete(Integer id) {
		projectTransferRecordDao.delete(id);
	}
	
	public List<ProjectTransferRecord> getAll() {
		return projectTransferRecordDao.getAll();
	}

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param cusName
	 * @param proname
	 * @param agentcompany 
	 * @return
	 */
	public Page<ProjectTransferRecord> getPage(int pageNo, int pageSize, String cusName,
			String proname, String agentcompany) {
		StringBuilder hql = new StringBuilder();
		hql.append("from ProjectTransferRecord o where o.status <> 0 ");
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
		return projectTransferRecordDao.getPage(pageNo, pageSize, hql.toString(), paramList);
	}

	/**
	 * @return
	 */
	public String getTransferLast() {
		return projectTransferRecordDao.getTransferLast();
	}
	
	/**
	 * @param projectInfo
	 * @return
	 */
	public ProjectTransferRecord getTransferByProjectInfo(
			ProjectInfo projectInfo) {
		String hql = "from ProjectTransferRecord o where o.status <> 0 and o.projectInfo like ? ";
		return projectTransferRecordDao.getUnique(hql, projectInfo);
	}
	
}

