
package com.yhcrt.controller.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yhcrt.controller.BaseController;
import com.yhcrt.entity.system.SysMuserInfo;
import com.yhcrt.entity.system.SysUser;
import com.yhcrt.service.stsyem.SysMuserInfoService;
import com.yhcrt.service.stsyem.SysUserService;
import com.yhcrt.utils.DateUtil;
import com.yhcrt.utils.PublicUtil;
import com.yhcrt.utils.ReflectionUtil;

import net.sf.json.JSONObject;

/**
 * TODO
 * @author 陈伟
 * 2017年5月30日 下午7:16:32
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Resource(name="sysUserService")
	private SysUserService sysUserService;
	
	@Resource(name="sysMuserInfoService")
	private SysMuserInfoService sysMuserInfoService;
	
	
	
	/**
	 * @Title: getByUser
	 * @Description: 获取当前用户用户信息
	 * @return: ModelAndView
	 */
	@RequestMapping("/indexUser")
	public ModelAndView getByUser(HttpServletRequest request){
		
		SysMuserInfo info;
		try {
			info = sysMuserInfoService.getByCid(getSessionCid());
			model.put("info", info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("user/indexUser", model);
	}
	/**
	 * @Title: updateByUser
	 * @Description: 修改个人信息
	 * @return: ModelAndView
	 */
	@RequestMapping("/updateByUser")
	@ResponseBody
	public String updateByUser(HttpServletRequest request,@RequestParam String jsonString){
		
		String result="";
		if (jsonString==null||jsonString.equals("")) {
			result="nodata";
		}else {
			try {
				JSONObject userInfoJson=JSONObject.fromObject(jsonString);
				SysMuserInfo info = (SysMuserInfo) JSONObject.toBean(userInfoJson, SysMuserInfo.class);
				SysMuserInfo Minfo = sysMuserInfoService.getByCid(getSessionCid());
				ReflectionUtil.bean2Bean(Minfo,info,"cid,userId,orderNum,state,backup");
				Minfo.setLastUpdateTime(DateUtil.getTime());
				Minfo.setLastUpdateIp(PublicUtil.getIp());
				sysMuserInfoService.updateByPrimaryKeySelective(Minfo);
				
				//修改的信息更新到session
				setSessionUser(Minfo);
				result="success";
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}	
		}
		return result;
	}
	@RequestMapping("/indexPwd")
	public ModelAndView modifyPwd(){
		return new ModelAndView("user/indexPwd");
	}
	
	/**
	 * @Title: isPwd
	 * @Description: 判断密码是否正确
	 * @return: ModelAndView
	 */
	@RequestMapping("/isPwd")
	@ResponseBody
	public String isPwd(HttpServletRequest request){
		
		String result="";
			try {
				String pwd = request.getParameter("pwd");
				SysUser sysUser = sysUserService.getByCid(getSessionCid());				
				if(!pwd.equals(sysUser.getPassword())){
					result = "errorPwd";
				}else{
					result = "success";
				}
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
			return result;
		
	}
	/**
	 * @Title: modifyPwd
	 * @Description: 修改密码
	 * @return: ModelAndView
	 */
	@RequestMapping("/modifyPwd")
	@ResponseBody
	public String modifyPwd(HttpServletRequest request){
		
		String result="";
			try {
				String pwd = request.getParameter("pwd");
				
				SysUser sysUser = sysUserService.getByCid(getSessionCid());				
				if(!pwd.equals(sysUser.getPassword())){
					result = "errorPwd";
				}else{
					String pwd1 = request.getParameter("pwd1");
					String pwd2 = request.getParameter("pwd2");
					if(!pwd1.equals(pwd2)){
						result = "errortwo";
					}else{
						sysUser.setPassword(pwd1);
						sysUserService.updateByPrimaryKeySelective(sysUser);
						result = "success";
					}
				}
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}	
			return result;
		}
	
	
	
	
	
}
