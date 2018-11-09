<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员管理页面</title>
</head>
<body>
<div class="mg15 txq_main">
	<input type="hidden" id="msg" value="${msg}"/>
    <form id="pageForm"  name="pageForm" action="${ctx}/manageUser/list" method="post">
	<input type="hidden" id="search_type" name="search_type"/>
	
    <div class="mgtb10 txq_txtbox clearfix">
        <p class="fl mgr10"><span>用户名：</span><input id="account" name="account" value="${account }" type="text" style="width:150px;" class="inp focus" placeholder="请输入用户名"/></p>
        <p class="fl mgr10"><span>用户角色：</span><input id="role_name" name="role_name" value="${role_name }" type="text" style="width:150px;" class="inp focus"/></p>
        <p class="fl mgr10"><span>账号状态：</span>
        	<select name="status" class="sel" style="width:150px;" id="status">
	        	<option value="" <c:if test="${empty status}">selected="selected"</c:if>>全部</option>
	        	<option value="0" <c:if test="${status=='0'}">selected="selected"</c:if>>正常</option>
	        	<option value="1" <c:if test="${status=='1'}">selected="selected"</c:if>>禁用</option>
        	</select>
        </p>
        <p class="fl"><input type="button" onclick="search()" class="btn_blue" value="查询"/></p>
    </div>
    <div class="mgtb10">
		<div class="base_title"><strong>管理员列表</strong></div>

		<div class="clearfix mgtb10">
			<input type="button" class="btn_blue" value="新增" onclick="add()"/>&nbsp;&nbsp;
			<input type="button" class="btn_blue" value="禁用" onclick="enableAndDisable('1')"/>&nbsp;&nbsp;
			<input type="button" class="btn_blue" value="启用" onclick="enableAndDisable('0')"/>&nbsp;&nbsp;
			<input type="button" class="btn_blue" value="删除" onclick="del()"/>&nbsp;&nbsp;
			<input type="button" class="btn_blue" value="导入" onclick="importexcel()"/>&nbsp;&nbsp;
			<input type="button" class="btn_blue" value="导出" onclick="exportexcel()"/>
			<input type="button" class="btn_blue" value="授予角色" onclick="grantRole()"/>
		</div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tr>
				<th width="4%"><input type="checkbox" name="ckAll"></th>
				<th width="10%">用户名</th>
				<th width="10%">姓名</th>
				<th width="31%">用户角色</th>
				<th width="8%">账号状态</th>
				<th width="8%">创建时间</th>
				<th width="8%">最后登录时间</th>
				<th width="6%">登录次数</th>
				<th width="15%">操作</th>
		    </tr>
		    <c:forEach items="${list}" var="obj" varStatus="status">
			    <tr>
					<td>
						<input type="checkbox" id="id_${status.index}" name="ckItm" value="${obj.id}">
						<input type="hidden" id="account_${status.index}" value="${obj.account}">
						<input type="hidden" id="user_name_${status.index}" value="${obj.user_name}">
						<input type="hidden" id="password_${status.index}" value="${obj.password}">
					</td>
					<td>${obj.account }</td>
					<td>${obj.user_name }</td>
					<td title="${obj.role_name }">
						<c:if test="${empty obj.role_name}">暂未授予角色</c:if>
						<c:if test="${not empty obj.role_name && fn:length(obj.role_name)<=25}">${fn:substring(obj.role_name,0,fn:length(obj.role_name))}</c:if>
						<c:if test="${fn:length(obj.role_name)>25}">${fn:substring(obj.role_name,0,25)}...</c:if>
					</td>
					<td>
						<c:if test="${obj.status=='0' }">正常</c:if>
						<c:if test="${obj.status=='1' }">禁用</c:if>
					</td>
					<td><fmt:formatDate value="${obj.create_time }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${obj.last_time }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${obj.login_count }</td>
					<td>
						<a href="javascript:void(0);" class="mgr10" onclick="edit('${status.index}')">修改</a>
						<a href="javascript:void(0);" class="mgr10" onclick="grantRole('${obj.id}')">授予角色</a>
						<c:if test="${obj.status=='0' }"><a href="javascript:void(0);" class="mgr10" onclick="enableAndDisable('1','${obj.id}')">禁用</a></c:if>
						<c:if test="${obj.status=='1' }"><a href="javascript:void(0);" class="mgr10" onclick="enableAndDisable('0','${obj.id}')">启用</a></c:if>
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
				action="${ctx}/manageUser/list"></newTag:page>
		</c:if>
	</div>
	</form>
</div>
<!--导入excel-->
<div class="popup jumpBox importexcel dis_none">
	<input type="hidden" name="id">
    <div class="tit"><span class="fl">导入excel文件</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
    <form action="${ctx}/manageUser/importexcel"  method="post" enctype ="multipart/form-data" id="importexcel" > 
    <table class="small_space data_list" width="100%" style="border-collapse:collapse;">
    <tr>
     <td align="right"><span style="color: red;"></span>下载导入模板：</td>
     <td align="left">
     <a href="${ctx}/manageUser/uploadexcel"">管理员信息模板</a>
     </td>
     </tr>
     <tr>
     <td align="right"><span style="color: red;"></span>选择导入文件：</td>
     <td align="left">
    <input id="file"  name="file" type="file" onchange="javascript:checkfileextend(this);" style="width:250px;"/>
    <span style="color:red">文件格式：.xls、.xlsx</span>
     </td>
     </tr>
     <tr>
        	<td colspan="2" style="height: 53px;">
        		 <input type="submit" name="Button1" value="导入" id="Button1"  class="btn_blue"/>
        		<span class="dis_none"><img src="${ctx}/images/loading.gif">&nbsp;&nbsp;&nbsp;系统正在处理中...</span>
        		<input type="button" name="close" class="btn_gray" value="取消" />
        	</td>
     </tr>
     </table>
    </form>
    </div>
</div>

<!--新增  编辑-->
<div class="popup jumpBox add_edit dis_none">
	<input type="hidden" name="id">
    <div class="tit"><span class="fl">标题</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="25%" align="right"><span style="color: red;">*</span>用户名：</td>
               <td width="75%" align="left">
				<input type="text" name="account" class="inp focus"/>
				<span class="red"></span>
               </td>
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>真实姓名：</td>
               <td align="left">
               	<input type="text" name="user_name" class="inp focus"/>
               	<span class="red"></span>
               </td>
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>账号密码：</td>
               <td align="left">
               	<input type="text" name="password" class="inp focus"/>
               	<span class="red"></span>
               </td>
           </tr>
           <tr>
           	<td colspan="2" style="height: 53px;">
           		<input type="button" name="save" class="btn_blue" value="保存" />
           		<span class="dis_none"><img src="${ctx}/images/loading.gif">&nbsp;&nbsp;&nbsp;系统正在处理中...</span>
           		<input type="button" name="close" class="btn_gray" value="取消" />
           	</td>
           </tr>
       </table>
    </div>
</div>

<!--授予角色-->
<div class="popup jumpBox grantRole dis_none" style="width: 300px;">
    <div class="tit"><span class="fl">授予角色</span><span class="close" name="close">X</span></div>
    <div class="cont pd10" style="text-align: center;">
    	<div style="height:300px;overflow :auto;">
    		<table id="roleData" style="width: 100%">
    		</table>
        </div>
        <div>
            <input type="button" name="save" class="btn_blue" value="保存" />
           	<input type="button" name="close" class="btn_gray" value="取消" />
        </div>
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

function emptyAddEdit(){
	$(".add_edit input[type='hidden']").prop("value",'');
	$(".add_edit input[type='text']").prop("value",'');
	$(".add_edit .red").text('');
	
	$(".add_edit input[name='save']").show();
	$(".add_edit input[name='save']").next().hide();
	$(".add_edit input[name='save']").next().next().show();
}

function add(){
	emptyAddEdit();
	$(".add_edit .fl").text("新增");
	$(".add_edit").jumpBox(true);
}

function edit(index){
	emptyAddEdit();
	$(".add_edit .fl").text("修改");
	
	$(".add_edit input[name='id']").val($("#id_"+index).val());
	$(".add_edit input[name='account']").val($("#account_"+index).val());
	$(".add_edit input[name='user_name']").val($("#user_name_"+index).val());
	$(".add_edit input[name='password']").val($("#password_"+index).val());
	$(".add_edit").jumpBox(true);
}

$(document).ready(function(){ 
	$(".add_edit input[name='save']").click(function(){
		var id=$(".add_edit input[name='id']").val();
		var account=$(".add_edit input[name='account']").val();
		var user_name=$(".add_edit input[name='user_name']").val();
		var password=$(".add_edit input[name='password']").val();
		
		if($.trim(account)==''){
			$(".add_edit input[name='account']").siblings(".red").text("请填写用户名");
			return;
		}
		if($.trim(user_name)==''){
			$(".add_edit input[name='user_name']").siblings(".red").text("请填写真实姓名");
			return;
		}
		if($.trim(password)==''){
			$(".add_edit input[name='password']").siblings(".red").text("请填写密码");
			return;
		}
		
		$(this).hide();
		$(this).next().show();
		$(this).next().next().hide();
		
		setTimeout(function(){
			$.ajax({
				type : "POST",
				url : "${ctx}/manageUser/save",
				data : {
					"id" : id,
					"account"  : account,
					"user_name"  : user_name,
					"password"  : password
				},
				async : false,
				dataType : 'text',
				success : function(msg) {
					if(msg=='success'){
						$(".add_edit").closeBox();
						$("#pageForm").submit();
					}
					if(msg=='fail'){
						$(".add_edit input[name='account']").siblings(".red").text("该用户名已经存在！");
						$(".add_edit input[name='save']").show();
						$(".add_edit input[name='save']").next().hide();
						$(".add_edit input[name='save']").next().next().show();
					}
				}
			});
		}, 1);
		
	});
});

function enableAndDisable(status,id){
	var ids;
	if(id == null){
		ids = getCheckValue();
	}else{
		ids = id;
	}
	if(ids==""){
		$.alert("请至少选择一项！");
		return;
	}
	$.ajax({
		type : "POST",
		url : "${ctx}/manageUser/enableAndDisable",
		data : {"ids" : ids,"status" : status},
		async : false,
		dataType : 'text',
		success : function(msg) {
			if(msg=='success'){
				$("#pageForm").submit();
			}
		}
	});
}

function del(){
	var ids = getCheckValue();
	if(ids==""){
		$.alert("请至少选择一项！");
		return;
	}
	$.confirm("确定要删除选中的管理员信息吗？",function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manageUser/delete",
			data : {"ids" : ids},
			async : false,
			dataType : 'text',
			success : function(msg) {
				if(msg=='success'){
					$("#pageForm").submit();
				}
			}
		});
	});
}

function exportexcel(){
	var name=$('#account').val();
	var type=$('#role_name').val();
	var status=$('#status').val();
	//window.location.href='${ctx}/manageUser/exportexcel?name='+encodeURI(encodeURI(name))+'&type='+encodeURI(encodeURI(type))+'&status='+encodeURI(encodeURI(status));
	window.location.href='${ctx}/manageUser/exportexcel?name='+name+'&type='+type+'&status='+status;
}
function importexcel(){
	$(".importexcel").jumpBox(true);
}

//导入excel时检查后缀名是否符合
function checkfileextend(obj){
	var file =  obj.value;
	var extend = file.substring(file.lastIndexOf(".")+1);
	if(extend != "xls" && extend != "xlsx" && extend.length != 0){
		alert("请上传后缀名为xls或xlsx的文件!");
		var form = document.getElementById("importexcel");
		form.reset();
		return false;
	}
	return true;
}

var user_ids;
function grantRole(user_id){
	var ids = getCheckValue();
	if(user_id==null && ids==""){
		$.alert("请至少选择一项！");
		return;
	}else if(user_id != null){
		user_ids = user_id;
	}else{
		user_ids = ids;
	}
	
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/sysrole/queryUserRole",
		data : {"user_id" : user_id},
		async : false,
		dataType : 'json',
		success : function(data) {
			if (data == null || data == '[]' || data.length < 1) {
				$.alert("没有角色数据");
			}else{
				var html = '';
				for(var i=0;i<data.length;i++){
					html += '<tr>';
					html +='<td align="right" width="30%"><input type="checkbox" value="'+data[i].id+'" '+(data[i].checked=='1'?'checked="checked"':'')+' name="role"></td>';
					html +='<td align="left" width="70%">'+data[i].role_name+'</td>';
					html += '</tr>';
				}
				$("#roleData").html(html);
			}
			$(".grantRole").jumpBox(true);
		}
	});
	
}

$(function(){
	$(".grantRole input[name='save']").click(function(){
		var $ckRole = $("input[name='role']");
		var role_ids = '';
		$.each( $ckRole, function(i, ckRole){
			if(ckRole.checked){
				role_ids += "," + ckRole.value;
			}
		});
		if(role_ids != ""){
			role_ids = role_ids.substring(1);
		}
		
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/sysrole/grantRole",
			data : {"user_ids" : user_ids,"role_ids" : role_ids},
			async : false,
			dataType : 'text',
			success : function(msg) {
				if (msg=="success") {
					$(".grantRole").closeBox();
					$("#pageForm").submit();
				}
			}
		});
	});
	
	//页面消息提示
	if($("#msg").val()!=''){
		$.alert($("#msg").val());
	}
	
});
</script>
</body>
</html>