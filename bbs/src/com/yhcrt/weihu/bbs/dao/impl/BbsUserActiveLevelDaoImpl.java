package com.yhcrt.weihu.bbs.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.bbs.dao.BbsUserActiveLevelDao;
import com.yhcrt.weihu.bbs.entity.BbsUserActiveLevel;
import com.yhcrt.weihu.common.hibernate3.Finder;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;
import com.yhcrt.weihu.common.page.Pagination;

@Repository
public class BbsUserActiveLevelDaoImpl extends HibernateBaseDao<BbsUserActiveLevel, Integer> implements BbsUserActiveLevelDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}
	
	public List<BbsUserActiveLevel> getList(Integer count){
		Finder f=Finder.create("from BbsUserActiveLevel bean order by bean.requiredHour asc");
		if(count==null){
			count=1000;
		}
		f.setMaxResults(count);
		f.setCacheable(true);
		return find(f);
	}

	public BbsUserActiveLevel findById(Integer id) {
		BbsUserActiveLevel entity = get(id);
		return entity;
	}

	public BbsUserActiveLevel save(BbsUserActiveLevel bean) {
		getSession().save(bean);
		return bean;
	}

	public BbsUserActiveLevel deleteById(Integer id) {
		BbsUserActiveLevel entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<BbsUserActiveLevel> getEntityClass() {
		return BbsUserActiveLevel.class;
	}
}