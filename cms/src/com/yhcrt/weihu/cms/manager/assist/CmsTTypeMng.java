package com.yhcrt.weihu.cms.manager.assist;

import java.util.List;

import com.yhcrt.weihu.cms.entity.assist.CmsTType;

public interface CmsTTypeMng {
	public List<CmsTType> getList(Integer siteId);


	public CmsTType findById(Integer id);

	public CmsTType save(CmsTType bean);

	public CmsTType update(CmsTType bean);

	public CmsTType deleteById(Integer id);

	public CmsTType[] deleteByIds(Integer[] ids);
}