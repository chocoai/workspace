package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.Uptype;
import com.smart.dao.UptypeDao;

/**
 * NoticeService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class UptypeService {

	@Autowired
	private UptypeDao uptypeDao;

	public Uptype check(String name, int userid) {
		String hql = "from Uptype o where o.status = 1 and o.name = ? and o.user.id = ?";
		return uptypeDao.getUnique(hql, name, userid);
	}

	// ====================== 基本 C R U D 方法 ===========================
	public Uptype get(int id) {
		return uptypeDao.get(id);
	}

	public void save(Uptype entity) {
		uptypeDao.save(entity);
	}

	public void update(Uptype entity) {
		uptypeDao.update(entity);
	}

	public void delete(int id) {
		uptypeDao.delete(id);
	}

	public List<Uptype> getAll() {
		return uptypeDao.getAll();
	}

	public List<Uptype> getList(int userid) {
		StringBuilder hql = new StringBuilder(
				"from Uptype o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (userid != 0) {
			hql.append(" and o.user.id=? ");
			paramList.add(userid);
		}
		hql.append(" order by o.id asc");
		return uptypeDao.getList(hql.toString(), paramList);
	}
}