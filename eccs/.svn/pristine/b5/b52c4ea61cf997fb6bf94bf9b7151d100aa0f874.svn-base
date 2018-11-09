package com.smart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.BidApplyAssessDao;
import com.smart.model.BidApplyAssess;
import com.smart.model.ProjectInfo;
import com.smart.util.Page;
import com.smart.util.StringUtil;

/**
 * BidApplyAssessService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class BidApplyAssessService {

	@Autowired
	private BidApplyAssessDao bidApplyAssessDao;

	// ====================== 基本 C R U D 方法 ===========================
	public BidApplyAssess get(Integer id) {
		return bidApplyAssessDao.get(id);
	}

	public void save(BidApplyAssess entity) {
		bidApplyAssessDao.save(entity);
	}

	public void update(BidApplyAssess entity) {
		bidApplyAssessDao.update(entity);
	}

	public void delete(Integer id) {
		bidApplyAssessDao.delete(id);
	}

	public List<BidApplyAssess> getAll() {
		return bidApplyAssessDao.getAll();
	}

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param cusName
	 * @param name
	 * @param agentCompany
	 * @return
	 */
	public Page<BidApplyAssess> getPage(int pageNo, int pageSize,
			String cusName, String proname, String agentCompany) {
		StringBuilder hql = new StringBuilder();
		hql.append("from BidApplyAssess o where o.status <> 0 ");
		List<Object> paramList = new ArrayList<Object>();
		if (null != cusName) {
			hql.append("and o.projectInfo.bidmen.cusName like ? ");
			paramList.add("%" + cusName + "%");
		}
		if (!StringUtil.isBlank(proname)) {
			hql.append("and o.projectInfo.proname like ? ");
			paramList.add("%" + proname + "%");
		}
		if (!StringUtil.isBlank(agentCompany)) {
			hql.append("and o.agentCompany like ? ");
			paramList.add("%" + agentCompany + "%");
		}
		hql.append("order by o.id desc");
		return bidApplyAssessDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
	}

	/**
	 * @param projectInfo
	 * @return
	 */
	public BidApplyAssess getApplyAssessByProjectInfo(ProjectInfo projectInfo) {
		String hql = "from BidApplyAssess o where o.status <> 0 and o.projectInfo like ? ";
		return bidApplyAssessDao.getUnique(hql, projectInfo);
	}

}
