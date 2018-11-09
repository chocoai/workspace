package com.yhcrt.weihu.cms.dao.assist.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.cms.dao.assist.CmsTTypeDao;
import com.yhcrt.weihu.cms.entity.assist.CmsTType;
import com.yhcrt.weihu.common.hibernate3.Finder;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;

@Repository
public class CmsTTypeDaoImpl extends
		HibernateBaseDao<CmsTType, Integer> implements
		CmsTTypeDao {
	@SuppressWarnings("unchecked")
	public List<CmsTType> getList(Integer siteId) {
		Finder f = Finder.create("from CmsTType bean where 1=1");
		if (siteId != null) {
			f.append(" and bean.site.id=:siteId");
			f.setParam("siteId", siteId);
		}
		f.append(" order by bean.id asc");
		return find(f);
	}


	public CmsTType findById(Integer id) {
		CmsTType entity = get(id);
		return entity;
	}

	public CmsTType save(CmsTType bean) {
		getSession().save(bean);
		return bean;
	}

	public CmsTType deleteById(Integer id) {
		CmsTType entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}


	@Override
	protected Class<CmsTType> getEntityClass() {

		return CmsTType.class;
	}


}