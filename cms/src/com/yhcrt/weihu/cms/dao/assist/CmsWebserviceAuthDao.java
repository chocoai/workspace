package com.yhcrt.weihu.cms.dao.assist;

import com.yhcrt.weihu.cms.entity.assist.CmsWebserviceAuth;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface CmsWebserviceAuthDao {
	public Pagination getPage(int pageNo, int pageSize);
	
	public CmsWebserviceAuth findByUsername(String username);

	public CmsWebserviceAuth findById(Integer id);

	public CmsWebserviceAuth save(CmsWebserviceAuth bean);

	public CmsWebserviceAuth updateByUpdater(Updater<CmsWebserviceAuth> updater);

	public CmsWebserviceAuth deleteById(Integer id);
}