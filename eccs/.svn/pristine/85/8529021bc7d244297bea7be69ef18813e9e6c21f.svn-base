package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.smart.model.ContractPay;
import com.smart.dao.ContractPayDao;

/**
 * ContractPayService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class ContractPayService {

	@Autowired
	private ContractPayDao contractPayDao;

	// ====================== 基本 C R U D 方法 ===========================
	public ContractPay get(int id) {
		return contractPayDao.get(id);
	}

	public void save(ContractPay entity) {
		contractPayDao.save(entity);
	}

	public void update(ContractPay entity) {
		contractPayDao.update(entity);
	}

	public void delete(int id) {
		contractPayDao.delete(id);
	}

	public List<ContractPay> getAll() {
		return contractPayDao.getAll();
	}

}
