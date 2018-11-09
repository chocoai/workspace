/**
 * 
 */
package com.smart.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.smart.dao.ProjectProcessStateDao;
import com.smart.model.ProjectProcessState;
import com.smart.util.Page;
import com.smart.util.StringUtil;

/**
 * @Description:
 * @author raopanfeng
 * @date 2017年1月20日 下午5:01:39
 */
@Repository
public class ProjectProcessStateDaoImpl
		extends BaseDaoImpl<ProjectProcessState, Integer>
		implements ProjectProcessStateDao {

	/**
	 * 根据项目ID查询当前项目的状态
	 */
	@Override
	public List<ProjectProcessState> getListByProjectId(Integer projectId) {
		StringBuilder hql = new StringBuilder();
		hql.append("from ProjectProcessState bean where bean.project.id = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(projectId);
		List<ProjectProcessState> ppsList = this.getList(hql.toString(),
				paramList);
		return ppsList;
	}
	
	/**
	 * 根据项目ID查询当前项目的状态
	 */
	@Override
	public List<ProjectProcessState> getListByProjectInfoId(Integer ProjectInfoId) {
		StringBuilder hql = new StringBuilder();
		hql.append("from ProjectProcessState bean where bean.projectInfo.id = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(ProjectInfoId);
		List<ProjectProcessState> ppsList = this.getList(hql.toString(),
				paramList);
		return ppsList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.smart.dao.ProjectProcessStateDao#getOneByProjectIdAndStepCode(java.
	 * lang.Integer, java.lang.String)
	 */
	@Override
	public ProjectProcessState getOneByProjectIdAndStepCode(Integer projectId,
			String stepCode) {
		StringBuilder hql = new StringBuilder();
		hql.append(
				"from ProjectProcessState bean where bean.project.id = ? and bean.currentStep.stepCode = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(projectId);
		paramList.add(stepCode);
		ProjectProcessState pss = this.getUnique(hql.toString(), paramList);
		return pss;
	}

	@Override
	public ProjectProcessState getOneByProjectIdAndBusinesstype(
			Integer projectId, Integer businessType) {
		StringBuilder hqlBuilder = new StringBuilder();
		hqlBuilder.append("from ProjectProcessState bean where 1=1");
		if (projectId != null) {
			hqlBuilder.append(" and bean.project.id = ").append(projectId);
		}
		if (businessType != null) {
			hqlBuilder.append(" and bean.type =").append(businessType);
		}
		ProjectProcessState pss = this.getUnique(hqlBuilder.toString());
		return pss;
	}

	@Override
	public Page<ProjectProcessState> getPageState(Integer pageNo,
			Integer pageSize, String no, String name, Integer userId,
			String businessType) {
		StringBuilder hqlBuilder = new StringBuilder();
		hqlBuilder.append("from ProjectProcessState bean where 1=1 ");
		if (!StringUtil.isBlank(no)) {
			hqlBuilder.append(" and bean.project.no like '%").append(no)
					.append("%'");
		}
		if (!StringUtil.isBlank(name)) {
			hqlBuilder.append(" and bean.project.name like '%").append(name)
					.append("%'");
		}

		if (userId != null) {
			hqlBuilder.append(" and find_in_set('").append(userId)
					.append("',bean.currentUsers) > 0");
		}
		if (businessType != null) {
			hqlBuilder.append(" and bean.type =").append(businessType);
		}
		hqlBuilder.append(" order by bean.lastUpdateTime desc");
		return this.getPage(pageNo, pageSize, hqlBuilder.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smart.dao.ProjectProcessStateDao#countBacklog(java.lang.Integer)
	 */
	@Override
	public Integer countBacklog(Integer userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) from eccs.project_process_state t ");
		sql.append("where find_in_set('").append(userId)
				.append("',t.current_user_id) > 0");
		SQLQuery sqlQuery = getSession().createSQLQuery(sql.toString());
		return ((Number) sqlQuery.uniqueResult()).intValue();
	}

	/* (non-Javadoc)
	 * @see com.smart.dao.ProjectProcessStateDao#getOneByProjectInfoIdAndStepCode(java.lang.Integer, java.lang.String)
	 */
	@Override
	public ProjectProcessState getOneByProjectInfoIdAndStepCode(Integer id,
			String stepCode) {
		StringBuilder hql = new StringBuilder();
		hql.append(
				"from ProjectProcessState bean where bean.projectInfo.id = ? and bean.currentStep.stepCode = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(id);
		paramList.add(stepCode);
		ProjectProcessState pss = this.getUnique(hql.toString(), paramList);
		return pss;
	}

	/* (non-Javadoc)
	 * @see com.smart.dao.ProjectProcessStateDao#getOneByProjectInfoId(java.lang.Integer)
	 */
	@Override
	public ProjectProcessState getOneByProjectInfoId(Integer id) {
		StringBuilder hql = new StringBuilder();
		hql.append(
				"from ProjectProcessState bean where bean.projectInfo.id = ?");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(id);
		ProjectProcessState pss = this.getUnique(hql.toString(), paramList);
		return pss;
	}

}
