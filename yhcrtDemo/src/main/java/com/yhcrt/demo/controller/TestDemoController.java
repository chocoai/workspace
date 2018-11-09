package com.yhcrt.demo.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yhcrt.demo.model.SysUser;
import com.yhcrt.demo.model.TestDemo;
import com.yhcrt.demo.service.SysUserService;
import com.yhcrt.demo.service.TestDemoService;
import com.yhcrt.demo.util.Constant;
import com.yhcrt.demo.util.ExtJSBaseParameter;
import com.yhcrt.demo.util.Item;
import com.yhcrt.demo.util.ListView;
import com.yhcrt.demo.util.MD5;
import com.yhcrt.demo.util.QueryResult;
import com.yhcrt.demo.util.SystemCache;
import com.yhcrt.demo.util.UploadService;


/**
 * @author fengkun
 * @email 231788364@qq.com
 */
@Controller
@RequestMapping("/sys/test")
public class TestDemoController {

	@Resource
	private TestDemoService testDemoService;


	@RequestMapping(value = "/getTestDemoList",produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getTestDemo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer firstResult = Integer.valueOf(request.getParameter("start"));
		Integer maxResults = Integer.valueOf(request.getParameter("limit"));
		String sortedObject = null;
		String sortedValue = null;
		ObjectMapper mapper = new ObjectMapper();
		List<LinkedHashMap<String, Object>> sortedList = mapper.readValue(request.getParameter("sort"), List.class);
		for (int i = 0; i < sortedList.size(); i++) {
			Map<String, Object> map = sortedList.get(i);
			sortedObject = (String) map.get("property");
			sortedValue = (String) map.get("direction");
		}
		TestDemo testDemo = new TestDemo();
		String name = request.getParameter("name");
		if (StringUtils.isNoneBlank(name)) {
			testDemo.setName(name);
		}
		String tel = request.getParameter("tel");
		if (StringUtils.isNotBlank(tel)) {
			testDemo.setTel(tel);
		}
		String type = request.getParameter("type");
		if (StringUtils.isNotBlank(type)) {
			testDemo.setType(Integer.valueOf(type));;
		}
		String area = request.getParameter("area");
		if (StringUtils.isNotBlank(area)) {
			testDemo.setArea(area);;
		}
		testDemo.setFirstResult(firstResult);
		testDemo.setMaxResults(maxResults);
		Map<String, String> sortedCondition = new HashMap<String, String>();
		sortedCondition.put(sortedObject, sortedValue);
		testDemo.setSortedConditions(sortedCondition);
		
		QueryResult<TestDemo> queryResult = testDemoService.doPaginationQuery(testDemo);
		Long totalCount = testDemoService.getCount(testDemo);
		ListView<TestDemo> YhcrtListView = new ListView<TestDemo>();
		YhcrtListView.setData(queryResult.getResultList());
		YhcrtListView.setTotalRecord(totalCount);
		return JSONObject.toJSONString(YhcrtListView);
	}
	
	@RequestMapping("/deleteTestDemo")
	@ResponseBody
	public String deleteTestDemo(HttpServletRequest request, HttpServletResponse response, @RequestParam("ids") Long[] ids) throws IOException {
			boolean flag = testDemoService.deleteByPK(ids);
			if (flag) {
				return "{success:true}";
			} else {
				return "{success:false}";
			}
	}
	
	@RequestMapping(value = "/saveTestDemo", method = { RequestMethod.POST, RequestMethod.GET },produces = "application/json;charset=utf-8")
	@ResponseBody
	public String doSave(TestDemo entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ExtJSBaseParameter parameter = ((ExtJSBaseParameter) entity);
		if (Constant.CMD_EDIT.equals(parameter.getCmd())) {
			testDemoService.updateByPrimaryKeySelective(entity);
		} else if (Constant.CMD_NEW.equals(parameter.getCmd())) {
			entity.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			entity.setName(UploadService.upload(request));
			testDemoService.insert(entity);
		}
		parameter.setCmd(Constant.CMD_EDIT);
		parameter.setSuccess(true);
		return JSONObject.toJSONString(parameter);
	}

	
}
