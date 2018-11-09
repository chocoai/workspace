package com.yhcrt.weihu.bbs.dao.impl;

import static com.yhcrt.weihu.bbs.entity.BbsFriendShip.ACCEPT;
import static com.yhcrt.weihu.bbs.entity.BbsFriendShip.APPLYING;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.bbs.dao.BbsFriendShipDao;
import com.yhcrt.weihu.bbs.entity.BbsFriendShip;
import com.yhcrt.weihu.common.hibernate3.Finder;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;
import com.yhcrt.weihu.common.page.Page;
import com.yhcrt.weihu.common.page.Pagination;

@Repository
public class BbsFriendShipDaoImpl extends
		HibernateBaseDao<BbsFriendShip, Integer> implements BbsFriendShipDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<BbsFriendShip> getPage(Integer pageSize, Integer pageNo, Integer userId) {
		Page<BbsFriendShip> page = new Page<BbsFriendShip>(pageNo,pageSize);
		List<BbsFriendShip> list = new ArrayList<BbsFriendShip>();
		if(pageSize == null){
			page.setPageSize(10);
		}
		if(pageNo == null){
			page.setPageNo(0);
		}
		String hql = "from BbsFriendShip f where f.friend.id="+userId+" order by f.status , f.createTime desc";
		String hqlCount = "select count(*) from BbsFriendShip f where f.friend.id="+userId;
		Object obj = getSession().createQuery(hqlCount).uniqueResult();
		if(obj == null){
			page.setList(list);
			page.setTotalCount(0);
			return page;
		}else{
			page.setTotalCount(((Number)obj).intValue());
		}
		list = getSession().createQuery(hql).setFirstResult((page.getPageNo()-1)*page.getPageSize())
				.setMaxResults(page.getPageNo()*page.getPageSize()).setCacheable(false).list();
		page.setList(list);
		return page;
	}
	
	public BbsFriendShip findById(Integer id) {
		BbsFriendShip entity = get(id);
		return entity;
	}

	public BbsFriendShip save(BbsFriendShip bean) {
		getSession().save(bean);
		return bean;
	}

	public BbsFriendShip deleteById(Integer id) {
		BbsFriendShip entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	public BbsFriendShip getFriendShip(Integer userId, Integer friendId) {
		String hql = "from BbsFriendShip bean where bean.user.id=:userId and bean.friend.id=:friendId";
		Finder f = Finder.create(hql).setParam("userId", userId).setParam("friendId", friendId);
		f.setMaxResults(1);
		Query query = f.createQuery(getSession());
		return (BbsFriendShip) query.uniqueResult();
	}
	
	public List<BbsFriendShip> getListByUserId(Integer userId,Integer status) {
		String hql = "from BbsFriendShip bean where bean.user.id=:userId and bean.status=";
		if(status == null){
			hql += ACCEPT;
		}else{
			hql += status;
		}
		Finder f = Finder.create(hql).setParam("userId", userId);
		return find(f);
	}
	
	public Pagination getPageByUserId(Integer userId, Integer pageNo, Integer pageSize) {
		String hql = "from BbsFriendShip bean where bean.user.id=:userId and bean.status="+ACCEPT;
		Finder f = Finder.create(hql).setParam("userId", userId);
		return find(f, pageNo, pageSize);
	}
	
	public Pagination getApplyByUserId(Integer userId, Integer pageNo, Integer pageSize) {
		String hql = "from BbsFriendShip bean where bean.friend.id=:userId and bean.status="+APPLYING;
		Finder f = Finder.create(hql).setParam("userId", userId);
		return find(f, pageNo, pageSize);
	}

	
	public List<BbsFriendShip> getList(Integer userId) {
		String hql = "from BbsFriendShip bean where bean.friend.id=:userId and bean.status="+APPLYING;
		Finder f = Finder.create(hql).setParam("userId", userId);
		return find(f);
	}

	
	
	@Override
	protected Class<BbsFriendShip> getEntityClass() {
		return BbsFriendShip.class;
	}

}