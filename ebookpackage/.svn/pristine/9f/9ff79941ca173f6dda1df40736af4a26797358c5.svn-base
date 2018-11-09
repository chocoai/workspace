package com.whty.ebp.manage.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.common.util.GUIDGenerator;
import com.whty.common.util.TimeUtils;
import com.whty.ebp.base.service.BaseService;
import com.whty.ebp.manage.dao.AppNetBlackDao;
import com.whty.ebp.manage.dao.BrowserDao;
import com.whty.ebp.manage.dao.PlatformInfoDao;
import com.whty.ebp.manage.model.AppBlackList;
import com.whty.ebp.manage.model.Browser;
import com.whty.ebp.manage.model.WhiteList;
import com.whty.ebp.manage.service.AppNetBlackService;
import com.whty.ebp.manage.service.BrowserService;
import com.whty.ebp.manage.service.FlatModelService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

@Service
public class AppNetBlackServiceImpl extends BaseService implements AppNetBlackService{

	@Autowired
	private AppNetBlackDao appNetBlackDao;

	@Autowired
	private FlatModelService flatModelService;
	
	@Override
	public HandlerResult queryAppNetBlackPage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult re = new HandlerResult();
		List<AppBlackList> appList = appNetBlackDao.queryAppNetByCondition(paraMap);
		re.setResultList(appList);
		re.setPage(page);
		return re;
	}

	@Override
	public List<Map<String, Object>> queryPlatformCodeByAppBlackListId(Map<String, Object> params) {
		return appNetBlackDao.queryPlatformCodeByAppBlackListId(params);
	}

	@Override
	public AppBlackList queryById(String id) {
		return appNetBlackDao.loadById(id);
	}

	@Override
	public void save(AppBlackList appBlackList) {
		// 保存应用
		appBlackList.setId(GUIDGenerator.getGUID());
		appBlackList.setCreateTime(new Date());

		appBlackList.setStatus("2");

		if ("-1".equals(appBlackList.getFlatModelIds())) {
			appBlackList.setFlatModelIds("");

		} else {
			String[] platformCodeArr = appBlackList.getPlatform_codes().split(",");

			if (platformCodeArr != null) {
				for (int i = 0; i < platformCodeArr.length; i++) {
					Map<String, Object> aa = new HashMap<String, Object>();
					String platformCode = platformCodeArr[i];
					aa.put("platformCode", platformCode);
					aa.put("appBlackListId", appBlackList.getId());

					if ((platformCode != null && !platformCode.equals(""))
							&& (appBlackList.getId() != null && !appBlackList.getId().equals(""))) {
						appNetBlackDao.saveAppBlackListPlatformInfo(aa);
					}
				}
			}

			// 保存映射
			String[] flatModelIdArr = appBlackList.getFlatModelIds().split(",");
			for (int i = 0; i < flatModelIdArr.length; i++) {
				Map<String, Object> aa = new HashMap<String, Object>();
				String flatModelId = flatModelIdArr[i];
				aa.put("flatModelId", flatModelId);
				aa.put("appBlackListId", appBlackList.getId());

				if ((flatModelId != null && !"".equals(flatModelId))
						&& (appBlackList.getId() != null && !"".equals(appBlackList.getId()))) {
					appNetBlackDao.saveAppBlackListFlatModel(aa);
				}

			}
		}
		appNetBlackDao.save(appBlackList);
		
	}

	@Override
	public void updateStatus(AppBlackList appBlackList) {
		appNetBlackDao.update(appBlackList);
		
	}

	@Override
	public void update(AppBlackList appBlackList) {
		appNetBlackDao.deleteAppBlackListPlatformCodeByAppBlackListId(appBlackList.getId());// 先删除
		appNetBlackDao.deleteAppBlackListFlatCodeByBlackListId(appBlackList.getId());

		String[] platformCodeArr = appBlackList.getPlatform_codes().split(",");

		if (platformCodeArr != null) {
			for (int i = 0; i < platformCodeArr.length; i++) {
				Map<String, Object> aa = new HashMap<String, Object>();
				String platformCode = platformCodeArr[i];
				aa.put("platformCode", platformCode);
				aa.put("appBlackListId", appBlackList.getId());

				if ((platformCode != null && !platformCode.equals(""))
						&& (appBlackList.getId() != null && !appBlackList.getId().equals(""))) {
					appNetBlackDao.saveAppBlackListPlatformInfo(aa);
				}
			}
		}

		// 保存映射
		String[] flatModelIdArr = appBlackList.getFlatModelIds().split(",");
		for (int i = 0; i < flatModelIdArr.length; i++) {
			Map<String, Object> aa = new HashMap<String, Object>();
			String flatModelId = flatModelIdArr[i];
			aa.put("flatModelId", flatModelId);
			aa.put("appBlackListId", appBlackList.getId());

			if ((flatModelId != null && !flatModelId.equals(""))
					&& (appBlackList.getId() != null && !appBlackList.getId().equals(""))) {
				appNetBlackDao.saveAppBlackListFlatModel(aa);
			}
		}
		appNetBlackDao.update(appBlackList);
		
	}

	@Override
	public void delete(String id) {
		appNetBlackDao.deleteById(id);
		
	}

	@Override
	public List<Map<String, Object>> apiQueryMap(Map<String, Object> map) {
		List<Map<String, Object>> list = appNetBlackDao.apiQueryMap(map);

		if ((map.get("modelCode") != null && !"".equals(map.get("modelCode"))) && map.get("platformCode") != null
				&& !"".equals(map.get("platformCode"))) {
			// list.addAll(appBlackListDao.queryAppBlackListByModelCodeAndPlatformCodeMap(map));
			String modelCodeStr = map.get("modelCode").toString();
			String platformCodeStr = map.get("platformCode").toString();

			flatModelService.valModel(modelCodeStr.trim());//

			List<Map<String, Object>> pkgList = appNetBlackDao.queryAppBlackListByModelCodeAndPlatformCodeMap(map);

			for (Map<String, Object> pkgMap : pkgList) {
				String pkgStr = pkgMap.get("pkg").toString();
				String name = pkgMap.get("name").toString();

				Map<String, Object> pkgParam = new HashMap<String, Object>();
				pkgParam.put("pkg", pkgStr);
				pkgParam.put("name", name);
				if (pkgMap.get("flat_model") != null && pkgMap.get("platform_code") != null) {
					// String pkg = pkgMap.get("pkg").toString();
					String platformCode = pkgMap.get("platform_code").toString();
					String flatMode = pkgMap.get("flat_model").toString();

					if (flatMode.contains(modelCodeStr) && platformCode.contains(platformCodeStr)) {
						list.add(pkgParam);
					}
				}

				if (pkgMap.get("flat_model") != null && pkgMap.get("platform_code") == null) {
					String flatMode = pkgMap.get("flat_model").toString();
					if (flatMode.contains(modelCodeStr)) {
						list.add(pkgParam);
					}
				}

				if (pkgMap.get("platform_code") != null && pkgMap.get("flat_model") == null) {
					String platformCode = pkgMap.get("platform_code").toString();

					if (platformCode.contains(platformCodeStr)) {
						list.add(pkgParam);
					}
				}
				// }
				// }}

				// list.addAll(appBlackListDao.queryAppBlackListByModelCodeAndPlatformCodeMap(map));
			}
		} else {
			if (map.get("modelCode") != null && !"".equals(map.get("modelCode"))) {

				flatModelService.valModel(map.get("modelCode").toString());//

				List<Map<String, Object>> pkgList = appNetBlackDao.queryAppBlackListByModelCodeMap(map);

				for (Map<String, Object> pkgMap : pkgList) {
					String pkgStr = pkgMap.get("pkg").toString();
					String name = pkgMap.get("name").toString();
					
					Map<String, Object> pkgParam = new HashMap<String, Object>();
					pkgParam.put("pkg", pkgStr);
					pkgParam.put("name", name);
					if (pkgMap.get("platform_code") == null) {
						list.add(pkgParam);
					}
				}
				// list.addAll(appBlackListDao.queryAppBlackListByModelCodeMap(map));
			}

			if (map.get("platformCode") != null && !"".equals(map.get("platformCode"))) {
				List<Map<String, Object>> pkgList = appNetBlackDao.queryAppBlackListByPlatformCodeMap(map);

				for (Map<String, Object> pkgMap : pkgList) {
					String pkgStr = pkgMap.get("pkg").toString();
					String name = pkgMap.get("name").toString();
					Map<String, Object> pkgParam = new HashMap<String, Object>();
					pkgParam.put("pkg", pkgStr);
					pkgParam.put("name", name);
					if (pkgMap.get("flat_model") == null) {
						list.add(pkgParam);
					}
				}

				// list.addAll(appBlackListDao.queryAppBlackListByPlatformCodeMap(map));
			}
		}

		return list;
	}
	
//	@Override
//	public List<AppBlackList> apiQuery(Map<String, Object> map) {
//		List<AppBlackList> list = appBlackListDao.apiQuery(map);
//
//		if (map.get("modelCode") != null && !"".equals(map.get("modelCode"))) {
//			list.addAll(appBlackListDao.queryAppBlackListByModelCode(map));
//		}
//
//		if (map.get("platformCode") != null && !"".equals(map.get("platformCode"))) {
//			list.addAll(appBlackListDao.queryAppBlackListByPlatformCode(map));
//		}
//
//		return list;
//	}
//

}
