package com.whty.mxbj.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.whty.mxbj.api.dao.UpgradeDao;
import com.whty.mxbj.api.model.Upgrade;
import com.whty.mxbj.api.service.UpgradeService;

@Component("upgradeService")
public class UpgradeServiceImpl implements UpgradeService {
	@Autowired
	private UpgradeDao upgradeDao;

	@Override
	public void update(Upgrade upgrade) {
		upgradeDao.update(upgrade);

	}

	@Override
	public Upgrade getUpgrade() {
		List<Upgrade> list = upgradeDao.queryUpgrade(new HashMap<String, Object>());
		if (list != null && list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	@Override
	public List<Upgrade> queryUpgrade(Map<String, Object> param) {
		return upgradeDao.queryUpgrade(param);
	}

	@Override
	public List<Upgrade> queryPadUpgrade(HashMap<String, Object> hashMap) {
		return upgradeDao.queryPadUpgrade(hashMap);
	}

	@Override
	public String getpadUpgrade() {
		
		return upgradeDao.getpadUpgrade();
	}

}
