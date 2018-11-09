package com.yhcrt.healthcloud.system.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.system.entity.SysDictionary;
import com.yhcrt.healthcloud.system.entity.SysDictionaryExample;
import com.yhcrt.healthcloud.system.mapper.SysDictionaryMapper;
import com.yhcrt.healthcloud.system.service.SysDictService;
import com.yhcrt.healthcloud.util.DateUtil;
import com.yhcrt.healthcloud.util.DictSet;
import com.yhcrt.healthcloud.util.UploadUtils;
import com.yhcrt.healthcloud.web.listener.ServerEnv;

/**
 * @ClassName: SysDictController
 * @Description:
 * @version V1.0
 * @author rpf
 * @date: 2017年5月26日
 */

@Controller
@RequestMapping("/dict")
public class SysDictController {

	@Autowired
	private SysDictService sysDictService;
	@Autowired	
    private SysDictionaryMapper sysDictionaryMapper;

	/**
	 * 查询所有数据字典项
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		List<SysDictionary> dictList = sysDictService.listAllSysDict();
		modelMap.put("dictList", dictList);
		return "dict/list";
	}

	/**
	 * 添加数据字典预处理
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/toAdd", method = RequestMethod.GET)
	public String toAdd(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String parentId = request.getParameter("parentId");
		SysDictionary dict = new SysDictionary();
		dict.setParentId(parentId);
		request.setAttribute("dict", dict);
		return "dict/add";
	}

	/**
	 * 添加数据字典项
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param dict
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, SysDictionary dict) {
		String dictId = request.getParameter("dictId");
		// 上传字典图标
		String dictIcon = UploadUtils.uploadFile(request, "dictIconFile", "icon");
		if (StringUtils.isNoneBlank(dictIcon)) {
			dict.setDictIcon(dictIcon);
		}
		if (StringUtils.isNotBlank(dictId)) {
			SysDictionary dictionary = sysDictService.getSysDictByDictId(Integer.valueOf(dictId));
			BeanUtils.copyProperties(dict, dictionary, "createTime", "createUser", "status");
			sysDictService.updateByDictId(dictionary);
		} else {
			dict.setCreateTime(DateUtil.getDateTime());
			dict.setStatus(Constants.STATUS_NORMAL);
			sysDictService.insert(dict);
		}
		return "redirect:list";
	}

	/**
	 * 修改数据字典预处理
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/toEdit", method = RequestMethod.GET)
	public String toEdit(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		String dictId = request.getParameter("dictId");
		if (StringUtils.isNotBlank(dictId)) {
			SysDictionary dict = sysDictService.getSysDictByDictId(Integer.valueOf(dictId));
			modelMap.put("dict", dict);
		}
		return "dict/add";
	}

	/**
	 * 删除数据字典项
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			RedirectAttributes attr) {
		String dictId = request.getParameter("dictId");
		if (StringUtils.isNotBlank(dictId)) {
			// 物理删除
			sysDictService.deleteByDictId(Integer.valueOf(dictId));
			// 逻辑删除，更新数据状态标识位，不删除数据库中的记录
			// SysDictionary dict =
			// sysDictService.getSysDictByDictId(Integer.valueOf(dictId));
			// dict.setStatus(Constants.STATUS_DISABLE);
			// sysDictService.updateByDictId(dict);
		}
		return "redirect:list";
	}

	/**
	 * 重新加载数据字典
	 * 
	 * @param request
	 * @param response
	 * @param map
	 */
	@RequestMapping(value = "/reload")
	public void reload(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		try {
			JSONObject jsonObj = new JSONObject();
			List<SysDictionary> dicList = sysDictService.listAllSysDict();
			DictSet.reload(dicList, ServerEnv.getInstance().getServletContext());
			jsonObj.put("result", true);
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 字典表二级联动下拉框
	 * @param request
	 * @param response
	 * @param parentValue
	 * @return
	 */
    @RequestMapping(value = "/getTypeItem", method = RequestMethod.POST)	
    @ResponseBody
    public List<SysDictionary> getTypeItem(HttpServletRequest request,HttpServletResponse response,String parentValue,String type){	
       SysDictionaryExample example = new SysDictionaryExample();
       example.createCriteria().andDictEnNameEqualTo(type).andDictValueEqualTo(parentValue).andStatusEqualTo(0);
       List<SysDictionary> list = sysDictionaryMapper.selectByExample(example);
       List<SysDictionary> list2 = new ArrayList<SysDictionary>();
       if(list.size()>0){
    	   SysDictionary sysDictionary = list.get(0);
    	   example = new SysDictionaryExample();
           example.createCriteria().andParentIdEqualTo(String.valueOf(sysDictionary.getDictId())).andStatusEqualTo(0);
           list2 = sysDictionaryMapper.selectByExample(example);
       }
       return list2;		
    }
    

}
