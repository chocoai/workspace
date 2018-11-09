package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.smart.model.T_handle;
import com.smart.dao.T_handleDao;

/**
 * T_handleService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class T_handleService {

	@Autowired
	private T_handleDao t_handleDao;

	// ====================== 基本 C R U D 方法 ===========================
	public T_handle get(int id) {
		return t_handleDao.get(id);
	}

	public void save(T_handle entity) {
		t_handleDao.save(entity);
	}

	public void update(T_handle entity) {
		t_handleDao.update(entity);
	}

	public void delete(int id) {
		t_handleDao.delete(id);
	}

	public List<T_handle> getAll() {
		return t_handleDao.getAll();
	}

}
