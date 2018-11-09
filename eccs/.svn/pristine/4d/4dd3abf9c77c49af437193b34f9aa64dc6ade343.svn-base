package com.smart.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.smart.dao.impl.BaseDaoImpl;
import com.smart.dao.BacklogDao;
import com.smart.model.Backlog;

/**
 * BacklogDaoImpl. @author Auto Tools by 充满智慧的威哥
 */
@Repository
public class BacklogDaoImpl extends BaseDaoImpl<Backlog, Integer>
		implements BacklogDao {

	public List<Backlog> getList(Integer projectId, Integer status,
			Integer step, Integer userId) {
		StringBuffer hql = new StringBuffer("from Backlog b where 1=1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (projectId != null) {
			hql.append(" and b.project.id=? ");
			paramList.add(projectId);
		}
		if (status != null) {
			hql.append(" and b.status=? ");
			paramList.add(status);
		}
		if (step != null) {
			hql.append(" and b.projectStep=? ");
			paramList.add(step);
		}
		if (userId != null) {
			hql.append(" and b.user.id=? ");
			paramList.add(userId);
		}
		hql.append(" order by b.ctime desc ");
		return getList(hql.toString(), paramList);
	}

	public void updateToFinished(Integer projectId, Integer userId,
			Integer status, Integer step) {
		String hql = " update Backlog b set b.status=? where b.project.id=? and b.toUser.id=? and b.projectStep=? and b.status=1";
		Query query = getSession().createQuery(hql).setParameter(0, status)
				.setParameter(1, projectId).setParameter(2, userId)
				.setParameter(3, step);
		query.executeUpdate();
	}

	public Backlog getOneByProjectIdAndUserIdAndStep(Integer projectId,
			Integer userId, Integer step) {
		String hql = " from Backlog b where b.status=1 and b.project.id=? and b.toUser.id=? and b.projectStep=? ";
		Query query = getSession().createQuery(hql).setParameter(0, projectId)
				.setParameter(1, userId).setParameter(2, step);
		Object obj = query.uniqueResult();
		Backlog backlog = obj == null ? null : (Backlog) obj;
		return backlog;
	}

	public int deleteByProjectIdAndStep(Integer projectId, Integer step) {
		String hql = "update Backlog b set b.status=-1 where b.project.id=? and b.projectStep=? and b.status=1";
		Query query = getSession().createQuery(hql).setParameter(0, projectId)
				.setParameter(1, step);
		return query.executeUpdate();
	}

	public int deleteByProjectIdAndUserIdAndStep(Integer projectId,
			Integer userId, Integer step) {
		String hql = "update Backlog b set b.status=-1 where b.project.id=? and b.toUser.id=? and b.projectStep=? and b.status=1";
		Query query = getSession().createQuery(hql).setParameter(0, projectId)
				.setParameter(1, userId).setParameter(2, step);
		return query.executeUpdate();
	}

}
