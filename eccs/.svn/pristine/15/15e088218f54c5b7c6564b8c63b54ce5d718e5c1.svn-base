package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.Doc;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.DocDao;

/**
 * NoticeService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class DocService {

	@Autowired
	private DocDao docDao;

	public List<Doc> find(int id) {
		String hql = "from Doc o where o.status = 1 and o.uptype.id=?";
		return docDao.getList(hql, id);
	}

	public Doc check(String name, int userid, int uid) {
		String hql = "from Doc o where o.status = 1 and o.name = ? and o.user.id = ? and o.uptype.id=? ";
		return docDao.getUnique(hql, name, userid, uid);
	}

	// ====================== 基本 C R U D 方法 ===========================
	public Doc get(int id) {
		return docDao.get(id);
	}

	public void save(Doc entity) {
		docDao.save(entity);
	}

	public void update(Doc entity) {
		docDao.update(entity);
	}

	public void delete(int id) {
		docDao.delete(id);
	}

	public List<Doc> getAll() {
		return docDao.getAll();
	}

	public Page<Doc> getPage(int pageNo, int pageSize, String mix, int uptypeid,
			int userid) {// int
		// uptypeid,String
		// times,String
		// timee,
		StringBuilder hql = new StringBuilder("from Doc o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();// Uptype uptype,
		if (!StringUtil.isBlank(mix)) {
			hql.append("and (o.name like ? ");
			paramList.add("%" + mix + "%");
		}
		if (!StringUtil.isBlank(mix)) {
			hql.append("or o.remark like ? )");
			paramList.add("%" + mix + "%");
		}
		if (uptypeid != 0) {
			hql.append(" and o.uptype.id = ? ");
			paramList.add(uptypeid);
		}
		if (userid != 0) {
			hql.append(" and o.user.id=? ");
			paramList.add(userid);
		}
		hql.append(" order by o.id desc");
		return docDao.getPage(pageNo, pageSize, hql.toString(), paramList);
	}
}
