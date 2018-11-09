package com.yhcrt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户控制菜单页面跳转
 * @author kejunzhong
 * 2017年5月12日
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/System")
public class MenuController {
	//进入菜单首页
	@RequestMapping("/index")
	public ModelAndView index(){
		return new ModelAndView("index");
	}
	
	//主页
	@RequestMapping("/home")
	public ModelAndView home(){
		return new ModelAndView("home");
	}
	
	//分类管理
	@RequestMapping("/classManager")
	public ModelAndView classManager(){
		return new ModelAndView("redirect:/classManager/index");
	}
	
	//项目管理
	@RequestMapping("/projectManager")
	public ModelAndView projectManager(){
		return new ModelAndView("redirect:/projectManager/index");
	}
	
	//计分管理
	@RequestMapping("/scoreRecord")
	public ModelAndView scoreRecord(){
		return new ModelAndView("redirect:/scoreRecord/index");
	}
	
	//奖惩管理
	@RequestMapping("/pubRewInfo")
	public ModelAndView pubRewInfo(){
		return new ModelAndView("redirect:/pubRewInfo/index");
	}
	
	//竞赛记录管理
	@RequestMapping("/comRecord")
	public ModelAndView comRecord(){
		return new ModelAndView("redirect:/comRecord/index");
	}
	
	//运动员基本信息管理
	@RequestMapping("/athleteBaseInfo")
	public ModelAndView athleteBaseInfo(){
		return new ModelAndView("redirect:/athleteBaseInfo/index");
	}
	
	//运动员参赛信息管理
	@RequestMapping("/athleteParticipat")
	public ModelAndView athleteParticipat(){
		return new ModelAndView("redirect:/athleteParticipat/index");
	}
	//运动员参赛信息管理
	@RequestMapping("/participatInfo")
	public ModelAndView participatInfo(){
		return new ModelAndView("redirect:/participatInfo/index");
	}

	//参赛单位管理
	@RequestMapping("/unitInfo")
	public ModelAndView unitInfo(){
		return new ModelAndView("redirect:/unitInfo/index");
	}
	//预警提醒信息查询
	@RequestMapping("/warnInfo")
	public ModelAndView warnInfo(){
		return new ModelAndView("redirect:/warnInfo/index");
	}
	/***************************************************查询统计模块<end>*****************************************/	
	//奖惩信息统计查询
	@RequestMapping("/queryPubRew")
	public ModelAndView queryPubRew(){
		return new ModelAndView("redirect:/queryPubRew/index");
	}
	//成绩信息统计查询
	@RequestMapping("/queryScoreInfo")
	public ModelAndView queryScoreInfo(){
		return new ModelAndView("redirect:/queryScoreInfo/index");
	}
	//青少年三榜查询
	@RequestMapping("/queryTeenagersThreeList")
	public ModelAndView queryTeenagersThreeList(){
		return new ModelAndView("redirect:/queryTeenagersThreeList/index");
	}
	//群体三榜查询
	@RequestMapping("/queryGroupThreeList")
	public ModelAndView queryGroupThreeList(){
		return new ModelAndView("redirect:/queryGroupThreeList/index");
	}
	//项目信息统计查询
	@RequestMapping("/queryProjectList")
	public ModelAndView queryProjectList(){
		return new ModelAndView("redirect:/queryProjectList/index");
	}
	@RequestMapping("/queryDetailList")
	public ModelAndView queryDetailList(){
		return new ModelAndView("redirect:/queryDetailList/index");
	}
	@RequestMapping("/queryAthleteList")
	public ModelAndView queryAthleteList(){
		return new ModelAndView("redirect:/queryAthleteList/index");
	}
	/***************************************************系统配置*****************************************/	
	//系统部门管理
	@RequestMapping("/sysDept")
	public ModelAndView sysDept(){
		return new ModelAndView("redirect:/sysDept/index");
	}
	
	//系统用户管理
	@RequestMapping("/sysMuserInfo")
	public ModelAndView sysUser(){
		return new ModelAndView("redirect:/sysMuserInfo/index");
	}
	//系统角色管理
	@RequestMapping("/sysRole")
	public ModelAndView sysRole(){
		return new ModelAndView("redirect:/sysRole/index");
	}
	//系统权限管理
	@RequestMapping("/sysRes")
	public ModelAndView sysRes(){
		return new ModelAndView("redirect:/sysRes/index");
	}
	//系统权限管理
	@RequestMapping("/sysDict")
	public ModelAndView sysDict(){
		return new ModelAndView("redirect:/sysDict/index");
	}
	//系统配置文件
	@RequestMapping("/sysConfig")
	public ModelAndView sysConfig(){
		return new ModelAndView("redirect:/sysConfig/index");
	}
	//系统日志文件
	@RequestMapping("/sysLog")
	public ModelAndView sysLog(){
		return new ModelAndView("redirect:/sysLog/index");
	}
	//个人资料
	@RequestMapping("/indexUser")
	public ModelAndView indexUser(){
		return new ModelAndView("redirect:/user/indexUser");
	}
	//修改密码
	@RequestMapping("/indexPwd")
	public ModelAndView indexPwd(){
		return new ModelAndView("redirect:/user/indexPwd");
	}
	
	/***************************************************系统配置<end>*****************************************/	
	//成绩录入管理
	@RequestMapping("/inputScore")
	public ModelAndView inputScore(){
		return new ModelAndView("redirect:/inputScore/index");
	}
	
	//成绩基本信息管理
	@RequestMapping("/scoreBaseInfo")
	public ModelAndView scoreBaseInfo(){
		return new ModelAndView("redirect:/scoreBaseInfo/index");
	}
	
	//成绩综合查询
	@RequestMapping("/queryPublic")
	public ModelAndView scoreQuery(){
		return new ModelAndView("redirect:/queryPublic/index");
	}
}
