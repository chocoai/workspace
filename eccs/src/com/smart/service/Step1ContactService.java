package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.Step1Contact;
import com.smart.dao.Step1ContactDao;

/**
 * Step1ContactService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class Step1ContactService {

	@Autowired
	private Step1ContactDao step1ContactDao;

	// ====================== 基本 C R U D 方法 ===========================
	public Step1Contact get(int id) {
		return step1ContactDao.get(id);
	}

	public void save(Step1Contact entity) {
		step1ContactDao.save(entity);
	}

	public void update(Step1Contact entity) {
		step1ContactDao.update(entity);
	}

	public void delete(int id) {
		step1ContactDao.delete(id);
	}

	public List<Step1Contact> getAll() {
		return step1ContactDao.getAll();
	}

	public List<Step1Contact> getByStep1Id(Integer Step1Id) {
		StringBuilder hql = new StringBuilder(
				"from Step1Contact o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (Step1Id == null) {
			return new ArrayList<Step1Contact>();
		} else {
			hql.append("and o.step1.id = ?");
			paramList.add(Step1Id);
		}
		hql.append("order by o.id desc");
		return step1ContactDao.getList(hql.toString(), paramList);
	}

}
