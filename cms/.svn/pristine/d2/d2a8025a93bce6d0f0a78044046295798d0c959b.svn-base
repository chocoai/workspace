package com.yhcrt.weihu.cms.dao.main.impl;

import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.cms.dao.main.ContentDocDao;
import com.yhcrt.weihu.cms.entity.main.ContentDoc;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;

@Repository
public class ContentDocDaoImpl extends HibernateBaseDao<ContentDoc, Integer>
		implements ContentDocDao {
	public ContentDoc findById(Integer id) {
		ContentDoc entity = get(id);
		return entity;
	}

	public ContentDoc save(ContentDoc bean) {
		getSession().save(bean);
		return bean;
	}

	@Override
	protected Class<ContentDoc> getEntityClass() {
		return ContentDoc.class;
	}
}