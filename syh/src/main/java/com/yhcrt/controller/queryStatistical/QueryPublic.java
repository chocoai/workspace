package com.yhcrt.controller.queryStatistical;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yhcrt.controller.BaseController;

@Controller
@RequestMapping("/queryPublic")
public class QueryPublic extends BaseController{
	
	
	@RequestMapping("index")
	public ModelAndView getIndex(){
		return new ModelAndView("queryStatistical/queryPublic");
	}
}
