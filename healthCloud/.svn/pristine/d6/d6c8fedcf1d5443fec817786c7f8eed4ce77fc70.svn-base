package com.yhcrt.healthcloud.cms.service;

import java.util.List;

import com.yhcrt.healthcloud.cms.entity.CmsChannel;

public interface CmsChannelService {
	
	int insert(CmsChannel record);
	
	int delete(Integer channelId);
	
	int update(CmsChannel channel);
	
	CmsChannel getRootChannel();
	
	List<CmsChannel> getChildChannelByParentId(Integer parentId);
	
    CmsChannel getChannelByChannelId(Integer channelId);
    
    List<CmsChannel> getAllChannel();
	
	

}
