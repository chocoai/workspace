/**
 *
 */
package com.whty.wfd.page.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.util.StringUtil;
import com.whty.wfd.page.service.CountService;
import com.whty.wfd.page.service.PostService;
import com.whty.wfd.page.vo.Count;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whty.wfd.base.controller.BaseController;
import com.whty.wfd.page.dao.TPlateMapper;
import com.whty.wfd.page.dao.TPlatePostMapper;
import com.whty.wfd.page.dao.TUserMapper;
import com.whty.wfd.page.model.TPlate;
import com.whty.wfd.page.model.TPlatePost;
import com.whty.wfd.page.model.TUser;

import net.sf.json.JSONObject;

/**
 * 首页
 *
 * @author zhangzheng
 * @date 2018年8月23日
 */
@Controller
public class CountController {

	@Autowired
	private CountService countService;

	@Autowired
	private TPlatePostMapper tPlatePostMapper;

	@Autowired
	private PostService postService;

	@RequestMapping(value = "queryHotQeustion", method = RequestMethod.GET)
	public void queryHotQeustion(ModelMap map, HttpServletResponse response, HttpServletRequest request,
			@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(name = "pageSize", defaultValue = "30") Integer pageSize) throws IOException {
		JSONObject jsonObject = new JSONObject();

		TUser tUser = (TUser) request.getSession().getAttribute("userObj");

		List<TPlatePost> tPlatePosts = tPlatePostMapper.getPostByPlateIdAll("", tUser.getId(), 1,
				tUser.getSchoolId(), 0, 100000);

		jsonObject.put("list", tPlatePosts);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jsonObject);

	}

	@RequestMapping(value = "hotQeustion.html", method = RequestMethod.GET)
	public String hotQeustion() {
		return "count/hotQeustion";
	}

	@RequestMapping(value = "studentPost.html", method = RequestMethod.GET)
	public String studentPost(ModelMap map, HttpServletResponse response, HttpServletRequest request) {
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");

		List<Count> list1 = countService.getStudentPost(tUser.getSchoolId());
		map.put("list", list1);
		return "count/studentPost";
	}

	@RequestMapping(value = "teacherLikeCount.html", method = RequestMethod.GET)
	public String teacherLikeCount(ModelMap map, HttpServletResponse response, HttpServletRequest request) {
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");

		List<Count> list1 = countService.getTeacherLikeCount(tUser.getSchoolId());
		map.put("list", list1);
		return "count/teacherLikeCount";
	}

	@RequestMapping(value = "teacherReceiveCount.html", method = RequestMethod.GET)
	public String teacherReceiveCount(ModelMap map, HttpServletResponse response, HttpServletRequest request) {
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");

		List<Count> list1 = countService.getTeacherReceiveCount(tUser.getSchoolId());
		map.put("list", list1);
		return "count/teacherReceiveCount";
	}

	@RequestMapping(value = "count.html", method = RequestMethod.GET)
	public String count(ModelMap map, HttpServletResponse response, HttpServletRequest request) {
		int total1 = 0;
		int total2 = 0;
		int total3 = 0;
		int total4 = 0;

		TUser tUser = (TUser) request.getSession().getAttribute("userObj");

		List<Count> list1 = countService.getTeacherReceiveCount(tUser.getSchoolId());
		if (list1.size() > 0) {
			for (Count count : list1) {
				total1 += count.getTeacherReceiveCount();
			}
		}
		List<Count> list2 = countService.getTeacherLikeCount(tUser.getSchoolId());
		if (list2.size() > 0) {
			for (Count count : list2) {
				total2 += count.getTeacherLikeCount();
			}
		}

		List<Count> list3 = countService.getStudentPost(tUser.getSchoolId());
		if (list3.size() > 0) {
			for (Count count : list3) {
				total3 += count.getStudentPost();
			}
		}
		List<Count> list4 = countService.getHotQeustion(tUser.getSchoolId());
		if (list4.size() > 0) {
			for (Count count : list4) {
				total4 += count.getHotQeustion();
			}
		}
		if(total4>20){
			total4=20;
		}
		map.put("total1", total1);
		map.put("total2", total2);
		map.put("total3", total3);
		map.put("total4", total4);
		return "count/count";
	}

	/**
	 * 获取热门问题帖子
	 */
	@RequestMapping("getHostPost")
	public void getIndexPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject jsonObject = new JSONObject();
			TUser tUser = (TUser) request.getSession().getAttribute("userObj");
			List<TPlatePost> list = postService.getHostPost(tUser.getId(),tUser.getSchoolId());
			jsonObject.put("list", list);
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}