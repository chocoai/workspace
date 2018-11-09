package com.fxzhj.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fxzhj.model.Area;
import com.fxzhj.model.Community;
import com.fxzhj.service.AreaService;
import com.fxzhj.service.CommunityService;

/**
 * 省,市,区 区域表
 * @author jifei
 *
 */
@Controller
@RequestMapping(value = "/area")
public class AreaController {
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private CommunityService communityService;
	
	/**
	 * 查询tree
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "areaTree")
	@ResponseBody
	public List<Map<String, Object>> areaTree(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();  
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("id","");
		root.put("text","全部");
		root.put("state", "closed");
		List<Map<String, Object>> childs = insertTree();
		root.put("children", childs);
		root.put("attributes", 0);
		result.add(root);
		return result;
	}

	private List<Map<String, Object>> insertTree() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();  
		List<Area> listArea=areaService.queryAreaTree();
		if(listArea!=null && listArea.size()>0){
			for(Area bean:listArea){
				Map<String, Object> node = new HashMap<String, Object>();
				node.put("id", bean.getId());  
				node.put("text", bean.getName());
				node.put("state", "closed");
				//返回的是childs  
		        List<Map<String, Object>> childs = insertChilddrens(bean.getId(),bean.getDeep());
		        node.put("children", childs);
		        node.put("attributes", bean.getDeep());
		        result.add(node);
			}
		}
		return result;
	}

	//根据省其子孙节点
	private List<Map<String, Object>> insertChilddrens(Integer id,Byte deep) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();  
		try {
			if(deep==3){
				List<Community> listCommunity=communityService.queryByParentId(id);
				if(listCommunity!=null && listCommunity.size()>0){
					for(Community bean:listCommunity){
						Map<String, Object> node = new HashMap<String, Object>();
						node.put("id", bean.getCommunityId());
						node.put("text", bean.getName());
						node.put("attributes", 9);
						result.add(node);
					}
				}
			}
			List<Area> listArea=areaService.queryChild(id,deep);
			if(listArea!=null && listArea.size()>0){
				for(Area bean:listArea){
					Map<String, Object> node = new HashMap<String, Object>();
					node.put("id", bean.getId());  
					node.put("text", bean.getName());
					node.put("state", "closed");
					//返回的是childs  
			        List<Map<String, Object>> childs = insertChilddrens(bean.getId(),bean.getDeep());
			        node.put("children", childs);
			        node.put("attributes", bean.getDeep());
			        result.add(node);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return result;  
	}

}
