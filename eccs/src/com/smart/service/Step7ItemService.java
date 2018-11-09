package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.Step7Item;
import com.smart.dao.Step7ItemDao;

/**
 * Step7ItemService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class Step7ItemService {

	@Autowired
	private Step7ItemDao step7ItemDao;

	// ====================== 基本 C R U D 方法 ===========================
	public Step7Item get(int id) {
		return step7ItemDao.get(id);
	}

	public void save(Step7Item entity) {
		step7ItemDao.save(entity);
	}

	public void update(Step7Item entity) {
		step7ItemDao.update(entity);
	}

	public void delete(int id) {
		step7ItemDao.delete(id);
	}

	public List<Step7Item> getAll() {
		return step7ItemDao.getAll();
	}

	public void deleteByStep7Id(Integer Step7Id) {
		StringBuilder hql = new StringBuilder(
				"delete from Step7Item o where o.step7.id = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(Step7Id);
		step7ItemDao.delete(hql.toString(), paramList);
	}

	public List<Step7Item> getByStep7Id(Integer Step7Id) {
		StringBuilder hql = new StringBuilder(
				"from Step7Item o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (Step7Id == null) {
			return new ArrayList<Step7Item>();
		} else {
			hql.append("and o.step7.id = ?");
			paramList.add(Step7Id);
		}
		hql.append("order by o.id desc");
		return step7ItemDao.getList(hql.toString(), paramList);
	}
}
