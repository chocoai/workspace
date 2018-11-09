package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.Contract;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.ContractDao;

/**
 * ContractService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class ContractService {

	@Autowired
	private ContractDao contractDao;

	public Contract get(int id) {
		return contractDao.get(id);
	}

	public void save(Contract entity) {
		contractDao.save(entity);
	}

	public void update(Contract entity) {
		contractDao.update(entity);
	}

	public void delete(int id) {
		contractDao.delete(id);
	}

	public List<Contract> getAll() {
		return contractDao.getAll();
	}

	/**
	 * 分页查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param name
	 * @param deptId
	 * @return
	 */
	public Page<Contract> getPage(int pageNo, int pageSize, String no,
			String name) {
		StringBuilder hql = new StringBuilder();
		hql.append("from Contract o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(no)) {
			hql.append("and o.no like ?");
			paramList.add("%" + no + "%");
		}
		if (!StringUtil.isBlank(name)) {
			hql.append("and o.name like ?");
			paramList.add("%" + name + "%");
		}
		hql.append("order by o.id desc");
		return contractDao.getPage(pageNo, pageSize, hql.toString(), paramList);
	}

	public List<Contract> getList(Integer id) {
		String hql = "from Contract where project_id=" + id + "";
		return contractDao.getList(hql);
	}

	public Contract getOneByPorjectId(int projectId) {
		StringBuilder hql = new StringBuilder();
		hql.append("from Contract o where o.status <> 0  ");
		List<Object> paramList = new ArrayList<Object>();
		hql.append(" and o.project.id = ? ");
		paramList.add(projectId);
		return contractDao.getUnique(hql.toString(), paramList);
	}

}
