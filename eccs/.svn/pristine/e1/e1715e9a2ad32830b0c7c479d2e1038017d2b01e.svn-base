package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.Step2File;
import com.smart.dao.Step2FileDao;

/**
 * Step2FileService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class Step2FileService {

	@Autowired
	private Step2FileDao step2FileDao;

	// ====================== 基本 C R U D 方法 ===========================
	public Step2File get(int id) {
		return step2FileDao.get(id);
	}

	public void save(Step2File entity) {
		step2FileDao.save(entity);
	}

	public void update(Step2File entity) {
		step2FileDao.update(entity);
	}

	public void delete(int id) {
		step2FileDao.delete(id);
	}

	public List<Step2File> getAll() {
		return step2FileDao.getAll();
	}

	public void deleteByStep2Id(Integer step2id) {
		StringBuilder hql = new StringBuilder(
				"delete from Step2File o where o.step2.id = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(step2id);
		step2FileDao.delete(hql.toString(), paramList);
	}

	public List<Step2File> getByStep2Id(Integer step2id) {
		StringBuilder hql = new StringBuilder(
				"from Step2File o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (step2id == null) {
			return new ArrayList<Step2File>();
		} else {
			hql.append("and o.step2.id = ?");
			paramList.add(step2id);
		}
		hql.append("order by o.id asc");
		return step2FileDao.getList(hql.toString(), paramList);
	}

}
