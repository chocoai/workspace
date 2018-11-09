/**
 * 
 */
package com.whty.wfd.page.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whty.wfd.page.dao.TUserMapper;
import com.whty.wfd.page.model.TUser;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.whty.wfd.base.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * @author zhangzheng
 * @date 2018年8月23日
 */
@Controller
@RequestMapping("/page/test")
public class TestController extends BaseController {

	@Autowired
	private TUserMapper tUserMapper;

	@RequestMapping("test.html")
	public String test(HttpServletRequest request, Model model, HttpServletResponse response) {

		return "test2";
	}

	/**
	 * 对外监控
	 */
	@RequestMapping(value = "webTest",method = RequestMethod.GET)
	@ResponseBody
	public void webTest(HttpServletResponse response) throws IOException {
		JSONObject jsonObject = new JSONObject();
		try{
			TUser tUser = tUserMapper.selectByPrimaryKey(7057);
			jsonObject.put("code","000000");
		}catch (Exception e){
			jsonObject.put("code","000001");
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jsonObject);
	}

}
