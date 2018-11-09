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
<meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
<title>安全定位</title>
<link rel="stylesheet" href="https://cache.amap.com/lbs/static/main1119.css" />
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.3&key=81528f81f3d776262054cf8281d5572a"></script>
<script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script>
<jsp:include page="/pages/base/base.jsp"></jsp:include>
<style type="text/css">
	.amap-info-content {background: #fff;border: 1px solid #ccc; padding: 10px 18px 10px 10px;line-height: 1.4;overflow: auto;/* border-radius:50%; */}
	.bottom-panel { height: 40px;width: 100%;bottom: 0;margin: 0 auto;background-color: #626160;color: white;font-size: 16px;}
	.layui-layer-close1{background: red;line-height: 0;font-size:0;vertical-align: middle;-webkit-transform: rotate(45deg);}
	th, td { white-space: nowrap; }
    div.dataTables_wrapper {width: 380px;margin: 0 auto;}
	.dthover2 { position: relative; position: relative; box-shadow: 0 0 2px 3px rgba(0,0,0,0.1); height: 140px; width: 280px; border-radius: 4px; }
	.dthover2-hd { float: left; width: 280px; height: 40px; background: #14b45c; line-height: 40px; padding: 0 10px;}
	.dthover2-hd span { float: left; width: 230px; height: 40px; color: #fff; font-size: 14px; white-space: nowrap; text-overflow: ellipsis; overflow: hidden;}
	.dthover2-bd { float: left; width: 280px; padding: 5px 10px; overflow: hidden;background: white; }
	.dthover2-bd p { float: right; width: 260px; line-height: 18px; margin: 2px 0;}
	.dthover2-bd p a { color: #14b45c; }
    
    .dthover-close { float: right; width: 20px; height: 20px; background: url(<%=contextPath%>/res/img/dthover-close.png) no-repeat 0 0; margin-top: 10px; cursor: pointer;} 
	.pointed { float: left; width: 240px; height: 10px; z-index: 10; background: url(<%=contextPath%>/res/img/dthover.png) no-repeat; position: absolute; bottom: -10px; left: 0; }
</style>
</head>
<body>
	<div id="mystyle" style="height:98%">
		<div id="myMap" style="height:96%"></div>
		<div class="bottom-panel" style="float: left">
			<span id="onlinespan" style="position: absolute; margin: 10px;">当前在线 : ${locations.size()}</span>
		</div>
	</div>
			
	<script type="text/javascript">
		//初始化地图对象，加载地图
		var map = new AMap.Map("myMap", {
			resizeEnable : true
		});
		
		var lnglats = new Array();//经纬度
		var address = new Array();//地址
		var time = new Array();//时间
		var imei = new Array();//imei号
		var names = new Array();//会员姓名
		
		if("${empty locations}"){
			<c:forEach items="${locations}" var="location">
				lnglats.push(["${location.longitude}","${location.latitude}"]); 
				address.push("${location.addr}");
				time.push("${location.locationTime}");
				imei.push("${location.imei}");
				names.push("${location.name}");
			</c:forEach>	
		}
		
		var infoWindow = new AMap.InfoWindow({
			offset : new AMap.Pixel(30, -35),
			searchRadius : 5000,
			isCustom: true,
		});

		for (var i = 0, marker; i < lnglats.length; i++) {
			var marker = new AMap.Marker({
				position : lnglats[i],
				map : map,
				icon:"<%=contextPath%>/res/img/mapIcon.png"  //图片路径
			}); 
			marker.content = "<div class=\"dthover2\"><div class=\"pointed\"></div> <div class=\"dthover2-hd\"><span>姓名："+names[i]+" ("+imei[i]+")</span><div class=\"dthover-close\" onclick=\"closeInfoWindow()\"></div></div><div class=\"dthover2-bd\"><p>地址：" +address[i]+ "</p><p>时间："+time[i]+"</p><p><a href='getOrbit?imei="+imei[i]+"&locationTime="+time[i]+"'>轨迹</a></p></div></div>";
			marker.on('click', markerClick);
			marker.emit('click', {
				target : marker
			});
		}
		
		//判断定位是否成功
		if("${sts}" == '1'){
			layer.msg('定位成功', { anim: 6 });
			var i ;
			for (var k = 0; k < imei.length; k++) {
				if("${msg}" == imei[k]){
					i=k;
				}
			}
			if(i){
				setTimeout(function(){  //使用  setTimeout（）方法设定定时1000毫秒
					infoWindow.setContent("<div class=\"dthover2\"><div class=\"pointed\"></div><div class=\"dthover2-hd\"><span>姓名："+names[i]+" ("+imei[i]+")</span><div class=\"dthover-close\" onclick=\"closeInfoWindow()\"></div></div><div class=\"dthover2-bd\"><p>地址：" +address[i]+ "</p><p>时间："+ time[i] +"</p><p><a href='getOrbit?imei="+imei[i]+"&locationTime="+time[i]+"'>轨迹</a></p></div></div>");
					infoWindow.open(map, [longitude[i],latitude[i]]);
			    	map.setZoomAndCenter(14, [longitude[i],latitude[i]]);
				},1000);
			}
		}else if("${sts}" == '0'){
			layer.msg('定位失败', { anim: 6 });
		}
		
		function markerClick(e) {
			infoWindow.setContent(e.target.content);
			infoWindow.open(map, e.target.getPosition());
		}
		map.setFitView();
		//关闭信息窗体
	    function closeInfoWindow() {
	        map.clearInfoWindow();
	    }
		
	    layer.ready(function(){
	    	//iframe窗
	    	layer.open({
	    		type: 1,
	    		skin: 'layui-layer-demo', //样式类名
	    		title: '控制台',
	    		offset: 'rb',
	    		shadeClose: true,
	    		shade: false,
	    		//maxmin: true, //开启最大化最小化按钮
	    		area: ['470px', '370px'],
	    		content: '<table id="dataTable" class="table table-collection table-center">'
	    				+'<thead><tr><th>姓名</th><th>IMEI编号</th><th>心率</th><th>健康数据</th><th>操作</th></tr></thead>'
	    				+'<tbody><c:forEach items="${locations}" var="location">'
	    				+'<tr style="cursor:pointer" onclick="markerClick1(\'${location.longitude}\',\'${location.latitude}\',\'${location.locationTime}\',\'${location.addr}\',this)">'
	    				+'<td><a href="<%=contextPath%>/member/memberInformation?memberId=${location.memberDevice.memberId }&type=map">${location.name}</a></td>'
	    				+'<td>${location.imei}</td><td>${location.memberDevice.hdPulse.pulse}</td><td><a href="<%=contextPath%>/healthService/data?memberId=${location.memberDevice.memberId}">健康数据</a></td>'
	    				+'<td><a href="#" onclick="getLocate(${location.imei});">实时定位</a></td></tr></c:forEach></tbody></table>'
	    	});
	    });
	    
		$('#dataTable').dataTable({
			// DataTables - Features
			"autoWidth" : true, // 控制Datatables是否自适应宽度,默认值true
			"deferRender" : false, // 控制表格的延迟渲染，可以提高初始化的速度。默认值false
			"info" : true, // 控制是否显示表格左下角的信息,默认值true
			"lengthChange" : false, // 是否允许最终用户改变表格每页显示的记录数，默认值true
			"ordering" : false, // 是否允许Datatables开启排序,默认值true
			"paging" : true, // 是否允许表格分页，默认true
			"scrollX" : false, // 是否允许水平滚动，默认值false
			"scrollCollapse" : false, //当显示更少的记录时，是否允许表格减少高度, 默认值false
			"searching" : true, // 此选项用来开启、关闭Datatables的搜索功能,默认值true

			// DataTables - Options
			"destroy" : false, //销毁已经存在的Datatables实例并替换新的选项默认值false
			"displayStart" : 0, //初始化显示的时候从第几条数据开始显示(一开始显示第几页)
			"lengthMenu" : [ 10, 20 ], // 定义在每页显示记录数的select中显示的选项
			// "order" : [ [ 4, "desc" ] ], //表格在初始化的时候的排序，第2列，升序排列  
			"pageLength" : 5, // 改变初始化页长度（每页多少条数据）,默认值:10
			"pagingType" : "simple", // 分页按钮显示选项,full_numbers 首页，尾页，上一页和下一页四个按钮,加上数字按钮
			"renderer" : "bootstrap", // 显示组件渲染器类型
			"search" : {
				"caseInsensitive" : false, //在搜索或者过滤时，是否大小写敏感,默认值true
				"regex" : false, // 允许或者禁止对在搜索字符串中出现的正则表达式字符强制编码, 默认值false
				"smart" : true, // 允许或者禁止DataTables的只能过滤（搜索）功能, 默认值true
			},
			"columnDefs" : [ {
				"orderable" : false,//禁用排序
				"targets" : [ 0, 1 ] //指定的列
			} ],
			
			"language" : { //国际化配置  
				"processing" : "正在获取数据，请稍后...",
				"lengthMenu" : "显示 _MENU_ 条",
				"zeroRecords" : "没有您要搜索的内容",
				"info" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
				"infoEmpty" : "记录数为0",
				"infoFiltered" : "(全部记录数 _MAX_ 条)",
				"loadingRecords" : "数据正在加载中，请稍后...",
				"emptyTable" : "没有搜索到相关内容",
				"search" : "搜索&nbsp;&nbsp;",
				"url" : "",
				"paginate" : {
					"first" : "第一页",
					"previous" : "上一页",
					"next" : "下一页",
					"last" : "最后一页"
				},
				"aria" : {
					"sortAscending" : ": 以升序排列此列",
					"sortDescending" : ": 以降序排列此列"
				}
			}
			
    	});
		
		$(".col-sm-6").eq(1).css("margin-left","70px");
		$(".col-sm-6").eq(1).css("margin-top","10px");
		
		function markerClick1(longitude,latitude,time,address,th){
			var name = $(th).find("td:eq(0)").text();
			var imei = $(th).find("td:eq(1)").text();
			infoWindow.setContent("<div class=\"dthover2\"><div class=\"pointed\"></div><div class=\"dthover2-hd\"><span>姓名："+name+" ("+imei+")</span><div class=\"dthover-close\" onclick=\"closeInfoWindow()\"></div></div><div class=\"dthover2-bd\"><p>地址：" +address+ "</p><p>时间："+ time +"</p><p><a href='getOrbit?imei="+imei+"&locationTime="+time+"'>轨迹</a></p></div></div>");
			infoWindow.open(map, [longitude,latitude]);
	    	map.setZoomAndCenter(14, [longitude,latitude]);
		};
		
		// 实时定位
		function getLocate(imei){
			window.location.href = "getLocate?imei="+imei;
			event.stopPropagation();
		};
		
	</script>
</body>
</html>