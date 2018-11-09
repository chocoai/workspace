package com.whty.wfd.page.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.wfd.page.model.TPostMessage;
import com.whty.wfd.page.model.TUser;
import com.whty.wfd.page.service.MessageService;
import com.whty.wfd.page.util.TimeUtils;

/**
 * \* User: zjd \* Date: 2018/8/17 \* Time: 10:03 \* Description: \
 */
@Controller
public class ReplyController {

	@Autowired
	private MessageService messageService;

	@RequestMapping("getReply.html")
	public String gerReply(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");
		List<TPostMessage> replies = messageService.getReply(tUser.getId());
		for (TPostMessage entity : replies) {
			entity.setCreateTimeStr(TimeUtils.getTimeStr(new Date(), entity.getCreateTime()));
		}
		map.put("replies", replies);
		return "message/reply_message";
	}

	/**
	 * 改变所有@我的状态为true
	 */
	@RequestMapping("ediIsRead")
	public String ediIsRead(HttpServletRequest request) {
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");
		messageService.updateIsRead(tUser.getId());
		return "redirect:index.html";
	}
}