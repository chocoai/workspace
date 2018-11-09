package com.yhcrt.weihu.bbs.manager;

import com.yhcrt.weihu.bbs.entity.BbsGrade;
import com.yhcrt.weihu.bbs.entity.BbsPost;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsGradeMng {
	public BbsGrade saveGrade(BbsGrade entity, BbsUser bbsuser, BbsPost post);

	public Pagination getPage(int pageNo, int pageSize);

	public BbsGrade findById(Integer id);

	public BbsGrade save(BbsGrade bean);

	public BbsGrade update(BbsGrade bean);

	public BbsGrade deleteById(Integer id);

	public BbsGrade[] deleteByIds(Integer[] ids);
}