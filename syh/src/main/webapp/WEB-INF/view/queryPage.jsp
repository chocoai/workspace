<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<script language="javascript">
if (window != top)
top.location.href = location.href;
</script> 
</head>
<body class="medal-bg">
	<input type="hidden" value="${contextPath}" id="contextPath"/>
	<div class="header"
		style="background-image: url(${contextPath}/static/images/medal-home.png)">
		<div class="logoimg">
			<a href="medal.html" title="湖北省第十五届运动会奖牌榜"><img
				src="${contextPath}/static/images/logo-inverse.png" alt=""
				width="80"></a>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-xs-12">
					<div class="logotxt">
						<h1>湖北省第十五届运动会奖牌榜</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<main class="medal-main">
        <div class="container">
          <div class="col-md-9">
           <table class="table table-bordered table-medal">
              <thead>
                <tr class="gray-medal-bg">
              	  <th>排名</th>
              	  <th>单位</th>
              	  <th>总分</th>
	              <th>金牌<img src="${contextPath}/static/images/gold.png" style="margin-left: 6px;"></th>
	              <th>银牌<img src="${contextPath}/static/images/silver.png" style="margin-left: 6px;"></th>
	              <th>铜牌<img src="${contextPath}/static/images/bronze.png" style="margin-left: 6px;"></th>
                  <th>奖牌</th>
                </tr>
              </thead>
              <tbody id="list">
              </tbody>
           </table> 
          </div>
          <div class="col-md-3">
             <div class="panel panel-primary panel-medal">
                   <div class="panel-heading">
                     <h3 class="panel-title" style="text-align: left;">分类查看</h3>
                   </div>
                   <div class="panel-body">
                      <select class="form-control" id="select">
                        <option value="2">竞技体育(青少年类)奖牌榜</option>
                        <option value="1">全名健身成年人类(地方组)奖牌榜</option>
                        <option value="0">全名健身成年人类(企事业组)奖牌榜</option>
                      </select>
                   </div>
             </div>
          </div>
        </div>
    </main>
	<script src="${contextPath}/static/js/jquery.min.js"></script>
	<script src="${contextPath}/static/js/bootstrap.min.js"></script>
	<script src="${contextPath}/static/js/ie10-viewport-bug-workaround.js"></script>
	<script src="${contextPath}/static/localjs/queryPage.js"></script>
	<script src="${contextPath}/static/layer/layer.js"></script>
	<script src="${contextPath}/static/localjs/comreg.js"></script> 
</body>
</html>