<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<% String contextPath = request.getContextPath(); %>
<!doctype html>
<html lang="zx-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>视频监控</title>
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/css/bootstrap.min.css" >
<link rel="stylesheet" href="<%=contextPath%>/res/css/jimmy.css" type="text/css">
<style>
  a,a:hover,a:focus { text-decoration: none; }
  .left { float: left; width: 60%; height: 100%; padding: 15px 5px 30px 5px; position: absolute; }
  .video { float: left; width: 100%; background-color: #eee; height: 98%;}

</style>

<script src="<%=contextPath%>/res/js/jquery-3.2.1.min.js"></script>
<script src="<%=contextPath%>/res/js/bootstrap.min.js"></script>
<script src="<%=contextPath%>/res/plugins/layer/js/layer.js"></script>
<script type="text/javascript" src="<%=contextPath%>/res/js/videocontrol/json2.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/res/js/videocontrol/GisParam.js"></script>  
  	<script language="javascript">
	    var activeMap = new Map(); 
	    var talkMap = new Map(); 
		//初始化视频插件
		function initActiveX(){
			var ret = document.getElementById("Video").init(JSON.stringify(getInitData()));
			if(ret != 0){
				layer.msg("视频插件初始化失败！请检查……");
			}
			document.getElementById("Video").SetScreenStyle(1,1,5);
		}
		function oneScreen(){
			document.getElementById("Video").SetScreenStyle(1,1,5);
		}
		function fourScreen(){
			document.getElementById("Video").SetScreenStyle(2,2,5);
		}
		function nineScreen(){
			document.getElementById("Video").SetScreenStyle(3,3,5);
		}
		//初始化ocx
		function getInitData(){
			var data = {
				buttons: [ 1,2,6,5,8],
				menus: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
			};
			var diskplaninfo = {};
			diskplaninfo.diskpolicytype = 3;
			diskplaninfo.disklist = "D";
			diskplaninfo.diskdeltype = -1;
			var otherinfo = {};
			otherinfo.packtime = 1800;
			otherinfo.downloadPath = "D:\\RecordFile\\";
			otherinfo.capture = "D:\\capture\\";
			var info = {};
			info.username = "admin";
			info.password = "111111";
			info.client_sup_id = "whyhcscms";
			info.dev_sup_id = "whyhcscms";
			info.client_sup_ip = "192.168.1.10";
			info.client_sup_port = "8000";
			info.OcxType = 0;
			info.buttons = data.buttons;
			info.menus = data.menus;
			info.diskplaninfo = diskplaninfo;
			info.otherinfo = otherinfo;
			info.authority = 1;
			info.userLevel=100;
			return info;
		}
		
	 //开始预览视频方法
	 //参数：caption：通道1，dev_id：15，username：admin,password:1111,
	 //client_sup_id:dev1,dev_sup_id:dev1,client_sup_ip:220.220.220.66,client_sup_port:8000,ch:0,data_type:0
		function startVideo(devid){
			//获取接口需设置参数
			var info = getGisVideoParam(devid);
			var ret = document.getElementById("Video").StartVideo(info);
            if (ret != 0) {
            	layer.msg("ret != 0,视频连接失败！");
            }else{
            	var index = document.getElementById("Video").GetMonitorNum(); //获取当前窗口选中号  0开始
            	activeMap.set(devid,index);
            	document.getElementById("Video").MoveNext();  //选中下一个
            }
            return ret;
		}
	 
		//停止预览
		function stopVideoFromList(devid){
			var index = activeMap.get(devid);
			document.getElementById("Video").SetActive(index);
			var ret = document.getElementById("Video").StopVideo();
	        if (ret != 0) {
	        	layer.msg("停止预览失败！", ret);
	        }else{
	        	activeMap.delete(devid);
	        }
	        return ret;
		}

		//停止预览
		function stopVideo(){
			var index = document.getElementById("Video").GetMonitorNum(); //获取当前窗口选中号  0开始
			activeMap.forEach(function (value, key, map) {
			    if(index==value){
			    	document.getElementById('videoList').contentWindow.document.getElementById("dev_"+key).click();
			    }
			});
		}
		//抓拍
		function capture(){
			var ret = document.getElementById("Video").Capture("D:\\capture");
			if(ret==0){
				layer.msg("照片已存储到D:\\capture");
			}else{
				layer.msg("抓拍失败！");
			}
		}
		
		function startTalk(){
			var devid ;
			var index = document.getElementById("Video").GetMonitorNum();
			activeMap.forEach(function (value, key, map) {
			    if(index==value){
			    	devid = key;
			    }
			});
			var starttalkid = $("#starttalkid");
			if(starttalkid.text()=='对讲'){
	 			var parm = getTalkParam(devid);	
				var fd = document.getElementById("Video").StartTalk(parm);
				if(fd != -1){
					layer.msg("语音对讲开启！");
					starttalkid.text('关闭');
					talkMap.set(devid,fd);
					document.getElementById("Video").OpenSound();
				}else{
					layer.msg("语音对讲开启失败！");
				};
			}else{
				var fd = talkMap.get(devid);
	 			var ret = document.getElementById("Video").StopTalk(fd);
	 			if(ret==0){
	 				layer.msg("语音对讲关闭！");
					starttalkid.text('对讲');	
					document.getElementById("Video").CloseSound();
				}else{
					layer.msg("语音对讲关闭失败！");
				}
			}
		}
		var param='1';	//设置速度
		var param2='1';	//设置预置位
		//PTZ控制
		function PtzControl(cmd, start, power){
			if(cmd == 8 || cmd == 9 || cmd == 39){
				"{\"cmd\":0,\"param\":0,\"start\":0,\"power\":0}"
				var paramInfo = "{\"cmd\":"+cmd+",\"param\":"+param2+",\"start\":"+start+",\"power\":"+power+"}";
				var ret = document.getElementById("Video").PtzControl(paramInfo);
				if (ret != 0) {
					layer.msg("PTZ控制失败！", ret);
				}
			} else{
				"{\"cmd\":0,\"param\":0,\"start\":0,\"power\":0}"
				var paramInfo = "{\"cmd\":"+cmd+",\"param\":"+param+",\"start\":"+start+",\"power\":"+power+"}";
				var ret = document.getElementById("Video").PtzControl(paramInfo);
				if (ret != 0) {
					layer.msg("PTZ控制失败！", ret);
				}
			}
		}
	</script>
</head>

<body onload="initActiveX();">
<div class="container-fluid" style="padding-left: 0px; padding-right: 0px; overflow-x: hidden;">
	<div class="row-fluid">
		<div id="left" class="collapse in" style="width: 130px;">
			<div id="secondMenu">
				<ul class="nav nav-pills nav-stacked text-center">
					<shiro:hasPermission name="submenu:device:list">
						<li><a href="<%=contextPath%>/device/list" target="_self"><span>终端列表</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:device:setting">
						<li><a href="<%=contextPath%>/devicesetting" target="_self"><span>终端设置</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:device:video:list">
						<li><a href="<%=contextPath%>/deviceVideo/list" target="_self"><span>视频设备管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:device:video:monitor">
						<li class="active"><a href="<%=contextPath%>/device/videoControl" target="_self"><span>视频监控</span></a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</div>
		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right" style="padding-top: 0px;padding-left: 0px;padding-right: 0px;margin-left: 0px;margin-right: 0px;">
		<div class="left">
       		<OBJECT id="Video" class="video" classid="clsid:02EFF2E9-3D57-461F-BDAC-7A598239E53C" hspace=0 vspace=0>
			</OBJECT>
        	<div class="devices-btns">
          		<button class="btn btn-default" onclick="stopVideo();">停止</button>
          		<button class="btn btn-default" onclick="oneScreen();">单屏</button>
          		<button class="btn btn-default" onclick="fourScreen();">四屏</button>
         		<button class="btn btn-default" onclick="nineScreen();">九屏</button>
          		<button class="btn btn-default" onMouseDown="PtzControl(21,1,0);" onMouseUp="PtzControl(21,0,0);">上转</button>
          		<button class="btn btn-default" onMouseDown="PtzControl(22,1,0);" onMouseUp="PtzControl(22,0,0);">下转</button>
          		<button class="btn btn-default" onMouseDown="PtzControl(23,1,0);" onMouseUp="PtzControl(23,0,0);">左转</button>
          		<button class="btn btn-default" onMouseDown="PtzControl(24,1,0);" onMouseUp="PtzControl(24,0,0);">右转</button>
       			<button class="btn btn-default" onClick="PtzControl(29,1,0);"  >扫描</button>
       			<button class="btn btn-default" onclick="capture();">抓拍</button>
          		<button class="btn btn-default"  onclick="startTalk();" id="starttalkid" >对讲</button>
        	</div>
   		</div>
		<iframe id="videoList" name="videoList" src="<%=contextPath%>/device/videoList" frameborder="0" width="100%" height="600px" scrolling="no"> 
		</iframe>
  		</div>
	</div>
</div>
<script type="text/javascript" src="<%=contextPath%>/res/js/jimmy.common.js"></script>
</body>
</html>
