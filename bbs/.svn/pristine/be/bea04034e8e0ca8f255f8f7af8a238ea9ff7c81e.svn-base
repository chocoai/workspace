package com.yhcrt.weihu.bbs.manager;

import com.yhcrt.weihu.bbs.entity.BbsWebserviceAuth;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsWebserviceAuthMng {
	public Pagination getPage(int pageNo, int pageSize);
	
	public boolean isPasswordValid(String username, String password);
	
	public BbsWebserviceAuth findByUsername(String username);

	public BbsWebserviceAuth findById(Integer id);

	public BbsWebserviceAuth save(BbsWebserviceAuth bean);

	public BbsWebserviceAuth update(BbsWebserviceAuth bean);
	
	public BbsWebserviceAuth update(Integer id,String username,String password,String system,Boolean enable);

	public BbsWebserviceAuth deleteById(Integer id);
	
	public BbsWebserviceAuth[] deleteByIds(Integer[] ids);

}