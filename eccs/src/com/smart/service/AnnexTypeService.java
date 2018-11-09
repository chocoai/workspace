package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import com.smart.model.AnnexType;
import com.smart.util.StringUtil;
import com.smart.dao.AnnexTypeDao;

/**
 * AnnexTypeService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class AnnexTypeService {

	@Autowired
	private AnnexTypeDao annexTypeDao;

	// ====================== 基本 C R U D 方法 ===========================
	public AnnexType get(int id) {
		return annexTypeDao.get(id);
	}

	public void save(AnnexType entity) {
		annexTypeDao.save(entity);
	}

	public void update(AnnexType entity) {
		annexTypeDao.update(entity);
	}

	public void delete(int id) {
		annexTypeDao.delete(id);
	}

	public List<AnnexType> getAll() {
		return annexTypeDao.getAll();
	}

	public List<AnnexType> getByStep(String step, String forcombo) {
		StringBuilder hql = new StringBuilder(
				"from AnnexType o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(step)) {
			hql.append("and o.stepName = ?");
			paramList.add(step + "");
		}

		if (!StringUtil.isBlank(forcombo)) {
			hql.append("and o.forcombo = ?");
			paramList.add(forcombo);
		}
		hql.append("order by o.sort asc");
		return annexTypeDao.getList(hql.toString(), paramList);
	}

	public List<AnnexType> getByStep1(String step, String forcombo) {
		StringBuilder hql = new StringBuilder(
				"from AnnexType o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(step)) {
			hql.append("and o.stepName = ?");
			paramList.add(step + "");
		}

		if (!StringUtil.isBlank(forcombo)) {
			hql.append("and o.forcombo = ?");
			paramList.add(forcombo);
		}
		hql.append("order by o.id asc");
		return annexTypeDao.getList(hql.toString(), paramList);
	}
}
