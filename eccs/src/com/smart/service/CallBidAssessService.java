package com.smart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.CallBidAssessDao;
import com.smart.model.CallBidAssess;
import com.smart.model.ProjectInfo;
import com.smart.util.Page;
import com.smart.util.StringUtil;

/**
 * CallBidAssessService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class CallBidAssessService {
	
	
	@Autowired
	private CallBidAssessDao callBidAssessDao;
	
	
	
	
	
	//====================== 基本 C R U D 方法  ===========================
	public CallBidAssess get(Integer id) {
		return callBidAssessDao.get(id);
	}
	
	public void save(CallBidAssess entity) {
		callBidAssessDao.save(entity);
	}
	
	public void update(CallBidAssess entity) {
		callBidAssessDao.update(entity);
	}
	
	public void delete(Integer id) {
		callBidAssessDao.delete(id);
	}
	
	public List<CallBidAssess> getAll() {
		return callBidAssessDao.getAll();
	}

	/**
	 * @param pageNo
	 * @param pageSize
	 * @param string
	 * @param proname
	 * @param agentcompany 
	 * @return
	 */
	public Page<CallBidAssess> getPage(int pageNo, int pageSize, String cusName,
			String proname, String agentcompany) {
		StringBuilder hql = new StringBuilder();
		hql.append("from CallBidAssess o where o.status <> 0 ");
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
		return callBidAssessDao.getPage(pageNo, pageSize, hql.toString(), paramList);
	}

	/**
	 * @param projectInfo
	 * @return
	 */
	public CallBidAssess getCallAssessByProjectInfo(ProjectInfo projectInfo) {
		String hql = "from CallBidAssess o where o.status <> 0 and o.projectInfo like ? ";
		return callBidAssessDao.getUnique(hql, projectInfo);
	}
	
}

