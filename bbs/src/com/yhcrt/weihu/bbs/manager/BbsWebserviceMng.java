package com.yhcrt.weihu.bbs.manager;

import java.util.List;
import java.util.Map;

import com.yhcrt.weihu.bbs.entity.BbsWebservice;
import com.yhcrt.weihu.common.page.Pagination;

public interface BbsWebserviceMng {
	public Pagination getPage(int pageNo, int pageSize);
	
	public List<BbsWebservice> getList(String type);
	
	public boolean hasWebservice(String type);
	
	public String callWebService(BbsWebservice service,Map<String,String>params);
	
	public void callWebService(String operate,Map<String,String>params);

	public BbsWebservice findById(Integer id);

	public BbsWebservice save(BbsWebservice bean,String[] paramName, String[] defaultValue);

	public BbsWebservice update(BbsWebservice bean,String[] paramName, String[] defaultValue);

	public BbsWebservice deleteById(Integer id);
	
	public BbsWebservice[] deleteByIds(Integer[] ids);
}