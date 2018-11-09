package com.yhcrt.weihu.core.dao;

import java.util.List;

import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;
import com.yhcrt.weihu.core.entity.CmsWorkflow;

public interface CmsWorkflowDao {
	public Pagination getPage(Integer siteId,int pageNo, int pageSize);
	
	public List<CmsWorkflow> getList(Integer siteId,Boolean disabled);

	public CmsWorkflow findById(Integer id);

	public CmsWorkflow save(CmsWorkflow bean);

	public CmsWorkflow updateByUpdater(Updater<CmsWorkflow> updater);

	public CmsWorkflow deleteById(Integer id);
}