package com.yhcrt.weihu.cms.dao.assist;

import java.util.List;

import com.yhcrt.weihu.cms.entity.assist.CmsWebservice;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface CmsWebserviceDao {
	public Pagination getPage(int pageNo, int pageSize);
	
	public List<CmsWebservice> getList(String type);

	public CmsWebservice findById(Integer id);

	public CmsWebservice save(CmsWebservice bean);

	public CmsWebservice updateByUpdater(Updater<CmsWebservice> updater);

	public CmsWebservice deleteById(Integer id);
}