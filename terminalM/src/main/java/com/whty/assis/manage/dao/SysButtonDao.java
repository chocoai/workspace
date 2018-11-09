package com.whty.assis.manage.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.sysrole.model.SysButton;

public interface SysButtonDao extends IBaseDao<SysButton> {

	public List<String> queryButtonNamesByUserId(Map<String, Object> map);
}
