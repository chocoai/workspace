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
<title>left</title>
<link href="${ctx}/css/wdstyle.css" rel="stylesheet" type="text/css">
<script language="javascript" type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/js/global.js"></script>
<style type="text/css">
body
{
-moz-user-select: none;
}
</style>
<base target="main" />
</head>
<body onselectstart="return false">
<div class="wd_mainLeft">
	<div class="ltjg_lefttop">栏目结构</div>
	
	<div id="menu">
	<c:forEach items="${sysModularList}" var="sm" varStatus="index">
	<c:if test="${sm.parent_id=='0'}">
		<div class="ltjp_tit">
	    	<i class="wd_list_ico ${sm.imgcss}"></i>
	    	<div><em>&nbsp;</em><span>${sm.modular_name }</span></div>
		</div>
		<div class="ltjp_list">
			<ul>
				<c:forEach items="${sysModularList}" var="smt" varStatus="indext">
					<c:if test="${smt.parent_id==sm.id}">
						<li><a href="${smt.modular_path }" id="${smt.id }">${smt.modular_name }</a></li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	</c:forEach>
	
</div>
<script language="JavaScript" type="text/JavaScript">
(function(){
	var menu=document.getElementById('menu'),
		menu_c=menu.getElementsByTagName('div'),
		menu_t=[],menu_l=[];
	for(var i=0,l=menu_c.length;i<l;i++){
		if(menu_c[i].className=='ltjp_tit'){menu_t.push(menu_c[i]);}
		if(menu_c[i].className=='ltjp_list'){menu_l.push(menu_c[i]);}
	}
	for(var j=0,jl=menu_t.length;j<jl;j++){
		menu_t[j].onclick=function(i){
			return function(){
				for(var j=0,jl=menu_t.length;j<jl;j++){
					
					if(menu_t[j]){menu_t[j].getElementsByTagName('div')[0].className=i==j?menu_t[j].getElementsByTagName('div')[0].className=='off'?'on':'off':'on';}
					if(menu_l[j]){menu_l[j].style.display=i==j?menu_l[j].style.display=='block'?'none':'block':'none';}
				}
			}
		}(j)
	}
	
	$('.ltjp_tit').click(function() {
		//设置菜单导航
		var obj2 = window.parent.topFrame.contentWindow || window.parent.topFrame;
		var menu1 = $(this).find("span").eq(0).html();
		var html = '<a>'+menu1+'</a>';
		$(obj2.document.getElementById('viewPath')).prev().attr("class","next");
		obj2.document.getElementById('viewPath').innerHTML = html;
	});
	
	$('.ltjp_list a').click(function() {
		//设置菜单导航
		var obj2 = window.parent.topFrame.contentWindow || window.parent.topFrame;
		var menu1 = $(this).parent().parent().parent().prev().find('span').eq(0).html();
		var menu2 = $(this).html();
		var html = '<a class="next">'+menu1+'</a>';
		html += '<a>'+menu2+'</a>';
		$(obj2.document.getElementById('viewPath')).prev().attr("class","next");
		obj2.document.getElementById('viewPath').innerHTML = html;
		
		//获取模块ID
		var mainObj =  window.parent.main.contentWindow || window.parent.main;
		
		$.cookie('modular_id', this.id, {expires: 1, path: '/'});
	});
})();
</script>
</div>
</body>
</html>
