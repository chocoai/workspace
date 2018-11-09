package com.whty.ebp.manage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.common.util.CommonFunction;
import com.whty.common.util.GUIDGenerator;
import com.whty.ebp.manage.dao.AppBlackListDao;
import com.whty.ebp.manage.dao.FlatModelDao;
import com.whty.ebp.manage.model.AppBlackList;
import com.whty.ebp.manage.model.FlatModel;
import com.whty.ebp.manage.service.AppBlackListService;
import com.whty.ebp.manage.service.FlatModelService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

@Service
public class AppBlackListServiceImpl implements AppBlackListService {

	@Autowired
	private AppBlackListDao appBlackListDao;

	@Autowired
	private FlatModelService flatModelService;

	// @Override
	// public List<AppBlackList> query(Map<String, Object> map) {
	//
	// List<AppBlackList> list = appBlackListDao.queryAppBlackLst(map);
	//
	// if (map.get("modelCode") != null && !"".equals(map.get("modelCode"))) {
	// list.addAll(appBlackListDao.queryAppBlackListByModelCode(map));
	// }
	//
	// if (map.get("platformCode") != null &&
	// !"".equals(map.get("platformCode"))) {
	// list.addAll(appBlackListDao.queryAppBlackListByPlatformCode(map));
	// }
	//
	// return list;
	//
	// }

	@Override
	public HandlerResult queryAppBlackListPage(Map<String, Object> paramMap, PageContext page) {
		HandlerResult re = new HandlerResult();
		List<AppBlackList> appList = appBlackListDao.listByCondition(paramMap);
		re.setResultList(appList);
		re.setPage(page);
		return re;
	}

	@Override
	public void delete(String id) {
		appBlackListDao.deleteById(id);

	}

	@Override
	public void update(AppBlackList appBlackList) {
		appBlackListDao.deleteAppBlackListPlatformCodeByAppBlackListId(appBlackList.getId());// 先删除
		appBlackListDao.deleteAppBlackListFlatCodeByBlackListId(appBlackList.getId());

		String[] platformCodeArr = appBlackList.getPlatform_codes().split(",");

		if (platformCodeArr != null) {
			for (int i = 0; i < platformCodeArr.length; i++) {
				Map<String, Object> aa = new HashMap<String, Object>();
				String platformCode = platformCodeArr[i];
				aa.put("platformCode", platformCode);
				aa.put("appBlackListId", appBlackList.getId());

				if ((platformCode != null && !platformCode.equals(""))
						&& (appBlackList.getId() != null && !appBlackList.getId().equals(""))) {
					appBlackListDao.saveAppBlackListPlatformInfo(aa);
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
				appBlackListDao.saveAppBlackListFlatModel(aa);
			}
		}
		appBlackListDao.update(appBlackList);
	}

	@Override
	public void updateStatus(AppBlackList appBlackList) {
		appBlackListDao.update(appBlackList);

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
						appBlackListDao.saveAppBlackListPlatformInfo(aa);
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
					appBlackListDao.saveAppBlackListFlatModel(aa);
				}

			}
		}
		appBlackListDao.save(appBlackList);
	}

	@Override
	public AppBlackList queryById(String id) {
		return appBlackListDao.loadById(id);
	}

	@Override
	public List<Map<String, Object>> queryPlatformCodeByAppBlackListId(Map<String, Object> params) {
		return appBlackListDao.queryPlatformCodeByAppBlackListId(params);
	}

	@Override
	public List<AppBlackList> apiQuery(Map<String, Object> map) {
		List<AppBlackList> list = appBlackListDao.apiQuery(map);

		if (map.get("modelCode") != null && !"".equals(map.get("modelCode"))) {
			list.addAll(appBlackListDao.queryAppBlackListByModelCode(map));
		}

		if (map.get("platformCode") != null && !"".equals(map.get("platformCode"))) {
			list.addAll(appBlackListDao.queryAppBlackListByPlatformCode(map));
		}

		return list;
	}

	@Override
	public List<Map<String, Object>> apiQueryMap(Map<String, Object> map) {
		List<Map<String, Object>> list = appBlackListDao.apiQueryMap(map);

		if ((map.get("modelCode") != null && !"".equals(map.get("modelCode"))) && map.get("platformCode") != null
				&& !"".equals(map.get("platformCode"))) {
			// list.addAll(appBlackListDao.queryAppBlackListByModelCodeAndPlatformCodeMap(map));
			String modelCodeStr = map.get("modelCode").toString();
			String platformCodeStr = map.get("platformCode").toString();

			flatModelService.valModel(modelCodeStr.trim());//

			List<Map<String, Object>> pkgList = appBlackListDao.queryAppBlackListByModelCodeAndPlatformCodeMap(map);

			for (Map<String, Object> pkgMap : pkgList) {
				String pkgStr = pkgMap.get("pkg").toString();

				Map<String, Object> pkgParam = new HashMap<String, Object>();
				pkgParam.put("pkg", pkgStr);
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

				List<Map<String, Object>> pkgList = appBlackListDao.queryAppBlackListByModelCodeMap(map);

				for (Map<String, Object> pkgMap : pkgList) {
					String pkgStr = pkgMap.get("pkg").toString();
					Map<String, Object> pkgParam = new HashMap<String, Object>();
					pkgParam.put("pkg", pkgStr);
					if (pkgMap.get("platform_code") == null) {
						list.add(pkgParam);
					}
				}
				// list.addAll(appBlackListDao.queryAppBlackListByModelCodeMap(map));
			}

			if (map.get("platformCode") != null && !"".equals(map.get("platformCode"))) {
				List<Map<String, Object>> pkgList = appBlackListDao.queryAppBlackListByPlatformCodeMap(map);

				for (Map<String, Object> pkgMap : pkgList) {
					String pkgStr = pkgMap.get("pkg").toString();
					Map<String, Object> pkgParam = new HashMap<String, Object>();
					pkgParam.put("pkg", pkgStr);
					if (pkgMap.get("flat_model") == null) {
						list.add(pkgParam);
					}
				}

				// list.addAll(appBlackListDao.queryAppBlackListByPlatformCodeMap(map));
			}
		}

		return list;
	}

}
