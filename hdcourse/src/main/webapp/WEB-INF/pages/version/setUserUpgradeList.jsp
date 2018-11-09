<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>普通用户查询列表页面</title>
<script type="text/javascript" src="${ctx}/js/page.js"></script>
<style type="text/css">
.data_list thead,.txq_txtbox,#base_page
{
-moz-user-select: none;
}
</style>
</head>
<body>
<input type="hidden" id="softId" value="${softId}"/>
    <div class="txq_txtbox clearfix" onselectstart="return false">
		<p class="fl mgr10"><span>学校：</span>
			<input id="org_name" type="text" style="width:100px;" class="inp focus" placeholder="请输入学校名"/>
		</p>
		<p class="fl mgr10"><span>用户类型：</span>
			<select id="user_type" class="sel" style="width:80px;" id="user_type">
				<option value="">全部</option>
				<option value="0">学生</option>
				<option value="1">老师</option>
				<option value="2">学校管理员</option>
				<option value="3">机构管理员</option>
				<option value="4">超级管理员</option>
				<option value="5">家长</option>
			</select>
		</p>
		<p class="fl mgr10"><span>用户名：</span><input id="user_account" type="text" style="width:130px;" class="inp focus" placeholder="请输入用户名"/></p>
		<p class="fl mgr10"><input id="canUpgrade" type="checkbox" value="1" class="inp focus"/>已指定用户</p>
		<p class="fl mgr10"><input type="button" onclick="search()" class="btn_blue" value="查询"/></p>
		<p class="fl"><input type="button" onclick="make()" class="btn_blue" value="确定"/></p>
	</div>
	<div class="mgtb10" style="height: 300px;overflow: auto;">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<thead onselectstart="return false">
			<tr>
				<th width="5%"><input type="checkbox" name="ckAll"></th>
				<th width="15%">用户名</th>
				<th width="15%">姓名</th>
				<th width="15%">性别</th>
				<th width="20%">学校</th>
				<th width="15%">用户类型</th>
				<th width="15%">用户来源</th>
		    </tr>
		    </thead>
		    <tbody id="dataList"></tbody>
		</table>
		
	<div class="base_page clearfix" id="base_page" onselectstart="return false"></div>
	</div>
</body>
<script>
//tab
$("div[name='slide']").tabControl("*[name='slideTag'] a" , "*[name='slideCont']" , '.more');
//弹出层
$('*[name="roleChoose"]').each(function(){$(this).click(function(){$(".jumpBox").jumpBox(true);});});

var $ckAll = $("input[name='ckAll']");
$ckAll.bind("click",function() {
	$("input[name='ckItm']").prop("checked",this.checked);
});
$("input[name='ckItm']").live("click",function() {
    var b=$("input[name='ckItm']").filter(":checked").length==$("input[name='ckItm']").length;
    var flag=$ckAll.prop("checked",b?true:false);
});

//查询
function search(){
	resetPageParam();
	dataList();
}

function getCheckValue(){
	var ids = "";
	$.each( $("input[name='ckItm']").filter(":checked"), function(i, ckItm){
		ids += "," + ckItm.value;
	});
	if(ids != ""){
		return ids.substring(1);
	}
	return "";
}

function getAllCheckValue(){
	var allIds = "";
	$.each( $("input[name='ckItm']"), function(i, ckItm){
		allIds += "," + ckItm.value;
	});
	if(allIds != ""){
		return allIds.substring(1);
	}
	return "";
}

function loadList(){
	$ckAll.prop("checked",false);
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/version/querySetUserUpgrade",
		data : {
			"softId" : $("#softId").val(),
			"org_name" : $("#org_name").val(),
			"user_type" : $("#user_type").val(),
			"user_account" : $("#user_account").val(),
			"canUpgrade" : $("#canUpgrade:checked").val(),
			"currPage" : $('#currPage').val(),
			"pageSize" : $('#pageSize').val()
		},
		async : false,
		dataType : 'json',
		success : function(data) {
			var htmlStr = '';
			if(data != null){
				if(data.list != null && data.list.length > 0){
					for(var i=0;i<data.list.length;i++){
						var obj = data.list[i];
						htmlStr += '<tr>';
						htmlStr += '<td><input type="checkbox" '+(obj.canUpgrade=='1'?'checked="checked"':'')+' name="ckItm" value="'+obj.id+'"></td>';
						htmlStr += '<td>'+obj.user_account+'</td>';
						htmlStr += '<td>'+obj.real_name+'</td>';
						
						if(obj.gender=='1'){
							htmlStr += '<td>男</td>';
						}else{
							htmlStr += '<td>女</td>';
						}
						
						if(obj.org_name.length > 17){
							htmlStr += '<td title="'+obj.org_name+'">'+obj.org_name.substring(0,17)+'...</td>';
						}else{
							htmlStr += '<td>'+obj.org_name+'</td>';
						}
						
						if(obj.user_type=='0'){
							htmlStr += '<td>学生</td>';
						}else if(obj.user_type=='1'){
							htmlStr += '<td>老师</td>';
						}else if(obj.user_type=='2'){
							htmlStr += '<td>学校管理员</td>';
						}else if(obj.user_type=='3'){
							htmlStr += '<td>机构管理员</td>';
						}else if(obj.user_type=='4'){
							htmlStr += '<td>超级管理员</td>';
						}else if(obj.user_type=='5'){
							htmlStr += '<td>家长</td>';
						}else {
							htmlStr += '<td></td>';
						}
						
						htmlStr += '<td>'+obj.platform_id+'</td>';
						htmlStr += '</tr>';
					}
				}
				if(data.page != null){
					setPage($("#base_page"),data.page);
				}
			}
			
			if(htmlStr != ''){
				$("#dataList").html(htmlStr);
			}else{
				$("#dataList").html('<tr><td colspan="7" align="center"><font color="red">暂无数据</font></td></tr>');
				$("#base_page").html('');
			}
			if($("input[name='ckItm']").length > 0){
				var b=$("input[name='ckItm']").filter(":checked").length==$("input[name='ckItm']").length;
			    $ckAll.prop("checked",b?true:false);
			}
		}
	});
}

$(function(){
	dataList();
	
	
});

function make(){
	var obj = window.parent.contentWindow || window.parent;
	var ids = getCheckValue();
	var allIds = getAllCheckValue();
	obj.setUserUpgrade(allIds,ids); 
}
</script>
</html>