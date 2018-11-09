package com.yhcrt.weihu.cms.dao.main;

import java.util.List;

import com.yhcrt.weihu.cms.entity.main.ContentShareCheck;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface ContentShareCheckDao {
	public ContentShareCheck findById(Integer id);

	public ContentShareCheck save(ContentShareCheck bean);

	public ContentShareCheck updateByUpdater(Updater<ContentShareCheck> updater);
	
	public ContentShareCheck update(ContentShareCheck bean);
	
	public ContentShareCheck deleteById(Integer id);
	
	public ContentShareCheck[] deleteByIds(Integer[] ids);

	public List<ContentShareCheck> getList(Integer contentId, Integer channelId);
	
	public Pagination getPageForShared(String title, Byte status, Integer siteId,Integer targetSiteId, Integer requestSiteId, int pageNo, int pageSize);
	
	
}