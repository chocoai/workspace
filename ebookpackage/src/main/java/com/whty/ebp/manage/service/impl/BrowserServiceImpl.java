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
import com.whty.ebp.manage.dao.BrowserDao;
import com.whty.ebp.manage.dao.PlatformInfoDao;
import com.whty.ebp.manage.model.Browser;
import com.whty.ebp.manage.model.WhiteList;
import com.whty.ebp.manage.service.BrowserService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

@Service
public class BrowserServiceImpl extends BaseService implements BrowserService {

	@Autowired
	private BrowserDao browserDao;

	@Override
	public List<Browser> queryWhiteList(Map<String, Object> map) {
		List<Browser> list = new ArrayList<Browser>();
		List<Browser> appList = browserDao.whiteListByCondition(map);
		for (Browser br : appList) {
			if (map.get("modelCode").equals(br.getFlatModelIds())) {
				list.addAll(browserDao.queryWhiteListByModelCode(map));
				break;
			}
		}
		list.addAll(browserDao.queryWhiteListNotBy());
		return list;
	}

	@Override
	public String queryWhiteVersion() {
		Browser vd = browserDao.queryWhiteVersionByModelCode();
		String version;
		if (vd == null || vd.getUpdateTime() == null || "".equals(vd.getUpdateTime())) {
			version = "0.0";
		} else {
			version = TimeUtils.dateTimeToStr(vd.getUpdateTime(), "yyyyMMddHHmmss");
		}
		return version;
	}

	@Override
	public String queryLabelVersion() {
		Browser vd = browserDao.queryLabelVersionByModelCode();
		String version;
		if (vd == null || vd.getUpdateTime() == null || "".equals(vd.getUpdateTime())) {
			version = "0.0";
		} else {
			version = TimeUtils.dateTimeToStr(vd.getUpdateTime(), "yyyyMMddHHmmss");
		}
		return version;
	}

	@Override
	public HandlerResult queryWhiteListPage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult re = new HandlerResult();
		List<Browser> appList = browserDao.whiteListByCondition(paraMap);
		re.setResultList(appList);
		re.setPage(page);
		return re;
	}

	@Override
	public List<Browser> queryLabel(Map<String, Object> map) {
		List<Browser> list = new ArrayList<Browser>();
		List<Browser> appList = browserDao.labelByCondition(map);
		for (Browser br : appList) {
			if (map.get("modelCode").equals(br.getFlatModelIds())) {
				list.addAll(browserDao.queryLabelByModelCode(map));
				break;
			}
		}
		list.addAll(browserDao.queryLabelNotBy());
		return list;
	}

	@Override
	public HandlerResult queryLabelPage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult re = new HandlerResult();
		List<Browser> appList = browserDao.labelByCondition(paraMap);
		re.setResultList(appList);
		re.setPage(page);
		return re;
	}

	@Override
	public void saveWhite(Browser browser) {
		browser.setId(GUIDGenerator.getUUID32());
		browser.setUpdateTime(new Date());
		if ("-1".equals(browser.getFlatModelIds())) {
			browser.setFlatModelIds("");
		} else {
			// 保存映射
			String[] flatModelIdArr = browser.getFlatModelIds().split(",");
			for (int i = 0; i < flatModelIdArr.length; i++) {
				Map<String, Object> aa = new HashMap<String, Object>();
				String flatModelId = flatModelIdArr[i];
				aa.put("flatModelId", flatModelId);
				aa.put("browserId", browser.getId());

				if ((flatModelId != null && !"".equals(flatModelId))
						&& (browser.getId() != null && !"".equals(browser.getId()))) {
					browserDao.saveBrowserFlatModel(aa);
				}

			}
		}
		browserDao.saveWhite(browser);
	}

	@Override
	public void deleteWhite(Map<String, String> map) {
		int count = browserDao.queryCountById(map.get("id"));
		browserDao.deleteByMap(map);// 删除
		if (count <= 1) {
			browserDao.deleteBrowserWhiteById(map.get("id"));
			browserDao.deleteBrowserLabelById(map.get("id"));
		}
		Map<String, Object> aa = new HashMap<String, Object>();
		aa.put("updateTime", new Date());
		browserDao.updateWhiteVersion(aa);
	}

	@Override
	public Browser queryById(String id) {
		return browserDao.queryById(id);
	}

	@Override
	public void updateWhite(Browser browser) {
		browserDao.deleteBrowserById(browser.getId());
		browser.setUpdateTime(new Date());
		if ("-1".equals(browser.getFlatModelIds())) {
			browser.setFlatModelIds("");
		} else {
			// 保存映射
			String[] flatModelIdArr = browser.getFlatModelIds().split(",");
			for (int i = 0; i < flatModelIdArr.length; i++) {
				Map<String, Object> aa = new HashMap<String, Object>();
				String flatModelId = flatModelIdArr[i];
				aa.put("flatModelId", flatModelId);
				aa.put("browserId", browser.getId());

				if ((flatModelId != null && !"".equals(flatModelId))
						&& (browser.getId() != null && !"".equals(browser.getId()))) {
					browserDao.saveBrowserFlatModel(aa);
				}

			}
		}
		browserDao.updateWhite(browser);
	}

	@Override
	public void saveLabel(Browser browser) {
		browser.setId(GUIDGenerator.getUUID32());
		browser.setUpdateTime(new Date());
		if ("-1".equals(browser.getFlatModelIds())) {
			browser.setFlatModelIds("");
		} else {
			// 保存映射
			String[] flatModelIdArr = browser.getFlatModelIds().split(",");
			for (int i = 0; i < flatModelIdArr.length; i++) {
				Map<String, Object> aa = new HashMap<String, Object>();
				String flatModelId = flatModelIdArr[i];
				aa.put("flatModelId", flatModelId);
				aa.put("browserId", browser.getId());

				if ((flatModelId != null && !"".equals(flatModelId))
						&& (browser.getId() != null && !"".equals(browser.getId()))) {
					browserDao.saveBrowserFlatModel(aa);
				}

			}
		}
		browserDao.saveLabel(browser);

	}

	@Override
	public void deleteLabel(Map<String, String> map) {
		int count = browserDao.queryCountById(map.get("id"));
		browserDao.deleteByMap(map);// 删除
		if (count <= 1) {
			browserDao.deleteBrowserLabelById(map.get("id"));
		}
		Map<String, Object> aa = new HashMap<String, Object>();
		aa.put("updateTime", new Date());
		browserDao.updateLabelVersion(aa);

	}

	@Override
	public void updateLabel(Browser browser) {
		// TODO Auto-generated method stub

	}

}
