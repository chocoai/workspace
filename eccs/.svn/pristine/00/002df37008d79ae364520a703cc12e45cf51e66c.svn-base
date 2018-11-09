package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.smart.model.T_sealOption;
import com.smart.dao.T_sealOptionDao;

/**
 * T_sealOptionService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class T_sealOptionService {

	@Autowired
	private T_sealOptionDao t_sealOptionDao;

	// ====================== 基本 C R U D 方法 ===========================
	public T_sealOption get(int id) {
		return t_sealOptionDao.get(id);
	}

	public void save(T_sealOption entity) {
		t_sealOptionDao.save(entity);
	}

	public void update(T_sealOption entity) {
		t_sealOptionDao.update(entity);
	}

	public void delete(int id) {
		t_sealOptionDao.delete(id);
	}

	public List<T_sealOption> getAll() {
		return t_sealOptionDao.getAll();
	}

}
