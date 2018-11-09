package com.whty.ebp.manage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.common.util.CommonFunction;
import com.whty.common.util.GUIDGenerator;
import com.whty.ebp.base.controller.BaseController;
import com.whty.ebp.manage.model.Message;
import com.whty.ebp.manage.service.MessageService;
import com.whty.ebp.manage.service.ProductService;
import com.whty.ebp.sys.model.SysUser;
import com.whty.page.PageContext;
import com.whty.page.util.HandlerResult;

/**
 * 消息管理
 */
@Controller
@RequestMapping("/manage/message")
public class MessageController extends BaseController {

	@Autowired
	private MessageService messageService;
	@Autowired
	private ProductService productService;
	
	/**
	 * 查询消息列表
	 */
	@RequestMapping(value="/list")
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String search_type = request.getParameter("search_type");
		String currentPage = request.getParameter("currPage");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");

		Map<String, Object> paraMap = new HashMap<String, Object>();
		//paraMap.put("status", request.getParameter("status"));
		paraMap.put("theme", request.getParameter("theme"));
		paraMap.put("receiveObjId", request.getParameter("receiveObjId"));
		paraMap.put("createTimeStart", request.getParameter("createTimeStart"));
		paraMap.put("createTimeEnd", request.getParameter("createTimeEnd"));
		paraMap.put("releaseTimeStart", request.getParameter("releaseTimeStart"));
		paraMap.put("releaseTimeEnd", request.getParameter("releaseTimeEnd"));

		PageContext page = PageContext.getContext();

		// 请自行验证
		if (null == currentPage || StringUtils.isNotEmpty(search_type)) {
			page.setCurrentPage(1);
			page.setPageSize(10);
			page.setTotalPage(0);
			page.setTotalRows(0);
		} else {
			page.setCurrentPage(Integer.parseInt(currentPage));
			page.setPageSize(Integer.parseInt(pageSize));
			page.setTotalPage(Integer.parseInt(totalPage));
		}
		page.setPagination(true);
		HandlerResult handlerResult = messageService.queryMessage(paraMap);
		model.addAttribute("list", handlerResult.getResultList());
		model.addAttribute("page", page);
		model.addAllAttributes(paraMap);
		page.setPagination(false);

		model.addAttribute("productList", productService.queryAllProduct());
		
		return "message/list";
	}
	
	/**
	 * 查看消息详情
	 */
	@RequestMapping(value="/detail")
	public String detail(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String id = request.getParameter("id");
		Message message = new Message();
		if(StringUtils.isNotEmpty(id)){
			message = messageService.queryMessageDetail(id);
		}
		model.addAttribute("message", message);
		return "message/detail";
	}
	
	/**
	 * 跳转到创建消息的页面
	 */
	@RequestMapping(value="/initSend")
	public String create(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		model.addAttribute("productList", productService.queryAllProduct());
		model.addAttribute("messageTypeList",messageService.queryMessageType());
		return "message/send";
	}
	
	/**
	 * 创建消息
	 */
	@RequestMapping(value="/send")
	public void send(@ModelAttribute("message") Message message, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		SysUser sysUser = (SysUser)request.getSession().getAttribute("sysUser");
		
		message.setId(GUIDGenerator.getGUID());
		message.setSenderId(sysUser.getId());
		message.setSenderName(sysUser.getUser_name());
		message.setSendOrgName("系统管理员");
		message.setCreateTime(new Date());
		message.setReleaseTime(CommonFunction.getSampleTimeString(request.getParameter("releaseTimeStr")));
		message.setStatus("0");
		messageService.addMessage(message);
		
		CommonFunction.writeResp(response, "success");
	}
	
	/**
	 * 修改消息状态
	 */
	@RequestMapping(value="/updateStatus")
	public void updateStatus(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		Map map = new HashMap();
		map.put("id", request.getParameter("id"));
		map.put("status", request.getParameter("status"));
		messageService.updateMessageStatus(map);
		
		CommonFunction.writeResp(response, "success");
	}
	
}
