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


<div class="mg15 txq_main">

	<form id="pageForm"  name="pageForm" action="${ctx}/manage/activityCount/areaList" method="post">
		<div class="clearfix">
	        <p class="fl mgr10"><span>所属区域：</span>
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
				<select id="area" name="areaCode" class="sel" style="width:150px;">
					<option value="" <c:if test="${empty areaCode}">selected="selected"</c:if>>全部</option>
					<c:if test="${not empty areaList}">
					<c:forEach items="${areaList}" var="a" varStatus="st">
					<option value="${a.areaCode }" <c:if test="${a.areaCode==areaCode}">selected="selected"</c:if>>${a.areaName }</option>
					</c:forEach>
					</c:if>
				</select>
				
				
				<p class="fl mgr10"><span>学校：</span><input name="orgName" id="orgName"value="${orgName }" type="text" style="width:150px;" class="inp focus"/></p>
	        </p>
	    	
	    	<div class="mgtb10">
	    		<div class="clearfix mgtb10">
					<input type="button" class="btn_blue" value="查询" onclick="search()"/>
					<input type="button" class="btn_blue" value="导出excel" onclick="exportAreaActvityCountExcel()"/>
				</div>
	    	</div>
        </div>
        
        <div class="mgtb10">
			<div class="base_title"><strong>区域使用时长统计</strong></div>
	
			<div class="clearfix mgtb10">
				
			</div>
	       		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
	        	<tr>
					<th width="10%">机构名称</th>
					<th width="10%">区域名称</th>
					<th width="10%">活跃度</th>
					<th width="10%">上月活跃度</th>
					<th width="8%">平台</th>
					<th width="8%">年</th>
					<th width="6%">月</th>
			    </tr>
			    
			     <c:if test="${fn:length(activityCountList) < 1}">
			    	<tr>
			    		<td colspan="10" style="color: red">暂无数据</td>
			    	</tr>
			    </c:if>
			    
			    <c:forEach items="${activityCountList}" var="activityCount" varStatus="status">
			    	<tr>
			    		
			    		<td>${activityCount.orgName}</td>
			    		
			    		<td>${activityCount.areaName}</td>
			    		<td>
			    			${activityCount.useActivity}
			    		</td>
			    		<td>
			    			${activityCount.previousUseActivity}
			    		</td>
			    		<td>${activityCount.platformName}</td>
			    		<td>${activityCount.year}</td>
			    		<td>${activityCount.month}</td>
			    	</tr>
			    </c:forEach>
	        </table>
       </div>
        <div class="base_page clearfix">
		<!-- 分页 -->
		<c:if test="${fn:length(activityCountList) > 0}">
				<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
				currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
				action="${ctx}/manage/activityCount/areaList"></newTag:page>
		</c:if>
		</div>
	</form>
</div>

<script>

function exportAreaActvityCountExcel(){
	var province = $('#province').val();
	var city = $('#city').val();
	var area = $('#area').val();
	var orgName = $('#orgName').val();
	window.location.href='${ctx}/manage/activityCount/exportAreaActvityCountExcel?provinceCode='+province+'&cityCode='+city+'&areaCode='+area+'&orgName='+ orgName;
}

function search(){
	$("#search_type").val(0);
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

$(function(){
	$('#province').html(queryArea(1));
	
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
			$('#area').html('<option value="">全部</option>');
			return;		
		}
		$('#area').html(queryArea(3,$("#city").val()));
	}); 
});
</script>
</body>
</html>