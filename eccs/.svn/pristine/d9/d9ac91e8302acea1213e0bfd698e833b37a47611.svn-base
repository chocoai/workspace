package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.BidFileItem;
import com.smart.dao.BidFileItemDao;

/**
 * BidFileItemService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class BidFileItemService {

	@Autowired
	private BidFileItemDao bidFileItemDao;

	// ====================== 基本 C R U D 方法 ===========================
	public BidFileItem get(int id) {
		return bidFileItemDao.get(id);
	}

	public void save(BidFileItem entity) {
		bidFileItemDao.save(entity);
	}

	public void update(BidFileItem entity) {
		bidFileItemDao.update(entity);
	}

	public void delete(int id) {
		bidFileItemDao.delete(id);
	}

	public List<BidFileItem> getAll() {
		return bidFileItemDao.getAll();
	}

	public List<BidFileItem> getByBidSummaryId(Integer bidSummaryId) {
		StringBuilder hql = new StringBuilder(
				"from BidFileItem o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (bidSummaryId == null) {
			return new ArrayList<BidFileItem>();
		} else {
			hql.append("and o.bidSummary.id = ?");
			paramList.add(bidSummaryId);
		}
		hql.append("order by o.id desc");
		return bidFileItemDao.getList(hql.toString(), paramList);
		// return reviewFileItemDao.getByReviewId(reviewId);
	}

}
