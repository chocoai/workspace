package com.yhcrt.weihu.cms.dao.assist;

import java.util.List;

import com.yhcrt.weihu.cms.entity.assist.CmsScoreRecord;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface CmsScoreRecordDao {
	public Pagination getPage(int pageNo, int pageSize);
	
	public List<CmsScoreRecord> findListByContent(Integer contentId);
	
	public CmsScoreRecord findByScoreItemContent(Integer itemId,Integer contentId);

	public CmsScoreRecord findById(Integer id);

	public CmsScoreRecord save(CmsScoreRecord bean);

	public CmsScoreRecord updateByUpdater(Updater<CmsScoreRecord> updater);

	public CmsScoreRecord deleteById(Integer id);
}