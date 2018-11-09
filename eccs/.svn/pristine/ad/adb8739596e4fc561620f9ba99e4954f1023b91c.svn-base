package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import com.smart.model.ContractReviewItem;
import com.smart.dao.ContractReviewItemDao;

/**
 * ContractReviewItemService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class ContractReviewItemService {

	@Autowired
	private ContractReviewItemDao contractReviewItemDao;

	// ====================== 基本 C R U D 方法 ===========================
	public ContractReviewItem get(int id) {
		return contractReviewItemDao.get(id);
	}

	public void save(ContractReviewItem entity) {
		contractReviewItemDao.save(entity);
	}

	public void update(ContractReviewItem entity) {
		contractReviewItemDao.update(entity);
	}

	public void delete(int id) {
		contractReviewItemDao.delete(id);
	}

	public List<ContractReviewItem> getAll() {
		return contractReviewItemDao.getAll();
	}

	public void deleteByReviewId(Integer reviewId) {
		StringBuilder hql = new StringBuilder(
				"delete from ContractReviewItem o where o.contractReview.id = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(reviewId);
		contractReviewItemDao.delete(hql.toString(), paramList);
	}

	public List<ContractReviewItem> getByReviewId(Integer reviewId) {
		StringBuilder hql = new StringBuilder(
				"from ContractReviewItem o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (reviewId == null) {
			return new ArrayList<ContractReviewItem>();
		} else {
			hql.append("and o.contractReview.id = ?");
			paramList.add(reviewId);
		}
		hql.append("order by o.id asc");
		return contractReviewItemDao.getList(hql.toString(), paramList);
		// return reviewFileItemDao.getByReviewId(reviewId);
	}

}
