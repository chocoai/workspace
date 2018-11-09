package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.smart.model.UrgentType;
import com.smart.dao.UrgentTypeDao;

/**
 * UrgentTypeService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class UrgentTypeService {

	@Autowired
	private UrgentTypeDao urgentTypeDao;

	// ====================== 基本 C R U D 方法 ===========================
	public UrgentType get(int id) {
		return urgentTypeDao.get(id);
	}

	public void save(UrgentType entity) {
		urgentTypeDao.save(entity);
	}

	public void update(UrgentType entity) {
		urgentTypeDao.update(entity);
	}

	public void delete(int id) {
		urgentTypeDao.delete(id);
	}

	public List<UrgentType> getAll() {
		return urgentTypeDao.getAll();
	}

}
