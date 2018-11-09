package com.yhcrt.weihu.bbs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.bbs.dao.BbsFriendsDao;
import com.yhcrt.weihu.bbs.entity.BbsFriends;
import com.yhcrt.weihu.common.hibernate3.Finder;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;
import com.yhcrt.weihu.common.page.Page;

@Repository
public class BbsFriendsDaoImpl extends HibernateBaseDao<BbsFriends,Integer> implements BbsFriendsDao {

	@Override
	public void delete(Integer userId, Integer friendId) {
		String hql = "from BbsFriends f where f.user.id=? and f.friend.id=?";
		BbsFriends bean = (BbsFriends)findUnique(hql,userId,friendId);
		if(bean != null){
			getSession().delete(bean);
		}
	}

	@Override
	public boolean isFriend(Integer userId, Integer friendId) {
		String hql = "select count(*) from BbsFriends f where f.user.id="+userId+" and f.friend.id="+friendId;
		Object obj = getSession().createQuery(hql).uniqueResult();
		if(obj == null){
			return false;
		}else{
			if(((Number)obj).intValue()==0){
				return false;
			}else{
				return true;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BbsFriends> getMyFriends(Integer pageSize, Integer pageNo, Integer userId) {
		Page<BbsFriends> page = new Page<BbsFriends>(pageNo,pageSize);
		List<BbsFriends> list = new ArrayList<BbsFriends>();
		if(pageSize == null){
			page.setPageSize(20);
		}
		if(pageNo == null){
			page.setPageNo(0);
		}
		String hql = "from BbsFriends f where f.user.id="+userId;
		String hqlCount = "select count(*) from BbsFriends f where f.user.id="+userId;
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

	@Override
	public void save(BbsFriends bbsFriends) {
		getSession().save(bbsFriends);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BbsFriends> getMyFriends(Integer userId) {
		String hql = "from BbsFriends f where f.user.id=:userId";
		Finder f = Finder.create(hql).setParam("userId", userId);
		return find(f);
	}

	@Override
	protected Class<BbsFriends> getEntityClass() {
		return BbsFriends.class;
	}

}
