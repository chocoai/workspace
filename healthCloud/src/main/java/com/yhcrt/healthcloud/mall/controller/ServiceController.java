package com.yhcrt.healthcloud.mall.controller;	
	
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yhcrt.healthcloud.base.controller.BaseController;
import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.mall.entity.Service;
import com.yhcrt.healthcloud.mall.entity.ServicePrice;
import com.yhcrt.healthcloud.mall.entity.YwImage;
import com.yhcrt.healthcloud.mall.service.ServicePriceService;
import com.yhcrt.healthcloud.mall.service.ServiceService;
import com.yhcrt.healthcloud.mall.service.YwImageService;
import com.yhcrt.healthcloud.system.entity.SysDictionary;
import com.yhcrt.healthcloud.system.mapper.SysDictionaryMapper;
import com.yhcrt.healthcloud.system.service.SysSequenceService;
import com.yhcrt.healthcloud.util.UploadUtils;
	
/* @Description: 商品管理模块	
 * @version 1.0     2017年8月28日	
 * @author jimmy	
*/	
@Controller 
@RequestMapping("/service")	
public class ServiceController extends BaseController{
    	
    @Autowired	
    private ServiceService serviceService;
    @Autowired  
    private YwImageService ywImageService;
    @Autowired	
    private SysSequenceService sysSequenceService;
    @Autowired	
    private SysDictionaryMapper sysDictionaryMapper;
    @Autowired
	private ServicePriceService servicePriceService;
    
    /**
     * @Title: getServiceList
     * @Description: 根据条件查询服务，返回列表页
     * @param request
     * @param response
     * @param orgId
     * @param serviceType
     * @param serviceStatus
     * @param serviceName
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)	
    public String getServiceList(HttpServletRequest request,HttpServletResponse response, Service service){
		//列表查询
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("providerId", service.getProviderId());
        params.put("serviceType", service.getServiceType());
        params.put("serviceStatus", service.getServiceStatus());
        params.put("serviceName", service.getServiceName());
        params.put("status", Constants.STATUS_NORMAL);
        List<Service> serviceList = serviceService.getAll(params);	
        request.setAttribute("serviceList",serviceList);	
        request.setAttribute("service",service);
        request.setAttribute("providerList", getProList());
        if(StringUtils.isNoneBlank(service.getServiceCategory())){
			request.setAttribute("serviceTypeList", getTypeItem("service_category", service.getServiceCategory()));
		}
        return "mall/service/list";	
    }	

	/**
     * @Title: editService
     * @Description: 调到编辑服务信息页面，如果goodsId为null，则是新增操作，携带id返回到编辑页面
     *                  否则从数据库中查询出服务信息及服务关联的主图，携带这些信息返回到编辑页面
     * @param request
     * @param response
     * @param serviceId
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)	
    public String editService(HttpServletRequest request,HttpServletResponse response,Integer serviceId){	
    	Service service = new Service();
    	List<ServicePrice> priceList = new ArrayList<ServicePrice>();
    	if(null == serviceId){
            serviceId = sysSequenceService.getSequenceValue(Constants.SequenceName.SERVICE);
            request.setAttribute("serviceId", serviceId);
            request.setAttribute("serviceTypeList", new ArrayList<SysDictionary>());	
        }else{	
            List<YwImage> imgList = ywImageService.getByRefId(serviceId, "service");
            request.setAttribute("imgList", imgList);
            service = serviceService.get(serviceId);	
            priceList = servicePriceService.queryPriceByServiceId(serviceId);
            request.setAttribute("serviceTypeList", getTypeItem("service_category", service.getServiceCategory()));	
        }	
        request.setAttribute("service", service);
        request.setAttribute("priceList", priceList);
        request.setAttribute("priceNum", priceList.size());
        request.setAttribute("providerList", getProList());
        return "mall/service/edit";	
    }	
    
    /**
     * @Title: saveService
     * @Description: 根据editType参数来决定请求是新增还是修改
     * @param request
     * @param response
     * @param attr
     * @param service
     * @param titleImgFile
     * @param editType
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveService(HttpServletRequest request, HttpServletResponse response,
            RedirectAttributes attr, Service service, String editType){
    	String titleImg = UploadUtils.uploadFile(request, "titleImgFile", "service");
    	if (StringUtils.isNoneBlank(titleImg)){
    		service.setTitleImg(titleImg);
		}
        service.setStatus(Constants.STATUS_NORMAL);
        service.setUpdateTime(sdf.format(new Date()));
        //获取最新的最低的价格
        List<ServicePrice> priceList = servicePriceService.queryPriceByServiceId(service.getServiceId());
        service.setOriginalPrice(priceList.get(0).getOriPrice());
        service.setUnitPrice(priceList.get(0).getPrice().toString());
        service.setUnit(priceList.get(0).getUnit());
        Integer i = 0;
        if(!StringUtils.isBlank(service.getUnit())){
        	service.setCext1(sysDictionaryMapper.selectByDictValue(service.getUnit()));
        }
        if("edit".equals(editType)){
            //这里的upd调用的是updateByPrimaryKeySelective方法，为null的字段不会更新
            i = serviceService.upd(service);
        }else if("new".equals(editType)){
            service.setCreateTime(sdf.format(new Date()));
            service.setCreateUser(getLoginUser().getUserId().toString());
            i = serviceService.add(service);
        }
        if(i == 1){
            attr.addFlashAttribute("state", "SUCCESS");
        }else{
            attr.addFlashAttribute("state", "FALSE");
        }
        return "redirect:list"; 
    }	
    	
    /**
     * @Title: deleteService
     * @Description: 删除服务
     * @param request
     * @param response
     * @param attr
     * @param serviceId
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)	
    public String deleteService(HttpServletRequest request, HttpServletResponse response,
            RedirectAttributes attr, Integer serviceId){	

        Integer i = 0;
        Service service = serviceService.get(serviceId);
        service.setUpdateTime(sdf.format(new Date()));
        service.setStatus(Constants.STATUS_DISABLE);
        i = serviceService.upd(service);
        if(i == 1){
            attr.addFlashAttribute("state", "SUCCESS");
        }else{
            attr.addFlashAttribute("state", "FALSE");
        }
        return "redirect:list"; 
        	
    }	
    
    /**
     * @Title: batchDeleteService
     * @Description: 批量删除服务的操作
     * @param request
     * @param response
     * @param attr
     * @param serviceIds
     */
    @RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
    @ResponseBody
    public void batchDeleteService(HttpServletRequest request, HttpServletResponse response, 
            RedirectAttributes attr, Integer[] serviceIds){ 

        for(Integer serviceId:serviceIds){
            Service service = serviceService.get(serviceId);
            service.setUpdateTime(sdf.format(new Date()));
            service.setStatus(Constants.STATUS_DISABLE);
            serviceService.upd(service);
        }
            
    }
    
    /**
     * @Title: changeServiceStatus
     * @Description: 对服务的上架下架的操作
     * @param request
     * @param response
     * @param attr
     * @param serviceId
     * @param serviceStatus
     */
    @RequestMapping(value = "changeServiceStatus", method = RequestMethod.POST)
    @ResponseBody
    public void changeServiceStatus(HttpServletRequest request, HttpServletResponse response, 
            RedirectAttributes attr, Integer serviceId,String serviceStatus){
        Service service = serviceService.get(serviceId);
        service.setServiceStatus(serviceStatus);
        service.setUpdateTime(sdf.format(new Date()));
        serviceService.upd(service);
    }
    
}	
