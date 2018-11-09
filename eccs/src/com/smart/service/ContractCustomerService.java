package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import com.smart.model.ContractCustomer;
import com.smart.dao.ContractCustomerDao;

/**
 * ContractCustomerService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class ContractCustomerService {

	@Autowired
	private ContractCustomerDao contractCustomerDao;

	// ====================== 基本 C R U D 方法 ===========================
	public ContractCustomer get(int id) {
		return contractCustomerDao.get(id);
	}

	public void save(ContractCustomer entity) {
		contractCustomerDao.save(entity);
	}

	public void update(ContractCustomer entity) {
		contractCustomerDao.update(entity);
	}

	public void delete(int id) {
		contractCustomerDao.delete(id);
	}

	public List<ContractCustomer> getAll() {
		return contractCustomerDao.getAll();
	}

	public List<ContractCustomer> getByContractId(Integer contractid) {
		StringBuilder hql = new StringBuilder(
				"from ContractCustomer o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (contractid == null) {
			return new ArrayList<ContractCustomer>();
		} else {
			hql.append("and o.contract.id = ?");
			paramList.add(contractid);
		}
		hql.append("order by o.id desc");
		return contractCustomerDao.getList(hql.toString(), paramList);
		// return reviewFileItemDao.getByReviewId(reviewId);
	}

}
