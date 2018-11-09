/**
 * @Title:   ProviderController.java 
 * @Package: com.yhcrt.healthcloud.provider.controller  
 * @Description: 
 * @author: rpf
 * @date: 2018年1月21日 
 * @version V1.0 
 * Copyright © 2018 Wuhan YHCRT Technology Co.Ltd. All rights reserved.
 */
package com.yhcrt.healthcloud.provider.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yhcrt.healthcloud.base.controller.BaseController;
import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.mall.entity.YwImage;
import com.yhcrt.healthcloud.mall.service.YwImageService;
import com.yhcrt.healthcloud.organization.entity.Organization;
import com.yhcrt.healthcloud.provider.entity.ServiceProvider;
import com.yhcrt.healthcloud.util.DateUtil;
import com.yhcrt.healthcloud.util.UploadUtils;


/**
 * @ClassName: ProviderController
 * @Description:
 * @version V1.0 
 * @author rpf
 * @date: 2018年1月21日 
 */

@Controller
@RequestMapping("/provider")
public class ProviderController extends BaseController {
	
	@Autowired
    private YwImageService ywImageService;

	@RequestMapping(value = "/toList", method = RequestMethod.GET)
	public String toList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String orgId = request.getParameter("orgId");
		if (StringUtils.isBlank(orgId)) {
			Organization organization = getLoginOrg(getLoginUser());
			orgId = organization.getOrgId().toString();
		} 
		List<ServiceProvider> providers = providerService.getProvidersByOrgId(orgId);
		modelMap.put("providers", providers);
		modelMap.put("orgId", orgId);
		return "provider/list";
	}
	
	/**
	 * 获取某机构下服务供应商列表
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String orgId = request.getParameter("orgId");
		List<ServiceProvider> providers = providerService.getProvidersByOrgId(orgId);
		modelMap.put("orgId", orgId);
		modelMap.put("providers", providers);
		return "provider/provider_list";
	}
	
	/**
	 * 添加服务供应商预处理
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String orgId = request.getParameter("orgId");
		Organization organization;
		if (StringUtils.isNotBlank(orgId)) {
			organization = organizationService.selectByPrimaryKey(Integer.valueOf(orgId));
			organization = organization == null ? getLoginOrg(getLoginUser()) : organization;
		} else {
			organization = getLoginOrg(getLoginUser());
		}
		modelMap.put("providerId", sysSequenceService.getSequenceValue(Constants.SequenceName.PROVIDER));
		modelMap.put("provider", new ServiceProvider());
		modelMap.put("org", organization);
		return "provider/add";
	}
	
	/**
	 * 是否存在同名服务供应商
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 */
	@RequestMapping(value = "/isExistProvider", method = RequestMethod.POST)
	public void isExistOrg(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String providerName = request.getParameter("providerName");
		String orgId = request.getParameter("orgId");
		String providerId = request.getParameter("providerId");
		try {
			boolean isExist = providerService.isExistProvider(orgId, providerName, providerId);
			response.getWriter().print(isExist);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加、修改服务供应商
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param provider
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, 
			ServiceProvider provider, RedirectAttributes attr, String editType) {
		// 上传供应商logo、标题图、工商营业执照
		String providerLogo = UploadUtils.uploadFile(request, "providerLogoFile", "provider");
		String titleImg = UploadUtils.uploadFile(request, "titleImgFile", "provider");
		String businessLicense = UploadUtils.uploadFile(request, "businessLicenseFile", "provider");
		if (StringUtils.isNoneBlank(providerLogo)){
			provider.setProviderLogo(providerLogo);
		}
		if (StringUtils.isNoneBlank(titleImg)){
			provider.setTitleImg(titleImg);
		}
		if (StringUtils.isNoneBlank(businessLicense)){
			provider.setBusinessLicense(businessLicense);
		}
		
		if ("edit".equals(editType)) {
			provider.setUpdateTime(DateUtil.getDateTime());
			providerService.update(provider);
		}else {
			provider.setCreateUser(getLoginUser().getUserId().toString());
			providerService.insert(provider);
		}
		attr.addAttribute("orgId", provider.getOrgId());
		return "redirect:toList";
	}
	
	@RequestMapping(value = "/toEdit", method = RequestMethod.GET)
	public String toEdit(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String providerId = request.getParameter("providerId");
		if (StringUtils.isNotBlank(providerId)) {
			ServiceProvider provider = providerService.getProviderById(Integer.valueOf(providerId));
			List<YwImage> imgList = ywImageService.getByRefId(Integer.valueOf(providerId), "provider");
			modelMap.put("imgList", imgList);
			modelMap.put("provider", provider);
			modelMap.put("org", provider.getOrg());
			modelMap.put("providerTypeList", getTypeItem("provider_category",provider.getProviderCategory()));	
		}
		return "provider/add";
	}
	
	/**
	 * 删除服务供应商
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			RedirectAttributes attr) {
		String orgId = request.getParameter("orgId");
		String providerId = request.getParameter("providerId");
		if (StringUtils.isNotBlank(providerId)) {
			ServiceProvider provider = providerService.getProviderById(Integer.valueOf(providerId));
			provider.setStatus(Constants.STATUS_DISABLE);
			providerService.update(provider);
		}
		attr.addAttribute("orgId", orgId);
		return "redirect:toList";
	}
	
}
