package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import com.smart.model.BidMember;
import com.smart.dao.BidMemberDao;

/**
 * BidMemberService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class BidMemberService {

	@Autowired
	private BidMemberDao bidMemberDao;

	public BidMember get(int id) {
		return bidMemberDao.get(id);
	}

	public void save(BidMember entity) {
		bidMemberDao.save(entity);
	}

	public void update(BidMember entity) {
		bidMemberDao.update(entity);
	}

	public void delete(int id) {
		bidMemberDao.delete(id);
	}

	public List<BidMember> getAll() {
		return bidMemberDao.getAll();
	}

	public List<BidMember> getByPlanId(Integer planid) {
		StringBuilder hql = new StringBuilder(
				"from BidMember o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (planid == null) {
			return new ArrayList<BidMember>();
		} else {
			hql.append("and o.bidPlan.id = ?");
			paramList.add(planid);
		}
		hql.append("order by o.id desc");
		return bidMemberDao.getList(hql.toString(), paramList);
	}

}
