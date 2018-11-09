package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.Step15;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.Step15Dao;

/**
 * Step15Service. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class Step15Service {

	@Autowired
	private Step15Dao step15Dao;

	// ====================== 基本 C R U D 方法 ===========================
	public Step15 get(int id) {
		return step15Dao.get(id);
	}

	public void save(Step15 entity) {
		step15Dao.save(entity);
	}

	public void update(Step15 entity) {
		step15Dao.update(entity);
	}

	public void delete(int id) {
		step15Dao.delete(id);
	}

	public List<Step15> getAll() {
		return step15Dao.getAll();
	}

	public Page<Step15> getPage(int pageNo, int pageSize, String no,
			String name, Integer projectTypeId, Integer step) {
		StringBuilder hql = new StringBuilder(
				"from Step15 o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(no)) {
			hql.append("and o.project.no like ? ");
			paramList.add("%" + no + "%");
		}
		if (!StringUtil.isBlank(name)) {
			hql.append("and o.project.name like ? ");
			paramList.add("%" + name + "%");
		}
		if (projectTypeId != null && projectTypeId != 0) {
			hql.append("and o.project.projectType.id = ? ");
			paramList.add(projectTypeId);
		}
		hql.append(" and o.project.step= ? ");
		paramList.add(step);
		hql.append(" order by o.id desc");
		return step15Dao.getPage(pageNo, pageSize, hql.toString(), paramList);
	}

	public Step15 getByProjectId(Integer projectId) {
		StringBuilder hql = new StringBuilder(
				"from Step15 o where o.project.id = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(projectId);
		return step15Dao.getUnique(hql.toString(), paramList);
	}

}
