package com.whty.mxbj.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.mxbj.api.dao.PenFirmvareDao;
import com.whty.mxbj.api.model.PenFirmvare;
import com.whty.mxbj.api.service.PenFirmvareService;
import com.whty.mxbj.base.exception.BusinessException;
import com.whty.mxbj.common.constants.ResultConstants;
import com.whty.mxbj.common.utils.CommonFunction;

import net.sf.json.JSONObject;

@Service
public class PenFirmvareServiceImpl implements PenFirmvareService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PenFirmvareDao penfirmvareDao;

	@Override
	public Map<String, Object> getNewVersionCheckParam(String body) throws BusinessException {
		logger.info(body);
		JSONObject reqJson = null;
		reqJson = JSONObject.fromObject((body == null || body.equals("")) ? "{}" : body);
		// 参数检验
		if (!CommonFunction.isNotNull(reqJson)) {
			throw new BusinessException("参数不存在");
		}
		System.out.println(body);
		Map<String, Object> para = new HashMap<String, Object>();

		para.put("penModel", reqJson.get("penModel"));// 笔型号

		// 必填字段检查
		CommonFunction.checkParam(para);

		return para;
	}

	@Override
	public Map<String, Object> getNewVersion(Map<String, Object> map) {
		// String penModel = map.get("penModel").toString();
		List<PenFirmvare> beanlst = penfirmvareDao.getNewVersionByPenModel(map);
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (beanlst.size() > 0) {
			PenFirmvare bean = beanlst.get(0);
			if (bean.getStatus().equals("0")) {
				resultMap.put("downUrl", beanlst.get(0).getDownUrl());
				resultMap.put("memo", beanlst.get(0).getMemo());
				resultMap.put("version", beanlst.get(0).getVersion());
			}
		}

		resultMap.put(ResultConstants.RESULT, ResultConstants.SUCCESS_CODE);
		resultMap.put(ResultConstants.RESULT_MSG, ResultConstants.SUCCESS_MESSAGE);
		return resultMap;
	}
}
