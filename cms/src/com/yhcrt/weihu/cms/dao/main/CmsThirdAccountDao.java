package com.yhcrt.weihu.cms.dao.main;

import com.yhcrt.weihu.cms.entity.main.CmsThirdAccount;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface CmsThirdAccountDao {
	public Pagination getPage(String username,String source,int pageNo, int pageSize);

	public CmsThirdAccount findById(Long id);
	
	public CmsThirdAccount findByKey(String key);

	public CmsThirdAccount save(CmsThirdAccount bean);

	public CmsThirdAccount updateByUpdater(Updater<CmsThirdAccount> updater);

	public CmsThirdAccount deleteById(Long id);
}