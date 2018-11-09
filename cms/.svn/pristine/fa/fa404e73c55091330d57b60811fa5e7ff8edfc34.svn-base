package com.yhcrt.weihu.core.manager;

import java.util.Date;
import java.util.List;

import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.entity.CmsUser;
import com.yhcrt.weihu.core.entity.CmsWorkflowEvent;
import com.yhcrt.weihu.core.entity.CmsWorkflowRecord;

public interface CmsWorkflowRecordMng {
	
	public List<CmsWorkflowRecord> getList(Integer eventId,Integer userId);

	public CmsWorkflowRecord save(CmsSite site, CmsWorkflowEvent event,
			CmsUser user, String opinion,Date recordTime, Integer type);

}