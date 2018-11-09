package com.yhcrt.weihu.core.dao;

import java.util.List;

import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;
import com.yhcrt.weihu.core.entity.CmsWorkflowRecord;

public interface CmsWorkflowRecordDao {
	public List<CmsWorkflowRecord> getList(Integer eventId,Integer userId);
	
	public Pagination getPage(int pageNo, int pageSize);

	public CmsWorkflowRecord findById(Integer id);

	public CmsWorkflowRecord save(CmsWorkflowRecord bean);

	public CmsWorkflowRecord updateByUpdater(Updater<CmsWorkflowRecord> updater);

	public CmsWorkflowRecord deleteById(Integer id);
}