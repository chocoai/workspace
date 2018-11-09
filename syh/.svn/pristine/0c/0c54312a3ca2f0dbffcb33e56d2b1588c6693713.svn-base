package com.yhcrt.controller.warnInfo;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yhcrt.controller.BaseController;
import com.yhcrt.service.warnInfo.WarnInfoService;

/**
 * 预警提示信息的控制层
 * @author heyun
 * 2017年5月25日
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/warnInfo")
public class WarnInfoController  extends BaseController{

	@Autowired
	private WarnInfoService warnInfoService;
	
	protected HashMap<String,Object> hashMap = new HashMap<String,Object>();
	/**
	 * @Title: listInfo
	 * @Description: 预警信息查询参赛年龄页面
	 * @return: ModelAndView
	 */
	@RequestMapping("/index")
	public ModelAndView listInfo(){
		return new ModelAndView("warnInfo/index");
	}
	/**
	 * AJAX分页预警信息查询参赛年龄页面
	 * @param currentPage,athleteName,teamName
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	public HashMap<String, Object> queryPage(Integer currentPage,String athleteName){
		hashMap.clear();
	//	hashMap.put("warnState", "2");
		hashMap.put("athleteName", athleteName);
		hashMap.put("currentPage", currentPage);
		hashMap.put("pageSize", pageSize);
		try {
			 HashMap<String, Object> maps =warnInfoService.selectAgeWarn(hashMap);
			 return maps;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @Title: toSumAge
	 * @Description: 预警信息查询年龄总和
	 * @return: ModelAndView
	 */
	@RequestMapping("/toSumAge")
	public ModelAndView toSumAge(){
		return new ModelAndView("warnInfo/sumAge");
	}
	/**
	 * AJAX分页预警信息查询年龄总和限制
	 * @param currentPage,athleteName,teamName
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toSumAgePage")
	@ResponseBody
	public HashMap<String, Object> toSumAgePage(Integer currentPage,String athleteName){
		hashMap.clear();
		hashMap.put("athleteName", athleteName);
		hashMap.put("currentPage", currentPage);
		hashMap.put("pageSize", pageSize);
		try {
			 HashMap<String, Object> maps =warnInfoService.selectAgeSumWarn(hashMap);
			 return maps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * @Title: toProjectNum
	 * @Description: 预警信息查询项目个数页面
	 * @return: ModelAndView
	 */
	@RequestMapping("/toPersonNum")
	public ModelAndView toProjectNum(){
		return new ModelAndView("warnInfo/personNum");
	}
	/**
	 * AJAX分页预警信息查询项目个数页面
	 * @param currentPage,athleteName,teamName
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toPersonNumPage")
	@ResponseBody
	public HashMap<String, Object> toPersonNumPage(Integer currentPage,String athleteName){
		hashMap.clear();
		hashMap.put("athleteName", athleteName);
		hashMap.put("currentPage", currentPage);
		hashMap.put("pageSize", pageSize);
		try {
			 HashMap<String, Object> maps =warnInfoService.selectPersonNumWarn(hashMap);
			 return maps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @Title: toProjectNum
	 * @Description: 预警信息查询单位项目个数页面
	 * @return: ModelAndView
	 */
	@RequestMapping("/toUnitNum")
	public ModelAndView toUnitNum(){
		return new ModelAndView("warnInfo/unitNum");
	}
	/**
	 * AJAX分页预警信息查询项目个数页面
	 * @param currentPage,athleteName,teamName
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toUnitNumPage")
	@ResponseBody
	public HashMap<String, Object> toUnitNumPage(Integer currentPage,String unitName){
		hashMap.clear();
		hashMap.put("unitName", unitName);
		hashMap.put("currentPage", currentPage);
		hashMap.put("pageSize", pageSize);
		try {
			 HashMap<String, Object> maps =warnInfoService.selectUnitNumWarn(hashMap);
			 return maps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * @Title: toNuptial
	 * @Description: 预警信息查询重名页面
	 * @return: ModelAndView
	 */
	@RequestMapping("/toNuptial")
	public ModelAndView toNuptial(){
		return new ModelAndView("warnInfo/nuptial");
	}
	/**
	 * AJAX分页预警信息查询重名页面
	 * @param currentPage,athleteName,teamName
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toNuptialPage")
	@ResponseBody
	public HashMap<String, Object> toNuptialPage(Integer currentPage,String athleteName){
		hashMap.clear();
		hashMap.put("athleteName", athleteName);
		hashMap.put("currentPage", currentPage);
		hashMap.put("pageSize", pageSize);
		try {
			 HashMap<String, Object> maps =warnInfoService.selectNuptialWarn(hashMap);
			 return maps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @Title: toAcrossUnit
	 * @Description: 预警信息查询跨单位参赛页面
	 * @return: ModelAndView
	 */
	@RequestMapping("/toAcrossUnit")
	public ModelAndView toAcrossUnit(){
		return new ModelAndView("warnInfo/acrossUnit");
	}
	/**
	 * AJAX分页预警信息查询跨单位参赛页面
	 * @param currentPage,athleteName,teamName
	 * @return 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toAcrossUnitPage")
	@ResponseBody
	public HashMap<String, Object> toAcrossUnitPage(Integer currentPage,String athleteName){
		hashMap.clear();
		hashMap.put("athleteName", athleteName);
		hashMap.put("currentPage", currentPage);
		hashMap.put("pageSize", pageSize);
		try {
			 HashMap<String, Object> maps =warnInfoService.selectAcrossUnitWarn(hashMap);
			 return maps;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
