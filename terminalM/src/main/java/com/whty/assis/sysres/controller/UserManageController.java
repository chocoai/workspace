/**
 * 
 */
package com.whty.assis.sysres.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whty.assis.sysres.model.SysRole;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.whty.assis.base.controller.BaseController;
import com.whty.assis.basicdata.model.Area;
import com.whty.assis.basicdata.model.School;
import com.whty.assis.basicdata.service.AreaService;
import com.whty.assis.basicdata.service.SchoolService;
import com.whty.assis.sysres.model.SysModular;
import com.whty.assis.sysres.model.TaManageUserInfo;
import com.whty.assis.sysres.service.SysModularService;
import com.whty.assis.sysres.service.SysRoleService;
import com.whty.assis.sysres.service.SysUserService;
import com.whty.common.util.Constants;
import com.whty.common.util.Md5PwdEncoder;

/** 
 * @ClassName: UserManageController 
 * @author: zjd
 * @date: 2018年6月7日 上午9:09:44  
 */
@Controller
@RequestMapping("/manage/user")
public class UserManageController extends BaseController{
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private SchoolService schoolService;
	@Autowired
	private SysRoleService roleService;
	@Autowired
	private SysModularService sysModularService;
	
	/**
	 * 
	* @Title: 用户主页  
	* @param @param request
	* @param @param response
	* @param @param map
	* @param @return    设定文件  
	* @return String    返回类型  
	* @throws
	 */
	@RequestMapping(value="/list")
	public String list(HttpServletRequest request ,HttpServletResponse response,ModelMap map){
		List<TaManageUserInfo> users  = sysUserService.selectUsers();
		List<Area> province = areaService.getAreaByLevelId(1);
		List<SysRole> sysRoles = roleService.selectRoles();
		List<School> schools = schoolService.getAllSchools();
		map.put("schools", schools);
		map.put("users", users);
		map.put("province", province);
		map.put("sysRoles", sysRoles);
		return "managerUser.adminList";
	}
	/**
	 * 
	* @Title: 区域学校四级联动  
	* @param @param request
	* @param @param response
	* @param @param parentId    设定文件  
	* @return void    返回类型  
	* @throws
	 */
	@RequestMapping(value="/getArea")
	@ResponseBody
	public void getArea(HttpServletRequest request ,HttpServletResponse response,Integer provinceId,Integer cityId,Integer areaId){
		try {
			List<Area> areas = null;
			List<School> schools = null;
			if(provinceId!=null){
				areas = areaService.getAreaByParentId(provinceId);
			}
			if(cityId!=null){
				areas = areaService.getAreaByParentId(cityId);
			}
			if(areaId!=null){
				areas = areaService.getAreaByParentId(areaId);
			}
			if(areas!=null){
				schools = schoolService.getSchools(provinceId, cityId, areaId);
			}
			JSONObject obj = new JSONObject();
			obj.put("areas", areas);
			obj.put("schools", schools);
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	* @Title: 添加用户  
	* @param @param request
	* @param @param response
	* @param @param taManageUserInfo
	* @param @return    设定文件  
	* @return Integer    返回类型  
	* @throws
	 */
	@RequestMapping(value="/addUser")
	@ResponseBody
	public Integer addUser(HttpServletRequest request ,HttpServletResponse response,TaManageUserInfo taManageUserInfo){
		int i =0;
		TaManageUserInfo user = sysUserService.selectByAccount(taManageUserInfo.getAccount());
		if(user!=null){
			return i;
		}
		taManageUserInfo.setPassword(Md5PwdEncoder.encodePassword(taManageUserInfo.getPassword()));
		taManageUserInfo.setCreateTime(new Date());
		taManageUserInfo.setEffectiveTime(new Date());
		taManageUserInfo.setLoginCount(0);
		taManageUserInfo.setStatus(Constants.SYS_STATUS_NORMAL);
		taManageUserInfo.setUserType("a");
		taManageUserInfo.setPlatformCodeId(1);
		i = sysUserService.insert(taManageUserInfo);
		return i;
	}
	
	/**
	 * 
	* @Title: gerUser  
	* @param @param request
	* @param @param response
	* @param @param id    设定文件  
	* @return void    返回类型  
	* @throws
	 */
	@RequestMapping(value="/getUser")
	public void gerUser(HttpServletRequest request ,HttpServletResponse response,Integer id){
		try {
			TaManageUserInfo taManageUserInfo  = sysUserService.selectById(id);
			JSONObject obj = new JSONObject();
			obj.put("taManageUserInfo", taManageUserInfo);
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(obj.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 
	* @Title: 更新用户  
	* @param @param request
	* @param @param response
	* @param @param taManageUserInfo
	* @param @return    设定文件  
	* @return Integer    返回类型  
	* @throws
	 */
	@RequestMapping(value="/updateUser")
	@ResponseBody
	public Integer updateUser(HttpServletRequest request ,HttpServletResponse response,TaManageUserInfo taManageUserInfo){
		int i =0;
		TaManageUserInfo taManageUserInfo2 = sysUserService.selectById(taManageUserInfo.getId());
		if(!taManageUserInfo2.getPassword().equals(taManageUserInfo.getPassword())){
			taManageUserInfo2.setPassword(Md5PwdEncoder.encodePassword(taManageUserInfo.getPassword()));
		}
		taManageUserInfo2.setUpdateTime(new Date());
		taManageUserInfo2.setAccount(taManageUserInfo.getAccount());
		taManageUserInfo2.setProvinceCode(taManageUserInfo.getProvinceCode());
		taManageUserInfo2.setCityCode(taManageUserInfo.getCityCode());
		taManageUserInfo2.setAreaCode(taManageUserInfo.getAreaCode());
		taManageUserInfo2.setSchoolId(taManageUserInfo.getSchoolId());
		taManageUserInfo2.setRoleId(taManageUserInfo.getRoleId());
		taManageUserInfo2.setUserName(taManageUserInfo.getUserName());
		taManageUserInfo2.setDepartment(taManageUserInfo.getDepartment());
		taManageUserInfo2.setStatus(taManageUserInfo.getStatus());
		i = sysUserService.update(taManageUserInfo2);
		return i;
	}
	
	/**
	 * 
	* @Title: 登录  
	* @param @param request
	* @param @param response
	* @param @param account
	* @param @param password
	* @param @return    设定文件  
	* @return String    返回类型  
	* @throws
	 */
	@RequestMapping(value ="/login")
	@ResponseBody
	public void login(HttpServletRequest request ,HttpServletResponse response,String account,String password){
		JSONObject jsonObj = new JSONObject();
		try {
			password =Md5PwdEncoder.encodePassword(password);
			UsernamePasswordToken token = new UsernamePasswordToken(account, password);
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			TaManageUserInfo user = sysUserService.selectByAccount(account);
			user.setLoginCount(user.getLoginCount()+1);
			sysUserService.update(user);
			List<SysModular> modularList = sysModularService.listAllSysModular(user.getId());
			List<SysModular> modularList2 = sysModularService.listAllUserModular(user.getId());
			subject.getSession().setAttribute("loginUser", user);
			subject.getSession().setAttribute("modularList", modularList);
			subject.getSession().setAttribute("modularList2", modularList2);
			jsonObj.put("result", true);
		} catch (UnknownAccountException e) {
			e.printStackTrace();
			jsonObj.put("result", false);
			jsonObj.put("message", e.getMessage());
		} catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			jsonObj.put("result", false);
			jsonObj.put("message", e.getMessage());
		} catch (DisabledAccountException e) {
			e.printStackTrace();
			jsonObj.put("result", false);
			jsonObj.put("message", e.getMessage());
		} catch (AuthenticationException e) {
			e.printStackTrace();
			jsonObj.put("result", false);
			jsonObj.put("message", e.getMessage());
		}
		try {
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 登出功能
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/quit")
	public String quit(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		req.getSession().invalidate();
		return "login";
	}
	
	/**
	 * 首页
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		return "index";
	}
}