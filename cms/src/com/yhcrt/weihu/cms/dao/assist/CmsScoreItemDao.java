package com.yhcrt.weihu.cms.dao.assist;

import com.yhcrt.weihu.cms.entity.assist.CmsScoreItem;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface CmsScoreItemDao {
	public Pagination getPage(Integer groupId,int pageNo, int pageSize);

	public CmsScoreItem findById(Integer id);

	public CmsScoreItem save(CmsScoreItem bean);

	public CmsScoreItem updateByUpdater(Updater<CmsScoreItem> updater);

	public CmsScoreItem deleteById(Integer id);
}