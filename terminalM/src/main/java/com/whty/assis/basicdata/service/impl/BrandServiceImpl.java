/**
 * 
 */
package com.whty.assis.basicdata.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.basicdata.dao.BrandDao;
import com.whty.assis.basicdata.dao.DeviceInfoDao;
import com.whty.assis.basicdata.model.Brand;
import com.whty.assis.basicdata.model.DeviceInfo;
import com.whty.assis.basicdata.service.BrandService;
import com.whty.assis.sysres.model.TaManageUserInfo;
import com.whty.common.util.SysConstants;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * @author zhangzheng
 * @date 2018年3月31日
 */
@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandDao brandDao;

	@Autowired
	private DeviceInfoDao deivceInfoDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.BrandService#saveBrand(com.whty.assis.
	 * basicdata.model.Brand)
	 */
	@Override
	public String saveBrand(HttpServletRequest request) {

		String brandName = request.getParameter("brandName");
		String modelName = request.getParameter("modelName");
		String terminalDeviceTypeId = request.getParameter("terminalDeviceTypeId");
		String vendor = request.getParameter("vendor");
		Brand bean = new Brand();
		bean.setBrandName(brandName);
		bean.setModelName(modelName);
		bean.setTerminalDeviceTypeId(Integer.valueOf(terminalDeviceTypeId));
		bean.setUpdateTime(new Date());
		bean.setCreateTime(new Date());
		TaManageUserInfo mUser = (TaManageUserInfo) request.getSession().getAttribute(SysConstants.SESSION_USER);
		bean.setCreator(mUser.getId());
		bean.setVendor(vendor);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("brandName", bean.getBrandName());
		param.put("modelName", bean.getModelName());
		param.put("terminalDeviceTypeId", bean.getTerminalDeviceTypeId());

		List<Brand> lst = brandDao.listByCondition(param);

		if (lst != null && lst.size() > 0) {
			return "已经存在该品牌型号";
		} else {
			brandDao.save(bean);
			return SysConstants.SUCCESS;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.BrandService#listByCondition(java.util.
	 * Map)
	 */
	@Override
	public List<Brand> listByCondition(Map<String, Object> paramMap) {
		return brandDao.listByCondition(paramMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.BrandService#updateBrand(com.whty.assis.
	 * basicdata.model.Brand)
	 */
	@Override
	public String updateBrand(Brand bean) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("brandName", bean.getBrandName());
		param.put("modelName", bean.getModelName());
		param.put("terminalDeviceTypeId", bean.getTerminalDeviceTypeId());

		List<Brand> lst = brandDao.listByCondition(param);

		if (lst != null && lst.size() > 0) {
			return "0001";
		} else {
			brandDao.update(bean);
			return SysConstants.SUCCESS;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.whty.assis.basicdata.service.BrandService#deleteBrand(java.lang.
	 * Integer)
	 */
	@Override
	public String deleteBrand(Integer id) {
		// TODO
		Map<String, Object> param = new HashMap<String, Object>(1);
		param.put("brandId", id);
		List<DeviceInfo> deviceInfolst = deivceInfoDao.listByCondition(param);

		if (deviceInfolst != null && deviceInfolst.size() > 0) {
			return "该供应商下有设备不能删除";
		} else {
			brandDao.deleteById(id);
			return SysConstants.SUCCESS;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.BrandService#queryBrandPage(java.util.
	 * Map)
	 */
	@Override
	public HandlerResult queryBrandPage(Map<String, Object> paramMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(brandDao.listByCondition(paramMap));
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.whty.assis.basicdata.service.BrandService#queryBrandPage(java.util.
	 * Map, com.whty.page.PageContext)
	 */
	@Override
	public HandlerResult queryBrandPage(Map<String, Object> paraMap, PageContext page) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(brandDao.listByCondition(paraMap));
		rs.setPage(page);
		return rs;
	}

	/* (non-Javadoc)
	 * @see com.whty.assis.basicdata.service.BrandService#loadById(java.lang.Integer)
	 */
	@Override
	public Brand loadById(Integer id) {
		return brandDao.loadById(id);
	}

}
