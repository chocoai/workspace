package com.yhcrt.weihu.bbs.action.member;

import static com.yhcrt.weihu.bbs.Constants.TPLDIR_MEMBER;
import static com.yhcrt.weihu.bbs.Constants.TPLDIR_TOPIC;
import static com.yhcrt.weihu.bbs.entity.BbsFriendShip.APPLYING;
import static com.yhcrt.weihu.bbs.entity.BbsFriendShip.REFUSE;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhcrt.weihu.bbs.entity.BbsFriendShip;
import com.yhcrt.weihu.bbs.entity.BbsFriends;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.manager.BbsFriendShipMng;
import com.yhcrt.weihu.bbs.manager.BbsFriendsMng;
import com.yhcrt.weihu.bbs.manager.BbsPointDetailMng;
import com.yhcrt.weihu.bbs.manager.BbsUserMng;
import com.yhcrt.weihu.bbs.web.CmsUtils;
import com.yhcrt.weihu.bbs.web.FrontUtils;
import com.yhcrt.weihu.common.page.Page;
import com.yhcrt.weihu.common.web.RequestUtils;
import com.yhcrt.weihu.core.entity.CmsSite;

import net.sf.json.JSONObject;


@Controller
public class FriendAct {
	public static final String FRIEND_SEARCH = "tpl.friendSearch";
	public static final String FRIEND_SEARCH_RESULT = "tpl.friendSearchResult";
	public static final String SUGGEST = "tpl.suggest";
	public static final String FRIEND_APPLY_RESULT = "tpl.friendApplyResult";
	public static final String MYFRIEND = "tpl.myfriend";
	public static final String FRIEND_APPLY = "tpl.friendApply";
	public static final String TPL_NO_LOGIN = "tpl.nologin";
	public static final String TPL_GET_MSG_SEND = "tpl.msgsendpage";

	@RequestMapping(value = "/member/friendSearch*.jhtml", method = RequestMethod.GET)
	public String search(HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site,
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		model.addAttribute("kw", RequestUtils.getQueryParam(request, "kw"));
		model.addAttribute("user", user);
		FrontUtils.frontData(request, model, site);
		FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site,
				TPLDIR_MEMBER, FRIEND_SEARCH);
	}

	@RequestMapping("/member/suggest.jhtml")
	public String suggest(HttpServletRequest request,
			HttpServletResponse response, String username, Integer count,
			ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		List<BbsUser> list = bbsUserMng.getSuggestMember(username, count);
		model.addAttribute("suggests", list);
		return FrontUtils.getTplPath(request, site,
				TPLDIR_MEMBER, SUGGEST);
	}

	@RequestMapping(value = "/member/apply.jhtml")
	public String apply(HttpServletRequest request,
			HttpServletResponse response, String u, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		BbsUser friend = bbsUserMng.findByUsername(u);
		if (validateApply(user, friend)) {
			bbsFriendShipMng.apply(user, friend, null);
			model.addAttribute("message", "friend.applyed");
			return FrontUtils.getTplPath(request, site,
					TPLDIR_MEMBER, FRIEND_APPLY_RESULT);
		}
		return null;
	}

	@RequestMapping(value = "/member/applyJson.jhtml")
	@ResponseBody
	public void applyJson(Integer id, String applyDesc,
			HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws IOException{
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		BbsUser user = CmsUtils.getUser(request);
		BbsUser friend = bbsUserMng.findById(id);
		if (user == null || friend == null) {
			json.put("status", -1);
			json.put("msg", "申请失败");
			response.getWriter().write(json.toString());
			return ;
		}
		//判断是否已经是好友了，如果是，那么就不能再申请了
		//应该用好友表来进行判断,而不是申请记录来进行判断，因为申请是单个的，而好友表里面的记录是成双成对的
		boolean userBean = bbsFriendsMng.isFriend(user.getId(), friend.getId());
		if (userBean) {
			json.put("status", -1);
			json.put("msg", "你俩已经是好友了");
			response.getWriter().write(json.toString());
			return ;
		}
		bbsFriendShipMng.apply(user, friend, applyDesc);
		json.put("status", 1);
		json.put("msg", "申请成功");
		response.getWriter().write(json.toString());
	}

	@RequestMapping("/member/getsendmsgpage.jhtml")
	public String getmagicpage(String username, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		model.addAttribute("username",username);
		FrontUtils.frontData(request, model, site);
		return FrontUtils.getTplPath(request, site,
				TPLDIR_MEMBER, TPL_GET_MSG_SEND);
	}

	@RequestMapping(value = "/member/accept.jhtml")
	public String accept(HttpServletRequest request,
			HttpServletResponse response, Integer id, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		BbsUser user = CmsUtils.getUser(request);
		BbsFriendShip friendShip = bbsFriendShipMng.findById(id);
		if (validateAccept(user, friendShip)) {
			bbsFriendShipMng.accept(friendShip);
			model.addAttribute("message", "friend.accepted");
			return FrontUtils.getTplPath(request, site,
					TPLDIR_MEMBER, FRIEND_APPLY_RESULT);
		}
		return null;
	}

	@RequestMapping(value = "/member/refuse.jhtml")
	public void refuse(HttpServletRequest request,
			HttpServletResponse response, Integer id, ModelMap model) {
		BbsUser user = CmsUtils.getUser(request);
		BbsFriendShip friendShip = bbsFriendShipMng.findById(id);
	}

	@RequestMapping("/member/myfriend*.jhtml")
	public String myfriend(Integer pageNo, Integer pageSize,
			HttpServletRequest request, ModelMap model) {
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		if (user == null) {
			return FrontUtils.getTplPath(request, site,
					TPLDIR_TOPIC, TPL_NO_LOGIN);
		}
		Page<BbsFriends> pageBean = bbsFriendsMng.getMyFriends(pageSize, pageNo, user.getId());
		model.put("pageBean", pageBean);
		model.put("isSign", bbsPointDetailMng.isSign(user.getId()));
		//FrontUtils.frontPageData(request, model);
		return FrontUtils.getTplPath(request, site,
				TPLDIR_MEMBER, MYFRIEND);
	}

	@RequestMapping("/member/friendApply.jhtml")
	public String friendApply(Integer pageNo, HttpServletRequest request,
			ModelMap model) {
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
				TPLDIR_MEMBER, FRIEND_APPLY);
	}

	private boolean validateApply(BbsUser user, BbsUser friend) {
		// 用户未登录
		if (user == null) {
			return false;
		}
		// 好友不存在
		if (friend == null) {
			return false;
		}
		// 不允许加自己为好友
		if (user.equals(friend)) {
			return false;
		}
		// 申请被拒绝则可以重新申请好友
		BbsFriendShip bean = bbsFriendShipMng.getFriendShip(user.getId(),
				friend.getId());
		if (bean != null && bean.getStatus() != REFUSE) {
			return false;
		}
		return true;
	}

	private boolean validateAccept(BbsUser user, BbsFriendShip friendShip) {
		// 用户未登录
		if (user == null) {
			return false;
		}
		// 好友申请不存在
		if (friendShip == null) {
			return false;
		}
		// 只处理申请中的好友关系
		if (friendShip.getStatus() != APPLYING) {
			return false;
		}
		// 只处理自己的好友关系
		if (!user.equals(friendShip.getFriend())) {
			return false;
		}
		return true;
	}

	@Autowired
	private BbsUserMng bbsUserMng;
	@Autowired
	private BbsFriendShipMng bbsFriendShipMng;
	@Autowired
	private BbsPointDetailMng bbsPointDetailMng;
	@Autowired
	private BbsFriendsMng bbsFriendsMng;
	
}
