/**
 * 
 */
package com.whty.assis.sysres.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.basicdata.model.DictData;
import com.whty.assis.basicdata.service.DictDataService;
import com.whty.assis.sysres.model.SysModular;
import com.whty.assis.sysres.service.SysModularService;
import com.whty.common.util.Constants;

/** 
 * @ClassName: SysResController 
 * @author: zjd
 * @date: 2018年6月4日 上午9:58:04  
 */
@Controller
@RequestMapping("/manage/sysModular")
public class SysModularController extends BaseController {
	
	@Autowired
	private SysModularService modularService;
	@Autowired
	private DictDataService dictDataService;
	
	@RequestMapping(value = "/list")
	public String modularList(HttpServletRequest request,HttpServletResponse response,ModelMap map){
		List<SysModular> sysModulars =null;
		List<DictData> buttons = null;
		Map<String, Object> paramMap =new HashMap<>();
		paramMap.put("typeId", Constants.SYS_BUTTONS);
		try{
			buttons = dictDataService.listByCondition(paramMap);
			sysModulars = modularService.allModular();
		}catch(Exception e){
			e.printStackTrace();
		}
		map.addAttribute("buttons", buttons);
		map.addAttribute("sysModulars", sysModulars);
		return "modular.list";
	}
	
	@RequestMapping(value="/loadList",method = RequestMethod.POST)
	@ResponseBody
	public void loadList(HttpServletRequest request,HttpServletResponse response,Integer modularId,String modularName
			,@RequestParam(name="pageValue",defaultValue="1")Integer page){
		if(page<1){
            page = 1;
        }
		SysModular record = new SysModular();
		record.setId(modularId);
		record.setModularName(modularName);
		PageHelper.startPage(page, 10);
		List<SysModular> sysModulars = modularService.selectByExample(record);
		PageInfo<SysModular> p = new PageInfo<SysModular>(sysModulars);
		JSONObject obj = new JSONObject();
		obj.put("list", sysModulars);
		obj.put("pageNum", p.getPageNum());
		obj.put("pages", p.getPages());
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		try {
			response.getWriter().print(obj);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		
	}
	
	@RequestMapping(value="/saveModular")
	@ResponseBody
	public int saveModular(HttpServletRequest request,HttpServletResponse response,SysModular sysModular){
		sysModular.setStatus(Constants.SYS_STATUS_NORMAL);
		return modularService.insert(sysModular);
	}
	
	@RequestMapping(value="/delModular")
	@ResponseBody
	public int delModular(HttpServletRequest request,HttpServletResponse response,Integer id){
		return modularService.deleteByPrimaryKey(id);
	}
	
	@RequestMapping(value="/udpModular")
	@ResponseBody
	public int udpModular(HttpServletRequest request,HttpServletResponse response,SysModular sysModular){
		int i = 0 ;
		if(sysModular.getId()!=null){
			sysModular.setStatus(Constants.SYS_STATUS_NORMAL);
			i= modularService.updateByPrimaryKey(sysModular);
		}
		return i;
	}
	
	@RequestMapping(value="/getModular")
	@ResponseBody
	public void getModular(HttpServletRequest request,HttpServletResponse response,Integer id){
		SysModular sysModular = modularService.getModularId(id);
		if(sysModular.getParentId()!=null && sysModular.getParentId().length()!=0 && sysModular.getParentId()!=""){
			sysModular.setParentName(modularService.selectByPrimaryKey(Integer.parseInt(sysModular.getParentId())).getModularName());
		}
		if(sysModular!=null){
			JSONObject obj = new JSONObject();
			obj.put("sysModular", sysModular);
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");
			try {
				response.getWriter().print(obj);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

}
