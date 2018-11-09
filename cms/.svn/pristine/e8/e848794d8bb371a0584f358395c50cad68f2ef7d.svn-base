package com.yhcrt.weihu.cms.manager.assist;

import com.yhcrt.weihu.cms.entity.assist.CmsWebserviceCallRecord;
import com.yhcrt.weihu.common.page.Pagination;

public interface CmsWebserviceCallRecordMng {
	public Pagination getPage(int pageNo, int pageSize);

	public CmsWebserviceCallRecord findById(Integer id);
	
	public CmsWebserviceCallRecord save(String clientUsername,String serviceCode);

	public CmsWebserviceCallRecord save(CmsWebserviceCallRecord bean);

	public CmsWebserviceCallRecord update(CmsWebserviceCallRecord bean);

	public CmsWebserviceCallRecord deleteById(Integer id);
	
	public CmsWebserviceCallRecord[] deleteByIds(Integer[] ids);
}