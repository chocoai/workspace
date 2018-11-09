/**
 * 
 */
package com.whty.assis.sysres.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whty.assis.sysres.model.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whty.assis.base.controller.BaseController;
import com.whty.assis.sysres.model.SysModular;
import com.whty.assis.sysres.model.SysRoleModular;
import com.whty.assis.sysres.service.SysModularService;
import com.whty.assis.sysres.service.SysRoleModularService;
import com.whty.assis.sysres.service.SysRoleService;
import com.whty.common.util.Constants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @ClassName: SysResController
 * @author: zjd
 * @date: 2018年6月4日 上午9:58:04
 */
@Controller
@RequestMapping("/manage/sysRole")
public class SysRoleController extends BaseController{
	
	@Autowired
	private SysModularService modularService;
	
	@Autowired
	private SysRoleService roleService;
	
	@Autowired
	private SysRoleModularService srmService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 
	* @Title: 查询所有角色  
	* @param @param request
	* @param @param response
	* @param @param map
	* @param @return    设定文件  
	* @return String    返回类型  
	* @throws
	 */
	@RequestMapping(value = "/list")
	public String modularList(HttpServletRequest request,HttpServletResponse response,ModelMap map){
		List<SysRole> sysRoles = roleService.selectRoles();
		map.addAttribute("sysRoles", sysRoles);
		return "sysrole.list";
	}
	
	/**
	 * 
	* @Title: 权限资源树  
	* @param @param request
	* @param @param response
	* @param @param role_id    设定文件  
	* @return void    返回类型  
	* @throws
	 */
	@RequestMapping(value="/queryModular")
	public void queryModular(HttpServletRequest request,HttpServletResponse response,String role_id){
		try {
			//List<SysRoleModular> sysRoleModulars = srmService.selectByRoleId(role_id);
			List<SysModular> list = modularService.allModulars();
			JSONArray jsonArray = new JSONArray();
			for (SysModular res : list) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", res.getId());
				jsonObj.put("name", res.getModularName());
				jsonObj.put("pId", res.getParentId());
				
				jsonArray.add(jsonObj);
			}
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	* @Title: 创建角色  
	* @param @param request
	* @param @param response
	* @param @param sysRole
	* @param @return    设定文件  
	* @return int    返回类型  
	* @throws
	 */
	@RequestMapping(value="/saveSole")
	@ResponseBody
	public int saveModular(HttpServletRequest request,HttpServletResponse response,SysRole sysRole){
		sysRole.setStatus(Constants.SYS_STATUS_NORMAL);
		sysRole.setCreateTime(sdf.format(new Date()));
		sysRole.setUpdateTime(sdf.format(new Date()));
		return roleService.insert(sysRole);
	}
	/**
	* @Title: 删除角色  
	* @param @param request
	* @param @param response
	* @param @param sysRole
	* @param @return    设定文件  
	* @return int    返回类型  
	* @throws
	 */
	@RequestMapping(value="/delSole")
	@ResponseBody
	public int delModular(HttpServletRequest request,HttpServletResponse response,Integer id){
		return roleService.delete(id);
	}
	
	/**
	 * 
	* @Title: 更新角色  
	* @param @param request
	* @param @param response
	* @param @param sysRole
	* @param @return    设定文件  
	* @return int    返回类型  
	* @throws
	 */
	@RequestMapping(value="/udpSole")
	@ResponseBody
	public int udpModular(HttpServletRequest request,HttpServletResponse response,SysRole sysRole){
		sysRole.setUpdateTime(sdf.format(new Date()));
		return roleService.update(sysRole);
	}
	
	/**
	 * 
	* @Title: 权限资源树带返回值勾选  
	* @param @param request
	* @param @param response
	* @param @param roleId    设定文件  
	* @return void    返回类型  
	* @throws
	 */
	@RequestMapping(value="/authority")
	@ResponseBody
	public void authority(HttpServletRequest request,HttpServletResponse response,Integer roleId){
		try {
			List<SysRoleModular> roleModulars = srmService.selectByRoleId(roleId);
			List<SysModular> modulars = modularService.allModulars();
			JSONArray jsonArray = new JSONArray();
			for (SysModular sysModular : modulars) {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("id", sysModular.getId());
				jsonObj.put("name", sysModular.getModularName());
				jsonObj.put("pId", sysModular.getParentId());
				for (SysRoleModular sysRoleModular : roleModulars) {
					if (sysRoleModular.getModularId().equals(sysModular.getId().toString())) {
						jsonObj.put("checked", true);
						break;
					}
				}
				jsonArray.add(jsonObj);
			}
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	* @Title: 修改权限并保存  
	* @param @param request
	* @param @param response
	* @param @param roleId
	* @param @param modularIds
	* @param @return    设定文件  
	* @return String    返回类型  
	* @throws
	 */
	@RequestMapping(value="/saveRolePermission")
	@ResponseBody
	public String saveRolePermission(HttpServletRequest request,HttpServletResponse response,String roleId,String modularIds){
		String[] resIds = modularIds.split(",");
		String result =srmService.insert(roleId, resIds);
		return result;
	}
}