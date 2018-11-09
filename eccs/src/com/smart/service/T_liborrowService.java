package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.T_liborrow;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.T_liborrowDao;

/**
 * T_liborrowService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class T_liborrowService {

	@Autowired
	private T_liborrowDao t_liborrowDao;

	// ====================== 基本 C R U D 方法 ===========================
	public T_liborrow get(int id) {
		return t_liborrowDao.get(id);
	}

	public void save(T_liborrow entity) {
		t_liborrowDao.save(entity);
	}

	public void update(T_liborrow entity) {
		t_liborrowDao.update(entity);
	}

	public void delete(int id) {
		t_liborrowDao.delete(id);
	}

	public List<T_liborrow> getAll() {
		return t_liborrowDao.getAll();
	}

	public Page<T_liborrow> getPage(int pageNo, int pageSize, String documents,
			String rtimec, String rtimej, String uname, String deptsid,
			Integer userid) {
		StringBuilder hql = new StringBuilder(
				"from T_liborrow c where c.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(documents)) {
			hql.append("and c.documents like ?");
			paramList.add("%" + documents + "%");
		}
		if (!StringUtil.isBlank(rtimec)) {
			hql.append(" and c.rtime>='" + rtimec + "'");
		}
		if (!StringUtil.isBlank(rtimej)) {
			hql.append(" and c.rtime<'" + rtimej + "'");
		}
		if (!StringUtil.isBlank(uname)) {
			hql.append("and user in (select id from User where name like '%"
					+ uname + "%')");
		}
		if (!StringUtil.isBlank(deptsid)) {
			hql.append("and ((c.sdeptid='" + deptsid + "'");
		}
		if (userid != null) {
			hql.append(
					" and  (c.handle is null or (c.handle is not null and (select a.user from T_handle a where id=c.handle)="
							+ userid + " ))) or c.user=" + userid + ")");
		}
		if (userid != null) {
			// hql.append(" or c.user="+userid);
		}
		hql.append(" order by c.id desc");
		return t_liborrowDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
	}

	public List<T_liborrow> getLists(Integer id) {
		String hql = "from T_liborrow c where c.status=1 and c.handle=" + id
				+ "";
		return t_liborrowDao.getList(hql);
	}
}
