package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.ContractReview;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.ContractReviewDao;

/**
 * ContractReviewService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class ContractReviewService {

	@Autowired
	private ContractReviewDao contractReviewDao;

	public ContractReview get(int id) {
		return contractReviewDao.get(id);
	}

	public ContractReview getByContractId(int contractId) {
		return contractReviewDao.getByContractId(contractId);
	}

	public void save(ContractReview entity) {
		contractReviewDao.save(entity);
	}

	public void update(ContractReview entity) {
		contractReviewDao.update(entity);
	}

	public void delete(int id) {
		contractReviewDao.delete(id);
	}

	public String getContractLast(){
		return contractReviewDao.getContractLast();
	}
	
	public List<ContractReview> getAll() {
		return contractReviewDao.getAll();
	}

	public ContractReview getOneByPorjectId(int projectId) {
		StringBuilder hql = new StringBuilder();
		hql.append("from ContractReview o where o.status <> 0  ");
		List<Object> paramList = new ArrayList<Object>();
		hql.append(" and o.contract.project.id = ? ");
		paramList.add(projectId);
		return contractReviewDao.getUnique(hql.toString(), paramList);
	}

	/**
	 * 分页查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param name
	 * @param deptId
	 * @return
	 */
	public Page<ContractReview> getPage(int pageNo, int pageSize, String no,
			String name) {
		StringBuilder hql = new StringBuilder();
		hql.append("from ContractReview o where o.status <> 0 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(no)) {
			hql.append(" and o.contract.no like ?");
			paramList.add("%" + no + "%");
		}
		if (!StringUtil.isBlank(name)) {
			hql.append(" and o.contract.name like ?");
			paramList.add("%" + name + "%");
		}
		hql.append("order by o.id desc");
		return contractReviewDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
	}

}
