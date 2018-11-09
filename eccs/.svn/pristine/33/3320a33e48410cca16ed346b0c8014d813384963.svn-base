package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import com.smart.model.PerformanceUser;
import com.smart.dao.PerformanceUserDao;

/**
 * PerformanceUserService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class PerformanceUserService {

	@Autowired
	private PerformanceUserDao performanceUserDao;

	public List<PerformanceUser> getList(int id) {
		StringBuilder hql = new StringBuilder("from PerformanceUser o where ");
		List<Object> paramList = new ArrayList<Object>();
		if (id != 0) {
			hql.append("  o.performance.id = ? ");
			paramList.add(id);
		}
		hql.append("order by o.id asc");
		return performanceUserDao.getList(hql.toString(), paramList);
	}

	// ====================== 基本 C R U D 方法 ===========================
	public PerformanceUser get(int id) {
		return performanceUserDao.get(id);
	}

	public void save(PerformanceUser entity) {
		performanceUserDao.save(entity);
	}

	public void update(PerformanceUser entity) {
		performanceUserDao.update(entity);
	}

	public void delete(int id) {
		performanceUserDao.delete(id);
	}

	public List<PerformanceUser> getAll() {
		return performanceUserDao.getAll();
	}

}
