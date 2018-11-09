package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.T_Customer;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.T_CustomerDao;

/**
 * T_CustomerService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class T_CustomerService {

	@Autowired
	private T_CustomerDao t_CustomerDao;

	// ====================== 基本 C R U D 方法 ===========================
	public T_Customer get(int id) {
		return t_CustomerDao.get(id);
	}

	public void save(T_Customer entity) {
		t_CustomerDao.save(entity);
	}

	public void update(T_Customer entity) {
		t_CustomerDao.update(entity);
	}

	public void delete(int id) {
		t_CustomerDao.delete(id);
	}

	public List<T_Customer> getAll() {
		return t_CustomerDao.getAll();
	}

	public Page<T_Customer> getAll1(int pageNo, int pageSize, String cName) {
		StringBuilder hql = new StringBuilder(
				"from T_Customer c where c.status = 1");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(cName)) {
			hql.append("and c.cusName= ?");
			paramList.add(cName);
		}
		hql.append("order by c.id desc");
		return t_CustomerDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
	}

	// 条件查询
	public Page<T_Customer> getPage(int pageNo, int pageSize, String cName,
			Integer cNature, String email, Integer cType) {
		StringBuilder hql = new StringBuilder(
				"from T_Customer c where c.status = 1");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(cName)) {
			hql.append("and c.cusName like ?");
			paramList.add("%" + cName + "%");
		}
		if (cNature != null) {
			hql.append("and c.cusNature = ?");
			paramList.add(cNature);
		}
		if (!StringUtil.isBlank(email)) {
			hql.append("and c.email like ?");
			paramList.add("%" + email + "%");
		}
		if (cType != null) {
			hql.append("and c.cusType = ?");
			paramList.add(cType);
		}
		hql.append("order by c.id desc");
		return t_CustomerDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
	}

	// 查询所有
	public List<T_Customer> getList() {
		String hql = "from T_Customer c where c.status = 1";
		return t_CustomerDao.getList(hql);
	}

	// 查询不分页
	public List<T_Customer> getList1(String cName, Integer cNature,
			String email, Integer cType) {
		StringBuilder hql = new StringBuilder(
				"from T_Customer c where c.status = 1");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(cName)) {
			hql.append("and c.cusName like ?");
			paramList.add("%" + cName + "%");
		}
		if (cNature != null) {
			hql.append("and c.cusNature = ?");
			paramList.add(cNature);
		}
		if (!StringUtil.isBlank(email)) {
			hql.append("and c.email like ?");
			paramList.add("%" + email + "%");
		}
		if (cType != null) {
			hql.append("and c.cusType = ?");
			paramList.add(cType);
		}
		hql.append("order by c.id desc");
		return t_CustomerDao.getList(hql.toString(), paramList);
	}
}