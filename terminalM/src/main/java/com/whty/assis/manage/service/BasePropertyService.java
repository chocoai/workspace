package com.whty.assis.manage.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.manage.model.BaseProperty;
import com.whty.page.util.HandlerResult;

public interface BasePropertyService {

	String getPropertyValue(String propertyKey, String platformCode);

}
