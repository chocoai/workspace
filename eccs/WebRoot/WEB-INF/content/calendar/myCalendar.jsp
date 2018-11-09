<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<link href="/calendar/css/main.css" rel="stylesheet" type="text/css" />
<link href="/calendar/css/dailog.css" rel="stylesheet" type="text/css" />
<link href="/calendar/css/calendar.css" rel="stylesheet" type="text/css" />
<link href="/calendar/css/dp.css" rel="stylesheet" type="text/css" />
<link href="/calendar/css/alert.css" rel="stylesheet" type="text/css" />
<link href="/calendar/css/blackbird.css" rel="stylesheet" type="text/css" />

<script src="/calendar/js/jquery.min.js" type="text/javascript"></script>
<script src="/calendar/js/Common.js" type="text/javascript"></script>
<script src="/calendar/js/blackbird.js" type="text/javascript"></script>
<script src="/calendar/js/datepicker_lang_zh_CN.js" type="text/javascript"></script>
<script src="/calendar/js/jquery.datepicker.js" type="text/javascript"></script>
<script src="/calendar/js/jquery.alert.js" type="text/javascript"></script>
<script src="/calendar/js/jquery.ifrmdailog.js" defer="defer" type="text/javascript"></script>
<script src="/calendar/js/xgcalendar_lang_zh_CN.js" type="text/javascript"></script>
<script src="/calendar/js/xgcalendar.js?v=1.2.0.4" type="text/javascript"></script>

<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="javascript:window.location.reload();">我的日程</a>
</div>

<div>
	<div id="calhead" style="padding-left: 1px; padding-right: 1px;">
		<div class="cHead">
			<div class="ftitle">
				我的日程
			</div>
			<div id="loadingpannel" class="ptogtitle loadicon"
				style="display: none;">
				正在加载数据...
			</div>
			<div id="errorpannel" class="ptogtitle loaderror"
				style="display: none;">
				非常抱歉，无法加载您的活动，请稍后再试
			</div>
		</div>

		<div id="caltoolbar" class="ctoolbar">
			<div id="faddbtn" class="fbutton">
				<div>
					<span title='点击新建日程' class="addcal">新建日程</span>
				</div>
			</div>
			<div class="btnseparator"></div>
			<div id="showtodaybtn" class="fbutton">
				<div>
					<span title='点击返回当前日程 ' class="showtoday">今天</span>
				</div>
			</div>
			<div class="btnseparator"></div>
			<div id="showdaybtn" class="fbutton">
				<div>
					<span title='日' class="showdayview">日</span>
				</div>
			</div>
			<div id="showweekbtn" class="fbutton fcurrent">
				<div>
					<span title='周' class="showweekview">周</span>
				</div>
			</div>
			<div id="showmonthbtn" class="fbutton">
				<div>
					<span title='月' class="showmonthview">月</span>
				</div>
			</div>
			<div class="btnseparator"></div>
			<div id="showreflashbtn" class="fbutton">
				<div>
					<span title='刷新' class="showdayflash">刷新</span>
				</div>
			</div>
			<div class="btnseparator"></div>
			<div id="sfprevbtn" title="上一个" class="fbutton">
				<span class="fprev"></span>
			</div>
			<div id="sfnextbtn" title="下一个" class="fbutton">
				<span class="fnext"></span>
			</div>
			<div class="fshowdatep fbutton">
				<div>
					<input type="hidden" name="txtshow" id="hdtxtshow" />
					<span id="txtdatetimeshow">Loading</span>
				</div>
			</div>
			<div class="btnseparator"></div>
			
			<div class="clear"></div>
		</div>
	</div>

	<div style="padding: 1px;">
		<div class="t1 chromeColor">
			&nbsp;
		</div>
		<div class="t2 chromeColor">
			&nbsp;
		</div>
		<div id="dvCalMain" class="calmain printborder">
			<div id="gridcontainer" style="overflow-y: visible;"></div>
		</div>
		<div class="t2 chromeColor">
			&nbsp;
		</div>
		<div class="t1 chromeColor">
			&nbsp;
		</div>
	</div>

</div>


<script type="text/javascript">
$(document).ready(function() {
	//eventItems本身是个数组，数组的项本身又是个数组，结构如下所示 
	//[主键,标题,开始时间,结束时间，是否全天日程，是否跨天日程,是否循环日程,颜色主题,是否有权限,地点,参与人]
	//[id,title,start,end，全天日程，跨日日程,循环日程,theme,'','','']       
	//对应的数据类型
	//[String,String,Date,Date,1/0,1/0,1/0,0-21,0/1,String,String] 
   	var view="week";       
	var op = {
		view: view,
		theme:3,
		autoload: true, //是否在页面加载完毕后自动获取当前视图时间的数据
		showday: new Date(),
		EditCmdhandler:Edit,
		DeleteCmdhandler:Delete,
		ViewCmdhandler:View,    
		onWeekOrMonthToDay:wtd,
		onBeforeRequestData: cal_beforerequest,
		onAfterRequestData: cal_afterrequest,
		onRequestDataError: cal_onerror, 
		url: "/calendar/list.htm" ,  
		quickAddUrl: "/calendar/add.htm" ,  
		quickUpdateUrl: "/calendar/update.htm" ,  
		quickDeleteUrl:  "/calendar/delete.htm" //快速删除日程的
	   /* timeFormat:" hh:mm t", //t表示上午下午标识,h 表示12小时制的小时，H表示24小时制的小时,m表示分钟
		tgtimeFormat:"ht" //同上 */             
	};
	var $dv = $("#calhead");
	var _MH = document.documentElement.clientHeight;
	var dvH = $dv.height() + 2;
	op.height = _MH - dvH;
	op.eventItems =[];

	//debugger;
	var p = $("#gridcontainer").bcalendar(op).BcalGetOp();
	if (p && p.datestrshow) {
		$("#txtdatetimeshow").text(p.datestrshow);
	}
	$("#caltoolbar").noSelect();
	
	$("#hdtxtshow").datepicker({ picker: "#txtdatetimeshow", showtarget: $("#txtdatetimeshow"),
	onReturn:function(r){                          
					var p = $("#gridcontainer").BCalGoToday(r).BcalGetOp();
					if (p && p.datestrshow) {
						$("#txtdatetimeshow").text(p.datestrshow);
					}
			 } 
	});
	function cal_beforerequest(type)
	{
		var t="正在加载数据...";
		switch(type)
		{
			case 1:
				t="正在加载数据...";
				break;
			case 2:                      
			case 3:  
			case 4:    
				t="正在处理请求...";                                   
				break;
		}
		$("#errorpannel").hide();
		$("#loadingpannel").html(t).show();    
	}
	function cal_afterrequest(type)
	{
		switch(type)
		{
			case 1:
				$("#loadingpannel").hide();
				break;
			case 2:
			case 3:
			case 4:
				$("#loadingpannel").html("操作成功!");
				window.setTimeout(function(){ $("#loadingpannel").hide();},2000);
			break;
		}              
	   
	}
	function cal_onerror(type,data)
	{
		$("#errorpannel").html(data.Msg);
		$("#errorpannel").show();
	}
	function Edit(data)
	{
	   var eurl="/calendar/edit.htm";   
		if(data)
		{
			var url = StrFormat(eurl,data);
			url = url + "?calId="+data[0];
			window.showModalDialog(url,"新增日程",'dialogWidth:550px;dialogHeight:350px; dialogLeft:200px;dialogTop:150px;center:yes;help:yes;resizable:yes;status:yes');
		 	$("#gridcontainer").BCalReload();
<!--			OpenModelWindow(url,{ width: 500, height: 400, caption:"修改日程",onclose:function(){-->
<!--			   $("#gridcontainer").BCalReload();-->
<!--			}});-->
			
		}
	}    
	function View(data)
	{
		
		var vurl="/calendar/show.htm";   
		if(data)
		{
			var url = StrFormat(vurl,data);
			url = url + "?calId="+data[0];
			OpenModelWindow(url,{ width: 500, height: 400, caption: "查看日程"});
		}                
	}    
	function Delete(data,callback)
	{  
		$.alerts.okButton="确定";  
		$.alerts.cancelButton="取消";  
		hiConfirm("是否要删除该日程?", '确认',function(r){ r && callback(0);});           
	}
	function wtd(p)
	{
	   if (p && p.datestrshow) {
			$("#txtdatetimeshow").text(p.datestrshow);
		}
		$("#caltoolbar div.fcurrent").each(function() {
			$(this).removeClass("fcurrent");
		})
		$("#showdaybtn").addClass("fcurrent");
	}
	//显示日视图
	$("#showdaybtn").click(function(e) {
		//document.location.href="#day";
		$("#caltoolbar div.fcurrent").each(function() {
			$(this).removeClass("fcurrent");
		})
		$(this).addClass("fcurrent");
		var p = $("#gridcontainer").BCalSwtichview("day").BcalGetOp();
		if (p && p.datestrshow) {
			$("#txtdatetimeshow").text(p.datestrshow);
		}
	});
	//显示周视图
	$("#showweekbtn").click(function(e) {
		//document.location.href="#week";
		$("#caltoolbar div.fcurrent").each(function() {
			$(this).removeClass("fcurrent");
		})
		$(this).addClass("fcurrent");
		var p = $("#gridcontainer").BCalSwtichview("week").BcalGetOp();
		if (p && p.datestrshow) {
			$("#txtdatetimeshow").text(p.datestrshow);
		}

	});
	//显示月视图
	$("#showmonthbtn").click(function(e) {
		//document.location.href="#month";
		$("#caltoolbar div.fcurrent").each(function() {
			$(this).removeClass("fcurrent");
		})
		$(this).addClass("fcurrent");
		var p = $("#gridcontainer").BCalSwtichview("month").BcalGetOp();
		if (p && p.datestrshow) {
			$("#txtdatetimeshow").text(p.datestrshow);
		}
	});
	
	$("#showreflashbtn").click(function(e){
	
		$("#gridcontainer").BCalReload();
	});
	
	//点击新增日程
	$("#faddbtn").click(function(e) {
		var url ="/calendar/new.htm";
		window.showModalDialog(url,"新增日程",'dialogWidth:550px;dialogHeight:350px; dialogLeft:200px;dialogTop:150px;center:yes;help:yes;resizable:yes;status:yes');
		$("#gridcontainer").BCalReload();
	});
	//点击回到今天
	$("#showtodaybtn").click(function(e) {
		var p = $("#gridcontainer").BCalGoToday().BcalGetOp();
		if (p && p.datestrshow) {
			$("#txtdatetimeshow").text(p.datestrshow);
		}


	});
	//上一个
	$("#sfprevbtn").click(function(e) {
		var p = $("#gridcontainer").BCalPrev().BcalGetOp();
		if (p && p.datestrshow) {
			$("#txtdatetimeshow").text(p.datestrshow);
		}

	});
	//下一个
	$("#sfnextbtn").click(function(e) {
		var p = $("#gridcontainer").BCalNext().BcalGetOp();
		if (p && p.datestrshow) {
			$("#txtdatetimeshow").text(p.datestrshow);
		}
	});
	$("#changetochinese").click(function(e){
		location.href="?lang=zh-cn";
	});
	$("#changetoenglish").click(function(e){
		location.href="?lang=en-us";
	});
	 $("#changetoenglishau").click(function(e){
		location.href="?lang=en-au";
	});
	
});
</script>
