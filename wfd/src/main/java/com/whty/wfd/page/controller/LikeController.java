package com.whty.wfd.page.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whty.wfd.page.model.*;
import com.whty.wfd.page.service.LikeService;
import com.whty.wfd.page.vo.Like;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * \* User: zjd \* Date: 2018/8/17 \* Time: 16:00 \* Description: \
 */
@Controller
public class LikeController {

	@Autowired
	private LikeService likeService;

	final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping("getLike.html")
	public String getLike(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		return "like/like";
	}

	@RequestMapping("getLikeList")
	@ResponseBody
	public void getLikeList(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");
		PageInfo<Like> p = likeService.getLikes(tUser.getId(), pageNum, pageSize);
		JSONObject obj = new JSONObject();
		for (Like like : p.getList()) {
			like.setCreateTimeStr(sdf.format(like.getCreateTime()));
		}
		obj.put("list", p.getList());
		obj.put("pageNum", p.getPageNum());
		obj.put("pageTotal", p.getTotal());
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		try {
			response.getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "addMessageLike", method = RequestMethod.GET)
	@ResponseBody
	public int addMessageLike(HttpServletRequest request, String messageId, Integer type) {
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");
		int i = 0;
		try {
			if (type == 0) {// 添加
				TPostMessageLike tPostMessageLike = new TPostMessageLike();
				tPostMessageLike.setPostMessageId(messageId);
				tPostMessageLike.setUserId(tUser.getId());
				tPostMessageLike.setIsRead(false);
				tPostMessageLike.setCreateTime(new Date());
				tPostMessageLike.setUpdateTime(new Date());
				likeService.addMessageLike(tPostMessageLike);
			}
			if (type == 1) {// 删除
				TPostMessageLikeKey key = new TPostMessageLike();
				key.setPostMessageId(messageId);
				key.setUserId(tUser.getId());
				likeService.deleteMessageLike(key);
			}
			i = likeService.getMessageLikeCountByMessageId(messageId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	@RequestMapping(value = "addPostLike", method = RequestMethod.GET)
	@ResponseBody
	public int addPostLike(HttpServletRequest request, String postId, Integer type) {
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");
		int i = 0;
		try {
			if (type == 0) {// 添加
				TPostLike tPostLike = new TPostLike();
				tPostLike.setPlatePostId(postId);
				tPostLike.setUserId(tUser.getId());
				tPostLike.setIsRead(false);
				tPostLike.setCreateTime(new Date());
				tPostLike.setUpdateTime(new Date());
				likeService.addPostLike(tPostLike);
			}
			if (type == 1) {// 删除
				TPostLikeKey key = new TPostLikeKey();
				key.setPlatePostId(postId);
				key.setUserId(tUser.getId());
				likeService.deletePostLike(key);
			}
			i = likeService.getPostLikeCountByPostId(postId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	@RequestMapping(value = "updateLike", method = RequestMethod.GET)
	public String updateLike(HttpServletRequest request) {
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");
		likeService.updateLikeIsRead(tUser.getId());
		return "redirect:index.html";
	}

	@ResponseBody
	@RequestMapping(value = "updateLikeIsReadById", method = RequestMethod.GET)
	public int updateLikeIsReadById(HttpServletRequest request, String id, Integer userId, Integer type) {
		boolean dateType = false;
		if (type == 1) {
			dateType = true;
		}
		return likeService.updateLikeIsReadById(id, userId, dateType);
	}

}