package com.smart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.BidInfoDao;
import com.smart.model.BidInfo;
import com.smart.model.ProjectInfo;
import com.smart.util.Page;
import com.smart.util.StringUtil;

/**
 * BidInfoService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class BidInfoService {
	
	
	@Autowired
	private BidInfoDao bidInfoDao;
	
	
	
	
	
	//====================== 基本 C R U D 方法  ===========================
	public BidInfo get(Integer id) {
		return bidInfoDao.get(id);
	}
	
	public void save(BidInfo entity) {
		bidInfoDao.save(entity);
	}
	
	public void update(BidInfo entity) {
		bidInfoDao.update(entity);
	}
	
	public void delete(Integer id) {
		bidInfoDao.delete(id);
	}
	
	public List<BidInfo> getAll() {
		return bidInfoDao.getAll();
	}

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param cusName
	 * @param proname
	 * @param agentcompany 
	 * @return
	 */
	public Page<BidInfo> getPage(int pageNo, int pageSize, String cusName,
			String proname, String agentcompany) {
		StringBuilder hql = new StringBuilder();
		hql.append("from BidInfo o where o.status <> 0 ");
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
		return bidInfoDao.getPage(pageNo, pageSize, hql.toString(), paramList);
	}
	
	/**
	 * @param projectInfo
	 * @return
	 */
	public BidInfo getInfoByProjectInfo(ProjectInfo projectInfo) {
		String hql = "from BidInfo o where o.status <> 0 and o.projectInfo like ? ";
		return bidInfoDao.getUnique(hql, projectInfo);
	}
	
}

