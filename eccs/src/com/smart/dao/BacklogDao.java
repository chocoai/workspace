package com.smart.dao;

import java.util.List;

import com.smart.dao.BaseDao;
import com.smart.model.Backlog;

/**
 * BacklogDao. @author Auto Tools by 充满智慧的威哥
 */
public interface BacklogDao extends BaseDao<Backlog, Integer> {

	public List<Backlog> getList(Integer projectId, Integer status,
			Integer step, Integer userId);

	public void updateToFinished(Integer projectId, Integer userId,
			Integer status, Integer step);

	public Backlog getOneByProjectIdAndUserIdAndStep(Integer projectId,
			Integer userId, Integer step);

	public int deleteByProjectIdAndUserIdAndStep(Integer projectId,
			Integer userId, Integer step);

	public int deleteByProjectIdAndStep(Integer projectId, Integer step);

}
