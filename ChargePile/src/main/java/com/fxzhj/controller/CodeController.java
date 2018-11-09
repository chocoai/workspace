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

import com.alibaba.fastjson.JSON;
import com.fxzhj.model.Code;
import com.fxzhj.model.Type;
import com.fxzhj.service.CodeService;
import com.fxzhj.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.util.StringUtil;

/**
 * 二维码信息
 * @author jifei
 *
 */
@Controller
@RequestMapping(value = "/code")
public class CodeController {
	
	@Autowired
	private CodeService codeService;
	
	@Autowired
	private TypeService typeService;
	
	/**
	 * 跳转二维码绑定界面
	 * @return
	 */
	@RequestMapping(value = "codeJsp")
	public String codeJsp(){
		return "code.jsp";
	}
	
	/**
	 * 根据节点查询是否绑定
	 * @param code
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryCodeByNid")
	@ResponseBody
	public Map<String, Object> queryCodeByNid(Code code, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(request.getParameter("page")) && StringUtil.isNotEmpty(request.getParameter("rows"))){
			int pageNo = Integer.parseInt((String) request.getParameter("page"));
			// 当前检索的条数
			int pageSize = Integer.parseInt((String) request.getParameter("rows"));
			PageHelper.startPage(pageNo, pageSize);
		}
		List<Code> list =codeService.queryCodeByUrl(code);
		PageInfo<Code> pageList = null;
		if(list!=null&&list.size()>0){
			pageList = new PageInfo<Code>(list);
		}
		
		if (pageList != null && pageList.getList() != null && pageList.getList().size()>0) {
			map.put("total", pageList.getTotal());
			map.put("rows", pageList.getList());
		}else{
			map.put("total", 0);
			map.put("rows", new ArrayList<Code>());
		}
		return map;
	}
	
	/**
	 * 查询未绑定二维码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryCodeUnbounded")
	@ResponseBody
	public Map<String, Object> queryCodeUnbounded(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isNotEmpty(request.getParameter("page")) && StringUtil.isNotEmpty(request.getParameter("rows"))){
			int pageNo = Integer.parseInt((String) request.getParameter("page"));
			// 当前检索的条数
			int pageSize = Integer.parseInt((String) request.getParameter("rows"));
			PageHelper.startPage(pageNo, pageSize);
		}
		List<Code> list =codeService.queryCodeUnbounded();
		PageInfo<Code> pageList = null;
		if(list!=null&&list.size()>0){
			pageList = new PageInfo<Code>(list);
		}
		
		if (pageList != null && pageList.getList() != null && pageList.getList().size()>0) {
			map.put("total", pageList.getTotal());
			map.put("rows", pageList.getList());
		}else{
			map.put("total", 0);
			map.put("rows", new ArrayList<Code>());
		}
		return map;
	}
	
	/**
	 * 根据二维码id修改状态
	 * @param id,nodeUrl
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateCode")
	@ResponseBody
	public Map<String, Object> updateCode(Code code, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		String json=request.getParameter("data");
		if (StringUtil.isNotEmpty(json)) {
			code = JSON.parseObject(json, Code.class);
			try {
				codeService.updateCode(code);
				map.put("state", "SUCCESS");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("state", "ERROR");
			}
		}else{
			map.put("state", "FALSE");
		}
		return map;
	}
	
	/**
	 * 根据二维码编号解绑
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/unbundlingCode")
	@ResponseBody
	public Map<String, Object> unbundlingCode(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(id)) {
			try {
				codeService.unbundlingCode(Long.parseLong(id));
				map.put("state", "SUCCESS");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("state", "ERROR");
			}
		}else{
			map.put("state", "FALSE");
		}
		return map;
	}
	
	/**
	 * 二维码绑定类型
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryAll")
	@ResponseBody
	public List<Type> queryAll(){
		Type type = new Type();
		type.setValue("--请选择--");
		List<Type> list = typeService.showCombobox();
		list.add(0, type);
		return list;
	}
	
	/**
	 * 批量生成二维码
	 * @return
	 */
	@RequestMapping(value = "/batchCode")
	@ResponseBody
	public Map<String, Object> batchCode(){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			codeService.batchCode();
			map.put("state","SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("state","ERROR");
		}
		return map;
	}
	
}
