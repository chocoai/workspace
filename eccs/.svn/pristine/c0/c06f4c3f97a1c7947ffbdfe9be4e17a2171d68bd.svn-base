package com.smart.service;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import com.smart.model.Step13Item;
import com.smart.dao.Step13ItemDao;

/**
 * Step13ItemService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class Step13ItemService {

	@Autowired
	private Step13ItemDao step13ItemDao;

	// ====================== 基本 C R U D 方法 ===========================
	public Step13Item get(int id) {
		return step13ItemDao.get(id);
	}

	public void save(Step13Item entity) {
		step13ItemDao.save(entity);
	}

	public void update(Step13Item entity) {
		step13ItemDao.update(entity);
	}

	public void delete(int id) {
		step13ItemDao.delete(id);
	}

	public List<Step13Item> getAll() {
		return step13ItemDao.getAll();
	}

	public List<Step13Item> getByStep13Id(Integer Step13Id) {
		StringBuilder hql = new StringBuilder(
				"from Step13Item o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (Step13Id == null) {
			return new ArrayList<Step13Item>();
		} else {
			hql.append("and o.step13.id = ?");
			paramList.add(Step13Id);
		}
		hql.append("order by o.id desc");
		return step13ItemDao.getList(hql.toString(), paramList);
	}

}
