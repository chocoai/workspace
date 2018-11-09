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
	<form id="pageForm"  name="pageForm" action="${ctx}/manage/userActivityCount/userCount" method="post">
	<h2 class="adminTit bor_bottom"><em>使用时长统计</em><span class="fr"></span></h2>
    <div class="data_index mgt15">
        <table class="mod_table t_c mgauto" width="60%" style="border-collapse:collapse;">
            <tr>
                <td><a href="areaCount">
                	<p>区域统计</p>
                    <p class="num"><span class="cf00">${areaCount }</span>个</p>
                    <!-- <p class="incr t_r">本月新增<em class="mgl10">1</em><i class="up"></i></p> -->
                </a></td>
                <td><a  href="orgCount">
                	<p>学校统计</p>
                    <p class="num"><span class="c00f">${orgCount }</span>个</p>
                    <!-- <p class="incr t_r">本月新增<em class="mgl10">81</em><i class="up"></i></p> -->
                </a></td>
                <td><a href="javascript:void(0)" class="on">
                	<p>老师统计</p>
                    <p class="num"><span class="c3cc">${userCount }</span>个</p>
                    <!-- <p class="incr t_r">本月新增<em class="mgl10">120</em><i class="up"></i></p> -->
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
        <h3><i class="ico ico1"></i>当月数据指标</h3>
   </div>
   <div class="code_table data_index">
        <table class="mod_table t_c" width="100%" style="border-collapse:collapse;">
            <tr>
                <td width="10%">
                	<p>使用人数</p>
                    <p class="num"><span class="cf00">${userTotal}个</span></p>
                </td>
                <td width="10%">
                	<p>总时长</p>
                    <p class="num"><span class="c00f">${loginTakingTotal}次</span></p>
                </td>
            </tr>
        </table>
    </div>
    
    
    <div class="bo_title mgt10">
        <h3 class="mgr25"><i class="ico ico2"></i>数据趋势 TOP10</h3>
    </div>
    <div class="code_stateBox">
        <div class="stats_canvas" id="canvas" style="width:100%; height:450px"></div>
    </div>
  	<div class="bo_title mgt25">
        <h3><i class="ico ico3"></i>数据报表</h3>
        <div class="sort fr">
            <input type="button" onclick="exportUserCountExcel()" class="btn_blue" value="导出Excel"/>
        </div>
    </div>
    <div class="code_table">
        <table class="small_space data_list" width="100%" style="border-collapse:collapse;">
            <tr>
                <th width="15%">区域</th>
                <th width="15%">学校</th>
                <th width="7%">老师</th>
                <th width="8%">日期</th>
                <th width="9%">在线时长</th>
                <th width="9%">上月在线时长</th>
                <th width="7%">增长率</th>
                <th width="5%">操作</th>
            </tr>
            <c:forEach items="${userCountList}" var="userCount" varStatus="status">
             	<tr>
             		<td>${userCount.areaName}</td>
             		<td>${userCount.orgName}</td>
             		<td>${userCount.userName}</td>
             		<td>${time}</td>
             		<td>${userCount.loginTakingStr}</td>
             		<td>${userCount.previousLoginTakingStr}</td>
             		<td>${userCount.rate}</td>
             		<td><a href="javascript:void(0)" onclick="userInfo('${userCount.orgId}','${userCount.userId}')">用户信息</a></td>
             	</tr>
           </c:forEach>
            
        </table>
    </div>
    <div class="base_page clearfix">
		<!-- 分页 -->
		<c:if test="${fn:length(userCountList) > 0}">
				<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
				currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
				action="${ctx}/manage/userActivityCount/userCount"></newTag:page>
		</c:if>
	</div>
	</form>
</div>

<div class="popup jumpBox userInfo dis_none">
		<div class="tit">
			<span class="fl">用户信息</span><span class="close" name="close">X</span>
		</div>
		<div class="cont pd10">
			<table class="small_space data_list" width="100%"
				style="border-collapse: collapse;">
				<tr>
					<td width="15%" align="right"><span style="color: red;">*</span>用户名称：</td>
					<td width="15%" align="left"><span class="name"></span></td>
					
					<td width="15%" align="right"><span style="color: red;">*</span>帐号：</td>
					<td width="15%" align="left"><span class="userAccount"></span></td>
						
					<td width="15%" align="right"><span style="color: red;">*</span>所属平台：</td>
					<td width="15%"align="left"><span class="platformName"></span></td>	
				</tr>
				
				<tr>
					<td align="right"><span style="color: red;">*</span>学校：</td>
					<td align="left"><span class="organame"></span></td>	
					<td align="right"><span style="color: red;">*</span>省份：</td>
					<td align="left"><span class="provicename"></span></td>
					<td align="right"><span style="color: red;">*</span>城市：</td>
					<td align="left"><span class="cityname"></span></td>
				</tr>
				
				<tr>
					<td align="right"><span style="color: red;">*</span>邮箱：</td>
					<td align="left"><span class="email"></span></td>	
					<td align="right"><span style="color: red;">*</span>性别：</td>
					<td align="left"><span class="gender"></span></td>
					<td align="right"><span style="color: red;">*</span>电话：</td>
					<td align="left"><span class="mobnum"></span></td>
				</tr>
				
				<tr>
					<td  align="right"><span style="color: red;">*</span>最后登录时间：</td>
					<td  align="left"><span class="lastTime"></span></td>
					<td align="right"><span style="color: red;">*</span>总登录次数：</td>
					<td align="left" align="left" ><span class="loginCount"></span></td>
					<td align="right"><span style="color: red;">*</span>生日：</td>
					<td align="left"><span class="birthday"></span></td>
				</tr>


				<tr>
					<td align="right"><span style="color: red;">*</span>注册时间：</td>
					<td align="left" align="left" colspan="5"><span class="regiTime"></span></td>
				</tr>


				<tr>
					<td colspan="6" style="height: 53px;">
						<input type="button" name="close" class="btn_gray" value="关闭" />
					</td>
				</tr>
			</table>
		</div>
</div>


<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/fun.js"></script>
<script type="text/javascript" src="js/global.js"></script>
<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/echarts.min.js"></script>
<script>

function userInfo(orgId,userId){
	console.log(orgId,userId);
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/useCount/userInfo",
		data : {"userId" : userId,"orgId":orgId},
		async : false,
		dataType : 'json',
		success : function(app) {
			if(app != null){
				console.log(app.name);
				$(".userInfo .name").text(app.name);
				$(".userInfo .userAccount").text(app.userAccount);
				$(".userInfo .email").text(app.email);
				
				if(app.gender="0"){
					$(".userInfo .gender").text("女");
				}else{
					$(".userInfo .gender").text("男");					
				}
				
				$(".userInfo .mobnum").text(app.mobnum);
				$(".userInfo .birthday").text(app.birthday);
				$(".userInfo .lastTime").text(app.lastTime);
				$(".userInfo .loginCount").text(app.loginCount);
				$(".userInfo .platformName").text(app.platformName);
				$(".userInfo .regiTime").text(app.regiTime);
				
				
				$(".userInfo .organame").text(app.organame);
				$(".userInfo .provicename").text(app.provicename);
				$(".userInfo .cityname").text(app.cityname);
				
				$(".userInfo").jumpBox(true);
			}
		}
	});
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

function exportUserCountExcel(){
	var province=$('#province').val();
	var city=$('#city').val();
	var org =$('#org').val();
	var orgName = $('#orgName').text();
	var start_time = $(".search input[name='start_time']").val();
	//window.location.href='${ctx}/sys/user/exportexcel?name='+encodeURI(encodeURI(name))+'&type='+encodeURI(encodeURI(type))+'&status='+encodeURI(encodeURI(status));
	window.location.href='${ctx}/manage/userActivityCount/exportUserActivityCountExcel?province=' + province+'&cityCode='+city+'&org='+org+'&start_time='+start_time+"&orgName="+orgName;
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
		url : "${ctx}/manage/useCount/queryOrg",
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
		 tooltip: {
            trigger: 'axis',
            formatter:function(params,ticket,callback){
        		var mss = params[0].data
        		var days = parseInt(mss / (1000 * 60 * 60 * 24));
        		var hours = parseInt((mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        		var minutes = parseInt((mss % (1000 * 60 * 60)) / (1000 * 60));
        		var seconds = parseInt((mss % (1000 * 60)) / 1000);
        		var str =  days + " 天 " + hours + " 小时 " + minutes + " 分钟 " + seconds + " 秒 ";
        		return str;
        	},
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
		        url : "${ctx}/manage/userActivityCount/getUserActivityChartsData",
		        data : {province:$("#province").val(),city:$("#city").val(),org:$("#org").val(),start_time:$(".search input[name='start_time']").val()},
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
		        myChartA.hideLoading();
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
    		        url : "${ctx}/manage/userActivityCount/getUserActivityChartsData",
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
    		        myChartA.hideLoading();
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
