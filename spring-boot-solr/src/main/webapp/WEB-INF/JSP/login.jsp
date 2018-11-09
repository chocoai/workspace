<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>EasyUi</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta name="author" content="" />
  	<!-- Facebook and Twitter integration -->
	<meta property="og:title" content=""/>
	<meta property="og:image" content=""/>
	<meta property="og:url" content=""/>
	<meta property="og:site_name" content=""/>
	<meta property="og:description" content=""/>
	<meta name="twitter:title" content="" />
	<meta name="twitter:image" content="" />
	<meta name="twitter:url" content="" />
	<meta name="twitter:card" content="" />

	<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700" rel="stylesheet">
	
	<!-- Animate.css -->
	<link rel="stylesheet" href="<%=contextPath%>/static/css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="<%=contextPath%>/static/css/icomoon.css">
	<!-- Themify Icons-->
	<link rel="stylesheet" href="<%=contextPath%>/static/css/themify-icons.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="<%=contextPath%>/static/css/bootstrap.css">

	<!-- Magnific Popup -->
	<link rel="stylesheet" href="<%=contextPath%>/static/css/magnific-popup.css">

	<!-- Owl Carousel  -->
	<link rel="stylesheet" href="<%=contextPath%>/static/css/owl.carousel.min.css">
	<link rel="stylesheet" href="<%=contextPath%>/static/css/owl.theme.default.min.css">

	<!-- Theme style  -->
	<link rel="stylesheet" href="<%=contextPath%>/static/css/style.css">

	<!-- Modernizr JS -->
	<script src="<%=contextPath%>/static/js/modernizr-2.6.2.min.js"></script>
	<!-- FOR IE9 below -->
	<!--[if lt IE 9]>
	<script src="<%=contextPath%>/static/js/respond.min.js"></script>
	<![endif]-->
	
	<link rel="stylesheet" href="<%=contextPath%>/static/scripts/themes/default/easyui.css">  
<link rel="stylesheet" href="<%=contextPath%>/static/scripts/themes/icon.css">  
<script type="text/javascript" src="<%=contextPath%>/static/scripts/jquery.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/scripts/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/scripts/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=contextPath%>/static/js/jquery-form.js"></script>

	<script type="text/javascript">
		if("${result}"!=null && "${result}"!=""){
			alert("${result}")
		}
	</script>
	</head>
	<body>
	<header id="gtco-header" class="gtco-cover" role="banner" style="background-image: url(<%=contextPath%>/static/images/img_4.jpg)">
		<div class="overlay"></div>
		<div class="gtco-container">
			<div class="row">
				<div class="col-md-12 col-md-offset-0 text-left">
				
					<div class="row row-mt-15em">
						<div class="col-md-7 mt-text animate-box" data-animate-effect="fadeInUp">
							<span class="intro-text-small">Welcome to Splash</span>
							<h1>Build website using this template.</h1>	
						</div>
						<div class="col-md-4 col-md-push-1 animate-box" data-animate-effect="fadeInRight">
							<div class="form-wrap">
								<div class="tab">
									<ul class="tab-menu">
										<li class="active gtco-first"><a href="#" data-tab="login">Login</a></li>
										<li class="gtco-second"><a href="#" data-tab="signup">Sign up</a></li>
									</ul>
									<div class="tab-content">
										<div class="tab-content-inner " data-content="signup">
											<form action="signForm" method="post" id="signFrom">
												<div class="row form-group">
													<div class="col-md-12">
														<label for="username">Username or Email</label>
														<input type="text" class="easyui-validatebox form-control" id="userName" name="userName" required="required">
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-12">
														<label for="password">Password</label>
														<input type="password" class="easyui-validatebox form-control" name="userPassword" required="required">
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-12">
														<label for="password2">Repeat Password</label>
														<input type="password" class="easyui-validatebox form-control" id="userPassword2" >
													</div>
												</div>

												<div class="row form-group">
													<div class="col-md-12">
														<input type="button" class="btn btn-primary" value="Sign up" onclick="signForm()">
													</div>
												</div>
											</form>	
										</div>

										<div  class="tab-content-inner active" data-content="login">
											<form action="loginForm" method="post" id="loginFrom">
												<div class="row form-group">
													<div class="col-md-12">
														<label for="username">Username or Email</label>
														<input type="text" class="easyui-validatebox form-control"  id="email" name="userName" data-options="label:'Email:',required:true,validType:'email'">
													</div>
												</div>
												<div class="row form-group">
													<div class="col-md-12">
														<label for="password">Password</label>
														<input type="password" class="easyui-validatebox form-control"  id="userPassword" name="userPassword" required="required">
													</div>
												</div>

												<div class="row form-group">
													<div class="col-md-12">
														<input type="button"  class="btn btn-primary" value="Login" onclick="submitForm()">
													</div>
												</div>
											</form>	
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
	
<script type="text/javascript">
function submitForm(){
	var validate = $("#loginFrom").form('validate');
	if (validate==true) {  
		$('#loginFrom').submit();
	}
}

function signForm(){
	var validate = $("#signFrom").form('validate');
	if (validate==true) {  
		$('#signFrom').submit();
	}
}
</script>
	<!-- jQuery Easing -->
	<script src="<%=contextPath%>/static/js/jquery.easing.1.3.js"></script>
	<!-- Waypoints -->
	<script src="<%=contextPath%>/static/js/jquery.waypoints.min.js"></script>
	<!-- Carousel -->
	<script src="<%=contextPath%>/static/js/owl.carousel.min.js"></script>
	<!-- countTo -->
	<script src="<%=contextPath%>/static/js/jquery.countTo.js"></script>
	<!-- Magnific Popup -->
	<script src="<%=contextPath%>/static/js/jquery.magnific-popup.min.js"></script>
	<script src="<%=contextPath%>/static/js/magnific-popup-options.js"></script>
	<!-- Main -->
	<script src="<%=contextPath%>/static/js/main.js"></script>
	</body>
</html>

