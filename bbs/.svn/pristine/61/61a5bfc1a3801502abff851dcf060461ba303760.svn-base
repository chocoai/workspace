package com.yhcrt.weihu.bbs.action.front;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yhcrt.weihu.bbs.entity.BbsConcern;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.manager.BbsConcernMng;
import com.yhcrt.weihu.bbs.manager.BbsFriendShipMng;
import com.yhcrt.weihu.bbs.manager.BbsFriendsMng;
import com.yhcrt.weihu.bbs.manager.BbsUserMng;
import com.yhcrt.weihu.bbs.web.CmsUtils;
import com.yhcrt.weihu.bbs.web.FrontUtils;
import com.yhcrt.weihu.common.page.Page;
import com.yhcrt.weihu.common.web.RequestUtils;
import com.yhcrt.weihu.core.entity.CmsSite;

@Controller
public class BbsSearchAct {

	@RequestMapping("/search/search_user.jspx")
	public String searchUser(Integer pageSize,Integer pageNo,
			HttpServletRequest request, ModelMap model){
		CmsSite site = CmsUtils.getSite(request);
		FrontUtils.frontData(request, model, site);
		BbsUser user = CmsUtils.getUser(request);
		String keywords = RequestUtils.getQueryParam(request, "keywords");
		Page<BbsUser> pageBean = bbsUserMng.searchUser(pageSize, pageNo, keywords);
		//查找自己已关注过的所有用户的ID
		if(user != null){
			List<BbsConcern> bbsConcerns = bbsConcernMng.getListByUser(user.getId());
			List<Integer> isConcerns = new ArrayList<Integer>();
			if(bbsConcerns != null){
				for(int i=0; i<bbsConcerns.size(); i++){
					isConcerns.add(bbsConcerns.get(i).getConcernUser().getId());
				}
			}
			model.put("isFriends", bbsFriendsMng.getMyFriendsId(user.getId()));
			model.put("isApplys", bbsFriendShipMng.getApplyIds(user.getId()));
			model.put("isConcerns", isConcerns);
		}
		//热门用户
		List<BbsUser> userList = bbsUserMng.getHostUser(10);
		model.put("userList", userList);
		model.put("pageBean", pageBean);
		model.put("keywords", keywords);
		return "/WEB-INF/t/cms/www/blue/search/search_user.html";
	}
	
	@Autowired
	private BbsUserMng bbsUserMng;
	@Autowired
	private BbsConcernMng bbsConcernMng;
	@Autowired
	private BbsFriendShipMng bbsFriendShipMng;
	@Autowired
	private BbsFriendsMng bbsFriendsMng;
	
}
