package com.yhcrt.weihu.bbs.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.bbs.dao.BbsUserDao;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.entity.BbsUserActiveLevel;
import com.yhcrt.weihu.common.hibernate3.Finder;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Page;
import com.yhcrt.weihu.common.page.Pagination;

@Repository
public class BbsUserDaoImpl extends HibernateBaseDao<BbsUser, Integer>
		implements BbsUserDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BbsUser> getHostUser(Integer count) {
		String hql = "from BbsUser u order by u.point desc,u.primeCount desc,u.topicCount desc,u.replyCount desc,u.registerTime desc";
		List<BbsUser> list = getSession().createQuery(hql.toString()).setFirstResult(0).setMaxResults(count).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BbsUser> searchUser(Integer pageSize, Integer pageNo, String keywords) {
		Page<BbsUser> page = new Page<BbsUser>(pageNo,pageSize);
		List<BbsUser> list = new ArrayList<BbsUser>();
		if(pageSize == null){
			page.setPageSize(10);
		}
		if(pageNo == null){
			page.setPageNo(0);
		}
		StringBuffer hql = new StringBuffer("from BbsUser u ");
		StringBuffer hqlCount = new StringBuffer("select count(*) from BbsUser u ");
		if(!StringUtils.isBlank(keywords)){
			hql.append(" where u.username like '%"+keywords+"%'");
			hqlCount.append("where u.username like '%"+keywords+"%'");
		}
		hql.append(" order by u.point desc,u.topicCount desc ");
		
		Object obj = getSession().createQuery(hqlCount.toString()).uniqueResult();
		if(obj == null){
			page.setList(list);
			page.setTotalCount(0);
			return page;
		}else{
			page.setTotalCount(((Number)obj).intValue());
		}
		list = getSession().createQuery(hql.toString()).setFirstResult((page.getPageNo()-1)*page.getPageSize())
				.setMaxResults(page.getPageNo()*page.getPageSize()).setCacheable(false).list();
		page.setList(list);
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public List<BbsUser> getList(String username, Integer count) {
		Finder f = Finder.create("select bean from BbsUser bean where bean.username like :username");
		f.setParam("username", "%" + username + "%");
		Query query = f.createQuery(getSession());
		query.setMaxResults(count);
		return query.list();
	}
	
	public Pagination getPage(String username, String email, Integer groupId,
			Boolean disabled, Boolean admin, Boolean official, Integer lastLoginDay,Integer orderBy, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from BbsUser bean");
		if (orderBy != null && !orderBy.equals(0)) {
			f.append(" left join bean.userOnline online ");
		}
		f.append(" where 1=1");
		if (!StringUtils.isBlank(username)) {
			f.append(" and bean.username like :username");
			f.setParam("username", "%" + username + "%");
		}
		if (!StringUtils.isBlank(email)) {
			f.append(" and bean.email like :email");
			f.setParam("email", "%" + email + "%");
		}
		if (groupId != null) {
			f.append(" and bean.group.id=:groupId");
			f.setParam("groupId", groupId);
		}
		if (disabled != null) {
			f.append(" and bean.disabled=:disabled");
			f.setParam("disabled", disabled);
		}
		if (admin != null) {
			f.append(" and bean.admin=:admin");
			f.setParam("admin", admin);
		}
		if(official!=null){
			f.append(" and bean.official=:official").setParam("official", official);
		}
		if(lastLoginDay!=null&&lastLoginDay!=0){
			Calendar calendar=Calendar.getInstance();
			calendar.add(Calendar.DATE, -lastLoginDay);
			f.append(" and bean.lastLoginTime>:lastLoginTime").setParam("lastLoginTime", calendar.getTime());
		}
		if (orderBy != null && !orderBy.equals(0)) {
			if (orderBy.equals(1)) {
				/**
				 * 今日活跃度降序
				 */
				f.append(" order by online.onlineDay desc");
			} else if (orderBy.equals(2)) {
				/**
				 * 今日活跃度升序
				 */
				f.append(" order by online.onlineDay asc");
			} else if (orderBy.equals(3)) {
				/**
				 * 本周活跃度降序
				 */
				f.append(" order by online.onlineWeek desc");
			} else if (orderBy.equals(4)) {
				/**
				 * 本周活跃度升序
				 */
				f.append(" order by online.onlineWeek asc");
			} else if (orderBy.equals(5)) {
				/**
				 * 本月活跃度降序
				 */
				f.append(" order by online.onlineMonth desc");
			} else if (orderBy.equals(6)) {
				/**
				 * 本月活跃度升序
				 */
				f.append(" order by online.onlineMonth asc");
			} else if (orderBy.equals(7)) {
				/**
				 * 本年活跃度降序
				 */
				f.append(" order by online.onlineYear desc");
			} else if (orderBy.equals(8)) {
				/**
				 * 本年活跃度升序
				 */
				f.append(" order by online.onlineYear asc");
			} else if (orderBy.equals(9)) {
				/**
				 * 积分降序
				 */
				f.append(" order by bean.point desc");
			} else if (orderBy.equals(10)) {
				/**
				 * 积分升序
				 */
				f.append(" order by bean.point asc");
			} else if (orderBy.equals(11)) {
				/**
				 * 威望降序
				 */
				f.append(" order by bean.prestige desc");
			} else if (orderBy.equals(12)) {
				/**
				 * 威望升序
				 */
				f.append(" order by bean.prestige asc");
			}
		} else {
			/**
			 * 没有指定排序方式
			 */
			f.append(" order by bean.id desc");
		}
		return find(f, pageNo, pageSize);
	}
	
	public List<BbsUser> getList(Integer count) {
		Finder f = Finder.create("select bean from BbsUser bean ");
		Query query = f.createQuery(getSession());
		if(count==null){
			count=5000;
		}
		query.setMaxResults(count);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<BbsUser> getSuggestMember(String username, Integer count) {
		Finder f = Finder.create("select bean from BbsUser bean where bean.username like :username and bean.admin=false");
		f.setParam("username", username+"%");
		Query query = f.createQuery(getSession());
		query.setMaxResults(count);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<BbsUser> getAdminList(Integer siteId, Boolean allChannel,
			Boolean disabled, Integer rank) {
		Finder f = Finder.create("select bean from BbsUser");
		f.append(" bean join bean.userSites us");
		f.append(" where us.site.id=:siteId");
		f.setParam("siteId", siteId);
		if (allChannel != null) {
			f.append(" and us.allChannel=:allChannel");
			f.setParam("allChannel", allChannel);
		}
		if (disabled != null) {
			f.append(" and bean.disabled=:disabled");
			f.setParam("disabled", disabled);
		}
		if (rank != null) {
			f.append(" and bean.rank<=:rank");
			f.setParam("rank", rank);
		}
		f.append(" and bean.admin=true");
		f.append(" order by bean.id asc");
		return find(f);
	}

	public BbsUser findById(Integer id) {
		BbsUser entity = get(id);
		return entity;
	}

	public BbsUser findByUsername(String username) {
		return findUniqueByProperty("username", username);
	}
	
	public int countByUsername(String username) {
		String hql = "select count(*) from BbsUser bean where bean.username=:username";
		Query query = getSession().createQuery(hql);
		query.setParameter("username", username);
		return ((Number) query.iterate().next()).intValue();
	}

	public int countByEmail(String email) {
		String hql = "select count(*) from BbsUser bean where bean.email=:email";
		Query query = getSession().createQuery(hql);
		query.setParameter("email", email);
		return ((Number) query.iterate().next()).intValue();
	}

	public BbsUser save(BbsUser bean) {
		getSession().save(bean);
		return bean;
	}
	
	public BbsUser updateActiveLevel(BbsUser bean,BbsUserActiveLevel level){
		Updater<BbsUser> updater = new Updater<BbsUser>(bean);
		bean.setActiveLevel(level);
		bean=updateByUpdater(updater);
		return bean;
	}

	public BbsUser deleteById(Integer id) {
		BbsUser entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<BbsUser> getEntityClass() {
		return BbsUser.class;
	}

}