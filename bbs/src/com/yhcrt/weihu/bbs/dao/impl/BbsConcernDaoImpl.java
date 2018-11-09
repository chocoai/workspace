package com.yhcrt.weihu.bbs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.bbs.dao.BbsConcernDao;
import com.yhcrt.weihu.bbs.entity.BbsConcern;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;
import com.yhcrt.weihu.common.page.Page;

@Repository
public class BbsConcernDaoImpl  extends HibernateBaseDao<BbsConcern,Integer> 
	implements BbsConcernDao{

	@Override
	public void deleteById(Integer id) {
		BbsConcern bean = super.get(id);
		if(bean != null){
			getSession().delete(bean);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<BbsConcern> getPage(Integer userId,Integer pageSize,Integer pageNo) {
		Page<BbsConcern> page = new Page<BbsConcern>(pageNo,pageSize);
		List<BbsConcern> list = new ArrayList<BbsConcern>();
		if(pageSize == null){
			page.setPageSize(10);
		}
		if(pageNo == null){
			page.setPageNo(0);
		}
		String hql = "from BbsConcern c where c.creater.id="+userId;
		String sqlCount = "select count(*) from bbs_concern c where c.CREATER_ID="+userId;
		Object obj = getSession().createSQLQuery(sqlCount).uniqueResult();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<BbsConcern> getListByUser(Integer userId) {
		String hql = "from BbsConcern c where c.creater.id="+userId;
		return getSession().createQuery(hql).list();
	}

	@Override
	public int deleteByUser(Integer userId, Integer concernUserId) {
		String hql = "delete from BbsConcern c where c.creater.id="+userId+" and c.concernUser.id="+concernUserId;
		return getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public Boolean isConcernByUser(Integer userId, Integer concernUserId) {
		String sql = "select count(*) from bbs_concern c where c.CREATER_ID="+userId
					+" and c.CONCERN_USER_ID="+concernUserId;
		Object obj = getSession().createSQLQuery(sql).uniqueResult();
		if(obj == null){
			return null;
		}else{
			if(((Number)obj).intValue()==0){
				return false;
			}else{
				return true;
			}
		}
	}

	@Override
	public void save(BbsConcern bean) {
		getSession().save(bean);
	}
	
	@Override
	protected Class<BbsConcern> getEntityClass() {
		return BbsConcern.class;
	}

}
