package com.whty.assis.basicdata.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.basicdata.dao.DeviceInfoDao;
import com.whty.assis.basicdata.dao.IdDao;
import com.whty.assis.basicdata.dao.SchoolLocationAreaDao;
import com.whty.assis.basicdata.dao.SchoolLocationDao;
import com.whty.assis.basicdata.model.SchoolLocation;
import com.whty.assis.basicdata.model.SchoolLocationArea;
import com.whty.assis.basicdata.model.SchoolLocationLayer;
import com.whty.assis.basicdata.service.SchoolLocationService;
import com.whty.assis.sysres.model.TaManageUserInfo;
import com.whty.common.util.PinyinUtils;
import com.whty.common.util.SysConfig;
import com.whty.common.util.SysConstants;
import com.whty.page.util.HandlerResult;

@Service
public class SchoolLocationServiceImpl implements SchoolLocationService {

	@Autowired
	private SchoolLocationDao schoolLocationDao;

	@Autowired
	private SchoolLocationAreaDao schoolLocationAreaDao;

	@Autowired
	private IdDao idDao;

	@Autowired
	private DeviceInfoDao deviceInfoDao;

	/**
	 * 根据汉字生成不重复的code编码
	 * 
	 * @param name
	 * @param allCode
	 * @return
	 */
	private String getCode(String name, String allCode) {
		String code = PinyinUtils.cn2py(name);
		if (allCode == null)
			return code;

		String[] allCodeStr = allCode.split(",");

		List<String> list = Arrays.asList(allCodeStr);

		boolean flag = true;
		while (flag) {
			if (!list.contains(code)) {
				flag = false;
			} else {
				code = code + "A";
			}
		}
		return code;
	}

	@Override
	public String saveSchoolLocation(HttpServletRequest request) {
		String name = request.getParameter("name");
		String ogLayer = request.getParameter("ogLayer");
		String ugLayer = request.getParameter("ugLayer");
		String memo = request.getParameter("memo");
		String schoolId = request.getParameter("schoolId");
		// 获取所有服务区编码
		String allCode = schoolLocationDao.getAllCode();

		Map<String, Object> idParam = new HashMap<String, Object>(2);
		idParam.put("databaseName", SysConfig.getStrValue("databaseName"));
		idParam.put("tableName", SysConfig.getStrValue("t_school_location"));
		Integer schoolLocationId = idDao.getId(idParam);

		SchoolLocation bean = new SchoolLocation();
		String code = getCode(name, allCode);
		bean.setName(name);
		TaManageUserInfo mUser = (TaManageUserInfo) request.getSession().getAttribute(SysConstants.SESSION_USER);
		bean.setCreator(mUser.getId());// TODO 登录做完后，从session中获取
		bean.setCreateTime(new Date());
		bean.setMemo(memo);
		bean.setSchoolId(Integer.valueOf(schoolId));
		bean.setCode(code);
		bean.setUpdateTime(new Date());
		schoolLocationDao.saveSchoolLocation(bean);

		if (ogLayer != null && ogLayer != "0") {
			for (int i = 1; i <= Integer.valueOf(ogLayer); i++) {
				SchoolLocationLayer schoolLocationLayer = new SchoolLocationLayer();
				schoolLocationLayer.setLayer(i);
				schoolLocationLayer.setSchoolLocationId(schoolLocationId);
				schoolLocationDao.saveSchoolLocationLayer(schoolLocationLayer);
			}
		}

		if (ugLayer != null && ugLayer != "0") {
			for (int i = 1; i <= Integer.valueOf(ugLayer); i++) {
				SchoolLocationLayer schoolLocationLayer = new SchoolLocationLayer();
				schoolLocationLayer.setLayer(-i);
				schoolLocationLayer.setSchoolLocationId(schoolLocationId);
				schoolLocationDao.saveSchoolLocationLayer(schoolLocationLayer);
			}
		}

		return "success";
	}

	@Override
	public List<SchoolLocation> listByCondition(Map<String, Object> paramMap) {
		return schoolLocationDao.listByCondition(paramMap);
	}

	@Override
	public HandlerResult querySchoolLocationPage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(schoolLocationDao.listByCondition(paramMap));
		return rs;
	}

	@Override
	public void updateSchoolLocation(SchoolLocation bean) {
		schoolLocationDao.updateSchoolLocation(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.SchoolLocationService#
	 * listSchoolLocationArea(java.util.Map)
	 */
	@Override
	public List<SchoolLocationArea> listSchoolLocationArea(Map<String, Object> paramMap) {
		return schoolLocationAreaDao.listByCondition(paramMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.SchoolLocationService#
	 * saveSchoolLocationArea(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String saveSchoolLocationArea(HttpServletRequest request) {
		String schoolLocationId = request.getParameter("schoolLocationId");
		String attributeType = request.getParameter("attributeType");
		String area = request.getParameter("area");
		String description = request.getParameter("description");
		String name = request.getParameter("name");
		// 获取所有服务区编码
		// String allCode = schoolLocationAreaDao.getAllCode();

		SchoolLocationArea bean = new SchoolLocationArea();
		bean.setSchoolLocationId(Integer.valueOf(schoolLocationId));
		bean.setArea(area);
		bean.setAttributeType(Integer.valueOf(attributeType));
		bean.setNumber("1");// TODO 编号，由服务功能区编号，加00--99流水自动生成；
		bean.setCreateTime(new Date());
		TaManageUserInfo mUser = (TaManageUserInfo) request.getSession().getAttribute(SysConstants.SESSION_USER);
		bean.setCreator(mUser.getId());// TODO 登录做完后，从session中获取
		bean.setDescription(description);
		bean.setName(name);
		schoolLocationAreaDao.saveSchoolLocationArea(bean);

		return "success";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.SchoolLocationService#
	 * updateSchoolLocationArea(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String updateSchoolLocationArea(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.SchoolLocationService#
	 * deleteSchoolLocationArea(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String deleteSchoolLocationArea(HttpServletRequest request) {

		String schoolLocationId = request.getParameter("schoolLocationId");
		String schoolLocationAreaId = request.getParameter("schoolLocationAreaId");
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("schoolLocationId", schoolLocationId);
		param.put("schoolLocationArea", schoolLocationAreaId);

		List<SchoolLocationArea> listSchoolAreaList = schoolLocationAreaDao.listByCondition(param);

		if (listSchoolAreaList != null && listSchoolAreaList.size() > 0) {
			return "00001";
		} else {
			schoolLocationAreaDao.deleteById(schoolLocationAreaId);
		}

		return "success";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.SchoolLocationService#
	 * deleteSchoolLocation(java.lang.String, java.lang.String) 删除区域
	 */
	@Override
	public String deleteSchoolLocation(String schoolLocationId, String layer) {
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("schoolLocationId", Integer.valueOf(schoolLocationId));

		if (StringUtils.isNotEmpty(layer)) {
			Map<String, Object> layerParamMap = new HashMap<String, Object>(2);
			layerParamMap.put("schoolLocationId", schoolLocationId);
			layerParamMap.put("layer", Integer.valueOf(layer));
			SchoolLocationLayer schoolLocationLayer = schoolLocationDao.listLayer(layerParamMap);

			param.put("schoolLocationLayerId", schoolLocationLayer.getId());
		}

		// 是否设备绑定服务器，有则不能删除
		List<Map<String, Object>> listAreaList = deviceInfoDao.listDeviceSchoolLocation(param);

		if (listAreaList != null && listAreaList.size() > 0) {
			return "服务区下有设备，不能删除!";
		}

		List<SchoolLocationArea> schoolLocationAreaList = schoolLocationAreaDao.listByCondition(param);

		if (schoolLocationAreaList != null && schoolLocationAreaList.size() > 0) {
			return "服务区下有功能区，不能删除!";
		}

		Map<String, Object> layerParam = new HashMap<String, Object>(2);
		layerParam.put("schoolLocationId", schoolLocationId);
		if (StringUtils.isNotEmpty(layer)) {// 删除楼层
			layerParam.put("layer", layer);
			schoolLocationDao.deleteSchoolLocationLayer(layerParam);
		} else {// 删除整层
			schoolLocationDao.deleteSchoolLocationLayer(layerParam);
			schoolLocationDao.deleteById(Integer.valueOf(schoolLocationId));
		}

		return SysConstants.SUCCESS;
	}

}
