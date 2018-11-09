<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.whty.common.util.*" %>
<%@ page import="com.whty.assis.api.utils.HttpUtils" %>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.whty.assis.manage.service.BasePropertyService"%>

<%@page import="org.springframework.web.context.support.AnnotationConfigWebApplicationContext"%>

<%@page import="org.apache.commons.lang3.StringUtils"%>


<%@ page import="net.sf.json.JSONObject" %>

<%
	String userName =null;
	String userId ="";
	String personId = "";
	String platformCode = "";
	String usessionId = "";
	String loginPlatformCode="";
	
	Map<String, Object> param = new HashMap<String, Object>();
	
	String appId = "goDy89cfmgmUZdNwwgt9CR67zd9aSDPK";
	String appKey = "KGjZc9Xxb0d9yEst51A0JmK4K2XqRCuf";

	//String appId = "UnKLu6SBn4pwwP5KucTZVGOXsuzAXoIp";
	//String appKey = "kQyBPQHCnp2WIHD5e08cfMIJZUtvjfzV";
	
	String timeStamp = Long.valueOf(System.currentTimeMillis()).toString();

	String keyInfoStr = appId + appKey + timeStamp;

	byte[] hmacSHA = EncryptionUtils.getHmacSHA1(keyInfoStr, appKey);
	String digest = EncryptionUtils.bytesToHexString(hmacSHA);

	
	String path = request.getContextPath(); 
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; 
    String sysCode = request.getParameter("sysCode");//用request得到 
	
    String ticket =request.getParameter("ticket");
    
    System.out.println(sysCode);
    
    System.out.println(ticket);
    
	param.put("appId", appId);
	param.put("timeStamp", timeStamp);
	param.put("keyInfo", digest);
	param.put("sysCode", sysCode);
	
	platformCode = sysCode;
	//loginPlatformCode = sysCode;
	String ss = "http://gateway.system.eduyun.cn:40015/apigateway/getAccessToken";
	try {
		String result = HttpUtils.getInstance().httpPost(ss, JSONObject.fromObject(param).toString());
		System.out.println(result);
		JSONObject jsonObject = JSONObject.fromObject(result);
		
		if("000000".equals(jsonObject.optString("retCode"))){
			JSONObject data = jsonObject.optJSONObject("data");
			String accessToken = data.optString("accessToken");
			String url2 = "http://gateway.system.eduyun.cn:40015/userSession/validaTicket?accessToken="+accessToken;
			Map<String,Object> ticketMap = new HashMap<String,Object>();
			ticketMap.put("ticket", ticket);
			
			String result2 = HttpUtils.getInstance().httpPost(url2, JSONObject.fromObject(ticketMap).toString());
			System.out.println(result2);
			JSONObject userInfo = JSONObject.fromObject(result2);
			
			if("000000".equals(userInfo.optString("retCode"))){
				JSONObject userInfoData = userInfo.optJSONObject("data");
				userName = userInfoData.optString("name");
				userId = userInfoData.optString("userId");
			}

			//String url3 = "http://gateway.system.eduyun.cn:40015/userSession/validaTicket?accessToken="+accessToken;
			//String result3 = HttpUtils.getInstance().httpPost(url3, JSONObject.fromObject(ticketMap).toString());
			//System.out.println(result3);			
			
			if(userId != null && !userId.equals("")){
				String getUserKeyInfo ="http://gateway.system.eduyun.cn:40015/baseInfo/getUserkeyInfo?accessToken="+accessToken;
				Map<String,Object> userKeyInfoParam = new HashMap<String,Object>();
				userKeyInfoParam.put("sysCode",sysCode);
				
				String[] personIds = {userId};
				userKeyInfoParam.put("personId",personIds);
				System.out.println(JSONObject.fromObject(userKeyInfoParam).toString());
				String result4 = HttpUtils.getInstance().httpPost(getUserKeyInfo, JSONObject.fromObject(userKeyInfoParam).toString());
				System.out.println(result4);
				
				ServletContext sc = this.getServletContext();
				ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sc);
				String[] beans = ac.getBeanDefinitionNames();

				BasePropertyService serviceuser = (BasePropertyService) ac.getBean("basePropertyServiceImpl");// 这里就可以直接取出所需要的service层中的Bean了

	
				Map<String,Object> paramUserName = serviceuser.getUserName(result4);
				
				System.out.println("11");
				
				userName = paramUserName.get("userName").toString();
				personId = paramUserName.get("userId").toString();
				loginPlatformCode = paramUserName.get("loginPlatformCode").toString();
				System.out.println(userName+":"+personId+":"+platformCode+":"+loginPlatformCode);
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>