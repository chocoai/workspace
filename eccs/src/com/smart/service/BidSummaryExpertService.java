package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.BidSummaryExpert;
import com.smart.dao.BidSummaryExpertDao;

/**
 * BidSummaryExpertService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class BidSummaryExpertService {

	@Autowired
	private BidSummaryExpertDao bidSummaryExpertDao;

	// ====================== 基本 C R U D 方法 ===========================
	public BidSummaryExpert get(int id) {
		return bidSummaryExpertDao.get(id);
	}

	public void save(BidSummaryExpert entity) {
		bidSummaryExpertDao.save(entity);
	}

	public void update(BidSummaryExpert entity) {
		bidSummaryExpertDao.update(entity);
	}

	public void delete(int id) {
		bidSummaryExpertDao.delete(id);
	}

	public List<BidSummaryExpert> getAll() {
		return bidSummaryExpertDao.getAll();
	}

	public List<BidSummaryExpert> getBySummaryId(Integer summaryid) {
		StringBuilder hql = new StringBuilder(
				"from BidSummaryExpert o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (summaryid == null) {
			return new ArrayList<BidSummaryExpert>();
		} else {
			hql.append("and o.bidSummary.id = ?");
			paramList.add(summaryid);
		}
		hql.append("order by o.id desc");
		return bidSummaryExpertDao.getList(hql.toString(), paramList);
	}

}
