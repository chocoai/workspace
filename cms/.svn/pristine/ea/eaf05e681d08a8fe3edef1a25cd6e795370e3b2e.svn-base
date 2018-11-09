package com.yhcrt.weihu.core.manager;

import java.util.Date;
import java.util.Map;

import com.yhcrt.weihu.core.entity.BbsConfigAttr;
import com.yhcrt.weihu.core.entity.CmsConfig;
import com.yhcrt.weihu.core.entity.CmsConfigAttr;
import com.yhcrt.weihu.core.entity.MarkConfig;
import com.yhcrt.weihu.core.entity.MemberConfig;

public interface CmsConfigMng {
	public CmsConfig get();

	public void updateCountCopyTime(Date d);

	public void updateCountClearTime(Date d);

	public CmsConfig update(CmsConfig bean);

	public MarkConfig updateMarkConfig(MarkConfig mark);

	public void updateMemberConfig(MemberConfig memberConfig);
	
	public void updateConfigAttr(CmsConfigAttr configAttr);
	
	public void updateConfigAttr(BbsConfigAttr bbsconfigAttr);
	
	public void updateSsoAttr(Map<String,String> ssoAttr);
}