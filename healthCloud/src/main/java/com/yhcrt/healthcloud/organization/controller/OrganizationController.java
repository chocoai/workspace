package com.yhcrt.healthcloud.organization.controller;

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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhcrt.healthcloud.base.controller.BaseController;
import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.mall.entity.YwImage;
import com.yhcrt.healthcloud.mall.service.YwImageService;
import com.yhcrt.healthcloud.organization.entity.Organization;
import com.yhcrt.healthcloud.system.entity.SysUser;
import com.yhcrt.healthcloud.util.UploadUtils;

/**
 * @ClassName: OrganizationController
 * @Description:组织机构管理
 * @version V1.0
 * @author rpf
 * @date: 2017年5月17日
 */
@Controller
@RequestMapping("/org")
public class OrganizationController extends BaseController {

    @Autowired  
    private YwImageService ywImageService;

	/**
	 * 查询组织机构列表
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String orgCode = null;
    	Organization org = new Organization();
        //机构ID的集合
    	String parentOrgId = request.getParameter("parentOrgId");
		if (StringUtils.isBlank(parentOrgId)) {
            //获取登录用户
            SysUser loginUser = getLoginUser();
            //获取当前登录用户的机构
            orgCode = getLoginOrg(loginUser).getOrgCode();
            parentOrgId = getLoginOrg(loginUser).getOrgId().toString();
        }else{
        	//根据orgId查询用户所属机构及子机构的ID
        	org = organizationService.selectByPrimaryKey(Integer.parseInt(parentOrgId));
        	orgCode = org.getOrgCode();
        }
        //通过orgCode获取该机构及该机构所有的下级机构
        List<Organization> orgList = organizationService.getChildOrgByParentCode(orgCode);
		modelMap.put("orgList", orgList);
		modelMap.put("parentOrgId", parentOrgId);
		return "org/list";
	}

	/**
	 * 根据机构ID查询所有子机构
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getChildOrg", method = RequestMethod.GET)
	public String getChildOrg(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String orgCode = null;
    	Organization org = new Organization();
		String parentOrgId = request.getParameter("parentOrgId");
		if (StringUtils.isBlank(parentOrgId)) {
            //获取登录用户
            SysUser loginUser = getLoginUser();
            //获取当前登录用户的机构
            orgCode = getLoginOrg(loginUser).getOrgCode();
            parentOrgId = getLoginOrg(loginUser).getOrgId().toString();
        }else{
        	//根据orgId查询用户所属机构及子机构的ID
        	org = organizationService.selectByPrimaryKey(Integer.parseInt(parentOrgId));
        	orgCode = org.getOrgCode();
		}
		//通过orgCode获取该机构及该机构所有的下级机构
        List<Organization> orgList = organizationService.getChildOrgByParentCode(orgCode);
		modelMap.put("orgList", orgList);
		modelMap.put("parentOrgId", parentOrgId);
		return "org/org_list";
	}

	/**
	 * 新增组织机构预处理：查询新增组织机构的父级机构
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String parentOrgId = request.getParameter("parentOrgId");
		Organization parentOrg;
		if (StringUtils.isNotBlank(parentOrgId)) {
			parentOrg = organizationService.selectByPrimaryKey(Integer.valueOf(parentOrgId));
			parentOrg = parentOrg == null ? organizationService.getOrgRootNode() : parentOrg;
		} else {
			parentOrg = organizationService.getOrgRootNode();
		}
		Integer orgId = sysSequenceService.getSequenceValue(Constants.SequenceName.ORGANIZATION);    
		modelMap.put("orgId", orgId);
		modelMap.put("org", new Organization());
		modelMap.put("parentOrg", parentOrg);
		return "org/add";
	}

	/**
	 * 新增组织机构
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param org
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, 
			Organization org, RedirectAttributes attr, String editType) {
		// 上传机构logo和机构标题图
		String orgLogo = UploadUtils.uploadFile(request, "orgLogoFile", "org");
		String titleImg = UploadUtils.uploadFile(request, "titleImgFile", "org");
		if (StringUtils.isNoneBlank(orgLogo)){
			org.setOrgLogo(orgLogo);
		}
		if (StringUtils.isNoneBlank(titleImg)){
			org.setTitleImg(titleImg);
		}
		if ("edit".equals(editType)) {
			organizationService.update(org);
		} else {
			org.setCreateUser(getLoginUser().getUserId().toString());
			organizationService.insert(org);
		}
		attr.addAttribute("parentOrgId", org.getParentOrgId());
		return "redirect:list";
	}

	/**
	 * 编辑组织机构预处理
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/toEdit", method = RequestMethod.GET)
	public String toEdit(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String orgId = request.getParameter("orgId");
		if (StringUtils.isNotBlank(orgId)) {
			Organization org = organizationService.selectByPrimaryKey(Integer.valueOf(orgId));
			List<YwImage> imgList = ywImageService.getByRefId(Integer.parseInt(orgId), "org");
			modelMap.put("imgList", imgList);
			modelMap.put("org", org);
			modelMap.put("parentOrg", org.getParentOrg());
		}
		return "org/add";
	}

	/**
	 * 删除组织机构
	 * 
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
		String parentOrgId = "";
		if (StringUtils.isNotBlank(orgId)) {
			Organization org = organizationService.selectByPrimaryKey(Integer.valueOf(orgId));
			parentOrgId = org != null ? org.getParentOrgId().toString() : parentOrgId;
			// 物理删除
			// organizationService.deleteByOrgId(Integer.valueOf(orgId));
			// 逻辑删除，更新数据状态标识位，不删除数据库中的记录
			org.setStatus(Constants.STATUS_DISABLE);
			organizationService.update(org);
		}
		attr.addAttribute("parentOrgId", parentOrgId);
		return "redirect:list";
	}

	/**
	 * 查询组织机构树
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 */
	@RequestMapping(value = "/getOrgTree", method = RequestMethod.POST)
	public void getOrgTree(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		try {
			// 获取当前登录用户的机构编码
			Organization org = getLoginOrg(getLoginUser());
			// 通过orgCode获取该机构及该机构所有的下级机构
			List<Organization> orgList = organizationService.getAllChildOrgByParentCode(org.getOrgCode());
			JSONArray jsonArray = new JSONArray();
			for (Organization organization : orgList) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", organization.getOrgId());
				jsonObj.put("name", organization.getOrgName());
				jsonObj.put("pId", organization.getParentOrgId());
				jsonArray.add(jsonObj);
			}
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据orgId查询组织机构树
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 */
	@RequestMapping(value = "/getTreeByOrgId", method = RequestMethod.POST)
	public void getTreeByOrgId(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Integer orgId) {
		try {
			// 根据orgId获取的机构编码
			Organization org = organizationService.selectByPrimaryKey(orgId);
			// 通过orgCode获取该机构及该机构所有的下级机构
			List<Organization> orgList = organizationService.getAllChildOrgByParentCode(org.getOrgCode());
			JSONArray jsonArray = new JSONArray();
			for (Organization organization : orgList) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", organization.getOrgId());
				jsonObj.put("name", organization.getOrgName());
				jsonObj.put("pId", organization.getParentOrgId());
				jsonArray.add(jsonObj);
			}
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询组织机构树
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 */
	@RequestMapping(value = "/showOrgTree", method = RequestMethod.GET)
	public String showOrgTree(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		try {
			// 获取当前登录用户的机构编码
			Organization organization = getLoginOrg(getLoginUser());
			String orgId = request.getParameter("orgId");
			// 通过orgCode获取该机构及该机构所有的下级机构
			List<Organization> orgList = organizationService.getAllChildOrgByParentCode(organization.getOrgCode());
			JSONArray jsonArray = new JSONArray();
			for (Organization org : orgList) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", org.getOrgId());
				jsonObj.put("name", org.getOrgName());
				jsonObj.put("pId", org.getParentOrgId());
				jsonArray.add(jsonObj);
			}
			modelMap.put("orgTree", jsonArray);
			modelMap.put("orgId", StringUtils.isNotBlank(orgId) ? orgId : organization.getOrgId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "org/org_tree";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @throws IOException
	 */
	@RequestMapping(value = "/isExistOrg", method = RequestMethod.POST)
	public void isExistOrg(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String orgName = request.getParameter("orgName");
		String parentOrgId = request.getParameter("parentOrgId");
		String orgId = request.getParameter("orgId");
		try {
			boolean isExist =  organizationService.isExistOrg(orgId, parentOrgId, orgName);
			response.getWriter().print(isExist);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
