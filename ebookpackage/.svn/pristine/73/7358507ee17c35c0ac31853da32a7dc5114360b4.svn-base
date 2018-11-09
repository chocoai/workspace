<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="newTag" uri="/WEB-INF/tld/pageTag.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request" />
<c:set var="css" value="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>header</title>
<link href="${ctx}/css/wdstyle.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<style type="text/css">
body
{
-moz-user-select: none;
}
</style>
</head>
<body onselectstart="return false">
<div class="wd_main">
	<div class="wd_head pngimg">
    	<div class="fr pdtb15">${sessionScope.sysUser.account}， 你好　|　
    	<a href="javascript:void(0);" id="changePassword" class="wd_cz"><em class="wd_ico wd_ico1">&nbsp;</em>修改密码</a>　
    	<a href="${ctx}/sys/user/logout" class="wd_cz" target="_parent"><em class="wd_ico wd_ico2">&nbsp;</em>注销</a>　
    	<%-- <a href="${ctx}/api/downloadclient/downclient" target="_blank" class="wd_cz"><em class="wd_ico wd_ico3">&nbsp;</em>下载</a> --%>
    	</div>
    	<div><!-- <a href="javascript:void(0);"> --><img src="${ctx}/images/logo.png" width="500" height="45"><!-- </a> --></div>
    </div>
	<div class="wd_nav" >
    	<span class="place">您的位置</span>
        <a href="${ctx}/index.html" target="_parent">首页</a>
        <div id="viewPath"></div>
    </div>
</div>
<script type="text/javascript">
var winObj =  window.parent.main.contentWindow || window.parent.main;
$(function(){
	$("#changePassword").click(function(){
		$(winObj.document).find(".changePwd").eq(0).jumpBox(true);
	});
});

$.fn.extend({
	//open box
	jumpBox:function(style){
		var _method=$(this);
		$(this).css({'position':'absolute'});
		center($(this),style);
		$(winObj.document).find("#screen").eq(0).css({'display':'block','width':'100%','height':$(winObj.document).height()});
		$(this).css({'display':'block'});
		$(this).find("*[name='close']").click(function(){
			$(winObj.document).find("#screen").eq(0).hide();
			_method.hide();
			enableScroll();
			return false;
		})
	},
	
	closeBox:function(){
		$(winObj).find("#screen").eq(0).hide();
		enableScroll();
		$(this).hide();
	}
});


function center(obj,style) {		//取中间值
	var screenWidth = $(winObj).width(), screenHeight = winObj.document.body.clientHeight; //$(winObj).height()
	var scrolltop = $(winObj.document).scrollTop();
	var objLeft = (screenWidth - obj.width())/2 ;
	var objTop = (screenHeight - obj.height())/2  + scrolltop;
	
	obj.css({position:'absolute',left: objLeft + 'px', top: objTop + 'px'/*,'display': 'block'*/});
	
	disableScroll();
}

/**---------------屏蔽和启用页面滚动条-----------------------*/
function disableScroll() {
    //鼠标移动到滚动条上时，禁止滚动条随滚轮滚动和鼠标拖动
    var height = $(winObj).scrollTop(); 
    $(winObj).scroll(function(){
		$(winObj).scrollTop(height);
	});
}
//启用滚动条
function enableScroll() {
    //启用滚动条
    $(winObj).unbind("scroll");
}

</script>

</body>
</html>
