package com.smart.service;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import com.smart.model.ReviewFileItem;
import com.smart.dao.ReviewFileItemDao;

/**
 * ReviewFileItemService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class ReviewFileItemService {

	@Autowired
	private ReviewFileItemDao reviewFileItemDao;

	// ====================== 基本 C R U D 方法 ===========================
	public ReviewFileItem get(int id) {
		return reviewFileItemDao.get(id);
	}

	public void save(ReviewFileItem entity) {
		reviewFileItemDao.save(entity);
	}

	public void update(ReviewFileItem entity) {
		reviewFileItemDao.update(entity);
	}

	public void delete(int id) {
		reviewFileItemDao.delete(id);
	}

	public List<ReviewFileItem> getAll() {
		return reviewFileItemDao.getAll();
	}

	public List<ReviewFileItem> getByReviewId(Integer reviewId) {
		StringBuilder hql = new StringBuilder(
				"from ReviewFileItem o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (reviewId == null) {
			return new ArrayList<ReviewFileItem>();
		} else {
			hql.append("and o.contractReview.id = ?");
			paramList.add(reviewId);
		}
		hql.append("order by o.id desc");
		return reviewFileItemDao.getList(hql.toString(), paramList);
		// return reviewFileItemDao.getByReviewId(reviewId);
	}

}
