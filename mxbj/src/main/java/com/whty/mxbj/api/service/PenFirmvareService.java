package com.whty.mxbj.api.service;

import java.util.Map;

import com.whty.mxbj.base.exception.BusinessException;

public interface PenFirmvareService {

	Map<String, Object> getNewVersionCheckParam(String body) throws BusinessException;

	Map<String, Object> getNewVersion(Map<String, Object> map);

}
