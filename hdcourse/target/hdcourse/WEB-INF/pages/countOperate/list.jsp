﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>区域统计</title>
</head>
<body>
<div class="mg15 txq_main">
	<input type="hidden" id="areaid"/>
	<input type="hidden" id="orderField" name="orderField" value="logintimes"/>
	<input type="hidden" id="orderState" name="orderState" value="desc"/>
	
	 <form id="pageForm"  name="pageForm" action="${ctx}/manage/countOperate/list" method="post">
    <div class="mgtb10 txq_txtbox clearfix" id="search" onselectstart="return false">
   
    	<div class="mgt10 clearfix">
	        <p class="fl mgr10"><span>所属区域：</span>
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
	        </p>
	        <p class="fl mgr10"><span>所属学校：</span>
	        	<input name="organame" type="text" style="width:150px;" class="inp focus"/>
	        </p>
	    </div>
	    <div class="mgt10 clearfix">
	        <p class="fl mgr10"><span>教师姓名：</span>
	        	<input name="username" type="text" style="width:150px;" class="inp focus"/>
	        </p>
	        <p class="fl mgr10"><span>教师账号：</span>
	        	<input name="usercode" type="text" style="width:150px;" class="inp focus"/>
	        </p>
	        <input type="submit" onclick="search()" class="btn_blue" value="查询"/>
<!-- 	        <p class="fl mgr10"><span>筛选：</span>
	        	<input name="notAllZero" value="1" type="checkbox" class="inp focus"/>
	        	不全为零
	        </p> -->
	        <%-- <p class="fl"><permission:button value="查询" name="button" method="search()" style="btn_blue"></permission:button></p> --%>
	    </div>
	   
    </div>
    <div class="mgtb10">
		<div class="base_title"><strong>互动课堂教师端${currentTime }运营情况报表</strong></div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<thead>
			<tr>
				<th width="5%" rowspan="2">序号</th>
				<th width="10%" rowspan="2">教师</th>
				<th width="10%" rowspan="2">账号</th>
				<th width="5%" rowspan="2">登录次数</th>
				<th width="15%" rowspan="2">最后登录时间</th>
				<th width="5%" rowspan="2">使用总时长</th>
				<th width="5%" rowspan="2">有效使用次数</th>
				<th width="5%" rowspan="2">有效使用时长</th>
				<th width="5%" rowspan="2">板书文件数</th>
				<th width="5%" rowspan="2">课堂实录数</th>
				<th width="5%" rowspan="2">电子书包授课</th>
				<th width="5%" rowspan="2">移动讲台授课</th>
				<th width="5%" rowspan="2">掌中黑板授课</th>
				<th width="5%" rowspan="2">答题器授课</th>
				<th width="10%" rowspan="2">操作</th>
		    </tr>
		    </thead>
		    <tbody>
	    	<c:if test="${fn:length(userOperateList) < 1}">
		    	<tr>
		    		<td colspan="10" style="color: red">暂无数据</td>
		    	</tr>
		    </c:if>
	    	<c:forEach items="${userOperateList}" var="userOperate" varStatus="status">
	    	<fmt:formatDate value="${userOperate.LAST_LOGIN_TIME}" type="date" var="dat" pattern="yyyy-MM-dd HH:mm:ss"/>
		    	<tr>
		    		<td>${status.index+1}</td>
		    		<td>${userOperate.USER_NAME}</td>
		    		<td>${userOperate.USER_ACCOUNT}</td>
		    		<td>${userOperate.ALL_LOGIN_NUM}</td>
		    		<td>${dat }</td>
		    		<td>${userOperate.ALL_LOGIN_USE_TIME}</td>
		    		<td>${userOperate.ALL_LOGIN_VALID_NUM}</td>
		    		<td>${userOperate.ALL_LOGIN_VALID_TIME}</td>
		    		<td>${userOperate.ALL_JXZS_BS_NUM}</td>
		    		<td>${userOperate.ALL_JXZS_KTSL_NUM}</td>
		    		<td>${userOperate.ALL_TERMINAL_DZSB_NUM}</td>
		    		<td>${userOperate.ALL_TERMINAL_YDJT_NUM}</td>
		    		<td>${userOperate.ALL_TERMINAL_ZZHB_NUM}</td>
		    		<td>${userOperate.ALL_TERMINAL_DTQ_NUM}</td>
		    		<td><input type="button" onclick="detail('${userOperate.USER_ID}', '${userOperate.USER_NAME}','${userOperate.USER_ACCOUNT}','${userOperate.GENDER}','${userOperate.PHONE_NUMBER}','${userOperate.EMAIL}','${userOperate.CITY_NAME}','${userOperate.ORGA_NAME}')" class="btn_blue fr mglr20" value="详情"></td>
		    	</tr>
		    </c:forEach>
		    </tbody>
		</table>
    </div>
    
	<div class="base_page clearfix">
		<!-- 分页 -->
		<c:if test="${fn:length(userOperateList) > 0}">
				<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
				currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
				action="${ctx}/manage/countOperate/list"></newTag:page>
		</c:if>
	</div>
	</form>
</div>

<input type="hidden" id="totalRows" name="totalRows"/>
<!--详情-->
<div class="popup jumpBox detail dis_none" style="width: 850px;">
    <div class="tit"><span class="fl">详情</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
    	<div class="base_title"><strong>基本信息</strong></div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="10%" align="right">教师姓名：</td>
               <td width="23%" align="left"><span id="username"></span></td>
               <td width="10%" align="right">用户账号：</td>
               <td width="23%" align="left"><span id="usercode"></span></td>
               <td width="10%" align="right">性别：</td>
               <td width="24%" align="left"><span id="gender"></span></td>
           </tr>
           <tr>
               <td align="right">年龄：</td>
               <td align="left"><span id="birthday"></span></td>
               <td align="right">联系电话：</td>
               <td align="left"><span id="mobnum"></span></td>
               <td align="right">联系邮箱：</td>
               <td align="left"><span id="email"></span></td>
           </tr>
            <tr>
               <td align="right">所属区域：</td>
               <td align="left"><span id="areaname"></span></td>
               <td align="right">所属学校：</td>
               <td align="left" colspan="3"><span id="organame"></span></td>
           </tr>
       </table>
       <div class="base_title"><strong>使用详情</strong></div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tbody id="funcList"></tbody>
           <!-- <tr>
               <td width="15%" align="right">【登录次数】</td>
               <td width="18%" align="left"><span id="logintimes"></span></td>
               <td width="15%" align="right">【最后登录时间】</td>
               <td width="18%" align="left"><span id="lastlogintime"></span></td>
               <td width="15%" align="right">【我的资源总数】</td>
               <td width="19%" align="left"><span id="coursecount"></span></td>
           </tr>
           <tr>
           	   <td align="right">【我的导学总数】</td>
               <td align="left"><span id="guidancecount"></span></td>
               <td align="right">【我的课件总数】</td>
               <td align="left"><span id="preparationcount"></span></td>
               <td align="right">【我的课堂】</td>
               <td align="left">板书：<span id="writingcount"></span>，实录：<span id="classroomcount"></span></td>
           </tr> -->
       </table>
    </div>
</div>

<script>
//tab
$("div[name='slide']").tabControl("*[name='slideTag'] a" , "*[name='slideCont']" , '.more');
//弹出层
$('*[name="roleChoose"]').each(function(){$(this).click(function(){$(".jumpBox").jumpBox(true);});});

var $ckAll = $("input[name='ckAll']");
var $ckItm = $("input[name='ckItm']");
var len = $ckItm.length;
$ckAll.click(function() {
    $ckItm.prop("checked",this.checked);
});
$ckItm.click(function() {
    var b=$ckItm.filter(":checked").length==len;
    var flag=$ckAll.prop("checked",b?true:false);
});

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

function search(){
	
}

//var dataArray = eval('('+'${jsonList}'+')');
var dataArray;

function getObjById(id){
	for(var i=0;i<dataArray.length;i++){
		if(id==dataArray[i].id){
			return dataArray[i];
		}
	}
}

function detail(id,name,account,gender,phone_number,email,city_name,orga_name){
	var user_id = id;
	$("#username").html(name);
	$("#usercode").html(account);
	var gen = gender;
	if(gen == "1"){
		$("#gender").html("男");
	}
	if(gen == "0"){
		$("#gender").html("女");
	}
	$("#mobnum").html(phone_number);
	$("#email").html(email);
	$("#areaname").html(city_name);
	$("#organame").html(orga_name);
	
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/countOperate/queryUserEvent",
		data : {
			//只统计教师数据
			"user_id":user_id,
		},
		async : true,
		dataType : 'json',
		success : function(data) {
			var htmlStr = '';
			if(data != null){
				if(data.list != null && data.list.length > 0){
					dataArray = data.list;
					for(var i=0;i<data.list.length;i++){
						var obj = data.list[i];
						htmlStr += '<tr>';
						htmlStr += '<td width="15%" align="left">'+obj.EVENT_NAME+'</td>';
						htmlStr += '<td width="18%" align="left">'+obj.ALL_EVENT_USE_NUM+'</td>';
						htmlStr += '</tr>';
					}
				}
			}
			
			if(htmlStr != ''){
				$("#funcList").html(htmlStr);
			}else{
				$("#funcList").html('<tr><td colspan="13" align="center"><font color="red">暂无数据</font></td></tr>');
			//	$("#base_page").html('');
			}
			
		}
	});
	$(".detail").jumpBox(true);
}

$(function(){
	
	
});
</script>
</body>
</html>