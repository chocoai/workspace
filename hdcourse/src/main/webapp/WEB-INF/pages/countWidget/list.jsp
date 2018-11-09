<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>控件使用统计</title>

</head>

<body>
<div class="bo_main">
   <form id="pageForm"  name="pageForm" action="${ctx}/manage/countWidget/widget" method="post">
	<h2 class="adminTit bor_bottom"><em>使用次数统计</em><span class="fr"></span></h2>
    <div class="data_index mgt15">
          <table class="mod_table t_c mgauto" width="60%" style="border-collapse:collapse;">
            <tr>
                <td>
                	<p>区域统计</p>
                    <p class="num"><span class="cf00">${areaCount }</span>个</p>
                </td>
                <td>
                	<p>学校统计</p>
                    <p class="num"><span class="c00f">${orgCount }</span>个</p>
                </td>
                <td>
                	<p>老师统计</p>
                    <p class="num"><span class="c3cc">${userCount }</span>个</p>
                </td>
            </tr>
        </table>
    </div>
    
    <div class="mgt25 search">
        <span>所属区域：</span>
        <select id="province" name="provinceCode" class="sel mgr10" style="width:150px;">
			<option value="" <c:if test="${empty provinceCode}">selected="selected"</c:if>>全部</option>
			<c:if test="${not empty provinceList}">
			<c:forEach items="${provinceList}" var="a" varStatus="st">
			<option value="${a.areaCode }" <c:if test="${a.areaCode==provinceCode}">selected="selected"</c:if>>${a.areaName }</option>
			</c:forEach>
			</c:if>
		</select>  
		<select id="city" name="cityCode" class="sel mgr10" style="width:150px;">
			<option value="" <c:if test="${empty cityCode}">selected="selected"</c:if>>全部</option>
			<c:if test="${not empty cityList}">
			<c:forEach items="${cityList}" var="a" varStatus="st">
			<option value="${a.areaCode }" <c:if test="${a.areaCode==cityCode}">selected="selected"</c:if>>${a.areaName }</option>
			</c:forEach>
			</c:if>
		</select>
		
		<select id="org" name="org" class="sel mgr10" style="width:150px;">
			<option value="" <c:if test="${empty orgId}">selected="selected"</c:if>>全部</option>
			<c:if test="${not empty orgList}">
			<c:forEach items="${orgList}" var="a" varStatus="st">
			<option value="${a.orgId }" <c:if test="${a.orgId==orgId}">selected="selected"</c:if>>${a.orgName}</option>
			</c:forEach>
			</c:if>
		</select>
		
      	  日期
		<input type="text" name="start_time" value="${start_time}" readonly="readonly" class="inp focus" style="width: 100px;"/>
        <input type="text" id="d4311" class="dis_none"/>
        <img onclick="WdatePicker({el:'d4311',onpicked:startTimePickedFunc,oncleared:clearStartTime,dateFmt:'yyyy-MM',maxDate:'\'2020-12\'}'})" src="${ctx}/js/My97DatePicker/skin/datePicker.gif" width="20" height="26"/>
        <input type="button" onclick="search()" class="btn_blue" value="查询"/>
    </div>
    
<!-- 当月数据 -->
   <div class="bo_title mgt15">
        <h3><i class="ico ico1"></i>当月数据指标</h3>
   </div>
   <div class="code_table data_index">
        <table class="mod_table t_c" width="100%" style="border-collapse:collapse;">
            <tr>
                <td width="10%">
                	<p>区域</p>
                    <p class="num"><span class="cf00">${currentAreaCount}个</span></p>
                </td>
                <td width="10%">
                	<p>使用次数</p>
                    <p class="num"><span class="c00f">${useCountToalCount}</span></p>
                </td>
                <td  width="10%">
                	<p>学校</p>
                	<p class="num"><span class="c000">${orgTotalCount}个</span></p>
                </td>
            </tr>
        </table>
    </div>
    
    <div class="bo_title mgt10">
        <h3 class="mgr25"><i class="ico ico2"></i>当月功能使用次数 TOP10</h3>
    </div>
    <div class="code_stateBox">
        <div class="stats_canvas" id="canvas" style="width:100%; height:450px"></div>
    </div>
  	<div class="bo_title mgt25">
        <h3><i class="ico ico3"></i>数据报表</h3>
        <div class="sort fr">
            <input type="button" onclick="exportExcel()" class="btn_blue" value="导出Excel"/>
        </div>
    </div>
    <div class="code_table">
        <table class="small_space data_list" width="100%" style="border-collapse:collapse;">
            <tr>
                <th width="20%">控件名称</th>
                <th width="20%">使用次数</th>
                <th width="20%">上月使用次数</th>
                <th width="20%">增长率</th>
                 <th width="20%">描述</th>
            </tr>
            
            <c:forEach items="${currentMonthWidgetHistoryList}" var="widget" varStatus="status">
			    <tr>
			    	<td>${widget.widgetName}</td>
					<td>${widget.useCount}</td>
					<td>${widget.previousUseCount}</td>
					<td>
					
					<c:choose>
					   <c:when test="${fn:contains(widget.rate, '-')}"> 
					        <span style="color: red">${widget.rate}</span>
					   </c:when>
					   <c:otherwise>
					  		<span style="color: #3CB371">${widget.rate}</span>
					   </c:otherwise>
					</c:choose>
					</td>
					<td>${widget.description}</td>
			    </tr>
		    </c:forEach>
        </table>
    </div>
	</form>
</div>

<script>
function startTimePickedFunc(){
	//$("#start_time").val($('#d4311').val());
	$(".search input[name='start_time']").val($('#d4311').val());
}

function clearStartTime(){
	//$("#start_time")val('');
	$(".search input[name='start_time']").val('');
}
function exportExcel(){
	var province=$('#province').val();
	var city=$('#city').val();
	var org =$('#org').val();
	var startTime = $('d4311').val();
	var endTime = $('d4312').val();
	var start_time = $(".search input[name='start_time']").val();
	//window.location.href='${ctx}/sys/user/exportexcel?name='+encodeURI(encodeURI(name))+'&type='+encodeURI(encodeURI(type))+'&status='+encodeURI(encodeURI(status));
	window.location.href='${ctx}/manage/countWidget/exportExcel?province=' + province+'&city='+city+'&org='+org+'&startTime='+startTime+'&endTime='+endTime+'&start_time='+start_time;
}

function search(){
	//$("#search_type").val(0);
	$("#pageForm").submit();
}
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
					htmlStr += '<option value="'+obj.areaCode+'">'+obj.areaName+'</option>';
				}
			}
		}
	});
	return htmlStr;
}

function queryOrg(areaCode){
	var htmlStr='<option value="">全部</option>';
	var startTime = $('d4311').val();
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/countWidget/queryOrg",
		data : {"areaCode" : areaCode,"start_time":startTime},
		async : false,
		dataType : 'json',
		success : function(data) {
			if(data != null && data.list != null && data.list.length > 0){
				for(var i=0;i<data.list.length;i++){
					var obj = data.list[i];
					htmlStr += '<option value="'+obj.orgId+'">'+obj.orgName+'</option>';
				}
			}
		}
	});
	return htmlStr;
	
}

$(function(){
	$('.chklist2').hcheckbox2(); 
	$(".qjf_seleautodiv").SeleautoBox();//新版的下拉使用方法
	
	 //改变偶数行背景色
	changTd(".chklist2","#f6f7f6");
	
	//统计图
	myChart1('canvas');
	
	var province = '${provinceCode}';
	
	if(province==''){
		$('#province').html(queryArea(1));	
	}
	
	$("#province").bind("change", function(){
		$('#area').html('<option value="">全部</option>');
		if($("#province").val()==''){
			$('#city').html('<option value="">全部</option>');
			return;	
		}
		
		$('#city').html(queryArea(2,$("#province").val()));
	});
	
	$("#city").bind("change", function(){
		if($("#city").val()==''){
			$('#org').html('<option value="">全部</option>');
			return;		
		}
		$('#org').html(queryOrg($("#city").val()));
	}); 
})


//统计图
function myChart1(obj){
	var myChartA = echarts.init(document.getElementById(obj));
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
			name:'排名',
            type: 'category',
            axisTick:{show:false},
            axisLine:{lineStyle:{color:'#ccc'}},
			splitLine:{lineStyle:{color:'#ccc'}},
			data : (function(){
		        var arr=[];
		        $.ajax({
		        type : "post",
		        async : false, //同步执行
		        url : "${ctx}/manage/countWidget/chartsData",
		        data : {province:$("#province").val(),city:$("#city").val(),org:$("#org").val(),start_time:$(".search input[name='start_time']").val()},
		        dataType : "json", //返回数据形式为json
		        success : function(result) {
		        if (result) {
		               for(var i=0;i<result.length;i++){
		                  arr.push(result[i].name);
		                  console.log(result);
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
        		        url : "${ctx}/manage/countWidget/chartsData",
        		        data : {province:$("#province").val(),city:$("#city").val(),org:$("#org").val(),start_time:$(".search input[name='start_time']").val()},
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
