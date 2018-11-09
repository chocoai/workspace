<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>区域</title>
<script type="text/javascript" src="${ctx}/js/plupload-2.1.8/plupload.full.min.js"></script>
<style type="text/css">
.progress{ height: 4px; font-size: 0; line-height: 4px; background: orange; width: 0;}
.num{ margin-left: 20px;}
#file-list img{cursor: pointer;}
</style>
</head>
<body>
<div class="mg15 txq_main">
	<form id="pageForm"  name="pageForm" action="${ctx}/manage/version/specifyAreaUpgrades" method="post">
	<input type="hidden" id="softId" name="softId"value="${softId}"/>
    <div class="mgtb10 txq_txtbox clearfix">
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
        
        
        <input type="button" onclick="addSoftArea()" class="btn_blue" value="添加"/>
    </div>
    <div class="mgtb10">
		<div class="base_title"><strong>区域列表</strong></div>

		
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tr>
				<th width="10%">区域</th>
				<th width="10%">操作</th>
		    </tr>
		    <c:forEach items="${softAreaUpgradeList}" var="softAreaUpgrade">
			    <tr>
					<td>${softAreaUpgrade.areaName }</td>
					<td><a href="javascript:void(0);" onclick="deleteSoftArea('${softAreaUpgrade.softId}','${softAreaUpgrade.areaCode}')">删除</a></td>
			    </tr>
		    </c:forEach>
		</table>
    </div>
	<div class="base_page clearfix">
		<!-- 分页 -->
		<c:if test="${not empty page.totalPage && page.totalPage >= 1 }">
				<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
				currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
				action="${ctx}/manage/version/list"></newTag:page>
		</c:if>
	</div>
	</form>
</div>


<script>
//添加区域
function addSoftArea(){
	$("#pageForm").submit();
}

//删除区域
function deleteSoftArea(softId,areaCode){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/version/deleteSpecifyAreaUpgrades",
		data : {"softId" : softId,"areaCode" : areaCode},
		async : false,
		dataType : 'json',
		success : function(msg) {
			//alert(1)
			//if(msg=='success'){
			//	alert(2)
			//	$("#pageForm").submit();
			//}
		}
	});
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
});
</script>
</body>
</html>