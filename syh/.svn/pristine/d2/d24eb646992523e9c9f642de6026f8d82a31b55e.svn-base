
package com.yhcrt.controller.login;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.DisabledAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yhcrt.controller.BaseController;
import com.yhcrt.entity.system.SysMuserInfo;
import com.yhcrt.entity.system.SysUser;
import com.yhcrt.service.stsyem.SysMuserInfoService;
import com.yhcrt.shiro.TokenManager.TokenManager;
import com.yhcrt.utils.Constants;
import com.yhcrt.utils.ConstantsLog;
import com.yhcrt.utils.code.VerifyCodeUtils;

/**
 * TODO
 * @author 陈伟
 * 2017年5月30日 下午7:16:32
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/login")
public class loginController extends BaseController {
	
	@Resource(name="sysMuserInfoService")
	private SysMuserInfoService sysMuserInfoService;
	/**
	 * 
	 * @Title: login
	 * @Description: 跳转到登录页面
	 * @return: ModelAndView
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(){
		return new ModelAndView("/login");
	}
	
	/**
	 * 登录提交
	 * 
	 * @param request
	 *            request，用来取登录之前Url地址，用来登录后跳转到没有登录之前的页面。
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)  
	@ResponseBody
	public Map<String, Object> login(String vcode,SysUser user,Boolean rememberMe,Integer ersionNum,HttpServletRequest request) {
		model.clear();
		model.put("status", ConstantsLog.LOGIN_FAILURE);
		
		if(!VerifyCodeUtils.verifyCode(vcode)){
			model.put("message", "验证码不正确！");
			return model;
		}
		try {
			TokenManager.setVal2Session(Constants.ERSION_NUM, ersionNum);
			user =TokenManager.login(user,rememberMe);
			
			SysMuserInfo sysMuserInfo = sysMuserInfoService.findByUserId(user.getCid());
			setSessionUser(sysMuserInfo);
			
			model.put("status", ConstantsLog.LOGIN_SUCCESS);
			model.put("message", "登录成功");
		} catch (DisabledAccountException e) {
			model.put("message", e.getMessage());
		} catch (AccountException e) {
			model.put("message", e.getMessage());
		}catch (Exception e) {
			model.put("message", "你已经来到了外太空");
		}
		sysStsyemLogService.saveLoginSelective((String) model.get("status"),(String) model.get("message"), ConstantsLog.LOG_0, user.getUserCode());
		return model;
	}
	
	/**
	 * 退出
	 * @return
	 */
	@RequestMapping(value="logout",method =RequestMethod.GET)
	public ModelAndView logout(){
		return new ModelAndView("/login");
	}
	
}
