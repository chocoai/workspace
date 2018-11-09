package com.yhcrt.weihu.bbs.manager;

import com.yhcrt.weihu.bbs.entity.BbsMagicLog;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsMagicLogMng {

	public Pagination getPage(Byte operator,Integer userId,int pageNo, int pageSize);

	public BbsMagicLog findById(Integer id);

	public BbsMagicLog save(BbsMagicLog bean);

	public BbsMagicLog update(BbsMagicLog bean);

	public BbsMagicLog deleteById(Integer id);

	public BbsMagicLog[] deleteByIds(Integer[] ids);
}