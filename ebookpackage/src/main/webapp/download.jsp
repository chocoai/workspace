<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request" />
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>移动讲台</title>
	<link rel="stylesheet" href="http://css.huijiaoyun.cn/tianyu_edu_dev/html_touch/download/css/style.css" />
	<script src="/ebookpackage/js/jquery.js" type="text/javascript"></script>
	

</head>
<body>
	
	<section class="download_main">
		<div class="bg">
			<img src="http://css.huijiaoyun.cn/tianyu_edu_dev/html_touch/download/images/dowmload_01.jpg" alt="">
			<img src="http://css.huijiaoyun.cn/tianyu_edu_dev/html_touch/download/images/dowmload_02.jpg" alt="">
			<img src="http://css.huijiaoyun.cn/tianyu_edu_dev/html_touch/download/images/dowmload_03.jpg" alt="">
			<img src="http://css.huijiaoyun.cn/tianyu_edu_dev/html_touch/download/images/dowmload_04.jpg" alt="">
			<img src="http://css.huijiaoyun.cn/tianyu_edu_dev/html_touch/download/images/dowmload_05.jpg" alt="">
			<img src="http://css.huijiaoyun.cn/tianyu_edu_dev/html_touch/download/images/dowmload_06.jpg" alt="">
			<img src="http://css.huijiaoyun.cn/tianyu_edu_dev/html_touch/download/images/dowmload_07.jpg" alt="">
			<img src="http://css.huijiaoyun.cn/tianyu_edu_dev/html_touch/download/images/dowmload_08.jpg" alt="">
			<img src="http://css.huijiaoyun.cn/tianyu_edu_dev/html_touch/download/images/dowmload_09.jpg" alt="">
			<img src="http://css.huijiaoyun.cn/tianyu_edu_dev/html_touch/download/images/dowmload_10.jpg" alt="">
		</div>
		<a href="#" id="imgDownLoad" class="down_btn"  onclick="downSoftwore();">
		</a>
	</section>
	
	<div class="mask"></div>
	<div class="tips"><img src="http://css.huijiaoyun.cn/tianyu_edu_dev/html_touch/download/images/tips.jpg" alt=""></div>
	
	
<script type="text/javascript">	
window.onload = function(){
    if(isWeiXin()){
        var p = document.getElementsByTagName('p');
        p[0].innerHTML = window.navigator.userAgent;
    }
}
function isWeiXin(){
    var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
		var oDownBtn = document.getElementsByClassName('down_btn')[0];
		var oMask = document.getElementsByClassName('mask')[0];
		var oTip = document.getElementsByClassName('tips')[0];
		oDownBtn.onclick = function(){
			oMask.style.display = 'block';
			oTip.style.display = 'block';	
		}
		
    }else{
		return false;
    }
}

function check() {
	var u = navigator.userAgent, app = navigator.appVersion;
	var browser = {
		versions : function() {
			return {
				trident : u.indexOf('Trident') > -1, //IE内核
				presto : u.indexOf('Presto') > -1, //opera内核
				webKit : u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
				gecko : u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
				mobile : !!u.match(/AppleWebKit.*Mobile.*/)
						|| !!u.match(/AppleWebKit/), //是否为移动终端
				ios : !!u.match(/(i[^;]+;(U;)? CPU.+Mac OS X)/), //ios终端
				android : u.indexOf('Android') > -1
						|| u.indexOf('Linux') > -1, //android终端或者uc浏览器
				iPhone : u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
				iPad : u.indexOf('iPad') > -1, //是否iPad
				webApp : u.indexOf('Safari') == -1
			//是否web应该程序，没有头部与底部
			};
		}(),
		language : (navigator.browserLanguage || navigator.language)
				.toLowerCase()
	}
	/* 	 document.writeln("<BR/>语言版本: "+browser.language);
	 document.writeln(" <BR/>是否为移动终端: " + browser.versions.mobile);
	 document.writeln(" <BR/>ios终端: " + browser.versions.ios);
	 document.writeln(" <BR/>android终端: " + browser.versions.android);
	 document.writeln(" <BR/>是否为iPhone: " + browser.versions.iPhone);
	 document.writeln(" <BR/>是否iPad: " + browser.versions.iPad);
	 document.writeln(navigator.userAgent); */
	var headCode = "";
	var terminalType = "1";
	
	if ('' != '') {
		headCode = '';
	}
	//alert(terminalType);
	//if ('2' != "") {
	//	terminalType = '2';
	//}
	
	if (browser.versions.ios) {
		terminalType = '2';
	} else if (browser.versions.android) {
		terminalType = '1';
	}
	
	var webroot = document.location.pathname;
	var rootpath = webroot.substring(0, webroot.substr(1).indexOf('/') + 1);
 
  	$.ajax({
		url : rootpath + '/api/appDownload/download2',
		type : "POST",
		cache : false,
		async : false,
		dataType : "text",
		data : {
			"terminalType" : terminalType
		},
		success : function(data) {
			if (data != "") {
				window.location.href = data
			}
		}
	});    
} 




function downSoftwore(){
	check();
}



</script>
</body>
</html>