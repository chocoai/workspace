package com.yhcrt.weihu.bbs.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.bbs.dao.BbsWebserviceDao;
import com.yhcrt.weihu.bbs.entity.BbsWebservice;
import com.yhcrt.weihu.common.hibernate3.Finder;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;
import com.yhcrt.weihu.common.page.Pagination;

@Repository
public class BbsWebserviceDaoImpl extends HibernateBaseDao<BbsWebservice, Integer> implements BbsWebserviceDao {
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}
	
	public List<BbsWebservice> getList(String type){
		String hql="from BbsWebservice bean where bean.type=:type";
		Finder f =Finder.create(hql).setParam("type", type);
		f.setCacheable(true);
		return find(f);
	}

	public BbsWebservice findById(Integer id) {
		BbsWebservice entity = get(id);
		return entity;
	}

	public BbsWebservice save(BbsWebservice bean) {
		getSession().save(bean);
		return bean;
	}

	public BbsWebservice deleteById(Integer id) {
		BbsWebservice entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@Override
	protected Class<BbsWebservice> getEntityClass() {
		return BbsWebservice.class;
	}
}