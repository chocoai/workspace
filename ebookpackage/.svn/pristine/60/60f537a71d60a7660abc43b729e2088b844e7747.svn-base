package com.whty.ebp.manage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.common.util.GUIDGenerator;
import com.whty.ebp.base.service.BaseService;
import com.whty.ebp.manage.dao.FlatModelDao;
import com.whty.ebp.manage.model.FlatModel;
import com.whty.ebp.manage.service.FlatModelService;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

@Service
public class FlatModelServiceImpl extends BaseService implements FlatModelService {

	@Autowired
	private FlatModelDao flatModelDao;

	/**
	 * 系统添加新型号
	 * 
	 * @param model
	 */
	public void valModel(String modelCode) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("modelCode", modelCode);

		List<FlatModel> list = flatModelDao.listByCondition(param);

		if (list == null || list.size() == 0) {
			FlatModel flatModel = new FlatModel();

			flatModel.setId(GUIDGenerator.getGUID());
			flatModel.setCreateTime(new Date());
			flatModel.setMemo("自动添加的型号");
			flatModel.setModelCode(modelCode);
//			flatModelDao.save(flatModel);
		}

	}

	@Override
	public HandlerResult queryFlatModelPage(Map<String, Object> paramMap, PageContext page) {
		HandlerResult re = new HandlerResult();
		List<FlatModel> appList = flatModelDao.listByCondition(paramMap);
		re.setResultList(appList);
		re.setPage(page);
		return re;
	}

	@Override
	public FlatModel queryById(String id) {
		return flatModelDao.loadById(id);
	}

	@Override
	public void save(FlatModel flatModel) {
		flatModelDao.save(flatModel);
	}

	@Override
	public List<FlatModel> listByCondition(Map<String, Object> param) {
		return flatModelDao.listByCondition(param);
	}

	@Override
	public void update(FlatModel flatModel) {
		flatModelDao.update(flatModel);

	}

	@Override
	public void saveAppFlatModel(Map<String, Object> param) {
		flatModelDao.saveAppFlatModel(param);
	}

	@Override
	public List<Map<String, Object>> queryFlatModelByAppId(Map<String, Object> param) {
		return flatModelDao.queryFlatModelByAppId(param);
	}

	@Override
	public void delete(String id) {

		flatModelDao.deleteEbpAppFlatModelByFlatModelId(id);
		flatModelDao.deleteAppWhiteListFlatModeId(id);

		flatModelDao.deleteById(id);
	}

}
