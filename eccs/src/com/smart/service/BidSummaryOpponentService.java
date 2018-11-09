package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.BidSummaryOpponent;
import com.smart.dao.BidSummaryOpponentDao;

/**
 * BidSummaryOpponentService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class BidSummaryOpponentService {

	@Autowired
	private BidSummaryOpponentDao bidSummaryOpponentDao;

	// ====================== 基本 C R U D 方法 ===========================
	public BidSummaryOpponent get(int id) {
		return bidSummaryOpponentDao.get(id);
	}

	public void save(BidSummaryOpponent entity) {
		bidSummaryOpponentDao.save(entity);
	}

	public void update(BidSummaryOpponent entity) {
		bidSummaryOpponentDao.update(entity);
	}

	public void delete(int id) {
		bidSummaryOpponentDao.delete(id);
	}

	public List<BidSummaryOpponent> getAll() {
		return bidSummaryOpponentDao.getAll();
	}

	public List<BidSummaryOpponent> getBySummaryId(Integer summaryid) {
		StringBuilder hql = new StringBuilder(
				"from BidSummaryOpponent o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (summaryid == null) {
			return new ArrayList<BidSummaryOpponent>();
		} else {
			hql.append("and o.bidSummary.id = ?");
			paramList.add(summaryid);
		}
		hql.append("order by o.id desc");
		return bidSummaryOpponentDao.getList(hql.toString(), paramList);
	}

}
