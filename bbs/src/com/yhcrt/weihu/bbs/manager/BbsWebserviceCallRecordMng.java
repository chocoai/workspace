package com.yhcrt.weihu.bbs.manager;

import com.yhcrt.weihu.bbs.entity.BbsWebserviceCallRecord;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsWebserviceCallRecordMng {
	public Pagination getPage(int pageNo, int pageSize);

	public BbsWebserviceCallRecord findById(Integer id);
	
	public BbsWebserviceCallRecord save(String clientUsername,String serviceCode);

	public BbsWebserviceCallRecord save(BbsWebserviceCallRecord bean);

	public BbsWebserviceCallRecord update(BbsWebserviceCallRecord bean);

	public BbsWebserviceCallRecord deleteById(Integer id);
	
	public BbsWebserviceCallRecord[] deleteByIds(Integer[] ids);
}