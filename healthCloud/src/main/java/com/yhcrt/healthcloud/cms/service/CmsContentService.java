package com.yhcrt.healthcloud.cms.service;

import java.util.List;

import com.yhcrt.healthcloud.cms.entity.CmsContent;

public interface CmsContentService {
	
	int insert(CmsContent content);
	
	int delete(Integer contentId);
	
	int update(CmsContent content);
	
	CmsContent getContentById(Integer contentId);
	
	List<CmsContent> listContentsByChannelId(String channelId);
	
	List<CmsContent> getArchivedContents(Integer channelId);
	
	int batchArchive(String[] contentIds);
	
	int batchCancleArchive(String[] contentIds);
	
	int batchDelete(String[] contentIds);
	
}
