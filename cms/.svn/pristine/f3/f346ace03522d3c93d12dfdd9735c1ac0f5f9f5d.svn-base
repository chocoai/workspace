package com.yhcrt.weihu.cms.dao.assist.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.cms.dao.assist.CmsTTDao;
import com.yhcrt.weihu.cms.entity.assist.CmsTT;
import com.yhcrt.weihu.common.hibernate3.Finder;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;
import com.yhcrt.weihu.common.page.Pagination;

@Repository
public class CmsTTDaoImpl extends HibernateBaseDao<CmsTT, Integer> implements CmsTTDao {

	public Pagination getPage(Integer siteId, Integer typeId,Integer isAdmin,Integer demand,Integer userId,Boolean recommend,
			Boolean checked, boolean asc, boolean cacheable, int pageNo,
			int pageSize) {
		Finder f = createFinder(siteId, typeId, null,isAdmin,demand,userId,recommend, checked, asc,
				cacheable);
		return find(f, pageNo, pageSize);
	}
	
	public Pagination getAllPage(Integer siteId, Integer typeIds[],Integer isAdmin,Integer demand,Integer userId,Boolean recommend,
			Boolean checked, boolean asc, boolean cacheable, int pageNo,
			int pageSize) {
		Finder f = createFinder(siteId, null, typeIds,isAdmin,demand, userId, recommend, checked, asc, cacheable);
		return find(f, pageNo, pageSize);
	}

	@SuppressWarnings("unchecked")
	public List<CmsTT> getList(Integer siteId, Integer typeId,Integer isAdmin,Integer demand,
			Boolean recommend, Boolean checked, boolean desc,
			boolean cacheable, int first, int max) {
		Finder f = createFinder(siteId, typeId, null,isAdmin,demand,null,recommend, checked, desc,
				cacheable);
		f.setFirstResult(first);
		f.setMaxResults(max);
		return find(f);
	}

	private Finder createFinder(Integer siteId, Integer typeId,Integer typeIds[],Integer isAdmin,Integer demand,Integer userId,
			Boolean recommend, Boolean checked, boolean desc, boolean cacheable) {
		Finder f = Finder.create("from CmsTT bean where 1=1");
		if (siteId != null) {
			f.append(" and bean.site.id=:siteId");
			f.setParam("siteId", siteId);
		}
		if (demand != null) {
			f.append(" and bean.demand=:demand");
			f.setParam("demand", demand);
		}
		if (typeId != null) {
			f.append(" and bean.type.id =:typeId");
			f.setParam("typeId", typeId);
		}
		if(typeIds!=null&&typeIds.length>0){
			f.append(" and bean.type.id in(:typeIds)");
			f.setParamList("typeIds", typeIds);
		}
		if (isAdmin != null) {
			f.append(" and bean.isAdmin=:isAdmin");
			f.setParam("isAdmin", isAdmin);
		}
		if (userId != null) {
			f.append(" and bean.user.id=:userId");
			f.setParam("userId", userId);
		}
		if (recommend != null) {
			f.append(" and bean.recommend=:recommend");
			f.setParam("recommend", recommend);
		}
		if (checked != null) {
			f.append(" and bean.checked=:checked");
			f.setParam("checked", checked);
		}
		if (desc) {
			f.append(" order by bean.createTime desc");
		} else {
			f.append(" order by bean.createTime asc");
		}
		f.setCacheable(cacheable);
		return f;
	}
	
	public CmsTT findById(Integer id) {
		CmsTT entity = get(id);
		return entity;
	}

	public CmsTT save(CmsTT bean) {
		getSession().save(bean);
		return bean;
	}

	public CmsTT deleteById(Integer id) {
		CmsTT entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<CmsTT> getEntityClass() {
		return CmsTT.class;
	}

	
}