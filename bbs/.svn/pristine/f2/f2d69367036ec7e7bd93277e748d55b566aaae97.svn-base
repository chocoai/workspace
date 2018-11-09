package com.yhcrt.weihu.bbs.dao;

import com.yhcrt.weihu.bbs.entity.BbsWebserviceAuth;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsWebserviceAuthDao {
	public Pagination getPage(int pageNo, int pageSize);
	
	public BbsWebserviceAuth findByUsername(String username);

	public BbsWebserviceAuth findById(Integer id);

	public BbsWebserviceAuth save(BbsWebserviceAuth bean);

	public BbsWebserviceAuth updateByUpdater(Updater<BbsWebserviceAuth> updater);

	public BbsWebserviceAuth deleteById(Integer id);
}