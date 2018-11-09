package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.smart.model.ConsultType;
import com.smart.dao.ConsultTypeDao;

/**
 * ConsultTypeService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class ConsultTypeService {

	@Autowired
	private ConsultTypeDao consultTypeDao;

	// ====================== 基本 C R U D 方法 ===========================
	public ConsultType get(int id) {
		return consultTypeDao.get(id);
	}

	public void save(ConsultType entity) {
		consultTypeDao.save(entity);
	}

	public void update(ConsultType entity) {
		consultTypeDao.update(entity);
	}

	public void delete(int id) {
		consultTypeDao.delete(id);
	}

	public List<ConsultType> getAll() {
		return consultTypeDao.getAll();
	}

}
