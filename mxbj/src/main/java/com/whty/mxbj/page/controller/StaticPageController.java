package com.whty.mxbj.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StaticPageController {

	@RequestMapping("down.html")
	public String down(){
		return "down";
	}
	
	@RequestMapping("downTest.html")
	public String downTest(){
		return "downTest";
	}
	
}
