package com.whty.ebp.manage.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.ebp.manage.dao.DerivativeAppLineDao;
import com.whty.ebp.manage.model.DerivativeAppLine;
import com.whty.ebp.manage.model.FlatModel;
import com.whty.ebp.manage.service.DerivativeAppLineService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

@Service
public class DerivativeAppLineServiceImpl implements DerivativeAppLineService {

	@Autowired
	private DerivativeAppLineDao derivativeAppLineDao;

	@Override
	public HandlerResult queryDerivativeAppLinePage(Map<String, Object> paramMap, PageContext page) {
		HandlerResult re = new HandlerResult();
		List<DerivativeAppLine> appList = derivativeAppLineDao.listByCondition(paramMap);
		re.setResultList(appList);
		re.setPage(page);
		return re;
	}

	@Override
	public void save(DerivativeAppLine derivativeAppLine) {
		derivativeAppLineDao.save(derivativeAppLine);
	}

}
