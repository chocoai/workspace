package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.Step9Item;
import com.smart.dao.Step9ItemDao;

/**
 * Step9ItemService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class Step9ItemService {

	@Autowired
	private Step9ItemDao step9ItemDao;

	// ====================== 基本 C R U D 方法 ===========================
	public Step9Item get(int id) {
		return step9ItemDao.get(id);
	}

	public void save(Step9Item entity) {
		step9ItemDao.save(entity);
	}

	public void update(Step9Item entity) {
		step9ItemDao.update(entity);
	}

	public void delete(int id) {
		step9ItemDao.delete(id);
	}

	public List<Step9Item> getAll() {
		return step9ItemDao.getAll();
	}

	public void deleteByStep9Id(Integer Step9Id) {
		StringBuilder hql = new StringBuilder(
				"delete from Step9Item o where o.step9.id = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(Step9Id);
		step9ItemDao.delete(hql.toString(), paramList);
	}

	public List<Step9Item> getByStep9Id(Integer Step9Id) {
		StringBuilder hql = new StringBuilder(
				"from Step9Item o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (Step9Id == null) {
			return new ArrayList<Step9Item>();
		} else {
			hql.append("and o.step9.id = ?");
			paramList.add(Step9Id);
		}
		hql.append("order by o.id desc");
		return step9ItemDao.getList(hql.toString(), paramList);
	}

}
