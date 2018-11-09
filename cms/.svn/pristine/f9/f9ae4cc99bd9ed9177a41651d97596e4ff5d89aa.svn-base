package com.yhcrt.weihu.cms.dao.assist.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.cms.dao.assist.CmsChangeDao;
import com.yhcrt.weihu.cms.entity.assist.CmsChange;
import com.yhcrt.weihu.common.hibernate3.Finder;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;
import com.yhcrt.weihu.common.page.Pagination;

@Repository
public class CmsChangeDaoImpl extends HibernateBaseDao<CmsChange, Integer>
		implements CmsChangeDao {
	public Pagination getPage(Integer userId, Integer contentId,
			Integer siteId, boolean cacheable, int pageNo, int pageSize) {
		Finder f = Finder.create("from CmsChange bean where 1=1 ");
		if (userId != null) {
			f.append(" and bean.user.id=:userId").setParam("userId", userId);
		}
		if (contentId != null) {
			f.append(" and bean.content.id=:contentId").setParam("contentId",
					contentId);
		}
		if (siteId != null) {
			f.append(" and bean.content.site.id=:siteId").setParam("siteId",
					siteId);
		}
		f.append(" order by bean.changeTime asc");
		f.setCacheable(cacheable);
		Pagination page = find(f, pageNo, pageSize);
		return page;
	}

	@SuppressWarnings("unchecked")
	public List<CmsChange> getList(Integer siteId, Integer userId, Integer contentId, boolean cacheable) {
		Finder f = Finder.create("from CmsChange bean where 1=1 ");
		if (siteId != null) {
			f.append(" and bean.content.site.id=:siteId").setParam("siteId",
					siteId);
		}
		if (userId != null) {
			f.append(" and bean.user.id=:userId").setParam("userId", userId);
		}
		if (contentId != null) {
			f.append(" and bean.content.id=:contentId").setParam("contentId",
					contentId);
		}
		f.append(" order by bean.changeTime asc");
		f.setCacheable(cacheable);
		return find(f);
	}
	public CmsChange findById(Integer id) {
		CmsChange entity = get(id);
		return entity;
	}

	public CmsChange save(CmsChange bean) {
		getSession().save(bean);
		return bean;
	}

	public CmsChange deleteById(Integer id) {
		CmsChange entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<CmsChange> getEntityClass() {
		return CmsChange.class;
	}

}