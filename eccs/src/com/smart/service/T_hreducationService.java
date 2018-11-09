package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.smart.model.T_hreducation;
import com.smart.dao.T_hreducationDao;

/**
 * T_hreducationService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class T_hreducationService {

	@Autowired
	private T_hreducationDao t_hreducationDao;

	// ====================== 基本 C R U D 方法 ===========================
	public T_hreducation get(int id) {
		return t_hreducationDao.get(id);
	}

	public void save(T_hreducation entity) {
		t_hreducationDao.save(entity);
	}

	public void update(T_hreducation entity) {
		t_hreducationDao.update(entity);
	}

	public void delete(int id) {
		t_hreducationDao.delete(id);
	}

	public List<T_hreducation> getAll() {
		return t_hreducationDao.getAll();
	}

	public List<T_hreducation> getList(Integer id) {
		String hql = "from T_hreducation c where c.gdate=(select max(gdate) from T_hreducation where status=1 and t_hremployee="
				+ id + ") and c.status=1";
		return t_hreducationDao.getList(hql);
	}

	public List<T_hreducation> getLists(Integer id) {
		String hql = "from T_hreducation c where c.status=1 and c.t_hremployee="
				+ id + "order by c.gdate";
		return t_hreducationDao.getList(hql);
	}
}
