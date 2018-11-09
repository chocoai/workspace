package com.yhcrt.iHealthCloud.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;

@Controller
@RequestMapping(value = "/mall")
public class MallController {
	
//	@Autowired
//	private MemberDeviceMapper memberDeviceMapper;

	
    
	@RequestMapping(value = "/index")
	public String uploadStepData(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws IOException {
        JSONArray jsonarray = new  JSONArray();
        jsonarray.add(1);
        jsonarray.add(2);
        jsonarray.add(3);
        modelMap.put("result", jsonarray);
		return "mall.html";
	}
	

}
