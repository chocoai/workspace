package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import com.smart.model.T_hrtrain;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.T_hrtrainDao;

/**
 * T_hrtrainService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class T_hrtrainService {

	@Autowired
	private T_hrtrainDao t_hrtrainDao;

	// ====================== 基本 C R U D 方法 ===========================
	public T_hrtrain get(int id) {
		return t_hrtrainDao.get(id);
	}

	public void save(T_hrtrain entity) {
		t_hrtrainDao.save(entity);
	}

	public void update(T_hrtrain entity) {
		t_hrtrainDao.update(entity);
	}

	public void delete(int id) {
		t_hrtrainDao.delete(id);
	}

	public List<T_hrtrain> getAll() {
		return t_hrtrainDao.getAll();
	}

	public Page<T_hrtrain> getPage(int pageNo, int pageSize, String theme,
			String teach, String rtimec, String rtimej) {
		StringBuilder hql = new StringBuilder(
				"from T_hrtrain c where c.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(theme)) {
			hql.append("and c.theme like ?");
			paramList.add("%" + theme + "%");
		}
		if (!StringUtil.isBlank(teach)) {
			hql.append("and c.teach like ?");
			paramList.add("%" + teach + "%");
		}
		if (!StringUtil.isBlank(rtimec)) {
			hql.append(" and c.tdate>='" + rtimec + "'");
		}
		if (!StringUtil.isBlank(rtimej)) {
			hql.append(" and c.tdate<'" + rtimej + "'");
		}
		hql.append(" order by c.id desc");
		return t_hrtrainDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
	}
}
