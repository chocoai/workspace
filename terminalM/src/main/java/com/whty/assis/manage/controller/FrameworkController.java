package com.whty.assis.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.assis.base.controller.BaseController;
import com.whty.assis.sysrole.model.SysModular;

/**
 * 后台首页主框架
 * 
 * @author zhujg
 */
@Controller
@RequestMapping("/manage/framework")
public class FrameworkController extends BaseController {

//	private static Logger logger = Logger.getLogger(FrameworkController.class);

	/**
	 * 首页头部数据加载
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/header")
	public String header(Model model) throws Exception {
		return "framework/header";
	}

	/**
	 * 首页尾部数据加载
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/footer")
	public String footer(Model model) throws Exception {

		return "framework/footer";
	}

	/**
	 * 首页左边菜单数据加载
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/left")
	public String left(Model model, HttpServletRequest request) throws Exception {
		// 用户登录后台时把菜单权限放入session中
		List<SysModular> sysModularList = (List<SysModular>) request.getSession().getAttribute("sysModularList");
		model.addAttribute("sysModularList", sysModularList);
		return "framework/left";
	}

	/**
	 * 首页右边业务数据加载
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/main_right_index")
	public String main_right_index(Model model) throws Exception {

		return "framework/main_right_index";
	}
}
