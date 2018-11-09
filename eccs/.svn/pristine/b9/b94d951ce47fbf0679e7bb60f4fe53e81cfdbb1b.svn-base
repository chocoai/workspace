package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import com.smart.model.Step10FKYJ;
import com.smart.dao.Step10FKYJDao;

/**
 * Step10FKYJService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class Step10FKYJService {

	@Autowired
	private Step10FKYJDao step10FKYJDao;

	// ====================== 基本 C R U D 方法 ===========================
	public Step10FKYJ get(int id) {
		return step10FKYJDao.get(id);
	}

	public void save(Step10FKYJ entity) {
		step10FKYJDao.save(entity);
	}

	public void update(Step10FKYJ entity) {
		step10FKYJDao.update(entity);
	}

	public void delete(int id) {
		step10FKYJDao.delete(id);
	}

	public List<Step10FKYJ> getAll() {
		return step10FKYJDao.getAll();
	}

	public List<Step10FKYJ> getByStep10Id(Integer step10id) {
		StringBuilder hql = new StringBuilder(
				"from Step10FKYJ o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (step10id == null) {
			return new ArrayList<Step10FKYJ>();
		} else {
			hql.append("and o.step10.id = ?");
			paramList.add(step10id);
		}
		hql.append("order by o.id asc");
		return step10FKYJDao.getList(hql.toString(), paramList);
	}

}
