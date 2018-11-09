package com.smart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.BidBondAssessDao;
import com.smart.model.BidBondAssess;
import com.smart.model.ProjectInfo;
import com.smart.util.Page;
import com.smart.util.StringUtil;

/**
 * BidBondAssessService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class BidBondAssessService {
	
	
	@Autowired
	private BidBondAssessDao bidBondAssessDao;
	
	
	
	
	
	//====================== 基本 C R U D 方法  ===========================
	public BidBondAssess get(Integer id) {
		return bidBondAssessDao.get(id);
	}
	
	public void save(BidBondAssess entity) {
		bidBondAssessDao.save(entity);
	}
	
	public void update(BidBondAssess entity) {
		bidBondAssessDao.update(entity);
	}
	
	public void delete(Integer id) {
		bidBondAssessDao.delete(id);
	}
	
	public List<BidBondAssess> getAll() {
		return bidBondAssessDao.getAll();
	}

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param cusName
	 * @param proname
	 * @param agentcompany 
	 * @return
	 */
	public Page<BidBondAssess> getPage(int pageNo, int pageSize, String cusName,
			String proname, String agentcompany) {
		StringBuilder hql = new StringBuilder();
		hql.append("from BidBondAssess o where o.status <> 0 ");
		List<Object> paramList = new ArrayList<Object>();
		if (null != cusName) {
			hql.append("and o.projectInfo.bidmen.cusName like ? ");
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
		return bidBondAssessDao.getPage(pageNo, pageSize, hql.toString(), paramList);
	}

	/**
	 * @return
	 */
	public String getBondLast() {
		return bidBondAssessDao.getBondLast();
	}
	
	/**
	 * @param projectInfo
	 * @return
	 */
	public BidBondAssess getBondAssessByProjectInfo(ProjectInfo projectInfo) {
		String hql = "from BidBondAssess o where o.status <> 0 and o.projectInfo like ? ";
		return bidBondAssessDao.getUnique(hql, projectInfo);
	}
	
}

