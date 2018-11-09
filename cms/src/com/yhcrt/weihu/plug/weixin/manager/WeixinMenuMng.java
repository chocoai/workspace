package com.yhcrt.weihu.plug.weixin.manager;

import java.util.List;

import com.yhcrt.weihu.common.page.Pagination;
import com.yhcrt.weihu.plug.weixin.entity.WeixinMenu;

public interface WeixinMenuMng {
	
	public Pagination getPage(Integer siteId,Integer parentId,int pageNo,int pageSize);
	
	public List<WeixinMenu> getList(Integer siteId);
	
	public WeixinMenu findById(Integer id);
	
	public WeixinMenu save(WeixinMenu bean);
	
	public WeixinMenu update(WeixinMenu bean);
	
	public WeixinMenu deleteById(Integer id);
	
	public WeixinMenu[] deleteByIds(Integer[] ids);
}
