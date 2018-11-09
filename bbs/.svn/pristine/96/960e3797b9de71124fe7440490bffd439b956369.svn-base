package com.yhcrt.weihu.bbs.action.member;

import static com.yhcrt.weihu.bbs.Constants.TPLDIR_MEMBER;
import static com.yhcrt.weihu.bbs.Constants.TPLDIR_TOPIC;

import java.util.Date;
import java.util.List;
//import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yhcrt.weihu.bbs.entity.BbsCreditExchange;
import com.yhcrt.weihu.bbs.entity.BbsPointDetail;
//import com.yhcrt.weihu.bbs.entity.BbsForum;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.entity.BbsUserExt;
//import com.yhcrt.weihu.bbs.manager.BbsCreditExchangeMng;
//import com.yhcrt.weihu.bbs.manager.BbsForumMng;
import com.yhcrt.weihu.bbs.manager.BbsPointDetailMng;
import com.yhcrt.weihu.bbs.manager.BbsTopicMng;
import com.yhcrt.weihu.bbs.manager.BbsUserExtMng;
import com.yhcrt.weihu.bbs.manager.BbsUserMng;
import com.yhcrt.weihu.bbs.web.CmsUtils;
import com.yhcrt.weihu.bbs.web.FrontUtils;
import com.yhcrt.weihu.bbs.web.WebErrors;
import com.yhcrt.weihu.common.page.Page;
import com.yhcrt.weihu.common.security.encoder.PwdEncoder;
import com.yhcrt.weihu.common.web.RequestUtils;
import com.yhcrt.weihu.common.web.ResponseUtils;
import com.yhcrt.weihu.common.web.springmvc.MessageResolver;
import com.yhcrt.weihu.core.entity.CmsConfigItem;
import com.yhcrt.weihu.core.entity.CmsSite;
import com.yhcrt.weihu.core.manager.CmsConfigItemMng;
import com.yhcrt.weihu.core.manager.UnifiedUserMng;

@Controller
public class UserPostAct {

	public static final String MEMBER_CENTER = "tpl.memberCenter";
	public static final String MEMBER_INFORM = "tpl.information";
	public static final String MEMBER_TOPIC = "tpl.memberTopic";
	public static final String MEMBER_POST = "tpl.memberPost";
	public static final String SEARCH = "tpl.search";
	public static final String SEARCH_RESULT = "tpl.searchResult";
	public static final String TPL_NO_LOGIN = "tpl.nologin";
	public static final String TPL_CREDIT = "tpl.creditMng";
	public static final String TPL_EDIT_USERIMG = "tpl.edituserimg";
	public static final String TPL_NO_VIEW = "tpl.noview";
	public static final String TPL_PWD = "tpl.memberPassword";

	@RequestMapping("/member/my_dynamic.jhtml")
	public String myDynamic(Integer pageSize,Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site,
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		model.put("isSign", bbsPointDetailMng.isSign(user.getId()));
		model.put("user", user);
		FrontUtils.frontPageData(request, model);
		return "/WEB-INF/t/cms/www/blue/member/my_dynamic.html";
	}
	
	@RequestMapping("/member/index*.jhtml")
	public String index(Integer pageSize,Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site,
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		model.put("pageBean", bbsTopicMng.getFriendDynamic(user.getId(), pageSize, pageNo));
		model.put("isSign", bbsPointDetailMng.isSign(user.getId()));
		return FrontUtils.getTplPath(request, site,
				TPLDIR_MEMBER, MEMBER_CENTER);
	}

	@RequestMapping("/member/information.jhtml")
	public String information(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site,
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		//判断是否已打卡
		boolean isSign = bbsPointDetailMng.isSign(user.getId());
		model.put("isSign", isSign);
		List<CmsConfigItem>items=cmsConfigItemMng.getList(site.getConfig().getId(), CmsConfigItem.CATEGORY_REGISTER);
		model.addAttribute("items", items);
		model.put("user", user);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site,
				TPLDIR_MEMBER, MEMBER_INFORM);
	}
	
	
	@RequestMapping("/member/editUserImg.jhtml")
	public String editUserImg(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site,
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		model.put("user", user);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site,
				TPLDIR_MEMBER, TPL_EDIT_USERIMG);
	}
	
	@RequestMapping("/member/updateUserImg.jhtml")
	public String updateUserImg(String email,
			String newPassword, String signed, String avatar, BbsUserExt ext,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site,
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
//		if(!user.getId().equals(ext.getId())){
//			model.put("msg", "更新错误");
//			return FrontUtils.getTplPath(request, site,
//					TPLDIR_TOPIC, TPL_NO_VIEW);
//		}
//		manager.updateMember(user.getId(), email, newPassword, null, signed,
//				avatar, ext, null,null);
		avatar = avatar.replaceAll("：", ":");
		BbsUser newUser = manager.updateImg(user.getId(), avatar);
		model.put("user", newUser);
		FrontUtils.frontPageData(request, model);
		return "redirect:/member/information.jhtml";
	}

	@RequestMapping("/member/update{typeId}.jspx")
	public String informationSubmit(@PathVariable Integer typeId,String email,
			String newPassword, String signed, String avatar, 
			boolean gender,Date birthday,String address,String intro,String comefrom,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		WebErrors errors = WebErrors.create(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site,
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		
//		if(!user.getId().equals(ext.getId())){
//			model.put("msg", "更新错误");
//			return FrontUtils.getTplPath(request, site,
//					TPLDIR_TOPIC, TPL_NO_VIEW);
//		}
		if (errors.ifNotEmail(email, "email", 100)) {
			return FrontUtils.showError(request, response, model, errors);
		}
		//Map<String,String>attrs=RequestUtils.getRequestMap(request, "attr_");
		if(typeId == 3){
			unifiedUserMng.update(user.getId(), newPassword, null);
		}else if(typeId == 2){
			BbsUserExt ext = bbsUserExtMng.findById(user.getId());
			ext.setAddress(address);
			ext.setBirthday(birthday);
			ext.setComefrom(comefrom);
			ext.setGender(gender);
			ext.setIntro(intro);
			user = manager.updateMember(user.getId(), null, null, null, null,
					null, ext,null, null);
		}
		List<CmsConfigItem>items=cmsConfigItemMng.getList(site.getConfig().getId(), CmsConfigItem.CATEGORY_REGISTER);
		model.put("user", user);
		model.put("typeId", typeId);
		model.addAttribute("items", items);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site,
				TPLDIR_MEMBER, MEMBER_INFORM);
	}

	@RequestMapping("/member/mytopic*.jhtml")
	public String mytopic(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site,
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		//判断是否已打卡
		boolean isSign = bbsPointDetailMng.isSign(user.getId());
		model.put("isSign", isSign);
		model.put("user", user);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site,
				TPLDIR_MEMBER, MEMBER_TOPIC);
	}

	@RequestMapping("/member/mypost*.jhtml")
	public String mypost(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site,
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		//判断是否已打卡
		boolean isSign = bbsPointDetailMng.isSign(user.getId());
		model.put("isSign", isSign);
		model.put("user", user);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site,
				TPLDIR_MEMBER, MEMBER_POST);
	}

	@RequestMapping(value = "/member/inputSearch*.jhtml", method = RequestMethod.GET)
	public String search(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site,
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site,
				TPLDIR_MEMBER, SEARCH);
	}

	@RequestMapping(value = "/member/search*.jhtml")
	public String searchSubmit( Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site,
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		String keywords = RequestUtils.getQueryParam(request, "keywords");
		
		Integer forumId=Integer.parseInt(RequestUtils.getQueryParam(request, "forumId"));
		
		model.put("keywords", keywords);
		model.put("forumId", forumId);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site,
				TPLDIR_MEMBER, SEARCH_RESULT);
	}

	@RequestMapping("/member/creditManager.jhtml")
	public String creditManager(Integer pageSize,Integer pageNo,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site,
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
//		BbsCreditExchange creditExchangeRule = creditExchangeMng.findById(site
//				.getId());
//		Boolean exchangeAvailable = false;
//		if (creditExchangeRule.getPointinavailable()
//				&& creditExchangeRule.getPrestigeoutavailable()
//				|| creditExchangeRule.getPointoutavailable()
//				&& creditExchangeRule.getPrestigeinavailable()) {
//			exchangeAvailable = true;
//		} else {
//			exchangeAvailable = false;
//		}
//		if (exchangeAvailable) {
//			if (!creditExchangeRule.getExpoint().equals(0)
//					&& !creditExchangeRule.getExprestige().equals(0)) {
//				exchangeAvailable = true;
//			} else {
//				exchangeAvailable = false;
//			}
//		}
		//判断是否已打卡
		boolean isSign = bbsPointDetailMng.isSign(user.getId());
		Page<BbsPointDetail> pageBean = bbsPointDetailMng.getPage(user.getId(), pageSize, pageNo); 
		model.put("pageBean", pageBean);
		model.put("isSign", isSign);
		model.put("pointsAll", bbsPointDetailMng.getPointsAll(user.getId()));
//		List<BbsForum> forums = forumMng.getList(site.getId());
		model.put("user", user);
//		model.put("exchangeAvailable", exchangeAvailable);
//		model.put("pointInAvail", creditExchangeRule.getPointinavailable());
//		model.put("pointOutAvail", creditExchangeRule.getPointoutavailable());
//		model.put("prestigeInAvail", creditExchangeRule
//				.getPrestigeinavailable());
//		model.put("prestigeOutAvail", creditExchangeRule
//				.getPrestigeoutavailable());
//		model.put("forums", forums);
//		model.put("creditExchangeRule", creditExchangeRule);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site,
				TPLDIR_MEMBER, TPL_CREDIT);
	}

	@RequestMapping(value = "/member/getCreditOutValue.jspx")
	public void getCreditOutValue(Integer creditIn, Integer creditInType,
			Integer creditOutType, double exchangetax,
			HttpServletRequest request, HttpServletResponse response) {
		JSONObject object = new JSONObject();
		Long creditOutValue = null;
		Double temp = 0.0;
		BbsCreditExchange rule = CmsUtils.getSite(request).getCreditExchange();
		if (creditInType.equals(1) && creditOutType.equals(2)) {
			// 积分换取威望
			temp = creditIn * rule.getExpoint() * 1.0 / rule.getExprestige()
					* (1.0 + exchangetax);
		} else if (creditInType.equals(2) && creditOutType.equals(1)) {
			// 威望换积分
			temp = creditIn * rule.getExprestige() * 1.0 / rule.getExpoint()
					* (1.0 + exchangetax);
		}
		creditOutValue = Long.valueOf((long) Math.ceil(temp));
		try {
			object.put("creditOutValue", creditOutValue);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, object.toString());
	}

	@RequestMapping(value = "/member/creditExchange.jspx")
	public void creditExchange(Integer creditIn, Integer creditOut,
			Integer creditOutType,  String password,
			HttpServletRequest request, HttpServletResponse response) {
		JSONObject object = new JSONObject();
		BbsUser user = CmsUtils.getUser(request);
		Boolean result=true;
		String message=MessageResolver.getMessage(request, "cmspoint.success");
		BbsCreditExchange rule = CmsUtils.getSite(request).getCreditExchange();
		Integer miniBalance=rule.getMiniBalance();
		//验证密码
		if (!pwdEncoder.isPasswordValid(unifiedUserMng.getByUsername(
				user.getUsername()).getPassword(), password)) {
			result = false;
			message=MessageResolver.getMessage(request, "cmscredit.passworderror");
		}else{
			//验证兑换规则
			int valid=validExchange(rule, user, creditIn, creditOut, creditOutType);
			if(valid==1){
				result = false;
				message=MessageResolver.getMessage(request, "cmscredit.pointisnotenough",miniBalance);
			}else if(valid==2){
				result = false;
				message=MessageResolver.getMessage(request, "cmscredit.prestigeisnotenough",miniBalance);
			}else if(valid==3){
				result = false;
				message=MessageResolver.getMessage(request, "operate.faile");
			}else{
				if(creditOutType.equals(1)){
					user.setPoint(user.getPoint()-creditOut);
					user.setPrestige(user.getPrestige()+creditIn);
				}else if(creditOutType.equals(2)){
					user.setPrestige(user.getPrestige()-creditOut);
					user.setPoint(user.getPoint()+creditIn);
				}
				//此处更新用户积分威望信息
				manager.updatePwdEmail(user.getId(), password, user.getEmail());
			}
		}
		try {
			object.put("message", message);
			object.put("result", result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseUtils.renderJson(response, object.toString());
	}
	
	@RequestMapping(value = "/member/pwd.jhtml", method = RequestMethod.GET)
	public String pwd(HttpServletRequest request,ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site,
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		return FrontUtils.getTplPath(request, site,TPLDIR_MEMBER, TPL_PWD);
	}
	
	@RequestMapping(value = "/member/pwd.jhtml", method = RequestMethod.POST)
	public String pwd_update(String origPwd,String password,HttpServletRequest request,HttpServletResponse response,ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site,
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		if(!pwdEncoder.isPasswordValid(unifiedUserMng.getByUsername(user.getUsername()).getPassword(), origPwd)){
			WebErrors errors =WebErrors.create(request);
			errors.addErrorCode("member.update.pwd.error");
			return FrontUtils.showError(request, response, model, errors);
		}
		manager.updatePwdEmail(user.getId(), password, null);
		return pwd(request, model);
	}
	
	/**
	 * 验证密码是否正确
	 * 
	 * @param origPwd
	 *            原密码
	 * @param request
	 * @param response
	 */
	@RequestMapping("/member/checkPwd.jhtml")
	public void checkPwd(String origPwd, HttpServletRequest request,
			HttpServletResponse response) {
		BbsUser user = CmsUtils.getUser(request);
		boolean pass = manager.isPasswordValid(user.getId(), origPwd);
		ResponseUtils.renderJson(response, pass ? "true" : "false");
	}
	
	private int validExchange(BbsCreditExchange rule,BbsUser user,Integer creditIn, Integer creditOut,
			Integer creditOutType){
		//兑出的积分是否充足
		if(user!=null&&creditOutType.equals(1)){
			if(user.getPoint()-creditOut<rule.getMiniBalance()){
				return 1;
			}
		}
		//兑出的威望是否充足
		if(user!=null&&creditOutType.equals(2)){
			if(user.getPrestige()-creditOut<rule.getMiniBalance()){
				return 2;
			}
		}
		Integer creditOutValue = null;
		Double temp = 0.0;
		if (creditOutType.equals(2)) {
			// 兑威望
			temp = creditIn * rule.getExpoint() * 1.0 / rule.getExprestige()
					* (1.0 + rule.getExchangetax());
		} else if (creditOutType.equals(1)) {
			// 兑积分
			temp = creditIn * rule.getExprestige() * 1.0 / rule.getExpoint()
					* (1.0 + rule.getExchangetax());
		}
		creditOutValue = Integer.valueOf((int) Math.ceil(temp));
		//creditOut兑换需要的积分或者威望被恶意修改
		if(!creditOutValue.equals(creditOut)){
			return 3;
		}
		return 0;
	}

	@Autowired
	private BbsUserMng manager;
//	@Autowired
//	private BbsCreditExchangeMng creditExchangeMng;
//	@Autowired
//	private BbsForumMng forumMng;
	@Autowired
	private PwdEncoder pwdEncoder;
	@Autowired
	private UnifiedUserMng unifiedUserMng;
	@Autowired
	private CmsConfigItemMng cmsConfigItemMng;
	@Autowired
	private BbsPointDetailMng bbsPointDetailMng;
	@Autowired
	private BbsUserExtMng bbsUserExtMng;
	@Autowired
	private BbsTopicMng bbsTopicMng;
	
}
