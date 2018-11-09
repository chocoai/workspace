/**
 * 
 */
package com.whty.wfd.page.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.wfd.base.controller.BaseController;
import com.whty.wfd.page.dao.TPlateMapper;
import com.whty.wfd.page.dao.TPostMessageMapper;
import com.whty.wfd.page.dao.TUserMapper;
import com.whty.wfd.page.model.TPlate;
import com.whty.wfd.page.model.TPlatePost;
import com.whty.wfd.page.model.TPostMessage;
import com.whty.wfd.page.model.TPostMessageExample;
import com.whty.wfd.page.model.TUser;
import com.whty.wfd.page.service.TeacherService;

/**
 * @author zhangzheng
 * @date 2018年8月16日
 */
@Controller
@RequestMapping("/page/teacher")
public class TeacherController extends BaseController {

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private TPlateMapper tPlateMapper;

	@Autowired
	private TPostMessageMapper tPostMessageMapper;

	@Autowired
	private TUserMapper tUserMapper;

	/**
	 * 板块列表
	 * 
	 * @param request
	 * @param model
	 * @param response
	 */
	@RequestMapping("plateList")
	public void plateList(HttpServletRequest request, Model model, HttpServletResponse response) {
		Integer plateId = Integer.valueOf(request.getParameter("plateId"));

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("plateId", plateId);
		List<TPlatePost> topPlatePostList = teacherService.getTopPlatePost(param);// 置顶的帖子

		Map<String, Object> resule = new HashMap<String, Object>();
		resule.put("list", topPlatePostList);
		printJson(response, resule);
	}

	/**
	 * 获取帖子列表
	 * 
	 * @param request
	 * @param model
	 * @param response
	 */
	@RequestMapping("platePostList")
	public void getPlatePostList(HttpServletRequest request, Model model, HttpServletResponse response) {
		Integer plateId = null;

		if (StringUtils.isNotEmpty(request.getParameter("plateId"))) {
			plateId = Integer.valueOf(request.getParameter("plateId"));
		}

		String sortType = request.getParameter("sortType");// 排序方式

		Map<String, Object> param = new HashMap<String, Object>();

		String startLine = request.getParameter("startLine");

		if (StringUtils.isEmpty(startLine)) {
			param.put("startLine", 0);
			param.put("endLine", 10);
		} else {
			Integer startLineInt = Integer.valueOf(startLine);
			Integer endLineInt = startLineInt + 10;
			param.put("startLine", startLineInt);
			param.put("endLine", endLineInt);
		}

		if (StringUtils.isEmpty(sortType)) {
			param.put("sortType", 1);
		} else {
			param.put("sortType", 2);
		}

		param.put("plateId", plateId);
		param.put("isTop", 0);
		param.put("isDelete", 0);
		param.put("userId", 1);
		List<TPlatePost> platePostList = teacherService.getPlatePost(param);
		Map<String, Object> resule = new HashMap<String, Object>();
		resule.put("list", platePostList);
		printJson(response, resule);
	}

	/**
	 * 获取置顶的帖子列表
	 * 
	 * @param request
	 * @param model
	 * @param response
	 */
	@RequestMapping("getTopPlatePostList")
	public void getTopPlatePostList(HttpServletRequest request, Model model, HttpServletResponse response) {
		Integer plateId = null;

		if (StringUtils.isNotEmpty(request.getParameter("plateId"))) {
			plateId = Integer.valueOf(request.getParameter("plateId"));
		}

		// 获取帖子列表
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("plateId", plateId);
		param.put("isTop", 1);
		param.put("isDelete", 0);
		param.put("userId", 1);
		param.put("sortType", 1);
		param.put("startLine", 0);
		param.put("endLine", 3);
		List<TPlatePost> platePostList = teacherService.getPlatePost(param);
		Map<String, Object> resule = new HashMap<String, Object>();
		resule.put("list", platePostList);

		printJson(response, resule);
	}

	/**
	 * 板块页面
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("plate.html")
	public String plate(HttpServletRequest request, Model model, HttpServletResponse response) {
		String plateId = request.getParameter("plateId");

		Integer userId = Integer.valueOf(request.getParameter("userId"));

		TUser user = tUserMapper.selectByPrimaryKey(userId);

		if (StringUtils.isNotEmpty(plateId)) {
			TPlate tplate = tPlateMapper.selectByPrimaryKey(plateId);
			model.addAttribute("plateName", tplate.getName());
			model.addAttribute("plateId", tplate.getId());
		} else {
			model.addAttribute("plateName", "全部");
			model.addAttribute("plateId", "");
		}

		// 查询用户能看到的板块

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", userId);

		List<TPlate> plateList = tPlateMapper.selectByUserId(param);

		TPostMessageExample bean = new TPostMessageExample();
		bean.createCriteria().andUserIdEqualTo(userId);

		List<TPostMessage> postMessageList = tPostMessageMapper.selectByExample(bean);

		model.addAttribute("userName", user.getName());

		model.addAttribute("postMessageCount", postMessageList.size());// 回复问题数量

		model.addAttribute("plateList", plateList);

		return "teacher/plate";
	}

	/**
	 * 首页
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("index.html")
	public String index(HttpServletRequest request, Model model, HttpServletResponse response) {
		// Map<String, Object> param = new HashMap<String, Object>();
		// List<TPlatePost> topPlatePostList =
		// teacherService.getTopPlatePost(param);//置顶的帖子

		// model.addAttribute("topPlatePostList", topPlatePostList);
		return "teacher/index";
	}

	/**
	 * 帖子详情
	 * 
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 */
	@RequestMapping("detail.html")
	public String detail(HttpServletRequest request, Model model, HttpServletResponse response) {
		String platePostId = request.getParameter("platePostId");

		Integer userId = 1;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("isDelete", 0);
		param.put("userId", userId);
		param.put("sortType", 1);
		param.put("startLine", 0);
		param.put("endLine", 3);
		param.put("platePostId", platePostId);

		List<TPlatePost> platePostList = teacherService.getPlatePost(param);

		model.addAttribute("platePost", platePostList.get(0));

		Map<String, Object> postMessage = new HashMap<String, Object>();

		postMessage.put("platePostId", platePostList.get(0).getId());

		List<TPostMessage> postMessageList = tPostMessageMapper.selectByPlatePost(postMessage);

		model.addAttribute("postMessageList", postMessageList);
		model.addAttribute("userId", userId);
		return "teacher/detail";
	}

}
