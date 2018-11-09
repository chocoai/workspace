package com.yhcrt.weihu.cms.dao.assist.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.cms.dao.assist.CmsBaoLiaoDao;
import com.yhcrt.weihu.cms.entity.assist.CmsBaoLiao;
import com.yhcrt.weihu.common.hibernate3.Finder;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;
import com.yhcrt.weihu.common.page.Pagination;

@Repository
public class CmsBaoLiaoDaoImpl extends HibernateBaseDao<CmsBaoLiao, Integer>
		implements CmsBaoLiaoDao {
	public Pagination getPage(Integer siteId,Integer userId,Integer state,String title,Boolean checked, 
			Boolean recommend,boolean desc,boolean cacheable, int pageNo, int pageSize) {
		Finder f = createFinder(siteId, userId, state,title,recommend,checked,desc, cacheable);
		return find(f, pageNo, pageSize);
	}
    @Override
	public Pagination getPageByTitle(Integer siteId, String title, int pageNo, int pageSize) {
    	Finder f = Finder.create("from CmsBaoLiao bl where 1=1");
		if (siteId != null) {
			f.append(" and bl.site.id=:siteId");
			f.setParam("siteId",siteId);
		}
		
		if (!StringUtils.isBlank(title)) {
			f.append(" and bl.title like :title");
			f.setParam("title", "%" + title + "%");
		}
		f.append(" order by bl.createTime desc");
		
		return find(f, pageNo, pageSize);
	}
	@SuppressWarnings("unchecked")
	public List<CmsBaoLiao> getList(Integer siteId, Integer userId, Integer state,String title, Boolean checked, Boolean recommend,
			boolean desc,boolean cacheable,int first, int max) {
		Finder f = createFinder(siteId, userId, state,title,recommend,checked,desc,cacheable);
		f.setFirstResult(first);
		f.setMaxResults(max);
		return find(f);
	}
	private Finder createFinder(Integer siteId, Integer userId,Integer state,String title,
			Boolean recommend, Boolean checked,boolean desc, boolean cacheable) {
		Finder f = Finder.create("from CmsBaoLiao bl where 1=1");
		if (siteId != null) {
			f.append(" and bl.site.id=:siteId");
			f.setParam("siteId",siteId);
		}
		if (userId != null) {
			f.append(" and bl.user.id=:userId");
			f.setParam("userId", userId);
		}
		
		if (state != null) {
			f.append(" and bl.state=:state");
			f.setParam("state",state);
		}
		if (recommend != null) {
			f.append(" and bl.recommend=:recommend");
			f.setParam("recommend", recommend);
		}
		if (checked != null) {
			f.append(" and bl.checked=:checked");
			f.setParam("checked", checked);
		}
		if (!StringUtils.isBlank(title)) {
			f.append(" and bl.title like :title");
			f.setParam("title", "%" + title + "%");
		}
		if (desc) {
			f.append(" order by bl.createTime desc");
		} else {
			f.append(" order by bl.createTime asc");
		}
		if (desc) {
			f.append(" order by b1.commtCount desc,b1.readCount desc,bl.createTime desc");
		} else {
			f.append(" order by b1.createTime desc");
		}
		f.setCacheable(cacheable);
		return f;
	}
	public CmsBaoLiao findById(Integer id) {
		CmsBaoLiao entity = get(id);
		return entity;
	}

	public CmsBaoLiao save(CmsBaoLiao bean) {
		getSession().save(bean);
		return bean;
	}

	public CmsBaoLiao deleteById(Integer id) {
		CmsBaoLiao entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<CmsBaoLiao> getEntityClass() {
		return CmsBaoLiao.class;
	}

	

}