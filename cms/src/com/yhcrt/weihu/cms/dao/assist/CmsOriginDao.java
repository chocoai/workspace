package com.yhcrt.weihu.cms.dao.assist;

import java.util.List;

import com.yhcrt.weihu.cms.entity.assist.CmsOrigin;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface CmsOriginDao {
	public Pagination getPage(int pageNo, int pageSize);

	public List<CmsOrigin> getList(String name);

	public CmsOrigin findById(Integer id);

	public CmsOrigin save(CmsOrigin bean);

	public CmsOrigin updateByUpdater(Updater<CmsOrigin> updater);

	public CmsOrigin deleteById(Integer id);
}