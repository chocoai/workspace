package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.Step8Item;
import com.smart.dao.Step8ItemDao;

/**
 * Step8ItemService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class Step8ItemService {

	@Autowired
	private Step8ItemDao step8ItemDao;

	// ====================== 基本 C R U D 方法 ===========================
	public Step8Item get(int id) {
		return step8ItemDao.get(id);
	}

	public void save(Step8Item entity) {
		step8ItemDao.save(entity);
	}

	public void update(Step8Item entity) {
		step8ItemDao.update(entity);
	}

	public void delete(int id) {
		step8ItemDao.delete(id);
	}

	public List<Step8Item> getAll() {
		return step8ItemDao.getAll();
	}

	public void deleteByStep8Id(Integer Step8Id) {
		StringBuilder hql = new StringBuilder(
				"delete from Step8Item o where o.step8.id = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(Step8Id);
		step8ItemDao.delete(hql.toString(), paramList);
	}

	public List<Step8Item> getByStep8Id(Integer Step8Id) {
		StringBuilder hql = new StringBuilder(
				"from Step8Item o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (Step8Id == null) {
			return new ArrayList<Step8Item>();
		} else {
			hql.append("and o.step8.id = ?");
			paramList.add(Step8Id);
		}
		hql.append("order by o.id desc");
		return step8ItemDao.getList(hql.toString(), paramList);
	}
}
