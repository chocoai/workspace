package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import com.smart.model.Performance;
import com.smart.dao.PerformanceDao;

/**
 * PerformanceService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class PerformanceService {

	@Autowired
	private PerformanceDao performanceDao;

	public Performance getList(int id) {
		StringBuilder hql = new StringBuilder("from Performance o where ");
		List<Object> paramList = new ArrayList<Object>();
		if (id != 0) {
			hql.append("  o.project.id = ? ");
			paramList.add(id);
		}
		hql.append("order by o.id asc");
		return performanceDao.getList(hql.toString(), paramList).get(0);
	}

	// ====================== 基本 C R U D 方法 ===========================
	public Performance get(int id) {
		return performanceDao.get(id);
	}

	public void save(Performance entity) {
		performanceDao.save(entity);
	}

	public void update(Performance entity) {
		performanceDao.update(entity);
	}

	public void delete(int id) {
		performanceDao.delete(id);
	}

	public List<Performance> getAll() {
		return performanceDao.getAll();
	}

}
