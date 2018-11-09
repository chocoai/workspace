package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.smart.model.T_borrow_registration;
import com.smart.dao.T_borrow_registrationDao;

/**
 * T_borrow_registrationService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class T_borrow_registrationService {

	@Autowired
	private T_borrow_registrationDao t_borrow_registrationDao;

	// ====================== 基本 C R U D 方法 ===========================
	public T_borrow_registration get(int id) {
		return t_borrow_registrationDao.get(id);
	}

	public void save(T_borrow_registration entity) {
		t_borrow_registrationDao.save(entity);
	}

	public void update(T_borrow_registration entity) {
		t_borrow_registrationDao.update(entity);
	}

	public void delete(int id) {
		t_borrow_registrationDao.delete(id);
	}

	public List<T_borrow_registration> getAll() {
		return t_borrow_registrationDao.getAll();
	}

	public List<T_borrow_registration> getList(Integer lire) {
		String hql = "from T_borrow_registration c where c.status = 1 and c.borrow="
				+ lire + "";
		return t_borrow_registrationDao.getList(hql);
	}
}
