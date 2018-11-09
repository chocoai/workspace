package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.smart.model.T_file;
import com.smart.dao.T_fileDao;

/**
 * T_fileService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class T_fileService {

	@Autowired
	private T_fileDao t_fileDao;

	// ====================== 基本 C R U D 方法 ===========================
	public T_file get(int id) {
		return t_fileDao.get(id);
	}

	public void save(T_file entity) {
		t_fileDao.save(entity);
	}

	public void update(T_file entity) {
		t_fileDao.update(entity);
	}

	public void delete(int id) {
		t_fileDao.delete(id);
	}

	public List<T_file> getAll() {
		return t_fileDao.getAll();
	}

	public List<T_file> getList(Integer lire, Integer ids) {
		String hql = "from T_file c where c.status = 1 and c.type_id=" + lire
				+ " and c.type=" + ids + "";
		return t_fileDao.getList(hql);
	}
}
