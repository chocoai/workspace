package com.yhcrt.weihu.cms.dao.assist;

import java.util.List;

import com.yhcrt.weihu.cms.entity.assist.CmsChange;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface CmsChangeDao {
	public Pagination getPage(Integer userId, Integer contentId,
			Integer siteId, boolean cacheable, int pageNo, int pageSize);

	public List<CmsChange> getList(Integer siteId, Integer userId, Integer contentId, boolean cacheable);
	
	public CmsChange findById(Integer id);

	public CmsChange save(CmsChange bean);

	public CmsChange updateByUpdater(Updater<CmsChange> updater);

	public CmsChange deleteById(Integer id);
}