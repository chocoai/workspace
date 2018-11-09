<%@page import="net.sf.json.JSONArray"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.whty.common.util.*" %>
<%@ page import="com.whty.assis.api.utils.HttpUtils" %>
<%@page import="org.apache.commons.lang3.StringUtils"%>


<%@ page import="net.sf.json.JSONObject" %>

<%
	String userName =null;
	String tixiId ="";
	String personId = "";
	String platformCode = "";
	String usessionId = "";
	String loginPlatformCode="";
	String appAccessToken ="";
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
    
    String difangplatformCode = request.getParameter("platformCode");
    
    
    if(StringUtils.isEmpty(sysCode)){
    	sysCode = difangplatformCode;
    	
    }
    
    System.out.println(sysCode);
    
    System.out.println(ticket);
    
	param.put("appId", appId);
	param.put("timeStamp", timeStamp);
	param.put("keyInfo", digest);
	param.put("sysCode", sysCode);
	
	platformCode = sysCode;
	loginPlatformCode = sysCode;
	String ss = "http://gateway.system.eduyun.cn:40015/apigateway/getAccessToken";
	try {
		String result = HttpUtils.getInstance().httpPost(ss, JSONObject.fromObject(param).toString());
		System.out.println(result);
		JSONObject jsonObject = JSONObject.fromObject(result);
		
		if("000000".equals(jsonObject.optString("retCode"))){
			JSONObject data = jsonObject.optJSONObject("data");
			String accessToken = data.optString("accessToken");
			
			appAccessToken = accessToken;
			
			String url2 = "http://gateway.system.eduyun.cn:40015/userSession/validaTicket?accessToken="+accessToken;
			Map<String,Object> ticketMap = new HashMap<String,Object>();
			ticketMap.put("ticket", ticket);
			
			String result2 = HttpUtils.getInstance().httpPost(url2, JSONObject.fromObject(ticketMap).toString());
			System.out.println(result2);
			JSONObject userInfo = JSONObject.fromObject(result2);
			
			if("000000".equals(userInfo.optString("retCode"))){
				JSONObject userInfoData = userInfo.optJSONObject("data");
				userName = userInfoData.optString("name");
				tixiId = userInfoData.optString("userId");
			}

			//String url3 = "http://gateway.system.eduyun.cn:40015/userSession/validaTicket?accessToken="+accessToken;
			//String result3 = HttpUtils.getInstance().httpPost(url3, JSONObject.fromObject(ticketMap).toString());
			//System.out.println(result3);			
			
			if(tixiId != null && !tixiId.equals("")){
				String getUserKeyInfo ="http://gateway.system.eduyun.cn:40015/baseInfo/getUserkeyInfo?accessToken="+accessToken;
				Map<String,Object> userKeyInfoParam = new HashMap<String,Object>();
				userKeyInfoParam.put("sysCode",sysCode);
				
				String[] personIds = {tixiId};
				userKeyInfoParam.put("personId",personIds);
				System.out.println(JSONObject.fromObject(userKeyInfoParam).toString());
				String result4 = HttpUtils.getInstance().httpPost(getUserKeyInfo, JSONObject.fromObject(userKeyInfoParam).toString());
				System.out.println(result4);
				
				String basePropertyUrl = "http://localhost:8080/hdcourse/baseProperty/getUserInfo";
				
				String result5 = HttpUtils.getInstance().httpPost(basePropertyUrl, result4);
				
				JSONObject aamUserInfo = JSONObject.fromObject(result5);
				
				userName = aamUserInfo.optString("userName");
				personId = aamUserInfo.optString("userId");
				loginPlatformCode = aamUserInfo.optString("loginPlatformCode");
				String loginAccount = aamUserInfo.optString("loginAccount");
				String orgId = aamUserInfo.optString("orgId");
				System.out.println(userName+":"+personId+":"+platformCode+":"+loginPlatformCode+":"+loginAccount);
				
				if(StringUtils.isNotEmpty(userName)){
					//String appdengji = "http://gateway.system.eduyun.cn:40015/cert/independentAppRegister?accessToken="+accessToken;
					
					String appdengji = "http://gateway.system.eduyun.cn:40015/cert/independentAppValidateUser?accessToken="+accessToken;
					
					System.out.println(appdengji);
					
					Map<String,Object> appDengjiParam = new HashMap<String,Object>();
					appDengjiParam.put("loginAccount",loginAccount);
					appDengjiParam.put("userId",personId);
					//appDengjiParam.put("type",1);
					appDengjiParam.put("personId",tixiId);
					//appDengjiParam.put("personId","546a4e5ed8914c02aa4e5ed8915c0240");
					//appDengjiParam.put("orgId","15484566546045169e36f8d9e2a2491e");
					
					System.out.println(JSONObject.fromObject(appDengjiParam).toString());
					System.out.println(HttpUtils.getInstance().httpPost(appdengji, JSONObject.fromObject(appDengjiParam).toString() ));

				}
				
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta itemprop="name" content="智慧课堂"/>
<meta http-equiv="Expires" content="-1"/>
<meta name="keywords" content="智慧课堂"/>

<title>智慧课堂</title>
<link href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/area/888888/css/w_public.css" rel="stylesheet" type="text/css" />
<link href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/area/edu3.0/css/hjy_class.css" rel="stylesheet" type="text/css"/>
<link rel="shortcut icon" href="http://css.huijiaoyun.com/tianyu_edu/area/888888/images/head/edu.ico" type="image/x-icon"/>
<link href="https://css.zjer.cn/zj_edu/common/css/public.css" rel="stylesheet" type="text/css" />
<link href="https://css.zjer.cn/zj_edu/common/css/schoolTemp/template34_nuodinghan.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="logo-rect1 pos_rel">

        </div>
<div class="g-content" id="screenScroll">
    <div class="screen0" name="screen">
        <div class="logo-rect">
            <div class="w1200 clearfix">

                <div class="g-header">
            		<ul class="head-menu fr">
            			<%if(userName !=null) {%>
							 <li><%=userName %>	</li> 
						<%}else{%>
							
						<%} %>
                
                     </ul>
                </div>
            </div>
        </div>
        <div class="item-rect screen-rect">
            <div class="screen0-txt1 tc" data-top='0'>
            	<img data-src="http://zhkt.huijiaoyun.com/area/edu3.0/images/hjy_class/screan0_txt1.png" src="" width="681">
            </div>
            
            <div class="screen0-computer tc pos_rel"  data-top='25'>
            	<img data-src="http://zhkt.huijiaoyun.com/area/edu3.0/images/hjy_class/screan0_computer.png" src="" class="com" width="1144" height="290"/></div>
            <div class="screan0-btn tc" data-top='37'>
            	 <a href="javascript:void(0);" onclick="return openHudong('启动互动课堂客户端','520px');">
            		<img data-src="http://zhkt.huijiaoyun.com/images/pc_openBtn3.png" src="" width="171"/>
            	</a>
				
				<a target="_blank" href="http://whty.bj.bcebos.com/hdkt/%E4%BA%92%E5%8A%A8%E8%AF%BE%E5%A0%82_2.5.5_setup_20180531(%E6%AD%A3%E5%BC%8F).exe" title="PC版">
            		<img data-src="http://zhkt.huijiaoyun.com/images/pc_uploadBtn3.png" src="" width="171"/>
            	</a>
            <div class="phone_sm_img">
                <ul>
                    <li>
                    	<p class="photo"><img src="http://zhkt.huijiaoyun.com/images/jxb.png" width="100px" height="100px"/></p>
                        <p class="name">移动讲台<br />（Android/IOS）</p>
                    </li>
                </ul>
            </div>
            
            
            </div>
	    </div>
        <p class="meteor meteor-1"></p>
        <p class="meteor meteor-2"></p>
        <p class="meteor meteor-3"></p>
        <p class="meteor meteor-4"></p>
        <p class="meteor meteor-5"></p>
        <p class="meteor meteor-6"></p>
        <p class="meteor meteor-7"></p>
        <p class="meteor meteor-8"></p>
    </div>
    <div class="screen1" name="screen">
        <div class="item-rect screen-rect">
            <div class="screen1-rect tc pos_rel">
            <img data-src="http://zhkt.huijiaoyun.com/area/edu3.0/images/hjy_class/screen1_img1.png" src="" class="imgsrc screen1-img1"/>
            <img data-src="http://zhkt.huijiaoyun.com/area/edu3.0/images/hjy_class/screen1_img2.png" src="" class="imgsrc screen1-img2"/>
            </div>
        </div>
    </div>
    <div class="screen2" name="screen">
        <div class="item-rect screen-rect">
            <div class="screen2-rect pos_rel"> <img data-src="http://zhkt.huijiaoyun.com/area/edu3.0/images/hjy_class/screen2_img1.png"  src="" class="imgsrc screen2-img1"/> <img data-src="../area/edu3.0/images/hjy_class/screen2_img2.png" src="" class="imgsrc screen2-img2"/> </div>
        </div>
    </div>
    <div class="screen3" name="screen">
        <div class="item-rect screen-rect">
            <div class="screen3-rect pos_rel">
            <img data-src="http://zhkt.huijiaoyun.com/area/edu3.0/images/hjy_class/screen3_1.png" src="" class="imgsrc screen3-img1" />
            <img data-src="http://zhkt.huijiaoyun.com/area/edu3.0/images/hjy_class/screen3_2.png" src="" class="imgsrc screen3-img2" />
            </div>
        </div>
    </div>
    <div class="screen4" name="screen">
        <div class="item-rect screen-rect">
                <div class="screen4-rect pos_rel">
            <img data-src="http://zhkt.huijiaoyun.com/area/edu3.0/images/hjy_class/screen4_2.png" src="" class="imgsrc screen4-img2" alt=""/>
            <img data-src="http://zhkt.huijiaoyun.com/area/edu3.0/images/hjy_class/screen4_3.png" src="" class="imgsrc screen4-img3" alt=""/>
            </div>
        </div>
    </div>
    <div class="screen5" name="screen">
        <div class="item-rect screen-rect">
                <div class="screen5-rect pos_rel">
		<div class="screen5-rect pos_rel">
                <img data-src="http://zhkt.huijiaoyun.com/area/edu3.0/images/hjy_class/screen5_1.png" src="" class="imgsrc screen5-img1"/>
                <img data-src="http://css.huijiaoyun.com/tianyu_edu/area/edu3.0/images/hjy_class/screen5_2.png" src="" class="imgsrc screen5-img2"/>
            </div>
        </div>
    </div>




</div>
<div class="loading-1">
    <i></i>
    <i></i>
    <i></i>
</div>

<div id="hudong" class="dis_none">
    <dl class="py_techHelper clearfix">
        <dt>
                <a href="javascript:void(0)" onclick="return Run();">
                <img src="http://122.229.30.170:20006/sme/upload//2017/0614/ACE300F79B7A.png" />
                <p>立即启动</p>
                </a>
                </dt>
        <dd>
            <p class="c888 f20 t_c mgt20">尚未安装互动课堂？</p>
            <a href="http://zhkt.huijiaoyun.com/hdcourse/appDownLoad/down" target="_blank"><p class="f18">立即下载</p></a>
        </dd>
    </dl>
</div>

<script type="text/javascript" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/common/js/jquery.js"></script>
<!--<script type="text/javascript" src="../common/js/jquery-3.1.1.min.js"></script>-->
<script type="text/javascript" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/common/js/jquery.mousewheel.min.js"></script>
<script type="text/javascript" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/common/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/area/edu3.0/js/fullscreen_class.js"></script>

<script type="text/javascript" src="https://css.zjer.cn/zj_edu/common/js/jquery.artDialog.js"></script>
<script type="text/javascript" src="https://css.zjer.cn/zj_edu/common/js/jquery.artDialog.plugins.js"></script>
<script type="text/javascript" src="https://css.zjer.cn/zj_edu/common/js/ajaxForm.js?123"></script>
<script type="text/javascript" src="https://css.zjer.cn/zj_edu/common/js/schoolTemp.js"></script>

<script type="text/javascript">
//$('.phone_sm').hover(function(){
 //       $(this).find('.phone_sm_img').fadeIn(1);
//},function(){
 //       $(this).find('.phone_sm_img').fadeOut(1);
//});

function openHudong(title,width){
    dialogTh = art.dialog({
        title:title,
        content:$('#hudong')[0],   //弹出框的内容
        width:width,
        padding:'25px 15px',//弹出框的宽度
        drag:false, //弹出框是否可移动，默认为false
        lock:true //弹出框是否可移动，默认为true,只当有lock为true时，才出有背景的出现
    });
}

function Run() {
	
	var userId = '<%=personId%>';
	var platformCode =  '<%=platformCode%>';
	var loginPlatformCode= '<%=loginPlatformCode%>';
	
	
	var url_l = 'IntelligentClassApp://<?xml version=\'1.0\' encoding=\'utf-8\'?><WebParam><txId>goDy89cfmgmUZdNwwgt9CR67zd9aSDPK</txId><usessionId></usessionId><userPlatformCode>330200</userPlatformCode><platformCode>330000</platformCode><bookId>10135</bookId><uId></uId><AccessToken></AccessToken><startType>1</startType></WebParam>';
	var url = '"IntelligentClassApp://<?xml version=\'1.0\' encoding=\'utf-8\'?><WebParam><txId>goDy89cfmgmUZdNwwgt9CR67zd9aSDPK</txId><usessionId></usessionId><userPlatformCode>'+platformCode+'</userPlatformCode><platformCode>'+loginPlatformCode+'</platformCode><bookId></bookId><uId>'+userId+'</uId><AccessToken></AccessToken><startType>1</startType></WebParam>"'; 
	   var flag=true;
	    try{
	        $.ajax({
	            type: "GET",
	            url: "http://localhost:8719/IntelligentClass/SSO/Run",                 
	            dataType: 'jsonp',
	            cache: false,
	            async: false,
	            timeout:2000,
	            data: {
	                    args: url
	             },
	            success: function(res){
	                var code = res.Code;
	                flag=false;
	                alert(res.code)
	                if(res.Code == '0'){
	                }else {
	                    window.location.href=url;
	                }
	              },
	              error:function(msg){
	                  flag=false;
	                  window.location.href=url;
	              } 
	            });
	    } catch(e) {
	        flag=false;
	        window.location.href=url_l;
	    }finally {
	        setTimeout(function(){
	            if(flag)
	            {
	                window.location.href=url_l;
	            }
	        }, 1000);
	    }
	}

</script>
</body>
</html>
