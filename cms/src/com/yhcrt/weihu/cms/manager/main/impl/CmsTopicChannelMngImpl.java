package com.yhcrt.weihu.cms.manager.main.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhcrt.weihu.cms.dao.main.CmsTopicChannelDao;
import com.yhcrt.weihu.cms.entity.main.TopicChannel;
import com.yhcrt.weihu.cms.manager.main.CmsTopicChannelMng;
import com.yhcrt.weihu.cms.manager.main.CmsTopicMng;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

/**
 * @Description:
 * 
 */
@Service
@Transactional
public class CmsTopicChannelMngImpl implements CmsTopicChannelMng {

    @Autowired
    private CmsTopicChannelDao dao;

    @Autowired
    private CmsTopicMng topicMng;

    /*
     * (non-Javadoc)
     * 
     * @see com.yhcrt.weihu.cms.manager.main.CmsTopicChannelMng#getPage(int,
     * int)
     */
    @Override
    public Pagination getPage(int topicId, int pageNo, int pageSize) {
	return dao.getPage(topicId, pageNo, pageSize, false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yhcrt.weihu.cms.manager.main.CmsTopicChannelMng#getListByTopic(java.
     * lang.Integer)
     */
    @Override
    public List<TopicChannel> getListByTopic(Integer topicId) {

	return dao.getListByTopicId(topicId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yhcrt.weihu.cms.manager.main.CmsTopicChannelMng#findById(java.lang.
     * Integer)
     */
    @Override
    public TopicChannel findById(Integer id) {

	return dao.findById(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yhcrt.weihu.cms.manager.main.CmsTopicChannelMng#save(com.yhcrt.weihu.
     * cms.entity.main.TopicChannel, java.lang.Integer)
     */
    @Override
    public TopicChannel save(TopicChannel bean, Integer topicId) {
	
	return dao.save(bean);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yhcrt.weihu.cms.manager.main.CmsTopicChannelMng#update(com.yhcrt.
     * weihu.cms.entity.main.TopicChannel, java.lang.Integer)
     */
    @Override
    public TopicChannel update(TopicChannel bean, Integer topicId) {

	Updater<TopicChannel> updater = new Updater<TopicChannel>(bean);
	TopicChannel entity = dao.updateByUpdater(updater);
	if (topicId != null) {
	    entity.setTopic(topicMng.findById(topicId));
	} else {
	    entity.setTopic(null);
	}
	return entity;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yhcrt.weihu.cms.manager.main.CmsTopicChannelMng#deleteById(java.lang.
     * Integer)
     */
    @Override
    public TopicChannel deleteById(Integer id) {
	TopicChannel bean = dao.deleteById(id);
	return bean;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yhcrt.weihu.cms.manager.main.CmsTopicChannelMng#deleteByIds(java.lang
     * .Integer[])
     */
    @Override
    public TopicChannel[] deleteByIds(Integer[] ids) {
	TopicChannel[] beans = new TopicChannel[ids.length];
	for (int i = 0, len = ids.length; i < len; i++) {
	    beans[i] = deleteById(ids[i]);
	}
	return beans;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.yhcrt.weihu.cms.manager.main.CmsTopicChannelMng#updatePriority(java.
     * lang.Integer[], java.lang.Integer[])
     */
    @Override
    public TopicChannel[] updatePriority(Integer[] ids, Integer[] priority) {
	int len = ids.length;
	TopicChannel[] beans = new TopicChannel[len];
	for (int i = 0; i < len; i++) {
	    beans[i] = findById(ids[i]);
	    beans[i].setPriority(priority[i]);
	}
	return beans;
    }

}
