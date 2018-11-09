package com.yhcrt.healthcloud.device.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yhcrt.healthcloud.base.controller.BaseController;
import com.yhcrt.healthcloud.device.entity.MemberDeviceVideo;
import com.yhcrt.healthcloud.device.service.DeviceVideoService;
import com.yhcrt.healthcloud.organization.entity.Organization;
import com.yhcrt.healthcloud.organization.service.OrganizationService;
import com.yhcrt.healthcloud.util.DateUtil;

@Controller
@RequestMapping("/deviceVideo")
public class DeviceVideoController extends BaseController {

	@Autowired
	private DeviceVideoService deviceVideoService;
	@Autowired
	private OrganizationService organizationService;

	/**
	 * 查询设备视频数据
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String orgId = request.getParameter("orgId");
		orgId = StringUtils.isBlank(orgId) ? getLoginOrg(getLoginUser()).getOrgId().toString() : orgId;
		List<Integer> orgList = getOrgList(Integer.parseInt(orgId));
		Organization org = organizationService.selectByPrimaryKey(Integer.parseInt(orgId));
		String deviceName = request.getParameter("deviceName");
		String deviceModel = request.getParameter("deviceModel");
		String status = request.getParameter("status");
		Map<String, Object> args = new HashMap<String, Object>();
		if (!orgList.isEmpty() && orgList.size()>0) {
			args.put("orgList", orgList);
		}
		if (StringUtils.isNotBlank(deviceName)) {
			args.put("deviceName", deviceName.trim());
		}
		if (StringUtils.isNotBlank(deviceModel)) {
			args.put("deviceModel", deviceModel.trim());
		}
		if(StringUtils.isNotBlank(status)){
			args.put("status", Integer.parseInt(status));
		}
		List<MemberDeviceVideo> list = deviceVideoService.queryPageList(args);
		modelMap.put("list", list);
		modelMap.put("org", org);
		modelMap.put("deviceName", deviceName);
		modelMap.put("deviceModel", deviceModel);
		modelMap.put("status", status);
		return "device/video/list";
	}
	
	/**
	 * 新增或修改跳转界面
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param cid
	 * @return
	 */
	@RequestMapping(value = "/toAdd")
	public String toAdd(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Integer cid) {
		Organization org;
		MemberDeviceVideo video = new MemberDeviceVideo();
		if(cid == null){	//新增界面
			// 获取当前登录用户的机构
			org = getLoginOrg(getLoginUser());
		}else {	//编辑界面
			video = deviceVideoService.queryByCid(cid);
			org = organizationService.selectByPrimaryKey(video.getOrgId());
		}
		modelMap.put("video", video);
		modelMap.put("org", org);
		return "device/video/add";
	}
	
	/**
	 * 新增或修改保存
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param video
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attr, MemberDeviceVideo video) {
		Integer i = 0;
		try {
			// 根据设备id进行去重判断
			int count = deviceVideoService.countByVideo(video);
			if(count == 0 ){
				if(video.getCid() == null){	//表示新增
					video.setCreateTime(DateUtil.getDateTime());
					i = deviceVideoService.insert(video);
				}else {	//表示修改
					i = deviceVideoService.update(video);
				}
			}else{
				i = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (i == 1) {
			attr.addFlashAttribute("state", "SUCCESS");
		} else if(i == -1){
			attr.addFlashAttribute("state", "SAME");
		}else{
			attr.addFlashAttribute("state", "FALSE");
		}
		return "redirect:list";
	}
	
	/**
	 * 批量删除设备
	 * @param request
	 * @param response
	 * @param cids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
	public Map<String, Object> batchDelete(HttpServletRequest request, HttpServletResponse response, Integer[] cids) {
		System.out.println(cids);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cids", cids);
		try {
			deviceVideoService.batchDelete(map);
			map.clear();
			map.put("state", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			map.clear();
			map.put("state", "FALSE");
		}
		return map;
	}

}
