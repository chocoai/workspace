package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.Step1;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.Step1Dao;

/**
 * Step1Service. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class Step1Service {

	@Autowired
	private Step1Dao step1Dao;

	// ====================== 基本 C R U D 方法 ===========================
	public Step1 get(int id) {
		return step1Dao.get(id);
	}

	public void save(Step1 entity) {
		step1Dao.save(entity);
	}

	public void update(Step1 entity) {
		step1Dao.update(entity);
	}

	public void delete(int id) {
		step1Dao.delete(id);
	}

	public List<Step1> getAll() {
		return step1Dao.getAll();
	}

	public Page<Step1> getPage(int pageNo, int pageSize, String no, String name,
			Integer projectTypeId, Integer step) {
		StringBuilder hql = new StringBuilder(
				"from Step1 o where o.status = 1 ");
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
		return step1Dao.getPage(pageNo, pageSize, hql.toString(), paramList);
	}

	public Step1 getByProjectId(Integer projectId) {
		StringBuilder hql = new StringBuilder(
				"from Step1 o where o.project.id = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(projectId);
		return step1Dao.getUnique(hql.toString(), paramList);
	}

}
