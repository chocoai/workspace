package com.yhcrt.weihu.core.manager;

import java.util.Set;

import com.yhcrt.weihu.core.entity.CmsUser;
import com.yhcrt.weihu.core.entity.CmsWorkflowEvent;
import com.yhcrt.weihu.core.entity.CmsWorkflowEventUser;

public interface CmsWorkflowEventUserMng {
	
	public Set<CmsWorkflowEventUser> save(CmsWorkflowEvent event,Set<CmsUser>users);

	public Set<CmsWorkflowEventUser> update(CmsWorkflowEvent event,Set<CmsUser>users);
	
	public void deleteByEvent(Integer eventId);

}