package com.yhcrt.weihu.core.dao;

import java.util.List;

import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;
import com.yhcrt.weihu.core.entity.CmsDepartment;

public interface CmsDepartmentDao {

	public List<CmsDepartment> getList(Integer parentId,boolean all);

	public Pagination getPage(String name, int pageNo,int pageSize);

	public CmsDepartment findById(Integer id);

	public CmsDepartment findByName(String name);

	public CmsDepartment save(CmsDepartment bean);

	public CmsDepartment deleteById(Integer id);

	public CmsDepartment updateByUpdater(Updater<CmsDepartment> updater);

}