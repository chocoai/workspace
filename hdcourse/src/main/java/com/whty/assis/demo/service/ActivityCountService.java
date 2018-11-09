package com.whty.assis.demo.service;

import java.util.List;
import java.util.Map;

import com.whty.assis.demo.model.AreaMonthActivityCount;
import com.whty.assis.demo.model.OrgMonthActivityCount;
import com.whty.page.util.HandlerResult;

public interface ActivityCountService {

	List<OrgMonthActivityCount> getOrgMonthActivityCount(Map<String, Object> map);

	List<AreaMonthActivityCount> getAreaMonthActivityCount(Map<String, Object> map);

	HandlerResult queryAreaMonthActivityCount(Map<String, Object> paramMap);

	HandlerResult queryOrgMonthActivityCount(Map<String, Object> paramMap);
}
