package com.yhcrt.weihu.core.dao;

import java.util.List;

import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.core.entity.CmsRole;

public interface CmsRoleDao {
	public List<CmsRole> getList();

	public CmsRole findById(Integer id);

	public CmsRole save(CmsRole bean);

	public CmsRole updateByUpdater(Updater<CmsRole> updater);

	public CmsRole deleteById(Integer id);
}