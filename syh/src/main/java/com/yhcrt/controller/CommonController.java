package com.yhcrt.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yhcrt.entity.Tree;
import com.yhcrt.entity.queryStatistical.ThreeList;
import com.yhcrt.service.CommonService;
import com.yhcrt.service.signUp.ParticipatInfoService;
import com.yhcrt.shiro.TokenManager.TokenManager;
import com.yhcrt.utils.GetPinyin;

import net.sf.json.JSONArray;


/**
 * 系统参数
 * @author kejunzhong
 * 2017年5月12日
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/common")
public class CommonController extends BaseController{
	@Autowired
	private CommonService commonService;
	
	@Resource
	private ParticipatInfoService participatInfoService;
	
	
	/**
	 * 获取项目分类树形结构
	 * @return
	 */
	@RequestMapping("/getTree")
	@ResponseBody
	public JSONArray getTree(){
		List<Tree> trees = null;
		try {
			trees=commonService.getTree();
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray json = JSONArray.fromObject( trees );
		return json;
	}
	
	/**
	 * 异步获取分类树结构
	 * @return
	 */
	@RequestMapping("/getAsynTree")
	@ResponseBody
	public JSONArray queryClassToTree(Integer id,Integer otherParam){
		model.clear();
		JSONArray json = null;
		if(id==null){
			id = otherParam;
		}
		model.put("pid", id);
		model.put("ersionNum", TokenManager.getErsionNum());
		try {
			List<Tree> trees = commonService.getAsynTree(model);
			json = JSONArray.fromObject( trees );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return json;
	}
	
	/**
	 * 获取名称的中文首字母
	 * @return
	 */
	@RequestMapping("/getPname")
	@ResponseBody
	public String getPname(String name){
		return GetPinyin.getPinYinHeadChar(name);
	}
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView login(){
		return new ModelAndView("/queryPage");
	}
	
	
	/**
	 * 
	 * @Title: query
	 * @Description: 三榜信息
	 * @return: Map<String,Object>
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> query(Integer type,Integer order){
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		model.put("type", type);
		model.put("order", order);
		model.put("ersionNum", TokenManager.getErsionNum());
		List<ThreeList> list = participatInfoService.queryTeenageThree(model);
		model.put("list", list);
		return model;
	}
	
	
}