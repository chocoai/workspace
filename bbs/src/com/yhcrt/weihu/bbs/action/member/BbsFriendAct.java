package com.yhcrt.weihu.bbs.action.member;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhcrt.weihu.bbs.entity.BbsTopic;
import com.yhcrt.weihu.bbs.entity.BbsUser;
import com.yhcrt.weihu.bbs.manager.BbsFriendsMng;
import com.yhcrt.weihu.bbs.manager.BbsTopicMng;
import com.yhcrt.weihu.bbs.web.CmsUtils;

import net.sf.json.JSONObject;

/**
 * BBS好友功能action，其原有的action中只用到了申请加好友和进入好友页面
 * 其中这两个action还改动过，在此类中的action是新增的
 * 
 * 对好友的相关操作中：
 * 		申请：此时查询了是否已经是好友，不是的话就查询是否已经申请过，如果申请过，那么就将申请状态改为申请，没有的话就新建申请
 * 		同意：该人处理的申请记录状态改为接受，再判断两个人是否已经是好友（防止不合法的操作），
 * 		            如果是就不向好友表插入数据，如果不是就向好友表插入两条好友记录，我的和他的，之后还要查询我是否也向他申请了，如果申请了，那么我的申请状态也得改为接受
 * 		拒绝：就将该申请记录状态改为拒绝，不对好友表进行操作
 * 		删除：将好友表中的好友记录物理删除掉，两条，我的和他的，之前的申请状态不需要改动
 * 		          （因为在页面显示是否能够加为好友的时候只查询申请状态为申请中的和好友表中是否为好友的，而是删除的时候，之前的申请如果两个人都申请了，那么申请状态肯定没有申请中这个状态）
 * 加好友时的处理：
 * 		在页面中显示是否能够加为好友，查询的有两种，一种是申请中的，另外一种是好友，所以申请状态为接受和拒绝的只是改变状态不为申请中
 * @author 
 *
 */
@Controller
public class BbsFriendAct {

	@RequestMapping("/personal/forword_topic.jspx")
	@ResponseBody
	public void forwordTopic(Integer topicId,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) throws IOException{
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		BbsUser user = CmsUtils.getUser(request);
		if(user == null){
			json.put("status", "-1");
			json.put("msg", "请重新登录");
			response.getWriter().write(json.toString());
			return ;
		}
		if(topicId == null || topicId == 0){
			json.put("status", "-1");
			json.put("msg", "参数错误");
			response.getWriter().write(json.toString());
			return ;
		}
		BbsTopic topic = bbsTopicMng.findById(topicId);
		if(topic == null){
			json.put("status", "-1");
			json.put("msg", "原贴不存在");
			response.getWriter().write(json.toString());
			return ;
		}
		bbsTopicMng.forwordTopic(topic,user.getId());
		json.put("status", "1");
		json.put("msg", "转发成功");
		response.getWriter().write(json.toString());
	}
	
	@RequestMapping("/personal/friend_sub.jspx")
	@ResponseBody
	public void friendSub(Integer friendId,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) throws IOException{
		response.setCharacterEncoding("UTF-8");
		JSONObject json = new JSONObject();
		BbsUser user = CmsUtils.getUser(request);
		if(user == null){
			json.put("status", "-1");
			json.put("msg", "请重新登录");
			response.getWriter().write(json.toString());
			return ;
		}
		if(friendId == null || friendId == 0){
			json.put("status", "-1");
			json.put("msg", "参数错误");
			response.getWriter().write(json.toString());
			return ;
		}
		//删除
		bbsFriendsMng.delete(user.getId(), friendId);
		json.put("status", "1");
		json.put("msg", "删除成功");
		response.getWriter().write(json.toString());
		return ;
	}
	
	@Autowired
	private BbsFriendsMng bbsFriendsMng;
	@Autowired
	private BbsTopicMng bbsTopicMng;
	
}
