package com.yhcrt.iHealthCloud.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.entity.CmsContent;



public interface CmsContentService {
	
	int insert(CmsContent content);
	
	int delete(Integer contentId);
	
	int update(CmsContent content);
	
	CmsContent getContentById(Integer contentId);
	
	List<CmsContent> listContentsByChannelId(String channelId);
	
	int batchArchive(String[] contentIds);
	
	String getRecommendCms(JSONObject pdata);
	
	String getCmsContentList(JSONObject pdata);
	
	String getCmsContentDetail(JSONObject pdata);
	
    String getImages(JSONObject pdata);
}
