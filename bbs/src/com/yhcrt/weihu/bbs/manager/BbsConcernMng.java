package com.yhcrt.weihu.bbs.manager;

import java.util.List;

import com.yhcrt.weihu.bbs.entity.BbsConcern;
import com.yhcrt.weihu.common.page.Page;

public interface BbsConcernMng {

	public void delete(Integer concernId);
	public Page<BbsConcern> getPage(Integer userId,Integer pageSize,Integer pageNo);
	public List<BbsConcern> getListByUser(Integer userId);
	public int deleteByUser(Integer userId,Integer concernUserId);
	public Boolean isConcernByUser(Integer userId,Integer concernUserId);
	public void save(Integer userId,Integer concernUserId);
	
}
