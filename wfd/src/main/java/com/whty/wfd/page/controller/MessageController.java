package com.whty.wfd.page.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.whty.wfd.page.model.*;
import com.whty.wfd.page.service.impl.BasePropertyServiceImpl;
import com.whty.wfd.page.util.BaiduYun;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.whty.wfd.base.controller.BaseController;
import com.whty.wfd.common.utils.GUIDGenerator;
import com.whty.wfd.page.dao.TUserMapper;
import com.whty.wfd.page.service.MessageService;
import com.whty.wfd.page.service.PostService;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

/**
 * \* User: zjd \* Date: 2018/8/16 \* Time: 15:41 \* Description: \
 */
@Controller
public class MessageController extends BaseController {

	@Autowired
	private MessageService messageService;
	@Autowired
	private PostService postService;
	@Autowired
	private TUserMapper tUserMapper;
	@Autowired
	private MemcachedClient memcachedClient;

	final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping(value = "sendMessage.html", method = RequestMethod.GET)
	public String sendMessage(ModelMap map, String postId, String content, Integer receiver, String plateId,
			String turn) {
		if (StringUtil.isEmpty(plateId)) {
			TPlatePost tPlatePost = postService.selectByKey(postId);
			plateId = tPlatePost.getPlateId();
			map.put("type", 1);
		} else {
			map.put("type", 2);
		}
		map.put("postId", postId);
		map.put("content", content);
		map.put("receiver", receiver);
		map.put("plateId", plateId);
		map.put("turn", turn);
		return "message/add_message";
	}

	@RequestMapping("addMessage")
	@ResponseBody
	public void addMessage(HttpServletRequest request,HttpServletResponse response, String content, String postId, String[] path, Integer[] userId,
			Integer receiver, String turn, String[] myAudio, Integer[] taking) {
		JSONObject obj = new JSONObject();
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			String reg = "data:image/jpeg;base64,";
			String reg2 = "data:image/jpeg;base64";
			String regAudio = "data:audio/mp4;base64,";
			String regAudio2 = "data:audio/mp4;base64";
			List<TPostMessageImg> tPostMessageImgs = new ArrayList<>();
			List<TPostMessageAudio> tPostMessageAudios = new ArrayList<>();
			TPostMessage tPostMessage = new TPostMessage();
			tPostMessage.setId(GUIDGenerator.getGUID());
			tPostMessage.setReceiver(receiver);
			tPostMessage.setCreateTime(new Date());
			tPostMessage.setContent(content);
			tPostMessage.setUpdateTime(new Date());
			tPostMessage.setPlatePostId(postId);
			tPostMessage.setUserId(tUser.getId());
			tPostMessage.setIsTop(false);
			tPostMessage.setIsRead(false);
			tPostMessage.setDelete(false);
			if (myAudio != null && myAudio.length > 0) {
				for (int i = 0; i < myAudio.length; i++) {
					int leng = myAudio[i].lastIndexOf("base64,");
					if (leng == -1) {
						int leng2 = myAudio[i].lastIndexOf("base64");
						if (leng2 == -1) {
							byte[] imageByte = decoder.decodeBuffer(myAudio[i]);
							String bosAddress = BaiduYun.upload(imageByte, ".aac");
							TPostMessageAudio tPostMessageAudio = new TPostMessageAudio();
							tPostMessageAudio.setAudioUsetaking(taking[0]);
							tPostMessageAudio.setDownUrl(bosAddress);
							tPostMessageAudio.setPostMessageId(tPostMessage.getId());
							tPostMessageAudio.setCreateTime(new Date());
							tPostMessageAudio.setUpdateTime(new Date());
							tPostMessageAudios.add(tPostMessageAudio);
							continue;
						} else {
							continue;
						}
					}
					String base64 = myAudio[i].substring(leng + 7);
					byte[] imageByte = decoder.decodeBuffer(base64);
					String bosAddress = BaiduYun.upload(imageByte, ".aac");
					TPostMessageAudio tPostMessageAudio = new TPostMessageAudio();
					tPostMessageAudio.setAudioUsetaking(taking[i]);
					tPostMessageAudio.setDownUrl(bosAddress);
					tPostMessageAudio.setPostMessageId(tPostMessage.getId());
					tPostMessageAudio.setCreateTime(new Date());
					tPostMessageAudio.setUpdateTime(new Date());
					tPostMessageAudios.add(tPostMessageAudio);
				}
			}
			if (path != null && path.length > 0) {
				for (int i = 0; i < path.length; i++) {
					if (reg2.equals(path[i])) {
						continue;
					}
					String base64 = path[i].replace(reg, "");
					byte[] imageByte = decoder.decodeBuffer(base64);
					String bosAddress = BaiduYun.upload(imageByte, ".jpg");
					TPostMessageImg tPostMessageImg = new TPostMessageImg();
					tPostMessageImg.setPostMessageId(tPostMessage.getId());
					tPostMessageImg.setDownUrl(bosAddress);
					tPostMessageImg.setMd5("1");
					tPostMessageImg.setCreateTime(new Date());
					tPostMessageImg.setUpdateTime(new Date());
					tPostMessageImg.setCreator(tUser.getId());
					tPostMessageImgs.add(tPostMessageImg);
				}
			}
			messageService.addMessage(tUser, tPostMessage, tPostMessageImgs, userId, tPostMessageAudios);
			obj.put("result","success");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("result","fali");
		}
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().print(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("getPostMessage.html")
	public String getPostMessage() {
		return "message/post_message";
	}

	@RequestMapping("getPostMessageList")
	@ResponseBody
	public void getPostMessage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");
		PageInfo<TPostMessage> p = messageService.getPostMessagesByUserId(tUser.getId(), pageNum, pageSize);
		JSONObject obj = new JSONObject();
		for (TPostMessage tPostMessage : p.getList()) {
			tPostMessage.setCreateTimeStr(sdf.format(tPostMessage.getCreateTime()));
		}
		obj.put("list", p.getList());
		obj.put("pageNum", p.getPageNum());
		obj.put("pageTotal", p.getTotal());
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		try {
			response.getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("getPostMessagesByPostId")
	@ResponseBody
	public void getPostMessagesByPostId(HttpServletResponse response, HttpServletRequest request, String postId,
			String orderByClause) {
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");
		List<TPostMessage> tPostMessages = messageService.getPostMessagesByPostId(postId, tUser.getId(), orderByClause);
		JSONObject obj = new JSONObject();
		for (TPostMessage tPostMessage : tPostMessages) {
			tPostMessage.setCreateTimeStr(sdf.format(tPostMessage.getCreateTime()));
		}

		// if (orderByClause.equals("like_count desc")) {
		// Collections.sort(tPostMessages, new Comparator<TPostMessage>() {
		//
		// @Override
		// public int compare(TPostMessage o1, TPostMessage o2) {
		// int like1 = o1.getLikeCount();
		// int like2 = o2.getLikeCount();
		//
		// if (like2 > like1) {
		// return 1;
		// } else if (like2 == like1) {
		// return 0;
		// } else {
		// return -1;
		// }
		// }
		// });
		// }

		obj.put("list", tPostMessages);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		try {
			response.getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 删除评论
	@RequestMapping(value = "deleteMessageByMessageId", method = RequestMethod.GET)
	@ResponseBody
	public int deletePostByPostId(String messageId) {
		return messageService.updateByMessageId(messageId);
	}

	// 设置评论为最佳答案
	@RequestMapping(value = "bestAnswer", method = RequestMethod.GET)
	@ResponseBody
	public int bestAnswer(String messageId, boolean type) {
		return messageService.updateByMessageIdAnswer(messageId, type);
	}

	// 修改单个ID message的未读状态为已读
	@RequestMapping(value = "saveMessageIsRead", method = RequestMethod.GET)
	@ResponseBody
	public int saveMessageIsRead(String messageId, boolean isRead) {
		return messageService.updateByMessageIdIsRead(messageId, isRead);
	}

	@RequestMapping(value = "updateMessage", method = RequestMethod.GET)
	public String updateMessage(HttpServletRequest request) {
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");
		messageService.updateMessageIsRead(tUser.getId());
		return "redirect:index.html";
	}

	// Base64存入缓存中
	@ResponseBody
	@RequestMapping(value = "saveCacheImg", method = RequestMethod.POST)
	public Integer saveCacheImg(HttpServletRequest request, String base64, String id) {// 版块ID
																						// 或者是帖子ID
		Integer status = 0;
		try {
			List<String> strings = new ArrayList<>();
			TUser tUser = (TUser) request.getSession().getAttribute("userObj");
			String cacheKey = tUser.getId().toString() + id;
			if (memcachedClient.get(cacheKey) != null) {
				JSONArray list = JSONArray.fromObject(memcachedClient.get(cacheKey));
				strings = JSONArray.toList(list, String.class, new JsonConfig());
			}
			strings.add(base64);
			JSONArray list = JSONArray.fromObject(strings);
			boolean mstatus = memcachedClient.set(cacheKey, BasePropertyServiceImpl.generateExpireTime(), list);
			if (mstatus) {
				status = 1;
			}
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		return status;
	}

	// Base64 从缓存中删除
	@ResponseBody
	@RequestMapping(value = "deleteCacheImg", method = RequestMethod.POST)
	public Integer deleteCacheImg(HttpServletRequest request, String base64, String id) {// 版块ID
																							// 或者是帖子ID
		Integer status = 0;
		try {
			List<String> strings = new ArrayList<>();
			TUser tUser = (TUser) request.getSession().getAttribute("userObj");
			String cacheKey = tUser.getId().toString() + id;
			if (memcachedClient.get(cacheKey) != null) {
				JSONArray list = JSONArray.fromObject(memcachedClient.get(cacheKey));
				strings = JSONArray.toList(list, String.class, new JsonConfig());
				if (strings.size() > 0) {
					for (int i = 0; i < strings.size(); i++) {
						if (strings.get(i).equals(base64)) {
							strings.remove(i);
						}
					}
				}
				list = JSONArray.fromObject(strings);
				boolean mstatus = memcachedClient.set(cacheKey, BasePropertyServiceImpl.generateExpireTime(), list);
				if (mstatus) {
					status = 1;
				}
			}
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MemcachedException e) {
			e.printStackTrace();
		}
		return status;
	}

	@RequestMapping(value = "saveAudio", method = RequestMethod.POST)
	@ResponseBody
	public Integer saveAudio(HttpServletRequest request, String base64, String postId, Integer taking) {
		try {
			TUser tUser = (TUser) request.getSession().getAttribute("userObj");
			TPostMessage tPostMessage = new TPostMessage();
			TPostMessageAudio tPostMessageAudio = new TPostMessageAudio();
			tPostMessage.setId(GUIDGenerator.getGUID());
			tPostMessage.setCreateTime(new Date());
			tPostMessage.setUpdateTime(new Date());
			tPostMessage.setPlatePostId(postId);
			tPostMessage.setUserId(tUser.getId());
			tPostMessage.setIsTop(false);
			tPostMessage.setIsRead(false);
			tPostMessage.setDelete(false);

			BASE64Decoder decoder = new BASE64Decoder();
			int leng = base64.lastIndexOf("base64,");
			base64 = base64.substring(leng + 7);
			byte[] imageByte = decoder.decodeBuffer(base64);
			String bosAddress = BaiduYun.upload(imageByte, ".aac");
			tPostMessageAudio.setAudioUsetaking(taking);
			tPostMessageAudio.setDownUrl(bosAddress);
			tPostMessageAudio.setPostMessageId(tPostMessage.getId());
			tPostMessageAudio.setCreateTime(new Date());
			tPostMessageAudio.setUpdateTime(new Date());
			messageService.addMessageAudio(tUser, tPostMessage, tPostMessageAudio);
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
}