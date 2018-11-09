package com.yhcrt.weihu.bbs.dao;

import java.util.List;

import com.yhcrt.weihu.bbs.entity.CmsSensitivity;
import com.yhcrt.weihu.common.hibernate3.Updater;

public interface CmsSensitivityDao {
	public List<CmsSensitivity> getList(Integer siteId, boolean cacheable);

	public CmsSensitivity findById(Integer id);

	public CmsSensitivity save(CmsSensitivity bean);

	public CmsSensitivity updateByUpdater(Updater<CmsSensitivity> updater);

	public CmsSensitivity deleteById(Integer id);
}