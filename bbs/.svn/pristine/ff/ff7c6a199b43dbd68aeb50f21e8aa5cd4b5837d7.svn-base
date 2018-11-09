package com.yhcrt.weihu.bbs.dao;

import com.yhcrt.weihu.bbs.entity.BbsOperation;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsOperationDao {

	public Pagination getPage(int pageNo, int pageSize);

	public BbsOperation findById(Integer id);

	public BbsOperation save(BbsOperation bean);

	public BbsOperation updateByUpdater(Updater<BbsOperation> updater);

	public BbsOperation deleteById(Integer id);

}