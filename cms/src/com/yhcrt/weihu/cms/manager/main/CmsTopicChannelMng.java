package com.yhcrt.weihu.cms.manager.main;

import java.util.List;

import com.yhcrt.weihu.cms.entity.main.TopicChannel;
import com.yhcrt.weihu.common.page.Pagination;

public interface CmsTopicChannelMng {

    public Pagination getPage(int topicId, int pageNo, int pageSize);

    public List<TopicChannel> getListByTopic(Integer topicId);

    public TopicChannel findById(Integer id);

    public TopicChannel save(TopicChannel bean, Integer topicId);

    public TopicChannel update(TopicChannel bean, Integer topicId);

    public TopicChannel deleteById(Integer id);

    public TopicChannel[] deleteByIds(Integer[] ids);

    public TopicChannel[] updatePriority(Integer[] ids, Integer[] priority);
}