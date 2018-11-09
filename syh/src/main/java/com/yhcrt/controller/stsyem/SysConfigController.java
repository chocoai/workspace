
package com.yhcrt.controller.stsyem;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.yhcrt.controller.BaseController;
import com.yhcrt.entity.system.SysConfig;
import com.yhcrt.entity.system.SysDept;
import com.yhcrt.service.stsyem.SysConfigService;
import com.yhcrt.utils.ReflectionUtil;
import com.yhcrt.utils.StringUtils;

import net.sf.json.JSONObject;

/**  
* @author : 陈伟 
* @date : 2017年5月11日 上午9:38:58 
* @Description:系统部门管理
* @version 1.0  
*/
@Controller
@RequestMapping(value = "/sysConfig")
public class SysConfigController  extends BaseController {
	
	@Resource(name="sysConfigService")
	SysConfigService sysConfigService;


	/**
	 * 
	 * @Title: 获取配置信息
	 * @Description: 获取配置信息
	 * @return: ModelAndView
	 */
	@RequestMapping(value="/index")
	public ModelAndView get(ModelMap map){
		
		SysConfig sysConfig = null;
		try {
			sysConfig = sysConfigService.getByConfig();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("info", sysConfig);
		return new ModelAndView("/stsyem/indexConfig");
	}
	/**
	 * 
	 * @Title: edit
	 * @Description: 编辑配置信息
	 * @return: ModelAndView
	 */
	@RequestMapping(value="/saveToUpdate")
	@ResponseBody
	public String saveToUpdate(@RequestParam("jsonString") String jsonString){
		String result="";
		if (jsonString==null||jsonString.equals("")) {
			result="nodata";
		}else {
			JSONObject infoJson=JSONObject.fromObject(jsonString);
			SysConfig info = (SysConfig) JSONObject.toBean(infoJson, SysConfig.class);
			try {
				SysConfig sysConfig = sysConfigService.getByConfig();
				
				ReflectionUtil.bean2Bean(info,sysConfig,"siteName,sitePame,siteUrl,boottomText,logoUrl");
				sysConfigService.updateByPrimaryKeySelective(info);
				result="success";
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 
	 * @Title: backupDatebase
	 * @Description: 手动备份数据库
	 * @return: ModelAndView
	 */
	@RequestMapping(value="/backupDatebase")
	@ResponseBody
	public String backupDatebase(){
		String  error = null;
		try {
			sysConfigService.backupDatebase();
		} catch (Exception e) {
			error = "操作出现异常";
		}
		return error;
	}
	/**
	 * 
	 * @Title: restoreDatebase
	 * @Description: 执行数据库脚本
	 * @return: ModelAndView
	 */
	@RequestMapping(value="/restoreDatebase")
	@ResponseBody
	public String restoreDatebase(String filePath){
		String  error = null;
		if(StringUtils.isNotBlank(filePath)){
			try {
				sysConfigService.restoreDatebase(filePath);;
			} catch (Exception e) {
				error = "操作出现异常";
			}
		}else{
			error = "选择数据库脚本文件";
		}
		return error;
	}


}
