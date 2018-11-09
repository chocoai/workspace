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
	<form id="pageForm"  name="pageForm" action="${ctx}/manage/countUseTaking/areaCount" method="post">
	<h2 class="adminTit bor_bottom"><em>使用时长统计</em><span class="fr"></span></h2>
    <div class="data_index mgt15">
        <table class="mod_table t_c mgauto" width="60%" style="border-collapse:collapse;">
            <tr>
                <td><a class="on" href="javascript:void(0)">
                	<p>区域统计</p>
                    <p class="num"><span class="cf00">${areaCount }</span>个</p>
                </a></td>
                <td><a href="orgCount">
                	<p>学校统计</p>
                    <p class="num"><span class="c00f">${orgCount }</span>个</p>
                </a></td>
                <td><a href="userCount">
                	<p>老师统计</p>
                    <p class="num"><span class="c3cc">${userCount }</span>个</p>
                </a></td>
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
                	<p>总时长</p>
                    <p class="num"><span class="c00f">${useTakingToalCount}</span></p>
                </td>
                <td  width="10%">
                	<p>学校</p>
                	<p class="num"><span class="c000">${orgTotalCount}个</span></p>
                </td>
            </tr>
        </table>
    </div>
    
    <!-- 查询条件 -->
    <div class="bo_title mgt10">
        <h3 class="mgr25"><i class="ico ico2"></i>地域分布</h3>
    </div>
    <div class="code_stateBox">
        <div class="stats_canvas" id="canvas" style="width:100%; height:450px"></div>
    </div>
  	<div class="bo_title mgt25">
        <h3><i class="ico ico3"></i>数据报表</h3>
        <div class="sort fr">
           	<input type="button" onclick="exportAreaCountExcel()" class="btn_blue" value="导出Excel"/>
        </div>
    </div>
    <div class="code_table">
        <table class="small_space data_list" width="100%" style="border-collapse:collapse;">
            <tr>
                <th width="10%">区域名称</th>
                <th width="10%">在线时长</th>
                <th width="10%">上月在线时长</th>
                <th width="10%">日期</th>
                <th width="8%">增长率</th>
                <th width="10%">学校数量</th>
                <th width="5%">操作</th>
            </tr>
            
            <c:forEach items="${areaCountList}" var="areaCount" varStatus="status">
             	<tr>
             		<td>${areaCount.areaName}</td>
             		<td>${areaCount.useTakingStr}</td>
             		<td>${areaCount.previousUseTakingStr}</td>
             		<td>${time}</td>
             		<td>${areaCount.rate}</td>
             		<td>${areaCount.orgNum}</td>
             		<td><a href="javascript:void(0)" onclick="look('${areaCount.areaCode}')">查看</a></td>
             	</tr>
             </c:forEach>
        </table>
    </div>
    
    <div class="base_page clearfix">
		<c:if test="${fn:length(areaCountList) > 0}">
				<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
				currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
				action="${ctx}/manage/countUseTaking/areaCount"></newTag:page>
		</c:if>
	</div>
	</form>
</div>

<script>

function look(areaCode){
	console.log(areaCode);
	
	var start_time = $(".search input[name='start_time']").val();
	window.location.href="orgCount?cityCode="+areaCode+"&start_time="+start_time;
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
	$("#pageForm").submit();
}

function exportAreaCountExcel(){
	var province=$('#province').val();
	var start_time = $(".search input[name='start_time']").val();
	window.location.href='${ctx}/manage/countUseTaking/exportAreaActivityCountExcel?province=' + province+'&start_time='+start_time;
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

$(function(){
	$('.chklist2').hcheckbox2(); 
	$(".qjf_seleautodiv").SeleautoBox();//新版的下拉使用方法
	
	 //改变偶数行背景色
	changTd(".chklist2","#f6f7f6");
	
	//统计图
	myChart1('canvas');
	
	//myChart1('canvas');
	$("#province").bind("change", function(){
		$('#area').html('<option value="">全部</option>');
		if($("#province").val()==''){
			$('#city').html('<option value="">全部</option>');
			return;	
		}
		
		$('#city').html(queryArea(2,$("#province").val()));
	});
	
})
function date(mss){
	var hours = parseInt(mss/ ( 60 * 60));
	var minutes = parseInt((mss % ( 60 * 60)) / (60));
	var seconds = parseInt((mss % ( 60)) );
	var str =  hours + " 小时 " + minutes + " 分钟 " + seconds + " 秒 ";
	return str;
}

//统计图
function myChart1(obj){
	var myChartA = echarts.init(document.getElementById(obj));
	var optionA = { 
		 legend: {
			y:'5%',
			data:(function(){
	            var arr=[];
                $.ajax({
                type : "post",
                async : false, //同步执行
                url : "${ctx}/manage/countUseTaking/getAreaUseTakingChartsData",
                data :{province:$("#province").val(),start_time:$(".search input[name='start_time']").val()},
                dataType : "json", //返回数据形式为json
                success : function(result) {
                if (result) {
                      for(var i=0;i<result.length;i++){
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
		},
        tooltip: {
            trigger: 'item',
            formatter: "{b}<br/> {c}个 ({d}%)"
        },
        series: [
                 {
                     name:'',
                     type:'pie',
     				 center : ['center', '55%'],
                     avoidLabelOverlap: true,
                     label: {normal: {position: 'inner',formatter:'{d}%'}},
                     data:(function(){
         	            var arr=[];
                        $.ajax({
                        type : "post",
                        async : false, //同步执行
                        url : "${ctx}/manage/countUseTaking/getAreaUseTakingChartsData",
                        data : {province:$("#province").val(),start_time:$(".search input[name='start_time']").val()},
                        dataType : "json", //返回数据形式为json
                        success : function(result) {
                        if (result) {
                              for(var i=0;i<result.length;i++){
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
             ]
    }
	myChartA.setOption(optionA);
}

</script>
</body>
</html>
