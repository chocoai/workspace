package com.yhcrt.weihu.bbs.dao.impl;

import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.bbs.dao.BbsMagicConfigDao;
import com.yhcrt.weihu.bbs.entity.BbsMagicConfig;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;

@Repository
public class BbsMagicConfigDaoImpl extends
		HibernateBaseDao<BbsMagicConfig, Integer> implements BbsMagicConfigDao {

	public BbsMagicConfig findById(Integer id) {
		BbsMagicConfig entity = get(id);
		return entity;
	}

	public BbsMagicConfig save(BbsMagicConfig bean) {
		getSession().save(bean);
		return bean;
	}

	public BbsMagicConfig deleteById(Integer id) {
		BbsMagicConfig entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<BbsMagicConfig> getEntityClass() {
		return BbsMagicConfig.class;
	}
}