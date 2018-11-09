package com.yhcrt.healthcloud.mall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhcrt.healthcloud.base.controller.BaseController;
import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.mall.entity.ServicePrice;
import com.yhcrt.healthcloud.mall.service.ServicePriceService;

@Controller
@RequestMapping("/servicePrice")
public class ServicePriceController extends BaseController {

	@Autowired
	private ServicePriceService servicePriceService;

	/**
	 * 新能或修改保存
	 * @param request
	 * @param response
	 * @param sp
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save")
	public Map<String, Object> save(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		ServicePrice servicePrice = new ServicePrice();
		if(StringUtils.isNotBlank(request.getParameter("cid"))){
			servicePrice.setCid(Integer.valueOf(request.getParameter("cid")));
		}
		if(StringUtils.isNotBlank(request.getParameter("serviceId"))){
			servicePrice.setServiceId(Integer.valueOf(request.getParameter("serviceId")));
		}
		if(StringUtils.isNotBlank(request.getParameter("price"))){
			servicePrice.setPrice(Double.valueOf(request.getParameter("price")));
		}
		if(StringUtils.isNotBlank(request.getParameter("oriPrice"))){
			servicePrice.setOriPrice(Double.valueOf(request.getParameter("oriPrice")));
		}
		if(StringUtils.isNotBlank(request.getParameter("level"))){
			servicePrice.setLevel(request.getParameter("level"));
		}
		if(StringUtils.isNotBlank(request.getParameter("unit"))){
			servicePrice.setUnit(request.getParameter("unit"));
		}
		if(StringUtils.isNotBlank(request.getParameter("type"))){
			servicePrice.setType(Integer.valueOf(request.getParameter("type")));
			if("0".equals(request.getParameter("type"))){
				if(StringUtils.isNotBlank(request.getParameter("serviceNum"))){
					servicePrice.setServiceNum(Integer.valueOf(request.getParameter("serviceNum")));
				}
				if(StringUtils.isNotBlank(request.getParameter("serviceUnit"))){
					servicePrice.setServiceUnit(Integer.valueOf(request.getParameter("serviceUnit")));
				}
			}
		}
		
		if(StringUtils.isNotBlank(request.getParameter("desct"))){
			servicePrice.setDesct(request.getParameter("desct"));
		}
		servicePrice.setStatus(Constants.STATUS_NORMAL); 
		try {
			if (servicePrice.getCid() != null) { // 表示修改
				servicePriceService.update(servicePrice);
			} else { // 表示新增
				servicePrice.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.SERVICE_PRICE));
				servicePriceService.insert(servicePrice);
			}
			List<ServicePrice> list = servicePriceService.queryPriceByServiceId(servicePrice.getServiceId());
			map.put("state", "1");
			map.put("msg", list);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("state", "-1");
			map.put("msg", "保存异常");
		}
		return map;
	}
	
	/**
	 * 逻辑删除
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delPrice")
	public Map<String, Object> delPrice(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		ServicePrice servicePrice = new ServicePrice();
		if(StringUtils.isNotBlank(request.getParameter("cid"))){
			servicePrice.setCid(Integer.valueOf(request.getParameter("cid")));
		}
		if(StringUtils.isNotBlank(request.getParameter("serviceId"))){
			servicePrice.setServiceId(Integer.valueOf(request.getParameter("serviceId")));
		}
		servicePrice.setStatus(Constants.STATUS_DISABLE); 
		try {
			// 逻辑删除
			int n = servicePriceService.updateByCid(servicePrice);
			if(n==1){
				List<ServicePrice> list = servicePriceService.queryPriceByServiceId(servicePrice.getServiceId());
				map.put("state", "1");
				map.put("msg", list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("state", "-1");
			map.put("msg", "保存异常");
		}
		return map;
	}
	
	/**
	 * 根据cid查询需要修改的数据
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryByCid")
	public ServicePrice queryByCid(HttpServletRequest request, HttpServletResponse response) {
		ServicePrice servicePrice = new ServicePrice();
		if(StringUtils.isNotBlank(request.getParameter("cid"))){
			servicePrice = servicePriceService.queryByCid(Integer.parseInt(request.getParameter("cid")));
		}
		return servicePrice;
	}

	/**
	 * 根据serviceId查询价格列表
	 * 
	 * @param request
	 * @param response
	 * @param serviceId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryPriceByServiceId", method = RequestMethod.POST)
	public List<ServicePrice> queryPriceByServiceId(HttpServletRequest request,
			HttpServletResponse response, Integer serviceId) {
		return servicePriceService.queryPriceByServiceId(serviceId);
	}

}