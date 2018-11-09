package com.yhcrt.weihu.bbs.dao;

import com.yhcrt.weihu.bbs.entity.BbsReportExt;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsReportExtDao {
	public BbsReportExt findById(Integer id);

	public BbsReportExt save(BbsReportExt bean);

	public BbsReportExt updateByUpdater(Updater<BbsReportExt> updater);

	public BbsReportExt deleteById(Integer id);

	public Pagination getPage(Integer reportId,Integer pageNo, Integer pageSize);

	public BbsReportExt findByReportUid(Integer reportId, Integer userId);
}