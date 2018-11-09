package com.yhcrt.weihu.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;
import com.yhcrt.weihu.core.dao.CmsConfigDao;
import com.yhcrt.weihu.core.entity.CmsConfig;

@Repository
public class CmsConfigDaoImpl extends HibernateBaseDao<CmsConfig, Integer>
		implements CmsConfigDao {
	public CmsConfig findById(Integer id) {
		CmsConfig entity = get(id);
		return entity;
	}

	protected Class<CmsConfig> getEntityClass() {
		return CmsConfig.class;
	}
	@SuppressWarnings("unchecked")
	public List<CmsConfig> getList() {
		String hql = "from CmsConfig";
		return find(hql);
	}
}