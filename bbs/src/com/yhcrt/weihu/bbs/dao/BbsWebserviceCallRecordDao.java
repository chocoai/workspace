package com.yhcrt.weihu.bbs.dao;

import com.yhcrt.weihu.bbs.entity.BbsWebserviceCallRecord;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsWebserviceCallRecordDao {
	public Pagination getPage(int pageNo, int pageSize);

	public BbsWebserviceCallRecord findById(Integer id);

	public BbsWebserviceCallRecord save(BbsWebserviceCallRecord bean);

	public BbsWebserviceCallRecord updateByUpdater(Updater<BbsWebserviceCallRecord> updater);

	public BbsWebserviceCallRecord deleteById(Integer id);
}