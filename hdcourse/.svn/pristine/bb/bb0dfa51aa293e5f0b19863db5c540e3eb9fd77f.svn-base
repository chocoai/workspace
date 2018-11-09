<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通知管理页面</title>
</head>
<body>
<div class="mg15 txq_main">
	<form id="pageForm"  name="pageForm" action="${ctx}/manage/message/list" method="post">
	<input type="hidden" id="search_type" name="search_type"/>
	
    <div class="mgtb10 txq_txtbox clearfix">
        <p class="fl mgr10"><span>主题：</span><input name="theme" value="${theme }" type="text" style="width:150px;" class="inp focus"/></p>
        <p class="fl mgr10">
        	<span>发布时间：</span> 
            <input type="text" name="startTime" value="${startTime }" style="width: 100px;" readonly="readonly" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})" id="d4311" class="Wdate">
            <span>至</span> 
            <input type="text" name="endTime" value="${endTime }" style="width: 100px;" readonly="readonly" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})" id="d4312" class="Wdate">
        </p>
        <p class="fl mgr10"><span>有效状态：</span>
        	<select name="status" class="sel" style="width:150px;">
	        	<option value="" <c:if test="${empty status}">selected="selected"</c:if>>全部</option>
	        	<option value="0" <c:if test="${status=='0'}">selected="selected"</c:if>>有效</option>
	        	<option value="1" <c:if test="${status=='1'}">selected="selected"</c:if>>无效</option>
        	</select>
        </p>
        <p class="fl"><input type="button" onclick="search()" class="btn_blue" value="查询"/></p>
    </div>
    <div class="mgtb10">
		<div class="base_title"><strong>已发送通知列表</strong></div>
		<div class="clearfix mgtb10">
			<input type="button" class="btn_blue" value="发送通知" onclick="add()"/>&nbsp;&nbsp;
		</div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tr>
				<th width="5%"><input type="checkbox" name="ckAll"></th>
				<th width="25%">主题</th>
				<th width="10%">接收对象类型</th>
				<th width="30%">接收对象</th>
				<th width="10%">通知类型</th>
				<th width="10%">发送时间</th>
				<th width="5%">状态</th>
				<th width="5%">操作</th>
		    </tr>
		    <c:forEach items="${list}" var="obj" varStatus="status">
			    <tr>
					<td><input type="checkbox" name="ckItm" value="${obj.id}"></td>
					<td title="${obj.theme }">
						<a href="javascript:window.location.href='${ctx}/manage/message/detail?id=${obj.id}';">
						<c:if test="${fn:length(obj.theme)<=20}">${obj.theme }</c:if>
						<c:if test="${fn:length(obj.theme)>20}">${fn:substring(obj.theme,0,20)}...</c:if>
						</a>
					</td>
					<td>
						<c:if test="${obj.receiveObjType=='0' }">平台</c:if>
						<c:if test="${obj.receiveObjType=='1' }">个人</c:if>
						<c:if test="${obj.receiveObjType=='2' }">机构</c:if>
					</td>
					<td title='<c:if test="${obj.receiveObjType=='0' }">${obj.receivePlatformName }</c:if>
						<c:if test="${obj.receiveObjType=='1' }">${obj.receivePersonName }</c:if>
						<c:if test="${obj.receiveObjType=='2' }">${obj.receiveOrgName }</c:if>'>
						
						<c:if test="${obj.receiveObjType=='0' }">
							<c:if test="${fn:length(obj.receivePlatformName)<=20}">${obj.receivePlatformName }</c:if>
							<c:if test="${fn:length(obj.receivePlatformName)>20}">${fn:substring(obj.receivePlatformName,0,20)}...</c:if>
						</c:if>
						<c:if test="${obj.receiveObjType=='1' }">
							<c:if test="${fn:length(obj.receivePersonName)<=20}">${obj.receivePersonName }</c:if>
							<c:if test="${fn:length(obj.receivePersonName)>20}">${fn:substring(obj.receivePersonName,0,20)}...</c:if>
						</c:if>
						<c:if test="${obj.receiveObjType=='2' }">
							<c:if test="${fn:length(obj.receiveOrgName)<=20}">${obj.receiveOrgName }</c:if>
							<c:if test="${fn:length(obj.receiveOrgName)>20}">${fn:substring(obj.receiveOrgName,0,20)}...</c:if>
						</c:if>
					</td>
					<td>${obj.messageTypeName }</td>
					<td><fmt:formatDate value="${obj.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>
						<c:if test="${obj.status=='0' }">有效</c:if>
						<c:if test="${obj.status=='1' }">无效</c:if>
					</td>
					<td>
						<c:if test="${obj.status=='0' }"><a href="javascript:void(0);" class="mgr10" onclick="updateStatus('1','${obj.id}')">无效</a></c:if>
						<c:if test="${obj.status=='1' }"><a href="javascript:void(0);" class="mgr10" onclick="updateStatus('0','${obj.id}')">有效</a></c:if>
					</td>
			    </tr>
		    </c:forEach>
		</table>
    </div>
	<div class="base_page clearfix">
		<!-- 分页 -->
		<c:if test="${not empty page.totalPage && page.totalPage >= 1 }">
				<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
				currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
				action="${ctx}/manage/message/list"></newTag:page>
		</c:if>
	</div>
	</form>
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

function search(){
	$("#search_type").val(0);
	$("#pageForm").submit();
}

function getCheckValue(){
	var ids = "";
	$.each( $ckItm, function(i, ckItm){
		if(ckItm.checked){
			ids += "," + ckItm.value;
		}
	});
	if(ids != ""){
		return ids.substring(1);
	}
	return "";
}

function updateStatus(status,id){
	$.ajax({
		type : 'post',
		url : "${ctx}/manage/message/updateStatus",
		async: false,
		data : {
			"status" : status,
			"id" : id
		},
		dataType : 'text',
		success : function(text) {
			if(text=='success'){
				$("#pageForm").submit();
			}
		}
	});
}

function add(){
	window.location.href='${ctx}/manage/message/initAddMessage';
}

</script>
</body>
</html>