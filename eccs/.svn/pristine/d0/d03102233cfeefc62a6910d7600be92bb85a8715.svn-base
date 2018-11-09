package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.smart.model.T_hrcertificate;
import com.smart.dao.T_hrcertificateDao;

/**
 * T_hrcertificateService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class T_hrcertificateService {

	@Autowired
	private T_hrcertificateDao t_hrcertificateDao;

	// ====================== 基本 C R U D 方法 ===========================
	public T_hrcertificate get(int id) {
		return t_hrcertificateDao.get(id);
	}

	public void save(T_hrcertificate entity) {
		t_hrcertificateDao.save(entity);
	}

	public void update(T_hrcertificate entity) {
		t_hrcertificateDao.update(entity);
	}

	public void delete(int id) {
		t_hrcertificateDao.delete(id);
	}

	public List<T_hrcertificate> getAll() {
		return t_hrcertificateDao.getAll();
	}

	public List<T_hrcertificate> getList(Integer id) {
		String hql = "from T_hrcertificate c where c.status=1 and c.t_hremployee="
				+ id + "";
		return t_hrcertificateDao.getList(hql);
	}

	public List<T_hrcertificate> getList() {
		String hql = "from T_hrcertificate c where c.status=1";
		return t_hrcertificateDao.getList(hql);
	}
}
