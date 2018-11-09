package com.yhcrt.weihu.bbs.manager;

import com.yhcrt.weihu.bbs.entity.BbsLoginLog;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsLoginLogMng {

	public Pagination getPage(int pageNo, int pageSize);

	public BbsLoginLog findById(Integer id);

	public BbsLoginLog save(BbsLoginLog bean);
	
	public BbsLoginLog loginLog(BbsUser user ,String ip);

	public BbsLoginLog update(BbsLoginLog bean);

	public BbsLoginLog deleteById(Integer id);

	public BbsLoginLog[] deleteByIds(Integer[] ids);
}