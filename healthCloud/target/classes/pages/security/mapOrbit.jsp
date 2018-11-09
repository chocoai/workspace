<%@page language="java" pageEncoding="utf-8"%>

<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<% String contextPath = request.getContextPath(); %>
<!doctype html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport"
		content="initial-scale=1.0, user-scalable=no, width=device-width">
	<title>给多个点添加信息窗体</title>
	
	<jsp:include page="/pages/base/base.jsp"></jsp:include>
	<link rel="stylesheet" href="https://cache.amap.com/lbs/static/main1119.css" />
	<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.3&key=81528f81f3d776262054cf8281d5572a"></script>
	<script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script>
	<style type="text/css">
		#footer {
	    margin: 8px 0 0 0;
	    padding: 3px 0 0 0;
	    font-size: 11px;
	    text-align: center;
	    border-top: 2px solid #0663A2;
		}
		.alert-success {
	    color: #38b44a;
	    background-color: #caeecf;
	    border-color: #b7e8b6;
	    height:50px;
	    dispaly:block;
	    margin:0 auto;
		}
	</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="col-md-12" style="padding-left: 0px;padding-right: 0px;">
				<ol class="breadcrumb">
					<form id="deviceForm" action="getOrbit" method="post" class="form-inline">
						<input type="hidden" name="imei" value="${imei} " />
						&nbsp;&nbsp;
						<div class="form-group">
							<label for="alarmTime" class="control-label">告警时间</label>&nbsp;
							<div class="input-group date form_date" style="width: 196px;" data-date-format="yyyy-mm-dd" data-link-field="locationTime" data-link-format="yyyy-mm-dd">
								<input class="form-control" id="input_time" name="input_time" size="9" type="text" value="" readonly>
								<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span> 
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							</div>
							<input type="hidden" id="locationTime" name="locationTime" value="${locationTime }" /><br/>
						</div>
						&nbsp;&nbsp;
						<button type="submit" class="btn btn-info">查看</button>
						<button style="float: right;margin-right:30px;" class="btn btn-info" type="button"  onclick="javascript:window.location.href='getMap'">&nbsp;&nbsp;返回</button>
					</form>
				</ol>
			</div>
		</div>
	</div>
	<div id="myMap" style="height:90%;"></div>
	<script type="text/javascript">
		$('.form_date').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			format: 'yyyy-mm-dd',
			startView: 2,
			minView: 0,
			maxView: 4,
			minView: 'month',
			//pickerPosition: "bottom-left",
			forceParse: 0
	    });
		
		$("#input_time").val("${locationTime}");
		
		//初始化地图对象，加载地图
		var map = new AMap.Map("myMap", {
			resizeEnable : true
		});
		 //获取用户所在城市信息
		
		var lnglats = new Array();//经纬度
		var address = new Array();//地址
		var time = new Array();//时间
		<c:forEach items="${locations}" var="location">  
			lnglats.push(["${location.longitude}","${location.latitude}"]); 
			address.push("${location.addr}");
			time.push("${location.locationTime}");
		</c:forEach>
		
		var infoWindow = new AMap.InfoWindow({
			offset : new AMap.Pixel(0, -30)
		});
		for (var i = 0, marker; i < lnglats.length; i++) {
			var marker = new AMap.Marker({
				position : lnglats[i],
				map : map,
				icon:"<%=contextPath%>/res/img/trailPass.png"  //图片路径
			});

			var myIcon2 = new AMap.Icon({            
	            size: new AMap.Size(22, 22),  //图标大小
	            image: "<%=contextPath%>/res/img/end.png",
	            imageOffset: new AMap.Pixel(0, -60)});
			if(i==0){
				marker.setIcon("<%=contextPath%>/res/img/start.png");
			} //i==0为起点，设置maker图标 
			if(i==(lnglats.length-1)){
				marker.setIcon("<%=contextPath%>/res/img/end.png");
			} //i==(lnglats.length-1)为终点，设置maker图标 
			marker.content ="地址：" +address[i]+ "</br>" + "时间："+time[i];
			marker.on('click', markerClick);
			marker.emit('click', {
				target : marker
			});
		}
		
		function markerClick(e) {
			infoWindow.setContent(e.target.content);
			infoWindow.open(map, e.target.getPosition());
		}
		map.setFitView();
		
	</script>
</body>
</html>