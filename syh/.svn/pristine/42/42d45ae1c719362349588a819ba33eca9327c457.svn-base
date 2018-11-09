package com.yhcrt.controller;



import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.yhcrt.shiro.TokenManager.TokenManager;
import com.yhcrt.utils.LoggerUtils;
import com.yhcrt.utils.code.Captcha;
import com.yhcrt.utils.code.GifCaptcha;
import com.yhcrt.utils.code.SpecCaptcha;
import com.yhcrt.utils.code.VerifyCodeUtils;
@Controller
@Scope(value="prototype")
@RequestMapping("/open")
public class OpenController extends BaseController {
	
	public static  int WIDTH = 98;//二维码宽
	public static  int HEIGHT= 40;//二维码高
	public static  int DIGIT = 4;//二维码位数

	/**
	 * 404错误
	 * @param request
	 * @return
	 */
	@RequestMapping("404")
	public ModelAndView _404(HttpServletRequest request){
		ModelAndView view = new ModelAndView("common/404");
		return view;
	}
	/**
	 * 500错误
	 * @param request
	 * @return
	 */
	@RequestMapping("500")
	public ModelAndView _500(HttpServletRequest request){
		ModelAndView view = new ModelAndView("common/500");
		return view;
	}
	
	/**
	 * 获取验证码
	 * @param response
	 */
	@RequestMapping(value="/getVCode",method=RequestMethod.GET)
	public void getVCode(HttpServletResponse response,HttpServletRequest request){
		try {
			response.setHeader("Pragma", "No-cache");  
	        response.setHeader("Cache-Control", "no-cache");  
	        response.setDateHeader("Expires", 0);  
	        response.setContentType("image/jpg");  
	        
	        //生成随机字串  
	        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);  
	        //存入Shiro会话session  
	        TokenManager.setVal2Session(VerifyCodeUtils.V_CODE, verifyCode.toLowerCase());  
	        //生成图片  
	        int w = 146, h = 33;  
	        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode); 
		} catch (Exception e) {
			LoggerUtils.fmtError(getClass(),e, "获取验证码异常：%s",e.getMessage());
		}
	}
	
	/**
	 * 获取验证码（Gif版本）
	 * @param response
	 */
	@RequestMapping(value="/getGifCode",method=RequestMethod.GET)
	public void getGifCode(HttpServletResponse response,HttpServletRequest request){
		try {
			response.setHeader("Pragma", "No-cache");  
	        response.setHeader("Cache-Control", "no-cache");  
	        response.setDateHeader("Expires", 0);  
	        response.setContentType("image/gif");  
	        /**
	         * gif格式动画验证码
	         * 宽，高，位数。
	         */
	        Captcha captcha = new GifCaptcha(WIDTH,HEIGHT,DIGIT);
	        //输出
	        ServletOutputStream out = response.getOutputStream();
	        captcha.out(out);
	        out.flush();
	       //存入Shiro会话session  
	        System.out.println( captcha.text().toLowerCase());
	        TokenManager.setVal2Session(VerifyCodeUtils.V_CODE, captcha.text().toLowerCase());  
		} catch (Exception e) {
			LoggerUtils.fmtError(getClass(),e, "获取验证码异常：%s",e.getMessage());
		}
	}
	/**
	 * 获取验证码（jpg版本）
	 * @param response
	 */
	@RequestMapping(value="/getJPGCode",method=RequestMethod.GET)
	public void getJPGCode(HttpServletResponse response,HttpServletRequest request){
		try {
			response.setHeader("Pragma", "No-cache");  
			response.setHeader("Cache-Control", "no-cache");  
			response.setDateHeader("Expires", 0);  
			response.setContentType("image/jpg");  
			/**
			 * jgp格式验证码
			 * 宽，高，位数。
			 */
			Captcha captcha = new SpecCaptcha(WIDTH,HEIGHT,DIGIT);
			 //输出
	        ServletOutputStream out = response.getOutputStream();
	        captcha.out(out);
	        out.flush();
			//HttpSession session = request.getSession(true);  
			//存入Session
			//session.setAttribute(VerifyCodeUtils.V_CODE,captcha.text().toLowerCase());  
			 //存入Shiro会话session  
	        TokenManager.setVal2Session(VerifyCodeUtils.V_CODE, captcha.text().toLowerCase());  
		} catch (Exception e) {
			LoggerUtils.fmtError(getClass(),e, "获取验证码异常：%s",e.getMessage());
		}
	}
	/**
	 * 跳转到其他网站
	 * @param url
	 * @return
	 */
	@RequestMapping(value="www/open/goto",method=RequestMethod.GET)
	public ModelAndView _goto(String url){
		
		return new ModelAndView("www/go_to","url",url);
	}
	
	/**
	 * 没有权限提示页面
	 * @return
	 */
	@RequestMapping(value="/unauthorized",method=RequestMethod.GET)
	public ModelAndView unauthorized(){
		System.out.println("无权限。。。。。。");
		return new ModelAndView("common/unauthorized");
	}
}
