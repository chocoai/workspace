package com.yhcrt.weihu.bbs.action.member;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhcrt.weihu.bbs.entity.BbsFriendShip;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.manager.BbsFriendShipMng;
import com.yhcrt.weihu.bbs.manager.BbsPointDetailMng;
import com.yhcrt.weihu.bbs.web.CmsUtils;
import com.yhcrt.weihu.bbs.web.FrontUtils;
import com.yhcrt.weihu.common.page.Page;
import com.yhcrt.weihu.core.entity.CmsSite;

import net.sf.json.JSONObject;

/**
 * 其原来的方法有部分并不能满足需求，并且有些业务功能也不一样，
 * 所以原来的MessageAct满足不了的在此处理
 * @author 
 *
 */
@Controller
public class PersonalMessageAct {
	
	@RequestMapping("/personal/friend_refuse.jspx")
	@ResponseBody
	public void refuse(Integer id,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) throws IOException{
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if(user == null){
			json.put("status", "-1");
			json.put("msg", "请重新登录");
			response.getWriter().write(json.toString());
			return ;
		}
		bbsFriendShipMng.refuse(bbsFriendShipMng.findById(id));
		json.put("status", "1");
		response.getWriter().write(json.toString());
	}
	
	@RequestMapping("/personal/friend_agree.jspx")
	@ResponseBody
	public void agree(Integer id,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) throws IOException{
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if(user == null){
			json.put("status", "-1");
			json.put("msg", "请重新登录");
			response.getWriter().write(json.toString());
			return ;
		}
		bbsFriendShipMng.agree(id);
		json.put("status", "1");
		response.getWriter().write(json.toString());
	}
	
	@RequestMapping("/member/personal_message.jspx")
	public String myMessage(Integer pageSize,Integer pageNo,
			HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		Page<BbsFriendShip> pageBean = bbsFriendShipMng.getPage(pageSize, pageNo, user.getId());
		model.put("pageBean", pageBean);
		model.put("isSign", bbsPointDetailMng.isSign(user.getId()));
		return "/WEB-INF/t/cms/www/blue/member/personal_message.html";
	}
	
	@Autowired
	private BbsPointDetailMng bbsPointDetailMng;
	@Autowired
	private BbsFriendShipMng bbsFriendShipMng;
	
}
