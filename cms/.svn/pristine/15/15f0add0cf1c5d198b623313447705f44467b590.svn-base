package com.yhcrt.weihu.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;
import com.yhcrt.weihu.core.dao.CmsSiteCompanyDao;
import com.yhcrt.weihu.core.entity.CmsSiteCompany;

@Repository
public class CmsSiteCompanyDaoImpl extends
		HibernateBaseDao<CmsSiteCompany, Integer> implements CmsSiteCompanyDao {

	public CmsSiteCompany save(CmsSiteCompany bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	protected Class<CmsSiteCompany> getEntityClass() {
		return CmsSiteCompany.class;
	}
}