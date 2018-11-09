package com.whty.assis.demo.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.whty.common.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.whty.assis.base.service.BaseService;
import com.whty.assis.demo.dao.LicenceOrgDao;
import com.whty.assis.demo.model.Ebp_licence_org;
import com.whty.assis.demo.service.LicenceOrgService;
import com.whty.common.util.CommonFunction;
import com.whty.common.util.SysConfig;
import com.whty.page.util.HandlerResult;

/**
 * 授权机构信息service实现
 * 
 * @author Administrator
 *
 */
@Service
public class LicenceOrgServiceImpl extends BaseService implements LicenceOrgService {

	private static Logger logger = LoggerFactory.getLogger(LicenceOrgServiceImpl.class);

	@Autowired
	private LicenceOrgDao licenceOrgDao;

	/* 新增授权机构 */
	@Override
	public void saveLicenceOrg(Ebp_licence_org licence_org) {
		logger.info("saveLicenceOrg:" + licence_org);
		licenceOrgDao.saveLicenceOrg(licence_org);
	}

	/* 删除授权机构 */
	@Override
	public void deleteLicenceOrg(List list) {
		logger.info("deleteLicenceOrg:" + list);
		licenceOrgDao.deleteLicenceOrg(list);
	}

	/* 修改授权机构 */
	@Override
	public void updateLicenceOrg(Ebp_licence_org licence_org) {
		logger.info("updateLicenceOrg:" + licence_org);
		licenceOrgDao.updateLicenceOrg(licence_org);
	}

	/* 查询授权机构 */
	@Override
	public List<Ebp_licence_org> queryLicenceOrg(Map<String, Object> paramMap) {
		return licenceOrgDao.queryLicenceOrg(paramMap);
	}

	/* 分页查询授权机构列表 */
	@Override
	public HandlerResult queryLicenceOrgPage(Map<String, Object> paramMap) {
		logger.info("queryLicenceOrgPage:" + paramMap);
		HandlerResult rs = new HandlerResult();
		rs.setResultList(licenceOrgDao.queryLicenceOrg(paramMap));
		return rs;
	}

	@Override
	public void countLicenceCode(Map<String, Object> param) {
		licenceOrgDao.countLicenceCode(param);
	}

	@Override
	public List<Map> getLicenceCount(Map<String, Object> countParam) {
		return licenceOrgDao.getLicenceCount(countParam);
	}

	@Override
	public void setCountDataTOModel(Model model) {
		Map<String, Object> countParam = new HashMap<String, Object>();
		Date startTime = TimeUtil.getCurrentMonthStartDate();
		Date endTime = TimeUtil.getCurrentMonthEndDate();

		countParam.put("startTime", CommonFunction.getDateSampleString(startTime));
		countParam.put("endTime", CommonFunction.getDateSampleString(endTime));

		countParam.put("orgId1", SysConfig.getStrValue("orgId1"));
		countParam.put("orgId2", SysConfig.getStrValue("orgId2"));
		countParam.put("orgId3", SysConfig.getStrValue("orgId3"));
		List<Map> countMapList = getLicenceCount(countParam);

		int currMonthAddCount = 0;// 新增授权总数
		int licenceCount = 0;// 授权总数

		int currMonthUseCount = 0;// 新增已使用授权总数
		int licenceUseCount = 0;// 已使用授权总数

		int currMonthAddOrg = 0;// 新增授权学校数
		int licenceOrgCount = 0;// 授权学校总数

		int orgId1UseCount = 0;//
		int orgIdLicenceCount = 0;

		int orgId2UseCount = 0;
		int orgId2LicenceCount = 0;

		int orgId3UseCount = 0;
		int orgId3LicenceCount = 0;

		for (int i = 0; i < countMapList.size(); i++) {
			Map<String, Object> value = countMapList.get(i);
			switch (i) {
			case 0:
				currMonthAddCount = Integer.valueOf(value.get("COUNT").toString());
				break;
			case 1:
				licenceCount = Integer.valueOf(value.get("COUNT").toString());
				break;
			case 2:
				currMonthUseCount = Integer.valueOf(value.get("COUNT").toString());
				break;
			case 3:
				licenceUseCount = Integer.valueOf(value.get("COUNT").toString());
				break;
			case 4:
				currMonthAddOrg = Integer.valueOf(value.get("COUNT").toString());
				break;
			case 5:
				licenceOrgCount = Integer.valueOf(value.get("COUNT").toString());
				break;
			case 6:
				orgId1UseCount = Integer.valueOf(value.get("COUNT").toString());
				break;
			case 7:
				orgIdLicenceCount = Integer.valueOf(value.get("COUNT").toString());
				break;
			case 8:
				orgId2UseCount = Integer.valueOf(value.get("COUNT").toString());
				break;
			case 9:
				orgId2LicenceCount = Integer.valueOf(value.get("COUNT").toString());
				break;
			case 10:
				orgId3UseCount = Integer.valueOf(value.get("COUNT").toString());
				break;
			case 11:
				orgId3LicenceCount = Integer.valueOf(value.get("COUNT").toString());
				break;
			default:
				break;
			}
		}

		model.addAttribute("currMonthAddCount", currMonthAddCount);
		model.addAttribute("licenceCount", licenceCount);
		model.addAttribute("currMonthUseCount", currMonthUseCount);
		model.addAttribute("licenceUseCount", licenceUseCount);
		model.addAttribute("currMonthAddOrg", currMonthAddOrg);
		model.addAttribute("licenceOrgCount", licenceOrgCount);
		model.addAttribute("orgId1UseCount", orgId1UseCount);
		model.addAttribute("orgIdLicenceCount", orgIdLicenceCount);
		model.addAttribute("orgId2UseCount", orgId2UseCount);
		model.addAttribute("orgId2LicenceCount", orgId2LicenceCount);
		model.addAttribute("orgId3UseCount", orgId3UseCount);
		model.addAttribute("orgId3LicenceCount", orgId3LicenceCount);

	}

}
