package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.NoticeItem;
import com.smart.dao.NoticeItemDao;

/**
 * NoticeItemService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class NoticeItemService {

	@Autowired
	private NoticeItemDao noticeItemDao;

	// ====================== 基本 C R U D 方法 ===========================
	public NoticeItem get(int id) {
		return noticeItemDao.get(id);
	}

	public void save(NoticeItem entity) {
		noticeItemDao.save(entity);
	}

	public void update(NoticeItem entity) {
		noticeItemDao.update(entity);
	}

	public void delete(int id) {
		noticeItemDao.delete(id);
	}

	public List<NoticeItem> getAll() {
		return noticeItemDao.getAll();
	}

	public List<NoticeItem> getList(String comid) {
		StringBuilder hql = new StringBuilder(
				"from NoticeItem o where o.status = 1 and o.comid=" + comid);
		hql.append(" order by o.id desc");
		List<Object> paramList = new ArrayList<Object>();
		return noticeItemDao.getList(hql.toString(), paramList);
	}

}
