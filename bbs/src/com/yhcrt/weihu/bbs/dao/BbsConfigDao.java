package com.yhcrt.weihu.bbs.dao;

import com.yhcrt.weihu.bbs.entity.BbsConfig;
import com.yhcrt.weihu.common.hibernate3.Updater;

public interface BbsConfigDao {
	
	public BbsConfig getBySiteId(Integer siteId);
	/**
	 * 清理当日数据
	 */
	public void clearTodayData();
	
	public BbsConfig findById(Integer id);

	public BbsConfig save(BbsConfig bean);

	public BbsConfig updateByUpdater(Updater<BbsConfig> updater);

	public BbsConfig deleteById(Integer id);
}