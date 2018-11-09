package com.yhcrt.weihu.bbs.manager;

import java.util.List;

import com.yhcrt.weihu.bbs.entity.BbsSession;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.entity.BbsUserOnline;
import com.yhcrt.weihu.common.page.Pagination;
import com.yhcrt.weihu.core.entity.CmsConfig;

public interface BbsUserOnlineMng {

	public List<BbsUserOnline> getList();

	public Pagination getPage(int pageNo, int pageSize);

	public BbsUserOnline findById(Integer id);

	public BbsUserOnline save(BbsUserOnline bean);
	
	public BbsUserOnline saveByUser(BbsUser user);

	public BbsUserOnline update(BbsUserOnline bean);
	
	public void clearCount(CmsConfig config);
	
	public void updateUserOnlineTime(BbsSession userSession);

	public BbsUserOnline deleteById(Integer id);

	public BbsUserOnline[] deleteByIds(Integer[] ids);
}