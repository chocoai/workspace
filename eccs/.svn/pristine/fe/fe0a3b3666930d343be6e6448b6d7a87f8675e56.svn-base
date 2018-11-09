package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.T_hremployee;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.T_hremployeeDao;

/**
 * T_hremployeeService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class T_hremployeeService {

	@Autowired
	private T_hremployeeDao t_hremployeeDao;

	/**
	 * 系统账号查询
	 * 
	 * @param username
	 * @return
	 */
	public T_hremployee getSysCode(Integer userId) {
		String hql = "from T_hremployee t where t.status = 1 and t.sys_account.id = ?";
		return t_hremployeeDao.getUnique(hql, userId);
	}

	// ====================== 基本 C R U D 方法 ===========================
	public T_hremployee get(int id) {
		return t_hremployeeDao.get(id);
	}

	public void save(T_hremployee entity) {
		t_hremployeeDao.save(entity);
	}

	public void update(T_hremployee entity) {
		t_hremployeeDao.update(entity);
	}

	public void delete(int id) {
		t_hremployeeDao.delete(id);
	}

	public List<T_hremployee> getAll() {
		return t_hremployeeDao.getAll();
	}

	public Page<T_hremployee> getPage(int pageNo, int pageSize, String name,
			String college, String phone, Integer sex) {
		StringBuilder hql = new StringBuilder(
				"from T_hremployee c where c.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(name)) {
			hql.append("and c.name like ?");
			paramList.add("%" + name + "%");
		}
		if (!StringUtil.isBlank(college)) {
			hql.append(
					"and id in (select t_hremployee from T_hreducation where college like '%"
							+ college + "%')");
		}
		if (!StringUtil.isBlank(phone)) {
			hql.append("and c.phone like ?");
			paramList.add("%" + phone + "%");
		}
		if (sex != null) {
			hql.append("and c.sex=" + sex + "");
		}
		hql.append(" order by c.id desc");
		return t_hremployeeDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
	}

	public List<T_hremployee> getList() {
		String hql = "from T_hremployee c where c.status = 1";
		return t_hremployeeDao.getList(hql);
	}
}
