/**
 * 
 */
package com.whty.assis.devicemanage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.devicemanage.dao.SchoolClassRuleDao;
import com.whty.assis.devicemanage.dao.UseTimeRuleDao;
import com.whty.assis.devicemanage.model.SchoolClassRule;
import com.whty.assis.devicemanage.model.UseTimeRule;
import com.whty.assis.devicemanage.service.UseTimeRuleService;
import com.whty.common.util.SysConstants;
import com.whty.common.util.TimeUtils;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年4月21日
 */
@Service
public class UseTimeRuleServiceImpl implements UseTimeRuleService {

	@Autowired
	private UseTimeRuleDao useTimeRuleDao;

	@Autowired
	private SchoolClassRuleDao schoolClassRuleDao;

	@Override
	public UseTimeRule loadById(Integer useTimeRuleId){
		return useTimeRuleDao.loadById(useTimeRuleId);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.devicemanage.service.UseTimeRuleService#saveUseTimeRule(
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String saveUseTimeRule(HttpServletRequest request) {

		String name = request.getParameter("name");
		Map<String, Object> useTimeRuleNameParam = new HashMap<String, Object>();
		useTimeRuleNameParam.put("name", name);
		List<UseTimeRule> useTimeRuleList = useTimeRuleDao.listByCondition(useTimeRuleNameParam);

		if (useTimeRuleList != null && useTimeRuleList.size() > 0) {
			return "规则名称已经存在，请更换";
		}

		String dateType = request.getParameter("dateType");

		if (StringUtils.isEmpty(dateType)) {
			dateType = "1";
		}

		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		String memo = request.getParameter("memo");

		String timeType = request.getParameter("timeType");

		if (StringUtils.isEmpty(timeType)) {
			timeType = "1";
		}

		UseTimeRule bean = new UseTimeRule();
		bean.setName(name);
		bean.setDateType(Integer.valueOf(dateType));
		bean.setTimeType(Integer.valueOf(timeType));
		bean.setStartTime(startTime);
		bean.setEndTime(endTime);
		bean.setStartDate(TimeUtils.string2Date(startDate, TimeUtils.DAY_FORMAT_1));
		bean.setEndDate(TimeUtils.string2Date(endDate, TimeUtils.DAY_FORMAT_1));
		bean.setCreateTime(new Date());
		bean.setUpdateTime(new Date());
		bean.setMemo(memo);

		useTimeRuleDao.save(bean);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.devicemanage.service.UseTimeRuleService#listByCondition(
	 * java.util.Map)
	 */
	@Override
	public List<UseTimeRule> listByCondition(Map<String, Object> paramMap) {
		return useTimeRuleDao.listByCondition(paramMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.devicemanage.service.UseTimeRuleService#updateUseTimeRule(
	 * com.whty.assis.devicemanage.model.UseTimeRule)
	 */
	@Override
	public String updateUseTimeRule(UseTimeRule bean) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.devicemanage.service.UseTimeRuleService#deleteUseTimeRule(
	 * java.lang.Integer)
	 */
	@Override
	public String deleteUseTimeRule(Integer id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("usetimeRuleId", Integer.valueOf(id));
		List<SchoolClassRule> schoolClassRuleList = schoolClassRuleDao.listByCondition(param);

		if (schoolClassRuleList != null && schoolClassRuleList.size() > 0) {
			return "该规则被使用，不能删除";
		}

		useTimeRuleDao.deleteById(id);
		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.UseTimeRuleService#
	 * queryUseTimeRulePage(java.util.Map)
	 */
	@Override
	public HandlerResult queryUseTimeRulePage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(useTimeRuleDao.listByCondition(paramMap));
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.UseTimeRuleService#
	 * queryUseTimeRulePage(java.util.Map, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult queryUseTimeRulePage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(useTimeRuleDao.listByCondition(paraMap));
		rs.setPage(page);
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.devicemanage.service.UseTimeRuleService#updateUseTimeRule(
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String updateUseTimeRule(HttpServletRequest request) {
		String name = request.getParameter("name");
		String oldRuleName = request.getParameter("oldRuleName");
		
		if(!oldRuleName.equals(name)){
			Map<String, Object> useTimeRuleNameParam = new HashMap<String, Object>();
			useTimeRuleNameParam.put("name", name);
			List<UseTimeRule> useTimeRuleList = useTimeRuleDao.listByCondition(useTimeRuleNameParam);

			if (useTimeRuleList != null && useTimeRuleList.size() > 0) {
				return "规则名称已经存在，请更换";
			}
		}
		
		String id = request.getParameter("id");
		
		UseTimeRule bean = useTimeRuleDao.loadById(Integer.valueOf(id));

		String dateType = request.getParameter("dateType");

		if (StringUtils.isEmpty(dateType)) {
			dateType = "1";
		}

		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");

		String memo = request.getParameter("memo");

		String timeType = request.getParameter("timeType");

		if (StringUtils.isEmpty(timeType)) {
			timeType = "1";
		}

		bean.setName(name);
		bean.setDateType(Integer.valueOf(dateType));
		bean.setTimeType(Integer.valueOf(timeType));
		bean.setStartTime(startTime);
		bean.setEndTime(endTime);
		bean.setStartDate(TimeUtils.string2Date(startDate, TimeUtils.DAY_FORMAT_1));
		bean.setEndDate(TimeUtils.string2Date(endDate, TimeUtils.DAY_FORMAT_1));
		bean.setUpdateTime(new Date());
		bean.setMemo(memo);

		useTimeRuleDao.update(bean);

		return SysConstants.SUCCESS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.devicemanage.service.UseTimeRuleService#detailUseTimeRule(
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public UseTimeRule detailUseTimeRule(HttpServletRequest request) {
		String id = request.getParameter("id");
		UseTimeRule bean = useTimeRuleDao.loadById(Integer.valueOf(id));
		return bean;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.devicemanage.service.UseTimeRuleService#
	 * queryUseTimeRulePage(java.util.Map, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult queryBySchoolClassRule(Integer schoolClassId, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(useTimeRuleDao.listBySchoolClassRule(schoolClassId));
		rs.setPage(page);
		return rs;
	}
	
	
	/* (non-Javadoc)
	 * @see com.whty.assis.devicemanage.service.UseTimeRuleService#listBySchoolClassRule(java.lang.Integer)
	 */
	@Override
	public List<UseTimeRule> listBySchoolClassRule(Integer schoolClassId) {
		return useTimeRuleDao.listBySchoolClassRule(schoolClassId);
	}

}