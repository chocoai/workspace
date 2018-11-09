package com.yhcrt.weihu.bbs.dao;

import com.yhcrt.weihu.bbs.entity.BbsReport;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsReportDao {
	public BbsReport findById(Integer id);

	public BbsReport save(BbsReport bean);

	public BbsReport updateByUpdater(Updater<BbsReport> updater);

	public BbsReport deleteById(Integer id);

	public Pagination getPage(Boolean status,Integer pageNo, Integer pageSize);

	public BbsReport findByUrl(String url);
}