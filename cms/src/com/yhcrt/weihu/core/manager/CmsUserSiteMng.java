package com.yhcrt.weihu.core.manager;

import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.entity.CmsUser;
import com.yhcrt.weihu.core.entity.CmsUserSite;

public interface CmsUserSiteMng {
	public CmsUserSite findById(Integer id);

	public CmsUserSite save(CmsSite site, CmsUser user, Byte step,
			Boolean allChannel,Boolean allControlChannel);

	public CmsUserSite update(CmsUserSite bean);

	public void updateByUser(CmsUser user, Integer siteId, Byte step,
			Boolean allChannel,Boolean allControlChannel);

	public void updateByUser(CmsUser user, Integer[] siteIds, Byte[] steps,
			Boolean[] allChannels,Boolean[] allControlChannels);

	public int deleteBySiteId(Integer siteId);

	public CmsUserSite deleteById(Integer id);

	public CmsUserSite[] deleteByIds(Integer[] ids);
}