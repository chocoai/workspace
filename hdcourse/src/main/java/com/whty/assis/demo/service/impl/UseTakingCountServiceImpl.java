package com.whty.assis.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.whty.assis.demo.dao.UseTakingCountDao;
import com.whty.assis.demo.model.AreaMonthActivityCount;
import com.whty.assis.demo.model.AreaMonthUsageCount;
import com.whty.assis.demo.model.AreaMonthUseTakingCount;
import com.whty.assis.demo.model.OrgMonthActivityCount;
import com.whty.assis.demo.model.OrgMonthUseTakingCount;
import com.whty.assis.demo.model.UserMonthActivityCount;
import com.whty.assis.demo.model.UserMonthUseTakingCount;
import com.whty.assis.demo.service.UseTakingCountService;
import com.whty.common.util.CommonFunction;
import com.whty.page.util.HandlerResult;

@Service
public class UseTakingCountServiceImpl implements UseTakingCountService {

	@Autowired
	private UseTakingCountDao useTakingCountDao;

	@Override
	public HandlerResult queryAreaMonthUseTakingDataGroupByArea(Map<String, Object> paramMap) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(useTakingCountDao.getAreaUseTakingDataGroupByArea(paramMap));
		return handlerResult;
	}

	@Override
	public HandlerResult queryOrgUseTakingDataGroupByOrg(Map<String, Object> paramMap) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(useTakingCountDao.getOrgUseTakingDataGroupByOrg(paramMap));
		return handlerResult;
	}

	@Override
	public HandlerResult queryUserUseTakingDataGroupByUser(Map<String, Object> paramMap) {
		HandlerResult handlerResult = new HandlerResult();
		handlerResult.setResultList(useTakingCountDao.getUserUseTakingDataGroupByUser(paramMap));
		return handlerResult;
	}

	@Override
	public void addCountDataToAttribute(Model model) {
		List<AreaMonthUseTakingCount> useCountList = useTakingCountDao.getCountData();
		long areaCount = 0;
		long orgCount = 0;
		long userCount = 0;
		for (int i = 0; i < useCountList.size(); i++) {
			if (i == 0) {
				if (useCountList.get(i) != null) {
					areaCount = useCountList.get(i).getUseTaking();
				}
			}
			if (i == 1) {
				if (useCountList.get(i) != null) {
					orgCount = useCountList.get(i).getUseTaking();
				}
			}
			if (i == 2) {
				if (useCountList.get(i) != null) {
					userCount = useCountList.get(i).getUseTaking();
				}
			}
		}
		model.addAttribute("areaCount", areaCount);
		model.addAttribute("orgCount", orgCount);
		model.addAttribute("userCount", userCount);
	}

	@Override
	public void addAreaCountDataToAttribute(Model model, Map<String, Object> paramMap) {
		List<AreaMonthUseTakingCount> useCountList = useTakingCountDao.getAreaCountData(paramMap);
		long currentAreaCount = 0;
		long useTakingTotalCount = 0;
		long orgTotalCount = 0;
		for (int i = 0; i < useCountList.size(); i++) {
			if (i == 0) {
				if (useCountList.get(i) != null) {
					currentAreaCount = useCountList.get(i).getUseTaking();
				}
			}
			if (i == 1) {
				if (useCountList.get(i) != null) {
					useTakingTotalCount = useCountList.get(i).getUseTaking();
				}
			}
			if (i == 2) {
				if (useCountList.get(i) != null) {
					orgTotalCount = useCountList.get(i).getUseTaking();
				}
			}
		}
		model.addAttribute("currentAreaCount", currentAreaCount);
		model.addAttribute("useTakingToalCount", CommonFunction.formatDuring(useTakingTotalCount));
		model.addAttribute("orgTotalCount", orgTotalCount);

	}

	@Override
	public void addOrgCountDataToAttribute(Model model, Map<String, Object> param) {
		List<OrgMonthUseTakingCount> useCountList = useTakingCountDao.getOrgUseTakingData(param);
		long orgNum = 0;
		long orgTakingTotal = 0;
		for (int i = 0; i < useCountList.size(); i++) {
			if (i == 0) {
				if (useCountList.get(i) != null) {
					orgNum = useCountList.get(i).getUseTaking();
				}
			}
			if (i == 1) {
				if (useCountList.get(i) != null) {
					orgTakingTotal = useCountList.get(i).getUseTaking();
				}
			}
		}
		model.addAttribute("orgNum", orgNum);
		model.addAttribute("orgTakingTotal", CommonFunction.formatDuring(orgTakingTotal));
	}

	@Override
	public List<AreaMonthUseTakingCount> getAreaMonthUseTakingCountEcharts(Map<String, Object> param) {
		return useTakingCountDao.getAreaMonthUseTakingCountEcharts(param);
	}

	@Override
	public List<OrgMonthUseTakingCount> getOrgMonthUseTakingCountEcharts(Map<String, Object> param) {
		return useTakingCountDao.getOrgMonthUseTakingCountEcharts(param);
	}

	@Override
	public List<UserMonthUseTakingCount> getUserMonthUseTakingCountEcharts(Map<String, Object> param) {
		return useTakingCountDao.getUserMonthUseTakingCountEcharts(param);
	}

	@Override
	public List<Map> getOrgListByAreaCode(String string) {
		return useTakingCountDao.getOrgListByAreaCode(string);
	}

	@Override
	public void addUserCountDataToAttribute(Model model, Map<String, Object> paramMap) {
		List<UserMonthUseTakingCount> useCountList = useTakingCountDao.getUserUseTakingData(paramMap);
		long orgNum = 0;
		long loginTakingTotal = 0;
		for (int i = 0; i < useCountList.size(); i++) {
			if (i == 0) {
				if (useCountList.get(i) != null) {
					orgNum = useCountList.get(i).getUseTaking();
				}
			}
			if (i == 1) {
				if (useCountList.get(i) != null) {
					loginTakingTotal = useCountList.get(i).getUseTaking();
				}
			}

		}
		model.addAttribute("userTotal", orgNum);
		model.addAttribute("loginTakingTotal", CommonFunction.formatDuring(loginTakingTotal));
	}

	@Override
	public List<AreaMonthUseTakingCount> getAreaMonthUseTakingCountData(Map<String, Object> param) {
		return useTakingCountDao.getAreaMonthUseTakingCountData(param);
	}

}
