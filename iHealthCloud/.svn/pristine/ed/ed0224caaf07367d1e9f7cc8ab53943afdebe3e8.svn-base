package com.yhcrt.iHealthCloud.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.entity.CmsChannel;



public interface CmsChannelService {
	
	int insert(CmsChannel record);
	
	int delete(Integer channelId);
	
	int update(CmsChannel channel);
	
	CmsChannel getRootChannel();
	
	List<CmsChannel> getChildChannelByParentId(Integer parentId);
	
    CmsChannel getChannelByChannelId(Integer channelId);
    
    List<CmsChannel> getAllChannel();
    
    
    String getCmsChannel(JSONObject pdata);
	
	

}
