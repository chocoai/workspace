package com.yhcrt.weihu.bbs.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.bbs.dao.BbsGradeDao;
import com.yhcrt.weihu.bbs.entity.BbsGrade;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;
import com.yhcrt.weihu.common.page.Pagination;

@Repository
public class BbsGradeDaoImpl extends HibernateBaseDao<BbsGrade, Integer>
		implements BbsGradeDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public BbsGrade findById(Integer id) {
		BbsGrade entity = get(id);
		return entity;
	}

	public BbsGrade save(BbsGrade bean) {
		getSession().save(bean);
		return bean;
	}

	public BbsGrade deleteById(Integer id) {
		BbsGrade entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<BbsGrade> getEntityClass() {
		return BbsGrade.class;
	}
}