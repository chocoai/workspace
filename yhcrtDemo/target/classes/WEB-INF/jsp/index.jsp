<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% String contextPath = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>炎黄后台管理系统</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath %>static/ext/examples/shared/example.css" />  
		<link rel="stylesheet" type="text/css" href="<%=basePath %>static/ext/ext-icon.css" /> 
	    <script type="text/javascript" src="<%=basePath %>static/ext/examples/shared/include-ext.js?theme=classic"></script>
	    <script type="text/javascript" src="<%=basePath %>static/ext/locale/ext-lang-zh_CN.js"></script>
<%-- 	    <script type="text/javascript" src="<%=basePath %>static/ext/examples/shared/options-toolbar.js"></script> --%>
<%-- 	    <script type="text/javascript" src="<%=basePath %>static/ext/examples/shared/examples.js"></script> --%>
	    <script type="text/javascript">
	  		var userName = '${SESSION_SYS_USER.userName}';
	  		var globalRoleId = '${SESSION_SYS_USER.role}';
	    	var appBaseUri = '<%=basePath %>';
	    	var appName = '炎黄后台管理系统';
	        Ext.Loader.setPath('Ext.app', '<%=basePath %>static/ext/examples/portal/classes');
	        Ext.Loader.setPath('Ext.ux', '<%=basePath %>static/ext/examples/ux');
	        Ext.Loader.setPath('Yhcrt.app', '<%=basePath %>static/ext/examples/portal');
	      //通过Ext封装的ajax请示时，默认增加请求头
	      //或者可以使用所有ajax请求都带有的x-request-with:XMLHttpRequest头信息
	      //如果既有Ext又有Jquery而且要区分处理，则需要这种方式
	      Ext.Ajax.defaultHeaders = {
	          'Request-By': 'Ext'    //标识ajax请求
	      };
	      //当响应时，自动拦截并判断如果符合timeout为true就重定位到redirectUri
	      Ext.Ajax.on('requestcomplete',checkSessionStatus, this);         
	      function checkSessionStatus(conn,response,options){
	          var json = Ext.decode(response.responseText);
	          if(typeof json == 'object' 
	              && json.timeout){
	              Ext.Msg.alert("提示","登入超时,系统将自动跳转到登陆页面,请重新登入!"+json.redirectUri,function(){
	                  top.window.location.href = json.redirectUri;
	              });
	          }      
	      }
	    </script>
	    <script type="text/javascript" src="<%=basePath %>static/ext/examples/portal/portal.js"></script>
<!-- 	    <script type="text/javascript">
	        Ext.require([
	            'Ext.layout.container.*',
	            'Ext.resizer.Splitter',
	            'Ext.fx.target.Element',
	            'Ext.fx.target.Component',
	            'Ext.window.Window',
	            'Ext.app.Portlet',
	            'Ext.app.PortalColumn',
	            'Ext.app.PortalPanel',
	            'Ext.app.Portlet',
	            'Ext.app.PortalDropZone'
	        ]);
	    </script> -->
	</head>
	<body>
		<span id="app-msg" style="display:none;"></span>
	    <form id="history-form" class="x-hide-display">
	        <input type="hidden" id="x-history-field" />
	        <iframe id="x-history-frame"></iframe>
	    </form>
	</body>
</html>
