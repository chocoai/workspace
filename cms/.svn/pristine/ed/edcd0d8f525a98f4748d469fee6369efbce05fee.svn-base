package com.yhcrt.weihu.cms.manager.main;

import com.yhcrt.weihu.cms.entity.main.CmsThirdAccount;
import com.yhcrt.weihu.common.page.Pagination;

public interface CmsThirdAccountMng {
	public Pagination getPage(String username,String source,int pageNo, int pageSize);

	public CmsThirdAccount findById(Long id);
	
	public CmsThirdAccount findByKey(String key);

	public CmsThirdAccount save(CmsThirdAccount bean);

	public CmsThirdAccount update(CmsThirdAccount bean);

	public CmsThirdAccount deleteById(Long id);
	
	public CmsThirdAccount[] deleteByIds(Long[] ids);
}