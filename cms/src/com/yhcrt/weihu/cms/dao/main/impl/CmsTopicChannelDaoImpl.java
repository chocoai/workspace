package com.yhcrt.weihu.cms.dao.main.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.cms.dao.main.CmsTopicChannelDao;
import com.yhcrt.weihu.cms.entity.main.TopicChannel;
import com.yhcrt.weihu.common.hibernate3.Finder;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;
import com.yhcrt.weihu.common.page.Pagination;

/**
 * @Description:
 * 
 */

@Repository
public class CmsTopicChannelDaoImpl extends HibernateBaseDao<TopicChannel, Integer> implements CmsTopicChannelDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<TopicChannel> getListByTopicId(Integer topicId, Integer count, boolean cacheable) {
	Finder f = Finder.create("from TopicChannel bean where 1=1");
	if (topicId != null) {
	    f.append(" and bean.topic.id =:topicId");
	    f.setParam("topicId", topicId);
	}

	f.append(" order by bean.priority asc,bean.id desc");
	if (count != null) {
	    f.setMaxResults(count);
	}
	f.setCacheable(cacheable);
	return find(f);
    }

    @Override
    public Pagination getPage(Integer topicId, int pageNo, int pageSize, boolean cacheable) {

	Finder f = Finder.create("from TopicChannel bean where 1=1");
	if (topicId != null) {
	    f.append(" and bean.topic.id=:topicId");
	    f.setParam("topicId", topicId);
	}
	f.append(" order by bean.priority asc,bean.id desc");
	return find(f, pageNo, pageSize);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TopicChannel> getListByTopicId(Integer topicId) {
	Finder f = Finder.create("from TopicChannel bean where 1=1");
	if (topicId != null) {
	    f.append(" and bean.topic.id =:topicId");
	    f.setParam("topicId", topicId);
	}
	f.append(" order by bean.priority asc");
	return find(f);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TopicChannel> getListByTopicIds(Integer[] topicIds) {
	String hql = "from TopicChannel bean where bean.topic.id in (:ids) order by bean.id asc";
	return getSession().createQuery(hql).setParameterList("ids", topicIds).list();
    }

    @Override
    public TopicChannel findById(Integer id) {
	TopicChannel entity = get(id);
	return entity;
    }

    @Override
    public TopicChannel save(TopicChannel bean) {
	getSession().save(bean);
	return bean;
    }

    @Override
    public TopicChannel deleteById(Integer id) {
	TopicChannel entity = super.get(id);
	if (entity != null) {
	    getSession().delete(entity);
	}
	return entity;
    }

    @Override
    public int deleteContentRef(Integer id) {
	return 0;
    }

    @Override
    public int countByTopicId(Integer topicId) {
	String hql = "select count(*) from TopicChannel bean where bean.topic.id=:topicId";
	Query query = getSession().createQuery(hql);
	query.setParameter("topicId", topicId);
	return ((Number) query.iterate().next()).hashCode();
    }

    @Override
    protected Class<TopicChannel> getEntityClass() {
	return TopicChannel.class;
    }

}
