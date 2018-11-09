package com.yhcrt.weihu.bbs.dao;

import com.yhcrt.weihu.bbs.entity.BbsLoginLog;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsLoginLogDao {
	
	public Pagination getPage(int pageNo, int pageSize);

	public BbsLoginLog findById(Integer id);

	public BbsLoginLog save(BbsLoginLog bean);

	public BbsLoginLog updateByUpdater(Updater<BbsLoginLog> bean);

	public BbsLoginLog deleteById(Integer id);
	
}