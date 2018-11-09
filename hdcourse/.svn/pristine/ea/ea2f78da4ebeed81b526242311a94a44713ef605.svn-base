<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>终端使用统计</title>

</head>
<body>
<div class="bo_main">
	<h2 class="adminTit bor_bottom"><em>终端使用统计</em><span class="fr"></span></h2>
    <div class="data_index mgt15">
        
    </div>
    <form id="pageForm"  name="pageForm" action="${ctx}/manage/countTerminalUse/list" method="post">
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
    <div class="bo_title mgt15">
        <h3><i class="ico ico1"></i>数据指标（次/月）</h3>
    </div>
    <div class="code_table data_index">
        <table class="mod_table t_c" width="100%" style="border-collapse:collapse;"> 
            <tr>
                <td>
                	<p>电子书包</p>
                    <p class="num"><span class="c00f">${dzsbCount}</span></p>
                    <p class="day"><span>AVG：<i>${dzsbAvgCount}</i>次/天</span><em>MAX：<i>${dzsbMaxCount}</i>次/天</em></p>
                </td>
                <td>
                	<p>掌中黑板</p>
                    <p class="num"><span class="c3cc">${zzhbCount}</span></p>
                    <p class="day"><span>AVG：<i>${zzhbAvgCount}</i>次/天</span><em>MAX：<i>${zzhbMaxCount}</i>次/天</em></p>
                </td>
                <td>
                	<p>学生数码笔</p>
                    <p class="num"><span class="cf00">${smbCount}</span></p>
                    <p class="day"><span>AVG：<i>${smbAvgCount}</i>次/天</span><em>MAX：<i>${smbMaxCount}</i>次/天</em></p>
                </td>
                <td>
                	<p>答题器</p>
                    <p class="num"><span class="c96f">${dtqCount}</span></p>
                    <p class="day"><span>AVG：<i>${dtqAvgCount}</i>次/天</span><em>MAX：<i>${dtqMaxCount}</i>次/天</em></p>
                </td>
            </tr>
        </table>
    </div>
    <div class="bo_title mgt10">
        <h3 class="mgr25"><i class="ico ico2"></i>数据趋势</h3>
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
                <th>电子书包（次/天）</th>
                <th>掌中黑板（次/天）</th>
                <th>学生数码笔（次/天）</th>
                <th>答题器（次/天）</th>
                <th>时间</th>
            </tr>
             <c:forEach items="${terminalUseHistoryList}" var="bean" varStatus="status">
			    <tr>
			    	<td>${bean.dzsbLinkNum}</td>
					<td>${bean.zzhbLinkNum}</td>
					<td>${bean.smbLinkNum}</td>
					<td>${bean.dtqLinkNum}</td>
					<td>${bean.countCreateTime}</td>
			    </tr>
		    </c:forEach>
        </table>
    </div>
    <div class="base_page clearfix">
		<!-- 分页 -->
		<c:if test="${fn:length(terminalUseHistoryList) > 0}">
				<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
				currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
				action="${ctx}/manage/countTerminalUse/list"></newTag:page>
		</c:if>
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
	
	var start_time = $(".search input[name='start_time']").val();
	
	//window.location.href='${ctx}/sys/user/exportexcel?name='+encodeURI(encodeURI(name))+'&type='+encodeURI(encodeURI(type))+'&status='+encodeURI(encodeURI(status));
	window.location.href='${ctx}/manage/countTerminalUse/exportExcel?province=' + province+'&city='+city+'&org='+org+'&start_time='+start_time;
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
	
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/countLogin/queryOrg",
		data : {"areaCode" : areaCode},
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
		color:['#63CDE5','#efd450','#6DC7BE','#FF8761'],
		tooltip: {
        trigger: 'axis'
		},
		grid: {
			left: '5%',
			right: '8%',
			bottom: '8%',
			top: '15%',
			containLabel: true
		},
		legend: {
        	top: '3%',
			data:['电子书包','掌中黑板','学生数码笔','答题器']
		},
		
		xAxis : [
			{
				name:'日期',
				type : 'category',
				axisTick: {alignWithLabel: true},
				axisLine:{lineStyle:{color:'#ccc'}},
				
				data:(function(){
		            var arr=[];
	                $.ajax({
	                type : "post",
	                async : false, //同步执行
	                url : "${ctx}/manage/countTerminalUse/getChartsData",
	                data : {province:$("#province").val(),city:$("#city").val(),org:$("#org").val(),start_time:$(".search input[name='start_time']").val()},
	                dataType : "json", //返回数据形式为json
	                success : function(result) {
	                if (result) {
	                	//console.log(result)
	                	//arr=result;
	                	//arr.push(result);
	                      for(var i=0;i<result.length;i++){
	                    	  //console.log(1);
	                    	  //console.log(result[i].date);
	                    	  console.log(result[i])
	                          arr.push(result[i]);
	                        }    
	                }
	                
	            },
	            error : function(errorMsg) {
	                alert("不好意思，大爷，图表请求数据失败啦!");
	                myChartA.hideLoading();
	            }
	           })
	           return arr;
	        })() 
			}
		],
		yAxis : [
				{
				name:'使用次数（/次）',
				type: 'value',
				axisLine:{lineStyle:{color:'#ccc'}},
				splitLine:{lineStyle:{color:'#ccc'}},
				axisLabel: {formatter: '{value}'}
			},
		],
		series : (function(){
	        var arr=[];
	        $.ajax({
	        type : "post",
	        async : false, //同步执行
	        url : "${ctx}/manage/countTerminalUse/chartsData",
	        data : {province:$("#province").val(),city:$("#city").val(),org:$("#org").val(),start_time:$(".search input[name='start_time']").val()},
	        dataType : "json", //返回数据形式为json
	        success : function(result) {
	        if (result) {
	               for(var i=0;i<result.length;i++){
	                  var item={name:result[i].name,
	                		    type:result[i].type,
	                		    data:result[i].data
	                  }
	                  arr.push(result[i]);
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
	
    }
	myChartA.setOption(optionA);
}
</script>
</body>
</html>