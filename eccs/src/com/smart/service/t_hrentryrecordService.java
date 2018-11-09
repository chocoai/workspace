package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import com.smart.model.t_hrentryrecord;
import com.smart.dao.t_hrentryrecordDao;

/**
 * t_hrentryrecordService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class t_hrentryrecordService {

	@Autowired
	private t_hrentryrecordDao t_hrentryrecordDao;

	// ====================== 基本 C R U D 方法 ===========================
	public t_hrentryrecord get(int id) {
		return t_hrentryrecordDao.get(id);
	}

	public void save(t_hrentryrecord entity) {
		t_hrentryrecordDao.save(entity);
	}

	public void update(t_hrentryrecord entity) {
		t_hrentryrecordDao.update(entity);
	}

	public void delete(int id) {
		t_hrentryrecordDao.delete(id);
	}

	public List<t_hrentryrecord> getAll() {
		return t_hrentryrecordDao.getAll();
	}

	public List<t_hrentryrecord> getList(Integer id) {
		String hql = "from t_hrentryrecord where t_hremployee=" + id + "";
		return t_hrentryrecordDao.getList(hql);
	}
}
