package com.yhcrt.weihu.cms.dao.assist;

import java.util.List;

import com.yhcrt.weihu.cms.entity.assist.CmsSensitivity;
import com.yhcrt.weihu.common.hibernate3.Updater;

public interface CmsSensitivityDao {
	public List<CmsSensitivity> getList(boolean cacheable);

	public CmsSensitivity findById(Integer id);

	public CmsSensitivity save(CmsSensitivity bean);

	public CmsSensitivity updateByUpdater(Updater<CmsSensitivity> updater);

	public CmsSensitivity deleteById(Integer id);
}