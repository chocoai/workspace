package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.smart.model.T_hrrecruitmentitem;
import com.smart.dao.T_hrrecruitmentitemDao;

/**
 * T_hrrecruitmentitemService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class T_hrrecruitmentitemService {

	@Autowired
	private T_hrrecruitmentitemDao t_hrrecruitmentitemDao;

	// ====================== 基本 C R U D 方法 ===========================
	public T_hrrecruitmentitem get(int id) {
		return t_hrrecruitmentitemDao.get(id);
	}

	public void save(T_hrrecruitmentitem entity) {
		t_hrrecruitmentitemDao.save(entity);
	}

	public void update(T_hrrecruitmentitem entity) {
		t_hrrecruitmentitemDao.update(entity);
	}

	public void delete(int id) {
		t_hrrecruitmentitemDao.delete(id);
	}

	public List<T_hrrecruitmentitem> getAll() {
		return t_hrrecruitmentitemDao.getAll();
	}

	public List<T_hrrecruitmentitem> getList(Integer id) {
		String hql = "from T_hrrecruitmentitem c where c.status = 1 and c.t_hrrecruitment="
				+ id + "";
		return t_hrrecruitmentitemDao.getList(hql);
	}
}
