package com.yhcrt.weihu.cms.manager.assist;

import com.yhcrt.weihu.cms.entity.assist.CmsWebserviceAuth;
import com.yhcrt.weihu.common.page.Pagination;

public interface CmsWebserviceAuthMng {
	public Pagination getPage(int pageNo, int pageSize);
	
	public boolean isPasswordValid(String username, String password);
	
	public CmsWebserviceAuth findByUsername(String username);

	public CmsWebserviceAuth findById(Integer id);

	public CmsWebserviceAuth save(CmsWebserviceAuth bean);

	public CmsWebserviceAuth update(CmsWebserviceAuth bean);
	
	public CmsWebserviceAuth update(Integer id,String username,String password,String system,Boolean enable);

	public CmsWebserviceAuth deleteById(Integer id);
	
	public CmsWebserviceAuth[] deleteByIds(Integer[] ids);

}