/**
 * 
 */
package com.smart.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.smart.dao.ProjectProcessHistoryDao;
import com.smart.model.ProjectProcessHistory;

/**
 * @Description:
 * @author raopanfeng
 * @date 2017年1月20日 下午5:57:50
 */
@Repository
public class ProjectProcessHistoryDaoImpl
		extends BaseDaoImpl<ProjectProcessHistory, Integer>
		implements ProjectProcessHistoryDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smart.dao.ProjectProcessHistoryDao#getListByProjectId(java.lang.
	 * Integer)
	 */
	@Override
	public List<ProjectProcessHistory> getListByProjectId(Integer projectId) {
		StringBuilder hql = new StringBuilder();
		hql.append("from ProjectProcessHistory bean where bean.project.id = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(projectId);
		hql.append(" order by bean.operateTime desc ");
		List<ProjectProcessHistory> pphList = this.getList(hql.toString(),
				paramList);
		return pphList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smart.dao.ProjectProcessHistoryDao#getListByProjectId(java.lang.
	 * Integer)
	 */
	@Override
	public List<ProjectProcessHistory> getListByProjectInfoId(Integer projectInfoId) {
		StringBuilder hql = new StringBuilder();
		hql.append("from ProjectProcessHistory bean where bean.projectInfo.id = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(projectInfoId);
		hql.append(" order by bean.operateTime desc ");
		List<ProjectProcessHistory> pphList = this.getList(hql.toString(),
				paramList);
		return pphList;
	}
}
