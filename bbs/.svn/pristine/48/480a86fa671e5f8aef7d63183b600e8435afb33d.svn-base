package com.yhcrt.weihu.bbs.dao.impl;

import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.bbs.dao.BbsUserExtDao;
import com.yhcrt.weihu.bbs.entity.BbsUserExt;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;

@Repository
public class BbsUserExtDaoImpl extends HibernateBaseDao<BbsUserExt, Integer> implements BbsUserExtDao {
	public BbsUserExt update(BbsUserExt ext){
		getSession().update(ext);
		return ext;
	}
	
	public BbsUserExt findById(Integer id) {
		BbsUserExt entity = get(id);
		return entity;
	}

	public BbsUserExt save(BbsUserExt bean) {
		getSession().save(bean);
		return bean;
	}
	
	@Override
	protected Class<BbsUserExt> getEntityClass() {
		return BbsUserExt.class;
	}
}