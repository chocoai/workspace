<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>区域统计</title>
</head>

<body>
<div class="bo_main">
<form id="pageForm"  name="pageForm" action="${ctx}/manage/countLogin/orgCount" method="post">
	<h2 class="adminTit bor_bottom"><em>登录次数统计</em><span class="fr"></span></h2>
    <div class="data_index mgt15">
        <table class="mod_table t_c mgauto" width="60%" style="border-collapse:collapse;">
            <tr>
                <td>
                	<a href="areaCount">
                	<p>区域统计</p>
                    <p class="num">总<span class="cf00">${areaCount }</span>个</p>
               		</a>
               	</td>
                <td><a class="on" href="javascript:void(0)">
                	<p>学校统计</p>
                    <p class="num">总<span class="c00f">${orgCount }</span>个</p>
                	</a>
                </td>
                <td><a href="userCount">
                	<p>老师统计</p>
                    <p class="num">总<span class="c3cc">${userCount }</span>个</p>
                	</a>
                </td>
            </tr>
        </table>
    </div>
   
    
    <div class="mgt25 search">
        <select id="province" name="provinceCode" class="sel mgr10" style="width:150px;">
			<option value="" <c:if test="${empty provinceCode}">selected="selected"</c:if>>全部</option>
			<c:if test="${not empty provinceList}">
			<c:forEach items="${provinceList}" var="a" varStatus="st">
			<option value="${a.areaCode }" <c:if test="${a.areaCode==provinceCode}">selected="selected"</c:if>>${a.areaName }</option>
			</c:forEach>
			</c:if>
		</select>  

		<select id="city2" name="cityCode" class="sel mgr10" style="width:150px;">
			<option value="" <c:if test="${empty cityCode}">selected="selected"</c:if>>全部</option>
			
			<c:forEach items="${cityList}" var="a" varStatus="st">
			<option value="${a.areaCode }" <c:if test="${a.areaCode==cityCode}">selected="selected"</c:if>>${a.areaName }</option>
			</c:forEach>
			
		</select>
		
		
		
		
		学校
		<input type="text" name="orgName" id="orgName" value="${orgName}" class="sel mgr10" style="width:150px;">
		
		日期
		<input type="text" name="start_time" value="${start_time}" readonly="readonly" class="inp focus" style="width: 100px;"/>
        <input type="text" id="d4311" class="dis_none"/>
        <img onclick="WdatePicker({el:'d4311',onpicked:startTimePickedFunc,oncleared:clearStartTime,dateFmt:'yyyy-MM',maxDate:'\'2020-12\'}'})" src="${ctx}/js/My97DatePicker/skin/datePicker.gif" width="20" height="26"/>
        <input type="button" onclick="search()" class="btn_blue" value="查询"/>
    </div>
    
   <div class="bo_title mgt15">
        <h3><i class="ico ico1"></i>当月数据指标</h3>
   </div>
   <div class="code_table data_index">
        <table class="mod_table t_c" width="100%" style="border-collapse:collapse;">
            <tr>
                <td width="10%">
                	<p>学校数量</p>
                    <p class="num"><span class="cf00">${orgNum}个</span></p>
                </td>
                <td width="10%">
                	<p>总次数</p>
                    <p class="num"><span class="c00f">${orgUseTotal}次</span></p>
                </td>
            </tr>
        </table>
    </div>
    
    
    <div class="bo_title mgt10">
        <h3 class="mgr25"><i class="ico ico2"></i>当月数据趋势 TOP10</h3>
    </div>
    <div class="code_stateBox">
        <div class="stats_canvas" id="canvas" style="width:100%; height:450px"></div>
    </div>
  	<div class="bo_title mgt25">
        <h3><i class="ico ico3"></i>数据报表</h3>
        <div class="sort fr">
            <input type="button" onclick="exportOrgCountExcel()" class="btn_blue" value="导出Excel"/>
        </div>
    </div>
    <div class="code_table">
        <table class="small_space data_list" width="100%" style="border-collapse:collapse;">
            <tr>
                <th width="7%">区域名称</th>
                <th width="19%">学校名称</th>
                <th width="8%">日期</th>
                <th width="8%">使用次数</th>
                <th width="11%">上月使用次数</th>
                <th width="8%">增长率</th>
                <th width="5%">老师数量</th>
                <th width="5%">操作</th>
            </tr>
           
           <c:forEach items="${orgCountList}" var="orgCount" varStatus="status">
             	<tr>
             		<td>${orgCount.areaName}</td>
             		<td>${orgCount.orgName}</td>
             		<td>${time}</td>
             		<td>${orgCount.useCount}</td>
             		<td>${orgCount.previousUseCount}</td>
             		<td>${orgCount.rate}</td>
             		<td>${orgCount.userRanking}</td>
             		<td><a href="javascript:void(0)" onclick="look('${orgCount.orgId}','${orgCount.areaCode}')">查看</a></td>
             	</tr>
           </c:forEach>
        </table>
    </div>
    <div class="base_page clearfix">
		<!-- 分页 -->
		<c:if test="${fn:length(orgCountList) > 0}">
				<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
				currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
				action="${ctx}/manage/countLogin/orgCount"></newTag:page>
		</c:if>
	</div>
	</form>
</div>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/fun.js"></script>
<script type="text/javascript" src="js/global.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>
<script>

function look(orgId,areaCode){
	console.log(orgId+areaCode);
	
	var start_time = $(".search input[name='start_time']").val();
	window.location.href="userCount?org="+orgId+"&cityCode="+areaCode+"&start_time="+start_time;
}

function startTimePickedFunc(){
	//$("#start_time").val($('#d4311').val());
	$(".search input[name='start_time']").val($('#d4311').val());
}

function clearStartTime(){
	//$("#start_time")val('');
	$(".search input[name='start_time']").val('');
}

function search(){
	//$("#search_type").val(0);
	$("#pageForm").submit();
}
function exportOrgCountExcel(){
	var province=$('#province').val();
	var city=$("#city2").val();
	var start_time = $(".search input[name='start_time']").val();
	var orgName = $("#orgName").val();
	//window.location.href='${ctx}/sys/user/exportexcel?name='+encodeURI(encodeURI(name))+'&type='+encodeURI(encodeURI(type))+'&status='+encodeURI(encodeURI(status));
	window.location.href='${ctx}/manage/countLogin/exportOrgCountExcel?province=' + province+'&cityCode='+city+'&start_time='+start_time+'&orgName='+orgName;
}

function queryArea(levelId,parentId){
	var cityCode = '${cityCode}';
		
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
						htmlStr += '<option value="'+obj.areaCode+'">'+obj.areaName+'</option>';
					}
				}
			}
		});
	
	
	
	return htmlStr;
}
$("#province").bind("change", function(){
	$('#area').html('<option value="">全部</option>');
	if(province==''){
		console.log("22");
		$('#city2').html('<option value="">全部</option>');
		return;	
	}
	
	
	console.log("12");
	$('#city2').html(queryArea(2,$("#province").val()));
});

//$("#city").bind("change", function(){
	//if($("#city").val()==''){
	//	$('#area').html('<option value="">全部</option>');
	//	return;		
	//}
	//console.log("31");
	//$('#area').html(queryArea(3,$("#city").val()));
//}); 
$(function(){
	//$('.chklist2').hcheckbox2(); 
	//$(".qjf_seleautodiv").SeleautoBox();//新版的下拉使用方法
	
	 //改变偶数行背景色
	//changTd(".chklist2","#f6f7f6");
	
	//统计图
	myChart1('canvas');

	var province = '${provinceCode}';
	
	if(province==''){
		console.log("41");
		$('#province').html(queryArea(1));	
	}
	
	
	
})
//统计图
function myChart1(obj){
	var myChartA = echarts.init(document.getElementById(obj));
	//var Ohref=window.location.href;
	//var arrhref=Ohref.split("?cityCode=");
	//var cityCode = arrhref[1];
	//$("#city2").val(cityCode);
	var optionA = { 
		 tooltip: {
            trigger: 'axis',
            formatter:'{b} {c}',
            axisPointer: {
                type: 'shadow'
            }
        },
        grid: {
            top:'12%',
            left: '5%',
            right: '10%',
            bottom: '8%',
            containLabel: true
        },
        xAxis: {
			name:'使用次数（/次）',
            type: 'value',
            axisTick:{show:false},
            axisLine:{lineStyle:{color:'#ccc'}},
        },
        yAxis: {
			name:'排名学校',
            type: 'category',
            axisTick:{show:false},
            axisLine:{lineStyle:{color:'#ccc'}},
			splitLine:{lineStyle:{color:'#ccc'}},
            data: (function(){
		        var arr=[];
		        $.ajax({
		        type : "post",
		        async : false, //同步执行
		        url : "${ctx}/manage/countLogin/getOrgCountChartsData",
		        data : {orgName:$("#orgName").val(),province:$("#province").val(),city2:$("#city2").val(),start_time:$(".search input[name='start_time']").val()},
		        dataType : "json", //返回数据形式为json
		        success : function(result) {
		        if (result) {
		               for(var i=0;i<result.length;i++){
		                  arr.push(result[i].name);
		                }
		        }
		    },
		    error : function(errorMsg) {
		        alert("不好意思，大爷，图表请求数据失败啦!");
		        myChart.hideLoading();
		    }
		   })
		   return arr;
		})()
        },
        series: [
            {
                name: '',
                type: 'bar',
				barWidth : 20,//柱图宽度
                label:{normal:{ show: true,position: 'right',textStyle:{color:'#fff',fontSize:14}}},
                data: (function(){
    		        var arr=[];
    		        $.ajax({
    		        type : "post",
    		        async : false, //同步执行
    		        url : "${ctx}/manage/countLogin/getOrgCountChartsData",
    		        data : {orgName:$("#orgName").val(),province:$("#province").val(),city2:$("#city2").val(),start_time:$(".search input[name='start_time']").val()},
    		        dataType : "json", //返回数据形式为json
    		        success : function(result) {
    		        if (result) {
    		               for(var i=0;i<result.length;i++){
    		                  arr.push(result[i].value);
    		                }
    		        }
    		    },
    		    error : function(errorMsg) {
    		        alert("不好意思，大爷，图表请求数据失败啦!");
    		        myChart.hideLoading();
    		    }
    		   })
    		   return arr;
    		})(),
				itemStyle: {
					normal: {
						color: function(params) {
							var colorList = ['#c1232b','#b5c334','#fcce10','#e87c25','#27727b','#fe8463','#9bca63','#fad860','#f3a43b','#60c0dd','#d7504b'];
							return colorList[params.dataIndex]
						}
					}
				}
            }
        ]
    }
	myChartA.setOption(optionA);
}
</script>
</body>
</html>
