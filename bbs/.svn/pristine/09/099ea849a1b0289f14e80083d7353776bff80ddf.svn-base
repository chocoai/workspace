package com.yhcrt.weihu.bbs.action.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhcrt.weihu.bbs.Constants;
import com.yhcrt.weihu.bbs.entity.BbsConcern;
import com.yhcrt.weihu.bbs.entity.BbsConfig;
import com.yhcrt.weihu.bbs.entity.BbsPost;
import com.yhcrt.weihu.bbs.entity.BbsSendCaptcha;
import com.yhcrt.weihu.bbs.entity.BbsTopic;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.entity.BbsUserExt;
import com.yhcrt.weihu.bbs.entity.BbsUserGroup;
import com.yhcrt.weihu.bbs.manager.BbsConcernMng;
import com.yhcrt.weihu.bbs.manager.BbsConfigMng;
import com.yhcrt.weihu.bbs.manager.BbsFriendShipMng;
import com.yhcrt.weihu.bbs.manager.BbsFriendsMng;
import com.yhcrt.weihu.bbs.manager.BbsPointDetailMng;
import com.yhcrt.weihu.bbs.manager.BbsPostMng;
import com.yhcrt.weihu.bbs.manager.BbsSendCaptchaMng;
import com.yhcrt.weihu.bbs.manager.BbsTopicMng;
import com.yhcrt.weihu.bbs.manager.BbsUserExtMng;
import com.yhcrt.weihu.bbs.manager.BbsUserGroupMng;
import com.yhcrt.weihu.bbs.manager.BbsUserMng;
import com.yhcrt.weihu.bbs.web.CmsUtils;
import com.yhcrt.weihu.bbs.web.FrontUtils;
import com.yhcrt.weihu.bbs.web.WebErrors;
import com.yhcrt.weihu.common.email.EmailSender;
import com.yhcrt.weihu.common.email.MessageTemplate;
import com.yhcrt.weihu.common.page.Page;
import com.yhcrt.weihu.common.phone.SendPhoneCaptchaTools;
import com.yhcrt.weihu.common.util.GeneratorStr;
import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.entity.UnifiedUser;
import com.yhcrt.weihu.core.manager.ConfigMng;
import com.yhcrt.weihu.core.manager.UnifiedUserMng;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 该类里面的action主要原本的action满足不了需求，
 * 所以在此处重写方法及添加方法，重写的方法极少，因为避免动了原来的逻辑
 * 该action中的方法比较杂，2016-11-16之后写的方法就分类出去了
 * @author 
 *
 */
@Controller
public class BbsPersonalAct {
	
	@RequestMapping("/personal/checkOld.jspx")
	@ResponseBody
	public void checkOld(String captcha,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) throws IOException{
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		BbsUser user = CmsUtils.getUser(request);
		if(user == null){
			json.put("status", "-1");
			response.getWriter().write(json.toString());
			return ;
		}
		BbsSendCaptcha bbsSendCaptcha = bbsSendCaptchaMng.findByUserIdAndType(user.getId(),Constants.CAPTCHA_BUSINESS_BINDING_PHONE);
		if(!captcha.equals(bbsSendCaptcha.getCaptcha())){
			json.put("status", "-2");
			json.put("msg", "验证码错误");
			response.getWriter().write(json.toString());
			return ;
		}
		if(bbsSendCaptchaMng.isExpire(user.getId(),captcha,Constants.CAPTCHA_BUSINESS_BINDING_PHONE)){
			json.put("status", "-2");
			json.put("msg", "验证码过期了");
			response.getWriter().write(json.toString());
			return ;
		}
		json.put("status", "1");
		json.put("msg", "验证成功");
		response.getWriter().write(json.toString());
		return ;
	}
	
	@RequestMapping("/personal/binding_phone.jspx")
	@ResponseBody
	public void bindingPhone(String captcha,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) throws IOException{
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		BbsUser user = CmsUtils.getUser(request);
		if(user == null){
			json.put("status", "-1");
			response.getWriter().write(json.toString());
			return ;
		}
		BbsSendCaptcha bbsSendCaptcha = bbsSendCaptchaMng.findByUserIdAndType(user.getId(),Constants.CAPTCHA_BUSINESS_BINDING_PHONE);
		if(!captcha.equals(bbsSendCaptcha.getCaptcha())){
			json.put("status", "-2");
			json.put("msg", "验证码错误");
			response.getWriter().write(json.toString());
			return ;
		}
		if(bbsSendCaptchaMng.isExpire(user.getId(),captcha,Constants.CAPTCHA_BUSINESS_BINDING_PHONE)){
			json.put("status", "-2");
			json.put("msg", "验证码过期了");
			response.getWriter().write(json.toString());
			return ;
		}
		BbsUserExt ext = user.getUserExt();
		ext.setMoble(bbsSendCaptcha.getTarget());
		bbsUserExtMng.update(ext);
		json.put("status", "1");
		json.put("msg", "绑定成功");
		response.getWriter().write(json.toString());
		return ;
	}
	
	@RequestMapping("/personal/send_phone_captcha.jspx")
	@ResponseBody
	public void sendPhoneCaptcha(String phone,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) throws IOException{
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		BbsUser user = CmsUtils.getUser(request);
		if(user == null){
			json.put("status", "-1");
			response.getWriter().write(json.toString());
			return ;
		}
		JSONArray phones = new JSONArray();
		phones.add(phone);
		String captcha = GeneratorStr.getString(6);
		try {
			JSONArray params = new JSONArray();
	        params.add(captcha);
			JSONObject o = SendPhoneCaptchaTools.sendCaptcha(captcha, phones,params);
			if(o.optString("code").equals("200")){
				bbsSendCaptchaMng.sendCaptcha(null, null, unifiedUserMng.findById(user.getId()), phone, "3", captcha, Constants.CAPTCHA_BUSINESS_BINDING_PHONE);
				json.put("status", "1");
				json.put("msg", "短信发送成功");
			}else{
				json.put("status", "-2");
				json.put("msg", "短信发送失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("status", "-2");
			json.put("msg", "短信发送失败");
			response.getWriter().write(json.toString());
			return ;
		}
		response.getWriter().write(json.toString());
	}
	
	@RequestMapping("/personal/reset_save.jspx")
	@ResponseBody
	public void resetSave(Integer userId,String password,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) throws IOException{
		response.setCharacterEncoding("UTF-8");
		if(StringUtils.isBlank(password)){
			response.getWriter().write("-1");
			return ;
		}
		unifiedUserMng.updatePassword(userId, password);
		response.getWriter().write("1");
	}
	
	@RequestMapping("/personal/reset_password.jspx")
	public String resetPassword(String uuid,String resetPwd,String userId,
			HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		if(StringUtils.isBlank(uuid) || StringUtils.isBlank(resetPwd) || StringUtils.isBlank(userId)){
			return "redirect:/personal/find_password.jspx";
		}
		Integer id = 0;
		try {
			id = Integer.parseInt(userId);
		} catch (NumberFormatException e) {
			return "redirect:/personal/find_password.jspx";
		}
		UnifiedUser user = unifiedUserMng.findById(id);
		if(!uuid.equals(user.getResetKey()) || !resetPwd.equals(user.getResetPwd())){
			return "redirect:/personal/find_password.jspx";
		}
		model.put("joUser", user);
		return "/WEB-INF/t/cms/www/blue/member/reset_password.html";
	}
	
	@RequestMapping("/personal/sendCaptcha.jspx")
	@ResponseBody
	public void findPassword3(String username ,String email,String phone,String type,
			HttpServletRequest request,HttpServletResponse response,  ModelMap model) throws IOException{
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		if(StringUtils.isBlank(email) && StringUtils.isBlank(phone) 
				|| StringUtils.isBlank(username) || StringUtils.isBlank(type)){
			json.put("status", "-1");//输入有空值，前台做了拦截的，所以此处是以防万一
			json.put("msg", "输入的数据不能有空值");
			response.getWriter().write(json.toString());
			return ;
		}
		UnifiedUser user = unifiedUserMng.getByUsername(username);
		if(user == null){
			json.put("status", "-1");
			json.put("msg", "用户不存在");
			response.getWriter().write(json.toString());
			return ;
		}
		if(type.equals("1")){
			if(!email.equals(user.getEmail())){
				json.put("status", "-1");
				json.put("msg", "不是绑定的邮箱");
				response.getWriter().write(json.toString());
				return ;
			}
			EmailSender sender = configMng.getEmailSender();
			MessageTemplate msgTpl = configMng.getRegisterMessageTemplate();
			try {
				String captcha = GeneratorStr.getString(6);
				bbsSendCaptchaMng.sendCaptcha(sender, msgTpl, user, email, type, captcha, Constants.CAPTCHA_BUSINESS_PASSWORD_EMAIL);
			} catch (Exception e) {
				e.printStackTrace();
				json.put("status", "-1");
				json.put("msg", "邮件发送失败");
				response.getWriter().write(json.toString());
				return ;
			}
			json.put("status", "1");
			json.put("msg", "发送成功");
			response.getWriter().write(json.toString());
			return ;
		}else if(type.equals("2")){
			if(!phone.equals(bbsUserExtMng.findById(user.getId()).getMoble())){
				json.put("status", "-1");
				json.put("msg", "该电话号码不是您绑定的手机号");
				response.getWriter().write(json.toString());
				return ;
			}
			JSONArray phones = new JSONArray();
			phones.add(phone);
			String captcha = GeneratorStr.getString(6);
			try {
				JSONArray params = new JSONArray();
		        params.add(captcha);
				JSONObject o = SendPhoneCaptchaTools.sendCaptcha(captcha, phones,params);
				if(o.optString("code").equals("200")){
					bbsSendCaptchaMng.sendCaptcha(null, null, unifiedUserMng.findById(user.getId()), phone, type, captcha, Constants.CAPTCHA_BUSINESS_PASSWORD_PHONE);
					json.put("status", "1");
					json.put("msg", "短信发送成功");
				}else{
					json.put("status", "-1");
					json.put("msg", "短信发送失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
				json.put("status", "-1");
				json.put("msg", "短信发送失败");
				response.getWriter().write(json.toString());
				return ;
			}
			response.getWriter().write(json.toString());
			return ;
		}else{
			//type不是 1 ，也不是 2 ,所以出现不可预知的情况了   正常情况只有 1 和 2 
			json.put("status", "0");
			json.put("msg", "出现了未知情况");
			response.getWriter().write(json.toString());
			return ;
		}
	}
	
	@RequestMapping("/personal/check_captcha.jspx")
	@ResponseBody
	public void checkPassword1(String username,String captcha,String type,
			HttpServletRequest request,HttpServletResponse response,ModelMap model) throws IOException{
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		if(StringUtils.isBlank(username) || StringUtils.isBlank(captcha) || StringUtils.isBlank(type)){
			json.put("status", "-1");
			json.put("msg", "输入的不能有空值");
			response.getWriter().write(json.toString());
			return ;
		}
		UnifiedUser user = unifiedUserMng.getByUsername(username);
		if(user == null){
			json.put("status", "-1");
			json.put("msg", "用户不存在");
			response.getWriter().write(json.toString());
			return ;
		}
		BbsSendCaptcha bbsSendCaptcha = null;
		if(type.equals("1")){
			bbsSendCaptcha = bbsSendCaptchaMng.findByUserIdAndType(user.getId(),Constants.CAPTCHA_BUSINESS_PASSWORD_EMAIL);
		}else if(type.equals("2")){
			bbsSendCaptcha = bbsSendCaptchaMng.findByUserIdAndType(user.getId(),Constants.CAPTCHA_BUSINESS_PASSWORD_PHONE);;
		}else{
			json.put("status", "-1");
			json.put("msg", "参数出现异常");
			response.getWriter().write(json.toString());
			return ;
		}
		if(bbsSendCaptcha == null){
			json.put("status", "-1");
			json.put("msg", "验证码发送失败，请重新发送");
			response.getWriter().write(json.toString());
			return ;
		}
		if(!captcha.equals(bbsSendCaptcha.getCaptcha())){
			json.put("status", "-1");
			json.put("msg", "验证码错误");
			response.getWriter().write(json.toString());
			return ;
		}
		boolean isExpire = true;
		if(type.equals("1")){
			isExpire = bbsSendCaptchaMng.isExpire(user.getId(),captcha,Constants.CAPTCHA_BUSINESS_PASSWORD_EMAIL);
		}else{
			isExpire = bbsSendCaptchaMng.isExpire(user.getId(),captcha,Constants.CAPTCHA_BUSINESS_PASSWORD_PHONE);
		}
		if(isExpire){
			json.put("status", "-1");
			json.put("captchaError","验证码已过期");
			response.getWriter().write(json.toString());
			return ;
		}
		json.put("status", "1");
		user = unifiedUserMng.updateKeyAndPwd(user.getId());
		json.put("uuid", user.getResetKey());
		json.put("resetPwd", user.getResetPwd());
		json.put("userId", user.getId());
		response.getWriter().write(json.toString());
	}
	
	@RequestMapping("/personal/find_password.jspx")
	public String findPassword1(HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		return "/WEB-INF/t/cms/www/blue/member/find_password.html";
	}
	
	@RequestMapping("/member/personal_binding.jspx")
	public String personalBinding(HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		model.put("isSign", bbsPointDetailMng.isSign(user.getId()));
		return "/WEB-INF/t/cms/www/blue/member/personal_binding.html";
	}
	
	@RequestMapping("/member/personal_confirm.jspx")
	public String personalConfirm(HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		model.put("isSign", bbsPointDetailMng.isSign(user.getId()));
		return "/WEB-INF/t/cms/www/blue/member/personal_confirm.html";
	}
	
	@RequestMapping("/register/checkEmail.jspx")
	@ResponseBody
	public String checkEmail(String email,HttpServletRequest request, ModelMap model){
		WebErrors errors = WebErrors.create(request);
		if (errors.ifNotEmail(email, "email", 100)) {
			return "1";
		}else{
			return "0";
		}
	}
	
	@RequestMapping("/register/checkUserName.jspx")
	@ResponseBody
	public String usernameRepeat(String username,HttpServletRequest request, ModelMap model){
		WebErrors errors = WebErrors.create(request);
		if(errors.ifNotUsername(username, "username", 3, 20)){
			return "-1";
		}
		if(unifiedUserMng.usernameExist(username)){
			return "1";
		}else{
			return "0";
		}
	}
	
	@RequestMapping("/personal/clause.jspx")
	public String clause(HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsConfig config = bbsConfigMng.getBySiteId(site.getId());
		model.put("bbsConfig", config);
		return "/WEB-INF/t/cms/www/blue/member/clause.html";
	}

	@RequestMapping("/member/isPassWord{userId}.jspx")
	@ResponseBody
	public String isPassWord(@PathVariable Integer userId,String oldPassWord,
			HttpServletRequest request, ModelMap model){
		if(oldPassWord == null){
			return "0";
		}
		if(unifiedUserMng.isPasswordValid(userId, oldPassWord)){
			return "1";
		}else{
			return "0";
		}
	}
	
	
	@RequestMapping("/personal/other_reply_post{userId}.jspx")
	public String otherReplyPost(@PathVariable Integer userId,Integer pageSize,Integer pageNo,
			HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		if(userId == null){
			return "redirect:/";
		}
		BbsUser user = CmsUtils.getUser(request);
		if(user == null){
			//model.put("isSign", false);
			//model.put("isConcern",false);
		}else{
			model.put("isConcern", bbsConcernMng.isConcernByUser(user.getId(), userId));
			model.put("isFriend", bbsFriendsMng.isFriend(userId, user.getId()));
			model.put("isSign", bbsPointDetailMng.isSign(user.getId()));
		}
		BbsUser otherUser = bbsUserMng.findById(userId);
		model.put("otherUser", otherUser);
		Page<BbsPost> pageBean = bbsPostMng.getPage(userId, pageSize, pageNo);
		model.put("pageBean", pageBean);
		return "/WEB-INF/t/cms/www/blue/member/other_reply_post.html";
	}
	
	@RequestMapping("/personal/other_topic{userId}.jspx")
	public String otherTopic(@PathVariable Integer userId,Integer pageSize,Integer pageNo,
			HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		if(userId == null){
			return "redirect:/";
		}
		BbsUser user = CmsUtils.getUser(request);
		if(user == null){
			//model.put("isSign", false);
			//model.put("isConcern",false);
		}else{
			model.put("isConcern", bbsConcernMng.isConcernByUser(user.getId(), userId));
			model.put("isFriend", bbsFriendsMng.isFriend(userId, user.getId()));
			model.put("isSign", bbsPointDetailMng.isSign(user.getId()));
		}
		BbsUser otherUser = bbsUserMng.findById(userId);
		model.put("otherUser", otherUser);
		Page<BbsTopic> pageBean = bbsTopicMng.getPage(userId, pageSize, pageNo);
		model.put("pageBean", pageBean);
		return "/WEB-INF/t/cms/www/blue/member/other_topic.html";
	}
	
	@RequestMapping("/personal/other_information{userId}.jspx")
	public String otherInformation(@PathVariable Integer userId,
			HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		if(userId == null){
			return "redirect:/";
		}
		BbsUser user = CmsUtils.getUser(request);
		if(user == null){
			//model.put("isSign", false);
			//model.put("isConcern",false);
		}else{
			model.put("isConcern", bbsConcernMng.isConcernByUser(user.getId(), userId));
			model.put("isFriend", bbsFriendsMng.isFriend(userId, user.getId()));
			model.put("isSign", bbsPointDetailMng.isSign(user.getId()));
		}
		BbsUser otherUser = bbsUserMng.findById(userId);
		model.put("otherUser", otherUser);
		return "/WEB-INF/t/cms/www/blue/member/other_information.html";
	}
	
	@RequestMapping("/member/point_sign.jspx")
	@ResponseBody
	public String sign(HttpServletRequest request, ModelMap model){
		BbsUser userOld = CmsUtils.getUser(request);
		if(userOld == null){
			return "-1";
		}
		BbsUser user = bbsUserMng.findById(userOld.getId());
		if(bbsPointDetailMng.isSign(user.getId())){
			return "0";
		}
		user.setPoint(user.getPoint()+5);
		String description = "<b>"+user.getUsername()+"</b>完成<b>每日打卡</b>任务,获得系统赠送积分";
		bbsPointDetailMng.save(user.getId(), 5, description, Constants.POINT_SIGN,Constants.POINT_TYPE_SIGN);
		List<BbsUserGroup> list = bbsUserGroupMng.getListOfPersonal();
		Integer pointsAll = bbsPointDetailMng.getPointsAll(user.getId());
		for(int i=0; i<list.size(); i++){
			if(pointsAll>=list.get(i).getPoint()){
				continue;
			}else if(i == list.size()-1){
				if(user.getGroup().getId()!=list.get(i).getId()){
					bbsUserMng.updateGroup(user.getId(), list.get(i).getId());
					break;
				}else{
					break;
				}
			}else{
				if(user.getGroup().getId()!=list.get(i-1).getId()){
					bbsUserMng.updateGroup(user.getId(), list.get(i-1).getId());
					break;
				}else{
					break;
				}
			}
		}
		return "1";
	}
	
	@RequestMapping("/member/myTopic_delete.jspx")
	public String delete(Integer[] topicIds, Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		bbsTopicMng.deleteByIds(topicIds);
		return "redirect:/member/mytopic.jhtml";
	}
	
	@RequestMapping("/member/task_list.jspx")
	public String taskList(HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		//判断是否已打卡
		boolean isSign = bbsPointDetailMng.isSign(user.getId());
		//判断是否已上传过头像
		boolean isUploadImg = bbsPointDetailMng.isUploadImg(user.getId());
		//判断今日发帖任务是否已完成
		boolean isSendTopic = bbsPointDetailMng.isTodayComplete(user.getId(), Constants.POINT_TYPE_TOPIC);
		model.put("isUploadImg", isUploadImg);
		model.put("isSign", isSign);
		model.put("isSendTopic", isSendTopic);
		return "/WEB-INF/t/cms/www/blue/member/task.html";
	}
	
	@RequestMapping("/member/level_list.jspx")
	public String levelList(HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		if(user == null){
			
		}
		FrontUtils.frontData(request, model, site);
		//判断是否已打卡
		boolean isSign = bbsPointDetailMng.isSign(user.getId());
		model.put("isSign", isSign);
		List<BbsUserGroup> list = bbsUserGroupMng.getListOfPersonal();
		model.put("bbsUserGroupList", list);
		return "/WEB-INF/t/cms/www/blue/member/level.html";
	}
	
	@RequestMapping("/member/concern_list.jspx")
	public String concernList(Integer pageSize,Integer pageNo,
			HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		FrontUtils.frontData(request, model, site);
		//判断是否已打卡
		boolean isSign = bbsPointDetailMng.isSign(user.getId());
		model.put("isSign", isSign);
		Page<BbsConcern> pageBean = bbsConcernMng.getPage(user.getId(), pageSize, pageNo);
		model.put("pageBean", pageBean);
		model.put("isFriends", bbsFriendsMng.getMyFriendsId(user.getId()));
		model.put("isApplys", bbsFriendShipMng.getApplyIds(user.getId()));
		return "/WEB-INF/t/cms/www/blue/member/concern.html";
	}
	
	@RequestMapping("/member/concern_delete{concernId}.jspx")
	public String concernDelete(@PathVariable Integer concernId,
			HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		bbsConcernMng.delete(concernId);
		return "redirect:/member/concern_list.jspx";
	}
	
	@RequestMapping("/member/concern_add.jspx")
	@ResponseBody
	public String concern(Integer concernUserId,
			HttpServletRequest request, ModelMap model){
		BbsUser user = CmsUtils.getUser(request);
		if(user == null){
			return "-1";
		}
		Boolean isConcern = bbsConcernMng.isConcernByUser(user.getId(), concernUserId);
		if(isConcern == null){
			return "0";
		}else if(isConcern){
			bbsConcernMng.deleteByUser(user.getId(), concernUserId);
			return "2";
		}else{
			bbsConcernMng.save(user.getId(), concernUserId);
			return "1";
		}
	}
	
	@RequestMapping("/member/test_inm.jspx")
	@ResponseBody
	public void test(HttpServletRequest request,HttpServletResponse response, ModelMap model) throws IOException{
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		json.put("error", 0);
		json.put("url", "http://www.weihuwang.cn/up_file/cms/www/201309/1117164754x7.jpg");
		response.getWriter().write(json.toString());
	}
	
	@Autowired
	private BbsUserGroupMng bbsUserGroupMng;
	@Autowired
	private BbsConcernMng bbsConcernMng;
	@Autowired
	private BbsTopicMng bbsTopicMng;
	@Autowired
	private BbsPointDetailMng bbsPointDetailMng;
	@Autowired
	private BbsUserMng bbsUserMng;
	@Autowired
	private BbsPostMng bbsPostMng;
	@Autowired
	private UnifiedUserMng unifiedUserMng;
	@Autowired
	private ConfigMng configMng;
	@Autowired
	private BbsSendCaptchaMng bbsSendCaptchaMng;
	@Autowired
	private BbsUserExtMng bbsUserExtMng;
	@Autowired
	private BbsFriendShipMng bbsFriendShipMng;
	@Autowired
	private BbsFriendsMng bbsFriendsMng;
	@Autowired
	private BbsConfigMng bbsConfigMng;
	
}
