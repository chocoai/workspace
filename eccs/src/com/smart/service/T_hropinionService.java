package com.smart.service;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.smart.model.T_hropinion;
import com.smart.dao.T_hropinionDao;

/**
 * T_hropinionService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class T_hropinionService {

	@Autowired
	private T_hropinionDao t_hropinionDao;

	// ====================== 基本 C R U D 方法 ===========================
	public T_hropinion get(int id) {
		return t_hropinionDao.get(id);
	}

	public void save(T_hropinion entity) {
		t_hropinionDao.save(entity);
	}

	public void update(T_hropinion entity) {
		t_hropinionDao.update(entity);
	}

	public void delete(int id) {
		t_hropinionDao.delete(id);
	}

	public List<T_hropinion> getAll() {
		return t_hropinionDao.getAll();
	}

}
