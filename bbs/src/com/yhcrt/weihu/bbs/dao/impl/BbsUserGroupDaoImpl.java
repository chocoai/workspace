﻿package com.yhcrt.weihu.bbs.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.bbs.dao.BbsUserGroupDao;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.entity.BbsUserGroup;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;
import com.yhcrt.weihu.common.page.Pagination;

@Repository
public class BbsUserGroupDaoImpl extends
		HibernateBaseDao<BbsUserGroup, Integer> implements BbsUserGroupDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BbsUserGroup> getListOfPersonal() {
		String hql = "select bean from BbsUserGroup bean where bean.type=1 order by bean.type, bean.point";
		return getSession().createQuery(hql).setCacheable(false).list();
	}
	
	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	@SuppressWarnings("unchecked")
	public List<BbsUserGroup> getList(Integer siteId) {
		String hql = "select bean from BbsUserGroup bean where bean.site.id=? order by bean.type, bean.point";
		return find(hql, siteId);
	}

	public BbsUserGroup getRegDef() {
		String hql = "select bean from BbsUserGroup bean where bean.default=true";
		return (BbsUserGroup) findUnique(hql);
	}

	public BbsUserGroup findById(Integer id) {
		BbsUserGroup entity = get(id);
		return entity;
	}

	public BbsUserGroup save(BbsUserGroup bean) {
		getSession().save(bean);
		return bean;
	}

	public BbsUserGroup deleteById(Integer id) {
		BbsUserGroup entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<BbsUserGroup> getEntityClass() {
		return BbsUserGroup.class;
	}

	/* (non-Javadoc)
	 * @see com.yhcrt.weihu.bbs.dao.BbsUserGroupDao#findNearByPoint()
	 */
	public BbsUserGroup findNearByPoint(Long point) {
		// TODO Auto-generated method stub
		String hql = "from BbsUserGroup bean where bean.point <= :point order by bean.point desc ";
		List<BbsUserGroup> groups = getSession().createQuery(hql).setLong("point", point).setCacheable(false).list();
		BbsUserGroup group = null;
		if(groups.get(0).getPoint()==0){
			String hql1 = "from BbsUserGroup bean where bean.default = 1";
			group = (BbsUserGroup)getSession().createQuery(hql1).uniqueResult();
		}else{
			group = groups.get(0);
		}
		
		return group;
	}

}