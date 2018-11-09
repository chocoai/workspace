package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import com.smart.model.T_hrrecruitment;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.T_hrrecruitmentDao;

/**
 * T_hrrecruitmentService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class T_hrrecruitmentService {

	@Autowired
	private T_hrrecruitmentDao t_hrrecruitmentDao;

	// ====================== 基本 C R U D 方法 ===========================
	public T_hrrecruitment get(int id) {
		return t_hrrecruitmentDao.get(id);
	}

	public void save(T_hrrecruitment entity) {
		t_hrrecruitmentDao.save(entity);
	}

	public void update(T_hrrecruitment entity) {
		t_hrrecruitmentDao.update(entity);
	}

	public void delete(int id) {
		t_hrrecruitmentDao.delete(id);
	}

	public List<T_hrrecruitment> getAll() {
		return t_hrrecruitmentDao.getAll();
	}

	public Page<T_hrrecruitment> getPage(int pageNo, int pageSize,
			String hdept_id, String user_id, String rtimec, String rtimej,
			String deptsid, Integer userid) {
		StringBuilder hql = new StringBuilder(
				"from T_hrrecruitment c where c.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(hdept_id)) {
			hql.append("and dept_id in (select id from Dept where name like '%"
					+ hdept_id + "%')");
		}
		if (!StringUtil.isBlank(user_id)) {
			hql.append("and user_id in (select id from User where name like '%"
					+ user_id + "%')");
		}
		if (!StringUtil.isBlank(rtimec)) {
			hql.append(" and c.rdate>='" + rtimec + "'");
		}
		if (!StringUtil.isBlank(rtimej)) {
			hql.append(" and c.rdate<'" + rtimej + "'");
		}
		if (!StringUtil.isBlank(deptsid)) {
			hql.append("and ((c.hdept_id='" + deptsid + "'");
		}
		if (userid != null) {
			hql.append(
					" and  (c.t_hropinion is null or (c.t_hropinion is not null and (select a.user_id from T_hropinion a where id=c.t_hropinion)="
							+ userid + " ))) or user_id=" + userid + ")");
		}
		if (userid != null) {
			// hql.append(" or user_id="+userid);
		}
		hql.append(" order by c.id desc");
		return t_hrrecruitmentDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
	}

	public List<T_hrrecruitment> getList() {
		String hql = "from T_hrrecruitment c where c.status=1";
		return t_hrrecruitmentDao.getList(hql);
	}
}
