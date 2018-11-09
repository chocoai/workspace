<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理页面</title>
<link href="${ctx}/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="mg15 txq_main">
	<form id="pageForm"  name="pageForm" action="${ctx}/manage/sysrole/list" method="post">
	<input type="hidden" id="search_type" name="search_type"/>
	
    <div class="mgtb10 txq_txtbox clearfix">
        <p class="fl mgr10"><span>角色名称：</span><input name="role_name" value="${role_name }" type="text" style="width:150px;" class="inp focus"/></p>
        <p class="fl mgr10">
        	<span>创建时间：</span> 
            <input type="text" name="startTime" value="${startTime }" style="width: 100px;" readonly="readonly" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'d4312\')||\'2020-10-01\'}'})" id="d4311" class="Wdate">
            <span>至</span> 
            <input type="text" name="endTime" value="${endTime }" style="width: 100px;" readonly="readonly" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'d4311\')}',maxDate:'2020-10-01'})" id="d4312" class="Wdate">
        </p>
        <p class="fl mgr10"><span>角色状态：</span>
        	<select name="status" class="sel" style="width:150px;">
	        	<option value="" <c:if test="${empty status}">selected="selected"</c:if>>全部</option>
	        	<option value="0" <c:if test="${status=='0'}">selected="selected"</c:if>>正常</option>
	        	<option value="1" <c:if test="${status=='1'}">selected="selected"</c:if>>禁用</option>
        	</select>
        </p>
        <p class="fl"><input type="button" onclick="search()" class="btn_blue" value="查询"/></p>
    </div>
    
    <div class="mgtb10">
		<div class="base_title"><strong>角色管理列表</strong></div>

		<div class="clearfix mgtb10">
			<input type="button" class="btn_blue" value="新增" onclick="add()"/>&nbsp;&nbsp;
			<input type="button" class="btn_blue" value="禁用" onclick="enableAndDisable('1')"/>&nbsp;&nbsp;
			<input type="button" class="btn_blue" value="启用" onclick="enableAndDisable('0')"/>&nbsp;&nbsp;
			<input type="button" class="btn_blue" value="删除" onclick="del()"/>
		</div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tr>
				<th width="4%"><input type="checkbox" name="ckAll"></th>
				<th width="15%">角色名称</th>
				<th width="35%">角色职责</th>
				<th width="15%">创建时间</th>
				<th width="15%">角色状态</th>
				<th width="16%">操作</th>
		    </tr>
		    <c:forEach items="${list}" var="obj" varStatus="status">
			    <tr>
					<td>
						<input type="checkbox" id="id_${status.index}" name="ckItm" value="${obj.id}">
						<input type="hidden" id="role_name_${status.index}" value="${obj.role_name }">
						<input type="hidden" id="role_comment_${status.index}" value="${obj.role_comment }">
					</td>
					<td>${obj.role_name }</td>
					<td title="${obj.role_comment }">
						<c:if test="${fn:length(obj.role_comment)<=30}">${obj.role_comment }</c:if>
						<c:if test="${fn:length(obj.role_comment)>30}">${fn:substring(obj.role_comment,0,30)}...</c:if>
					</td>
					<td>
						<fmt:formatDate value="${obj.create_time }" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td>
						<c:if test="${obj.status=='0' }">正常</c:if>
						<c:if test="${obj.status=='1' }">禁用</c:if>
					</td>
					<td>
						<a href="javascript:void(0);" class="mgr10" onclick="edit('${status.index}')">修改</a>
						<a href="javascript:void(0);" class="mgr10" onclick="editPermision('${obj.id}')">分配权限</a>
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
				action="${ctx}/manage/sysrole/list"></newTag:page>
		</c:if>
	</div>
	</form>
</div>

<!--新增  编辑-->
<div class="popup jumpBox add_edit dis_none">
	<input type="hidden" name="id">
    <div class="tit"><span class="fl">标题</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="25%" align="right"><span style="color: red;">*</span>角色名称：</td>
               <td width="75%" align="left">
				<input type="text" name="role_name" class="inp focus"/>
				<span class="red"></span>
               </td>
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>角色职责：</td>
               <td align="left">
               	<textarea name="role_comment" rows="5" cols="48" placeholder="请输入角色职责......"></textarea>
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

<!--分配权限-->
<div class="popup jumpBox editPermision dis_none" style="width: 300px;">
	<input type="hidden" name="role_id">
    <div class="tit"><span class="fl">分配权限</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
		<div style="width:100%; height:300px;overflow :auto;">
            <ul id="tree" class="ztree"></ul>
        </div>
        <div style="width:100%;text-align: center;">
            <input type="button" name="save" class="btn_blue" value="保存" />
           	<input type="button" name="close" class="btn_gray" value="取消" />
        </div>
    </div>
</div>

<!--树形菜单-开始-->
<script type="text/javascript" src="${ctx}/js/zTree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree/jquery.ztree.excheck-3.5.min.js"></script>
<SCRIPT type="text/javascript">
function editPermision(role_id) {
	loadTree(role_id);
	$(".editPermision input[name='role_id']").val(role_id);
	
	$(".editPermision").jumpBox(true);
}

	var zNodes;
	var setting = {
		async : {
			enable : false,
			dataType : 'json',
			url : "${ctx}/manage/sysrole/queryButton",
			autoParam:["id=modular_id"]
		},
		view : {
			showLine : false,
			showIcon : false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		check : {
			chkboxType : {
				"Y" : "ps",
				"N" : "ps"
			},
			chkStyle : 'checkbox',
			enable : true
		}
	};

	function loadTree(role_id) {
		//setting.async.otherParam = {"role_id":role_id}
		$("#tree").html("加载中...");
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/sysrole/queryModular",
			data : {"role_id" : role_id},
			async : false,
			dataType : 'json',
			success : function(msg) {
				if (msg == null || msg == '[]' || msg.length < 1) {
					$("#tree").html("没有数据！");
				} else {
					zNodes = msg;
					$.fn.zTree.init($("#tree"), setting, zNodes);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$("#tree").html('查询失败');
			}
		});

	}
	
	$(function(){
		$(".editPermision input[name='save']").click(function(){
			var role_id=$(".editPermision input[name='role_id']").val();
			
			var treeObj=$.fn.zTree.getZTreeObj("tree");
            var nodes=treeObj.getCheckedNodes(true);
            var modularIds = "";
            var buttonIds = "";
            for(var i=0;i<nodes.length;i++){
            	if(nodes[i].type=='modular'){
            		modularIds += nodes[i].id+",";
            	}else if(nodes[i].type=='button'){
            		buttonIds += nodes[i].pId+"_"+nodes[i].id+",";
            	}
            }
			
            $.ajax({
				type : "POST",
				url : "${ctx}/manage/sysrole/saveRolePermission",
				data : {
					"role_id" : role_id,
					"modularIds" : modularIds,
					"buttonIds" : buttonIds
				},
				async : false,
				dataType : 'text',
				success : function(msg) {
					if(msg=='success'){
						$(".editPermision").closeBox();
						$.alert("分配权限成功");
					}
				}
			});
			
		});
	});

</SCRIPT>

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
	$(".add_edit input[type='hidden']").val('');
	$(".add_edit input[type='text']").val('');
	$(".add_edit textarea").val('');
	
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
	$(".add_edit input[name='role_name']").val($("#role_name_"+index).val());
	$(".add_edit textarea[name='role_comment']").val($("#role_comment_"+index).val());
	$(".add_edit").jumpBox(true);
}

$(document).ready(function(){ 
	
	$(".add_edit input[name='save']").click(function(){
		var id=$(".add_edit input[name='id']").val();
		var role_name=$(".add_edit input[name='role_name']").val();
		var role_comment=$(".add_edit textarea[name='role_comment']").val();
		
		if($.trim(role_name)==''){
			$(".add_edit input[name='role_name']").siblings(".red").text("请填写角色名称");
			return;
		}
		if($.trim(role_comment)==''){
			$(".add_edit textarea[name='role_comment']").siblings(".red").text("请填写角色职责");
			return;
		}
		
		$(this).hide();
		$(this).next().show();
		$(this).next().next().hide();
		
		setTimeout(function(){
			$.ajax({
				type : "POST",
				url : "${ctx}/manage/sysrole/save",
				data : {
					"id" : id,
					"role_name"  : role_name,
					"role_comment"  : role_comment
				},
				async : false,
				dataType : 'text',
				success : function(msg) {
					if(msg=='success'){
						$(".add_edit").closeBox();
						$("#pageForm").submit();
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
		url : "${ctx}/manage/sysrole/enableAndDisable",
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
	$.confirm("确定要删除选中的角色信息吗？",function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/sysrole/delete",
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
</script>
</body>
</html>