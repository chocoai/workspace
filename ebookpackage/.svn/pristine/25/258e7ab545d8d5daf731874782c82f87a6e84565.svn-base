<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request" />
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<title>移动讲台</title>
	<link rel="stylesheet" href="http://css.huijiaoyun.cn/tianyu_edu_dev/html_touch/download/css/style.css" />
	<script type="text/javascript">
		if("${deviceType}"=="iPhone"){
			window.location.href="${ios_download_url}";
		}
	</script>
</head>
<body>
	<c:if test="${deviceType eq 'android'}">
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
		<a href="http://vike.huijiaoyun.cn/ebookpackage/api/downloadApp/app?id=F9A6FA6C6294F9E1489C7D7A9510FC38" class="down_btn"></a>
	</section>
	
	<div class="mask"></div>
	<div class="tips"><img src="http://css.huijiaoyun.cn/tianyu_edu_dev/html_touch/download/images/tips.jpg" alt=""></div>
	</c:if>
	
	<c:if test="${deviceType ne 'android'}">
		Is not an android or ios.
	</c:if>
	
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
</script>
</body>
</html>