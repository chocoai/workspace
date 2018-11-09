package com.yhcrt.weihu.bbs.dao;

import com.yhcrt.weihu.bbs.entity.BbsMemberMagic;
import com.yhcrt.weihu.common.hibernate3.Updater;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsMemberMagicDao {

	public Pagination getPage(Integer userId, int pageNo, int pageSize);

	public BbsMemberMagic findById(Integer id);

	public BbsMemberMagic save(BbsMemberMagic bean);

	public BbsMemberMagic updateByUpdater(Updater<BbsMemberMagic> bean);

	public BbsMemberMagic deleteById(Integer id);

}