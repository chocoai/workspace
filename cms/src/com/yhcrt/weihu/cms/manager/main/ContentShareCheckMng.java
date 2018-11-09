package com.yhcrt.weihu.cms.manager.main;

import java.util.List;

import com.yhcrt.weihu.cms.entity.main.Channel;
import com.yhcrt.weihu.cms.entity.main.Content;
import com.yhcrt.weihu.cms.entity.main.ContentShareCheck;
import com.yhcrt.weihu.common.page.Pagination;

/**
 * 共享内容审核Manager接口
 * 
 * '内容'数据存在，则'共享内容审核'数据必须存在。
 * 
 */
public interface ContentShareCheckMng {
	
	public ContentShareCheck findById(Integer id);
	
	public ContentShareCheck save(ContentShareCheck check);
	
	public ContentShareCheck save(ContentShareCheck check, Content content,Channel channel);

	public ContentShareCheck update(ContentShareCheck bean);
	
	public ContentShareCheck deleteById(Integer id);
	
	public ContentShareCheck[] deleteByIds(Integer[] ids);
	
	public List<ContentShareCheck> getList(Integer contentId,Integer channelId);
	
	public Pagination getPageForShared(String title, Byte status, Integer siteId,Integer targetSiteId,Integer requestSiteId, int pageNo, int pageSize);
}