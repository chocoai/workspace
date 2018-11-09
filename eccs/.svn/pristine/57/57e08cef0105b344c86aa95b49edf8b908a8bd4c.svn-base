package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.smart.model.Step5Logo;
import com.smart.util.Page;
import com.smart.dao.Step5LogoDao;

/**
 * Step5Service. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class Step5LogoService {

	@Autowired
	private Step5LogoDao step5LogoDao;

	// ====================== 基本 C R U D 方法 ===========================
	public Step5Logo get(int id) {
		return step5LogoDao.get(id);
	}

	public void save(Step5Logo entity) {
		step5LogoDao.save(entity);
	}

	public void update(Step5Logo entity) {
		step5LogoDao.update(entity);
	}

	public void delete(int id) {
		step5LogoDao.delete(id);
	}

	public List<Step5Logo> getAll() {
		return step5LogoDao.getAll();
	}

	public Page<Step5Logo> getPage(int pageNo, int pageSize, Integer step5Id) {
		StringBuilder hql = new StringBuilder(
				"from Step5Logo o where o.status = 0 ");
		if (step5Id != null) {
			hql.append(" and o.step5.id = ? ");
		}
		hql.append(" order by o.ctime desc");
		return step5LogoDao.getPage(pageNo, pageSize, hql.toString(), step5Id);
	}

	public List<Step5Logo> getByStep5Id(Integer step5Id) {
		StringBuilder hql = new StringBuilder(
				"from Step5Logo o where o.status = 0 ");
		if (step5Id != null) {
			hql.append(" and o.step5.id = ? ");
		}
		hql.append(" order by o.ctime desc");
		return step5LogoDao.getList(hql.toString(), step5Id);
	}

}
