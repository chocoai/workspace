package com.whty.assis.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.whty.assis.demo.dao.CountWidgetDao;
import com.whty.assis.demo.model.AreaMonthUseTakingCount;
import com.whty.assis.demo.model.WidgetHistory;
import com.whty.assis.demo.service.CountWidgetService;
import com.whty.common.util.CommonFunction;

@Service
public class CountWidgetServiceImpl implements CountWidgetService {

	@Autowired
	private CountWidgetDao countWidgetDao;

	@Override
	public List<Map> getOrgListByAreaCode(Map<String, Object> param) {
		return countWidgetDao.getOrgListByAreaCode(param);
	}

	@Override
	public List<WidgetHistory> getWidget(Map<String, Object> param) {
		return countWidgetDao.getWidget(param);
	}

	@Override
	public List<WidgetHistory> getWidgetCount(Map<String, Object> param) {
		return countWidgetDao.getWidgetCount(param);
	}

	@Override
	public List<WidgetHistory> getWidgetHistoryCount(Map<String, Object> param) {
		return countWidgetDao.getWidgetHistoryCount(param);
	}

	@Override
	public List<WidgetHistory> getAllRanking(Map<String, Object> paramMap) {
		return countWidgetDao.getAllRanking(paramMap);
	}

	@Override
	public List<WidgetHistory> getUserRankingByOrg(Map<String, Object> paramMap) {
		return countWidgetDao.getUserRankingByOrg(paramMap);
	}

	@Override
	public List<WidgetHistory> getOrgRankingByAreaCode(Map<String, Object> paramMap) {
		return countWidgetDao.getOrgRankingByAreaCode(paramMap);
	}

	@Override
	public List<WidgetHistory> getAreaCodeRankingByProvinceCode(Map<String, Object> paramMap) {
		return countWidgetDao.getAreaCodeRankingByProvinceCode(paramMap);
	}

	@Override
	public void addCountDataToAttribute(Model model) {
		List<WidgetHistory> useCountList = countWidgetDao.getCountData();
		int areaCount = 0;
		int orgCount = 0;
		int userCount = 0;
		for (int i = 0; i < useCountList.size(); i++) {
			if (i == 0) {
				if (useCountList.get(i) != null) {
					areaCount = useCountList.get(i).getUseCount();
				}
			}
			if (i == 1) {
				if (useCountList.get(i) != null) {
					orgCount = useCountList.get(i).getUseCount();
				}
			}
			if (i == 2) {
				if (useCountList.get(i) != null) {
					userCount = useCountList.get(i).getUseCount();
				}
			}
		}
		model.addAttribute("areaCount", areaCount);
		model.addAttribute("orgCount", orgCount);
		model.addAttribute("userCount", userCount);
	}

	@Override
	public void addAreaCountDataToAttribute(Model model, Map<String, Object> paramMap) {
		List<WidgetHistory> useCountList = countWidgetDao.getAreaCountData(paramMap);
		int currentAreaCount = 0;
		int useCountTotalCount = 0;
		int orgTotalCount = 0;
		for (int i = 0; i < useCountList.size(); i++) {
			if (i == 0) {
				if (useCountList.get(i) != null) {
					currentAreaCount = useCountList.get(i).getUseCount();
				}
			}
			if (i == 1) {
				if (useCountList.get(i) != null) {
					useCountTotalCount = useCountList.get(i).getUseCount();
				}
			}
			if (i == 2) {
				if (useCountList.get(i) != null) {
					orgTotalCount = useCountList.get(i).getUseCount();
				}
			}
		}
		model.addAttribute("currentAreaCount", currentAreaCount);
		model.addAttribute("useCountToalCount", useCountTotalCount);
		model.addAttribute("orgTotalCount", orgTotalCount);

	}

}
