package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import com.smart.model.Notice;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.NoticeDao;

/**
 * NoticeService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class NoticeService {

	@Autowired
	private NoticeDao noticeDao;

	// ====================== 基本 C R U D 方法 ===========================
	public Notice get(int id) {
		return noticeDao.get(id);
	}

	public void save(Notice entity) {
		noticeDao.save(entity);
	}

	public void update(Notice entity) {
		noticeDao.update(entity);
	}

	public void delete(int id) {
		noticeDao.delete(id);
	}

	public List<Notice> getAll() {
		return noticeDao.getAll();
	}

	public Page<Notice> getPage(int pageNo, int pageSize, int noticeItemid,
			String title, String times, String timee, String comid) {
		StringBuilder hql = new StringBuilder(
				"from Notice o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(title)) {
			hql.append("and o.title like ? ");
			paramList.add("%" + title + "%");
		}
		if (!StringUtil.isBlank(times)) {
			hql.append(" and DATE_FORMAT(o.ctime, '%Y-%m-%d' ) >= ?");
			paramList.add(times);
		}
		if (!StringUtil.isBlank(timee)) {
			hql.append(" and DATE_FORMAT(o.ctime, '%Y-%m-%d' ) <= ?");
			paramList.add(timee);
		}
		if (!comid.equals(001)) {
			hql.append(" and o.comid = ? ");
			paramList.add(comid);
		}
		if (noticeItemid != 0) {
			hql.append(" and o.noticeItem.id= ? ");
			paramList.add(noticeItemid);
		}
		hql.append(" order by o.id desc");
		return noticeDao.getPage(pageNo, pageSize, hql.toString(), paramList);
	}

}
