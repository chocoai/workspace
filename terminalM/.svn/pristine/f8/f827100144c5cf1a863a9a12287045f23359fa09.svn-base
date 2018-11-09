package com.whty.assis.basicdata.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.basicdata.dao.DeviceInfoDao;
import com.whty.assis.basicdata.dao.SchoolLocationAreaDao;
import com.whty.assis.basicdata.model.SchoolLocationArea;
import com.whty.assis.basicdata.model.SchoolLocationLayer;
import com.whty.assis.basicdata.service.SchoolLocationAreaService;
import com.whty.assis.sysres.model.TaManageUserInfo;
import com.whty.common.util.ChineseCharToEn;
import com.whty.common.util.SysConstants;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

@Service
public class SchoolLocationAreaServiceImpl implements SchoolLocationAreaService {

	@Autowired
	private SchoolLocationAreaDao schoolLocationAreaDao;

	@Autowired
	private DeviceInfoDao deviceInfoDao;

	@Override
	public String saveSchoolLocationArea(HttpServletRequest request) {
		String layer = request.getParameter("layer");
		String schoolLocationId = request.getParameter("schoolLocationId");

		String attributeType = request.getParameter("attributeType");
//		String number = "11";// TODO 生成的
		String description = request.getParameter("description");
		String area = request.getParameter("area");

		String name = request.getParameter("name");
		
		ChineseCharToEn cte = new ChineseCharToEn();
		
		String number = cte.getAllFirstLetter(name);
		
		SchoolLocationArea bean = new SchoolLocationArea();
		if (layer != null) {
			Map<String, Object> schoolLocationLayerParam = new HashMap<String, Object>();
			schoolLocationLayerParam.put("schoolLocationId", schoolLocationId);
			schoolLocationLayerParam.put("layer", layer);
			SchoolLocationLayer schoolLocationLayer = schoolLocationAreaDao
					.getSchoolLocationLayer(schoolLocationLayerParam);

			bean.setSchoolLocationLayerId(Integer.valueOf(schoolLocationLayer.getId()));
		}

		bean.setName(name);
		bean.setSchoolLocationId(Integer.valueOf(schoolLocationId));
		bean.setCreateTime(new Date());
		TaManageUserInfo mUser = (TaManageUserInfo) request.getSession().getAttribute(SysConstants.SESSION_USER);
		bean.setCreator(mUser.getId());// TODO 从session中获取
		bean.setAttributeType(Integer.valueOf(attributeType));
		bean.setNumber(number);
		bean.setDescription(description);
		bean.setArea(area);
		bean.setUpdateTime(new Date());
		schoolLocationAreaDao.saveSchoolLocationArea(bean);

		return "success";
	}

	@Override
	public List<SchoolLocationArea> listByCondition(Map<String, Object> paramMap) {
		return schoolLocationAreaDao.listByCondition(paramMap);
	}

	@Override
	public HandlerResult querySchoolLocationAreaPage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(schoolLocationAreaDao.listByCondition(paramMap));
		return rs;
	}

	@Override
	public void updateSchoolLocationArea(SchoolLocationArea bean) {
		
		ChineseCharToEn cte = new ChineseCharToEn();
		
		String number = cte.getAllFirstLetter(bean.getName());
		
		bean.setNumber(number);
		
		schoolLocationAreaDao.update(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.SchoolLocationAreaService#
	 * deleteSchoolLocationArea(java.lang.String)
	 */
	@Override
	public String deleteSchoolLocationArea(String id) {

		SchoolLocationArea schoolLocationArea = schoolLocationAreaDao.loadById(Integer.valueOf(id));

		Map<String, Object> param = new HashMap<String, Object>();

		if (schoolLocationArea.getSchoolLocationId() != null) {
			param.put("schoolLocationId", schoolLocationArea.getSchoolLocationId());
		}

		if (schoolLocationArea.getSchoolLocationLayerId() != null) {
			param.put("schoolLocationAreaId", schoolLocationArea.getId());
		}

		//功能区有绑定设备不能删除
		List<Map<String, Object>> lst = deviceInfoDao.listDeviceSchoolLocation(param);

		if (lst != null && lst.size() != 0) {
			return "功能区下有绑定设备，不能删除";
		} 
		
		schoolLocationAreaDao.deleteById(Integer.valueOf(id));
		return "success";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.SchoolLocationAreaService#
	 * querySchoolLocationAreaPage(java.util.Map, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult querySchoolLocationAreaPage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(schoolLocationAreaDao.listByCondition(paraMap));
		rs.setPage(page);
		return rs;
	}

	/* (non-Javadoc)
	 * @see com.whty.assis.basicdata.service.SchoolLocationAreaService#loadById(java.lang.Integer)
	 */
	@Override
	public SchoolLocationArea loadById(Integer id) {
		return schoolLocationAreaDao.loadById(id);
	}

}
