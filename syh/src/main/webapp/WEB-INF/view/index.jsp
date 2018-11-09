<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--shiro 标签 --%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>${sysConfig.sitePame }</title>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<input type="hidden" value="${contextPath}" id="contextPath"></input>

<link   rel="icon" href="${contextPath}/avicon.ico" type="image/x-icon" />
<link   rel="shortcut icon" href="${contextPath}/favicon.ico" />

<!-- Bootstrap core CSS -->
<link href="${contextPath}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/static/css/font-awesome.min.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="${contextPath}/static/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

<link href="${contextPath}/static/css/dashboard.css" rel="stylesheet">
<link href="${contextPath}/static/css/main.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="${contextPath}/static/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->


<style type="text/css">
    html{overflow:hidden;}
</style>
</head>
<body class="body">
	<!-- 头部 strat -->
    <nav class="navbar navbar-inverse navbar-fixed-top topest">
      <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed threeline" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <h1 class="logo">
             <img src="${contextPath}/static/images/logo.png">
             <span class="hidden-xs">${sysConfig.siteName }</span>
          </h1>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse collapse-color" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                  <i class=" icon-user" aria-hidden="true"></i>&nbsp;
                 	<%-- ${userLogin.userSname} --%>
                 	 <shiro:principal property="userCode"/> 
                 	&nbsp;<span class="caret"></span>
              </a>
              <ul class="dropdown-menu user-list">
                 <li>
                    <a href="indexUser">
                       <i class="icon-file-alt" aria-hidden="true"></i>&nbsp;
                      	 个人资料
                    </a>
                 </li>
                 <li>
                    <a href="indexPwd">
                       <i class="icon-key" aria-hidden="true"></i>&nbsp;
                       	修改密码
                    </a>
                 </li>
                 <li>
                    <a href="${contextPath}/open/logout">
                       <i class="icon-signout" aria-hidden="true"></i>&nbsp;
                      		 注销
                    </a>
                 </li>
              </ul>
            </li>
          </ul>
        </div><!-- /.navbar-collapse -->
      </div>
    </nav>
    <!-- 头部 end -->

    <div class="container-fluid">
      <div class="row">
        <!-- 菜单 strat -->
        <div class="col-sm-3 col-md-3 col-lg-2 sidebar">
          <ul class="nav nav-sidebar menu-list">
             <shiro:hasPermission name="/syh/project"> 
	             <li class="level01">
	               <a href="#"> 
	                  <i class="icon-desktop"></i> &nbsp;项目信息管理
	                  <i class="icon-arrow icon-angle-right"></i>
	               </a>
	               <ul class="list-unstyled level02" style=" display: none;">
	                  <shiro:hasPermission name="/classManager/index">  
	                  		<li><a href="classManager">项目分类管理</a></li>
					  </shiro:hasPermission> 
	                  <shiro:hasPermission name="/projectManager/index">  
	                  		<li><a href="projectManager">比赛项目管理</a></li>
					  </shiro:hasPermission> 
	                  <shiro:hasPermission name="/scoreRecord/index">  
	                  		<li><a href="scoreRecord">计分记牌配置</a></li>
					  </shiro:hasPermission> 
	               </ul>
	             </li>
             </shiro:hasPermission> 
             
	         <shiro:hasPermission name="/syh/signUp">  
	             <li class="level01">
	               <a href="#"> 
	                  <i class="icon-group"></i> &nbsp;报名信息管理
	                  <i class="icon-arrow icon-angle-right"></i>
	               </a>
	               <ul class="list-unstyled level02" style=" display: none;">
		              <shiro:hasPermission name="/unitInfo/index">  
	                  	  <li><a href="unitInfo"> 参赛单位信息管理</a></li>
	            	  </shiro:hasPermission> 
		              <shiro:hasPermission name="/athleteBaseInfo/index">  
	                      <li><a href="athleteBaseInfo">运动员基本信息管理</a></li>
	                  </shiro:hasPermission> 
		              <shiro:hasPermission name="/participatInfo/index">  
	                      <li><a href="participatInfo">运动员参赛信息</a></li>
	                  </shiro:hasPermission> 
		              <shiro:hasPermission name="/warnInfo/index">  
	                      <li><a href="warnInfo">预警提醒信息查询</a></li>
	                  </shiro:hasPermission> 
	               </ul>
	             </li>
	        </shiro:hasPermission> 
	         
		    <shiro:hasPermission name="/syh/achievement">  
	             <li class="level01">
	               <a href="#"> 
	                  <i class="icon-list-alt"></i> &nbsp;成绩信息管理
	                  <i class="icon-arrow icon-angle-right"></i>
	               </a>
	               <ul class="list-unstyled level02" style=" display: none;">
			           <shiro:hasPermission name="/inputScore/index">  
	                  		<li><a href="inputScore">成绩录入管理</a></li>
		         	   </shiro:hasPermission> 
			           <shiro:hasPermission name="/scoreBaseInfo/index">  
	                  		<li><a href="scoreBaseInfo">成绩基本信息管理</a></li>
		        	   </shiro:hasPermission> 
			           <shiro:hasPermission name="/comRecord/index">  
	                  		<li><a href="comRecord">竞赛项目记录库</a></li>
		         	   </shiro:hasPermission> 
	               </ul>
	             </li>
	         </shiro:hasPermission> 
	         
			<shiro:hasPermission name="/syh/reward">  
	             <li class="level01">
	               <a href="#"> 
	                  <i class="icon-trophy"></i> &nbsp;奖惩信息管理
	                  <i class="icon-arrow icon-angle-right"></i>
	               </a>
	               <ul class="list-unstyled level02" style=" display: none;">
				      <shiro:hasPermission name="/pubRewInfo/index">  
	                  		<li><a href="pubRewInfo">竞赛奖惩信息管理</a></li>
		         	  </shiro:hasPermission> 
	               </ul>
	             </li>
	         </shiro:hasPermission> 
	         
			 <shiro:hasPermission name="/syh/query">  
	             <li class="level01">
	               <a href="#"> 
	                  <i class=" icon-bar-chart"></i> &nbsp;查询统计管理
	                  <i class="icon-arrow icon-angle-right"></i>
	               </a>
	               <ul class="list-unstyled level02" style=" display: none;">
					    <shiro:hasPermission name="/queryTeenagersThreeList/index">  
	                  		<li><a href="queryTeenagersThreeList">青少年三榜查询</a></li>
		         		</shiro:hasPermission> 
					    <shiro:hasPermission name="/queryGroupThreeList/index">  
	                  		<li><a href="queryGroupThreeList">群众体育三榜查询</a></li>
		         		</shiro:hasPermission> 
					    <shiro:hasPermission name="/queryDetailList/index">  
	                  		<li><a href="queryDetailList">三榜明细查询</a></li> 
		         		</shiro:hasPermission> 
					    <shiro:hasPermission name="/queryScoreInfo/index">  
	                  		<li><a href="queryScoreInfo">成绩信息统计查询</a></li>
		         		</shiro:hasPermission> 
					    <shiro:hasPermission name="/queryProjectList/index">  
	                  		<li><a href="queryProjectList">项目信息统计查询</a></li>
		         		</shiro:hasPermission> 
					    <shiro:hasPermission name="/queryAthleteList/index">  
	                 		 <li><a href="queryAthleteList">报名信息统计查询</a></li>
		         		</shiro:hasPermission> 
					    <shiro:hasPermission name="/queryPubRew/index">  
	                  		<li><a href="queryPubRew">奖惩信息统计查询</a></li>
		         		</shiro:hasPermission> 
					    <shiro:hasPermission name="/queryPublic/index">  
	                  		<li><a href="queryPublic" target="_blank">成绩公共查询</a></li>
		         		</shiro:hasPermission> 
	                 <!--  <li><a href="cxtjgl_dxjsxmcx.html">单项竞赛项目查询</a></li> -->
	               </ul>
	             </li>
	         </shiro:hasPermission> 
	         
	         
			<shiro:hasPermission name="/syh/system">  
	             <li class="level01">
	               <a href="#"> 
	                  <i class="icon-wrench"></i> &nbsp;系统配置管理
	                  <i class="icon-arrow icon-angle-right"></i>
	               </a>
	               <ul class="list-unstyled level02" style=" display: none;">
					  <shiro:hasPermission name="/sysDict/index">  
	                  		<li><a href="sysDict">数据字典管理</a></li>
		         	  </shiro:hasPermission> 
					  <shiro:hasPermission name="/sysDept/index">  
	                    	<li><a href="sysDept">组织结构管理</a></li>
		         	  </shiro:hasPermission> 
					  <shiro:hasPermission name="/sysMuserInfo/index">  
	                  		<li><a href="sysMuserInfo">系统用户管理</a></li>
		         	  </shiro:hasPermission> 
					  <shiro:hasPermission name="/sysRole/index">  
	                  		<li><a href="sysRole">角色授权管理</a></li>
		           	  </shiro:hasPermission> 
					  <shiro:hasPermission name="	/sysRes/index">  
	                  		<li><a href="sysRes">权限资源管理</a></li>
		         	  </shiro:hasPermission> 
					  <shiro:hasPermission name="/sysConfig/index">  
	                  		<li><a href="sysConfig">系统信息配置</a></li>
		         	  </shiro:hasPermission> 
					  <shiro:hasPermission name="/sysLog/index">  
	                  		<li><a href="sysLog">系统日志管理</a></li>
		         	  </shiro:hasPermission> 
	               </ul>
	             </li>
	         </shiro:hasPermission> 
          </ul>
        </div>
        <!-- 菜单 end -->
        
        <div id="contentBody" class="col-sm-9 col-sm-offset-3 col-md-9 col-md-offset-3 col-lg-10 col-lg-offset-2 main">
             <iframe name="iframe0" width="100%" height="91.3%" src="home" frameborder="0" ></iframe>
        </div>
      </div>
    </div>

    <script src="${contextPath}/static/js/jquery.min.js"></script>
    <script src="${contextPath}/static/js/bootstrap.min.js"></script>
    <script src="${contextPath}/static/js/ie10-viewport-bug-workaround.js"></script>
    <script src="${contextPath}/static/js/menu.js"></script>
    <script type="text/javascript">
    var isFirefox=navigator.userAgent.toUpperCase().indexOf("Firefox")?true:false; 
    	$(".list-unstyled li a").bind("click",function(event){
    		event.preventDefault();
    		var href="";
    		$("#contentBody").html("");
    		href= $(this).attr("href");
    		$(".list-unstyled li a").removeClass("active");
    		$(this).addClass("active");
    		var ss='';
    		if(isChrome()){
    			ss='<iframe name="iframe0" width="100%" height="80%" src="'+href+'" frameborder="0" ></iframe>';
    		}else{
    			ss='<iframe name="iframe0" width="100%" height="100%" src="'+href+'" frameborder="0" ></iframe>';
    		}
    		$("#contentBody").html(ss);
    	});
    	$(".user-list li a:not(:last)").bind("click",function(event){
    		event.preventDefault();
    		var href="";
    		$("#contentBody").html("");
    		href= $(this).attr("href");
    		var ss='';
    		if(isChrome()){
    			ss='<iframe name="iframe0" width="100%" height="80%" src="'+href+'" frameborder="0" ></iframe>';
    		}else{
    			ss='<iframe name="iframe0" width="100%" height="100%" src="'+href+'" frameborder="0" ></iframe>';
    		}   		
    		$("#contentBody").html(ss);
    	});
    	
    	function isChrome() {  
    	    var isChrome = window.navigator.userAgent.indexOf("Chrome") && window.chrome;  
    	  	if (isChrome) { //是否谷歌浏览器 
    	        return true;  
    	    } else {  
    	        return false;  
    	    }  
    	}  
    </script>
  </body>
</html>