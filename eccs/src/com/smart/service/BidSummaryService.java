package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.BidSummary;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.BidSummaryDao;

/**
 * BidSummaryService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class BidSummaryService {

	@Autowired
	private BidSummaryDao bidSummaryDao;

	public BidSummary get(int id) {
		return bidSummaryDao.get(id);
	}

	public void save(BidSummary entity) {
		bidSummaryDao.save(entity);
	}

	public void update(BidSummary entity) {
		bidSummaryDao.update(entity);
	}

	public void delete(int id) {
		bidSummaryDao.delete(id);
	}

	public List<BidSummary> getAll() {
		return bidSummaryDao.getAll();
	}

	public Page<BidSummary> getPage(int pageNo, int pageSize, String no,
			String name, String biddept, String mydeptid) {
		StringBuilder hql = new StringBuilder(
				"from BidSummary o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(no)) {
			hql.append("and o.project.no like ? ");
			paramList.add("%" + no + "%");
		}
		if (!StringUtil.isBlank(name)) {
			hql.append("and o.project.name like ? ");
			paramList.add("%" + name + "%");
		}
		if (!StringUtil.isBlank(biddept)) {
			hql.append("and o.bidDeptName like ? ");
			paramList.add("%" + biddept + "%");
		}
		hql.append("order by o.id desc");
		return bidSummaryDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
	}

}
