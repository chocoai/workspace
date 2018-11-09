package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.smart.model.T_liregistration;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.T_liregistrationDao;

/**
 * T_liregistrationService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class T_liregistrationService {

	@Autowired
	private T_liregistrationDao t_liregistrationDao;

	// ====================== 基本 C R U D 方法 ===========================
	public T_liregistration get(int id) {
		return t_liregistrationDao.get(id);
	}

	public void save(T_liregistration entity) {
		t_liregistrationDao.save(entity);
	}

	public void update(T_liregistration entity) {
		t_liregistrationDao.update(entity);
	}

	public void delete(int id) {
		t_liregistrationDao.delete(id);
	}

	public List<T_liregistration> getAll() {
		return t_liregistrationDao.getAll();
	}

	public Page<T_liregistration> getPage(int pageNo, int pageSize,
			String licname, String licnumber, Integer licstatus) {
		StringBuilder hql = new StringBuilder(
				"from T_liregistration c where c.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(licname)) {
			hql.append("and c.licname like ?");
			paramList.add("%" + licname + "%");
		}
		if (licstatus != null) {
			hql.append("and c.licstatus = ?");
			paramList.add(licstatus);
		}
		if (!StringUtil.isBlank(licnumber)) {
			hql.append("and c.licnumber like ?");
			paramList.add("%" + licnumber + "%");
		}
		hql.append("order by c.id desc");
		return t_liregistrationDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
	}

	// 查借据id相同的证照
	public List<T_liregistration> getLists(Integer lire) {
		String hql = "from T_liregistration c where c.status = 1 and c.liborrow="
				+ lire + "";
		return t_liregistrationDao.getList(hql);
	}

	// 查询闲置的证照
	public List<T_liregistration> getList() {
		String hql = "from T_liregistration c where c.status = 1 and c.licstatus=1";
		return t_liregistrationDao.getList(hql);
	}

	// 年检提醒查询(当前日期>(年检日期-30天))
	public Page<T_liregistration> getPages(int pageNo, int pageSize,
			String licname, String licnumber, Integer licstatus) {
		Date date = new Date();
		Calendar ca = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ca.add(Calendar.DATE, +30);
		date = ca.getTime();
		StringBuilder hql = new StringBuilder(
				"from T_liregistration c where c.status = 1  and c.anndate<'"
						+ sdf.format(date) + "'");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(licname)) {
			hql.append("and c.licname like ?");
			paramList.add("%" + licname + "%");
		}
		if (licstatus != null) {
			hql.append("and c.licstatus = ?");
			paramList.add(licstatus);
		}
		if (!StringUtil.isBlank(licnumber)) {
			hql.append("and c.licnumber like ?");
			paramList.add("%" + licnumber + "%");
		}
		hql.append("order by c.id desc");
		return t_liregistrationDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
	}

	// 查借据id相同的证照
	public List<T_liregistration> getAlls() {
		String hql = "from T_liregistration c where c.status = 1";
		return t_liregistrationDao.getList(hql);
	}
}
