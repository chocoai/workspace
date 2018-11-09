package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.BidSpeed;
import com.smart.dao.BidSpeedDao;

/**
 * BidSpeedService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class BidSpeedService {

	@Autowired
	private BidSpeedDao bidSpeedDao;

	// ====================== 基本 C R U D 方法 ===========================
	public BidSpeed get(int id) {
		return bidSpeedDao.get(id);
	}

	public void save(BidSpeed entity) {
		bidSpeedDao.save(entity);
	}

	public void update(BidSpeed entity) {
		bidSpeedDao.update(entity);
	}

	public void delete(int id) {
		bidSpeedDao.delete(id);
	}

	public List<BidSpeed> getAll() {
		return bidSpeedDao.getAll();
	}

	public List<BidSpeed> getByPlanId(Integer planid) {
		StringBuilder hql = new StringBuilder(
				"from BidSpeed o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (planid == null) {
			return new ArrayList<BidSpeed>();
		} else {
			hql.append("and o.bidPlan.id = ?");
			paramList.add(planid);
		}
		hql.append("order by o.id desc");
		return bidSpeedDao.getList(hql.toString(), paramList);
		// return reviewFileItemDao.getByReviewId(reviewId);
	}

}
