package com.whty.ebp.manage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.common.util.GUIDGenerator;
import com.whty.ebp.manage.dao.NetWorkBlackListDao;
import com.whty.ebp.manage.model.NetWorkBlackList;
import com.whty.ebp.manage.service.FlatModelService;
import com.whty.ebp.manage.service.NetWorkBlackListService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

@Service
public class NetWorkBlackListServiceImpl implements NetWorkBlackListService {

	@Autowired
	private NetWorkBlackListDao netWorkBlackListDao;

	@Autowired
	private FlatModelService flatModelService;

	@Override
	public void save(NetWorkBlackList netWorkBlackList) {
		// 保存应用
		netWorkBlackList.setId(GUIDGenerator.getGUID());
		netWorkBlackList.setCreateTime(new Date());

		netWorkBlackList.setStatus("2");

		if ("-1".equals(netWorkBlackList.getFlatModelIds())) {
			netWorkBlackList.setFlatModelIds("");

		} else {
			String[] platformCodeArr = netWorkBlackList.getPlatform_codes().split(",");

			if (platformCodeArr != null) {
				for (int i = 0; i < platformCodeArr.length; i++) {
					Map<String, Object> aa = new HashMap<String, Object>();
					String platformCode = platformCodeArr[i];
					aa.put("platformCode", platformCode);
					aa.put("netWorkBlackListId", netWorkBlackList.getId());

					if ((platformCode != null && !platformCode.equals(""))
							&& (netWorkBlackList.getId() != null && !netWorkBlackList.getId().equals(""))) {
						netWorkBlackListDao.saveNetWorkBlackListPlatformInfo(aa);
					}
				}
			}

			// 保存映射
			String[] flatModelIdArr = netWorkBlackList.getFlatModelIds().split(",");
			for (int i = 0; i < flatModelIdArr.length; i++) {
				Map<String, Object> aa = new HashMap<String, Object>();
				String flatModelId = flatModelIdArr[i];
				aa.put("flatModelId", flatModelId);
				aa.put("netWorkBlackListId", netWorkBlackList.getId());

				if ((flatModelId != null && !"".equals(flatModelId))
						&& (netWorkBlackList.getId() != null && !"".equals(netWorkBlackList.getId()))) {
					netWorkBlackListDao.saveNetWorkBlackListFlatModel(aa);
				}

			}
		}
		netWorkBlackListDao.save(netWorkBlackList);
	}

	@Override
	public HandlerResult queryNetWorkBlackListPage(Map<String, Object> paramMap, PageContext page) {
		HandlerResult re = new HandlerResult();
		List<NetWorkBlackList> appList = netWorkBlackListDao.listByCondition(paramMap);
		re.setResultList(appList);
		re.setPage(page);
		return re;
	}

	@Override
	public NetWorkBlackList loadById(String id) {
		return netWorkBlackListDao.loadById(id);
	}

	@Override
	public List<Map<String, Object>> queryFlatModelByNetWorkBlackListId(Map<String, Object> params) {
		return netWorkBlackListDao.queryFlatModelByNetWorkBlackListId(params);
	}

	@Override
	public List<Map<String, Object>> queryPlatformCodeByNetWorkBlackListId(Map<String, Object> params) {
		return netWorkBlackListDao.queryPlatformCodeByNetWorkBlackListId(params);
	}

	@Override
	public void updateStatus(NetWorkBlackList netWorkBlackList) {
		netWorkBlackListDao.update(netWorkBlackList);
	}

	@Override
	public void delete(String id) {
		netWorkBlackListDao.deleteNetWorkBlackListFlatModeByBlackListId(id);// 先删除
		netWorkBlackListDao.deleteNetWorkBlackListPlatformCodeByBlackListId(id);

		netWorkBlackListDao.deleteById(id);

	}

	@Override
	public void update(NetWorkBlackList netWorkBlackList) {
		netWorkBlackListDao.deleteNetWorkBlackListFlatModeByBlackListId(netWorkBlackList.getId());// 先删除
		netWorkBlackListDao.deleteNetWorkBlackListPlatformCodeByBlackListId(netWorkBlackList.getId());

		String[] platformCodeArr = netWorkBlackList.getPlatform_codes().split(",");

		if (platformCodeArr != null) {
			for (int i = 0; i < platformCodeArr.length; i++) {
				Map<String, Object> aa = new HashMap<String, Object>();
				String platformCode = platformCodeArr[i];
				aa.put("platformCode", platformCode);
				aa.put("netWorkBlackListId", netWorkBlackList.getId());

				if ((platformCode != null && !platformCode.equals(""))
						&& (netWorkBlackList.getId() != null && !netWorkBlackList.getId().equals(""))) {
					netWorkBlackListDao.saveNetWorkBlackListPlatformInfo(aa);
				}
			}
		}

		// 保存映射
		String[] flatModelIdArr = netWorkBlackList.getFlatModelIds().split(",");
		for (int i = 0; i < flatModelIdArr.length; i++) {
			Map<String, Object> aa = new HashMap<String, Object>();
			String flatModelId = flatModelIdArr[i];
			aa.put("flatModelId", flatModelId);
			aa.put("netWorkBlackListId", netWorkBlackList.getId());
			if ((flatModelId != null && !flatModelId.equals(""))
					&& (netWorkBlackList.getId() != null && !netWorkBlackList.getId().equals(""))) {
				netWorkBlackListDao.saveNetWorkBlackListFlatModel(aa);

			}
		}
		netWorkBlackListDao.update(netWorkBlackList);

	}

	@Override
	public List<NetWorkBlackList> apiQuery(Map<String, Object> map) {
		List<NetWorkBlackList> list = netWorkBlackListDao.apiQuery(map);

		if (map.get("modelCode") != null && !"".equals(map.get("modelCode"))) {
			list.addAll(netWorkBlackListDao.queryNetWorkBlackListByModelCode(map));
		}

		if (map.get("platformCode") != null && !"".equals(map.get("platformCode"))) {
			list.addAll(netWorkBlackListDao.queryNetWorkBlackListByPlatformCode(map));
		}

		return list;

	}

	@Override
	public List<Map<String, Object>> apiQueryMap(Map<String, Object> map) {
		List<Map<String, Object>> list = netWorkBlackListDao.apiQueryMap(map);

		if (map.get("modelCode") != null && !"".equals(map.get("modelCode")) && map.get("platformCode") != null
				&& !"".equals(map.get("platformCode"))) {

			flatModelService.valModel(map.get("modelCode").toString().trim());

			String modelCodeStr = map.get("modelCode").toString();
			String platformCodeStr = map.get("platformCode").toString();

			List<Map<String, Object>> newWorkList = netWorkBlackListDao
					.queryNetWorkBlackListByPlatformCodeAndModelCode(map);

			for (Map<String, Object> netWorkMap : newWorkList) {
				// String pkgStr = pkg.get("pkg").toString();
				String net_work_address = netWorkMap.get("net_work_address").toString();

				Map<String, Object> netWrokAddressParam = new HashMap<String, Object>();

				netWrokAddressParam.put("net_work_address", net_work_address);

				if (netWorkMap.get("flat_model") != null && netWorkMap.get("platform_code") != null) {
					String flatMode = netWorkMap.get("flat_model").toString();
					String platformCode = netWorkMap.get("platform_code").toString();

					if (flatMode.contains(modelCodeStr) && platformCode.contains(platformCodeStr)) {
						list.add(netWrokAddressParam);
					}
				}

				if (netWorkMap.get("flat_model") != null && netWorkMap.get("platform_code") == null) {
					String flatMode = netWorkMap.get("flat_model").toString();
					if (flatMode.contains(modelCodeStr)) {
						list.add(netWrokAddressParam);
					}
				}

				if (netWorkMap.get("platform_code") != null && netWorkMap.get("flat_model") == null) {
					String platformCode = netWorkMap.get("platform_code").toString();

					if (platformCode.contains(platformCodeStr)) {
						list.add(netWrokAddressParam);
					}
				}
			}

			// }

			// list.addAll(netWorkBlackListDao.queryNetWorkBlackListByPlatformCodeAndModelCode(map));
		} else {
			if (map.get("modelCode") != null && !"".equals(map.get("modelCode"))) {

				flatModelService.valModel(map.get("modelCode").toString());

				List<Map<String, Object>> newWorkList = netWorkBlackListDao.queryNetWorkBlackListByModelCodeMap(map);

				for (Map<String, Object> netWorkMap : newWorkList) {
					String net_work_address = netWorkMap.get("net_work_address").toString();
					Map<String, Object> netWrokAddressParam = new HashMap<String, Object>();
					netWrokAddressParam.put("net_work_address", net_work_address);

					if (netWorkMap.get("platform_code") == null) {
						list.add(netWrokAddressParam);
					}

				}
				// list.addAll(netWorkBlackListDao.queryNetWorkBlackListByModelCodeMap(map));
			}

			if (map.get("platformCode") != null && !"".equals(map.get("platformCode"))) {
				List<Map<String, Object>> newWorkList = netWorkBlackListDao.queryNetWorkBlackListByPlatformCodeMap(map);

				for (Map<String, Object> netWorkMap : newWorkList) {
					String net_work_address = netWorkMap.get("net_work_address").toString();
					Map<String, Object> netWrokAddressParam = new HashMap<String, Object>();
					netWrokAddressParam.put("net_work_address", net_work_address);

					if (netWorkMap.get("flat_model") == null) {
						list.add(netWrokAddressParam);
					}

				}

				// list.addAll(netWorkBlackListDao.queryNetWorkBlackListByPlatformCodeMap(map));
			}
		}

		return list;
	}

}
