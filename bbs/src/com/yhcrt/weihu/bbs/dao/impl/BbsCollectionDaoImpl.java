package com.yhcrt.weihu.bbs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.bbs.Constants;
import com.yhcrt.weihu.bbs.dao.BbsCollectionDao;
import com.yhcrt.weihu.bbs.entity.BbsCollection;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;
import com.yhcrt.weihu.common.page.Page;

@Repository
public class BbsCollectionDaoImpl extends HibernateBaseDao<BbsCollection,Integer> 
	implements BbsCollectionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BbsCollection> getListByTopicOrForum(Integer topicId, Integer forumId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from BbsCollection c where 1=1 ");
		if(topicId != null){
			hql.append(" and c.topic.id="+topicId);
		}
		if(forumId != null){
			hql.append(" and c.forum.id="+forumId);
		}
		return getSession().createQuery(hql.toString()).list();
	}

	@Override
	public int deleteByTopicOrForum(Integer topicId, Integer forumId) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from bbs_collection where 1=1 ");
		if(forumId != null){
			sql.append(" and FORUM_ID="+forumId);
		}
		if(topicId != null){
			sql.append(" and TOPIC_ID="+topicId);
		}
		return getSession().createSQLQuery(sql.toString()).executeUpdate();
	}

	@Override
	public int deleteByIds(Integer[] ids) {
		String hql = "delete from BbsCollection c where c.id in (:id)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("id", ids);
		return query.executeUpdate();
	}

	@Override
	public void deleteByNotId(Integer userId, Integer forumId, Integer topicId) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from bbs_collection where CREATER_ID="+userId);
		if(forumId != null){
			sql.append(" and FORUM_ID="+forumId);
		}
		if(topicId != null){
			sql.append(" and TOPIC_ID="+topicId);
		}
		getSession().createSQLQuery(sql.toString()).executeUpdate();
	}

	@Override
	public int isCollection(Integer userId, Integer forumId, Integer topicId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from bbs_collection c where c.CREATER_ID="+userId);
		if(forumId != null){
			sql.append(" and c.FORUM_ID="+forumId);
		}
		if(topicId != null){
			sql.append(" and c.TOPIC_ID="+topicId);
		}
		Object obj = getSession().createSQLQuery(sql.toString()).uniqueResult();
		if(obj == null){
			return 0;
		}else{
			if(((Number)obj).intValue()>0){
				return 1;
			}else{
				return 0;
			}
		}
	}

	@Override
	public void save(BbsCollection bean) {
		getSession().save(bean);
	}

	@Override
	public BbsCollection findById(Integer id) {
		BbsCollection bean = get(id);
		return bean;
	}

	@Override
	public void deleteById(Integer id) {
		BbsCollection bean = super.get(id);
		if(bean != null){
			getSession().delete(bean);
		}
	}

	@Override
	protected Class<BbsCollection> getEntityClass() {
		return BbsCollection.class;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Page<BbsCollection> getPage(Integer pageSize,Integer pageNo,Integer userId,String type) {
		Page<BbsCollection> page = new Page<BbsCollection>(pageNo,pageSize);
		List<BbsCollection> list = new ArrayList<BbsCollection>();
		if(pageSize == null){
			page.setPageSize(10);
		}
		if(pageNo == null){
			page.setPageNo(0);
		}
		String sqlCount = null;
		String sql = null;
		if(type!=null && type.equals(Constants.COLLECTION_FORUM)){
			sqlCount = "select count(*) from bbs_collection c where c.TOPIC_ID IS NULL AND c.CREATER_ID="+userId;
			sql = "from BbsCollection c where c.topic IS NULL AND c.creater.id="+userId+" order by c.createTime desc ";
		}else{
			sqlCount = "select count(*) from bbs_collection c where c.FORUM_ID IS NULL AND c.CREATER_ID="+userId;
			sql = "from BbsCollection c where c.forum IS NULL AND c.creater.id="+userId+" order by c.createTime desc ";
		}
		Object obj = getSession().createSQLQuery(sqlCount).uniqueResult();
		if(obj == null){
			page.setList(list);
			page.setTotalCount(0);
			return page;
		}else{
			page.setTotalCount(((Number)obj).intValue());
		}
		list = getSession().createQuery(sql).setFirstResult((page.getPageNo()-1)*page.getPageSize())
				.setMaxResults(page.getPageNo()*page.getPageSize()).setCacheable(false).list();
		page.setList(list);
		return page;
	}

}
