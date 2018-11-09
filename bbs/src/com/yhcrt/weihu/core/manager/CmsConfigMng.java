package com.yhcrt.weihu.core.manager;

import java.util.Date;
import java.util.Map;

import com.yhcrt.weihu.core.entity.BbsConfigAttr;
import com.yhcrt.weihu.core.entity.CmsConfig;

public interface CmsConfigMng {
	public CmsConfig get();
	
	public Map<String,String> getAttr();

	public void updateCountCopyTime(Date d);

	public void updateCountClearTime(Date d);

	public CmsConfig update(CmsConfig bean);

	public void updateConfigAttr(BbsConfigAttr bbsconfigAttr);
	
	public void updateSsoAttr(Map<String,String> ssoAttr);
}