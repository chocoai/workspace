<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>按钮管理</title>
<link href="${ctx}/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">
<!--树形菜单-开始-->
<script type="text/javascript" src="${ctx}/js/zTree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree/jquery.ztree.exedit-3.5.js"></script>
<SCRIPT type="text/javascript">
var setting = {
	view: {
		dblClickExpand: false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback: {
		onClick: OnClick
	}
};

var zNodes,dataArray,currNode;
function OnClick(event, treeId, treeNode) {
	currNode = null;
	if(treeNode.level != 0){
		currNode = treeNode;
		loadButtonList(treeNode.id);
	}
};

/**
 * 加载模块下的按钮列表
 */
function loadButtonList(id){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/buttonManage/buttonList",
		data : {
			"MODULAR_ID" : id
		},
		async : false,
		dataType : 'json',
		success : function(arr) {
			var html = '';
			if(arr != null && arr != '[]' && arr.length>0){
				dataArray = arr;
				for(var i=0;i<arr.length;i++){
					var obj = arr[i];
					html += '<tr>';
					html += '<td>'+(i+1)+'</td>';
					html += '<td>'+obj.buttonName+'</td>';
					html += '<td>'+obj.sortNum+'</td>';
					html += '<td>'+obj.modularName+'</td>';
					html += '<td>'+ (obj.status=='0'?'有效':'无效') +'</td>';
					html += '<td>';
					html += '<a href="javascript:void(0);" class="mgr10" onclick="edit('+i+')">修改</a>';
					html += '<a href="javascript:void(0);" class="mgr10" onclick="del(\''+obj.id+'\')">删除</a>';
					html += '</td>';
					html += '</tr>';
				}
			}else{
				html += '<tr><td colspan="6">暂无数据！</td></tr>';
			}
			$("#buttonList").html(html);
		}
	});
}


$(document).ready(function(){
	loadTree();
	
	$(".add_edit input[name='save']").click(function(){
	var id=$("#id").val();
	var buttonName=$(".add_edit input[name='buttonName']").val();
	var sortNum=$(".add_edit input[name='sortNum']").val();
	var status=$(".add_edit select[name='status']").val();
	var modularId = $("#modularId").val();
	if($.trim(buttonName)==''){
		$(".add_edit input[name='buttonName']").siblings(".red").text("请填写按钮名称");
		return;
	}
	if($.trim(sortNum)==''){
		$(".add_edit input[name='sortNum']").siblings(".red").text("请填写排序");
		return;
	} else if(!/^[0-9]*$/.test(sortNum)) {
		$(".add_edit input[name='sortNum']").siblings(".red").text("排序只能为数字");
		return;
	}
	if($.trim(status)==''){
		$(".add_edit select[name='status']").siblings(".red").text("请输入状态");
		return;
	}
	if($.trim(modularId)==''){
		$(".add_edit input[name='modularName']").siblings(".red").text("请选择所属模块");
		return;
	}
	
	$(this).hide();
	$(this).next().show();
	$(this).next().next().hide();
	
	 setTimeout(function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/buttonManage/saveButton",
			data : {
				"id" : id,
				"buttonName" : buttonName,
				"sortNum"  : sortNum,
				"status"  : status,
				"modularId"  : modularId
			},
			async : false,
			dataType : 'text',
			success : function(msg) {
				if(msg=='success'){
					$(".add_edit").closeBox();
					loadButtonList(currNode.id);
				}
			}
		});
	}, 1); 
	
});
});

function loadTree() {
	$("#tree").html("加载中...");
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/modular/modularTree",
		data : {},
		async : false,
		dataType : 'json',
		success : function(msg) {
			if (msg == null || msg.zNodes == '[]' || msg.zNodes.length < 1) {
				$("#tree").html("没有数据！");
			} else {
				zNodes = msg.zNodes;
				$.fn.zTree.init($("#tree"), setting, zNodes);
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$("#tree").html('查询失败');
		}
	});

}

function emptyAddEdit(){
	 $(".add_edit input[type='hidden']").prop("value",'');
	 $(".add_edit input[type='text']").prop("value",'');
	 $(".add_edit .red").text('');
	
	 $(".add_edit input[name='save']").show();
	 $(".add_edit input[name='save']").next().hide();
	 $(".add_edit input[name='save']").next().next().show();
	 } 
function edit(index){
	emptyAddEdit();
	$(".add_edit .fl").text("修改菜单按钮");
	$("#id").val(dataArray[index].id);
	$("#modularId").val(dataArray[index].modularId);
	$(".add_edit input[name='buttonName']").val(dataArray[index].buttonName);
	$(".add_edit input[name='sortNum']").val(dataArray[index].sortNum);
	$(".add_edit input[name='modularName']").val(dataArray[index].modularName);
	$(".add_edit select[name='status']").val(dataArray[index].status);
	$(".add_edit").jumpBox(true);
}
function add(){
	if(!currNode) return;
	emptyAddEdit();
	$(".add_edit .fl").text("新增菜单按钮");
	$(".add_edit input[name='modularName']").val(currNode.name);
	$("#modularId").val(currNode.id);
	$(".add_edit").jumpBox(true);
}
function del(id){
	$.confirm("确定要删除选中的按钮吗？",function(){
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/buttonManage/delete",
			data : {"id" : id},
			async : false,
			dataType : 'text',
			success : function(msg) {
				if(msg=='success'){
					loadButtonList(currNode.id);
				}
			}
		});
	});
}
</SCRIPT>
</head>
<body>
<div class="mg15 txq_main">
	<div class="fl" style="width: 25%;border: 2px solid #dadada;">
		<div class="scroll-pane" style="width: 100%;height:450px;overflow: auto;">
             <ul id="tree" class="ztree" style="height: 97%;"></ul>
		</div>
	</div>
   	<div class="fr" style="width: 73%">
       	<div class="base_title"><strong><span style="font-weight:bold;"></span>按钮列表</strong></div>
       	<div class="clearfix mgtb10">
			<input type="button" class="btn_blue" value="新增" onclick="add()" />
		</div>
         	<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
         	<thead>
         		<tr>
					<th width="5%">编号</th>
					<th width="14%">按钮名称</th>
					<th width="10%">排序</th>
					<th width="15%">所属模块</th>
					<th width="10%">状态</th>
					<th width="10%">操作</th>
		    	</tr>
         	</thead>
		    <tbody id="buttonList"><tr><td colspan="6">暂无数据！</td></tr></tbody>
		</table>
	</div>
</div>

<!--新增-->
<div class="popup jumpBox add_edit dis_none">
    <div class="tit"><span class="fl">标题</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="25%" align="right"><span style="color: red;">*</span>按钮名称：</td>
               <td width="75%" align="left">
				<input type="text" name="buttonName" class="inp focus"/>
				<span class="red"></span>
               </td>
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>排序：</td>
               <td align="left">
	               <input type="text" name="sortNum" class="inp focus"/>
	               <span class="red"></span>
               </td>
           </tr>
           <tr>
               <td align="right"><span style="color: red;">*</span>所属模块：</td>
               <td align="left">
	               <input type="text" name="modularName" class="inp focus" disabled="true"/>
	               <span class="red"></span>
               </td>
           </tr>
           <tr>
              <td align="right"><span style="color: red;">*</span>状态：</td>
			  <td align="left" style="padding: 0px">
			      <select name="status" class="sel" style="width: 160px">
					<option value="0" <c:if test="${status == '0'}">selected="selected"</c:if>>有效</option>
					<option value="1" <c:if test="${status == '1'}">selected="selected"</c:if>>无效</option>
			      </select>
			      <span class="red"></span>
			  </td>
           </tr>
           <tr>
           	<td colspan="4" style="height: 53px;">
           		<input type="hidden" id="modularId"/>
				<input type="hidden" id="id"/>
           		<input type="button" name="save" class="btn_blue" value="保存" />
           		<span class="dis_none"><img src="${ctx}/images/loading.gif">&nbsp;&nbsp;&nbsp;系统正在处理中...</span>
           		<input type="button" name="close" class="btn_gray" value="取消" />
           	</td>
           </tr>
       </table>
    </div>
</div>
</body>
</html>