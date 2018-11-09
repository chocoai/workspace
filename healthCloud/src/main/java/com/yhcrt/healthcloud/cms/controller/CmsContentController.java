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

import com.yhcrt.healthcloud.cms.entity.CmsChannel;
import com.yhcrt.healthcloud.cms.entity.CmsContent;
import com.yhcrt.healthcloud.cms.service.CmsChannelService;
import com.yhcrt.healthcloud.cms.service.CmsContentService;
import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.util.UploadUtils;

/**
 * @ClassName: CmsChannelController
 * @Description:cms栏目管理
 * @version V1.0
 * @author rpf
 * @date: 2017年9月6日
 */
@Controller
@RequestMapping("/content")
public class CmsContentController {
	@Autowired
	private CmsContentService contentService;
	@Autowired
	private CmsChannelService channelService;

	/**
	 * 内容列表
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String channelId = request.getParameter("channelId");
		if (StringUtils.isBlank(channelId)) {
			CmsChannel rootChannel = channelService.getRootChannel();
			channelId = rootChannel.getChannelId().toString();
		}
		modelMap.put("channelId", channelId);
		return "content/list";
	}

	/**
	 * 获取某栏目下的内容
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/getContentList", method = RequestMethod.GET)
	public String getContentList(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String channelId = request.getParameter("channelId");
		if (StringUtils.isBlank(channelId)) {
			CmsChannel rootChannel = channelService.getRootChannel();
			channelId = rootChannel.getChannelId().toString();
		}
		List<CmsContent> contents = contentService.listContentsByChannelId(channelId);
		modelMap.put("contentList", contents);
		modelMap.put("channelId", channelId);
		return "content/content_list";
	}
	
	@RequestMapping(value = "/getArchived", method = RequestMethod.GET)
	public String getArchivedContents(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String channelId = request.getParameter("channelId");
		if (StringUtils.isBlank(channelId)) {
			CmsChannel rootChannel = channelService.getRootChannel();
			channelId = rootChannel.getChannelId().toString();
		}
		List<CmsContent> contents = contentService.getArchivedContents(Integer.parseInt(channelId));
		modelMap.put("contentList", contents);
		modelMap.put("channelId", channelId);
		return "content/archived_contents";
	}

	/**
	 * 发布内容预处理
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String channelId = request.getParameter("channelId");
		CmsChannel channel;
		if (StringUtils.isNotBlank(channelId)) {
			channel = channelService.getChannelByChannelId(Integer.parseInt(channelId));
			channel = channel == null ? channelService.getRootChannel() : channel;
		} else {
			channel = channelService.getRootChannel();
		}
		modelMap.put("content", new CmsContent());
		modelMap.put("channel", channel);
		return "content/edit";
	}

	/**
	 * 新增或者更新内容
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param content
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, CmsContent content,
			RedirectAttributes attr) {
		String contentId = request.getParameter("contentId");
		String titleImg = UploadUtils.uploadFile(request, "titleImgFile", "cms");
		if (StringUtils.isNoneBlank(titleImg)){
			content.setTitleImg(titleImg);
		}
			
		if (StringUtils.isNotBlank(contentId)) {
			CmsContent cmsContent = contentService.getContentById(Integer.parseInt(contentId));
			BeanUtils.copyProperties(content, cmsContent, "createUser", "status", "releaseTime");
			contentService.update(cmsContent);
		} else {
			contentService.insert(content);
		}
		attr.addAttribute("channelId", content.getChannelId());
		return "redirect:list";
	}

	/**
	 * 编辑栏目预处理
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/toEdit", method = RequestMethod.GET)
	public String toEdit(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String contentId = request.getParameter("contentId");
		if (StringUtils.isNotBlank(contentId)) {
			CmsContent content = contentService.getContentById(Integer.parseInt(contentId));
			modelMap.put("content", content);
			modelMap.put("channel", content.getChannel());
		}
		return "content/edit";
	}
	
	@RequestMapping(value = "/viewDetail", method = RequestMethod.GET)
	public String viewDetail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String contentId = request.getParameter("contentId");
		if (StringUtils.isNotBlank(contentId)) {
			CmsContent content = contentService.getContentById(Integer.parseInt(contentId));
			modelMap.put("content", content);
			modelMap.put("channel", content.getChannel());
		}
		return "content/content_detail";
	}

	/**
	 * 归档内容
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/archive", method = RequestMethod.GET)
	public String archive(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			RedirectAttributes attr) {
		String contentId = request.getParameter("contentId");
		if (StringUtils.isNotBlank(contentId)) {
			CmsContent content = contentService.getContentById(Integer.parseInt(contentId));
			content.setStatus(Constants.Content.STATUS_ARCHIVED);
			contentService.update(content);
			attr.addAttribute("channelId", content.getChannelId());
		}
		return "redirect:list";
	}
	
	@RequestMapping(value = "/cancelArchive", method = RequestMethod.GET)
	public String cancelArchive(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			RedirectAttributes attr) {
		String contentId = request.getParameter("contentId");
		if (StringUtils.isNotBlank(contentId)) {
			CmsContent content = contentService.getContentById(Integer.parseInt(contentId));
			content.setStatus(Constants.Content.STATUS_PASS);
			contentService.update(content);
			
			List<CmsContent> contents = contentService.getArchivedContents(content.getChannelId());
			modelMap.put("contentList", contents);
			modelMap.put("channelId", content.getChannelId());
		}
		return "content/archived_contents";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			RedirectAttributes attr) {
		String contentId = request.getParameter("contentId");
		if (StringUtils.isNotBlank(contentId)) {
			CmsContent content = contentService.getContentById(Integer.parseInt(contentId));
			attr.addAttribute("channelId", content.getChannelId());
			contentService.delete(Integer.parseInt(contentId));
		}
		return "redirect:list";
	}
	
	@RequestMapping(value = "/batchDelete", method = RequestMethod.POST)
	public void batchDelete(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String[] contentIds = request.getParameterValues("contentIds");
		if (contentIds != null && contentIds.length > 0) {
			contentService.batchDelete(contentIds);
		}
	}

	/**
	 * 批量归档内容
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 */
	@RequestMapping(value = "/batchArchive", method = RequestMethod.POST)
	public void batchArchive(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String[] contentIds = request.getParameterValues("contentIds");
		if (contentIds != null && contentIds.length > 0) {
			contentService.batchArchive(contentIds);
		}
	}
	
	@RequestMapping(value = "/batchCancleArchive", method = RequestMethod.POST)
	public void batchCancleArchive(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String[] contentIds = request.getParameterValues("contentIds");
		if (contentIds != null && contentIds.length > 0) {
			contentService.batchCancleArchive(contentIds);
		}
	}
}
