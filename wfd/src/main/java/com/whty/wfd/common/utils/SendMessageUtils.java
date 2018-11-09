/**
 * 
 */
package com.whty.wfd.common.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.whty.wfd.page.model.TUser;

import net.sf.json.JSONObject;

/**
 * 发送消息
 * 
 * @author zhangzheng
 * @date 2018年9月3日
 */
public class SendMessageUtils {

	protected static Logger logger = LoggerFactory.getLogger(SendMessageUtils.class);

	public static void main(String[] args) {

		String wfdUrl = SysConfigUtils.getStrValue("domain") + "/getPostDetail?postId=0324A03979BC3F801A9482AEEEF0AE0F";

		TUser tuser = new TUser();
		tuser.setId(5192);
		tuser.setLoginPlatformCode("888888");
		tuser.setPlatformCode("420000");
		tuser.setOrgaId("5c9c307224a3429c8e9f8c1ee7083dae");
		tuser.setPersonId("3bb93e9fa9594c8f835974f877d3d3d8");

		// e9dfc1de06894356ac0328e40853dbb9

		List<String> ss = new ArrayList<String>();
		ss.add("0036812f1f0f4865b8722cf7ef10b9d8");
		// sendMessage(tuser, ss, wfdUrl, "你好呀");
		sendOaMessage(tuser, ss, wfdUrl, "化学好学吗，为什么我总考不高？", "张三同学发布了新的提问", "2018-09-19");
	}

	public static void sendOaMessage(TUser tuser, List<String> sendeeIds, String url, String content, String title,
			String pushTime) {
		// String[] sendeeIdStr = sendeeIds.split("|");

		Set<String> setSendeeIds = new HashSet<String>();

		for (String sender : sendeeIds) {
			setSendeeIds.add(sender);
		}

		for (String sender : setSendeeIds) {

			if (sender.equals(setSendeeIds)) {
				continue;
			}

			// String sender = sendeeIdStr[i];
			String publisherId = tuser.getPersonId();
			String publisherName = "微辅导消息";
			String sendeeOrgId = tuser.getOrgaId();
			// String busiId = "AQWER1234";

			String loginPlatformCode = tuser.getLoginPlatformCode();
			String platformCode = tuser.getPlatformCode();
			// String msgType = "link";
			String msgType = "oa";
			String deadline = TimeUtils.date2String(new Date(), TimeUtils.TIME_FORMAT);

			Map<String, String> linkParam = new HashMap<String, String>();
			String wfdUrl = SysConfigUtils.getStrValue("domain") + "/init?showTitle=false&sysGoback=false&personId="
					+ sender + "&platformCode=" + tuser.getPlatformCode() + "&loginPlatformCode="
					+ tuser.getLoginPlatformCode() + "&returnUrl=" + url + "&useWKWebView=1";
			System.out.println(wfdUrl);
			linkParam.put("appUrl", wfdUrl);
			linkParam.put("pcUrl", wfdUrl);
			// linkParam.put("picUrl",
			// "http://css.huijiaoyun.com/tianyu_edu/area/888888/images/logo/logo.png");
			linkParam.put("content", content);
			// linkParam.put("content", content + "wfdUrl:" + wfdUrl);
			// linkParam.put("busiId", busiId);

			Map<String, Object> oaParam = new HashMap<String, Object>();

			Map<String, Object> headParam = new HashMap<String, Object>();
			headParam.put("text", "微辅导消息");
			headParam.put("bgColor", "FFCC0000");
			Map<String, Object> bodyParam = new HashMap<String, Object>();
			bodyParam.put("title", title);
			bodyParam.put("content", content);

			// JSONArray jsonArray = new JSONArray();
			//
			// JSONObject from = new JSONObject();
			// from.put("value", pushTime);
			// from.put("key", "发布时间");
			// jsonArray.add(from);
			// bodyParam.put("form", jsonArray);

			oaParam.put("head", headParam);
			oaParam.put("body", bodyParam);

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("publisherId", publisherId);
			param.put("publisherName", publisherName);
			param.put("sendeeOrgId", sendeeOrgId);
			// param.put("busiId", busiId);
			param.put("sendeeId", sender);
			param.put("loginPlatformCode", loginPlatformCode);
			param.put("platformCode", platformCode);
			param.put("msgType", msgType);
			param.put("deadline", deadline);

			param.put("appUrl", wfdUrl);
			param.put("pcUrl", wfdUrl);

			// param.put("link", linkParam);
			param.put("oa", oaParam);
			String result = HttpUtils.getInstance().httpPost(SysConfigUtils.getStrValue("jxb_sendMessage_url"),
					JSONObject.fromObject(param).toString());

			logger.info(result);
		}
	}

	/**
	 * 多个发送这用 |隔开
	 * 
	 * @param tuser
	 * @param sendeeIds
	 * @return
	 */
	public static void sendMessage(TUser tuser, List<String> sendeeIds, String url, String content) {

		// String[] sendeeIdStr = sendeeIds.split("|");

		for (String sender : sendeeIds) {
			// String sender = sendeeIdStr[i];
			String publisherId = tuser.getPersonId();
			String publisherName = "微辅导消息";
			String sendeeOrgId = tuser.getOrgaId();
			// String busiId = "AQWER1234";

			String loginPlatformCode = tuser.getLoginPlatformCode();
			String platformCode = tuser.getPlatformCode();
			String msgType = "link";
			// String msgType = "oa";
			String deadline = TimeUtils.date2String(new Date(), TimeUtils.TIME_FORMAT);

			Map<String, String> linkParam = new HashMap<String, String>();
			linkParam.put("pcUrl", "");
			String wfdUrl = SysConfigUtils.getStrValue("domain") + "/init?personId=" + sender + "&platformCode="
					+ tuser.getPlatformCode() + "&loginPlatformCode=" + tuser.getLoginPlatformCode() + "&returnUrl="
					+ url + "&useWKWebView=1";
			System.out.println(wfdUrl);
			linkParam.put("appUrl", wfdUrl);
			linkParam.put("pcUrl", wfdUrl);
			// linkParam.put("picUrl",
			// "http://css.huijiaoyun.com/tianyu_edu/area/888888/images/logo/logo.png");
			linkParam.put("content", content);
			// linkParam.put("content", content + "wfdUrl:" + wfdUrl);
			// linkParam.put("busiId", busiId);

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("publisherId", publisherId);
			param.put("publisherName", publisherName);
			param.put("sendeeOrgId", sendeeOrgId);
			// param.put("busiId", busiId);
			param.put("sendeeId", sender);
			param.put("loginPlatformCode", loginPlatformCode);
			param.put("platformCode", platformCode);
			param.put("msgType", msgType);
			param.put("deadline", deadline);
			param.put("link", linkParam);
			// param.put("oa", linkParam);
			String result = HttpUtils.getInstance().httpPost(SysConfigUtils.getStrValue("jxb_sendMessage_url"),
					JSONObject.fromObject(param).toString());

			logger.info(result);
		}

	}

}