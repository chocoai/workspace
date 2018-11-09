<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>


<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>区域统计</title>
</head>

<body>
<div class="wql_content mg20 wql_bg_fff">
 <form id="pageForm"  name="pageForm" action="${ctx}/manage/countHome/list" method="post">
	<div class="wql_g_sel">
		<div class="wql_selbox01 clearfix pd10">
			<span class="fl">统计区域：</span>
				<select id="province" name="provinceCode" class="sel mgr10" style="width:150px;">
					<option value="" <c:if test="${empty provinceCode}">selected="selected"</c:if>>全部</option>
					<c:if test="${not empty provinceList}">
					<c:forEach items="${provinceList}" var="a" varStatus="st">
					<option value="${a.areaCode }" <c:if test="${a.areaCode==PROV_CODE}">selected="selected"</c:if>>${a.areaName }</option>
					</c:forEach>
					</c:if>
				</select>  
				<select id="city" name="cityCode" class="sel mgr10" style="width:150px;">
					<option value="" <c:if test="${empty cityCode}">selected="selected"</c:if>>全部</option>
					<c:if test="${not empty cityList}">
					<c:forEach items="${cityList}" var="a" varStatus="st">
					<option value="${a.areaCode }" <c:if test="${a.areaCode==CITY_CODE}">selected="selected"</c:if>>${a.areaName }</option>
					</c:forEach>
					</c:if>
				</select>
             <input type="submit" onclick="search()" class="btn_blue" value="查询"/>
            <span class="fr">${currentTime}</span>
		</div>
	</div>
	<div class="bo_title">
        <h3 class="pdl10">全部区域统计数据（标题显示区域信息，如，湖北省武汉市统计数据）</h3>
    </div>
    <div class="code_table data_index">
        <table class="mod_table t_c" width="100%" style="border-collapse:collapse;"> 
            <tbody>
	            <tr>
	                <td>
	                	<p>区域统计</p>
	                    <p class="num"><span class="c00f">${couList.ALL_LOGIN_CITY_NUM }</span></p>
	                </td>
	                <td>
	                	<p>学校总数</p>
	                    <p class="num"><span class="c3cc">${couList.ALL_LOGIN_ORGA_NUM }</span></p>
	                </td>
	                <td>
	                	<p>教师总数</p>
	                    <p class="num"><span class="cf90">${couList.ALL_LOGIN_USER_NUM }</span></p>
	                </td>
	            </tr>
        	</tbody>
        </table>
    </div>
    <div class="bo_title mgt15">
        <h3 class="pdl10">基本指标</h3>
    </div>
    <div class="code_table">
        <table class="small_space data_list" width="100%" style="border-collapse:collapse;">
            <thead>
	            <tr>
	                <th></th>
	                <th>新增用户</th>
	                <th>活跃用户</th>
	                <th>累计用户</th>
	                <th>授课统计</th>
	                <th>累计授课次数</th>
	                <th>有效用户数</th>
	                <th>累计有效用户</th>
	                <th>有效课程数</th>
	                <th>累计有效课程</th>
	            </tr>
	        </thead>
	        <tbody>
	        	<tr>
	        		<td>昨日</td>
	        		<td>${currentBasicIndexes.ADD_LOGIN_NEW_NUM }</td>
	        		<td>${currentBasicIndexes.ADD_LOGIN_USER_NUM }</td>
	        		<td>${currentBasicIndexes.ALL_LOGIN_USER_NUM }</td>
	        		<td>${currentBasicIndexes.ADD_LOGIN_NUM }</td>
	        		<td>${currentBasicIndexes.ALL_LOGIN_NUM }</td>
	        		<td>${currentBasicIndexes.ADD_LOGIN_VALID_NUM }</td>
	        		<td>${currentBasicIndexes.ALL_LOGIN_VALID_NUM }</td>
	        		<td>${currentBasicIndexes.ADD_LOGIN_COUR_NUM }</td>
	        		<td>${currentBasicIndexes.ALL_LOGIN_COUR_NUM }</td>
	        	</tr>
	        	<tr>
	        		<td>前日</td>
	        		<td>${lastBasicIndexes.ADD_LOGIN_NEW_NUM }</td>
	        		<td>${lastBasicIndexes.ADD_LOGIN_USER_NUM }</td>
	        		<td>${lastBasicIndexes.ALL_LOGIN_USER_NUM }</td>
	        		<td>${lastBasicIndexes.ADD_LOGIN_NUM }</td>
	        		<td>${lastBasicIndexes.ALL_LOGIN_NUM }</td>
	        		<td>${lastBasicIndexes.ADD_LOGIN_VALID_NUM }</td>
	        		<td>${lastBasicIndexes.ALL_LOGIN_VALID_NUM }</td>
	        		<td>${lastBasicIndexes.ADD_LOGIN_COUR_NUM }</td>
	        		<td>${lastBasicIndexes.ALL_LOGIN_COUR_NUM }</td>
	        	</tr>
	        </tbody>
        </table>
    </div>
    <div class="bo_title mgt15">
        <h3 class="pdl10">各项指标数据统计</h3>
    </div>
    <div class="code_stateBox">
    	<div class="wql_g_tab">
    		<div class="wql_tabbox01" name="wql_tab" id="dateDIv2">
    			<div class="wql_tabbtns clearfix" name="wql_tabbtns">
    				<a href="javascript:;"  name="b1" class="wql_tabbtn on" >活跃学校</a>
    				<a href="javascript:;"  name="b2" class="wql_tabbtn" >活跃用户</a>
    				<a href="javascript:;"  name="b3"  class="wql_tabbtn" >授课次数</a>
    				<a href="javascript:;"  name="b4"  class="wql_tabbtn" >新增用户</a>
    				<a href="javascript:;"  name="b5"  class="wql_tabbtn" >有效用户数</a>
    				<a href="javascript:;"  name="b6"  class="wql_tabbtn" >有效课程数</a>
    				<a href="javascript:;"  name="b7"  class="wql_tabbtn" >电子书包授课</a>
    				<a href="javascript:;"  name="b8"  class="wql_tabbtn" >移动讲台授课</a>
    				<a href="javascript:;"  name="b9"  class="wql_tabbtn" >掌中黑板授课</a>
    				<a href="javascript:;"  name="b10"  class="wql_tabbtn br0" >答题器授课</a>
    			</div>
    			<div class="wql_tabcont"  name="wql_tabcont"  style="height:480px;">
			
		    		<div class="wql_g_tab mgt20 ">
		                <div class="wql_tabbox02 clearfix" name="wql_tab">
							<div class="wql_tabbtns clearfix fl" name="wql_tabbtns" >
		                        <a href="javascript:;"  name="d1" class="wql_tabbtn on">按月度统计</a>
		                        <a href="javascript:;"  name="d2" class="wql_tabbtn" >最近7天</a>
		                        <a href="javascript:;"  name="d3" class="wql_tabbtn">最近15天</a>
		                        <a href="javascript:;"  name="d4" class="wql_tabbtn">最近30天</a>
		                    </div>
		                    <input type="button" onclick="getEndTime()" class="btn_blue fr mglr20" value="查询">
		                	<input type="text" class="Wdate inp mgl10 fr" name="end_time" placeholder="选择日期" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" style="">
		                </div>
		                <div  name="wql_tabcont">
		                	<div class="stats_canvas" id="myChart01" style="width:100%; height:450px"></div>
		                </div>
		            </div>
		    		
		    	</div>
    		</div>

    	</div>
	    	
		
    </div>
    <div class="bo_title mgt15">
        <h3 class="pdl10">有效课程数据统计</h3>
    </div>
	<div class="code_stateBox">
    	<div class="wql_g_tab mgt20 clearfix">
            <div class="wql_tabbox02 fl">
				<div class="wql_tabbtns clearfix" id="dateDiv">
                    <a href="javascript:;"  name="d1" class="wql_tabbtn on abb">按月度统计</a>
                    <a href="javascript:;"  name="d2" class="wql_tabbtn abb" >最近7天</a>
                    <a href="javascript:;"  name="d3" class="wql_tabbtn abb">最近15天</a>
                    <a href="javascript:;"  name="d4" class="wql_tabbtn abb">最近30天</a>
                </div>
            </div>
            <input type="button" onclick="getEndTime()" class="btn_blue fr mglr20" value="查询">
		    <input type="text" class="Wdate inp mgl10 fr" name="end_time" placeholder="选择日期" onclick="WdatePicker({dateFmt:'yyyyMMdd'})" style="">
        </div>
		<div class="stats_canvas" id="myChart02" style="width:100%; height:450px"></div>
    	
		
    </div>
    </form>
</div>


<!-- 表格 -->
<!--编辑-->
<div class="popup jumpBox edit dis_none txq_main" style="width:700px;">
<div class="code_table">
        <table class="small_space data_list" width="100%" style="border-collapse:collapse;">
            <thead>
	            <tr>
	                <th></th>
	                <th>新增用户</th>
	                <th>活跃用户</th>
	                <th>累计用户</th>
	                <th>授课统计</th>
	                <th>累计授课次数</th>
	                <th>有效用户数</th>
	                <th>累计有效用户</th>
	                <th>有效课程数</th>
	                <th>累计有效课程</th>
	            </tr>
	        </thead>
	        <tbody>
	        	<tr>
	        		<td>昨日</td>
	        		<td>${currentBasicIndexes.ADD_LOGIN_NEW_NUM }</td>
	        		<td>${currentBasicIndexes.ADD_LOGIN_USER_NUM }</td>
	        		<td>${currentBasicIndexes.ALL_LOGIN_USER_NUM }</td>
	        		<td>${currentBasicIndexes.ADD_LOGIN_NUM }</td>
	        		<td>${currentBasicIndexes.ALL_LOGIN_NUM }</td>
	        		<td>${currentBasicIndexes.ADD_LOGIN_VALID_NUM }</td>
	        		<td>${currentBasicIndexes.ALL_LOGIN_VALID_NUM }</td>
	        		<td>${currentBasicIndexes.ADD_LOGIN_COUR_NUM }</td>
	        		<td>${currentBasicIndexes.ALL_LOGIN_COUR_NUM }</td>
	        	</tr>
	        	<tr>
	        		<td>前日</td>
	        		<td>${lastBasicIndexes.ADD_LOGIN_NEW_NUM }</td>
	        		<td>${lastBasicIndexes.ADD_LOGIN_USER_NUM }</td>
	        		<td>${lastBasicIndexes.ALL_LOGIN_USER_NUM }</td>
	        		<td>${lastBasicIndexes.ADD_LOGIN_NUM }</td>
	        		<td>${lastBasicIndexes.ALL_LOGIN_NUM }</td>
	        		<td>${lastBasicIndexes.ADD_LOGIN_VALID_NUM }</td>
	        		<td>${lastBasicIndexes.ALL_LOGIN_VALID_NUM }</td>
	        		<td>${lastBasicIndexes.ADD_LOGIN_COUR_NUM }</td>
	        		<td>${lastBasicIndexes.ALL_LOGIN_COUR_NUM }</td>
	        	</tr>
	        </tbody>
        </table>
    </div>
</div>


<script src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.artDialog.js"></script> 
<script type="text/javascript" src="js/fun.js"></script> 
<script type="text/javascript" src="js/jquery.artDialog.plugins.js"></script> 
<script type="text/javascript" src="js/echarts.common.min.js"></script> 
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript">

var basicId = "b1";
var dateId = "d1";
var endTime = new Date();
var yname="活跃学校";
function queryArea(levelId,parentId){
	var htmlStr = '<option value="">全部</option>';
	$.ajax({
		type : "POST",
		url : "${ctx}/baseProperty/queryArea",
		data : {"levelId" : levelId,"parentId" : parentId},
		async : false,
		dataType : 'json',
		success : function(data) {
			if(data != null && data.list != null && data.list.length > 0){
				for(var i=0;i<data.list.length;i++){
					var obj = data.list[i];
					console.log(obj.areaName)
					htmlStr += '<option value="'+obj.areaCode+'">'+obj.areaName+'</option>';
				}
			}
		}
	});
	console.log(htmlStr)
	return htmlStr;
}

$("#province").bind("change", function(){
	$('#area').html('<option value="">全部</option>');
	if($("#province").val()==''){
		$('#city').html('<option value="">全部</option>');
		return;	
	}
	console.log($("#province").val())
	$('#city').html(queryArea(2,$("#province").val()));
});


$(function(){
	$(".qjf_seleautodiv").SeleautoBox();//新版的下拉使用方法
	showTab();
	showChart(basicId,dateId,endTime)
	showChart2(dateId,endTime)
	myChart('myChart02',option2);
})

function showTab(){
	var obj = $('.wql_g_tab .wql_tabbox01');
	var w = obj.width();
	
	var item = obj.children('.wql_tabbtns').children();
	item.css('width',w/item.length-1)
	console.log(w)
	console.log(item.length)
}
function showChart(basicId,dateId,endTime){
	var PROV_CODE = $("#province").val();
	var CITY_CODE ="${CITY_CODE}";
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/countHome/getChart",
		data : {"basicId" : basicId,"dateId" : dateId,"endTime":endTime,"PROV_CODE":PROV_CODE,"CITY_CODE":CITY_CODE},
		async : false,
		dataType : 'json',
		success : function(data) {
			var school = new Array();
			var time = new Array();
			if(data != null && data.length > 0){
				for(var i=0;i<data.length;i++){
					if(dateId=="d1"){//表示月结果
						if(basicId=="b1"){
							school.push(data[i].MON_LOGIN_ORGA_NUM);
							time.push(data[i].createTime);
						}
						if(basicId==("b2")){
							school.push(data[i].MON_LOGIN_USER_NUM);
							time.push(data[i].createTime);
						}
						if(basicId==("b3")){
							school.push(data[i].MON_LOGIN_NUM);
							time.push(data[i].createTime);
						}
						if(basicId==("b4")){
							school.push(data[i].MON_LOGIN_NEW_NUM);
							time.push(data[i].createTime);
						} 
						if(basicId=="b5"){
							school.push(data[i].MON_LOGIN_VALID_NUM);
							time.push(data[i].createTime);
						}
						if(basicId==("b6")){
							school.push(data[i].MON_LOGIN_COUR_NUM);
							time.push(data[i].createTime);
						}
						if(basicId==("b7")){
							school.push(data[i].MON_TERMINAL_DZSB_NUM);
							time.push(data[i].createTime);
						}
						if(basicId==("b8")){
							school.push(data[i].MON_TERMINAL_YDJT_NUM);
							time.push(data[i].createTime);
						}
						if(basicId=="b9"){
							school.push(data[i].MON_TERMINAL_ZZHB_NUM);
							time.push(data[i].createTime);
						}
						if(basicId==("b10")){
							school.push(data[i].MON_TERMINAL_DTQ_NUM);
							time.push(data[i].createTime);
						}
					}else{
						if(basicId=="b1"){
							school.push(data[i].ADD_LOGIN_ORGA_NUM);
							time.push(data[i].createTime);
						}
						if(basicId==("b2")){
							school.push(data[i].ADD_LOGIN_USER_NUM);
							time.push(data[i].createTime);
						}
						if(basicId==("b3")){
							school.push(data[i].ADD_LOGIN_NUM);
							time.push(data[i].createTime);
						}
						if(basicId==("b4")){
							school.push(data[i].ADD_LOGIN_NEW_NUM);
							time.push(data[i].createTime);
						} 
						if(basicId=="b5"){
							school.push(data[i].ADD_LOGIN_VALID_NUM);
							time.push(data[i].createTime);
						}
						if(basicId==("b6")){
							school.push(data[i].ADD_LOGIN_COUR_NUM);
							time.push(data[i].createTime);
						}
						if(basicId==("b7")){
							school.push(data[i].ADD_TERMINAL_DZSB_NUM);
							time.push(data[i].createTime);
						}
						if(basicId==("b8")){
							school.push(data[i].ADD_TERMINAL_YDJT_NUM);
							time.push(data[i].createTime);
						}
						if(basicId=="b9"){
							school.push(data[i].ADD_TERMINAL_ZZHB_NUM);
							time.push(data[i].createTime);
						}
						if(basicId==("b10")){
							school.push(data[i].ADD_TERMINAL_DTQ_NUM);
							time.push(data[i].createTime);
						}
					}
					
				}
				
			}
			
			var option1 = {
				    
				    tooltip : {
				        trigger: 'axis'
				    },
				    
				    toolbox: {
				        show : true,
				        feature : {
				            magicType : {show: true, type: ['line', 'bar']},
				            restore : {show: true},
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
				            data : time
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value'
				        }
				    ],
				    series : [
				        {
				            name:yname,
				            type:'bar',
				            data: school,
				            markPoint : {
				                data : [
				                    {type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'}
				                ]
				            },
				           
				        },
				        
				    ]
				};
			myChart('myChart01',option1);
		}
	});
}

function showChart2(dateId,endTime){
	var PROV_CODE = $("#province").val();
	var CITY_CODE ="${CITY_CODE}";
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/countHome/getChart2",
		data : {"dateId" : dateId,"endTime":endTime,"PROV_CODE":PROV_CODE,"CITY_CODE":CITY_CODE},
		async : false,
		dataType : 'json',
		success : function(data) {
			var school = new Array();
			var school2 = new Array();
			var school3 = new Array();
			var school4 = new Array();
			var school5 = new Array();
			var school6 = new Array();
			var time = new Array();
			if(data != null && data.length > 0){
				for(var i=0;i<data.length;i++){
					if(dateId=="d1"){//表示月结果
						school.push(data[i].MON_JXZS_VALIDBS_NUM);
						school2.push(data[i].MON_JXZS_VALIDKTSL_NUM);
						school3.push(data[i].MON_TERMINAL_VALIDDZSB_NUM);
						school4.push(data[i].MON_TERMINAL_VALIDYDJT_NUM);
						school5.push(data[i].MON_TERMINAL_VALIDZZHB_NUM);
						school6.push(data[i].MON_TERMINAL_VALIDDTQ_NUM);
						time.push(data[i].createTime);
					}else{
						school.push(data[i].ADD_JXZS_VALIDBS_NUM);
						school2.push(data[i].ADD_JXZS_VALIDKTSL_NUM);
						school3.push(data[i].ADD_TERMINAL_VALIDDZSB_NUM);
						school4.push(data[i].ADD_TERMINAL_VALIDYDJT_NUM);
						school5.push(data[i].ADD_TERMINAL_VALIDZZHB_NUM);
						school6.push(data[i].ADD_TERMINAL_VALIDDTQ_NUM);
						time.push(data[i].createTime);
					}
					
				}
			}
			
			var option2 = {
				    color:['#CACFE3', '#B5D4CE', '#DFDBBE', '#C3AD9F'],
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
				        data: ['板书数', '实录数', '移动讲台授课', '电子书包授课', '掌中黑板授课', '答题器授课'],
				        top: 20,
				        left: 'center',
				    },
				    grid:{
				        left:100,
				        right:100,
				        top:80,
				        bottom:30
				    },
				    xAxis: {
				        type: 'category',
				        boundaryGap: false,
				        data: time
				    },
				    yAxis: {
				        type: 'value'
				    },
				    series: [
				        {
				            name:"板书数",
				            data: school,
				            type: 'line',
				            smooth: true,
				            symbolSize:10,
				        },
				        {
				            name:"实录数",
				            data: school2,
				            type: 'line',
				            smooth: true,
				            symbolSize:10,
				        },
				        {
				            name:"移动讲台授课",
				            data: school3,
				            type: 'line',
				            smooth: true,
				            symbolSize:10,
				        },
				        {
				            name:"电子书包授课",
				            data: school4,
				            type: 'line',
				            smooth: true,
				            symbolSize:10,
				        },
				        {
				            name:"掌中黑板授课",
				            data: school5,
				            type: 'line',
				            smooth: true,
				            symbolSize:10,
				        },
				        {
				            name:"答题器授课",
				            data: school6,
				            type: 'line',
				            smooth: true,
				            symbolSize:10,
				        },
				    ]
				};
			myChart('myChart02',option2);
		}
	});
}

function getEndTime(){
	endTime = $('input[name="end_time"]').val();
	showChart(basicId,dateId,endTime)
}

$('#dateDIv2').find(".wql_tabbtn").click(function(){
	var nameId = $(this).attr("name");
	$(this).addClass('on').siblings().removeClass('on')
	if(nameId.indexOf("d") != -1){
		dateId = nameId;
	}
	if(nameId.indexOf("b") != -1){
		basicId = nameId;
		yname=$(this).text();
	}
	showChart(basicId,dateId,endTime)
	
})
$('#dateDiv').find(".abb").click(function(){
	var nameId = $(this).attr("name");
	$(this).addClass('on').siblings().removeClass('on')
	if(nameId.indexOf("d") != -1){
		dateId = nameId;
	}
	showChart2(dateId,endTime)
	
})

function myChart(id,opt,w,h){
    var obj = document.getElementById(id);
    var w = w||false;
    var h = h||false;
    if(w) {
        $(obj).css({'width':w});   
    } 
    if(h) {
        $(obj).css({'height':h});   
    } 
    var myChart = echarts.init(obj);
    myChart.setOption(opt);
    myChart.on('click', function(param) {
    	alert(param.date)
    	$(".jumpBox").jumpBox(true);
    	});
}



</script>
</body>
</html>