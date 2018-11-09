package com.yhcrt.weihu.cms.dao.assist;

import java.util.List;

import com.yhcrt.weihu.cms.entity.assist.CmsUserMenu;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface CmsUserMenuDao {
	public Pagination getPage(Integer userId,int pageNo, int pageSize);
	
	public List<CmsUserMenu> getList(Integer userId,int count);

	public CmsUserMenu findById(Integer id);

	public CmsUserMenu save(CmsUserMenu bean);

	public CmsUserMenu updateByUpdater(Updater<CmsUserMenu> updater);

	public CmsUserMenu deleteById(Integer id);
}