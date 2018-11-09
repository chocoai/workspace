package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.Requisition;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.RequisitionDao;

/**
 * RequisitionService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class RequisitionService {

	@Autowired
	private RequisitionDao requisitionDao;

	// ====================== 基本 C R U D 方法 ===========================
	public Requisition get(int id) {
		return requisitionDao.get(id);
	}

	public void save(Requisition entity) {
		requisitionDao.save(entity);
	}

	public void update(Requisition entity) {
		requisitionDao.update(entity);
	}

	public void delete(int id) {
		requisitionDao.delete(id);
	}

	public List<Requisition> getAll() {
		return requisitionDao.getAll();
	}

	public Page<Requisition> getPage(int pageNo, int pageSize, String no,
			String name) {
		StringBuilder hql = new StringBuilder(
				"from Requisition o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(no)) {
			hql.append("and o.project.no like ?");
			paramList.add("%" + no.trim() + "%");
		}
		if (!StringUtil.isBlank(name)) {
			hql.append("and o.project.name like ?");
			paramList.add("%" + name.trim() + "%");
		}
		hql.append("order by o.id desc");
		return requisitionDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
	}

	public Requisition getByProjectId(Integer projectId) {
		StringBuilder hql = new StringBuilder(
				"from Requisition o where o.project.id = ? ");
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(projectId);
		return requisitionDao.getUnique(hql.toString(), paramList);
	}
}
