package com.whty.ebp.manage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.common.util.GUIDGenerator;
import com.whty.ebp.base.service.BaseService;
import com.whty.ebp.manage.dao.PlatformInfoDao;
import com.whty.ebp.manage.dao.WhiteListDao;
import com.whty.ebp.manage.model.WhiteList;
import com.whty.ebp.manage.service.WhiteListService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

@Service
public class WhiteListServiceImpl extends BaseService implements WhiteListService {

	@Autowired
	private WhiteListDao whiteListDao;

	@Autowired
	private PlatformInfoDao platformInfoDao;

	@Override
	public List<WhiteList> query(Map<String, Object> map) {

		List<WhiteList> list = whiteListDao.queryWhiteList(map);

		if (map.get("modelCode") != null && !"".equals(map.get("modelCode"))) {
			list.addAll(whiteListDao.queryWhiteListByModelCode(map));
		}

		if (map.get("platformCode") != null && !"".equals(map.get("platformCode"))) {
			list.addAll(whiteListDao.queryWhiteListByPlatformCode(map));
		}

		return list;

	}

	@Override
	public HandlerResult queryWhiteListPage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult re = new HandlerResult();
		List<WhiteList> appList = whiteListDao.listByCondition(paraMap);
		re.setResultList(appList);
		re.setPage(page);
		return re;
	}

	@Override
	public WhiteList queryById(String id) {
		return whiteListDao.queryById(id);
	}

	@Override
	public void save(WhiteList whiteList) {
		// 保存应用
		whiteList.setId(GUIDGenerator.getGUID());
		whiteList.setCreateTime(new Date());

		whiteList.setStatus("2");

		if ("-1".equals(whiteList.getFlatModelIds())) {
			whiteList.setFlatModelIds("");

		} else {
			String[] platformCodeArr = whiteList.getPlatform_codes().split(",");

			if (platformCodeArr != null) {
				for (int i = 0; i < platformCodeArr.length; i++) {
					Map<String, Object> aa = new HashMap<String, Object>();
					String platformCode = platformCodeArr[i];
					aa.put("platformCode", platformCode);
					aa.put("appWhiteListId", whiteList.getId());

					if ((platformCode != null && !platformCode.equals(""))
							&& (whiteList.getId() != null && !whiteList.getId().equals(""))) {
						platformInfoDao.saveAppWhiteListPlatformInfo(aa);
					}
				}
			}

			// 保存映射
			String[] flatModelIdArr = whiteList.getFlatModelIds().split(",");
			for (int i = 0; i < flatModelIdArr.length; i++) {
				Map<String, Object> aa = new HashMap<String, Object>();
				String flatModelId = flatModelIdArr[i];
				aa.put("flatModelId", flatModelId);
				aa.put("appWhiteListId", whiteList.getId());

				if ((flatModelId != null && !"".equals(flatModelId))
						&& (whiteList.getId() != null && !"".equals(whiteList.getId()))) {
					whiteListDao.saveWhiteListFlatModel(aa);
				}

			}
		}
		whiteListDao.save(whiteList);

	}

	@Override
	public void update(WhiteList whiteList) {
		whiteListDao.deleteMapByAppWhiteListId(whiteList.getId());// 先删除
		platformInfoDao.deleteByWhiteListId(whiteList.getId());

		String[] platformCodeArr = whiteList.getPlatform_codes().split(",");

		if (platformCodeArr != null) {
			for (int i = 0; i < platformCodeArr.length; i++) {
				Map<String, Object> aa = new HashMap<String, Object>();
				String platformCode = platformCodeArr[i];
				aa.put("platformCode", platformCode);
				aa.put("appWhiteListId", whiteList.getId());

				if ((platformCode != null && !platformCode.equals(""))
						&& (whiteList.getId() != null && !whiteList.getId().equals(""))) {
					platformInfoDao.saveAppWhiteListPlatformInfo(aa);
				}
			}
		}

		// 保存映射
		String[] flatModelIdArr = whiteList.getFlatModelIds().split(",");
		for (int i = 0; i < flatModelIdArr.length; i++) {
			Map<String, Object> aa = new HashMap<String, Object>();
			String flatModelId = flatModelIdArr[i];
			aa.put("flatModelId", flatModelId);
			aa.put("appWhiteListId", whiteList.getId());

			if ((flatModelId != null && !flatModelId.equals(""))
					&& (whiteList.getId() != null && !whiteList.getId().equals(""))) {
				whiteListDao.saveWhiteListFlatModel(aa);
			}
		}
		whiteListDao.update(whiteList);

	}

	@Override
	public void delete(String id) {
		whiteListDao.deleteMapByAppWhiteListId(id);// 删除
		platformInfoDao.deleteByWhiteListId(id);

		whiteListDao.deleteById(id);
	}

	@Override
	public void updateStatus(WhiteList whiteList) {
		whiteListDao.update(whiteList);
	}

}
