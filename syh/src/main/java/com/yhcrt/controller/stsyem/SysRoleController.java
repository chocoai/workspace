
package com.yhcrt.controller.stsyem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.yhcrt.controller.BaseController;
import com.yhcrt.entity.system.SysRole;
import com.yhcrt.service.stsyem.SysRoleResService;
import com.yhcrt.service.stsyem.SysRoleService;
import com.yhcrt.service.stsyem.SysUserRoleService;
import com.yhcrt.utils.page.PageBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 系统角色控制层
 * @author 陈伟
 * 2017年5月23日 下午12:57:28
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController {

	
	@Resource(name="sysRoleService")
	private SysRoleService sysRoleService;
	@Resource(name="sysUserRoleService")
	private SysUserRoleService sysUserRoleService;
	@Resource(name="sysRoleResService")
	private SysRoleResService sysRoleResService;
	
	/**
	 * 
	 * @Title: listInfo
	 * @Description: 系统角色主页面
	 * @return: ModelAndView
	 */
	@RequestMapping("/index")
	public ModelAndView listInfo(){
		return new ModelAndView("stsyem/indexRole");
	}
	/**
	 * AJAX分页查询
	 * @param jsonString
	 * @return
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	public Map<String, Object> queryPage(Integer currentPage,String findContent){
		model.clear();
		try {
			 PageHelper.startPage(currentPage, getPageSize());
			 List<SysRole> list = sysRoleService.quertByParam(findContent);
			 PageBean<SysRole> pageInfo=new PageBean<SysRole>(list);
			 model.put("pageInfo",pageInfo);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return model;
	}
	
	/**
	 * @Title: updateInfo
	 * @Description: 获取角色信息
	 * @return: ModelAndView
	 */
	@RequestMapping("/updateInfo")
	public ModelAndView get(Integer cid){
		model.clear();
		SysRole info = null;
		try {
			if(cid != null){
				info = sysRoleService.getByCid(cid);
			}else {
				info = new SysRole();
			}
			 model.put("info", info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("stsyem/addInfoRole", model);
	}
	/**
	 * 
	 * @Title: doAddInfo
	 * @Description: 新增/更新数据
	 * @return: String
	 */
	@RequestMapping("/saveToUpdate")
	@ResponseBody
	public String saveToUpdate(@RequestParam String jsonString){
		String result="";
		if (jsonString==null||jsonString.equals("")) {
			result="nodata";
		}else {
			JSONObject roleInfoJson=JSONObject.fromObject(jsonString);
			SysRole roleInfo = (SysRole) roleInfoJson.toBean(roleInfoJson, SysRole.class);
			Integer cid = roleInfo.getCid();
			try {
				if(cid==null){
					//userInfo.setCreaRen();
					//userInfo.setCreaTime(DateUtil.getTime());
					sysRoleService.saveSelective(roleInfo);
				}else{
					sysRoleService.updateByPrimaryKeySelective(roleInfo);
				}
				result="success";
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	};
	
	/**
	 * 获取角色
	 * @param str
	 * @return
	 */
	@RequestMapping("/getRole")
	@ResponseBody
	public JSONArray getUnit(@RequestParam String str){
		System.out.println(str);
		List<SysRole> list=new ArrayList<SysRole>();
		if (!str.equals("")&&str!=null) {
			try {
					list=sysRoleService.quertByNotDisabledParam(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		JSONArray json = JSONArray.fromObject(list);
		return json;
	}
	
	/**
	 * 
	 * @Title: updateByCidSates
	 * @Description: 角色的禁用和激活
	 * @return: String
	 */
	@RequestMapping("/updateByCidSates")
	@ResponseBody
	public String updateByCidSates(@RequestParam("id") Integer id,@RequestParam("states") Integer states){
		String result="";
		if (id==null||states==null) {
			result="nodata";
		}else {
			try {
				sysRoleService.updateByCidSates(id, states);
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
	 * @Title: roleTask
	 * @Description:  建立角色权限关系
	 * @return: String
	 */
	@RequestMapping("/roleTask")
	@ResponseBody
	public String roleTask(@RequestParam("roleCid") Integer roleCid,Integer[] resCids){
		String result="";
		if (roleCid==null) {
			result="nodata";
		}else {
			try {
				sysRoleResService.saveSelectives(roleCid, resCids);
				result="success";
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 记录执行删除
	 * @param cids
	 * @return
	 */
	@RequestMapping("/deleteInfo")
	@ResponseBody
	public String deleteInfo(@RequestParam Integer[] cids){
		String result="";
		if (cids==null||cids.length==0) {
			result="nodata";
		}else {
			try {
				result=  sysRoleService.deleteAll(cids);
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	}
	
}
