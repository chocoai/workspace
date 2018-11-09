package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.Step4File;
import com.smart.dao.Step4FileDao;

/**
 * Step4FileService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class Step4FileService {

	@Autowired
	private Step4FileDao step4FileDao;

	// ====================== 基本 C R U D 方法 ===========================
	public Step4File get(int id) {
		return step4FileDao.get(id);
	}

	public void save(Step4File entity) {
		step4FileDao.save(entity);
	}

	public void update(Step4File entity) {
		step4FileDao.update(entity);
	}

	public void delete(int id) {
		step4FileDao.delete(id);
	}

	public List<Step4File> getAll() {
		return step4FileDao.getAll();
	}

	public void deleteByStep4Id(Integer step4id) {
		StringBuilder hql = new StringBuilder(
				"delete from Step4File o where o.step4.id = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(step4id);
		step4FileDao.delete(hql.toString(), paramList);
	}

	public List<Step4File> getByStep4Id(Integer step4id) {
		StringBuilder hql = new StringBuilder(
				"from Step4File o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (step4id == null) {
			return new ArrayList<Step4File>();
		} else {
			hql.append("and o.step4.id = ?");
			paramList.add(step4id);
		}
		hql.append("order by o.id asc");
		return step4FileDao.getList(hql.toString(), paramList);
	}
}
