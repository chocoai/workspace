package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import com.smart.model.Step10DFYJ;
import com.smart.dao.Step10DFYJDao;

/**
 * Step10DFYJService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class Step10DFYJService {

	@Autowired
	private Step10DFYJDao step10DFYJDao;

	// ====================== 基本 C R U D 方法 ===========================
	public Step10DFYJ get(int id) {
		return step10DFYJDao.get(id);
	}

	public void save(Step10DFYJ entity) {
		step10DFYJDao.save(entity);
	}

	public void update(Step10DFYJ entity) {
		step10DFYJDao.update(entity);
	}

	public void delete(int id) {
		step10DFYJDao.delete(id);
	}

	public List<Step10DFYJ> getAll() {
		return step10DFYJDao.getAll();
	}

	public List<Step10DFYJ> getByStep10Id(Integer step10id) {
		StringBuilder hql = new StringBuilder(
				"from Step10DFYJ o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (step10id == null) {
			return new ArrayList<Step10DFYJ>();
		} else {
			hql.append("and o.step10.id = ?");
			paramList.add(step10id);
		}
		hql.append("order by o.id asc");
		return step10DFYJDao.getList(hql.toString(), paramList);
	}

}
