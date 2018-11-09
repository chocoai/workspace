package com.smart.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.smart.dao.impl.BaseDaoImpl;
import com.smart.dao.ProjectDao;
import com.smart.model.Project;
import com.smart.util.Page;
import com.smart.util.StringUtil;

/**
 * ProjectDaoImpl. @author Auto Tools by 充满智慧的威哥
 */
@Repository
public class ProjectDaoImpl extends BaseDaoImpl<Project, Integer>
		implements ProjectDao {

	@SuppressWarnings("unchecked")
	public Page<Project> getPageHistory(Integer pageNo, Integer pageSize,
			String no, String name, Integer userId) {
		Page<Project> pager = new Page<Project>(pageNo, pageSize);
		List<Project> list = null;
		int count = getPageHistoryCount(pageNo, pageSize, no, name, userId);
		pager.setTotalCount(count);
		if (count > 0) {
			StringBuffer sqlBuff = new StringBuffer("SELECT {p.*} FROM");
			sqlBuff.append(
					" eccs.project_process_history AS pph LEFT JOIN eccs.project AS p ON pph.project_id = p.id INNER JOIN");
			sqlBuff.append(
					" project_process_state AS pps ON pph.project_id = pps.project_id AND pps.type = 1 where 1=1");
			if (!StringUtil.isBlank(no)) {
				sqlBuff.append(" and p.no like ").append("'%").append(no)
						.append("%'");
			}
			if (!StringUtil.isBlank(name)) {
				sqlBuff.append(" and p.name like ").append("'%").append(name)
						.append("%'");
			}
			if (userId != null) {
				sqlBuff.append(" and pph.operate_user=").append(userId);
			}
			sqlBuff.append(" GROUP BY pph.project_id ");
			sqlBuff.append(" ORDER BY p.ctime DESC ");
			sqlBuff.append(" LIMIT ").append(pager.getFirstIndex()).append(",")
					.append(pager.getPageSize());

			SQLQuery sqlQuery = getSession().createSQLQuery(sqlBuff.toString());
			sqlQuery.addEntity("p", Project.class);

			list = sqlQuery.list();
		} else {
			list = new ArrayList<Project>();
		}
		pager.setList(list);
		return pager;
	}

	public int getPageHistoryCount(Integer pageNo, Integer pageSize, String no,
			String name, Integer userId) {
		StringBuffer countBuff = new StringBuffer(
				"select count(*) from (SELECT p.* FROM");
		countBuff.append(
				" eccs.project_process_history AS pph LEFT JOIN eccs.project AS p ON pph.project_id = p.id INNER JOIN");
		countBuff.append(
				" project_process_state AS pps ON pph.project_id = pps.project_id AND pps.type = 1 where 1=1");
		if (!StringUtil.isBlank(no)) {
			countBuff.append(" and p.no like ").append("'%").append(no)
					.append("%'");
		}
		if (!StringUtil.isBlank(name)) {
			countBuff.append(" and p.name like ").append("'%").append(name)
					.append("%'");
		}

		if (userId != null) {
			countBuff.append(" and pph.operate_user=").append(userId);
		}
		countBuff.append(" GROUP BY pph.project_id ");
		countBuff.append(" ) as count");

		return ((Number) getSession().createSQLQuery(countBuff.toString())
				.uniqueResult()).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Project> getPageProject(Integer pageNo, Integer pageSize,
			String no, String name, Integer serviceTypeId) {
		Page<Project> pager = new Page<Project>(pageNo, pageSize);
		List<Project> list = null;
		int count = getPageProjectCount(pageNo, pageSize, no, name,
				serviceTypeId);
		pager.setTotalCount(count);

		if (count > 0) {
			StringBuffer sqlBuff = new StringBuffer("select {p.*} from");
			sqlBuff.append(
					" eccs.project AS p LEFT JOIN eccs.project_process_state AS pps");
			sqlBuff.append(
					" ON p.id = pps.project_id and pps.type = 1 where 1=1");
			if (!StringUtil.isBlank(no)) {
				sqlBuff.append(" and p.no like ").append("'%").append(no)
						.append("%'");
			}
			if (!StringUtil.isBlank(name)) {
				sqlBuff.append(" and p.name like ").append("'%").append(name)
						.append("%'");
			}
			if (serviceTypeId != null) {
				sqlBuff.append(" and p.service_type_id =")
						.append(serviceTypeId);
			}
			sqlBuff.append(" ORDER BY p.ctime DESC ");
			sqlBuff.append(" LIMIT ").append(pager.getFirstIndex()).append(",")
					.append(pager.getPageSize());

			SQLQuery sqlQuery = getSession().createSQLQuery(sqlBuff.toString());
			sqlQuery.addEntity("p", Project.class);

			list = sqlQuery.list();
		} else {
			list = new ArrayList<Project>();
		}
		pager.setList(list);
		return pager;
	}

	public int getPageProjectCount(Integer pageNo, Integer pageSize, String no,
			String name, Integer serviceTypeId) {
		StringBuffer countBuff = new StringBuffer("select count(*) from");
		countBuff.append(
				" eccs.project AS p LEFT JOIN eccs.project_process_state AS pps");
		countBuff
				.append(" ON p.id = pps.project_id and pps.type = 1 where 1=1");
		if (!StringUtil.isBlank(no)) {
			countBuff.append(" and p.no like ").append("'%").append(no)
					.append("%'");
		}
		if (!StringUtil.isBlank(name)) {
			countBuff.append(" and p.name like ").append("'%").append(name)
					.append("%'");
		}
		if (serviceTypeId != null) {
			countBuff.append(" and p.service_type_id =").append(serviceTypeId);
		}

		return ((Number) getSession().createSQLQuery(countBuff.toString())
				.uniqueResult()).intValue();
	}
	
	//获取最后一条项目编号
	public String getProjectLast() {
		String sql = "SELECT proNo FROM project_info WHERE proNo IS  NOT NULL  ORDER BY id DESC LIMIT 1";
		if(getSession().createSQLQuery(sql).uniqueResult()==null){
			return null;
		}else{
			String proNo = getSession().createSQLQuery(sql).uniqueResult().toString();
			System.out.println(proNo);
			return proNo;
		}
	}
}
