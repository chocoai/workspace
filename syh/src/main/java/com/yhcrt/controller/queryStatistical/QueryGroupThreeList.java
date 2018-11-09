package com.yhcrt.controller.queryStatistical;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yhcrt.controller.BaseController;
import com.yhcrt.entity.queryStatistical.ThreeList;
import com.yhcrt.service.signUp.ParticipatInfoService;
/**
 * 
 * 群体三榜查询
 * @author 陈伟
 * 2017年7月18日 下午2:05:48
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/queryGroupThreeList")
public class QueryGroupThreeList extends BaseController{

	@Resource
	private ParticipatInfoService participatInfoService;
	
	@RequestMapping("/index")
	public ModelAndView queryThreeList(){
		return new ModelAndView("queryStatistical/queryGroupThreeList");
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
		model.clear();
		model.put("type", type);
		model.put("order", order);
		List<ThreeList> list = participatInfoService.queryTeenageThree(model);
		model.put("list", list);
		return model;
	}
}
