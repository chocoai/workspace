package com.yhcrt.healthcloud.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhcrt.healthcloud.cms.entity.CmsChannel;
import com.yhcrt.healthcloud.cms.service.CmsChannelService;
import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.system.service.SysSequenceService;
import com.yhcrt.healthcloud.util.DateUtil;

/**
 * @ClassName: CmsChannelController
 * @Description:cms栏目管理
 * @version V1.0
 * @author rpf
 * @date: 2017年9月6日
 */
@Controller
@RequestMapping("/channel")
public class CmsChannelController {
	@Autowired
	private CmsChannelService channelService;
	@Autowired
	private SysSequenceService sysSequenceService;

	/**
	 * 查询栏目列表
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String parentId = request.getParameter("parentId");
		if (StringUtils.isBlank(parentId)) {
			CmsChannel rootChannel = channelService.getRootChannel();
			parentId = rootChannel.getChannelId().toString();
		}
		List<CmsChannel> channelList = channelService.getChildChannelByParentId(Integer.parseInt(parentId));
		modelMap.put("channelList", channelList);
		modelMap.put("parentId", parentId);
		return "channel/list";
	}

	/**
	 * 查询栏目树状结构
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 */
	@RequestMapping(value = "/getChannelTree", method = RequestMethod.POST)
	public void getChannelTree(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		try {
			List<CmsChannel> channelList = channelService.getAllChannel();
			JSONArray jsonArray = new JSONArray();
			for (CmsChannel channel : channelList) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", channel.getChannelId());
				jsonObj.put("name", channel.getChannelName());
				jsonObj.put("pId", channel.getParentId());
				jsonArray.add(jsonObj);
			}
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据栏目ID查询所有子栏目
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getChildChannel", method = RequestMethod.GET)
	public String getChildChannel(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String parentId = request.getParameter("parentId");
		if (StringUtils.isBlank(parentId)) {
			CmsChannel rootChannel = channelService.getRootChannel();
			parentId = rootChannel.getChannelId().toString();
		}
		List<CmsChannel> channelList = channelService.getChildChannelByParentId(Integer.parseInt(parentId));
		modelMap.put("channelList", channelList);
		modelMap.put("parentId", parentId);
		return "channel/channel_list";
	}

	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String parentId = request.getParameter("parentId");
		CmsChannel parentChannel;
		if (StringUtils.isNotBlank(parentId)) {
			parentChannel = channelService.getChannelByChannelId(Integer.parseInt(parentId));
			parentChannel = parentChannel == null ? channelService.getRootChannel() : parentChannel;
		} else {
			parentChannel = channelService.getRootChannel();
		}
		modelMap.put("channel", new CmsChannel());
		modelMap.put("parentChannel", parentChannel);
		return "channel/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, CmsChannel channel,
			RedirectAttributes attr) {
		String channelId = request.getParameter("channelId");
		if (StringUtils.isNotBlank(channelId)) {
			CmsChannel cmsChannel = channelService.getChannelByChannelId(Integer.parseInt(channelId));
			BeanUtils.copyProperties(channel, cmsChannel, "createTime", "status");
			channelService.update(cmsChannel);
		} else {
			channel.setChannelId(sysSequenceService.getSequenceValue(Constants.SequenceName.SYS_RES));
			channel.setCreateTime(DateUtil.getDateTime());
			channel.setStatus(Constants.STATUS_NORMAL);
			channelService.insert(channel);
		}
		attr.addAttribute("parentId", channel.getParentId());
		return "redirect:list";
	}
	
	@RequestMapping(value = "/toEdit", method = RequestMethod.GET)
	public String toEdit(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String channelId = request.getParameter("channelId");
		if (StringUtils.isNotBlank(channelId)) {
			CmsChannel cmsChannel = channelService.getChannelByChannelId(Integer.parseInt(channelId));
			modelMap.put("channel", cmsChannel);
			modelMap.put("parentChannel", cmsChannel.getParentChannel());
		}
		return "channel/add";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			RedirectAttributes attr) {
		String channelId = request.getParameter("channelId");
		String parentId = "";
		if (StringUtils.isNotBlank(channelId)) {
			CmsChannel cmsChannel = channelService.getChannelByChannelId(Integer.parseInt(channelId));
			parentId = cmsChannel != null ? cmsChannel.getParentId().toString() : parentId;
			// 逻辑删除，更新数据状态标识位，不删除数据库中的记录
			cmsChannel.setStatus(Constants.STATUS_DISABLE);
			channelService.update(cmsChannel);
			
		}
		attr.addAttribute("parentId", parentId);
		return "redirect:list";
	}

}
