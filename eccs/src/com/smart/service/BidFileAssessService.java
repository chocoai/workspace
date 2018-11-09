package com.smart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.BidFileAssessDao;
import com.smart.model.BidFileAssess;
import com.smart.model.ProjectInfo;
import com.smart.util.Page;
import com.smart.util.StringUtil;

/**
 * BidFileAssessService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class BidFileAssessService {
	
	
	@Autowired
	private BidFileAssessDao bidFileAssessDao;
	
	
	
	
	
	//====================== 基本 C R U D 方法  ===========================
	public BidFileAssess get(Integer id) {
		return bidFileAssessDao.get(id);
	}
	
	public void save(BidFileAssess entity) {
		bidFileAssessDao.save(entity);
	}
	
	public void update(BidFileAssess entity) {
		bidFileAssessDao.update(entity);
	}
	
	public void delete(Integer id) {
		bidFileAssessDao.delete(id);
	}
	
	public List<BidFileAssess> getAll() {
		return bidFileAssessDao.getAll();
	}

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param cusName
	 * @param proname
	 * @param agentcompany 
	 * @return
	 */
	public Page<BidFileAssess> getPage(int pageNo, int pageSize, String cusName,
			String proname, String agentcompany) {
		StringBuilder hql = new StringBuilder();
		hql.append("from BidFileAssess o where o.status <> 0 ");
		List<Object> paramList = new ArrayList<Object>();
		if (null != cusName) {
			hql.append("and o.customer.cusName like ? ");
			paramList.add("%" + cusName + "%");
		}
		if (!StringUtil.isBlank(proname)) {
			hql.append("and o.projectInfo.proname like ? ");
			paramList.add("%" + proname + "%");
		}
		if (!StringUtil.isBlank(agentcompany)) {
			hql.append("and o.projectInfo.agentcompany like ? ");
			paramList.add("%" + agentcompany + "%");
		}
		hql.append("order by o.id desc");
		return bidFileAssessDao.getPage(pageNo, pageSize, hql.toString(), paramList);
	}
	
	/**
	 * @param projectInfo
	 * @return
	 */
	public BidFileAssess getFileAssessByProjectInfo(ProjectInfo projectInfo) {
		String hql = "from BidFileAssess o where o.status <> 0 and o.projectInfo like ? ";
		return bidFileAssessDao.getUnique(hql, projectInfo);
	}
	
}

