package com.whty.assis.basicdata.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.basicdata.model.TerminalDeviceType;

public interface TerminalDeviceTypeDao extends IBaseDao<TerminalDeviceType>{

	List<Map<String, Object>> listTerminalType();
	
	List<TerminalDeviceType> listTerminalUserTypeCount();
 	
}
