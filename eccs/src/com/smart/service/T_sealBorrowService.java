package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.T_sealBorrow;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.T_sealBorrowDao;

/**
 * T_sealBorrowService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class T_sealBorrowService {

	@Autowired
	private T_sealBorrowDao t_sealBorrowDao;

	public List<T_sealBorrow> getSeals() {
		StringBuilder hql = new StringBuilder(
				"from T_sealBorrow c where c.status = 1 and (c.state = 2 or "
						+ "(c.state = 0 and c.option_id is null)) "
						+ "order by c.type desc");
		return t_sealBorrowDao.getList(hql.toString());
	}

	// ====================== 基本 C R U D 方法 ===========================
	public T_sealBorrow get(int id) {
		return t_sealBorrowDao.get(id);
	}

	public void save(T_sealBorrow entity) {
		t_sealBorrowDao.save(entity);
	}

	public void update(T_sealBorrow entity) {
		t_sealBorrowDao.update(entity);
	}

	public void delete(int id) {
		t_sealBorrowDao.delete(id);
	}

	public List<T_sealBorrow> getAll() {
		return t_sealBorrowDao.getAll();
	}

	public Page<T_sealBorrow> getPage(int pageNo, int pageSize, String uproject,
			String ubranch, String uname, String rtimec, String rtimej,
			Integer type, String deptsid, Integer userid) {
		StringBuilder hql = new StringBuilder(
				"from T_sealBorrow c where c.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(rtimec)) {
			hql.append(" and c.rtime>='" + rtimec + "'");
		}
		if (!StringUtil.isBlank(rtimej)) {
			hql.append(" and c.rtime<'" + rtimej + "'");
		}
		if (!StringUtil.isBlank(uname)) {
			hql.append("and user_id in (select id from User where name like '%"
					+ uname + "%')");
		}
		if (!StringUtil.isBlank(ubranch)) {
			hql.append("and dept_id in (select id from Dept where name like '%"
					+ ubranch + "%')");
		}
		if (!StringUtil.isBlank(uproject)) {
			hql.append(
					"and project_id in (select id from Project where name like '%"
							+ uproject + "%')");
		}
		if (type != null) {
			hql.append("and c.type=" + type + "");
		}
		if (!StringUtil.isBlank(deptsid)) {
			hql.append("and ((c.sealdept_id='" + deptsid + "'");
		}
		if (userid != null) {
			hql.append(
					" and  (c.option_id is null or (c.option_id is not null and (select a.user_id from T_sealOption a where id=c.option_id)="
							+ userid + " ))) or user_id= " + userid + ")");
		}
		if (userid != null) {
			// hql.append(" or user_id= "+userid);
		}
		hql.append(" order by c.id desc");
		return t_sealBorrowDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
	}
}
