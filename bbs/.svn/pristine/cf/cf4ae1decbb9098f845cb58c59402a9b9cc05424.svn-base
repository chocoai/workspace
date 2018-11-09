package com.yhcrt.weihu.bbs.dao;

import java.util.List;

import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.entity.BbsUserGroup;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsUserGroupDao {
	
	public List<BbsUserGroup> getListOfPersonal();
	
	/**
	 * 获得论坛会员组
	 * 
	 * @param siteId
	 *            站点ID
	 * @return
	 */
	public List<BbsUserGroup> getList(Integer siteId);

	public Pagination getPage(int pageNo, int pageSize);

	public BbsUserGroup getRegDef();

	public BbsUserGroup findById(Integer id);

	public BbsUserGroup save(BbsUserGroup bean);

	public BbsUserGroup updateByUpdater(Updater<BbsUserGroup> updater);

	public BbsUserGroup deleteById(Integer id);
	
	public BbsUserGroup findNearByPoint(Long point);
}