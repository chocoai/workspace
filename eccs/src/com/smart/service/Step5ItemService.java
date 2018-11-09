package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.smart.model.Step5Item;
import com.smart.util.Page;
import com.smart.dao.Step5ItemDao;

/**
 * Step5Service. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class Step5ItemService {

	@Autowired
	private Step5ItemDao step5ItemDao;

	// ====================== 基本 C R U D 方法 ===========================
	public Step5Item get(int id) {
		return step5ItemDao.get(id);
	}

	public void save(Step5Item entity) {
		step5ItemDao.save(entity);
	}

	public void update(Step5Item entity) {
		step5ItemDao.update(entity);
	}

	public void delete(int id) {
		step5ItemDao.delete(id);
	}

	public List<Step5Item> getAll() {
		return step5ItemDao.getAll();
	}

	public Page<Step5Item> getPage(int pageNo, int pageSize, Integer step5Id) {
		StringBuilder hql = new StringBuilder(
				"from Step5Item o where o.status = 0 ");
		if (step5Id != null) {
			hql.append(" and o.step5.id = ? ");
		}
		hql.append(" order by o.ctime desc");
		return step5ItemDao.getPage(pageNo, pageSize, hql.toString(), step5Id);
	}

	public List<Step5Item> getByStep5Id(Integer step5Id) {
		StringBuilder hql = new StringBuilder(
				"from Step5Item o where o.status = 0 ");
		if (step5Id != null) {
			hql.append(" and o.step5.id = ? ");
		}
		hql.append(" order by o.ctime desc");
		return step5ItemDao.getList(hql.toString(), step5Id);
	}

}
