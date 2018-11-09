package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.BidPlan;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.BidPlanDao;

/**
 * BidPlanService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class BidPlanService {

	@Autowired
	private BidPlanDao bidPlanDao;

	public BidPlan get(int id) {
		return bidPlanDao.get(id);
	}

	public void save(BidPlan entity) {
		bidPlanDao.save(entity);
	}

	public void update(BidPlan entity) {
		bidPlanDao.update(entity);
	}

	public void delete(int id) {
		bidPlanDao.delete(id);
	}

	public List<BidPlan> getAll() {
		return bidPlanDao.getAll();
	}

	public Page<BidPlan> getPage(int pageNo, int pageSize, String no,
			String name, String bidDeptName) {
		StringBuilder hql = new StringBuilder();
		hql.append("from BidPlan o where o.status <> 0 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(no)) {
			hql.append("and o.project.no like ? ");
			paramList.add("%" + no + "%");
		}
		if (!StringUtil.isBlank(name)) {
			hql.append("and o.project.name like ? ");
			paramList.add("%" + name + "%");
		}
		if (!StringUtil.isBlank(bidDeptName)) {
			hql.append("and o.bidDeptName like ? ");
			paramList.add("%" + bidDeptName + "%");
		}
		hql.append("order by o.id desc");
		return bidPlanDao.getPage(pageNo, pageSize, hql.toString(), paramList);
	}

	public BidPlan getBidPlanByProjectId(int projectId) {
		StringBuilder hql = new StringBuilder();
		hql.append("from BidPlan o where 1=1 ");
		List<Object> paramList = new ArrayList<Object>();
		hql.append(" and o.project.id = ? ");
		paramList.add(projectId);
		return bidPlanDao.getUnique(hql.toString(), paramList);
	}
}
