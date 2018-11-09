package com.whty.assis.api.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.api.model.ApiUser;
import com.whty.assis.base.exception.BusinessException;
import com.whty.assis.demo.model.Ta_user;
import com.whty.assis.demo.model.WidgetLog;

public interface ClientUserService {

	@SuppressWarnings("rawtypes")
	Map syncClientUser(ApiUser sessionUser, Map respMap) throws BusinessException;

	@SuppressWarnings("rawtypes")
	Ta_user getTaUserByParam(Map map);

	void syncClientUser(ApiUser apiUser, Ta_user ta_user);

	List<WidgetLog> getOp();

	@SuppressWarnings("rawtypes")
	void syncClientUser(Map param);

	@SuppressWarnings("rawtypes")
	void powerOff(Map map);

	void saveWidgetInfoLog(Map map);

	List<WidgetLog> getWidgetLog(Map map);

	void updateWidgetLog(Map map);

	void saveInstallLog(Map map);

	String visitCount(Map map);

	String listOgra(Map map, String start, String end, String url);

	String listClassesInfo(Map<String, Object> param, String string, String string2, String url);

	void saveInstallMac(Map<String, Object> paramMap);

}
