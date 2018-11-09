package com.yhcrt.controller.queryStatistical;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yhcrt.service.project.ClassManagerService;

@Controller
@RequestMapping("/queryPubRew")
public class QueryPubRewController {
	@Resource
	public ClassManagerService classManagerService;
	
	//查询奖惩
	@RequestMapping("/index")
	public ModelAndView index(){
		return new ModelAndView("queryStatistical/queryPubRew");
	}
}
