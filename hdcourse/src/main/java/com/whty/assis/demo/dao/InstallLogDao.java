package com.whty.assis.demo.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.demo.model.InstallLog;

public interface InstallLogDao extends IBaseDao<InstallLog> {

	public List<InstallLog> queryInstallLog(Map<String, Object> map);

}
