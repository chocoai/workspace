package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.smart.model.RequisitionType;
import com.smart.dao.RequisitionTypeDao;

/**
 * RequisitionTypeService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class RequisitionTypeService {

	@Autowired
	private RequisitionTypeDao requisitionTypeDao;

	// ====================== 基本 C R U D 方法 ===========================
	public RequisitionType get(int id) {
		return requisitionTypeDao.get(id);
	}

	public void save(RequisitionType entity) {
		requisitionTypeDao.save(entity);
	}

	public void update(RequisitionType entity) {
		requisitionTypeDao.update(entity);
	}

	public void delete(int id) {
		requisitionTypeDao.delete(id);
	}

	public List<RequisitionType> getAll() {
		return requisitionTypeDao.getAll();
	}

}
