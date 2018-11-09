package com.yhcrt.weihu.cms.dao.main;

import java.util.List;

import com.yhcrt.weihu.cms.entity.main.TopicChannel;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;


public interface CmsTopicChannelDao {
    public List<TopicChannel> getListByTopicId(Integer topicId, Integer count, boolean cacheable);

    public Pagination getPage(Integer topicId, int pageNo, int pageSize, boolean cacheable);

    public List<TopicChannel> getListByTopicId(Integer topicId);

    public List<TopicChannel> getListByTopicIds(Integer[] topicIds);

    public TopicChannel findById(Integer id);

    public TopicChannel save(TopicChannel bean);

    public TopicChannel updateByUpdater(Updater<TopicChannel> updater);

    public TopicChannel deleteById(Integer id);

    public int deleteContentRef(Integer id);

    public int countByTopicId(Integer topicId);
}