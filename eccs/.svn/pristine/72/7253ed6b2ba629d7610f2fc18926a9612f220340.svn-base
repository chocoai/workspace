package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.Step3Worker;
import com.smart.util.StringUtil;
import com.smart.dao.Step3WorkerDao;

/**
 * Step3WorkerService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class Step3WorkerService {

	@Autowired
	private Step3WorkerDao step3WorkerDao;

	// ====================== 基本 C R U D 方法 ===========================
	public Step3Worker get(int id) {
		return step3WorkerDao.get(id);
	}

	public void save(Step3Worker entity) {
		step3WorkerDao.save(entity);
	}

	public void update(Step3Worker entity) {
		step3WorkerDao.update(entity);
	}

	public void delete(int id) {
		step3WorkerDao.delete(id);
	}

	public List<Step3Worker> getAll() {
		return step3WorkerDao.getAll();
	}

	/**
	 * 查询某环节的操作用户
	 * 
	 * @return: Step3Worker
	 */
	public Step3Worker get(Integer projectid, String workType, Integer type) {
		List<Step3Worker> list = getByProjectId(projectid, workType, type);
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * 查询某项目前五部处理用户
	 * 
	 * @return: String 用户ID 用","隔开
	 */
	public String getStep5(Integer projectid, String workType, Integer type) {
		return step3WorkerDao.getStep5(projectid, workType, type);
	}

	public void deleteByProjectId(Integer projectid, String workType) {
		StringBuilder hql = new StringBuilder(
				"delete from Step3Worker o where o.project.id = ? and o.workType = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(projectid);
		paramList.add(workType);
		step3WorkerDao.delete(hql.toString(), paramList);
	}

	public boolean CheckForSave(Integer projectid, Integer type,
			Integer userid) {
		if (projectid == null || type == null || userid == null) {
			return true;
		}
		StringBuilder hql = new StringBuilder(
				"from Step3Worker o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		hql.append("and o.project.id = ?").append("and o.type = ?")
				.append("and o.workUser.id = ?");
		paramList.add(projectid);
		paramList.add(type);
		paramList.add(userid);
		List<Step3Worker> list = step3WorkerDao.getList(hql.toString(),
				paramList);
		return list.size() > 0 ? false : true;
	}

	public List<Step3Worker> getByProjectId(Integer projectid, String workType,
			Integer type) {
		StringBuilder hql = new StringBuilder(
				"from Step3Worker o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (projectid == null) {
			return new ArrayList<Step3Worker>();
		} else {
			hql.append("and o.project.id = ?");
			paramList.add(projectid);
		}
		if (!StringUtil.isBlank(workType)) {
			hql.append("and o.workType = ?");
			paramList.add(workType);
		}

		if (type != null) {
			hql.append("and o.type = ?");
			paramList.add(type);
		}
		hql.append("order by o.type ");
		return step3WorkerDao.getList(hql.toString(), paramList);
	}

}
