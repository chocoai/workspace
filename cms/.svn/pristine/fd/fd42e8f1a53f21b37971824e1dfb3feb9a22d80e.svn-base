package com.yhcrt.weihu.core.dao;

import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;
import com.yhcrt.weihu.core.entity.CmsWorkflowEventUser;

public interface CmsWorkflowEventUserDao {
	public Pagination getPage(int pageNo, int pageSize);

	public CmsWorkflowEventUser findById(Integer id);

	public CmsWorkflowEventUser save(CmsWorkflowEventUser bean);

	public CmsWorkflowEventUser updateByUpdater(Updater<CmsWorkflowEventUser> updater);

	public void deleteByEvent(Integer eventId);
	
	public CmsWorkflowEventUser deleteById(Integer id);
	
}